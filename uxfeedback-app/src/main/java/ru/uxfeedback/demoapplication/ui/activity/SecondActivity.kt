package ru.uxfeedback.demoapplication.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import ru.uxfeedback.demoapplication.databinding.ActivitySecondBinding
import ru.uxfeedback.pub.sdk.UxFbOnEventsListener
import ru.uxfeedback.pub.sdk.UxFeedback

@AndroidEntryPoint
class SecondActivity : AppCompatActivity() {

    private val listener = object:UxFbOnEventsListener{
        override fun uxFbOnReady() {
                runOnUiThread {
                    binding.textView.text = "Ready"
                }
        }

        override fun uxFbOnStartCampaign(campaignId: Int, eventName: String) {

        }

        override fun uxFbOnFinishCampaign(campaignId: Int, eventName: String) {

        }

        override fun uxFbOnFieldsEvent(
            campaignId: Int,
            eventName: String,
            fieldValues: Map<String, Array<String>>
        ) {

        }

        override fun uxFbOnTerminateCampaign(
            campaignId: Int,
            eventName: String,
            terminatedPage: Int,
            totalPages: Int
        ) {

        }

        override fun uxFbNoCampaignToStart(eventName: String) {

        }

    }

    private lateinit var binding :ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater).also { binding ->
            setContentView(binding.root)
            UxFeedback.sdk?.campaignListener = listener
            binding.button3.setOnClickListener {
                UxFeedback.sdk?.startCampaign(binding.editTextText.text.toString())
            }
        }
    }
}