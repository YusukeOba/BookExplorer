package tokyo.oversoftware.bookexplorer.di

import androidx.lifecycle.ViewModelProvider
import tokyo.oversoftware.bookexplorer.model.datastore.remote.ApiClient
import tokyo.oversoftware.bookexplorer.model.datastore.remote.BooksRemoteDataSource
import tokyo.oversoftware.bookexplorer.model.datastore.remote.GoogleBooksDataSource
import tokyo.oversoftware.bookexplorer.model.repository.BooksRepository
import tokyo.oversoftware.bookexplorer.model.repository.GoogleBooksRepository
import tokyo.oversoftware.bookexplorer.viewmodel.SearchViewModelFactory

/**
 * アプリで使う実装クラスの依存関係をすべてここで解決する
 */
object DependencyInjection {

    private val remoteDataSource: BooksRemoteDataSource = GoogleBooksDataSource(
        apiService = ApiClient.build()
    )

    private val remoteRepository: BooksRepository = GoogleBooksRepository(
        remoteDataSource = remoteDataSource
    )

    private val searchViewModelFactory: ViewModelProvider.Factory = SearchViewModelFactory(
        repository = remoteRepository
    )

    /**
     * 検索用ViewModelの取得
     */
    fun searchViewModelFactory(): ViewModelProvider.Factory {
        return searchViewModelFactory
    }

}