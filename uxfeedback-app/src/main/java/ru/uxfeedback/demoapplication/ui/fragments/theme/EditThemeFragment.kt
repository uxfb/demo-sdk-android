package ru.uxfeedback.demoapplication.ui.fragments.theme

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.uxfeedback.demoapplication.R
import ru.uxfeedback.demoapplication.api.prefs.SharedPreferencesApi
import ru.uxfeedback.demoapplication.databinding.FragmentEditThemeBinding
import ru.uxfeedback.demoapplication.ui.common.interfaces.OnBooleanClickListener
import ru.uxfeedback.demoapplication.ui.common.interfaces.OnUxFbColorClickListener
import ru.uxfeedback.demoapplication.ui.common.interfaces.OnUxFbDimenClickListener
import ru.uxfeedback.demoapplication.ui.common.interfaces.OnUxFbFontClickListener
import ru.uxfeedback.demoapplication.utils.showChangeColorDialog
import ru.uxfeedback.demoapplication.utils.showChangeDimenDialog
import ru.uxfeedback.demoapplication.utils.showChangeFontDialog
import ru.uxfeedback.pub.sdk.UxFbColor
import ru.uxfeedback.pub.sdk.UxFbDimen
import ru.uxfeedback.pub.sdk.UxFbFont
import javax.inject.Inject


@AndroidEntryPoint
class EditThemeFragment: Fragment(R.layout.fragment_edit_theme) {

    @Inject
    lateinit var sharedPreferencesApi: SharedPreferencesApi

    private lateinit var binding: FragmentEditThemeBinding

    private val customTheme by lazy{
        sharedPreferencesApi.getCustomTheme()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return FragmentEditThemeBinding.inflate(layoutInflater).apply {
            bindingHolder = BindingHolder()
            customTheme = this@EditThemeFragment.customTheme
            binding = this
        }.root
    }

    inner class BindingHolder: OnUxFbColorClickListener, OnUxFbDimenClickListener, OnBooleanClickListener, OnUxFbFontClickListener {

        fun onNavButtonClick() = findNavController().popBackStack()

        override fun colorClick(colorName: String, color: UxFbColor){
            activity?.showChangeColorDialog(colorName, color){
                sharedPreferencesApi.pushCustomTheme(customTheme)
                binding.invalidateAll()
            }
        }

        override fun dimenClick(dimenName: String, dimen: UxFbDimen) {
            activity?.showChangeDimenDialog(dimenName, dimen){
                sharedPreferencesApi.pushCustomTheme(customTheme)
                binding.invalidateAll()
            }
        }

        override fun booleanClick(booleanName: String, boolean: Boolean) {
            customTheme.lightNavigationBar = !boolean
            sharedPreferencesApi.pushCustomTheme(customTheme)
            binding.invalidateAll()
        }

        override fun fontClick(fontName: String, font: UxFbFont) {
            activity?.showChangeFontDialog(fontName, font){
                if (it != null){
                    when(fontName){
                        getString(R.string.uxfbFontH1Name) -> customTheme.fontH1 = it
                        getString(R.string.uxfbFontH2Name) -> customTheme.fontH2 = it
                        getString(R.string.uxfbFontP1Name) -> customTheme.fontP1 = it
                        getString(R.string.uxfbFontP2Name) -> customTheme.fontP2 = it
                        getString(R.string.uxfbFontBtnName) -> customTheme.fontBtn = it
                    }
                    sharedPreferencesApi.pushCustomTheme(customTheme)
                    binding.invalidateAll()
                }
            }
        }

    }


}