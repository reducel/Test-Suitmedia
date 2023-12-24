package com.example.test_suitmedia.ui.second_screen

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.test_suitmedia.databinding.ActivitySecondScreenBinding
import com.example.test_suitmedia.ui.ViewModelFactory
import com.example.test_suitmedia.ui.main.MainActivity
import com.example.test_suitmedia.ui.third_screen.ThirdScreenActivity
import com.example.test_suitmedia.ui.third_screen.ThirdScreenViewModel

class SecondScreenActivity : AppCompatActivity() {

    private val viewModel by viewModels<ThirdScreenViewModel> {
        ViewModelFactory.getInstance(this)
    }

    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            binding.selectedUser.text = data?.getStringExtra("name")
        }
    }

    private lateinit var binding: ActivitySecondScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var intent = intent
        val name = intent.getStringExtra("Name")
        val username = intent.getStringExtra("USER_NAME")
        binding.name.text = name

        binding.btnBack.setOnClickListener{
            startActivity(Intent(this@SecondScreenActivity, MainActivity::class.java))
        }
        binding.btnChooseUser.setOnClickListener {
            resultLauncher.launch(Intent(this@SecondScreenActivity, ThirdScreenActivity::class.java))
        }
    }

    companion object {
        const val USER_NAME = "user_name"
    }
}