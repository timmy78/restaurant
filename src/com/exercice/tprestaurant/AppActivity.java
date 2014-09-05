package com.exercice.tprestaurant;

import utils.FastDialog;
import utils.Network;
import utils.Preferences;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.exercice.tprestaurant.backend.data.User;
import com.exercice.tprestaurant.ui.about.AboutActivity;
import com.exercice.tprestaurant.ui.splashscreen.SplashScreenActivity;

public class AppActivity extends FragmentActivity {

	/**
	 * add the layout layoutID to the contentLayout of the main layout
	 * @param layoutId
	 */
	@Override
	public void setContentView(int layoutResID) {
		LinearLayout mainView = (LinearLayout) getLayoutInflater().inflate(R.layout.main, null);
		FrameLayout content = (FrameLayout) mainView.findViewById(R.id.content); 
		View view = getLayoutInflater().inflate(layoutResID, content, false);
		content.addView(view, 0);
		super.setContentView(mainView);
	}
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {

		switch (item.getItemId()) {

		case android.R.id.home:
			finish();
			break;

		case R.id.action_about:
			startActivity(new Intent(AppActivity.this, AboutActivity.class));
			break;

		case R.id.action_disconnect:

			Preferences.setInformations(AppActivity.this, null); // delete Informations
			Preferences.setUser(AppActivity.this, null); // delete User

			startActivity(new Intent(AppActivity.this, SplashScreenActivity.class));

			break;
		default:
			break;
		}

		return super.onMenuItemSelected(featureId, item);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch (item.getItemId()) {

		case android.R.id.home:
			finish();
			break;

		case R.id.action_about:
			startActivity(new Intent(AppActivity.this, AboutActivity.class));
			break;

		case R.id.action_disconnect:

			Preferences.setInformations(AppActivity.this, null); // delete Informations
			Preferences.setUser(AppActivity.this, null); // delete User

			startActivity(new Intent(AppActivity.this, SplashScreenActivity.class));

			break;
		default:
			break;
		}
		
		return super.onOptionsItemSelected(item);
	}
	

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        if(Preferences.getUser(this) != null ){
        	Log.e("dvvdvd","fefefe");        	
    		MenuItem disconnectMenuItem = (MenuItem) menu.findItem(R.id.action_disconnect);
    		disconnectMenuItem.setVisible(true);
        }
		
		
        
        return true;
    }

	
}
