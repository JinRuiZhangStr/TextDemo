package com.example.textdemo;

import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cn.smssdk.SMSSDK;

public class SharaActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText sEdit_phone,sEdit_number;
    private Button sBtn,sBtn_login;
    private SMSContent smsObsever;//短信观察者
    private String body;
    private TextView li;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shara);
        initView();
    }

    private void initView() {
        sEdit_phone= (EditText) findViewById(R.id.sEdit_phone);
        sEdit_number= (EditText) findViewById(R.id.sEdit_number);
        sBtn= (Button) findViewById(R.id.sBtn);
        sBtn_login= (Button) findViewById(R.id.sBtn_login);
        li= (TextView) findViewById(R.id.li);
        SMSSDK.initSDK(this,"1b440b915a0bf","fee2774007ac729f972d744e472fa809");
        smsObsever=new SMSContent(handler);
        getContentResolver().registerContentObserver(Uri.parse("content://sms/"), true, smsObsever);
        sBtn.setOnClickListener(this);
        sBtn_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sBtn:
                String phone=sEdit_phone.getText().toString();
                if (phone.trim().length()==11){
                    SMSSDK.getVerificationCode("86",phone);
                    sEdit_phone.requestFocus();
                }else {
                    Toast.makeText(SharaActivity.this, "请输入完整电话号码", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.sBtn_login:
                String number=sEdit_number.getText().toString();
//                if (body.equals(number)){
                    Intent intent=new Intent(SharaActivity.this,JTActivity.class);
                    startActivity(intent);
//                }
                break;
        }
    }



    public class SMSContent extends ContentObserver {
        /**
         * Creates a content observer.
         *
         * @param handler The handler to run {@link #onChange} on, or null if none.
         */
        private Handler mHandler;
        public SMSContent(Handler handler) {
            super(handler);
            mHandler=handler;
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            Cursor cursor = null;
            try {
                cursor = getContentResolver().query(
                        Uri.parse("content://sms/inbox"), null, null, null,
                        "date desc");
                if(cursor!=null){
                    if(cursor.moveToNext()){//不遍历只拿当前最新的一条短信
                        //获取当前的短信内容
                        body=cursor.getString(cursor.getColumnIndex("body"));
                        Message msg=Message.obtain();
                        Bundle bundle=new Bundle();
                        bundle.putString("body", body);
                        msg.setData(bundle);
                        mHandler.sendMessage(msg);
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
                if(cursor!=null){
                    cursor.close();
                }

            }
        }
    }

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle=msg.getData();
            body=bundle.getString("body");
            li.setText(body);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getContentResolver().unregisterContentObserver(smsObsever);
    }
}
