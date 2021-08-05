package com.bozaiye.androidstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.bozaiye.androidstudy.databinding.ActivityMainBinding
import com.bozaiye.androidstudy.utils.JumpUtil
import com.bozaiye.androidstudy.utils.inflate

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by inflate()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initListener()
    }

    private fun initListener() {
        binding.btView.setOnClickListener {
            JumpUtil.goToActivity(this, TouchViewMoveActivity::class.java)
        }
    }
}