package com.zhumingren.clientaidl;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.zhumingren.serveraidl.aidlfolder.IMyAidlInterface;

public class MainActivity extends AppCompatActivity {

    private IMyAidlInterface myAidlInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bind();
        findViewById(R.id.tv_hello).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callAddAidlService();
            }
        });
    }

    private void callAddAidlService() {
        try {
            int add = myAidlInterface.add(13, 14);
            Log.d("zmr", "add: " + add);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void bind() {
        Intent intent = new Intent("myaddservice");
        intent.setPackage("com.zhumingren.serveraidl");
        ComponentName componentName = new ComponentName("com.zhumingren.serveraidl", "com.zhumingren.serveraidl.MyAddService");
        ServiceConnection conn = new ServiceConnection() {

            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                myAidlInterface = IMyAidlInterface.Stub.asInterface(service);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        boolean b = bindService(intent, conn, Service.BIND_AUTO_CREATE);
        Log.i("zmr", "bind b= " + b);
    }
}
