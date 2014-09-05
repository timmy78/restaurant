package com.exercice.tprestaurant.ui.account;



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
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;

import com.exercice.tprestaurant.Constant;
import com.exercice.tprestaurant.R;
import com.exercice.tprestaurant.backend.status.StatusConnexion;
import com.exercice.tprestaurant.backend.status.StatusUserInfo;
import com.exercice.tprestaurant.ui.maintabs.MainTabsActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class AccountFragment extends Fragment {

	private Activity mActivity;
	private EditText genre, prenom, nom, tel, mobile, date, email, password, passwordConf;
	private RadioButton genreF,genreH;
	
	
	@Override
	public void onAttach(Activity activity) {
		mActivity = activity;
		super.onAttach(activity);
	}
	
	public AccountFragment() {
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
		
		return inflater.inflate(R.layout.account_fragment, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
				
		if(Network.isNetworkAvailable(mActivity)) {	
		    if(Preferences.getUser(mActivity) != null ){
				new AsyncCallWSConnection().execute();
	        }		
		} else {
			FastDialog.showDialog(mActivity,
					FastDialog.SIMPLE_DIALOG,
					getString(R.string.popup_erreur_connexion));
		}
		
		super.onActivityCreated(savedInstanceState);
	}
	
	private class AsyncCallWSConnection extends AsyncTask<String, Void, StatusUserInfo> {

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
		protected StatusUserInfo doInBackground(String... params) {
			Log.e("test", "lalalal");
			try {
				StatusUserInfo statusUserInfo = new StatusUserInfo();

				GsonBuilder builder = new GsonBuilder();
				Gson gson = builder.create();
				HttpResponse streamData = Network.getData(Constant.URL_WS_APP_CLIENT_INFO + Preferences.getInformations(mActivity).id + "/" + Preferences.getUser(mActivity).getId());
				if (streamData.getStatusLine().getStatusCode() == 200) {
					Reader reader = new InputStreamReader(streamData.getEntity().getContent());

					// recuperation de l’objet bindé
					statusUserInfo = gson.fromJson(reader, StatusUserInfo.class);
				} else {
					statusUserInfo.setStatus("error");	
				}

				return statusUserInfo;
				
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
		protected void onPostExecute(StatusUserInfo result) {

			if(monDialog != null){
				try {
					monDialog.cancel();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}

			if(mActivity != null) {
				if(result != null && result.getStatus().equalsIgnoreCase("ok")) {

					// Preference
					Preferences.setUser(mActivity, result.getUser());
					
					RadioButton genreH = (RadioButton) mActivity.findViewById(R.id.radioButtonCiviliteH);	
					RadioButton genreF = (RadioButton) mActivity.findViewById(R.id.radioButtonCiviliteF);	
					prenom = (EditText) mActivity.findViewById(R.id.editTextFirstname);
					nom = (EditText) mActivity.findViewById(R.id.editTextLastname);
					tel = (EditText) mActivity.findViewById(R.id.editTextTel);
					mobile = (EditText) mActivity.findViewById(R.id.editTextMobile);
					date = (EditText) mActivity.findViewById(R.id.editTextDateNaissance);
					email = (EditText) mActivity.findViewById(R.id.editTextEmail);
					password = (EditText) mActivity.findViewById(R.id.editTextPassword);
					passwordConf = (EditText) mActivity.findViewById(R.id.editTextPasswordConfirmation);

					if(result.getUser().civilite.toString().equalsIgnoreCase("0")){
						genreH.setChecked(true);
					}else{
						genreF.setChecked(true);
					}
					prenom.setText(result.getUser().prenom);
					nom.setText(result.getUser().nom);
					tel.setText(result.getUser().tel);
					mobile.setText(result.getUser().mobile);
					date.setText(result.getUser().date_anniversaire);
					email.setText(result.getUser().email);
					password.setText(result.getUser().password);
						
										
				} else {
					FastDialog.showDialog(mActivity,
							FastDialog.SIMPLE_DIALOG,
							"Erreur recup�ration url");	
					
				}
			}            
		}
	}
}
