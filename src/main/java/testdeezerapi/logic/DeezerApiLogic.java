package testdeezerapi.logic;

import javax.ws.rs.core.MediaType;

import org.apache.http.protocol.HTTP;

import com.google.common.net.HttpHeaders;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.research.ws.wadl.HTTPMethods;
import testdeezerapi.utilities.HttpURLConnections;



public class DeezerApiLogic {
	
	private final String urlDeezerApp = "https://deezerdevs-deezer.p.mashape.com";
	public final String headerXMashapeValue = "WILX7s8CIPmshwtyE6SxV6fR1PYPp1XRuE1jsnW5TBnXCEN164";
	private HttpURLConnections httpURLConnection;
	private String response;
	
	public DeezerApiLogic(){
		httpURLConnection = new HttpURLConnections();
	}
	
	public JsonObject queryForArtistName(String artistName) throws Exception{
		String uriSearch = null;
		JsonObject jsonData = null;
		try{
			uriSearch = String.format("%s/artist/%s", urlDeezerApp, artistName);
			httpURLConnection.setUrl(uriSearch);
			httpURLConnection.setRequestMethod(HTTPMethods.GET.value());
			httpURLConnection.addHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
			httpURLConnection.addHeader(HttpHeaders.CONTENT_TYPE , MediaType.APPLICATION_JSON);
			httpURLConnection.addHeader(HttpURLConnections.headerXMashapeKey, headerXMashapeValue);
			response = this.httpURLConnection.sendRequestHttp();
			jsonData = new JsonParser().parse(response.toString()).getAsJsonObject();
			
			return jsonData;
		}
		catch(Exception e){
			throw e;
		}
	}

}
