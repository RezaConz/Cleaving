package com.example.cleaving

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.NonNull
import androidx.viewpager.widget.PagerAdapter

class ItemSliderAdapter(var dataSlider : List<Int>, var context : Activity?) : PagerAdapter() {

    lateinit var  layoutInflater: LayoutInflater

    override fun getCount(): Int {
        return dataSlider.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    @NonNull
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.image_row_slider, container, false)

        var imgVIewSlider : ImageView
        imgVIewSlider = view.findViewById(R.id.item_slider)

        imgVIewSlider.setImageResource((dataSlider[position]))
        container.addView(view,0)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}