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
                "https://firebasestorage.googleapis.com/v0/b/xtrememultipagos-53d2e.appspot.com/o/Carriers%2Ftelcel-logo-e1334884696429.jpg?alt=media&token=5aa892ff-f6b3-4646-bd32-25fdfacb8f0c",
                "a"
            ),
            XTTaeModel(
                "Paquetes Telcel",
                2,
                "https://firebasestorage.googleapis.com/v0/b/xtrememultipagos-53d2e.appspot.com/o/Carriers%2FSERVICIOSTELCEL.png?alt=media&token=c7474ae5-edca-4d21-8f10-498186e9ab63",
                "b"
            ),
            XTTaeModel(
                "Paquetes Movistar",
                25,
                "https://firebasestorage.googleapis.com/v0/b/xtrememultipagos-53d2e.appspot.com/o/Carriers%2FSERVICIOSMOVISTAR.png?alt=media&token=ffd9aae4-03e5-415f-a985-fdfbb2e8ae44",
                "c"
            ),
            XTTaeModel(
                "Movistar",
                16,
                "https://firebasestorage.googleapis.com/v0/b/xtrememultipagos-53d2e.appspot.com/o/Carriers%2Fmovistar.png?alt=media&token=8ec703e4-5fca-4119-9462-abddc5673806",
                "d"
            ),
            XTTaeModel(
                "Att/Unefon",
                4,
                "https://firebasestorage.googleapis.com/v0/b/xtrememultipagos-53d2e.appspot.com/o/Carriers%2FATTUNEFON.jpg?alt=media&token=e6165c99-111a-4519-818e-08e4e7f45739",
                "e"
            ),
            XTTaeModel(
                "Bait",
                43,
                "https://firebasestorage.googleapis.com/v0/b/xtrememultipagos-53d2e.appspot.com/o/Carriers%2FBAIT.png?alt=media&token=ce4f872e-8d09-40c3-957c-349fa2866459",
                "f"
            ),
            XTTaeModel(
                "Virgin Mobile",
                39,
                "https://firebasestorage.googleapis.com/v0/b/xtrememultipagos-53d2e.appspot.com/o/Carriers%2FVIRGIN.png?alt=media&token=e482461a-4523-4560-b57d-7ef4f93f35ae",
                "j"
            ),
            XTTaeModel(
                "Pillofon",
                45,
                "https://firebasestorage.googleapis.com/v0/b/xtrememultipagos-53d2e.appspot.com/o/Carriers%2FPILLOFON.png?alt=media&token=4277ee7e-1420-4649-9154-131d2324498a",
                "g"
            ),
            XTTaeModel(
                "Oui Movil",
                38,
                "https://firebasestorage.googleapis.com/v0/b/xtrememultipagos-53d2e.appspot.com/o/Carriers%2FOUI.png?alt=media&token=4c8bb055-2248-4978-8b92-eca41e4aba3c",
                "i"
            ),
            XTTaeModel(
                "JrMovil",
                42,
                "https://firebasestorage.googleapis.com/v0/b/xtrememultipagos-53d2e.appspot.com/o/Carriers%2FJRMOVIL.png?alt=media&token=ece5bdee-10d0-4c8a-8c8f-f55846ab503d",
                "k"
            ),
            XTTaeModel(
                "Space movil",
                44,
                "https://firebasestorage.googleapis.com/v0/b/xtrememultipagos-53d2e.appspot.com/o/Carriers%2FSPACE.jpg?alt=media&token=66aa0c9f-2b32-4855-96c1-e73cef55f956",
                "l"
            ),
            XTTaeModel(
                "Diri",
                30,
                "https://firebasestorage.googleapis.com/v0/b/xtrememultipagos-53d2e.appspot.com/o/Carriers%2FDIRI.png?alt=media&token=874c6788-ecb9-4e4c-9e93-cce56fd7f9f2",
                "m"
            ),
            XTTaeModel(
                "Maya movil",
                29,
                "https://firebasestorage.googleapis.com/v0/b/xtrememultipagos-53d2e.appspot.com/o/Carriers%2FMAYA.png?alt=media&token=3ed51b11-e305-41b6-a00b-27b34285fbf4",
                "n"
            ),
            XTTaeModel(
                "Diveracy",
                41,
                "https://firebasestorage.googleapis.com/v0/b/xtrememultipagos-53d2e.appspot.com/o/Carriers%2FDIVERACY.png?alt=media&token=c5b34c40-0861-43e7-8fe7-a5bb7fc14d36",
                "o"
            ),
            XTTaeModel(
                "Valor Móvil",
                36,
                "https://firebasestorage.googleapis.com/v0/b/xtrememultipagos-53d2e.appspot.com/o/Carriers%2FVALOR.png?alt=media&token=1702d144-500d-4b56-b1d2-deed5e91aaa1",
                "p"
            ),
            XTTaeModel(
                "Recarga SIM",
                23,
                "https://firebasestorage.googleapis.com/v0/b/xtrememultipagos-53d2e.appspot.com/o/Carriers%2FSIMCARD.png?alt=media&token=fb45fe48-6dfb-4bca-bcea-52fb7ff93f46",
                "h"
            ),
            XTTaeModel(
                "Test Recarga",
                0,
                "https://firebasestorage.googleapis.com/v0/b/xtrememultipagos-53d2e.appspot.com/o/Carriers%2FTEST.jpg?alt=media&token=29178f14-3328-4164-b92f-45f0cd802e34",
                "q"
            ),
            XTTaeModel(
                "WiMoMovil",
                37,
                "https://firebasestorage.googleapis.com/v0/b/xtrememultipagos-53d2e.appspot.com/o/Carriers%2FWIMO.png?alt=media&token=2dc5a572-c8a2-454f-8f6d-9d3d398ce552",
                "w"
            ),
            XTTaeModel(
                "MazTiempo",
                40,
                "https://firebasestorage.googleapis.com/v0/b/xtrememultipagos-53d2e.appspot.com/o/Carriers%2FMAZTIEMPO.png?alt=media&token=c47fe493-4e2f-4753-8813-e9c3cdd92321",
                "x"
            ),
            XTTaeModel(
                "NetWey",
                35,
                "https://firebasestorage.googleapis.com/v0/b/xtrememultipagos-53d2e.appspot.com/o/Carriers%2Fnetwey_2.jpg?alt=media&token=56c828f7-e76d-4db0-9952-97d01ecaba42",
                "y"
            ),
            XTTaeModel(
                "VIRGIN",
                9,
                "https://firebasestorage.googleapis.com/v0/b/xtrememultipagos-53d2e.appspot.com/o/Carriers%2FVIRGIN.png?alt=media&token=e482461a-4523-4560-b57d-7ef4f93f35ae",
                "z"
            ),
            XTTaeModel(
                "Gugacom",
                34,
                "https://firebasestorage.googleapis.com/v0/b/xtrememultipagos-53d2e.appspot.com/o/Carriers%2FGUGACOM.png?alt=media&token=e965781c-3580-43d6-8ba0-7560e3687262",
                "za"
            ),
            XTTaeModel(
                "FreedomPop",
                33,
                "https://firebasestorage.googleapis.com/v0/b/xtrememultipagos-53d2e.appspot.com/o/Carriers%2FFreedompop.png?alt=media&token=0cad4cae-c14e-4189-bd7b-cbddffddb06a",
                "zb"
            ),
            XTTaeModel(
                "FlashMobile",
                32,
                "https://firebasestorage.googleapis.com/v0/b/xtrememultipagos-53d2e.appspot.com/o/Carriers%2FFLASHMOBILE.png?alt=media&token=c51d2d22-c804-40df-8a66-be6489101a10",
                "zc"
            ),
            XTTaeModel(
                "ComparTfon",
                31,
                "https://firebasestorage.googleapis.com/v0/b/xtrememultipagos-53d2e.appspot.com/o/Carriers%2FCOMPARTEFON.jpg?alt=media&token=b4876f85-3411-4447-8e79-1a07126742b3",
                "zd"
            ),
            XTTaeModel(
                "WEEX",
                24,
                "https://firebasestorage.googleapis.com/v0/b/xtrememultipagos-53d2e.appspot.com/o/Carriers%2FWEEX.png?alt=media&token=89d4c0cb-941a-4c37-a4df-4843eaf992a4",
                "ze"
            ),
            XTTaeModel(
                "CIERTO",
                22,
                "https://firebasestorage.googleapis.com/v0/b/xtrememultipagos-53d2e.appspot.com/o/Carriers%2FCIERTO.jpg?alt=media&token=e5419f69-0ce9-4988-9446-0c3be9014149",
                "zf"
            ),
            XTTaeModel(
                "MASRECARGA",
                20,
                "https://firebasestorage.googleapis.com/v0/b/xtremmultipagos-53d2e.appspot.com/o/Carriers%2FMASRECARGA.jpg?alt=media&token=5de263dc-b948-4b1f-99e1-a7710b8c1820",
                "zg"
            ),
            XTTaeModel(
                "MiMovil",
                26,
                "https://firebasestorage.googleapis.com/v0/b/xtrememultipagos-53d2e.appspot.com/o/Carriers%2FMIMOVIL.png?alt=media&token=eeb1d7e7-ba7b-4adf-9176-0f9a437de508",
                "zh"
            ),
            XTTaeModel(
                "MAZTIEMPO",
                19,
                "https://firebasestorage.googleapis.com/v0/b/xtrememultipagos-53d2e.appspot.com/o/Carriers%2FMAZTIEMPO.png?alt=media&token=c47fe493-4e2f-4753-8813-e9c3cdd92321",
                "zi"
            ),
            XTTaeModel(
                "Beneleit",
                48,
                "https://firebasestorage.googleapis.com/v0/b/xtrememultipagos-53d2e.appspot.com/o/Carriers%2FBneleit.png?alt=media&token=6c31fbb6-4cfe-4167-ba9e-4162d07c73b5",
                "ja"
            )
        )
    }

}