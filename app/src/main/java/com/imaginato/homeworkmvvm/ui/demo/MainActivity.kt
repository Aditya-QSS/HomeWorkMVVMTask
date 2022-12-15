package com.imaginato.homeworkmvvm.ui.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.imaginato.homeworkmvvm.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinApiExtension

@KoinApiExtension
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<MainActivityViewModel>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnDemo.setOnClickListener {
            viewModel.getDemoData()
        }
        initObserve()
    }

    private fun initObserve() {
        viewModel.resultLiveData.observe(this, Observer {
            binding.tvResult.text = it
        })
        viewModel.progress.observe(this, Observer {
            binding.pbLoading.isVisible = it
        })
    }
}