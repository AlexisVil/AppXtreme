package mx.com.evotae.appxtreme.di

import mx.com.evotae.appxtreme.main.reportarpago.datasource.XTDataSourceGetBanks
import mx.com.evotae.appxtreme.main.reportarpago.repository.XTRepositoryGetBanks
import mx.com.evotae.appxtreme.main.reportarpago.repository.XTRepositoryGetBanksImpl
import mx.com.evotae.appxtreme.main.reportarpago.usescases.XTUsesCasesGetBanks
import mx.com.evotae.appxtreme.main.reportarpago.usescases.XTUsesCasesGetBanksImpl
import mx.com.evotae.appxtreme.main.reportarpago.viewmodel.XTViewModelGetBanks
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import servicecordinator.callapis.XTGetBanksApiCall

val moduleGetBanks = module {
    factory {
        XTGetBanksApiCall(androidApplication())
    }
    factory {
        XTDataSourceGetBanks(get())
    }
    single<XTRepositoryGetBanks> {
        XTRepositoryGetBanksImpl(get())
    }
    single<XTUsesCasesGetBanks> {
        XTUsesCasesGetBanksImpl(get())
    }
    viewModel {
        XTViewModelGetBanks(get())
    }
}