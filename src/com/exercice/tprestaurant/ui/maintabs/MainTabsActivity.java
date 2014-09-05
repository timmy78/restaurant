package com.exercice.tprestaurant.ui.maintabs;

import utils.FastDialog;
import utils.Preferences;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.exercice.tprestaurant.AppActivity;
import com.exercice.tprestaurant.R;
import com.exercice.tprestaurant.backend.data.Commercant;
import com.exercice.tprestaurant.ui.account.ConnectionFragment;
import com.exercice.tprestaurant.ui.account.PasswordRecoveryFragment;
import com.exercice.tprestaurant.ui.account.RegisterFragment;
import com.exercice.tprestaurant.ui.bonplan.BonplanFragment;
import com.exercice.tprestaurant.ui.gallery.GalleryFragment;
import com.exercice.tprestaurant.ui.presentation.PresentationFragment;
import com.exercice.tprestaurant.ui.splashscreen.SplashScreenActivity;
import com.exercice.tprestaurant.ui.webpage.WebPageFragment;

public class MainTabsActivity extends AppActivity {

	private Commercant cm;

	public static View tabHome;
	View tabGallery;
	View tabBonPlan;
	View tabFacebook;
	View tabTwitter;
	public static View tabAccount;
	public static View tabAccountInformations;
	public static View tabAccountPasswordRecovery;

	ImageView imgHome;
	ImageView imgGallery;
	ImageView imgBonPlan;
	ImageView imgFacebook;
	ImageView imgWeb;
	ImageView imgAccount;

	private View tabToogle;
	private Context mContext;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.maintabs);

		try{
			cm = Preferences.getInformations(this);

			tabHome = findViewById(R.id.mt_lt_home);
			tabGallery = findViewById(R.id.mt_lt_gallery);
			tabBonPlan = findViewById(R.id.mt_lt_bp);
			tabFacebook = findViewById(R.id.mt_lt_facebook);
			tabTwitter = findViewById(R.id.mt_lt_twitter);
			tabAccount = findViewById(R.id.mt_lt_account);
			tabAccountInformations = findViewById(R.id.mt_lt_account_informations);
			tabAccountPasswordRecovery = findViewById(R.id.mt_lt_account_password_recovery);

			imgHome = (ImageView) findViewById(R.id.mt_img_home);
			imgGallery = (ImageView) findViewById(R.id.mt_img_gallery);
			imgBonPlan = (ImageView) findViewById(R.id.mt_img_bp);
			imgFacebook = (ImageView) findViewById(R.id.mt_img_facebook);
			imgWeb = (ImageView) findViewById(R.id.mt_img_twitter);
			imgAccount = (ImageView) findViewById(R.id.mt_img_account);

			mContext = this;

			tabHome.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// tabHost.setCurrentTab(2);
					if (tabToogle != v) {
						toogleTab(v);
						imgHome.setImageResource(R.drawable.menu_bas_icone_1accueil_active);
						switchContent(new PresentationFragment(), null);

						// getActionBar
					}

				}
			});
			tabGallery.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if(Preferences.getInformations(mContext) != null && Preferences.getInformations(mContext).photos != null && Preferences.getInformations(mContext).photos.size() > 0) {
						toogleTab(v);
						imgGallery.setImageResource(R.drawable.menu_bas_icone_2galerie_active);
						switchContent(new GalleryFragment(), null);

						// getActionBar
					} else {
						FastDialog.showDialog(mContext, FastDialog.SIMPLE_DIALOG, getString(R.string.popup_erreur_gallery));
					}

				}
			});
			tabBonPlan.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// tabHost.setCurrentTab(2);
					toogleTab(v);
					imgBonPlan.setImageResource(R.drawable.menu_bas_icone_3bonplan_active);
					switchContent(new BonplanFragment(), null);

					// getActionBar
				}
			});
			

			tabFacebook.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if (cm.facebook != null && cm.facebook.toString().length() > 0) {
						toogleTab(v);
						imgFacebook.setImageResource(R.drawable.menu_bas_icone_5facebook_active);
						switchContent(new WebPageFragment(), cm.facebook);

						// getActionBar
					} else {
						FastDialog.showDialog(mContext, FastDialog.SIMPLE_DIALOG, getString(R.string.popup_erreur_no_facebook));
					}


				}
			});
			tabTwitter.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if (cm.twitter != null && cm.twitter.toString().length() > 0) {
						toogleTab(v);
						imgWeb.setImageResource(R.drawable.menu_bas_icone_6web_active);
						switchContent(new WebPageFragment(), cm.twitter);

						// getActionBar
					} else {
						FastDialog.showDialog(mContext, FastDialog.SIMPLE_DIALOG, getString(R.string.popup_erreur_no_twitter));
					}
				}
			});

			tabAccount.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// tabHost.setCurrentTab(2);
					toogleTab(v);
					imgAccount.setImageResource(R.drawable.menu_bas_icone_7moncompte_active);

					// getActionBar

					if(Preferences.getUser(getApplicationContext()) != null && Preferences.getUser(getApplicationContext()).id != 0) {
						switchContent(new RegisterFragment(), null);
					} else {
						switchContent(new ConnectionFragment(), null);	
					}
				}
			});

			tabAccountInformations.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if(tabAccount.getTag() != null) {
						if(tabAccount.getTag().toString().equalsIgnoreCase("rdv")) {
							tabAccount.setTag(null);
							//switchContent(new RDVEtape1Fragment(), null);
						} else if(tabAccount.getTag().toString().equalsIgnoreCase("create")) {
							tabAccount.setTag(null);
							//switchContent(new RDVEtape1Fragment(), null);
							switchContent(new RegisterFragment(), null);
						}
					} else {
						switchContent(new PresentationFragment(), null);	
					}

					// getActionBar
				}
			});

			tabAccountPasswordRecovery.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					switchContent(new PasswordRecoveryFragment(), null);

					// getActionBar
				}
			});

			tabHome.performClick();

		} catch (Exception e) {
			Preferences.setInformations(getApplicationContext(), null); // delete Commercant
			Preferences.setUser(getApplicationContext(), null); // delete User
			Preferences.setUserToken(getApplicationContext(), null); // delete UserToken
			
			startActivity(new Intent(getApplicationContext(), SplashScreenActivity.class));
			
			finish();
		}
	}

	// toogle the tab and reset the other
	void toogleTab(View tab) {
		imgHome.setImageResource(R.drawable.menu_bas_icone_1accueil_on);
		imgGallery.setImageResource(R.drawable.menu_bas_icone_2galerie_on);
		imgBonPlan.setImageResource(R.drawable.menu_bas_icone_3bonplan_on);
		imgFacebook.setImageResource(R.drawable.menu_bas_icone_5facebook_on);
		imgWeb.setImageResource(R.drawable.menu_bas_icone_6web_on);
		imgAccount.setImageResource(R.drawable.menu_bas_icone_7moncompte_on);
	}

	public void switchContent(Fragment fragment, String URL) {
		if (URL != null) {
			Bundle bundle = new Bundle();
			bundle.putString("LOAD_URL", URL);
			fragment.setArguments(bundle);
		}
		try {
			getSupportFragmentManager().beginTransaction().replace(R.id.mt_content, fragment).commit();
		} catch (IllegalStateException i) {
			i.printStackTrace();
		}
	}
}