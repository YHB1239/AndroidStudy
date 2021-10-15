package com.bozaiye.androidstudy.view_study.view_anim.frameAnim

import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bozaiye.androidstudy.R
import com.bozaiye.androidstudy.databinding.ActivityFrameAnimBinding
import com.bozaiye.androidstudy.utils.inflate

class FrameAnimActivity : AppCompatActivity() {
    private val binding: ActivityFrameAnimBinding by inflate()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frame_anim)
        binding.imageView.setImageResource(R.drawable.frame_anim_list)
        val drawable =  binding.imageView.drawable as AnimationDrawable
        drawable.isOneShot = false
        drawable.start()
    }
}