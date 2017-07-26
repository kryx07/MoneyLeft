package com.kryx07.moneyleft.widget

import android.content.Context
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import com.kryx07.moneyleft.R


class SimpleViewsFactory(val context: Context) : RemoteViewsService.RemoteViewsFactory {

    override fun onCreate() {

    }

    override fun onDataSetChanged() {

    }

    override fun onDestroy() {

    }

    override fun getCount(): Int {
        return 1
    }

    override fun getViewAt(position: Int): RemoteViews? {
        // val row = RemoteViews(context.packageName, R.layout.simple_row)
        //row.setTextViewText(R.id.text_view, articleList[position].getName())

        return null
    }

    override fun getLoadingView(): RemoteViews? {
        return null
    }

    override fun getViewTypeCount(): Int {
        return 1
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun hasStableIds(): Boolean {
        return true
    }
}