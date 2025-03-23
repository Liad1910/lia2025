package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class IncomingCallReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);

        if (TelephonyManager.EXTRA_STATE_RINGING.equals(state)) {
            String number = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
            if (number != null) {
                Toast.makeText(context, "שיחה נכנסת מ: " + number, Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(context, "שיחה נכנסת (מספר חסום או לא זמין)", Toast.LENGTH_LONG).show();
            }
        }
    }
}
