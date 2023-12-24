package com.example.test_suitmedia.ui.third_screen

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test_suitmedia.adapter.ListAdapter
import com.example.test_suitmedia.adapter.LoadingStateAdapter
import com.example.test_suitmedia.databinding.ActivityThirdScreenBinding
import com.example.test_suitmedia.ui.ViewModelFactory
import com.example.test_suitmedia.ui.second_screen.SecondScreenActivity

class ThirdScreenActivity : AppCompatActivity() {

    private val viewModel by viewModels<ThirdScreenViewModel> {
        ViewModelFactory.getInstance(this)
    }

    private lateinit var binding: ActivityThirdScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvListUser.layoutManager = LinearLayoutManager(this)

        binding.btnBack.setOnClickListener{
            startActivity(Intent(this@ThirdScreenActivity, SecondScreenActivity::class.java))
        }

        getData()
    }

    private fun getData(){
        val adapter = ListAdapter(this)
        binding.rvListUser.adapter = adapter.withLoadStateFooter(
            footer = LoadingStateAdapter {
                adapter.retry()
            }
        )

        viewModel.getUser().observe(this) {
            adapter.submitData(lifecycle, it)
            loading(false)
        }
    }

    private fun loading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}