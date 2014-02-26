package com.jasminesloan.tamarbraxton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.jasminesloan.tamarbraxton.library.JSON;
import com.jasminesloan.tamarbraxton.library.Network;
import com.jasminesloan.tamarbraxton.library.SongService;

@SuppressLint("HandlerLeak") 
public class MainActivity extends Activity implements MainActivityFragment.onListItemClicked{


	TextView albumMessage;
	ListView listview;
	JSON json;
	Boolean connected = false;
	Context _context;
	static JSONArray resultsArray;
	String JSONString = "https://itunes.apple.com/search?term=tamar+braxton&limit=25";
	Button searchButton;
	Button displayButton;
	EditText searchField;
	static SimpleAdapter adapter;
	MainActivityFragment fragment;
	ArrayList<HashMap<String, String>> albumList = new ArrayList<HashMap<String,String>>();
	
    @SuppressWarnings("unused")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.fragment_one);
        
        _context = this;
        
        fragment = (MainActivityFragment)getFragmentManager().findFragmentById(R.id.fragment1);
        
        json = JSON.getInstance();
        Log.i("MAIN", "creating JSON instance");        

        
        connected = Network.getConnectionStatus(_context);
                    if(connected){
                    	Log.i("Network Connected", Network.getConnectionType(_context));
                    }else{
                    	String files = JSON.readStringFile(_context, "tamar.txt");
                    	
                    	if(files !=null && !files.isEmpty()){
                    		Toast toast = Toast.makeText(_context, "No Network Connection, Last Search Loaded", Toast.LENGTH_SHORT);
                            toast.show();
                            
                            fragment.getFragmentData();
                    	}
                    	else{
                    		Toast.makeText(_context, "There Is No Info To Load", Toast.LENGTH_LONG).show();
                            return;
                    	}
                    }
        
        final Handler messageHandler = new Handler() {
        	public void handleMessage(Message message) {

        		Object returnedObject = message.obj;

        		Log.i("handleMessage", "here");

        		if (message.arg1 == RESULT_OK && returnedObject != null) {

        			Log.i("MAIN", "result ok, calling displayInfo");
        			fragment.getFragmentData();
        		}
        	}

};
         Messenger serviceMessenger = new Messenger(messageHandler);    

         Intent getJSONIntent = new Intent(this, SongService.class);
         getJSONIntent.putExtra("messenger", serviceMessenger);
         startService(getJSONIntent);
}
    
        

 /**   public void displayInfo()
    {
    	 JSON.getInstance();
         String JSONString = JSON.readStringFile(this, "tamar.txt");
        // Log.i("MAIN", "data = "+ JSONString);
        // final ArrayList<HashMap<String, String>> albumList = new ArrayList<HashMap<String,String>>();
         HashMap<String, String> displayAlbum;

         JSONObject object = null;
         JSONArray records = null;

         try {
        	 object = new JSONObject(JSONString);   
        	 records = object.getJSONArray("results");
  
        	 int count = object.getInt("resultCount");
 
        	 albumMessage.setText("There are " + count + " songs");

        	 for(int i = 0; i < count; i++){
             
             JSONObject recordObject = records.getJSONObject(i);
             String album;
             String track;
             String trackNumber;
             String artistView;
             String country;
             String genre;
             String release;
             
             try {
                     album = recordObject.getString("collectionName");
             } catch (Exception e) {
                     album = "unknown";
             }
             try {
                     track = recordObject.getString("trackName");
             } catch (Exception e) {
                     track = "unknown";
             }
             try {
                     trackNumber = recordObject.getString("trackNumber");
             } catch (Exception e){
                     trackNumber = "unknown";
             }
             try {
            	 artistView = recordObject.getString("artistViewUrl");
             } catch (Exception e){
        	 artistView = "unknown";
         	} 
             try {
         		country = recordObject.getString("country");
         	} catch (Exception e){
         		country = "unknown";
         	}
             try {
            	 genre = recordObject.getString("primaryGenreName");
          	} catch (Exception e){
          		genre = "unknown";
          	}
             try {
            	 release = recordObject.getString("releaseDate");
         	} catch (Exception e){
         		release = "unknown";
         	}


             displayAlbum = new HashMap<String, String>();
             displayAlbum.put("collectionName", album);
             displayAlbum.put("trackName", track);
             displayAlbum.put("trackNumber", trackNumber);
             displayAlbum.put("artistViewUrl", artistView);
             displayAlbum.put("country", country);
             displayAlbum.put("primaryGenreName", genre);
             displayAlbum.put("releaseDate", release);

             albumList.add(displayAlbum);
               } 
         }catch (Exception e) {
   				// TODO Auto-generated catch block
   				e.printStackTrace();
   			}
         }
        

			public void startResultActivity(String songTitle, String albumName, String songNumber, String artistUrl, String country, String genre, String release){
				Intent intent = new Intent(_context, ActivityTwo.class);
				intent.putExtra("collectionName", albumName);
				intent.putExtra("trackName", songTitle);
				intent.putExtra("trackNumber", songNumber);
				intent.putExtra("artistViewUrl", artistUrl);
				intent.putExtra("country", country);
				intent.putExtra("primaryGenreName", genre);
				intent.putExtra("releaseDate", release);
				
				startActivityForResult(intent, 0);
				Log.i("MAIN", "OnItemClick Selected");
				
			}
       }**/
     

         
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		if(resultCode == RESULT_OK && requestCode == 0){
			if(data.hasExtra("trackName")){
				
				Toast.makeText(getApplicationContext(), "trackName = " + data.getExtras().getString("trackName"), Toast.LENGTH_SHORT).show();
			}
		}
		
	}
    
	protected void onSaveInstanceState(Bundle outState){
		super.onSaveInstanceState(outState);
		
		if(albumList != null && !albumList.isEmpty())
		{
			outState.putSerializable("saved", (Serializable) albumList);
			
		}
	}
	
	@SuppressWarnings("unchecked")
	public void onRestoreInstanceState(Bundle savedInstanceState){
		super.onRestoreInstanceState(savedInstanceState);
		
		albumList = (ArrayList<HashMap<String, String>>) savedInstanceState.getSerializable("saved");
		SimpleAdapter albumAdapter = new SimpleAdapter(this, albumList, R.layout.listview, new String[] {"collectionName", "trackName", "trackNumber"}, new int[] {R.id.album, R.id.song, R.id.trackNumber});
        listview.setAdapter(albumAdapter);
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
	public void onListItemClicked(String songTitle, String albumName, String songNumber, String artistUrl, String country, String genre, String release) {
			
			Intent intent = new Intent(_context, ActivityTwo.class);
			intent.putExtra("collectionName", albumName);
			intent.putExtra("trackName", songTitle);
			intent.putExtra("trackNumber", songNumber);
			intent.putExtra("artistViewUrl", artistUrl);
			intent.putExtra("country", country);
			intent.putExtra("primaryGenreName", genre);
			intent.putExtra("releaseDate", release);
			
			startActivityForResult(intent, 0);
			Log.i("MAIN", "OnItemClick Selected");
		
	} 
    
}
