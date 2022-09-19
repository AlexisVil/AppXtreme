package mx.com.evotae.appxtreme.main.reportarpago.usescases

import mx.com.evotae.appxtreme.main.reportarpago.repository.XTRepositoryPayBank
import servicecordinator.model.response.XTResponsePayBank
import servicecordinator.retrofit.model.dataclass.XTResponseData
import servicecordinator.retrofit.model.dataclass.XTResponseGeneral

interface XTUsesCasesPayBank {
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
        comentarioView: String,
        regid: String
    ): XTResponseData<XTResponseGeneral<XTResponsePayBank>?>
}

class XTUsesCasesPayBankImpl(private val repository: XTRepositoryPayBank) : XTUsesCasesPayBank {
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
        comentarioView: String,
        regid: String
    ): XTResponseData<XTResponseGeneral<XTResponsePayBank>?> =
        repository.payBank(
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
            comentarioView,
            regid
        )

}