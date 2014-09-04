package com.exercice.tprestaurant.ui.about;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exercice.tprestaurant.R;

public class About1Fragment extends Fragment{

	@Override 
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View rootView= inflater.inflate(R.layout.about1_fragment, container, false);

		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {


		super.onActivityCreated(savedInstanceState);
	}

}