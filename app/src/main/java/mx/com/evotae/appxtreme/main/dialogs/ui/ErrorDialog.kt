package mx.com.evotae.appxtreme.main.dialogs.ui

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Environment
import android.text.format.DateFormat
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.activity_main.*
import mx.com.evotae.appxtreme.databinding.DialogErrorRecargaBinding
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.util.*
import java.util.jar.Manifest

class ErrorDialog(private val message: String,
                  private val ticket: String,) : DialogFragment() {
    private lateinit var binding: DialogErrorRecargaBinding
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogErrorRecargaBinding.inflate(LayoutInflater.from(context))
        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)
        binding.apply {
            tvTicket.text = ticket
            tvMessage.text = message
            btnAccept.setOnClickListener {
                dismiss()
            }
        }
        val dialog = builder.create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window!!.setGravity(Gravity.CENTER_HORIZONTAL)
        return dialog
    }

    fun takeScreenshotOfView() {
        if (!checkPermissions())
            return
        try {
            val now = Date()
            DateFormat.format("yyyy-MM-dd_hh:mm:ss",now)
            val path = requireActivity().getExternalFilesDir(null)!!.absolutePath + "/" + "/AppName"
            val fileDir = File(path)
            if (!fileDir.exists())
                fileDir.mkdir()
            val mPath = path + "/ScreenShot_" + now + ".jpg"
            val bitmap =  ss()
            val imageFile = File(mPath)
            val outputStream = FileOutputStream(imageFile)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
            outputStream.flush()
            outputStream.close()
        }
        catch (e: FileNotFoundException){
            e.printStackTrace()
        }catch (e: IOException){
            e.printStackTrace()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if ( grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED ){
            takeScreenshotOfView()
        }else{
            Toast.makeText(requireActivity(), "Denied", Toast.LENGTH_SHORT).show()
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun ss(): Bitmap {
        var bitmap= Bitmap.createBitmap(binding.root.width, binding.root.height, Bitmap.Config.ARGB_8888)
        var canvas = Canvas(bitmap)
        binding.root.draw(canvas)
        return bitmap
    }

    private fun checkPermissions(): Boolean {
        val permission= ActivityCompat.checkSelfPermission(requireActivity(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if (permission != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE),1)
            return false
        }
        return true
    }


}