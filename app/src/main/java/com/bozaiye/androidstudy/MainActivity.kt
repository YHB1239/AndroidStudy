package com.bozaiye.androidstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bozaiye.androidstudy.databinding.ActivityMainBinding
import com.bozaiye.androidstudy.utils.JumpUtil
import com.bozaiye.androidstudy.utils.inflate
import com.bozaiye.androidstudy.view_study.view_anim.AnimLearnActivity
import com.bozaiye.androidstudy.view_study.view_move.TouchViewMoveActivity

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

        binding.btAnimal.setOnClickListener {
            JumpUtil.goToActivity(this, AnimLearnActivity::class.java)
        }
    }
}