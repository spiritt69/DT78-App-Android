/*
 *
 * MIT License
 *
 * Copyright (c) 2021 Felix Biego
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.fbiego.dt78.data

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.fbiego.dt78.R

class ThemeAdapter(private val context: Context, private val mode: Boolean): BaseAdapter() {

    var inflater: LayoutInflater = LayoutInflater.from(context)
    private val item = arrayListOf("Dark", "Light", "Battery Saver", "System Default")
    private val colors = arrayListOf("Green", "Red", "Purple", "Blue")
    private val colId = arrayListOf(R.color.colorAccentGreen, R.color.colorAccentRed,
        R.color.colorAccentPurple, R.color.colorAccentBlue)
    private val themId = arrayListOf(Color.DKGRAY, Color.LTGRAY, Color.GRAY, Color.TRANSPARENT)

    override fun getView(i: Int, view1: View?, viewGroup: ViewGroup): View {

        val view = inflater.inflate(R.layout.spinner_item_2, null)
        val card = view.findViewById<CardView>(R.id.cardView)
        val text = view.findViewById<TextView>(R.id.text)

        if (mode) {
            card.backgroundTintList = ColorStateList.valueOf(themId[i])
            text.text = item[i]
        } else {
            //icon.setColorFilter(ContextCompat.getColor(context, colId[i]))
            card.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, colId[i]))
            text.text = colors[i]
        }

        return view

    }

    override fun getItem(p0: Int): Any {
        return if (mode){
            item[p0]
        } else {
            colors[p0]
        }
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return if (mode){
            item.size
        } else {
            colors.size
        }
    }
}