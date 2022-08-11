package servicecordinator.retrofit.builder

import android.content.Context
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import servicecordinator.retrofit.model.inter.XTConectionInterceptor
import servicecordinator.router.Routers
import java.util.concurrent.TimeUnit

class XTRetrofitApp<T> private constructor(
    private val host: String,
    private val context: Context,
    private val apiInterface: Class<T>
) {
    private fun retrofitClient(): Retrofit.Builder {
        val levelType: HttpLoggingInterceptor.Level = HttpLoggingInterceptor.Level.BODY
        val logging = HttpLoggingInterceptor().apply {
            level = levelType
        }
        val httpClient = OkHttpClient.Builder().apply {
            addInterceptor(logging)
            addInterceptor(XTConectionInterceptor(context))
            connectTimeout(1000, TimeUnit.MINUTES)
            writeTimeout(1000, TimeUnit.MINUTES)
            readTimeout(1000, TimeUnit.MINUTES)
        }
        return Retrofit.Builder()
            .baseUrl(Routers.HOST)
            .client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
    }

    fun instance(): T {
        return retrofitClient()
            .build()
            .create(apiInterface)
    }

    class Build<T> {
        private var host: String = ""
        private lateinit var context: Context
        private lateinit var apiInterface: Class<T>
        fun setHost(host: String): Build<T> {
            this.host = host
            return this
        }

        fun setContext(context: Context): Build<T> {
            this.context = context
            return this
        }

        fun setClass(apiInterface: Class<T>): Build<T> {
            this.apiInterface = apiInterface
            return this
        }

        fun builder(): XTRetrofitApp<T> {
            return XTRetrofitApp(host, context, apiInterface)
        }
    }
}