package mx.com.evotae.appxtreme.framework.base

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import mx.com.evotae.appxtreme.R
import mx.com.evotae.appxtreme.framework.util.extensions.getPreferenceToString
import mx.com.evotae.appxtreme.main.appactivity.XtremeActivity
import mx.com.evotae.appxtreme.main.dialogs.ui.ErrorDialog
import servicecordinator.retrofit.managercall.IDPDV

open class XTFragmentBase : Fragment() {

    private lateinit var safeActivity: Activity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context != null) {
            safeActivity = context as Activity
        }
    }

    protected fun handleError(): (String) -> Unit = {
        Toast.makeText(safeActivity, "Error: ${it}", Toast.LENGTH_SHORT).show()

    }

    protected fun handleErrorLogin(): (String) -> Unit = {
        Toast.makeText(safeActivity, "Error: Credenciales Inválidas", Toast.LENGTH_SHORT).show()
        val dialog = Dialog(safeActivity)
        dialog.setContentView(R.layout.custom_progress_dialog)
        dialog.show()
        dialog.dismiss()
        startActivity(Intent(safeActivity,XtremeActivity::class.java))
    }

    protected fun handleErrorRecharge(): (String) -> Unit = {
       // ErrorDialog(it).show(parentFragmentManager, "Dialog Error")
    }

    protected fun handleLoader(): (Boolean) -> Unit = {
        Toast.makeText(safeActivity, it.toString(), Toast.LENGTH_SHORT).show()
    }

}