package com.jasminesloan.tamarbraxton.library;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import android.content.Context;
import android.util.Log;


public class JSON {

	
	private static JSON tamarInstance;

 private JSON() { 

 }
 
 public static JSON getInstance(){
	 if(tamarInstance == null){
		 tamarInstance = new JSON();
	 }
	return tamarInstance;
	 
 }
 
 public static Boolean writeStringFile (Context context, String filename, String content){
     Boolean result = false;
     FileOutputStream output = null;
     
     try{
             output = context.openFileOutput(filename, Context.MODE_PRIVATE);
             output.write(content.getBytes());
             Log.i("Write String File", "String written");
     }catch (Exception e){
             Log.e("Write Error", e.toString());
     }
      
      return result;
      
}

public static String readStringFile (Context context, String filename) {
  String content = "";

  FileInputStream fins = null;

  try {
          fins = context.openFileInput(filename);

          BufferedInputStream bins = new BufferedInputStream(fins);
          byte[] contentBytes = new byte[1024];
          int bytesRead = 0;
          StringBuffer contentBuffer = new StringBuffer();

          while ((bytesRead = bins.read(contentBytes)) != -1) {
                  content = new String(contentBytes, 0, bytesRead);
                  contentBuffer.append(content);
          }
          content = contentBuffer.toString();
          fins.close();
          }catch (Exception e) {
                  
          } 

    
  return content;
}

public static Boolean dataStringFile(Context context)
{
	Boolean stored = false;
	File storedData = context.getFileStreamPath("tamar.txt");
		if(storedData.exists())
		{
			stored = true;
		}
	return stored;
	}
      
}
