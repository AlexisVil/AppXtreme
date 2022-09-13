package mx.com.evotae.appxtreme.main.service.repository

import mx.com.evotae.appxtreme.main.service.model.XTServicesModel

class XTRepositoryServicesProvider {
    companion object {
        val serviceList = listOf<XTServicesModel>(
            XTServicesModel(
                "Telmex",
                101,
                "https://cdn2.downdetector.com/static/uploads/logo/telmex-logo.png"
            ),
            XTServicesModel(
                "CFE",
                103,
                "https://www.gob.mx/cms/uploads/action_program/main_image/19684/post_Salamanca_CFE.jpg"
            ),
            XTServicesModel(
                "Izzi",
                114,
                "https://s03.s3c.es/imag/_v0/770x420/e/7/4/Izzi-logo-770.jpg"
            ),
            XTServicesModel(
                "VeTV Sky",
                102,
                "https://www.vetv-sky.com/imagenes/vetv.jpg"
            ),
            XTServicesModel(
                "GOBIERNODF CDMX",
                104,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/6/6a/CDMX_Logo.svg/640px-CDMX_Logo.svg.png"
            )
        )
    }
}