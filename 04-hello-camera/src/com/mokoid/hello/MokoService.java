package com.mokoid.hello;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MokoService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	public void onStart(Intent intent, int startId) {
		Log.i("Moko", "MokoService is started.");
	}
}
