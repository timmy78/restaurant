package utils;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.util.Log;

import com.exercice.tprestaurant.Constant;
import com.exercice.tprestaurant.backend.data.Commercant;
import com.exercice.tprestaurant.backend.data.User;
import com.google.gson.Gson;

public final class Preferences {
	/**
	 * Get the library shared preferences.
	 * @param context
	 * @return
	 */
	public static SharedPreferences get(Context context) {
		//return context.getSharedPreferences("PREF", 0);
		return PreferenceManager.getDefaultSharedPreferences(context);
	}

	public static void setString(Context context, String key, String value) {
		Editor prefsEditor = get(context).edit();

		prefsEditor.putString(key, value);
		prefsEditor.commit();
	}

	public static void setBoolean(Context context, String key, boolean value) {
		Editor prefsEditor = get(context).edit();

		prefsEditor.putBoolean(key, value);
		prefsEditor.commit();
	}

	public static Commercant getInformations(Context context) {

		if(get(context) == null)
			return null;

		Gson gsonp = new Gson();
		String jsonp = get(context).getString(Constant.PREFERENCE_APP_CONNEXION, "");

		Log.e("jsonp", "getInformations: "+jsonp);

		return gsonp.fromJson(jsonp, Commercant.class);
	}

	public static void setInformations(Context context, Commercant object) {
		Editor prefsEditor = get(context).edit();

		if(object != null) {
			//Log.e("setCommercant", "id: "+object.id);

			Gson gsonp = new Gson();
			String jsonp = gsonp.toJson(object);
			prefsEditor.putString(Constant.PREFERENCE_APP_CONNEXION, jsonp);

			//Log.e("setInformations", "id: "+object.id+" "+jsonp);

		} else {
			prefsEditor.putString(Constant.PREFERENCE_APP_CONNEXION, "");
		}

		prefsEditor.commit();
	}

	public static List<Commercant> getCommercantAccessList(Context context) {

		if(get(context) == null)
			return null;

		Gson gsonp = new Gson();
		String jsonp = get(context).getString(Constant.PREFERENCE_APP_ACCESS, "");

		List<Commercant> commercant = new ArrayList<Commercant>();

		if(jsonp.length() > 0) {
			Commercant[] commercantArray = gsonp.fromJson(jsonp, Commercant[].class);

			if(commercantArray != null && commercantArray.length > 0) {
				for(int i=0;i<commercantArray.length;i++) {
					commercant.add(commercantArray[i]);
				}
			}
		}

		return commercant;
	}

	public static void setCommercantAccessList(Context context, Commercant object) {
		Editor prefsEditor = get(context).edit();

		List<Commercant> commercant = new ArrayList<Commercant>();

		if(getCommercantAccessList(context) != null) {
			//Log.e("setCommercantAccessList", "1: "+object.getEnseigne());

			boolean exist = false;

			for(int i=0;i<getCommercantAccessList(context).size();i++) {
				if(getCommercantAccessList(context).get(i).id.equalsIgnoreCase(object.id)) {
					exist = true;
				}
				
				commercant.add(getCommercantAccessList(context).get(i));

				if(getCommercantAccessList(context).get(i).id.equalsIgnoreCase(object.id) && getCommercantAccessList(context).get(i).tel.equalsIgnoreCase(object.tel) == false) {
					commercant.get(i).tel = object.tel;
				} else if(getCommercantAccessList(context).get(i).id.equalsIgnoreCase(object.id) && getCommercantAccessList(context).get(i).dateGeolocalisation != object.dateGeolocalisation) {
					commercant.get(i).dateGeolocalisation = object.dateGeolocalisation;
				}
			}

			if(exist == false) {
				commercant.add(object);	
			}
		} else {
			//Log.e("setCommercantAccessList", "2: "+object.getEnseigne());

			commercant.add(object);
		}

		if(commercant.size() > 0) {
			//Log.e("setCommercantAccessList", "3: "+object.getEnseigne());

			Gson gsonp = new Gson();
			String jsonp = gsonp.toJson(commercant);
			prefsEditor.putString(Constant.PREFERENCE_APP_ACCESS, jsonp);
			prefsEditor.commit();
		}
	}

	public static User getUser(Context context) {

		if(get(context) == null)
			return null;

		Gson gsonp = new Gson();
		String jsonp = get(context).getString(Constant.PREF_USER, "");

		//Log.e("getUser", ""+jsonp);

		return gsonp.fromJson(jsonp, User.class);
	}

	public static void setUser(Context context, User object) {
		Editor prefsEditor = get(context).edit();

		Gson gsonp = new Gson();
		String jsonp = gsonp.toJson(object);
		prefsEditor.putString(Constant.PREF_USER, jsonp);
		prefsEditor.commit();
	}

	public static void setUserToken(Context context, String value) {
		Editor prefsEditor = get(context).edit();

		prefsEditor.putString(Constant.PREFERENCE_APP_USER_TOKEN, value);
		prefsEditor.commit();
	}

	public static String getUserToken(Context context) {
		return get(context).getString(Constant.PREFERENCE_APP_USER_TOKEN, "");
	}



}
