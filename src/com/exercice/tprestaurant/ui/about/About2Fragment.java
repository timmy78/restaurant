package com.exercice.tprestaurant.ui.about;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exercice.tprestaurant.R;

public class About2Fragment extends Fragment{

	public About2Fragment() {
		// TODO Auto-generated constructor stub
	}
	
	@Override 
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View rootView= inflater.inflate(R.layout.about2_fragment, container, false);

		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {


		super.onActivityCreated(savedInstanceState);
	}

}