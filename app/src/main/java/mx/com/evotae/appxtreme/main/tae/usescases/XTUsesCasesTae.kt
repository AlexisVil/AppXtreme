package mx.com.evotae.appxtreme.main.tae.usescases

import mx.com.evotae.appxtreme.main.tae.model.XTTaeModel

interface XTUsesCasesTae {
    suspend fun obtenerTae() : List<XTTaeModel>
}

class XTUsesCasesImpl{

//    override suspend fun obtenerListaTae(): List<XTTaeModel>{
//    }
}