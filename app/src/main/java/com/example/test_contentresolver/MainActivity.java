package com.example.test_contentresolver;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button);
        //点击Button时实现的方法
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //content://sms 查询系统所有短信的Uri
                Uri uri = Uri.parse("content://sms/");
                //获取ContentResolver对象
                ContentResolver resolver = getContentResolver();
                //通过ContentResolver对象查询系统短信
                Cursor cursor = resolver.query(uri, new String[]{"address", "date", "type", "body"}, null, null, null);
                List<SmsInfo> smsInfos = new ArrayList<SmsInfo>();
                while (cursor.moveToNext()) {
                    String address = cursor.getString(0);
                    long date = cursor.getLong(1);
                    int type = cursor.getInt(2);
                    String body = cursor.getString(3);
                    SmsInfo smsInfo = new SmsInfo(date, type, body, address);
                    smsInfos.add(smsInfo);
                }
                cursor.close();
                SmsUtils.backUpSms(smsInfos, MainActivity.this);
            }
        });
    }
//
//    public void click(View view) {
//        Uri uri = Uri.parse("content://sms/");
//        ContentResolver resolver = getContentResolver();
//        Cursor cursor = resolver.query(uri, new String[]{"address", "date", "type", "body"}, null, null, null);
//        List<SmsInfo> smsInfos = new ArrayList<SmsInfo>();
//        while (cursor.moveToNext()) {
//            String address = cursor.getString(0);
//            long date = cursor.getLong(1);
//            int type = cursor.getInt(2);
//            String body = cursor.getString(3);
//            SmsInfo smsInfo = new SmsInfo(date, type, body, address);
//            smsInfos.add(smsInfo);
//        }
//        cursor.close();
//        SmsUtils.backUpSms(smsInfos, this);
//    }
}
