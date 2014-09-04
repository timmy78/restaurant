package com.exercice.tprestaurant.ui.about;

import android.app.Fragment;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.exercice.tprestaurant.AppActivity;
import com.exercice.tprestaurant.R;

public class AboutActivity extends AppActivity {

	Button buttonAbout1;
	Button buttonAbout2;

	private View tabToogle;

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		finish();
		return(true);

	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);

		// Action Bar
		getActionBar().setDisplayHomeAsUpEnabled(true);

		buttonAbout1 = (Button) findViewById(R.id.buttonAbout1);
		buttonAbout2 = (Button) findViewById(R.id.buttonAbout2);

		buttonAbout1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// tabHost.setCurrentTab(2);
				if (tabToogle != v) {
					toogleTab(v);
					buttonAbout1.setBackgroundResource(R.drawable.menu_haut_sousmenu_active);
					switchContent(new About1Fragment(), null);
				}
			}
		});

		buttonAbout2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// tabHost.setCurrentTab(2);
				if (tabToogle != v) {
					toogleTab(v);
					buttonAbout2.setBackgroundResource(R.drawable.menu_haut_sousmenu_active);
					switchContent(new About2Fragment(), null);
				}
			}
		});

		buttonAbout1.performClick();
	}

	// toogle the tab and reset the other
	void toogleTab(View tab) {;
		buttonAbout1.setBackgroundResource(R.drawable.menu_haut_sousmenu_on);
		buttonAbout2.setBackgroundResource(R.drawable.menu_haut_sousmenu_on);
	}

	public void switchContent(Fragment fragment, String URL) {
		getFragmentManager().beginTransaction().replace(R.id.mt_content, fragment).commit();
	}




}
