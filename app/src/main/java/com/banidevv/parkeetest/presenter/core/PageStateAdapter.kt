package com.banidevv.parkeetest.presenter.core

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class PageStateAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    var listPage = mutableListOf<Fragment>()

    override fun getItemCount(): Int = listPage.size

    override fun createFragment(position: Int): Fragment = listPage[position]

    fun setItems(vararg fragment: Fragment) {
        this.listPage.addAll(fragment)
    }
}