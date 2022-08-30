package mx.com.evotae.appxtreme.di

import mx.com.evotae.appxtreme.main.user.datasource.XTDataSourceCheckBalance
import mx.com.evotae.appxtreme.main.user.repository.XTRepositoryCheckBalance
import mx.com.evotae.appxtreme.main.user.repository.XTRepositoryCheckBalanceImpl
import mx.com.evotae.appxtreme.main.user.usescases.XTUsesCasesCheckBalance
import mx.com.evotae.appxtreme.main.user.usescases.XTUsesCasesCheckBalanceImpl
import mx.com.evotae.appxtreme.main.user.viewmodel.XTViewModelCheckBalance
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import servicecordinator.callapis.XTCheckBalanceApiCall

val moduleCheckBalance = module {
    factory {
        XTCheckBalanceApiCall(androidApplication())
    }
    factory {
        XTDataSourceCheckBalance(get())
    }
    single<XTRepositoryCheckBalance> {
        XTRepositoryCheckBalanceImpl(get())
    }
    single<XTUsesCasesCheckBalance> {
        XTUsesCasesCheckBalanceImpl(get())
    }
    viewModel {
        XTViewModelCheckBalance(get())
    }
}