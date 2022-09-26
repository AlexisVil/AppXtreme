package mx.com.evotae.appxtreme.main.service.model

class ServicesProvider {
    companion object {
        val XTServicesModelLists = listOf<XTServicesModel>(
            XTServicesModel(
                "CFE",
                1,
                "https://www.gob.mx/cms/uploads/action_program/main_image/19684/post_Salamanca_CFE.jpg"
            ),
            XTServicesModel(
                "Telmex",
                2,
                "https://cdn2.downdetector.com/static/uploads/logo/telmex-logo.png"
            ),
            XTServicesModel(
                "Jafra",
                3,
                "https://farm3.static.flickr.com/2775/4464566663_405befcd49.jpg"
            ),
            XTServicesModel(
                "Natura",
                4,
                "https://logotipoz.com/wp-content/uploads/2021/10/natura-png-transparente.png"
            ),
            XTServicesModel(
                "Avon",
                5,
                "https://1000marcas.net/wp-content/uploads/2020/03/logo-Avon.png"
            ),
            XTServicesModel(
                "Netflix",
                6,
                "https://brandemia.org/sites/default/files/sites/default/files/icono_netflix_nuevo.jpg"
            ),
            XTServicesModel(
                "Izzi",
                7,
                "https://s03.s3c.es/imag/_v0/770x420/e/7/4/Izzi-logo-770.jpg"
            ),
            XTServicesModel(
                "VeTV Sky",
                8,
                "data:image/jpeg;base64,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DB36NTymP9ZUvjc0OyaAXC1mX9EZ7Tn7lNrJZQW42yzR6LoWKdn1V68X1ZTQwJXfwR/MZ+akuA8MT0075Z2BvR1W2c12biL7NmQVgWWNUJbjbbIuMssn3HlWzqa5qazzXea2kKNs0boni7XgtPAqp58B1oc4NjDmgkB2uwawByNicrjcrisllHRibKc9zmTYjCV35b/IpyDBWkGOa9kYDmkOaRIzIjMHaupijDtdVzNl5lotGxpGuzIi5dbPZdxVn2SyleOtclLTNdxCtnVKLim8n39Cmv8AYVccuaaPbZl5q36WEMY1g2NaGjuFl7WWVFfiZ3Zb2WhNh8JXRnuZ6hERQFkIiIAiIgCIiAIiIAiIgCxZZRAeUsLXCzmgjqIBHgVyanDVO/YwsP0SQPunLyXbWLLqMnHgyOdUJrKST8CJT4P9SU+02/mCPctSTCU42OYe9w+CnFksp1jLlzKstm4aX+Pk2iv3YZqR8y/BzfjZeb8N1P8AC/qZ+asSyWXfx1vd5ET2Rh318yspMM1R/cn7zPzXi7B9Wf3Q73s/NWnZLLp7QtfQ4WxsOub8/wCCrW8n1U7a6NveT5ALcpeTBp+WqHHsjaG+br+5WNZLKGWKtlzLNez8PDhE4Gh8H0dMQ6OEF4+e+73dxOQ7rLv2WUUDberLkYqKyQREXh6EREAREQBERAEREAREQBERAEREAREQBERAEREAREQBERAEREAREQBERAEREAREQBERAEREAREQBERAEREAREQBERAEREAREQBERAEREAREQBERAEREAREQBERAEREAREQBERAEREAREQBERAEREAREQBERAEREAREQBERAEREAREQBERAEREAREQBERAf/2Q=="
            ),
            XTServicesModel(
                "Dish",
                9,
                "https://comparaiso.mx/sites/comparaiso.mx/files/styles/article_hero/public/images/logo_dish.png?itok=J0qELEJ1"
            ),
            XTServicesModel(
                "Totalplay",
                10,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/b/bf/Logo_TotalPlay.svg/1200px-Logo_TotalPlay.svg.png"
            ),
            XTServicesModel(
                "Infonavit",
                11,
                "https://cdn-3.expansion.mx/dims4/default/7203922/2147483647/strip/true/crop/956x502+0+5/resize/1200x630!/format/jpg/quality/80/?url=https%3A%2F%2Fcdn-3.expansion.mx%2F16%2Fae%2F65d0223c474fa595d1dba37ca53d%2Fcaptura-de-pantalla-2021-11-03-145711.png"
            ),
            XTServicesModel(
                "Naturgy",
                12,
                "https://s03.s3c.es/imag/_v0/770x420/1/3/0/naturgy-azul.jpg"
            ),
            XTServicesModel(
                "GOBIERNODF CDMX",
                13,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/6/6a/CDMX_Logo.svg/640px-CDMX_Logo.svg.png"
                ),
            XTServicesModel(
                "FACTURA MOVISTAR",
                14,
                "https://guiafactura.com/wp-content/uploads/2021/01/DESCARGAR-FACTURA-MOVISTAR.png"
            ),
            XTServicesModel(
                "CINEPOLIS",
                15,
                "https://storage.googleapis.com/www-paredro-com/uploads/2019/04/LogoCinepolis.jpg"
            )
        )
    }
}