package ru.uxfeedback.demoapplication.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import ru.uxfeedback.demoapplication.databinding.ActivitySecondBinding

@AndroidEntryPoint
class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivitySecondBinding.inflate(layoutInflater).let { binding ->
            setContentView(binding.root)
        }
    }
}