package mx.com.evotae.appxtreme.di

import mx.com.evotae.appxtreme.main.user.datasource.XTDataSourceTransactions
import mx.com.evotae.appxtreme.main.user.repository.XTRepositoryTransactions
import mx.com.evotae.appxtreme.main.user.repository.XTRepositoryTransactionsImpl
import mx.com.evotae.appxtreme.main.user.usescases.XTUsesCasesTransactions
import mx.com.evotae.appxtreme.main.user.usescases.XTUsesCasesTransactionsImpl
import mx.com.evotae.appxtreme.main.user.viewmodel.XTViewModelTransactions
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import servicecordinator.callapis.XTTransactionsApiCall

val moduleTransactions = module {
    factory {
        XTTransactionsApiCall(androidApplication())
    }
    factory {
        XTDataSourceTransactions(get())
    }
    single<XTRepositoryTransactions> {
        XTRepositoryTransactionsImpl(get())
    }
    single<XTUsesCasesTransactions> {
        XTUsesCasesTransactionsImpl(get())
    }
    viewModel {
        XTViewModelTransactions(get())
    }
}