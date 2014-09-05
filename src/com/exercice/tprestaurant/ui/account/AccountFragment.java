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
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.RadioButton;

import com.exercice.tprestaurant.Constant;
import com.exercice.tprestaurant.R;
import com.exercice.tprestaurant.backend.data.User;
import com.exercice.tprestaurant.backend.status.StatusConnexion;
import com.exercice.tprestaurant.backend.status.StatusUserInfo;
import com.exercice.tprestaurant.backend.status.StatusUserRegister;
import com.exercice.tprestaurant.ui.maintabs.MainTabsActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class AccountFragment extends Fragment {

	private Activity mActivity;
	private EditText prenom, nom, tel, mobile, date, email, password, passwordConf;
	private RadioButton genreF,genreH;
	private String genre = "1";
	
	
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
				
		/*
		 * UPDATE
		 */
		mActivity.findViewById(R.id.bt_connect).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				//Set
				RadioButton genreH = (RadioButton) mActivity.findViewById(R.id.radioButtonCiviliteH);
				if(genreH.isChecked()) {
					genre = "0";
				}
				prenom = (EditText) mActivity.findViewById(R.id.editTextFirstname);
				nom = (EditText) mActivity.findViewById(R.id.editTextLastname);
				tel = (EditText) mActivity.findViewById(R.id.editTextTel);
				mobile = (EditText) mActivity.findViewById(R.id.editTextMobile);
				date = (EditText) mActivity.findViewById(R.id.editTextDateNaissance);
				email = (EditText) mActivity.findViewById(R.id.editTextEmail);
				password = (EditText) mActivity.findViewById(R.id.editTextPassword);
				passwordConf = (EditText) mActivity.findViewById(R.id.editTextPasswordConfirmation);
				if(Network.isNetworkAvailable(mActivity)) {
					//Champs obligatoires
					if(!prenom.getText().toString().isEmpty() && !nom.getText().toString().isEmpty() && !date.getText().toString().isEmpty() 
							&& !email.getText().toString().isEmpty() && !password.getText().toString().isEmpty() && !passwordConf.getText().toString().isEmpty()) {
						if(passwordConf.getText().toString().equals(password.getText().toString())) {
							new AsyncCallWSUpdate().execute(
									genre,
									prenom.getText().toString(),
									nom.getText().toString(),
									tel.getText().toString(),
									mobile.getText().toString(),
									date.getText().toString(),
									email.getText().toString(),
									password.getText().toString()
							);
						}
						else  {
							FastDialog.showDialog(mActivity,
									FastDialog.SIMPLE_DIALOG,
									"Mots de passe non identiques");
						}
					}
					else {
						FastDialog.showDialog(mActivity,
								FastDialog.SIMPLE_DIALOG,
								"Certains champs obligatoires sont vides");
					}
					
				} else {
					FastDialog.showDialog(mActivity,
							FastDialog.SIMPLE_DIALOG,
							getString(R.string.popup_erreur_connexion));
				}
			}
		});
		
		/*
		 * AFFICHAGE DES INFOS
		 */
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
	
	/*
	 * ECUPERATION INFOS POUR AFFICHAGE
	 */
	private class AsyncCallWSConnection extends AsyncTask<String, Void, StatusUserInfo> 
	{

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
				HttpResponse streamData = Network.getData(Constant.URL_WS_APP_CLIENT_INFO + Preferences.getInformations(mActivity).id + "/" + Preferences.getUser(mActivity).id);
				if (streamData.getStatusLine().getStatusCode() == 200) {
					Reader reader = new InputStreamReader(streamData.getEntity().getContent());

					// recuperation de lâ€™objet bindÃ©
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
							"Erreur recupération url");	
					
				}
			}            
		}
	}
	
	/*
	 * UPDATE
	 */
	private class AsyncCallWSUpdate extends AsyncTask<String, Void, StatusUserRegister> 
	{

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
		/**
		 * Params :
		 * 0 => 
		 */
		protected StatusUserRegister doInBackground(String... params) {

			/**
			 *  0 => civilite
			 * 	1 => prenom,
				2 => nom,
				3 => tel,
				4 => mobile,
				5 => date,
				6 => email,
				7 => password
			 */
			Log.e("where", "doInBackground");
			if(params[0].length() > 0 && params[1].length() > 0 && params[2].length() > 0) {
				Log.e("where", "if() true");
				try {
					StatusUserRegister statusUserRegister = new StatusUserRegister();

					GsonBuilder builder = new GsonBuilder();
					Gson gson = builder.create();

					List<NameValuePair> data = new ArrayList<NameValuePair>();
					data.add(new BasicNameValuePair("civilite", params[0]));
					data.add(new BasicNameValuePair("prenom", params[1]));
					data.add(new BasicNameValuePair("nom", params[2]));
					data.add(new BasicNameValuePair("tel", params[3]));
					data.add(new BasicNameValuePair("mobile", params[4]));
					data.add(new BasicNameValuePair("date_anniversaire", params[5]));
					data.add(new BasicNameValuePair("email", params[6]));
					data.add(new BasicNameValuePair("password", params[7]));
					
					
					HttpResponse streamData = Network.postData(Constant.URL_WS_APP_CLIENT_INSERT+"/"+Preferences.getUser(mActivity).id, data);
					Log.e("HttpResponse code", String.valueOf(streamData.getStatusLine().getStatusCode()));
					//HttpResponse streamData = Network.getData(Constant.URL_WS_APP_CLIENT_INSERT);

					if(mActivity != null) {
						Log.e("where", "mActivity != null");
						if (streamData.getStatusLine().getStatusCode() == 200) {
							Log.e("where", "code 200");
							Reader reader = new InputStreamReader(streamData.getEntity().getContent());

							// recuperation de lâ€™objet bindÃ©
							statusUserRegister = gson.fromJson(reader, StatusUserRegister.class);

							//Log.e("load","ok 200 "+statusUserConnexion.getStatus()+" "+Constant.URL_WS_APP_CLIENT_LOGIN+params[0]+"/"+params[1]+"/"+params[2]);

							if(statusUserRegister.getStatus().equalsIgnoreCase("ok")) {

								User user = statusUserRegister.getUser();
								user.password = params[1];

								//Log.e("password", user.getPassword());

								// Preferences
								Preferences.setUser(mActivity, user);
								//Preferences.setUserToken(mActivity, statusUserRegister.getToken());
							} else {
								Preferences.setUser(mActivity, null); // delete User
								//Preferences.setUserToken(mActivity, null); // delete UserToken
							}

							return statusUserRegister;
						}
						Log.e("where", "code != 200");
					}
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			Log.e("where", "return null");
			return null;
		}
		
		@Override
		protected void onPostExecute(StatusUserRegister result) {

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
						MainTabsActivity.tabAccount.performClick();
					} else {
						if(result.getCode() == "102") {
							FastDialog.showDialog(mActivity,
									FastDialog.SIMPLE_DIALOG,
									"Email déjà existant");
						}
						else {
						FastDialog.showDialog(mActivity,
								FastDialog.SIMPLE_DIALOG,
								getString(R.string.popup_error_register));
						}
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
