package testdeezerapi.test;

import java.util.Arrays;
import java.util.Collection;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import com.google.gson.JsonObject;
import testdeezerapi.logic.DeezerApiLogic;
import testdeezerapi.utilities.HttpURLConnections;

@RunWith(Parameterized.class)
public class DeezerApiLogicTest {
	
	@Mock  
	private HttpURLConnections httpURLConnections;
	
	@InjectMocks 
	private DeezerApiLogic deezerApiLogic;
	
	private String artistName;
	
	public DeezerApiLogicTest(String artistName){
		this.artistName = artistName;
	}
	
	@Parameterized.Parameters
	   public static Collection filtersArtistName() {
	      return Arrays.asList(new Object[][] {
	         { "Pedro" },
	         { "Fonseca" },
	         { "jennifer lopez" },
	         { "Pedro infante" }
	      });
	}
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void queryForArtistName(){
		JsonObject successResponse = null;
		JsonObject response = null;
		try {
			successResponse = new JsonObject();
			successResponse.addProperty("link", "www.deezer.com");
			
			Mockito.when(httpURLConnections.sendHttpsRequest()).thenReturn(successResponse.toString());
			
			response = deezerApiLogic.queryForArtistName(this.artistName);
			Assert.assertTrue(response.has("link"));
		} catch (Exception e) {
			Assert.fail();
		}
	}
}
