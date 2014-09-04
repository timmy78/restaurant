package com.exercice.tprestaurant.ui.splashscreen;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;

import utils.FastDialog;
import utils.Network;
import utils.Preferences;
import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exercice.tprestaurant.Constant;
import com.exercice.tprestaurant.R;
import com.exercice.tprestaurant.backend.status.StatusConnexion;
import com.exercice.tprestaurant.ui.maintabs.MainTabsActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SplashScreenFragment extends Fragment{

	private Activity mActivty; // Context
	
	@Override
	public void onAttach(Activity activity) {
		mActivty = activity;
		super.onAttach(activity);
	}

	@Override 
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View rootView= inflater.inflate(R.layout.splashscreen_fragment, container, false);

		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		new AsyncCallWSConnection().execute();

		super.onActivityCreated(savedInstanceState);
	}
	
	private class AsyncCallWSConnection extends AsyncTask<String, Void, StatusConnexion> {

		private Dialog monDialog;

		@Override
		protected void onPreExecute() {
			if(mActivty != null) {
				monDialog = FastDialog.showProgressDialog(mActivty,getString(R.string.popup_info_wait));
				try {
					monDialog.show();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		@Override
		protected StatusConnexion doInBackground(String... params) {

			try {
				StatusConnexion statusConnexion = new StatusConnexion();

				GsonBuilder builder = new GsonBuilder();
				Gson gson = builder.create();

				HttpResponse streamData = Network.getData(Constant.URL_WS_APP_CONNEXION_COMMERCANT);
				if (streamData.getStatusLine().getStatusCode() == 200) {
					Reader reader = new InputStreamReader(streamData.getEntity().getContent());

					// recuperation de l’objet bindé
					statusConnexion = gson.fromJson(reader, StatusConnexion.class);

					// Preferences
					Preferences.setInformations(mActivty, statusConnexion.getCommercant());
				} else {
					statusConnexion.setStatus("error");	
				}

				return statusConnexion;
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return null;
		}

		@Override
		protected void onPostExecute(StatusConnexion result) {

			if(monDialog != null){
				try {
					monDialog.cancel();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}

			if(mActivty != null) {
				if(result != null && result.getStatus().equalsIgnoreCase("ok")) {

					// Preference
					Preferences.setCommercantAccessList(mActivty, result.getCommercant());

					startActivity(new Intent(mActivty, MainTabsActivity.class));
				} else {

					if(result != null && result.getCode().equalsIgnoreCase("510")) {
						FastDialog.showDialog(mActivty,
								FastDialog.SIMPLE_DIALOG,
								result.getMessage());
					} else {
						FastDialog.showDialog(mActivty,
								FastDialog.SIMPLE_DIALOG,
								getString(R.string.popup_erreur_ws));	
					}
				}
			}            
		}
	}
}