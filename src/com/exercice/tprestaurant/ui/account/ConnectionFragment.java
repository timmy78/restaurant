package com.exercice.tprestaurant.ui.account;



import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import utils.FastDialog;
import utils.Network;
import utils.Preferences;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.support.v4.app.Fragment;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;

import com.exercice.tprestaurant.Constant;
import com.exercice.tprestaurant.R;
import com.exercice.tprestaurant.backend.data.User;
import com.exercice.tprestaurant.backend.status.StatusUserConnexion;
import com.exercice.tprestaurant.ui.maintabs.MainTabsActivity;
import com.exercice.tprestaurant.ui.splashscreen.SplashScreenActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ConnectionFragment extends Fragment {

	EditText email, password;

	private Activity mActivity;
	
	@Override
	public void onAttach(Activity activity) {
		mActivity = activity;
		super.onAttach(activity);
	}
	
	public ConnectionFragment() {
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
		return inflater.inflate(R.layout.connection_fragment, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		if(mActivity != null) {

			email = (EditText) mActivity.findViewById(R.id.editTextEmail);
			password = (EditText) mActivity.findViewById(R.id.editTextPassword);

			mActivity.findViewById(R.id.connexion_text_inscription).setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					MainTabsActivity.tabAccount.setTag("create");
					MainTabsActivity.tabAccountInformations.performClick();
				}
			});

			mActivity.findViewById(R.id.connexion_text_password_recovery).setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					MainTabsActivity.tabAccountPasswordRecovery.performClick();
				}
			});

			mActivity.findViewById(R.id.bt_connect).setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					
					if(email.getText().toString().isEmpty()){
						FastDialog.showDialog(mActivity,
								FastDialog.SIMPLE_DIALOG,
								"Email doit être renseigné");
						return;
					}
					
					if(password.getText().toString().isEmpty()){
						FastDialog.showDialog(mActivity,
								FastDialog.SIMPLE_DIALOG,
								"Mot de passe Vide");
						return;
					}
					
					if(Network.isNetworkAvailable(mActivity)) {
						new AsyncCallWSConnection().execute(email.getText().toString(), password.getText().toString(), Preferences.getInformations(mActivity).id);
					} else {
						FastDialog.showDialog(mActivity,
								FastDialog.SIMPLE_DIALOG,
								getString(R.string.popup_erreur_connexion));
					}
				}
			});
		}

		super.onActivityCreated(savedInstanceState);
	}

	private class AsyncCallWSConnection extends AsyncTask<String, Void, StatusUserConnexion> {

		private Dialog monDialog;

		@Override
		protected void onPreExecute() {
			if(mActivity != null) {
				monDialog = FastDialog.showProgressDialog(mActivity,getString(R.string.popup_info_wait));
				try {
					monDialog.show();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		@Override
		protected StatusUserConnexion doInBackground(String... params) {

			if(params[0].length() > 0 && params[1].length() > 0 && params[2].length() > 0) {
				try {
					StatusUserConnexion statusUserConnexion = new StatusUserConnexion();

					GsonBuilder builder = new GsonBuilder();
					Gson gson = builder.create();

					//Log.e("connexion", Constant.URL_WS_APP_CLIENT_LOGIN+params[0]+"/"+params[1]+"/"+params[2]);

					HttpResponse streamData = Network.getData(Constant.URL_WS_APP_CLIENT_LOGIN+params[0]+"/"+params[1]+"/"+params[2]);

					if(mActivity != null) {
						if (streamData.getStatusLine().getStatusCode() == 200) {
							Reader reader = new InputStreamReader(streamData.getEntity().getContent());

							// recuperation de lâ€™objet bindÃ©
							statusUserConnexion = gson.fromJson(reader, StatusUserConnexion.class);

							//Log.e("load","ok 200 "+statusUserConnexion.getStatus()+" "+Constant.URL_WS_APP_CLIENT_LOGIN+params[0]+"/"+params[1]+"/"+params[2]);

							if(statusUserConnexion.getStatus().equalsIgnoreCase("ok")) {

								User user = statusUserConnexion.getUser();
								user.password = params[1];

								
								
								//Log.e("password", user.getPassword());

								// Preferences
								Preferences.setUser(mActivity, user);
								Preferences.setUserToken(mActivity, statusUserConnexion.getToken());
							} else {
								Preferences.setUser(mActivity, null); // delete User
								Preferences.setUserToken(mActivity, null); // delete UserToken
							}

							return statusUserConnexion;
						}
					}
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			return null;
		}

		@Override
		protected void onPostExecute(StatusUserConnexion result) {

			if(monDialog != null){
				try {
					monDialog.cancel();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}

			if(mActivity != null) {

				if(result != null) {
					if(result.getStatus().equalsIgnoreCase("ok")) {						
						//MainTabsActivity.tabAccountInformations.performClick();
						Intent intent = new Intent(mActivity,SplashScreenActivity.class);
						startActivity(intent);
						mActivity.finish();
					} else {
						FastDialog.showDialog(mActivity,
								FastDialog.SIMPLE_DIALOG,
								getString(R.string.popup_info_login_incorrect));
					}
				} else {
					FastDialog.showDialog(mActivity,
							FastDialog.SIMPLE_DIALOG,
							getString(R.string.popup_erreur_ws));
				}

			}            
		}
	}
}
