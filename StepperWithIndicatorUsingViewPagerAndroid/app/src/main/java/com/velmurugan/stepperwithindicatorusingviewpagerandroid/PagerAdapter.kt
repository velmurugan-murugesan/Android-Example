package com.velmurugan.stepperwithindicatorusingviewpagerandroid

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

internal class PagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    var fragments = arrayOfNulls<Fragment>(4)

    override fun getCount(): Int {
        return 4
    }

    override fun getItem(position: Int): Fragment {

       return when(position) {
           0 -> OneFragment()
           1 -> TwoFragment()
           2 -> ThreeFragment()
           else -> FourFragment()
       }
    }
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val createdFragment = super.instantiateItem(container, position) as Fragment
        fragments[position] = createdFragment
        return createdFragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return "Page $position"
    }
}