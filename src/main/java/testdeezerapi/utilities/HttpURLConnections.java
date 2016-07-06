package testdeezerapi.utilities;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;



public class HttpURLConnections {
	
	private final String agentHeader = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36";
	private final String acceptLangHeader = "en-US,en;q=0.8";
	public static final String headerXMashapeKey   = "X-Mashape-Key";
	private String url;
	private String requestMethod;
	public HttpsURLConnection urlConnection;
	private HashMap<String, String> requestHeader;
	private String requestParams;
	//private HttpURLConnections httpURLConnections = new HttpURLConnections();
	
	public HttpURLConnections (){
		this.requestHeader = new HashMap<String, String>();
		this.requestParams = "";
	}
	
	public BufferedReader getResponseHttp() throws Exception{
		BufferedReader bufferedReader = null;
		try{
			bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(),"utf-8"));
			return bufferedReader;
		}
		catch(Exception e){
		 throw e;
		}
	}
	
	public String sendRequestHttp() throws Exception{
		Iterator iterator;
		Map.Entry property =null;
		DataOutputStream dataOutputStream = null;
		BufferedReader bufferedReader = null;
		String inputLine;
		String response = null;
		
		try{
			urlConnection = (HttpsURLConnection) new URL(this.url).openConnection();
			urlConnection.setRequestMethod(this.requestMethod);
			urlConnection.setRequestProperty("accept-language", acceptLangHeader);
			urlConnection.setRequestProperty("user-agent", agentHeader);
			urlConnection.setDoOutput(true);
			urlConnection.setDoInput(true);
		
			iterator = this.requestHeader.entrySet().iterator();
			while (iterator.hasNext()) {
				property = (Map.Entry)iterator.next();
				urlConnection.setRequestProperty(property.getKey().toString(), property.getValue().toString());
			}
			
			if(!this.requestParams.isEmpty()){
				dataOutputStream = new DataOutputStream(urlConnection.getOutputStream());
				dataOutputStream.writeBytes(this.requestParams);
				dataOutputStream.flush();
			}
			
			//bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(),"utf-8"));
			bufferedReader =  getResponseHttp();
			response = new String("");
		
			
			while ((inputLine = bufferedReader.readLine()) != null ) {
				inputLine = inputLine.replace("\\", "");
				inputLine = inputLine.replace("\r\n", "");
				inputLine = inputLine.replace("\n", "");
				response+=inputLine;
			}
			
		
			return response;
			
		}
		catch(Exception e){
			throw e;
		}
		finally{
			if(dataOutputStream!= null){
				dataOutputStream.close();
			}
				
			bufferedReader.close();
			urlConnection.disconnect();
		}
		
	}
	
	public void addHeader(String headerName, String headervalue){
		requestHeader.put(headerName, headervalue);
	}
	
	public void addParam(String nameParam, String valueParam){
		requestParams+= requestParams==null ? "" : "&";
		requestParams+= String.format("%s=%s", nameParam, valueParam);
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRequestMethod() {
		return requestMethod;
	}

	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}

	public HashMap<String, String> getRequestHeader() {
		return requestHeader;
	}



	public HttpsURLConnection getUrlConnection() {
		return urlConnection;
	}
}
