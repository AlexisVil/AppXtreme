package mx.com.evotae.appxtreme.di

import org.koin.core.module.Module

val modulesApp: List<Module> =
    listOf(
        moduleLogin,
        moduleTae,
        moduleProductList,
        moduleCheckBalance,
        moduleSellRecharge,
        modulePayService,
        moduleTransactions,
        moduleBankDeposit,
        moduleGetBanks,
        modulePayBank
    )