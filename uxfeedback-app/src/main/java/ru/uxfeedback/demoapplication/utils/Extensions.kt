package ru.uxfeedback.demoapplication.utils

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.skydoves.colorpickerview.ColorPickerDialog
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener
import ru.uxfeedback.demoapplication.BuildConfig
import ru.uxfeedback.demoapplication.BuildConfig.APPLICATION_ID
import ru.uxfeedback.demoapplication.R
import ru.uxfeedback.demoapplication.databinding.LayoutEditDialogBinding
import ru.uxfeedback.demoapplication.databinding.LayoutFontDialogBinding
import ru.uxfeedback.demoapplication.databinding.LayoutPropertyDialogBinding
import ru.uxfeedback.demoapplication.ui.activity.MainActivity
import ru.uxfeedback.demoapplication.ui.fragments.properties.entities.PropertyRecord
import ru.uxfeedback.pub.sdk.UxFbColor
import ru.uxfeedback.pub.sdk.UxFbDimen
import ru.uxfeedback.pub.sdk.UxFbFont

fun AppCompatActivity.findNavControllerByFragmentId(@IdRes fragmentId: Int): NavController {
    return (supportFragmentManager.findFragmentById(fragmentId) as NavHostFragment).navController
}

fun Activity.askRestartApplication(callback: () -> Unit){
    MaterialAlertDialogBuilder(this)
        .setMessage(R.string.restart_application)
        .setPositiveButton(R.string.ok, object: DialogInterface.OnClickListener{
            override fun onClick(p0: DialogInterface?, p1: Int) {
                callback.invoke()
            }
        })
        .setNegativeButton(R.string.cancel, null)
        .create().show()
}

fun Activity.restartApplication(){
    val intent = Intent(this, MainActivity::class.java)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    startActivity(intent)
    finish()
    Runtime.getRuntime().exit(0)
}

fun NavController.safeNavigate(@IdRes resId: Int, args: Bundle? = null) {
    currentDestination?.run{
        getAction(resId)?.run {
            if (id != destinationId){
                navigate(resId, args)
            }
        }
    }
}

fun Activity.showChangeColorDialog(colorName: String, color: UxFbColor, callback: () -> Unit){
    ColorPickerDialog.Builder(this)
        .setTitle(colorName)
        .setPreferenceName(colorName)
        .setPositiveButton(getString(R.string.apply),
            ColorEnvelopeListener { envelope, fromUser ->
                color.intValue =envelope.color
                callback.invoke()
            })
        .setNegativeButton(getString(R.string.cancel)
        ) { dialogInterface, i -> dialogInterface.dismiss() }
        .attachAlphaSlideBar(true)
        .attachBrightnessSlideBar(true)
        .setBottomSpace(12)
        .apply {
            colorPickerView.setInitialColor(color.intValue)
            show()
        }
}


fun Activity.showChangeDimenDialog(dimenName: String, dimen: UxFbDimen, callback: () -> Unit){
    val binding = LayoutEditDialogBinding.inflate(layoutInflater)
    binding.teEditDialog.setText(dimen.dpValue.toString())
    MaterialAlertDialogBuilder(this)
        .setTitle(dimenName)
        .setPositiveButton(getString(R.string.apply)) { dialogInterface, i ->
            try {
                dimen.dpValue = binding.teEditDialog.text.toString().toInt()
            }catch (_: Exception){}
            callback.invoke()
        }
        .setNegativeButton(getString(R.string.cancel)) { dialogInterface, i -> dialogInterface.dismiss() }
        .setView(binding.root)
        .show()
}

fun Activity.showChangeIntDialog(dimenName: String, integer: Int, callback: (Int) -> Unit){
    val binding = LayoutEditDialogBinding.inflate(layoutInflater)
    binding.teEditDialog.setText(integer.toString())
    MaterialAlertDialogBuilder(this)
        .setTitle(dimenName)
        .setPositiveButton(getString(R.string.apply)) { dialogInterface, i ->
            try {
                callback.invoke(binding.teEditDialog.text.toString().toInt())
            }catch (_: Exception){
                callback.invoke(integer)
            }
        }
        .setNegativeButton(getString(R.string.cancel)) { dialogInterface, i -> dialogInterface.dismiss() }
        .setView(binding.root)
        .show()
}


