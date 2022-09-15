package mx.com.evotae.appxtreme.di

import mx.com.evotae.appxtreme.main.reportarpago.datasource.XTDataSourceBankDeposit
import mx.com.evotae.appxtreme.main.reportarpago.repository.XTRepositoryBankDeposit
import mx.com.evotae.appxtreme.main.reportarpago.repository.XTRepositoryBankDepositImpl
import mx.com.evotae.appxtreme.main.reportarpago.usescases.XTUsesCasesBankDeposit
import mx.com.evotae.appxtreme.main.reportarpago.usescases.XTUsesCasesBankDepositImpl
import mx.com.evotae.appxtreme.main.reportarpago.viewmodel.XTViewModelBankDeposit
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import servicecordinator.callapis.XTBankDepositApiCall


val moduleBankDeposit = module{
    factory {
        XTBankDepositApiCall(androidApplication())
    }
    factory {
        XTDataSourceBankDeposit(get())
    }
    single<XTRepositoryBankDeposit> {
        XTRepositoryBankDepositImpl(get())
    }
    single<XTUsesCasesBankDeposit> {
        XTUsesCasesBankDepositImpl(get())
    }
    viewModel {
        XTViewModelBankDeposit(get())
    }
}