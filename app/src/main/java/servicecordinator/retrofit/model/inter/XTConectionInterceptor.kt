package servicecordinator.retrofit.model.inter

import android.content.Context
import android.net.ConnectivityManager
import okhttp3.Interceptor
import okhttp3.Response
import servicecordinator.retrofit.model.exception.XTConectionException
import java.net.InetAddress

class XTConectionInterceptor constructor(val context: Context) : Interceptor {
    private val URL_VERIFICATION_GOOGLE_DEFAULT = "www.google.com"

    override fun intercept(chain: Interceptor.Chain): Response {

        if (!isConnected()) {
            throw XTConectionException("Activa tus datos moviles o tu wifi para conectarte a internet.")
        }

        if (!this.availableNetwork()) {
            throw XTConectionException("No hay conexión a internet disponible.")
        }

        val builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }

    private fun isConnected(): Boolean {
        val connectionManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectionManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    private fun availableNetwork(): Boolean {
        return try {
            val connection: InetAddress = InetAddress.getByName(URL_VERIFICATION_GOOGLE_DEFAULT)
            !connection.equals("")
        } catch (e: java.lang.Exception) {
            false
        }
    }
}