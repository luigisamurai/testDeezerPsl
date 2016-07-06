package testdeezerapi.test;

import javax.ws.rs.core.Response;

import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import testdeezerapi.controller.DeezerApiController;
import testdeezerapi.logic.DeezerApiLogic;


public class DeezerApiControllerTest {
	@Mock  
	private DeezerApiLogic deezerApiLogic;
	
	@InjectMocks 
	private DeezerApiController deezerApiController;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}
	
	/**
	  * Realiza una consulta con filtro nombre del artista en el servicio publicado por esta aplicacion 
	  * @param  	 artistName  an absolute URL giving the base location of the image
	  * @return      Objecto JsonObject con la informacio obtenida
	  * @see         http://{SERVER}:{PORT}/testdeezerapi/rest/deezerApi/findAlbumForArtist/{artistName}
	*/
	@Test
	public void queryDeezerForArtistNameRestServices(){
		String artistName = "fonseca";
		Response expectedResponse = null;
		Response response = null;
		JsonObject expectedJson = null;
		try{
			
			expectedJson = new JsonParser().parse("{\"id\":188,\"name\":\"Joe\",\"link\":\"https://www.deezer.com/artist/188\",\"share\":\"http://www.deezer.com/artist/188?utm_source=deezer&utm_content=artist-188&utm_term=0_1467782509&utm_medium=web\",\"picture\":\"https://api.deezer.com/artist/188/image\",\"picture_small\":\"https://cdns-images.dzcdn.net/images/artist/c7a3ecb32c06a934d12dab642fa14cce/56x56-000000-80-0-0.jpg\",\"picture_medium\":\"https://cdns-images.dzcdn.net/images/artist/c7a3ecb32c06a934d12dab642fa14cce/250x250-000000-80-0-0.jpg\",\"picture_big\":\"https://cdns-images.dzcdn.net/images/artist/c7a3ecb32c06a934d12dab642fa14cce/500x500-000000-80-0-0.jpg\",\"nb_album\":43,\"nb_fan\":73008,\"radio\":true,\"tracklist\":\"https://api.deezer.com/artist/188/top?limit=50\",\"type\":\"artist\"}").getAsJsonObject();
			expectedResponse = Response.status(HttpStatus.SC_OK).entity(expectedJson.toString()).build();
			
			Mockito.when(deezerApiLogic.queryForArtistName(artistName)).thenReturn(expectedJson);
			response = deezerApiController.findAlbumForArtist(artistName);
			
			Assert.assertEquals(expectedResponse.getStatus(), response.getStatus());
			
		} catch (Exception e) {
			Assert.fail();
		}
	}
}
