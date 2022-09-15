package mx.com.evotae.appxtreme.main.reportarpago.repository

import mx.com.evotae.appxtreme.main.reportarpago.datasource.XTDataSourcePayBank
import servicecordinator.model.response.XTResponsePayBank
import servicecordinator.retrofit.model.dataclass.XTResponseData
import servicecordinator.retrofit.model.dataclass.XTResponseGeneral

interface XTRepositoryPayBank {
    suspend fun payBank(
        idOperacion: String,
        user: String,
        pwd: String,
        claveOperador: String,
        banco: String,
        tipoDeposito: String,
        sucursal: String,
        referencia: String,
        fecha: String,
        hora: String,
        minuto: String,
        monto: String,
        recargas: String,
        servicios: String,
        regid: String
    ): XTResponseData<XTResponseGeneral<XTResponsePayBank>?>
}

class XTRepositoryPayBankImpl(private val dataSourcePayBank: XTDataSourcePayBank) :
    XTRepositoryPayBank {
    override suspend fun payBank(
        idOperacion: String,
        user: String,
        pwd: String,
        claveOperador: String,
        banco: String,
        tipoDeposito: String,
        sucursal: String,
        referencia: String,
        fecha: String,
        hora: String,
        minuto: String,
        monto: String,
        recargas: String,
        servicios: String,
        regid: String
    ): XTResponseData<XTResponseGeneral<XTResponsePayBank>?> {
        return dataSourcePayBank.payBank(
            idOperacion,
            user,
            pwd,
            claveOperador,
            banco,
            tipoDeposito,
            sucursal,
            referencia,
            fecha,
            hora,
            minuto,
            monto,
            recargas,
            servicios,
            regid
        )
    }

}