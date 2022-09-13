package mx.com.evotae.appxtreme.di

import mx.com.evotae.appxtreme.main.service.datasource.XTDataSourcePayService
import mx.com.evotae.appxtreme.main.service.repository.XTRepositoryPayService
import mx.com.evotae.appxtreme.main.service.repository.XTRepositoryPayServiceImpl
import mx.com.evotae.appxtreme.main.service.usescases.XTUsesCasesPayServices
import mx.com.evotae.appxtreme.main.service.usescases.XTUsesCasesPayServicesImpl
import mx.com.evotae.appxtreme.main.service.viewmodel.XTViewModelPayService
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import servicecordinator.callapis.XTPayServiceApiCall

val modulePayService = module {
    factory {
        XTPayServiceApiCall(androidApplication())
    }
    factory {
        XTDataSourcePayService(get())
    }
    single<XTRepositoryPayService>{
        XTRepositoryPayServiceImpl(get())
    }
    single<XTUsesCasesPayServices>{
        XTUsesCasesPayServicesImpl(get())
    }
    viewModel {
        XTViewModelPayService(get())
    }
}