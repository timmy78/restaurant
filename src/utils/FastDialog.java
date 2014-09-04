package utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;

import com.exercice.tprestaurant.R;

public class FastDialog {

	public static final int PROGRESS_DIALOG = 0;
	public static final int SIMPLE_DIALOG = 1;

	public static void showDialog(Context c, int typeDialog, String message) {
		if (c != null) {
			ProgressDialog progressDialog;
			switch (typeDialog) {
			case PROGRESS_DIALOG:
				// Looper.prepare();
				progressDialog = new ProgressDialog(c);
				progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
				progressDialog.setMessage(message);

				// progressThread = new ProgressThread(handler);
				// progressThread.start();
				// Looper.loop();
				// Looper.myLooper().quit();
				// return progressDialog;
			case SIMPLE_DIALOG:


				AlertDialog.Builder builder = new AlertDialog.Builder(c);
				// 2. Chain together various setter methods to set the dialog
				// characteristics
				builder.setMessage(message);
				builder.setCancelable(false);

				// Add the buttons
				builder.setPositiveButton(R.string.ok, null);

				// Create the AlertDialog
				builder.create().show();

				// return dialog;

			default:
				// return null;
			}
		}
		// return null;
	}

	public static void showDialog(Context c, int typeDialog, String message,
			OnClickListener callbk) {
		if (c != null) {
			ProgressDialog progressDialog;
			switch (typeDialog) {
			case PROGRESS_DIALOG:
				// Looper.prepare();
				progressDialog = new ProgressDialog(c);
				progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
				progressDialog.setMessage(message);

				// progressThread = new ProgressThread(handler);
				// progressThread.start();
				// Looper.loop();
				// Looper.myLooper().quit();
				// return progressDialog;
			case SIMPLE_DIALOG:
				AlertDialog.Builder builder = new AlertDialog.Builder(c);
				// 2. Chain together various setter methods to set the dialog
				// characteristics
				builder.setMessage(message);

				// Add the buttons
				builder.setPositiveButton(R.string.ok, callbk);
				builder.setCancelable(false);

				// Create the AlertDialog
				builder.create().show();
				// return dialog;

			default:
				// return null;
			}
		}
		// return null;
	}

	public static Dialog showProgressDialog(Context c, String message) {
		ProgressDialog progressDialog = new ProgressDialog(c);
		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progressDialog.setMessage(message);
		progressDialog.setCancelable(false);
		return progressDialog;
	}
}
