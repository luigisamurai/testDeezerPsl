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
	private int responseCode;
	
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
	
	/**
	  * Env�a una petici�n Http con una cabecera, m�todo de envi� (GET o POST) y par�metros de petici�n.
	  * La cabecera, m�todo y par�metros de la petici�n son parametrizados antes de acceder a este m�todo
	  * @return      String, almacena la respuesta en el formato solicitado
	  * @see         https://docs.oracle.com/javase/7/docs/api/javax/net/ssl/HttpsURLConnection.html
	*/
	public String sendHttpsRequest() throws Exception{
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
			
			this.responseCode = urlConnection.getResponseCode();
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
	
	/**
	  * Establece un par�metro y valor para ser adicionado como Cabecera de la petici�n HTTP
	  * @param  headerName  Nombre de la propiedad de cabecera que se adiciona
	  * @param  headervalue Valor de la propiedad de cabecera que se adiciona
	*/
	public void addHeader(String headerName, String headervalue){
		requestHeader.put(headerName, headervalue);
	}
	
	/**
	  * Establece un nombre y valor para ser adicionado como parametros de la petici�n HTTP
	  * @param  nameParam  Nombre de la propiedad que se adiciona
	  * @param  valueParam Valor de la propiedad que se adiciona
	*/
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
	
	/**
	  * Establece el m�todo que se utiliza para el envi� de la petici�n Http 
	  * @param  requestMethod  Nombre del m�todo de envi� de la petici�n http (Ej: GET., POST) 
	*/
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

	public int getResponseCode() {
		return responseCode;
	}
}
