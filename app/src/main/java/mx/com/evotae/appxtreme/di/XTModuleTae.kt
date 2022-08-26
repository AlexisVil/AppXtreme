package mx.com.evotae.appxtreme.di

import mx.com.evotae.appxtreme.main.login.usescases.XTUsesCasesLoginImpl
import mx.com.evotae.appxtreme.main.tae.datasource.XTDataSourceBrand
import mx.com.evotae.appxtreme.main.tae.repository.XTRepositoryBrand
import mx.com.evotae.appxtreme.main.tae.repository.XTRepositoryBrandImpl
import mx.com.evotae.appxtreme.main.tae.usescases.XTUsesCasesBrand
import mx.com.evotae.appxtreme.main.tae.usescases.XTUsesCasesBrandImpl
import mx.com.evotae.appxtreme.main.tae.viewmodel.XTViewModelTae
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import servicecordinator.callapis.XTBrandApiCall

val moduleTae = module {
    factory {
        XTBrandApiCall(androidApplication())
    }
    factory {
        XTDataSourceBrand(get())
    }
    single<XTRepositoryBrand> {
        XTRepositoryBrandImpl(get())
    }
    single<XTUsesCasesBrand> {
        XTUsesCasesBrandImpl(get())
    }
    viewModel {
        XTViewModelTae(get())
    }
}