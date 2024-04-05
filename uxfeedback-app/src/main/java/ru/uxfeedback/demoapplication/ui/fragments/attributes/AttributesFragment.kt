package ru.uxfeedback.demoapplication.ui.fragments.attributes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.uxfeedback.demoapplication.R
import ru.uxfeedback.demoapplication.api.prefs.SharedPreferencesApi
import ru.uxfeedback.demoapplication.databinding.FragmentAttributesBinding
import ru.uxfeedback.demoapplication.ui.fragments.attributes.entities.AttributeRecord
import ru.uxfeedback.demoapplication.utils.showAttributeDialog
import javax.inject.Inject


@AndroidEntryPoint
class AttributesFragment: Fragment(R.layout.fragment_attributes) {

    @Inject
    lateinit var sharedPreferencesApi: SharedPreferencesApi

    @Inject
    lateinit var attributeRecords: MutableList<AttributeRecord>

    private lateinit var binding: FragmentAttributesBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return FragmentAttributesBinding.inflate(layoutInflater).apply {
            attributeRecords = this@AttributesFragment.attributeRecords
            bindingHolder = BindingHolder()
            binding = this
        }.root
    }

    inner class BindingHolder{
        fun onNavButtonClick() = findNavController().popBackStack()

        fun addClick(){
            activity?.showAttributeDialog{ attribute->
                attribute?.let {
                    attributeRecords.add(it)
                    binding.invalidateAll()

                }
            }
        }

        fun deleteClick(key: String){
            attributeRecords.find { it.name ==key }?.let {
                attributeRecords.remove(it)
            }
            binding.invalidateAll()
        }

        fun editClick(record: AttributeRecord){
            activity?.showAttributeDialog(record){ attribute->
                attribute?.let {
                    attributeRecords[attributeRecords.indexOf(record)] = attribute
                    binding.invalidateAll()
                }
            }
        }

    }

}