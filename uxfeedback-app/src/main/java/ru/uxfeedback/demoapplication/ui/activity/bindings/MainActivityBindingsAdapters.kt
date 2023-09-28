package ru.uxfeedback.demoapplication.ui.activity.bindings

import androidx.core.view.forEach
import androidx.databinding.BindingAdapter
import androidx.navigation.NavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.uxfeedback.demoapplication.R
import ru.uxfeedback.demoapplication.ui.activity.MainActivity

@BindingAdapter("setupWithNavController")
fun setupWithNavController(view: BottomNavigationView, navController: NavController) {
    navController.addOnDestinationChangedListener { controller, destination, arguments ->
        view.menu.forEach {
            if (it.itemId == destination.id){
                it.isChecked = true
            }
        }
    }
}


@BindingAdapter("registerOnItemSelectedHandler")
fun registerOnItemSelectedHandler(view: BottomNavigationView, bindingHolder: MainActivity.BindingHolder) {
    view.setOnItemSelectedListener {
        when(it.itemId){
            R.id.presenterFragment -> {
                bindingHolder.presenterClick()
            }
            R.id.listenersFragment -> {
                bindingHolder.listenersClick()
            }
            R.id.optionsFragment -> {
                bindingHolder.optionsClick()
            }
        }
        false
    }
}