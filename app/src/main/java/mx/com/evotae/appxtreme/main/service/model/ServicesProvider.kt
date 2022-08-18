package mx.com.evotae.appxtreme.main.service.model

class ServicesProvider {
    companion object {
       val servicesList = listOf<Services>(
           Services("CFE",
               1, "https://www.gob.mx/cms/uploads/action_program/main_image/19684/post_Salamanca_CFE.jpg"),
           Services("Telmex",
               2,
               "https://cdn2.downdetector.com/static/uploads/logo/telmex-logo.png"),
           Services("Jafra",
               3,
               "https://farm3.static.flickr.com/2775/4464566663_405befcd49.jpg"),
           Services("Natura",
               4,
               "https://logotipoz.com/wp-content/uploads/2021/10/natura-png-transparente.png"),
           Services("Avon",
               5,
               "https://1000marcas.net/wp-content/uploads/2020/03/logo-Avon.png"),
           Services("Netflix",
               6,
               "https://brandemia.org/sites/default/files/sites/default/files/icono_netflix_nuevo.jpg"),
           Services("Izzi",
               7,
               "https://s03.s3c.es/imag/_v0/770x420/e/7/4/Izzi-logo-770.jpg"),
           Services("VeTV Sky",
               8,
               "https://www.vetv-sky.com/imagenes/vetv.jpg"),
           Services("Dish",
               9,
               "https://comparaiso.mx/sites/comparaiso.mx/files/styles/article_hero/public/images/logo_dish.png?itok=J0qELEJ1"),
           Services("Totalplay",
               10,
               "https://upload.wikimedia.org/wikipedia/commons/thumb/b/bf/Logo_TotalPlay.svg/1200px-Logo_TotalPlay.svg.png"),
           Services("Infonavit",
               11,
               "https://e7.pngegg.com/pngimages/673/260/png-clipart-logo-instituto-del-fondo-nacional-de-la-vivienda-para-los-trabajadores-font-brand-infonavit-logo-computer-network-text.png")
       )
    }
}