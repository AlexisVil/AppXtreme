package mx.com.evotae.appxtreme.main.tae.model

class TaeProvider {
    companion object {
        val taeList = listOf<Tae>(
            Tae(
                "Telcel",
                1,
                "https://media.pasionmovil.com/2012/04/telcel-logo-e1334884696429.jpg"
            ),
            Tae(
                "Movistar",
                2,
                "https://i.pinimg.com/originals/1f/17/91/1f1791c876624f00b92cc6d19e817fc6.png"
            ),
            Tae(
                "Att/Unefon",
                3,
                "data:image/jpeg;base64,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"
            ),
            Tae(
                "Virgin Mobile",
                4,
                "https://brandslogos.com/wp-content/uploads/images/large/virgin-logo.png"
            ),
            Tae(
                "Pillofon",
                5,
                "https://d3jkoqalzfs5lw.cloudfront.net/imagenesPillo/Website/PilloFon_Logo_fav.png"
            ),
            Tae(
                "Oui Movil",
                6,
                "https://play-lh.googleusercontent.com/40I0MAr1getFF-dLOzxekLi2_C3MC_u0HL2gmqNbXNWy32n9Bo3XIz2YYlAutfCT8aw"
            ),
            Tae(
                "JrMovil",
                7,
                "https://jrmovil.com/wp-content/uploads/2021/04/cropped-cropped-JR-movil-fondo-transparente-01.png"
            ),
            Tae(
                "Space movil",
                8,
                "https://pbs.twimg.com/profile_images/1324014137559588864/ZCpX6K1s_400x400.jpg"
            ),
            Tae(
                "Bait",
                9,
                "https://selectra.mx/sites/selectra.mx/files/images/logos/bait-350x183.png"
            ),
            Tae(
                "Diri",
                10,
                "https://d3jkoqalzfs5lw.cloudfront.net/imagesDiri/img/Diri_Logo_fav.png"
            ),
            Tae(
                "Maya movil",
                11,
                "https://mayamovil.com.mx/web/image/1510-0bfb27b7/MAYA%20BLANCO.png"
            ),
            Tae(
                "Diveracy",
                12,
                "https://pbs.twimg.com/profile_banners/1040278766390530048/1536857373/1500x500"
            )
        )
    }
}