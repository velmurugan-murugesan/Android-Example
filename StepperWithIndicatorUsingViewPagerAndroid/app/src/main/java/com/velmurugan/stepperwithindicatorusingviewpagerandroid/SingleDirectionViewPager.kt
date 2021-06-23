package com.velmurugan.stepperwithindicatorusingviewpagerandroid

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

enum class SwipeDirection {
    ALL, LEFT, RIGHT, NONE
}

class SingleDirectionViewPager @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?
) : ViewPager(context, attrs) {

    private var initialXValue = 0f
    private var direction: SwipeDirection = SwipeDirection.ALL

    override fun onTouchEvent(event: MotionEvent): Boolean =
            if (isSwipeAllowed(event)) {
                super.onTouchEvent(event)
            } else {
                false
            }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean =
            if (isSwipeAllowed(event)) {
                super.onInterceptTouchEvent(event)
            } else {
                false
            }

    private fun isSwipeAllowed(event: MotionEvent): Boolean {
        if (direction == SwipeDirection.ALL) {
            return true
        }

        if (direction == SwipeDirection.NONE) {
            return false
        }

        if (event.action == MotionEvent.ACTION_DOWN) {
            initialXValue = event.x
            return true
        }

        if (event.action == MotionEvent.ACTION_MOVE) {
            val diffX = event.x - initialXValue

            if (diffX > 0 && direction === SwipeDirection.RIGHT) {
                // swipe from left to right detected
                return false
            } else if (diffX < 0 && direction === SwipeDirection.LEFT) {
                // swipe from right to left detected
                return false
            }
        }

        return true
    }

    fun setAllowedSwipeDirection(direction: SwipeDirection) {
        this.direction = direction
    }
}