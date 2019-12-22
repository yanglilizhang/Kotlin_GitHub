package com.devyk.kotlin_github.widget

import android.database.DataSetObserver
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.view.View
import com.devyk.common.utils.Weak
import com.devyk.kotlin_github.mvp.v.MainActivity
import kotlinx.android.synthetic.main.app_bar_main.*

class ActionBarController(private val mainActivity: MainActivity) {

    private val context by Weak<MainActivity> {
        mainActivity
    }

    private val tab by lazy {
        context!!.tabLayout
    }


    private val dataSetObserver by lazy {
        ViewPagerDataSetObserver(tab)
    }

    class ViewPagerDataSetObserver(private val tabLayout: TabLayout) : DataSetObserver() {
        var viewPager: ViewPager? = null
            set(value) {
                viewPager?.adapter?.unregisterDataSetObserver(this)
                value?.adapter?.registerDataSetObserver(this)
                field = value
            }

        override fun onChanged() {
            super.onChanged()
            viewPager?.let { viewPager ->
                if (viewPager.adapter?.count ?: 0 <= 1) {
                    tabLayout.visibility = View.GONE
                } else {
                    tabLayout.visibility = View.VISIBLE
                    tabLayout.tabMode =
                        if (viewPager.adapter?.count ?: 0 > 3)
                            TabLayout.MODE_SCROLLABLE
                        else
                            TabLayout.MODE_FIXED
                }
            }
        }
    }

    fun setupWithViewPager(viewPager: ViewPager?) {
        viewPager?.let(dataSetObserver::viewPager::set) ?: run { tab.visibility = View.GONE }
        tab.setupWithViewPager(viewPager)
    }
}