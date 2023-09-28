package ru.uxfeedback.demoapplication.ui.fragments.options

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.uxfeedback.demoapplication.R
import ru.uxfeedback.demoapplication.api.prefs.SharedPreferencesApi
import ru.uxfeedback.demoapplication.databinding.FragmentOptionsBinding
import ru.uxfeedback.demoapplication.utils.askRestartApplication
import ru.uxfeedback.demoapplication.utils.restartApplication
import ru.uxfeedback.demoapplication.utils.safeNavigate
import javax.inject.Inject


@AndroidEntryPoint
class OptionsFragment: Fragment(R.layout.fragment_options) {

    @Inject
    lateinit var sharedPreferencesApi: SharedPreferencesApi

    private lateinit var binding: FragmentOptionsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return FragmentOptionsBinding.inflate(layoutInflater).apply {
            sharedPreferencesApi = this@OptionsFragment.sharedPreferencesApi
            bindingHolder = BindingHolder()
            posTheme = this@OptionsFragment.sharedPreferencesApi.themePos
            binding = this
        }.root
    }

    inner class BindingHolder{

        fun choiceApiUrls(pos: Int){
            activity?.apply {
                askRestartApplication {
                    sharedPreferencesApi.pushApiUrlPos(pos)
                    restartApplication()
                }
            }
        }

        fun choiceTheme(pos: Int){
            binding.posTheme = pos
            sharedPreferencesApi.pushThemePos(pos)
        }

        fun editTheme()= findNavController().safeNavigate(R.id.action_optionsFragment_to_editThemeFragment)

        fun sdkSettings()= findNavController().safeNavigate(R.id.action_optionsFragment_to_SDKSettingsFragment)

        fun sdkProperties()= findNavController().safeNavigate(R.id.action_optionsFragment_to_propertiesFragment)
    }

}