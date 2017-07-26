package com.kryx07.moneyleft.widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import android.content.Intent
import com.kryx07.moneyleft.R
import com.kryx07.moneyleft.SharedPreferencesManager
import timber.log.Timber


class NewAppWidget : AppWidgetProvider() {


    /*fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager,
                        appWidgetId: Int) {

        //CharSequence widgetText = context.getString(R.string.appwidget_text);

        //val intent = Intent(context, SimpleService::class.java)

        // Construct the RemoteViews object
        //val views = RemoteViews(context.getPackageName(), R.layout.new_app_widget)
        //views.setRemoteAdapter(R.id.widget_text, intent)
        //views.setTextViewText(R.id.appwidget_text, widgetText);

        // Instruct the widget manager to update the widget
        //
        val views = RemoteViews(context.packageName, R.layout.new_app_widget)
        views.setTextViewText(R.id.widget_text, SharedPreferencesManager(context).read(R.string.money_per_day_amount))
        appWidgetManager.updateAppWidget(appWidgetId, views)
    }
*/
    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            //updateAppWidget(context, appWidgetManager, appWidgetId)

            val views = RemoteViews(context.packageName, R.layout.new_app_widget)
            views.setTextViewText(R.id.widget_text, SharedPreferencesManager(context).read(R.string.money_per_day_amount))
            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.widget_text)
            appWidgetManager.updateAppWidget(appWidgetId, views)

            Timber.e(Integer.toString(appWidgetId))

            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.widget_text)

        }
    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)

        val action = intent.action
        if (action == AppWidgetManager.ACTION_APPWIDGET_UPDATE) {
            val views = RemoteViews(context.packageName, R.layout.new_app_widget)
            views.setTextViewText(R.id.widget_text, SharedPreferencesManager(context).read(R.string.money_per_day_amount))
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

}