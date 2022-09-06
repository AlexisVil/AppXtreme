package mx.com.evotae.appxtreme.di

import mx.com.evotae.appxtreme.main.recargar.datasource.XTDataSourceSellRecharge
import mx.com.evotae.appxtreme.main.recargar.repository.XTRepositorySellRecharge
import mx.com.evotae.appxtreme.main.recargar.repository.XTRepositorySellRechargeImpl
import mx.com.evotae.appxtreme.main.recargar.usescases.XTUsesCasesSellRecharge
import mx.com.evotae.appxtreme.main.recargar.usescases.XTUsesCasesSellRechargeImpl
import mx.com.evotae.appxtreme.main.recargar.viewmodel.XTViewModelSellRecharge
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import servicecordinator.callapis.XTSellRechargeApiCall

val moduleSellRecharge = module {
    factory {
        XTSellRechargeApiCall(androidApplication())
    }
    factory {
        XTDataSourceSellRecharge(get())
    }
    single<XTRepositorySellRecharge> {
        XTRepositorySellRechargeImpl(get())
    }
    single<XTUsesCasesSellRecharge> {
        XTUsesCasesSellRechargeImpl(get())
    }
    viewModel {
        XTViewModelSellRecharge(get())
    }
}