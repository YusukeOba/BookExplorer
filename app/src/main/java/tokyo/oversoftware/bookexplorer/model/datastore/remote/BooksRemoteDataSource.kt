package tokyo.oversoftware.bookexplorer.model.datastore.remote

import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import tokyo.oversoftware.bookexplorer.entity.Books

/**
 * 書籍データをリモートから取得するデータソース
 */
interface BooksRemoteDataSource {
    /**
     * 書籍データを取得する
     * @param searchKeyword 検索キーワード
     */
    fun fetchBooks(searchKeyword: String): Single<Books>
}

/**
 * Google Books Apiを利用した書籍データソース
 */
class GoogleBooksDataSource(// Retrofitのインタフェース
    private val apiService: ApiClient.ApiService
) : BooksRemoteDataSource {

    override fun fetchBooks(searchKeyword: String): Single<Books> {
        return apiService.volumes(name = searchKeyword)
    }
}