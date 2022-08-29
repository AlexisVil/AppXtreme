package mx.com.evotae.appxtreme.di

import mx.com.evotae.appxtreme.main.reportarpago.datasource.XTDataSourceCheckBalance
import mx.com.evotae.appxtreme.main.reportarpago.repository.XTRepositoryCheckBalance
import mx.com.evotae.appxtreme.main.reportarpago.repository.XTRepositoryCheckBalanceImpl
import mx.com.evotae.appxtreme.main.reportarpago.usescases.XTUsesCasesCheckBalance
import mx.com.evotae.appxtreme.main.reportarpago.usescases.XTUsesCasesCheckBalanceImpl
import mx.com.evotae.appxtreme.main.reportarpago.viewmodel.XTViewModelCheckBalance
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