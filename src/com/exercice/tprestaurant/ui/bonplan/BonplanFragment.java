package com.exercice.tprestaurant.ui.bonplan;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exercice.tprestaurant.R;
import com.exercice.tprestaurant.backend.data.Commercant;

public class BonplanFragment extends Fragment {
	
	private Commercant cm;
	
	public BonplanFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.bonplan_fragment, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {

		// TODO
		
		super.onViewCreated(view, savedInstanceState);
	}
	
}
