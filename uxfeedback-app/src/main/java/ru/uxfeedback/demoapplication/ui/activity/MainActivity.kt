package ru.uxfeedback.demoapplication.ui.activity

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import dagger.hilt.android.AndroidEntryPoint
import ru.uxfeedback.demoapplication.R
import ru.uxfeedback.demoapplication.databinding.ActivityMainBinding
import ru.uxfeedback.demoapplication.utils.findNavControllerByFragmentId
import ru.uxfeedback.demoapplication.utils.safeNavigate


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        return super.dispatchTouchEvent(ev)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityMainBinding.inflate(layoutInflater).let { binding ->
            setContentView(binding.root)
            binding.navController = findNavControllerByFragmentId(R.id.navHostFragmentMain)
            binding.bindingHolder = BindingHolder()
        }
    }

    inner class BindingHolder{
        fun presenterClick() = findNavControllerByFragmentId(R.id.navHostFragmentMain).safeNavigate(R.id.action_global_presenterFragment)
        fun listenersClick() = findNavControllerByFragmentId(R.id.navHostFragmentMain).safeNavigate(R.id.action_global_listenersFragment)
        fun optionsClick() = findNavControllerByFragmentId(R.id.navHostFragmentMain).safeNavigate(R.id.action_global_optionsFragment)
    }
}