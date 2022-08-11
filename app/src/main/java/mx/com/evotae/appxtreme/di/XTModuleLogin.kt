package mx.com.evotae.appxtreme.di

import mx.com.evotae.appxtreme.main.login.datasource.XTDataSourceLogin
import mx.com.evotae.appxtreme.main.login.repository.XTRepositoryLogin
import mx.com.evotae.appxtreme.main.login.repository.XTRepositoryLoginImpl
import mx.com.evotae.appxtreme.main.login.usescases.XTUsesCasesLogin
import mx.com.evotae.appxtreme.main.login.usescases.XTUsesCasesLoginImpl
import mx.com.evotae.appxtreme.main.login.viewmodel.XTViewModelLogin
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.scope.get
import org.koin.dsl.module
import servicecordinator.callapis.XTLoginApiCall

val moduleLogin = module {
    factory {
        XTLoginApiCall(androidApplication())
    }

    factory {
        XTDataSourceLogin(get())
    }

    single<XTRepositoryLogin>{
        XTRepositoryLoginImpl(get())
    }
    single<XTUsesCasesLogin>{
        XTUsesCasesLoginImpl(get())
    }

    viewModel {
        XTViewModelLogin(get())
    }
}