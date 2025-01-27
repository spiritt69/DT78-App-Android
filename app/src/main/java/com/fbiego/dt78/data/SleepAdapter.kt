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
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.fbiego.dt78.R
import java.util.*
import kotlin.collections.ArrayList

class SleepAdapter(private val data: ArrayList<SleepData>)
    : RecyclerView.Adapter<SleepAdapter.DataHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): DataHolder {
        val inflater = LayoutInflater.from(p0.context)
        val view = inflater.inflate(R.layout.sleep_item, p0, false)
        return DataHolder(view, p0.context)
    }

    override fun getItemCount(): Int = data.size

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onBindViewHolder(p0: DataHolder, p1: Int) {
        p0.bind(data[p1])
    }

    class DataHolder(itemView: View, private val context: Context) : RecyclerView.ViewHolder(itemView){
        private val mTime: TextView = itemView.findViewById(R.id.time)
        private val mDuration: TextView = itemView.findViewById(R.id.duration)
        private val mType: TextView = itemView.findViewById(R.id.type)
        private val card: CardView = itemView.findViewById(R.id.cardView)
        private val cnt = context

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        fun bind (sleep: SleepData){

            //mTime.text = String.format("%02d-%02d-20%02d", sleep.day, sleep.month, sleep.year)
            val cal = Calendar.getInstance(Locale.getDefault())
            cal.set(Calendar.HOUR_OF_DAY, sleep.hour)
            cal.set(Calendar.MINUTE, sleep.minute)
            cal.add(Calendar.MINUTE, sleep.duration)
            mTime.text = String.format("%02d:%02d - %02d:%02d", sleep.hour, sleep.minute, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE))
            mDuration.text = time(sleep.duration)
            mType.text = type(sleep.type, context)

            if (sleep.type == 2){
                card.backgroundTintList = ColorStateList.valueOf(cnt.getColorFromAttr(R.attr.colorCardBackgroundDark))
            } else {
                card.backgroundTintList = ColorStateList.valueOf(cnt.getColorFromAttr(R.attr.colorCardBackgroundLight))
            }


        }



    }
}