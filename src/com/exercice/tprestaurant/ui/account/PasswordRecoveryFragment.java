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

import utils.FastDialog;
import utils.Network;
import utils.Preferences;
import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.EditText;

import com.exercice.tprestaurant.Constant;
import com.exercice.tprestaurant.R;
import com.exercice.tprestaurant.backend.status.StatusPasswordRecovery;
import com.exercice.tprestaurant.ui.maintabs.MainTabsActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class PasswordRecoveryFragment extends Fragment {

	EditText email;
	boolean error;

	public PasswordRecoveryFragment() {
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
		return inflater.inflate(R.layout.password_recovery_fragment, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		if(getActivity() != null) {
				
			email = (EditText) getActivity().findViewById(R.id.editTextEmail);
			
			getActivity().findViewById(R.id.bt_connect).setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					error = false; 
					
					if(Network.isNetworkAvailable(getActivity())) {
						
						if (error == false && (email.getText().toString() == null || "".equals(email.getText().toString()) || (email.getText().toString() != null && (email.getText().toString().contains(" ") == true || email.getText().toString().matches(".+@.+\\.[a-z]+") == false)))) {
							FastDialog.showDialog(getActivity(),FastDialog.SIMPLE_DIALOG,getString(R.string.popup_error_email_empty));

							error = true;
						}
						
						if(error == false) {
							new AsyncCallWSPasswordRecovery().execute(email.getText().toString());	
						}
					} else {
						FastDialog.showDialog(getActivity(),
								FastDialog.SIMPLE_DIALOG,
								getString(R.string.popup_erreur_connexion));
					}
				}
			});
		}

		super.onActivityCreated(savedInstanceState);
	}

	private class AsyncCallWSPasswordRecovery extends AsyncTask<String, Void, StatusPasswordRecovery> {

		private Dialog monDialog;

		@Override
		protected void onPreExecute() {
			if(getActivity() != null) {
				monDialog = FastDialog.showProgressDialog(getActivity(),getString(R.string.popup_info_wait));
				try {
					monDialog.show();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		@Override
		protected StatusPasswordRecovery doInBackground(String... params) {
			StatusPasswordRecovery statusPasswordRecevory = new StatusPasswordRecovery();
			
			if(params[0].length() > 0) {
				try {
					GsonBuilder builder = new GsonBuilder();
					Gson gson = builder.create();
					
					HttpResponse streamData = Network.getData(Constant.URL_WS_APP_CLIENT_PASSWORD_RECOVERY+params[0]);

					if (streamData.getStatusLine().getStatusCode() == 200) {
						Reader reader = new InputStreamReader(streamData.getEntity().getContent());

						// recuperation de l’objet bindé
						statusPasswordRecevory = gson.fromJson(reader, StatusPasswordRecovery.class);

						return statusPasswordRecevory;
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
		protected void onPostExecute(StatusPasswordRecovery result) {

			if(monDialog != null){
				try {
					monDialog.cancel();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}

			if(getActivity() != null) {

				if(result != null) {
					FastDialog.showDialog(getActivity(),
							FastDialog.SIMPLE_DIALOG,
							result.getMessage());
					
					MainTabsActivity.tabAccount.performClick();
				} else {
					FastDialog.showDialog(getActivity(),
							FastDialog.SIMPLE_DIALOG,
							getString(R.string.popup_erreur_ws));
				}

			}            
		}
	}
}
