package com.example.btth2_intentbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class mySmsReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        processReceive(context, intent);
    }

    private void processReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        String mess = "";
        String body = "";
        String addr = "";
        if(extras!=null){
            Object[] smsEtra = (Object[]) extras.get("pdus");
            for(int i=0; i<smsEtra.length;i++){
                SmsMessage sms = SmsMessage.createFromPdu((byte[]) smsEtra[i]);
                body = sms.getDisplayMessageBody();
                addr= sms.getOriginatingAddress();
                mess +="Co 1 tinh nhan tu "+addr+"\n"+body+"vua moi gui den";
            }
            Toast.makeText(context, mess, Toast.LENGTH_SHORT).show();
        }
    }
}
