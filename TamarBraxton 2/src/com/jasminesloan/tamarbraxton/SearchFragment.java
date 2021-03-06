package com.jasminesloan.tamarbraxton;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class SearchFragment extends DialogFragment{
	String searchString = "";
	
	public static SearchFragment newTnstance(){
		return new SearchFragment();
		
	}

	public static SearchFragment newInstance() {
		// TODO Auto-generated method stub
		return new SearchFragment();
	}
	private listenerSearch listener;
	public interface listenerSearch
	{
		public void searchSongs(String searchString);
		public void hideSoftKeyboard(Activity activity, View view);
	}
	
	@Override
	public void onAttach(Activity activity){
		super.onAttach(activity);
		
		try{
			listener = (listenerSearch)activity;
		} catch (ClassCastException e){
			throw new ClassCastException(activity.toString() + "Attaching dialog fragment failed");
		}
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		final View v = inflater.inflate(R.layout.search_dialog_fragment, container, false);
		
		Button searchButton = (Button)v.findViewById(R.id.searchButtonDialog);
		
		searchButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EditText searchField = (EditText) v.findViewById(R.id.searchField);
				
				listener.searchSongs(searchField.getText().toString());
				listener.hideSoftKeyboard(getActivity(), getView());
				
				dismiss();
			}
		});
		
		return v;
		
	}

}
