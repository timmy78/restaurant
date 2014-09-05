package com.exercice.tprestaurant.ui.splashscreen;

import android.os.Bundle;

import com.exercice.tprestaurant.AppActivity;
import com.exercice.tprestaurant.R;

public class SplashScreenActivity extends AppActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splashscreen);

		if (savedInstanceState == null) {
			SplashScreenFragment fragment = new SplashScreenFragment();
			getFragmentManager().beginTransaction()
			.add(R.id.splashscreen_container, fragment).commit();
		}
	}
}
