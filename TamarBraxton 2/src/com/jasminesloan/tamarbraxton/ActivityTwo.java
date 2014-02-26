package com.jasminesloan.tamarbraxton;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import com.jasminesloan.tamarbraxton.library.JSON;


public class ActivityTwo extends Activity{
	
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Intent intent = getIntent();
        
        album = intent.getStringExtra("collectionName");
        song = intent.getStringExtra("trackName");
        track = intent.getStringExtra("trackNumber");
        country = intent.getStringExtra("country");
        genre = intent.getStringExtra("primaryGenreName");
        release = intent.getStringExtra("releaseDate");

        this.setContentView(R.layout.fragment_two);
        
        FragmentTwo fragment = (FragmentTwo) getFragmentManager().findFragmentById(R.id.fragment2);
        fragment.displayResults(song, album, track, country, genre, release);

}
	
    public void displayResults(String songTitle, String albumName, String songNumber, String country, String genre, String release){
    	
  /**  	TextView textview1 = (TextView)findViewById(R.id.header1);
        textview1.setText("Song Title = "  + song);
        textview1.setTypeface(null, Typeface.BOLD);
        
        TextView textview2 = (TextView)findViewById(R.id.header2);
        textview2.setText("Album = "  + album);
        textview2.setTypeface(null, Typeface.ITALIC);
        
        TextView textview = (TextView)findViewById(R.id.headerOne);
        textview.setText("Track = "  + track);
        textview.setTypeface(null, Typeface.ITALIC);

        TextView textviewThree = (TextView)findViewById(R.id.headerThree);
        textviewThree.setText("Country = "  + country);
        textviewThree.setTypeface(null, Typeface.ITALIC);
        
        TextView textviewFour = (TextView)findViewById(R.id.headerFour);
        textviewFour.setText("Genre = "  + genre);
        textviewFour.setTypeface(null, Typeface.ITALIC);
        
        TextView textviewFive = (TextView)findViewById(R.id.headerFive);
        textviewFive.setText("Release Date = "  + release);
        textviewFive.setTypeface(null, Typeface.ITALIC);**/
    	
    }
	
	public void finish(){
		Intent info = new Intent();
		info.putExtra("trackName", song);
		setResult(RESULT_OK, info);
		super.finish();
	}
}

