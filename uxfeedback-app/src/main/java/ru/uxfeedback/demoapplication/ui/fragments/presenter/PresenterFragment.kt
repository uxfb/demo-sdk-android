package ru.uxfeedback.demoapplication.ui.fragments.presenter

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import ru.uxfeedback.demoapplication.R
import ru.uxfeedback.demoapplication.api.prefs.SharedPreferencesApi
import ru.uxfeedback.demoapplication.databinding.FragmentPresenterBinding
import ru.uxfeedback.demoapplication.ui.activity.SecondActivity
import ru.uxfeedback.demoapplication.utils.askRestartApplication
import ru.uxfeedback.demoapplication.utils.restartApplication
import ru.uxfeedback.demoapplication.utils.safeNavigate
import ru.uxfeedback.pub.sdk.UxFeedback
import ru.uxfeedback.pub.sdk.UxFbFont
import ru.uxfeedback.pub.sdk.UxFbTheme
import javax.inject.Inject

@AndroidEntryPoint
class PresenterFragment: Fragment(R.layout.fragment_presenter) {

    @Inject
    lateinit var sharedPreferencesApi: SharedPreferencesApi

    private lateinit var binding: FragmentPresenterBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return FragmentPresenterBinding.inflate(layoutInflater).apply {
            sharedPreferencesApi = this@PresenterFragment.sharedPreferencesApi
            bindingHolder = BindingHolder()
            binding = this
        }.root
    }

    inner class BindingHolder{
        fun saveAppIdClick(appId: String){
            activity?.apply {
                askRestartApplication {
                    sharedPreferencesApi.pushAppId(appId)
                    restartApplication()
                }
            }
        }
        fun saveEventIdClick(eventName: String){
            sharedPreferencesApi.pushEventName(eventName)
            binding.invalidateAll()
        }

        fun startCampaign(campaignName: String){
            when (sharedPreferencesApi.themePos){
                0 -> UxFeedback.sdk?.theme = UxFbTheme.fromStyle(R.style.UXFBLightTheme)
                1 -> UxFeedback.sdk?.theme = UxFbTheme.fromStyle(R.style.UXFBDarkTheme)
                2 -> UxFeedback.sdk?.theme = UxFbTheme.fromStyle(R.style.ResourcesUXFBLightTheme)
                3 -> UxFeedback.sdk?.theme = UxFbTheme.fromStyle(R.style.ResourcesUXFBDarkTheme)
                4 -> UxFeedback.sdk?.theme = sharedPreferencesApi.getCustomTheme()
            }
            UxFeedback.sdk?.startCampaign(campaignName)
        }
        fun stopCampaign() = UxFeedback.sdk?.stopCampaign()
        fun startSecondActivityClick()  {
           activity?.startActivity(Intent(requireContext(), SecondActivity::class.java))
        }

    }
}