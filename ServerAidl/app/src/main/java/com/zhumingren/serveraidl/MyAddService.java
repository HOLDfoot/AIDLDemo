package com.zhumingren.serveraidl;

import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.zhumingren.serveraidl.aidlfolder.IMyAidlInterface;

import java.io.ByteArrayOutputStream;

public class MyAddService extends Service {
    public MyAddService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    class MyBinder extends IMyAidlInterface.Stub{

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public int add(int a, int b) throws RemoteException {
            return a + b;
        }

        @Override
        public byte[] getOriginBitmapBytes() throws RemoteException {
            //Bitmap bitmap = BitmapFactory.decodeFile("/sdcard/1/surfacebitmap.png");
            Bitmap bitmap = BitmapFactory.decodeResource(MyAddService.this.getResources(), R.drawable.surface_bitmap);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            byte[] bytes = outputStream.toByteArray();
            Log.e("zyl", "getOriginBitmapBytes bytes length: " + bytes.length);
            return bytes;
        }
    }
}
