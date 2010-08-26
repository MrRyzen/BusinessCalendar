/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.calendar.widget;

import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class CalendarAppWidgetReceiver extends BroadcastReceiver {

    private static final String TAG = "CalendarAppWidgetReceiver";
    private static final boolean LOGD = false;

    @Override
    public void onReceive(Context context, Intent intent) {
        // Launch over to service so it can perform update
        final Intent updateIntent = new Intent(
                CalendarAppWidgetProvider.ACTION_CALENDAR_APPWIDGET_UPDATE);

        // Copy over the relevant extra fields if they exist
        if (intent.hasExtra(CalendarAppWidgetProvider.EXTRA_EVENT_IDS)) {
            int[] data = intent.getIntArrayExtra(CalendarAppWidgetProvider.EXTRA_EVENT_IDS);
            updateIntent.putExtra(CalendarAppWidgetProvider.EXTRA_EVENT_IDS, data);
        }

        if (intent.hasExtra(AppWidgetManager.EXTRA_APPWIDGET_ID)) {
            int data = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
            updateIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, data);
        }

        if (intent.hasExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS)) {
            int[] data = intent.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS);
            updateIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, data);
        }

        if (LOGD) Log.d(TAG, "Something changed, updating widget");
        context.sendBroadcast(updateIntent);
    }
}