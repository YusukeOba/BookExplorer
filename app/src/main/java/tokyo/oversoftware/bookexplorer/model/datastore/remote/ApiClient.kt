package tokyo.oversoftware.bookexplorer.model.datastore.remote

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import tokyo.oversoftware.bookexplorer.BuildConfig
import tokyo.oversoftware.bookexplorer.entity.Books

/**
 * Retrofitのインタフェースを定義する
 */
object ApiClient {

    // APIの既定URL
    private const val API_BASE_URL = "https://www.googleapis.com/books/v1/"

    /**
     * Retrofitのインタフェースを作成する
     */
    fun build(): ApiService {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        // Releaseビルド時は実行パフォーマンスの妨げになるのでログを出力しない
        val httpClient: OkHttpClient = if (BuildConfig.DEBUG) {
            OkHttpClient.Builder().addInterceptor(
                interceptor()
            ).build()
        } else {
            OkHttpClient.Builder().build()
        }

        val builder = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())

        val retrofit: Retrofit = builder.client(httpClient).build()

        return retrofit.create(ApiService::class.java)
    }

    private fun interceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    // API定義
    interface ApiService {
        @GET("volumes")
        fun volumes(
            @Query("q") name: String, @Query("startIndex") startIndex: Int = 0,
            @Query("maxResults") limit: Int = 20
        ): Single<Books>
    }
}