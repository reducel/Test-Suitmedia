package com.example.test_suitmedia.ui.main

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.test_suitmedia.databinding.ActivityMainBinding
import com.example.test_suitmedia.ui.second_screen.SecondScreenActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.checkButton.setOnClickListener{
            palindromeCheck(binding.palindromeEditText.text.toString())
        }

        binding.nextButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, SecondScreenActivity::class.java)
                .putExtra("Name", binding.nameEditText.text.toString()))
        }
    }

    fun palindromeCheck(input: String): Boolean{
        if (input.reversed() == input){
            AlertDialog.Builder(this)
                .setMessage("isPalindrome")
                .create()
                .show()
            return true
        } else {
            AlertDialog.Builder(this)
                .setMessage("if it's not Palindrome")
                .create()
                .show()
            return false
        }
    }
}
