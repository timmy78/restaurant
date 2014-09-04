package com.exercice.tprestaurant.ui.presentation;


import utils.Preferences;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.exercice.tprestaurant.R;
import com.exercice.tprestaurant.backend.data.Commercant;
import com.squareup.picasso.Picasso;

public class PresentationFragment extends Fragment {

	private ImageView viewImage;
	private TextView viewText;
	private Commercant commercant;

	private Activity mActivity;
	
	@Override
	public void onAttach(Activity activity) {
		mActivity = activity;
		super.onAttach(activity);
	}
	
	public PresentationFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.presentation_fragment, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		this.viewImage = (ImageView) mActivity.findViewById(R.id.presentation_img);
		this.viewText = (TextView) mActivity.findViewById(R.id.presentation_text);
		this.commercant = Preferences.getInformations(mActivity);
		
		this.viewText.setText(this.commercant.presentationContenu);
		Picasso.with(mActivity).load(this.commercant.photos.get(0).getFileImg()).into(this.viewImage);
		
	}


}
