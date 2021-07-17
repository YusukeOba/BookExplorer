package tokyo.oversoftware.bookexplorer

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.blockingSubscribeBy
import org.junit.Assert
import org.junit.Test
import tokyo.oversoftware.bookexplorer.entity.Books
import tokyo.oversoftware.bookexplorer.model.datastore.remote.BooksRemoteDataSource
import tokyo.oversoftware.bookexplorer.model.repository.GoogleBooksRepository

class RepositoryTest {

    private class EmptyDataSource : BooksRemoteDataSource {
        override fun fetchBooks(searchKeyword: String): Single<Books> {
            return Single.just(Books(kind = "", totalItems = 0, items = emptyList()))
        }
    }

    private class InvalidDataSource : BooksRemoteDataSource {
        override fun fetchBooks(searchKeyword: String): Single<Books> {
            return Single.error(Throwable("Invalid Response"))
        }
    }

    private class CacheCheckDataSource : BooksRemoteDataSource {

        var count = 0

        override fun fetchBooks(searchKeyword: String): Single<Books> {
            // ２回目以降は空のレスポンスを返すことでキャッシュロジックが有効かを評価する
            if (count == 0) {
                count += 1
                return Single.just(Constants.mockBook)
            } else {
                return Single.just(Books(kind = "", totalItems = 0, items = emptyList()))
            }
        }
    }

    @Test
    fun `Validate success Empty Response`() {
        val repository = GoogleBooksRepository(remoteDataSource = EmptyDataSource())
        val result = repository.findBooks(searchKeyword = "").blockingGet()
        Assert.assertEquals(result.items.size, 0)
    }

    @Test
    fun `Validate Cache Logic`() {
        val repository = GoogleBooksRepository(remoteDataSource = CacheCheckDataSource())
        val resultA = repository.findBooks(searchKeyword = "YusukeOba").blockingGet()
        val resultB = repository.findBooks(searchKeyword = "YusukeOba").blockingGet()
        val resultC = repository.findBooks(searchKeyword = "C").blockingGet()
        Assert.assertEquals(resultA.items[0].volumeInfo?.title, "ExampleBook")
        // １，２回目のデータはキーワードが同じなのでレスポンスも同じか
        Assert.assertEquals(resultB.items[0].volumeInfo?.title, "ExampleBook")
        // ３回目のデータはキーワードが違うのでレスポンスは空か
        Assert.assertEquals(resultC.items.size, 0)
    }

    @Test
    fun `Validate Invalid Response`() {
        val repository = GoogleBooksRepository(remoteDataSource = InvalidDataSource())
        var throwable: Throwable? = null
        val result =
            repository.findBooks(searchKeyword = "")
                .blockingSubscribeBy(onError = {
                    throwable = it
                })
        Assert.assertEquals(throwable?.message, "Invalid Response")
    }
}