package com.bozaiye.androidstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import com.bozaiye.androidstudy.databinding.ActivityAnimalTestBinding
import com.bozaiye.androidstudy.utils.inflate
import com.bozaiye.androidstudy.view.FitImageView

class AnimalTestActivity : AppCompatActivity() {
    private val binding: ActivityAnimalTestBinding by inflate()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animal_test)
        val imageView1 = FitImageView(this)
        val imageView2 = FitImageView(this)
        val layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT)
        imageView1.layoutParams = layoutParams
        imageView2.layoutParams = layoutParams
        imageView1.setImageResource(R.drawable.ic_register_male)
        imageView2.setImageResource(R.drawable.ic_register_female)
        val imageList = ArrayList<ImageView>()
        imageList.add(imageView1)
        imageList.add(imageView2)
        binding.gradientView.setImageViews(imageList)
    }
}