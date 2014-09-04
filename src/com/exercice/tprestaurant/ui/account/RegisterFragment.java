package com.exercice.tprestaurant.ui.account;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exercice.tprestaurant.R;

public class RegisterFragment extends Fragment {


	public RegisterFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		return inflater.inflate(R.layout.account_fragment, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		// TODO
		

		super.onActivityCreated(savedInstanceState);
	}


}
