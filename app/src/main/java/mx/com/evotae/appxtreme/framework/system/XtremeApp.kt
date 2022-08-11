package mx.com.evotae.appxtreme.framework.system

import android.app.Application
import android.os.Build
import mx.com.evotae.appxtreme.BuildConfig
import mx.com.evotae.appxtreme.di.modulesApp
import mx.com.evotae.appxtreme.framework.base.XTConstant.appContext
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class XtremeApp: Application() {
    override fun onCreate() {
        super.onCreate()
        appContext = this
        startKoin {
            androidLogger(if (BuildConfig.DEBUG)Level.ERROR else Level.NONE)
            androidContext(this@XtremeApp)
            modules(modulesApp)
        }
    }
}