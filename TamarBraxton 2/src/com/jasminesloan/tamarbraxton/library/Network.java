package com.jasminesloan.tamarbraxton.library;

import java.io.BufferedInputStream;
import java.net.URL;
import java.net.URLConnection;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class Network {
	   
		static Boolean _connected = false;
	    static String _connectionType = "Unavailable";

	    public static String getConnectionType(Context context) {
	            netInfo(context);
	            return _connectionType;
	    }

	    public static Boolean getConnectionStatus(Context context) {
	            netInfo(context);
	            return _connected;
	    }

	    private static void netInfo(Context context) {
	            ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
	            NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
	            if (networkInfo != null) {
	                    if (networkInfo.isConnected()) {
	                            _connectionType = networkInfo.getTypeName();
	                            _connected = true;
	                    } 
	                    else {
	                            _connectionType = null;
	                            _connected = false;
	                    }
	            }
	    }

	    public static String getURLStringResponse(URL url) {
	            String response = "";

	            try {
	                    URLConnection urlConn = url.openConnection();
	                    BufferedInputStream bufferedInput = new BufferedInputStream(urlConn.getInputStream());

	                    byte[] contentBytes = new byte[1024];
	                    int bytesRead = 0;
	                    StringBuffer responseBuffer = new StringBuffer();

	                    while ((bytesRead = bufferedInput.read(contentBytes)) != -1) {
	                            response = new String(contentBytes, 0, bytesRead);
	                            responseBuffer.append(response);
	                    }
	                    return responseBuffer.toString();

	            } 
	            catch(Exception e) {
	                    Log.e("URL RESPONSE ERROR", "getURLStringResponse");
	            }

	            return response;
	    }
	}
