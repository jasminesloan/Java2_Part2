package com.jasminesloan.tamarbraxton.library;

import java.net.MalformedURLException;
import java.net.URL;


import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

public class SongService extends IntentService{
	
	public static final String CONNECT_INFO = "messenger";
	public static final String COMBINED_URL = "https://itunes.apple.com/search?term=tamar+braxton&limit=25"; 
	static URL finalURL = null;
	JSON JSONFileManager;

	public SongService() {
		super("SongService");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub
		
		Log.i("onHandleIntent", "Start");
		
		Bundle extras = intent.getExtras();
		Messenger messenger = (Messenger) extras.get(CONNECT_INFO);
		String combinedUrl = "https://itunes.apple.com/search?term=tamar+braxton&limit=25";

		try {
            finalURL = new URL(combinedUrl);
    } 
    catch (MalformedURLException e) {
            e.printStackTrace();
    }

    Message message = Message.obtain();
    message.arg1 = Activity.RESULT_OK;
    //message.obj = Network.getURLStringResponse(finalURL);
    JSON.getInstance();
    JSON.writeStringFile(getApplication(), "tamar.txt", Network.getURLStringResponse(finalURL));
    message.obj = "Service Complete";

    try {
            messenger.send(message);
    } 
    catch (RemoteException e) {
            Log.e("onHandleIntent", e.getMessage().toString());
            e.printStackTrace();
    	}
	} 
}