package com.jasminesloan.tamarbraxton;

import com.jasminesloan.tamarbraxton.library.JSON;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class FragmentTwo extends Fragment implements OnClickListener{

	TextView header1, header2, headerOne, headerThree, headerFour, headerFive;
	

	String song;
	String album;
	String track;
	String country;
	String genre;
	String release;
	Context _context;
	JSON json;
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.activty_two, container);
		// TODO Auto-generated method stub
		TextView textview1 = (TextView)view.findViewById(R.id.header1);
        textview1.setText("Song Title = "  + song);
        textview1.setTypeface(null, Typeface.BOLD);
        
        TextView textview2 = (TextView)view.findViewById(R.id.header2);
        textview2.setText("Album = "  + album);
        textview2.setTypeface(null, Typeface.ITALIC);
        
        TextView textview = (TextView)view.findViewById(R.id.headerOne);
        textview.setText("Track = "  + track);
        textview.setTypeface(null, Typeface.ITALIC);

        TextView textviewThree = (TextView)view.findViewById(R.id.headerThree);
        textviewThree.setText("Country = "  + country);
        textviewThree.setTypeface(null, Typeface.ITALIC);
        
        TextView textviewFour = (TextView)view.findViewById(R.id.headerFour);
        textviewFour.setText("Genre = "  + genre);
        textviewFour.setTypeface(null, Typeface.ITALIC);
        
        TextView textviewFive = (TextView)view.findViewById(R.id.headerFive);
        textviewFive.setText("Release Date = "  + release);
        textviewFive.setTypeface(null, Typeface.ITALIC);
		
        Button webButton = (Button) view.findViewById(R.id.webButton);
        webButton.setOnClickListener(this);
		
		return view;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.tamarbraxton.com/"));
        startActivity(browser);
	}
	
public void displayResults(String songTitle, String albumName, String songNumber, String country, String genre, String release){
    	
    	
    	
    }

}
