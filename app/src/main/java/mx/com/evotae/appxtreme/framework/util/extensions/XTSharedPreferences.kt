package mx.com.evotae.appxtreme.framework.util.extensions

import android.content.Context
import android.content.SharedPreferences
import mx.com.evotae.appxtreme.framework.base.XTConstant

fun preferences(): String {
    return "XTREMEAPP"
}

fun String.savePreferencesToString(data: String) {
    val edit = createEditPreferences()
    edit?.putString(this, data)
    edit?.commit()
}

fun String.getPreferenceToString(): String?{
    val edit = createPreferences()
    return edit?.getString(this, "")
}

private fun createPreferences(): SharedPreferences?{
    return XTConstant.appContext?.getSharedPreferences(preferences(), Context.MODE_PRIVATE)
}
private fun createEditPreferences(): SharedPreferences.Editor?{
    return XTConstant.appContext?.getSharedPreferences(preferences(), Context.MODE_PRIVATE)?.edit()
}
//Limpiar valores
fun wipe(){
    val edit = createEditPreferences()
    edit?.clear()
    edit?.commit()
}

//Guarda variables en la APP