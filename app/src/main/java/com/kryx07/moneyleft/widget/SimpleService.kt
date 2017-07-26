package com.kryx07.moneyleft.widget

import android.widget.RemoteViewsService
import android.content.Intent



/**
 * Created by wd41 on 26.07.17.
 */
class SimpleService : RemoteViewsService() {
    override fun onGetViewFactory(intent: Intent): RemoteViewsService.RemoteViewsFactory {
        return SimpleViewsFactory(applicationContext)
    }
}