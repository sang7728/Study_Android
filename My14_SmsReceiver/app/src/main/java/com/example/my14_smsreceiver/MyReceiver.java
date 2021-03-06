package com.example.my14_smsreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyReceiver extends BroadcastReceiver {
    private static final String TAG = "main:MyReceiver";

    public SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    //백그라운드에서 대기하다가 자신에게 오는 데이터를 수신
    @Override //                    메인   메세지수신 데이터
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: 호출됨");

        //내부적으로 데이터를 저장할수 있는 객체 : Bundle
        Bundle bundle = intent.getExtras();
        SmsMessage[] messages = parseSmsMessage(bundle);

        if(messages != null && messages.length > 0) {
            Log.d(TAG, "onReceive: SMS를 수신하였습니다.");

            //보낸사람 전화번호
            String sender = messages[0].getOriginatingAddress();
            Log.d(TAG, "sender : " + sender);

            //보낸 날짜와 시간
            Date receivedDate = new Date(messages[0].getTimestampMillis());
            Log.d(TAG, "receivedDate: " + receivedDate);

            //메세지 내용
            String contents = messages[0].getMessageBody();
            Log.d(TAG, "contents: " + contents);

            //인텐트를 만들어서 데이터를 넣은후 액티비티를 띄운다.
            Intent disIntent = new Intent(context, SmsDisActivity.class);
            //액티비티가 아닌곳에서 인텐트를 만들어 사용할때
            //FLAG_ACTIVITY_NEW_TASK를 플래그에 추가해야 한다.
            //FLAG_ACTIVITY_SINGLE_TOP은 똑같은 화면이 띄워져 있는 상태라면
            //그 화면을 재사용 하겠다.
            disIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);

            disIntent.putExtra("sender", sender);
            disIntent.putExtra("receivedDate", dateFormat.format(receivedDate));
            disIntent.putExtra("contents", contents);
            context.startActivity(disIntent);
        }
    }

    private SmsMessage[] parseSmsMessage(Bundle bundle) {
        Object[] objs = (Object[]) bundle.get("pdus");
        SmsMessage[] messages = new SmsMessage[objs.length];

        for (int i = 0; i < objs.length; i++) {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { //API23
                String format = bundle.getString("format");
                messages[i] = SmsMessage.createFromPdu((byte[]) objs[i], format);
            } else {
                messages[i] = SmsMessage.createFromPdu((byte[]) objs[i]);
            }
        }
        return messages;
    }
}