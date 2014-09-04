package com.exercice.tprestaurant;



public class Constant {
	//preference
	public static final String PREF_USER 		= "PREF_USER";
	
	// URL Web service
	public static final String URL_WS_APP_CONNEXION_COMMERCANT 		= "http://formationandroid.client.vivaneo.fr/app_dev.php/user/informations";
	
	/** add {email}/{password}/{commercant_id} **/
	public static final String URL_WS_APP_CLIENT_LOGIN 				= "http://formationandroid.client.vivaneo.fr/app_dev.php/user/client_login/";
	
	/** {commercant_id}/{client_id} **/
	public static final String URL_WS_APP_CLIENT_INFO 				= "http://formationandroid.client.vivaneo.fr/app_dev.php/user/client_info/";
	
	/** add {client_id} **/
	public static final String URL_WS_APP_CLIENT_UPDATE 			= "http://formationandroid.client.vivaneo.fr/app_dev.php/user/client_insert/";
	public static final String URL_WS_APP_CLIENT_INSERT 			= "http://formationandroid.client.vivaneo.fr/app_dev.php/user/client_insert";
	
	/** add {email} **/
	public static final String URL_WS_APP_CLIENT_PASSWORD_RECOVERY  = "http://formationandroid.client.vivaneo.fr/app_dev.php/user/client_password_recovery/";

	// Preferences
	public static final String PREFERENCE_APP_CONNEXION 	= "PREFERENCE_APP_CONNEXION";
	public static final String PREFERENCE_APP_ACCESS 		= "PREFERENCE_APP_ACCESS";
	public static final String PREFERENCE_APP_USER_TOKEN 	= "PREFERENCE_APP_USER_TOKEN";

}
