package ru.uxfeedback.demoapplication.ui.fragments.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.uxfeedback.demoapplication.R
import ru.uxfeedback.demoapplication.api.prefs.SharedPreferencesApi
import ru.uxfeedback.demoapplication.databinding.FragmentSdkSettingsBinding
import ru.uxfeedback.demoapplication.ui.common.interfaces.OnBooleanClickListener
import ru.uxfeedback.demoapplication.ui.common.interfaces.OnIntegerClickListener
import ru.uxfeedback.demoapplication.ui.common.interfaces.OnUxFbColorClickListener
import ru.uxfeedback.demoapplication.utils.showChangeColorDialog
import ru.uxfeedback.demoapplication.utils.showChangeDimenDialog
import ru.uxfeedback.demoapplication.utils.showChangeIntDialog
import ru.uxfeedback.pub.sdk.UxFeedback
import ru.uxfeedback.pub.sdk.UxFbColor
import javax.inject.Inject


@AndroidEntryPoint
class SDKSettingsFragment: Fragment(R.layout.fragment_sdk_settings) {

    @Inject
    lateinit var sharedPreferencesApi: SharedPreferencesApi

    private lateinit var binding: FragmentSdkSettingsBinding

    private val customSettings by lazy {
        sharedPreferencesApi.getSettings()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return FragmentSdkSettingsBinding.inflate(layoutInflater).apply {
            customSettings = this@SDKSettingsFragment.customSettings
            bindingHolder = BindingHolder()
            binding = this
        }.root
    }


    inner class BindingHolder: OnBooleanClickListener, OnIntegerClickListener, OnUxFbColorClickListener {

        fun onNavButtonClick() = findNavController().popBackStack()

        override fun booleanClick(booleanName: String, boolean: Boolean) {
            when(booleanName){
                getString(R.string.uxfbDebugEnabledName) -> {
                    customSettings.debugEnabled = !boolean
                    UxFeedback.sdk?.settings?.debugEnabled = customSettings.debugEnabled
                }
                getString(R.string.uxfbFieldsEventEnabledName) -> {
                    customSettings.fieldsEventEnabled = !boolean
                    UxFeedback.sdk?.settings?.fieldsEventEnabled = customSettings.fieldsEventEnabled
                }
                getString(R.string.uxfbSlideInUiBlockedName) -> {
                    customSettings.slideInUiBlocked = !boolean
                    UxFeedback.sdk?.settings?.slideInUiBlocked = customSettings.slideInUiBlocked
                }
            }
            sharedPreferencesApi.pushSettings(customSettings)
            binding.invalidateAll()
        }

        override fun integerClick(integerName: String, integer: Int) {
            activity?.showChangeIntDialog(integerName, integer){ newValue ->
                when(integerName){
                    getString(R.string.uxfbRetryTimeoutName) -> {
                        customSettings.retryTimeout = newValue
                        UxFeedback.sdk?.settings?.retryTimeout = customSettings.retryTimeout
                    }
                    getString(R.string.uxfbRetryCountName) -> {
                        customSettings.retryCount = newValue
                        UxFeedback.sdk?.settings?.retryCount = customSettings.retryCount
                    }
                    getString(R.string.uxfbSocketTimeoutName) -> {
                        customSettings.socketTimeout = newValue
                        UxFeedback.sdk?.settings?.socketTimeout = customSettings.socketTimeout
                    }
                    getString(R.string.uxfbSlideInUiBlackoutOpacityName) -> {
                        customSettings.slideInUiBlackoutOpacity = newValue
                        UxFeedback.sdk?.settings?.slideInUiBlackoutOpacity = customSettings.slideInUiBlackoutOpacity
                    }
                    getString(R.string.uxfbSlideInUiBlackoutBlurName) -> {
                        customSettings.slideInUiBlackoutBlur = newValue
                        UxFeedback.sdk?.settings?.slideInUiBlackoutBlur = customSettings.slideInUiBlackoutBlur
                    }
                    getString(R.string.uxfbPopupUiBlackoutOpacityName) -> {
                        customSettings.popupUiBlackoutOpacity = newValue
                        UxFeedback.sdk?.settings?.popupUiBlackoutOpacity = customSettings.popupUiBlackoutOpacity
                    }
                    getString(R.string.uxfbPopupUiBlackoutBlurName) -> {
                        customSettings.popupUiBlackoutBlur = newValue
                        UxFeedback.sdk?.settings?.popupUiBlackoutBlur = customSettings.popupUiBlackoutBlur
                    }
                    getString(R.string.uxfbStartGlobalDelayTimerName) -> {
                        customSettings.startGlobalDelayTimer = newValue
                        UxFeedback.sdk?.settings?.startGlobalDelayTimer = customSettings.startGlobalDelayTimer
                    }
                }
                sharedPreferencesApi.pushSettings(customSettings)
                binding.invalidateAll()
            }
        }

        override fun colorClick(colorName: String, color: UxFbColor) {
            activity?.showChangeColorDialog(colorName, color){
                sharedPreferencesApi.pushSettings(customSettings)
                when(colorName){
                    getString(R.string.uxfbSlideInUiBlackoutColorName) -> {
                        UxFeedback.sdk?.settings?.slideInUiBlackoutColor = color
                    }
                    getString(R.string.uxfbPopupUiBlackoutColorName) -> {
                        UxFeedback.sdk?.settings?.popupUiBlackoutColor = color
                    }
                }
                binding.invalidateAll()
            }
        }
    }

}