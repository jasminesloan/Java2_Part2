package com.jasminesloan.tamarbraxton;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.jasminesloan.tamarbraxton.library.JSON;


public class ActivityTwo extends Activity{
	
	String song;
	Context _context;
	JSON json;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Intent intent = getIntent();
        
        String album = intent.getStringExtra("collectionName");
        song = intent.getStringExtra("trackName");
        String track = intent.getStringExtra("trackNumber");
        String country = intent.getStringExtra("country");
        String genre = intent.getStringExtra("primaryGenreName");
        String release = intent.getStringExtra("releaseDate");
        
        this.setContentView(R.layout.activty_two);
        
        TextView textview1 = (TextView)findViewById(R.id.header1);
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
        textviewFive.setTypeface(null, Typeface.ITALIC);
        
        
        Button webButton = (Button) findViewById(R.id.webButton);
        webButton.setOnClickListener(new OnClickListener() {
    		
    		@Override
    		public void onClick(View v) {
    			// TODO Auto-generated method stub
    			Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.tamarbraxton.com/"));
                startActivity(browser);
    		}
    	});
 
}
	
	public void finish(){
		Intent info = new Intent();
		info.putExtra("trackName", song);
		setResult(RESULT_OK, info);
		super.finish();
	}
}
