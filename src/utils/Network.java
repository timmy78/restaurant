package utils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.params.ConnManagerPNames;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.xml.sax.InputSource;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Network {

	/*
	 * send parameters to url with method POST
	 */
	/*
	 * public static void postData(String url,Map<String,String> values) { //
	 * Create a new HttpClient and Post Header HttpClient httpclient = new
	 * DefaultHttpClient(); //"http://www.yoursite.com/script.php" HttpPost
	 * httppost = new HttpPost(url);
	 * 
	 * try { // Add your data
	 * 
	 * 
	 * List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
	 * nameValuePairs.add(new BasicNameValuePair("id", "12345"));
	 * nameValuePairs.add(new BasicNameValuePair("stringdata",
	 * "AndDev is Cool!")); httppost.setEntity(new
	 * UrlEncodedFormEntity(nameValuePairs));
	 * 
	 * // Execute HTTP Post Request HttpResponse response =
	 * httpclient.execute(httppost);
	 * 
	 * } catch (ClientProtocolException e) { // TODO Auto-generated catch block
	 * } catch (IOException e) { // TODO Auto-generated catch block } }
	 */

	private static HttpResponse response;

	/*
	 * UrlEncodedFormEntity : throws UnsupportedEncodingException
	 * httpclient.execute : throws ClientProtocolException, IOException
	 */
	public static HttpResponse postData(String url,
			List<NameValuePair> nameValuePairs) throws ClientProtocolException,
			IOException {

		HttpParams httpParameters = new BasicHttpParams();
		// Set the timeout in milliseconds until a connection is established.
		int timeoutConnection = 30000;
		httpParameters.setLongParameter(ConnManagerPNames.TIMEOUT, 30000);
		HttpConnectionParams.setConnectionTimeout(httpParameters,
				timeoutConnection);
		// Set the default socket timeout (SO_TIMEOUT)
		// in milliseconds which is the timeout for waiting for data.
		int timeoutSocket = 30000;
		HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);

		// Create a new HttpClient and Post Header
		HttpClient httpclient = new DefaultHttpClient(httpParameters);
		httpclient.getParams().setParameter("http.protocol.content-charset",
				HTTP.UTF_8);
		HttpPost httppost = new HttpPost(url);
		
		httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
		// httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

		// Execute HTTP Post Request

		HttpResponse response = httpclient.execute(httppost);
		return response;

	}

	/*
	 * UrlEncodedFormEntity : throws UnsupportedEncodingException
	 * httpclient.execute : throws ClientProtocolException, IOException
	 */
	/*
	public static HttpResponse postImageData(String url,
			List<NameValuePair> nameValuePairs) throws ClientProtocolException,
			IOException {

		// HttpClient httpClient = new DefaultHttpClient();
		// HttpPost postRequest = new HttpPost("You Link");
		// MultipartEntity reqEntity = new
		// MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
		// reqEntity.addPart("name", new StringBody("Name"));
		// reqEntity.addPart("Id", new StringBody("ID"));
		// reqEntity.addPart("title",new StringBody("TITLE"));
		// reqEntity.addPart("caption", new StringBody("Caption"));
		// try{
		// ByteArrayOutputStream bos = new ByteArrayOutputStream();
		// bitmap.compress(CompressFormat.JPEG, 75, bos);
		// byte[] data = bos.toByteArray();
		// ByteArrayBody bab = new ByteArrayBody(data, "forest.jpg");
		// reqEntity.addPart("picture", bab);
		// }
		// catch(Exception e){
		// //Log.v("Exception in Image", ""+e);
		// reqEntity.addPart("picture", new StringBody(""));
		// }
		// postRequest.setEntity(reqEntity);
		// HttpResponse response = httpClient.execute(postRequest);
		// BufferedReader reader = new BufferedReader(new
		// InputStreamReader(response.getEntity().getContent(), "UTF-8"));
		// String sResponse;
		// StringBuilder s = new StringBuilder();
		// while ((sResponse = reader.readLine()) != null) {
		// s = s.append(sResponse);
		// }
		//
		// HttpResponse response = httpclient.execute(httppost);
		// return response;

		HttpClient httpClient = new DefaultHttpClient();
		HttpContext localContext = new BasicHttpContext();
		HttpPost httpPost = new HttpPost(url);

		try {
			MultipartEntity entity = new MultipartEntity(
					HttpMultipartMode.BROWSER_COMPATIBLE);

			for (int index = 0; index < nameValuePairs.size(); index++) {
				if (nameValuePairs.get(index).getName()
						.equalsIgnoreCase("image")) {
					// If the key equals to "image", we use FileBody to transfer
					// the data
					entity.addPart(nameValuePairs.get(index).getName(),
							new FileBody(new File(nameValuePairs.get(index)
									.getValue())));
				} else {
					// Normal string data
					entity.addPart(
							nameValuePairs.get(index).getName(),
							new StringBody(nameValuePairs.get(index).getValue()));
				}
			}

			httpPost.setEntity(entity);

			response = httpClient.execute(httpPost, localContext);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;

	}*/

	/*
	public static HttpResponse postImageData(String url,
			List<NameValuePair> nameValuePairs, Bitmap b)
			throws ClientProtocolException, IOException {

		// HttpClient httpClient = new DefaultHttpClient();
		// HttpPost postRequest = new HttpPost("You Link");
		// MultipartEntity reqEntity = new
		// MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
		// reqEntity.addPart("name", new StringBody("Name"));
		// reqEntity.addPart("Id", new StringBody("ID"));
		// reqEntity.addPart("title",new StringBody("TITLE"));
		// reqEntity.addPart("caption", new StringBody("Caption"));
		// try{
		// ByteArrayOutputStream bos = new ByteArrayOutputStream();
		// bitmap.compress(CompressFormat.JPEG, 75, bos);
		// byte[] data = bos.toByteArray();
		// ByteArrayBody bab = new ByteArrayBody(data, "forest.jpg");
		// reqEntity.addPart("picture", bab);
		// }
		// catch(Exception e){
		// //Log.v("Exception in Image", ""+e);
		// reqEntity.addPart("picture", new StringBody(""));
		// }
		// postRequest.setEntity(reqEntity);
		// HttpResponse response = httpClient.execute(postRequest);
		// BufferedReader reader = new BufferedReader(new
		// InputStreamReader(response.getEntity().getContent(), "UTF-8"));
		// String sResponse;
		// StringBuilder s = new StringBuilder();
		// while ((sResponse = reader.readLine()) != null) {
		// s = s.append(sResponse);
		// }
		//
		// HttpResponse response = httpclient.execute(httppost);
		// return response;

		HttpClient httpClient = new DefaultHttpClient();
		HttpContext localContext = new BasicHttpContext();
		HttpPost httpPost = new HttpPost(url);

		try {
			MultipartEntity entity = new MultipartEntity(
					HttpMultipartMode.BROWSER_COMPATIBLE);
			// we use FileBody to transfer the data
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			b.compress(Bitmap.CompressFormat.JPEG, 80, stream);
			entity.addPart("image", new ByteArrayBody(stream.toByteArray(),
					"myfile.jpg"));
			for (int index = 0; index < nameValuePairs.size(); index++) {

				// Normal string data
				entity.addPart(nameValuePairs.get(index).getName(),
						new StringBody(nameValuePairs.get(index).getValue()));

			}

			httpPost.setEntity(entity);

			response = httpClient.execute(httpPost, localContext);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;

	}*/

	public static HttpResponse deleteData(String url)
			throws ClientProtocolException, IOException, URISyntaxException {

		HttpParams httpParameters = new BasicHttpParams();
		// Set the timeout in milliseconds until a connection is established.
		int timeoutConnection = 30000;
		HttpConnectionParams.setConnectionTimeout(httpParameters,
				timeoutConnection);
		// Set the default socket timeout (SO_TIMEOUT)
		// in milliseconds which is the timeout for waiting for data.
		int timeoutSocket = 30000;
		HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);

		// Create a new HttpClient and Post Header
		HttpClient httpclient = new DefaultHttpClient(httpParameters);
		URI uri = new URI(url);
		// httpGet.setURI(uri);
		HttpDelete httpDelete = new HttpDelete();
		httpDelete.setURI(uri);
		// httpget.setParams(params)setEntity(new
		// UrlEncodedFormEntity(nameValuePairs));

		// Execute HTTP Post Request
		HttpResponse response = httpclient.execute(httpDelete);
		return response;

	}

	public static HttpResponse getData(String url)
			throws ClientProtocolException, IOException, URISyntaxException {
		HttpParams httpParameters = new BasicHttpParams();
		// Set the timeout in milliseconds until a connection is established.
		int timeoutConnection = 30000;
		HttpConnectionParams.setConnectionTimeout(httpParameters,
				timeoutConnection);
		// Set the default socket timeout (SO_TIMEOUT)
		// in milliseconds which is the timeout for waiting for data.
		int timeoutSocket = 30000;
		HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);

		// Create a new HttpClient and Post Header
		HttpClient httpclient = new DefaultHttpClient(httpParameters);
		URI uri = new URI(url);
		// httpGet.setURI(uri);
		HttpGet httpGet = new HttpGet();
		httpGet.setURI(uri);
		// httpget.setParams(params)setEntity(new
		// UrlEncodedFormEntity(nameValuePairs));

		// Execute HTTP Post Request
		HttpResponse response = httpclient.execute(httpGet);
		return response;

	}

	public static HttpResponse putData(String url, String data)
			throws ClientProtocolException, IOException {

		HttpParams httpParameters = new BasicHttpParams();
		// Set the timeout in milliseconds until a connection is established.
		int timeoutConnection = 30000;
		HttpConnectionParams.setConnectionTimeout(httpParameters,
				timeoutConnection);
		// Set the default socket timeout (SO_TIMEOUT)
		// in milliseconds which is the timeout for waiting for data.
		int timeoutSocket = 30000;
		HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);

		// Create a new HttpClient and Post Header
		HttpClient httpclient = new DefaultHttpClient(httpParameters);
		HttpPut httpput = new HttpPut(url);
		// httpput.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		httpput.setEntity(new StringEntity(data));
		// httpput.setHeader("Content-Type", "application/xml");
		httpput.setHeader("Content-Type", "text/xml");

		// Execute HTTP Post Request
		HttpResponse response = httpclient.execute(httpput);
		return response;

	}

	public static HttpResponse putData2(String url,
			List<NameValuePair> nameValuePairs) throws ClientProtocolException,
			IOException {

		HttpParams httpParameters = new BasicHttpParams();
		// Set the timeout in milliseconds until a connection is established.
		int timeoutConnection = 30000;
		HttpConnectionParams.setConnectionTimeout(httpParameters,
				timeoutConnection);
		// Set the default socket timeout (SO_TIMEOUT)
		// in milliseconds which is the timeout for waiting for data.
		int timeoutSocket = 30000;
		HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);

		// Create a new HttpClient and Post Header
		HttpClient httpclient = new DefaultHttpClient(httpParameters);
		HttpPut httpput = new HttpPut(url);
		// httpput.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		httpput.setEntity(new StringEntity(nameValuePairs.get(0).getValue()));
		// httpput.setHeader("Content-Type", "application/xml");
		httpput.setHeader("Content-Type", "text/xml");

		// Execute HTTP Post Request
		HttpResponse response = httpclient.execute(httpput);
		return response;

	}

	public static boolean isNetworkAvailable(Context context) {
		// Context context = getApplicationContext();
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity != null) {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private static InputSource retrieveInputStream(HttpEntity httpEntity) {
		InputSource insrc = null;
		try {
			insrc = new InputSource(httpEntity.getContent());
		} catch (Exception e) {
		}
		return insrc;
	}

}
