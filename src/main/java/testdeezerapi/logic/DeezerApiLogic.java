package testdeezerapi.logic;

import javax.ws.rs.core.MediaType;

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
	
	/**
	  * Realiza una consulta con filtro nombre del artista en el servicio publicado Deezer 
	  * @param  artistName  an absolute URL giving the base location of the image
	  * @return      Objecto JsonObject con la informacio obtenida
	 * @throws Exception 
	  * @see         https://market.mashape.com/deezerdevs/deezer-1#artist
	*/
	public JsonObject queryForArtistName(String artistName) throws Exception{
	  String uriSearch = null;
	  JsonObject jsonData = null;
	
	  uriSearch = String.format("%s/artist/%s", urlDeezerApp, artistName);
	  httpURLConnection.setUrl(uriSearch);
	  httpURLConnection.setRequestMethod(HTTPMethods.GET.value());
	  httpURLConnection.addHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
	  httpURLConnection.addHeader(HttpHeaders.CONTENT_TYPE , MediaType.APPLICATION_JSON);
	  httpURLConnection.addHeader(HttpURLConnections.headerXMashapeKey, headerXMashapeValue);
	  response = this.httpURLConnection.sendHttpsRequest();
	  jsonData = new JsonParser().parse(response.toString()).getAsJsonObject();
	
	  return jsonData;
	}

}
