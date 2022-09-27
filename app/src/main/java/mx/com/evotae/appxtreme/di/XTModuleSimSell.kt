package mx.com.evotae.appxtreme.di


import mx.com.evotae.appxtreme.main.ventasim.datasource.XTDataSourceSimSell
import mx.com.evotae.appxtreme.main.ventasim.repository.XTRepositorySimSell
import mx.com.evotae.appxtreme.main.ventasim.repository.XTRepositorySimSellImpl
import mx.com.evotae.appxtreme.main.ventasim.usescases.XTUsesCasesSimSell
import mx.com.evotae.appxtreme.main.ventasim.usescases.XTUsesCasesSimSellImpl
import mx.com.evotae.appxtreme.main.ventasim.viewmodel.XTViewModelSimSell
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import servicecordinator.callapis.XTSimSellApiCall

val moduleSimSell = module {
    factory {
        XTSimSellApiCall(androidApplication())
    }
    factory {
        XTDataSourceSimSell(get())
    }
    single<XTRepositorySimSell> {
        XTRepositorySimSellImpl(get())
    }
    single<XTUsesCasesSimSell> {
        XTUsesCasesSimSellImpl(get())
    }
    viewModel {
        XTViewModelSimSell(get())
    }
}