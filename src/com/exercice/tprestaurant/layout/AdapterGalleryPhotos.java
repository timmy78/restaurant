package com.exercice.tprestaurant.layout;

import java.io.Console;
import java.util.List;

import utils.FastDialog;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.MediaStore.Files;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.exercice.tprestaurant.Constant;
import com.exercice.tprestaurant.R;
import com.exercice.tprestaurant.backend.data.Photos;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class AdapterGalleryPhotos extends ArrayAdapter<Photos>  {
	/** The parent context */
	private Photos item;
	private LayoutInflater mInflater;
	private DisplayImageOptions options;
	protected ImageLoader imageLoader;
	private Context c;

	/** Simple Constructor saving the 'parent' context. */
	public AdapterGalleryPhotos(Context c, int textViewResourceId, List<Photos> objects) {
		
		super(c, textViewResourceId, objects);

		mInflater = LayoutInflater.from(c);
		this.c = c;
	}

	// Returns a new ImageView to be displayed,
	public View getView(int position, View convertView, ViewGroup parent) {

		 

		return convertView;
	}

	static class ViewHolder {
		ImageView imageView;
	}
}