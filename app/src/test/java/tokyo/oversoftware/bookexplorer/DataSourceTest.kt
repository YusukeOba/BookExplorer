package tokyo.oversoftware.bookexplorer

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.blockingSubscribeBy
import org.junit.Assert
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.mock.BehaviorDelegate
import retrofit2.mock.Calls
import retrofit2.mock.MockRetrofit
import retrofit2.mock.NetworkBehavior
import tokyo.oversoftware.bookexplorer.entity.Books
import tokyo.oversoftware.bookexplorer.model.datastore.remote.ApiClient
import tokyo.oversoftware.bookexplorer.model.datastore.remote.GoogleBooksDataSource

/**
 * 不正データのモック
 */
private class InvalidRetrofitResponse(
    private val delegate: BehaviorDelegate<ApiClient.ApiService>
) : ApiClient.ApiService {
    override fun volumes(name: String, startIndex: Int, limit: Int): Single<Books> {
        return delegate.returning(
            Calls.failure<Books>(Error("Invalid Response"))
        ).volumes(name = name)
    }
}

/**
 * 空データのモック
 */
private class EmptyRetrofitResponse(
    private val delegate: BehaviorDelegate<ApiClient.ApiService>
) : ApiClient.ApiService {
    override fun volumes(name: String, startIndex: Int, limit: Int): Single<Books> {
        return delegate.returningResponse(
            Books(
                kind = "none",
                totalItems = 0,
                items = emptyList()
            )
        )
            .volumes(name = name)
    }
}

/**
 * 通常データのモック
 */
private class NormalRetrofitResponse(
    private val delegate: BehaviorDelegate<ApiClient.ApiService>
) : ApiClient.ApiService {
    override fun volumes(name: String, startIndex: Int, limit: Int): Single<Books> {
        return delegate.returningResponse(
            Constants.mockBook
        )
            .volumes(name = name)
    }
}

class DataSourceTest {

    /**
     * 注入した振る舞いにできるRetrofitのインタフェースを生成
     */
    private fun buildRetrofit(): BehaviorDelegate<ApiClient.ApiService> {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://google.com/")
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val behavior = NetworkBehavior.create()

        val mockRetrofit = MockRetrofit.Builder(retrofit)
            .networkBehavior(behavior)
            .build()

        return mockRetrofit.create(ApiClient.ApiService::class.java)
    }

    @Test
    fun `Validate success Empty Response`() {
        val dataSource =
            GoogleBooksDataSource(apiService = EmptyRetrofitResponse(delegate = buildRetrofit()))
        val result = dataSource.fetchBooks(searchKeyword = "EXAMPLE").blockingGet()
        Assert.assertEquals(result.items?.size, 0)
    }

    @Test
    fun `Validate success Normal Response`() {
        val dataSource =
            GoogleBooksDataSource(apiService = NormalRetrofitResponse(delegate = buildRetrofit()))
        val result = dataSource.fetchBooks(searchKeyword = "EXAMPLE").blockingGet()
        Assert.assertEquals(result.items?.size, 1)
        Assert.assertEquals(result.items?.get(0)?.volumeInfo?.title, "ExampleBook")
        Assert.assertEquals(result.items?.get(0)?.volumeInfo?.authors?.get(0), "YusukeOba")
        Assert.assertEquals(
            result.items?.get(0)?.volumeInfo?.imageLinks?.thumbnail,
            "https://picsum.photos/536/354"
        )
    }

    @Test
    fun `Validate Error Response`() {
        val dataSource =
            GoogleBooksDataSource(apiService = InvalidRetrofitResponse(delegate = buildRetrofit()))
        var throwable: Throwable? = null
        dataSource.fetchBooks(searchKeyword = "EXAMPLE").blockingSubscribeBy(onError = {
            throwable = it
        })

        Assert.assertEquals(throwable?.message, "Invalid Response")
    }
}