fun Activity.showChangeFontDialog(fontName: String, font: UxFbFont, callback:(UxFbFont?) -> Unit){
    val binding = LayoutFontDialogBinding.inflate(layoutInflater)
    binding.fontValue = font
    MaterialAlertDialogBuilder(this)
        .setTitle(fontName)
        .setPositiveButton(getString(R.string.apply)) { dialogInterface, i ->
            try {
                val name = binding.actvFontName.editableText?.toString()?.toAppFont()?.toAssetsFont() ?: ""
                val weight = binding.actvFontWeight.editableText?.toString()?.fromFontWeight() ?: UxFbFont.NORMAL
                val size = binding.teFontSize.editableText?.toString()?.toInt() ?: 14
                val italic = binding.checkBoxItalic.isChecked
                callback.invoke(UxFbFont.fromName(name, weight , size, italic))
            }catch (_: Exception){
                callback.invoke(null)
            }
        }
        .setNegativeButton(getString(R.string.cancel)) { dialogInterface, i -> dialogInterface.dismiss() }
        .setView(binding.root)
        .show()
}



fun Activity.showPropertyDialog(oldValue: PropertyRecord = Pair("", ""), callback: (PropertyRecord) -> Unit){
    val binding = LayoutPropertyDialogBinding.inflate(layoutInflater)
    binding.teKey.setText(oldValue.first)
    binding.teValue.setText(oldValue.second)
    MaterialAlertDialogBuilder(this)
        .setTitle(R.string.property)
        .setPositiveButton(getString(R.string.save)) { dialogInterface, i ->
            try {
                callback.invoke(Pair(binding.teKey.text.toString(),  binding.teValue.text.toString()))
            }catch (_: Exception){
                callback.invoke(Pair("", ""))
            }
        }
        .setNegativeButton(getString(R.string.cancel)) { dialogInterface, i -> dialogInterface.dismiss() }
        .setView(binding.root)
        .show()
}


fun String.fromAppFont(): String{
    val parser = FontNameResourceParser(this)
   return when (parser.isFontResource){
        true -> parser.resourceName
        false -> this
    }
}

fun String.fromAssetsFont(): String{
    val parser = FontNameResourceParser(this)
    return when (parser.isAssetsResource){
        true -> parser.assetsResourceName
        false -> this
    }
}



fun String.toAppFont(): String{
    return if (applicationFonts.contains(this)) {
        "$APPLICATION_ID:font/$this"
    }else {
        this
    }
}

fun String.toAssetsFont(): String{
    return if (assetsFonts.contains(this)) {
        "assets:/fonts/$this"
    }else {
        this
    }
}


val systemFonts = mutableListOf("casual", "cursive", "monospace", "sans-serif", "serif", "serif-monospace")

val applicationFonts = mutableListOf("airfool", "amerika", "azoft", "comicrelief", "devroyun", "pixy")

val assetsFonts = mutableListOf("mirosln.ttf")

fun Int.toFontWeight(): String{
    return when(this){
        100 -> "THIN"
        200 -> "EXTRA_LIGHT"
        300 -> "LIGHT"
        400 -> "NORMAL"
        500 -> "MEDIUM"
        600 -> "SEMI_BOLD"
        700 -> "BOLD"
        800 -> "EXTRA_BOLD"
        900 -> "BLACK"
        else -> this.toString()
    }
}

fun String.fromFontWeight(): Int{
    return when(this){
        "THIN" -> 100
        "EXTRA_LIGHT" ->   200
        "LIGHT" -> 300
        "NORMAL" -> 400
        "MEDIUM" -> 500
        "SEMI_BOLD" -> 600
        "BOLD" -> 700
        "EXTRA_BOLD" -> 800
        "BLACK" -> 900
        else -> this.toInt()
    }
}