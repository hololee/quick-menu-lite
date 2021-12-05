package com.hanstudio.quick.lite;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

/**
 * Created by L on 2015-10-25.
 */
public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPreferences pref = context.getSharedPreferences("bool" ,Context.MODE_APPEND);
        boolean flag = pref.getBoolean("isRunning", false);

        if(flag) {

            Intent intents = new Intent(context, RunningViewService.class);
            context.startService(intents);
        }
    }
}
