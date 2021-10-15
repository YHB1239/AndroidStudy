package com.bozaiye.androidstudy.view_study.view_anim

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bozaiye.androidstudy.R
import com.bozaiye.androidstudy.databinding.ActivityAnimLearnBinding
import com.bozaiye.androidstudy.utils.JumpUtil
import com.bozaiye.androidstudy.utils.inflate
import com.bozaiye.androidstudy.view_study.view_anim.frameAnim.FrameAnimActivity

class AnimLearnActivity : AppCompatActivity() {
    private val binding: ActivityAnimLearnBinding by inflate()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_anim_learn)
        initListener()
    }

    private fun initListener() {
        binding.btFrame.setOnClickListener {
            JumpUtil.goToActivity(this, FrameAnimActivity::class.java)
        }

        binding.btTweened.setOnClickListener {

        }

        binding.btProperty.setOnClickListener {

        }
    }
}