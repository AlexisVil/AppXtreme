package mx.com.evotae.appxtreme.di

import mx.com.evotae.appxtreme.main.recargar.datasource.XTDataSourceProductList
import mx.com.evotae.appxtreme.main.recargar.repository.XTRepositoryProductList
import mx.com.evotae.appxtreme.main.recargar.repository.XTRepositoryProductListImpl
import mx.com.evotae.appxtreme.main.recargar.usescases.XTUsesCasesProductList
import mx.com.evotae.appxtreme.main.recargar.usescases.XTUsesCasesProductListImpl
import mx.com.evotae.appxtreme.main.recargar.viewmodel.XTViewModelProductList
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import servicecordinator.callapis.XTProductListApiCall

val moduleProductList = module {
    factory {
        XTProductListApiCall(androidApplication())
    }
    factory {
        XTDataSourceProductList(get())
    }
    single<XTRepositoryProductList>{
        XTRepositoryProductListImpl(get())
    }
    single<XTUsesCasesProductList>{
        XTUsesCasesProductListImpl(get())
    }
    viewModel {
        XTViewModelProductList(get())
    }
}