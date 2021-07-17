package tokyo.oversoftware.bookexplorer.model.repository

import android.util.Log
import io.reactivex.rxjava3.core.Single
import tokyo.oversoftware.bookexplorer.entity.Books
import tokyo.oversoftware.bookexplorer.model.datastore.remote.BooksRemoteDataSource

/**
 * 書籍情報を取得するRepository
 * 依存元からデータをどこから取得しているかをカプセル化するために利用する
 */
interface BooksRepository {
    /**
     * 書籍情報を取得する
     * @param searchKeyword: 検索キーワード
     */
    fun findBooks(searchKeyword: String): Single<Books>
}

/**
 * Googleから書籍情報を取得するデータソース
 * MEMO: 現在は`RemoteDataSource`しかコンストラクタで注入していないが、
 * 規模の大きなアプリであればCacheDataSourceなども注入する
 */
class GoogleBooksRepository(private val remoteDataSource: BooksRemoteDataSource) : BooksRepository {

    // キャッシュデータ
    // 検索ワードと書籍データのペア。画面回転時のキャッシュに利用する
    var cache: Pair<String, Books>? = null

    /**
     * 書籍情報を取得する
     * @param searchKeyword: 検索キーワード
     */
    override fun findBooks(searchKeyword: String): Single<Books> {
        // キャッシュがあればそれを返す
        if (searchKeyword == cache?.first) {
            return Single.just(cache?.second)
        }

        return remoteDataSource.fetchBooks(searchKeyword = searchKeyword)
            .doOnSuccess {
                // 初回か別のキーワードであればメモリに入れておく
                val differentSearchWord: Boolean = searchKeyword != cache?.first
                if (cache == null || differentSearchWord) {
                    cache = Pair(searchKeyword, it)
                }
            }
    }
}