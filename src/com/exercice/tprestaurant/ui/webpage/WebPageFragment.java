package com.exercice.tprestaurant.ui.webpage;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.exercice.tprestaurant.R;

@SuppressLint("SetJavaScriptEnabled")
public class WebPageFragment extends Fragment {
	
	private String url;
	private ProgressBar progressBar;
	
	public WebPageFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.webpage_fragment, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		progressBar = (ProgressBar) getActivity().findViewById(R.id.webLoad);
		progressBar.getProgressDrawable().setColorFilter(Color.GRAY, Mode.SRC_IN);
        progressBar.setMax(100);
		
		Bundle extras = getArguments();
		if (extras != null) {
			url = extras.getString("LOAD_URL");
		}
		
		WebView page = (WebView) getActivity().findViewById(R.id.webpage_webview);
		page.getSettings().setJavaScriptEnabled(true);
		page.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
		page.loadUrl(url);
		page.setWebViewClient(new WebViewClient() {

			public boolean shouldOverrideUrlLoading(WebView view, String url){
				view.loadUrl(url);
				return false;
			}
		});
		page.setWebChromeClient(new MyWebViewClient());
		
		WebPageFragment.this.progressBar.setProgress(0);
	}
	
	private class MyWebViewClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {         
        	WebPageFragment.this.setValue(newProgress);
        	
            if (newProgress == 100)
            	progressBar.setVisibility(View.INVISIBLE);
            
            super.onProgressChanged(view, newProgress);
        }
    }
	
	public void setValue(int progress) {
        this.progressBar.setProgress(progress);       
    }
}
