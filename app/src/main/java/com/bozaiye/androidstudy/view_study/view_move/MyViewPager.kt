package com.bozaiye.androidstudy.view_study.view_move

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.ViewConfiguration
import android.view.ViewGroup
import android.widget.Scroller
import kotlin.math.abs


class MyViewPager: ViewGroup {
    private var mScroller: Scroller = Scroller(context)
    val mViewConfiguration = ViewConfiguration.get(context)
    val mTouchSlop = mViewConfiguration.scaledPagingTouchSlop
    val mMaxVelocity = mViewConfiguration.scaledMinimumFlingVelocity
    private var mVelocityTracker: VelocityTracker? = null
    private var mCurrentPage = 0
    private var mLastX = 0

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val count = childCount
        for (i in 0 until count) {
            val child = getChildAt(i)
            measureChild(child, widthMeasureSpec, heightMeasureSpec)
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(p0: Boolean, l: Int, t: Int, r: Int, b: Int) {
        val count = childCount
        Log.d(
            "TAG",
            "--l-->$l,--t-->$t,-->r-->$r,--b-->$b"
        )
        for (i in 0 until count) {
            val child = getChildAt(i)
            child.layout(i * width, 0, (i + 1) * width, height)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        initVelocityTrackerIfNotExists()
        mVelocityTracker?.addMovement(event)
        val x = event?.x?.toInt() ?: 0
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                if(!mScroller.isFinished){
                    mScroller.abortAnimation()
                }
                mLastX = x
            }
            MotionEvent.ACTION_MOVE -> {
                val dx = mLastX - x
                scrollBy(dx,0)
                mLastX = x
            }
            MotionEvent.ACTION_UP -> {
                mVelocityTracker?.computeCurrentVelocity(1000)
                val initVelocity = mVelocityTracker?.xVelocity?.toInt() ?: 0
                if (initVelocity > mMaxVelocity && mCurrentPage > 0) { //如果是快速的向右滑，则需要显示上一个屏幕
                    Log.d("TAG", "----------------快速的向右滑--------------------")
                    scrollToPage(mCurrentPage - 1)
                } else if (initVelocity < -mMaxVelocity && mCurrentPage < childCount - 1) { //如果是快速向左滑动，则需要显示下一个屏幕
                    Log.d("TAG", "----------------快速的向左滑--------------------")
                    scrollToPage(mCurrentPage + 1)
                } else { //不是快速滑动的情况，此时需要计算是滑动到
                    Log.d("TAG", "----------------慢慢的滑动--------------------")
                    slowScrollToPage()
                }
                recycleVelocityTracker()
            }
            else -> {
            }
        }
        return true
    }

    private fun initVelocityTrackerIfNotExists() {
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain()
        }
    }

    /**
     * 缓慢滑动抬起手指的情形，需要判断是停留在本Page还是往前、往后滑动
     */
    private fun slowScrollToPage() {
        //当前的偏移位置
        val scrollX = scrollX
        val scrollY = scrollY
        //判断是停留在本Page还是往前一个page滑动或者是往后一个page滑动
        val whichPage = (getScrollX() + width / 2) / width
        scrollToPage(whichPage)
    }

    /**
     * 滑动到指定屏幕
     * @param indexPage
     */
    private fun scrollToPage(indexPage: Int) {
        mCurrentPage = indexPage
        if (mCurrentPage > childCount - 1) {
            mCurrentPage = childCount - 1
        }
        //计算滑动到指定Page还需要滑动的距离
        val dx = mCurrentPage * width - scrollX
        mScroller.startScroll(
            scrollX,
            0,
            dx,
            0,
            abs(dx) * 2
        ) //动画时间设置为Math.abs(dx) * 2 ms
        //记住，使用Scroller类需要手动invalidate
        invalidate()
    }

    private fun recycleVelocityTracker() {
        if (mVelocityTracker != null) {
            mVelocityTracker?.recycle()
            mVelocityTracker = null
        }
    }

    override fun computeScroll() {
        super.computeScroll()
        if(mScroller.computeScrollOffset()){
            scrollTo(mScroller.currX,mScroller.currY)
            invalidate()
        }
    }
}