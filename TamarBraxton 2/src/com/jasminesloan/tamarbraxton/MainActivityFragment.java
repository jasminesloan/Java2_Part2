package com.jasminesloan.tamarbraxton;


import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;

import com.jasminesloan.tamarbraxton.library.JSON;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;


@SuppressLint("NewApi")
public class MainActivityFragment extends Fragment implements OnClickListener {

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
	ArrayList<HashMap<String, String>> albumList = new ArrayList<HashMap<String,String>>();
	
    private onListItemClicked parentActivity;
    
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		
		if (activity instanceof onListItemClicked){
			parentActivity = (onListItemClicked) activity;
		}
		
		else{
			throw new ClassCastException(activity.toString() + "must implement onListItmeClicked");
		}
	}
	
    public interface onListItemClicked{
    	void onListItemClicked(String songTitle, String albumName, String songNumber, String artistUrl, String country, String genre, String release);
    	ArrayList<HashMap<String, String>> albumList = new ArrayList<HashMap<String,String>>();
    	
    	
    }

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		View view = inflater.inflate(R.layout.activity_main, container);
		
        
        albumMessage = (TextView) view.findViewById(R.id.header);
        listview = (ListView) view.findViewById(R.id.songList);
        listview.setTextFilterEnabled(true);

        View songHeader = inflater.inflate(R.layout.headers, null);
        listview.addHeaderView(songHeader);
        
        searchButton = (Button) view.findViewById(R.id.songIdButton);
        searchButton.setOnClickListener(this);
		
        
        Button webButton = (Button) view.findViewById(R.id.siteButton);
        webButton.setOnClickListener(new OnClickListener() {
    		
    		@Override
    		public void onClick(View v) {
    			// TODO Auto-generated method stub
    			Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.tamarbraxton.com/"));
                startActivity(browser);
    		}
        
    	});
        
        Button displayButton = (Button) view.findViewById(R.id.displayButton);
        displayButton.setOnClickListener(new OnClickListener() {
    		
    		@Override
    		public void onClick(View v) {
    			// TODO Auto-generated method stub
    			displayInfo();
    		}
       });
        
        return view;
	}
        
        
    		
    		

			private void displayInfo() {
				// TODO Auto-generated method stub
				SimpleAdapter albumAdapter = new SimpleAdapter(getActivity(), albumList, R.layout.listview, new String[] {"collectionName", "trackName", "trackNumber"}, new int[] {R.id.album, R.id.song, R.id.trackNumber});
		        listview.setAdapter(albumAdapter);
		        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
						// TODO Auto-generated method stub
						@SuppressWarnings("unchecked")
						HashMap<String, String> songMap = (HashMap<String, String>)parent.getItemAtPosition(position);
						
						String songTitle = songMap.get("trackName").toString();
						String albumName = songMap.get("collectionName").toString();
						String songNumber = songMap.get("trackNumber").toString();
						String artistUrl = songMap.get("artistViewUrl").toString();
						String country = songMap.get("country").toString();
						String genre = songMap.get("primaryGenreName").toString();
						String release = songMap.get("releaseDate").toString();
					
						parentActivity(songTitle, albumName, songNumber, artistUrl, country, genre, release);
					}
			}
			}
    		

	

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

}
