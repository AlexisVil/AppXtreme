package mx.com.evotae.appxtreme.main.tae.repository

import android.widget.Toast
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import mx.com.evotae.appxtreme.framework.base.XTFragmentBase
import mx.com.evotae.appxtreme.main.tae.model.XTTaeModel
import mx.com.evotae.appxtreme.main.tae.viewmodel.XTViewModelTae
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import servicecordinator.model.response.XTResponseBrand

class XTTaeBrandsProvider {
    val storageReference: StorageReference = FirebaseStorage.getInstance().reference.child("Carriers/BAIT.png")
    val imp = storageReference.downloadUrl.toString()
    companion object {
        val taeList = mutableListOf<XTTaeModel>(
            XTTaeModel(
                "Telcel",
                1,
                "https://firebasestorage.googleapis.com/v0/b/xtrememultipagos-53d2e.appspot.com/o/Carriers%2Ftelcel-logo-e1334884696429.jpg?alt=media&token=5aa892ff-f6b3-4646-bd32-25fdfacb8f0c"
            ),
            XTTaeModel(
                "Paquetes Telcel",
                2,
                "https://telceltijuana.com/images/planTelcel.png"
            ),
            XTTaeModel(
                "Paquetes Movistar",
                25,
                "https://selectra.mx/sites/selectra.mx/files/styles/_default/public/images/planes-movistar-825x293_1.png"
            ),
            XTTaeModel(
                "Movistar",
                16,
                "https://firebasestorage.googleapis.com/v0/b/xtrememultipagos-53d2e.appspot.com/o/Carriers%2Fmovistar.png?alt=media&token=8ec703e4-5fca-4119-9462-abddc5673806"
            ),
            XTTaeModel(
                "Att/Unefon",
                4,
                "https://http2.mlstatic.com/D_NQ_NP_795969-MLM32821376088_112019-O.jpg"
            ),
            XTTaeModel(
                "Bait",
                43,
                "https://firebasestorage.googleapis.com/v0/b/xtrememultipagos-53d2e.appspot.com/o/Carriers%2FBAIT.png?alt=media&token=ce4f872e-8d09-40c3-957c-349fa2866459"
            ),
            XTTaeModel(
                "Virgin Mobile",
                39,
                "https://brandslogos.com/wp-content/uploads/images/large/virgin-logo.png"
            ),
            XTTaeModel(
                "Pillofon",
                45,
                "https://d3jkoqalzfs5lw.cloudfront.net/imagenesPillo/Website/PilloFon_Logo_fav.png"
            ),
            XTTaeModel(
                "Oui Movil",
                38,
                "https://play-lh.googleusercontent.com/40I0MAr1getFF-dLOzxekLi2_C3MC_u0HL2gmqNbXNWy32n9Bo3XIz2YYlAutfCT8aw"
            ),
            XTTaeModel(
                "JrMovil",
                42,
                "https://jrmovil.com/wp-content/uploads/2021/04/cropped-cropped-JR-movil-fondo-transparente-01.png"
            ),
            XTTaeModel(
                "Space movil",
                44,
                "https://pbs.twimg.com/profile_images/1324014137559588864/ZCpX6K1s_400x400.jpg"
            ),
            XTTaeModel(
                "Diri",
                30,
                "https://d3jkoqalzfs5lw.cloudfront.net/imagesDiri/img/Diri_Logo_fav.png"
            ),
            XTTaeModel(
                "Maya movil",
                29,
                "https://mayamovil.com.mx/web/image/1510-0bfb27b7/MAYA%20BLANCO.png"
            ),
            XTTaeModel(
                "Diveracy",
                41,
                "https://pbs.twimg.com/profile_banners/1040278766390530048/1536857373/1500x500"
            ),
            XTTaeModel(
                "Valor Móvil",
                36,
                "https://valortelecom.mx/ukokreew/2022/03/cropped-logo-gris-02.png"
            ),
            XTTaeModel(
                "Recarga SIM",
                23,
                "https://cdn-icons-png.flaticon.com/512/4298/4298126.png"
            ),
            XTTaeModel(
                "Test Recarga",
                0,
                "https://d1csarkz8obe9u.cloudfront.net/posterpreviews/testing-logo-design-template-ce84480d61b3db9a8e1522a99875832f_screen.jpg?ts=1615794516"
            ),
            XTTaeModel(
                "WiMoMovil",
                37,
                "https://wimotelecom.com/wp-content/uploads/2020/09/logo_wimo_fondo_blue.png"
            ),
            XTTaeModel(
                "MazTiempo",
                40,
                "https://i.blogs.es/1e3a67/650_1000_maz-tiempo/450_1000.png"
            ),
            XTTaeModel(
                "NetWey",
                35,
                "https://secure.netwey.com.mx/site/img/Logotipo-Netwey-Black.svg"
            ),
            XTTaeModel(
                "Gugacom",
                34,
                "https://www.gugacom.com/wp-content/uploads/2020/01/gugacom_fina_300x100.png"
            ),
            XTTaeModel(
                "FreedomPop",
                33,
                "https://upload.wikimedia.org/wikipedia/commons/c/c8/Freedompop_logo.png"
            ),
            XTTaeModel(
                "FlashMobile",
                32,
                "https://play-lh.googleusercontent.com/R0lyJSbdzfDmKC5-FxMTe_HoVCboZvdbUiL1xk8v1c9nTnZBizqK2gm_7HgF6XO4Geep"
            ),
            XTTaeModel(
                "ComparTfon",
                31,
                "https://www.tuexpertomovil.com/wp-content/uploads/2020/11/opiniones-compartfon-2020-1200x800.jpg"
            ),
            XTTaeModel(
                "WEEX",
                24,
                "https://logovtor.com/wp-content/uploads/2020/10/weex-mx-logo-vector.png"
            ),
            XTTaeModel(
                "CIERTO",
                22,
                "https://i.blogs.es/b1d452/cierto-logo/1366_2000.jpg"
            ),
            XTTaeModel(
                "MASRECARGA",
                20,
                "https://firebasestorage.googleapis.com/v0/b/xtrememultipagos-53d2e.appspot.com/o/Carriers%2FMASRECARGA.jpg?alt=media&token=5de263dc-b948-4b1f-99e1-a7710b8c1820"
            ),
            XTTaeModel(
                "MiMovil",
                26,
                "https://cdn-pcu.s3-us-west-2.amazonaws.com/images/main-logo.png"
            )
        )
    }

}