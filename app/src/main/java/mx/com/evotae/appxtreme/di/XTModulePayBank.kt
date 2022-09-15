package mx.com.evotae.appxtreme.di

import mx.com.evotae.appxtreme.main.reportarpago.datasource.XTDataSourcePayBank
import mx.com.evotae.appxtreme.main.reportarpago.repository.XTRepositoryPayBank
import mx.com.evotae.appxtreme.main.reportarpago.repository.XTRepositoryPayBankImpl
import mx.com.evotae.appxtreme.main.reportarpago.usescases.XTUsesCasesPayBank
import mx.com.evotae.appxtreme.main.reportarpago.usescases.XTUsesCasesPayBankImpl
import mx.com.evotae.appxtreme.main.reportarpago.viewmodel.XTViewModelPayBank
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import servicecordinator.callapis.XTPayBankApiCall

val modulePayBank= module {
    factory {
        XTPayBankApiCall(androidApplication())
    }
    factory {
        XTDataSourcePayBank(get())
    }
    single<XTRepositoryPayBank> {
        XTRepositoryPayBankImpl(get())
    }
    single<XTUsesCasesPayBank> {
        XTUsesCasesPayBankImpl(get())
    }
    viewModel {
        XTViewModelPayBank(get())
    }
}