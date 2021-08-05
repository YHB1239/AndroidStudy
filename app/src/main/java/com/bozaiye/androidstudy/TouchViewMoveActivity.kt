package com.bozaiye.androidstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bozaiye.androidstudy.databinding.ActivityTouchViewMoveBinding
import com.bozaiye.androidstudy.utils.inflate
import com.bozaiye.androidstudy.view.TouchMoveView

class TouchViewMoveActivity : AppCompatActivity() {
    private val binding: ActivityTouchViewMoveBinding by inflate()
    private val mBt6Text = "Scoller演示"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_touch_view_move)
        initView()
        initListener()
        scrollerTest()
    }

    private fun initView() {
        binding.bt1.text = TouchMoveView.TYPE_SET_X_Y
        binding.bt2.text = TouchMoveView.TYPE_LAYOUT
        binding.bt3.text = TouchMoveView.TYPE_OFFSET
        binding.bt4.text = TouchMoveView.TYPE_LAYOUTPARAMS
        binding.bt5.text = TouchMoveView.TYPE_SCROLL_FUN
        binding.bt6.text = mBt6Text
    }

    private fun initListener() {
        binding.bt1.setOnClickListener {
            binding.titleText.text = TouchMoveView.TYPE_SET_X_Y
            binding.touchMoveView.setMoveType(TouchMoveView.TYPE_SET_X_Y)
        }
        binding.bt2.setOnClickListener {
            binding.titleText.text = TouchMoveView.TYPE_LAYOUT
            binding.touchMoveView.setMoveType(TouchMoveView.TYPE_LAYOUT)
        }
        binding.bt3.setOnClickListener {
            binding.titleText.text = TouchMoveView.TYPE_OFFSET
            binding.touchMoveView.setMoveType(TouchMoveView.TYPE_OFFSET)
        }
        binding.bt4.setOnClickListener {
            binding.titleText.text = TouchMoveView.TYPE_LAYOUTPARAMS
            binding.touchMoveView.setMoveType(TouchMoveView.TYPE_LAYOUTPARAMS)
        }
        binding.bt5.setOnClickListener {
            binding.titleText.text = TouchMoveView.TYPE_SCROLL_FUN
            binding.touchMoveView.setMoveType(TouchMoveView.TYPE_SCROLL_FUN)
        }
    }

    private fun scrollerTest() {
        val scrollerFragment = ScollerFragment.newInstance("null", "null")
        scrollerFragment.setClosePageListener(object : ScollerFragment.ClosePageListener{
            override fun closePage() {
                binding.fragmentParent.visibility = View.GONE
            }

        })

        binding.bt6.setOnClickListener {
            binding.titleText.text = mBt6Text
            binding.fragmentParent.visibility = View.VISIBLE
            supportFragmentManager.beginTransaction().replace(R.id.fragmentParent, scrollerFragment).commit()
        }
    }
}