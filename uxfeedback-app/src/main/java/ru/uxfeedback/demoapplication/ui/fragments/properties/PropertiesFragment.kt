package ru.uxfeedback.demoapplication.ui.fragments.properties

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.uxfeedback.demoapplication.R
import ru.uxfeedback.demoapplication.api.prefs.SharedPreferencesApi
import ru.uxfeedback.demoapplication.databinding.FragmentPropertiesBinding
import ru.uxfeedback.demoapplication.ui.fragments.properties.entities.PropertyRecord
import ru.uxfeedback.demoapplication.utils.showPropertyDialog
import ru.uxfeedback.pub.sdk.UxFeedback
import javax.inject.Inject


@AndroidEntryPoint
class PropertiesFragment: Fragment(R.layout.fragment_properties) {

    @Inject
    lateinit var sharedPreferencesApi: SharedPreferencesApi

    private lateinit var binding: FragmentPropertiesBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return FragmentPropertiesBinding.inflate(layoutInflater).apply {
            sdk = UxFeedback.sdk
            bindingHolder = BindingHolder()
            binding = this
        }.root
    }

    inner class BindingHolder{

        fun onNavButtonClick() = findNavController().popBackStack()

        fun addClick(){
            activity?.showPropertyDialog{ pair->
                UxFeedback.sdk?.properties?.put(pair.first, pair.second)
                binding.invalidateAll()
            }
        }

        fun deleteClick(key: String){
            UxFeedback.sdk?.properties?.remove(key)
            binding.invalidateAll()
        }

        fun editClick(record: PropertyRecord){
            activity?.showPropertyDialog(record){ pair->
                UxFeedback.sdk?.properties?.put(pair.first, pair.second)
                binding.invalidateAll()
            }
        }

    }

}