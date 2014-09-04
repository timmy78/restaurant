package com.exercice.tprestaurant.ui.bonplan;

import utils.Preferences;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.exercice.tprestaurant.R;
import com.exercice.tprestaurant.backend.data.Commercant;

public class BonplanFragment extends Fragment {
	
	private Activity mActivity;
	private Commercant cm;
	private TextView contenu;
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		this.mActivity = activity;
		super.onAttach(activity);
	}
	
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
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		this.cm = Preferences.getInformations(mActivity);
		this.contenu = (TextView) getActivity().findViewById(R.id.bp_text);
		
		this.contenu.setText(cm.getBonplanContenu());
		
		super.onActivityCreated(savedInstanceState);
	}
}
