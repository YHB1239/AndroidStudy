package com.bozaiye.androidstudy.view

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Scroller
import androidx.annotation.RequiresApi


class TouchMoveView : View {


    private var mScroller: Scroller? = null

    companion object {
        const val TYPE_SET_X_Y = "set_x_y"
        const val TYPE_LAYOUT = "layout"
        const val TYPE_OFFSET = "offset"
        const val TYPE_LAYOUTPARAMS = "layoutParams"
        const val TYPE_SCROLL_FUN = "scrollFun"
    }

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private var rawX = 0
    private var rawY = 0

    private var mMoveType = ""

    fun setMoveType(moveType: String) {
        mMoveType = moveType
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun onTouchEvent(motionEvent: MotionEvent?): Boolean {
        val x = motionEvent?.x?.toInt() ?: 0
        val y = motionEvent?.y?.toInt() ?: 0
        when (motionEvent?.action) {
            MotionEvent.ACTION_DOWN -> {
                rawX = x
                rawY = y
            }
            MotionEvent.ACTION_MOVE -> {
                val offsetX = x - rawX //计算滑动的距离
                val offsetY = y - rawY

                when (mMoveType) {
                    TYPE_SET_X_Y -> {
                        this.x = (this.x + offsetX)
                        this.y = (this.y + offsetY)
                    }
                    TYPE_LAYOUT -> {
                        val l: Int = left
                        val r: Int = right
                        val t: Int = top
                        val b: Int = bottom
                        layout(l + offsetX, t + offsetY, r + offsetX, b + offsetY)
                    }
                    TYPE_OFFSET -> {
                        offsetLeftAndRight(offsetX)
                        offsetTopAndBottom(offsetY)
                    }
                    TYPE_LAYOUTPARAMS -> {
                        layoutParams = layoutParams.apply {
                            if (this is ViewGroup.MarginLayoutParams) {
                                leftMargin += offsetX
                                topMargin += offsetY
                            }
                        }
                    }
                    TYPE_SCROLL_FUN -> {
                        (parent as View).scrollBy(scrollX - offsetX, scrollY - offsetY)
                    }
                    else -> {

                    }
                }

            }
            MotionEvent.ACTION_UP -> {
            }
        }
        return true
    }

    init {
        mScroller = Scroller(context)
    }
}