package tokyo.oversoftware.bookexplorer

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import tokyo.oversoftware.bookexplorer.entity.Books
import tokyo.oversoftware.bookexplorer.entity.DisplayableBook
import tokyo.oversoftware.bookexplorer.model.repository.BooksRepository
import tokyo.oversoftware.bookexplorer.viewmodel.SearchViewModel
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

/**
 * 非同期テスト用のObserver
 * MutableLiveData#postValue のような非同期で変わりうる処理の場合はJUnitでそのままテストが出来ないので
 * CountDownLatchを利用したObserverを作成する
 */
class TestObserver<T>(count: Int = 1) : Observer<T> {

    private val latch: CountDownLatch = CountDownLatch(count)

    private val values = mutableListOf<T?>()

    override fun onChanged(t: T?) {
        values.add(t)
        latch.countDown()
    }

    fun get(): T? {
        if (values.size == 0) {
            throw IllegalStateException("onChanged is not called.")
        }
        return values[values.size - 1]
    }

    fun await(timeout: Long = 1, unit: TimeUnit = TimeUnit.SECONDS) {
        if (!latch.await(timeout, unit)) {
            throw TimeoutException()
        }
    }
}

class ViewModelTest {

    @Rule
    @JvmField
    val rule: TestRule = InstantTaskExecutorRule()

    private class EmptyDataRepository : BooksRepository {
        override fun findBooks(searchKeyword: String): Single<Books> {
            return Single.just(Books(kind = "", totalItems = 0, items = emptyList()))
        }
    }

    private class InvalidDataRepository : BooksRepository {
        override fun findBooks(searchKeyword: String): Single<Books> {
            return Single.error(Throwable("Invalid Response"))
        }
    }

    private class NormalDataRepository : BooksRepository {
        override fun findBooks(searchKeyword: String): Single<Books> {
            return Single.just(Constants.mockBook)
        }
    }

    @Test
    fun `Validate success Empty Response`() {
        val viewModel = SearchViewModel(repository = EmptyDataRepository())
        // 変数初期化時, 検索開始時, 検索終了時で3回値が流れるので3回分待つ
        val observer = TestObserver<List<DisplayableBook>>(count = 3)
        viewModel.items.observeForever(observer)
        viewModel.onSearch(Observable.just("example"))
        observer.await()
        Assert.assertEquals(observer.get()?.size, 0)
    }

    @Test
    fun `Validate success Normal Response`() {
        val viewModel = SearchViewModel(repository = NormalDataRepository())
        // 変数初期化時, 検索開始時, 検索終了時で3回値が流れるので3回分待つ
        val observer = TestObserver<List<DisplayableBook>>(count = 3)
        viewModel.items.observeForever(observer)
        viewModel.onSearch(Observable.just("example"))
        observer.await()
        Assert.assertEquals(observer.get()?.last()?.title, "ExampleBook")
    }

    @Test
    fun `Validate Error Response`() {
        val viewModel = SearchViewModel(repository = InvalidDataRepository())
        // 変数初期化時, 検索開始時, エラーで3回値が流れるので3回分待つ
        val observer = TestObserver<Boolean>(count = 3)
        viewModel.onError.observeForever(observer)
        viewModel.onSearch(Observable.just("example"))
        Assert.assertEquals(observer.get(), false)
        observer.await()
        Assert.assertEquals(observer.get(), true)
    }
}