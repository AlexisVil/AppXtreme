package mx.com.evotae.appxtreme.framework.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment

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

    protected fun handleLoader(): (Boolean) -> Unit = {
        Toast.makeText(safeActivity, it.toString(), Toast.LENGTH_SHORT).show()
    }

}