package com.exercice.tprestaurant.ui.gallery;

import utils.Preferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Gallery;

import com.exercice.tprestaurant.R;
import com.exercice.tprestaurant.layout.AdapterGalleryPhotos;

public class GalleryFragment extends Fragment {

	public GalleryFragment() {
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

		return inflater.inflate(R.layout.gallery_fragment, container, false);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		Gallery gallery_photos = (Gallery) getActivity().findViewById(R.id.gallery_photos);

		if(getActivity() != null) {
			AdapterGalleryPhotos mAdapter=new AdapterGalleryPhotos(getActivity(), R.layout.listphotos_row, Preferences.getInformations(getActivity()).getPhotos());
			gallery_photos.setAdapter(mAdapter);
			
			// TODO 
			// Il reste à terminer la classe AdapterGalleryPhotos
		}
		super.onActivityCreated(savedInstanceState);
	}
}
