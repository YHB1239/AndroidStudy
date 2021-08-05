package com.bozaiye.androidstudy.utils

import android.app.Activity
import android.content.Intent

object JumpUtil {
    fun goToActivity(fromActivity: Activity, toActivity: Class<*>){
        fromActivity.startActivity(Intent(fromActivity, toActivity))
    }
}