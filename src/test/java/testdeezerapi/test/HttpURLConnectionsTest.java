package testdeezerapi.test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.ws.rs.core.MediaType;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.net.HttpHeaders;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.research.ws.wadl.HTTPMethods;

import testdeezerapi.utilities.HttpURLConnections;

@RunWith(MockitoJUnitRunner.class)
public class HttpURLConnectionsTest {
	
	@Mock  private HttpURLConnections mockHttpURLConnections;
	@InjectMocks private HttpURLConnections httpURLConnection;
	
	private String headerXMashapeValue = null;
	
	@Before
	public void setup(){
		headerXMashapeValue = "WILX7s8CIPmshwtyE6SxV6fR1PYPp1XRuE1jsnW5TBnXCEN164";
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void validateAddKeyHeader(){
		
		try {
			httpURLConnection.addHeader(HttpHeaders.ACCEPT, MediaType.TEXT_PLAIN);
			httpURLConnection.addHeader(HttpHeaders.CONTENT_TYPE , MediaType.APPLICATION_JSON);
			httpURLConnection.addHeader(HttpURLConnections.headerXMashapeKey, headerXMashapeValue);
			
			Assert.assertTrue(httpURLConnection.getRequestHeader().containsKey(HttpHeaders.ACCEPT));
			Assert.assertTrue(httpURLConnection.getRequestHeader().containsKey(HttpHeaders.CONTENT_TYPE));
			Assert.assertTrue(httpURLConnection.getRequestHeader().containsKey(HttpURLConnections.headerXMashapeKey));
			
		} catch (Exception e) {
			Assert.fail();
		}
	}
	
	
	@Test
	public void validateAddValueHeader(){
		
		try {
			httpURLConnection.addHeader(HttpHeaders.ACCEPT, MediaType.TEXT_PLAIN);
			httpURLConnection.addHeader(HttpHeaders.CONTENT_TYPE , MediaType.APPLICATION_JSON);
			httpURLConnection.addHeader(HttpURLConnections.headerXMashapeKey, headerXMashapeValue);
			
			Assert.assertTrue(httpURLConnection.getRequestHeader().containsValue(MediaType.TEXT_PLAIN));
			Assert.assertTrue(httpURLConnection.getRequestHeader().containsValue(MediaType.APPLICATION_JSON));
			Assert.assertTrue(httpURLConnection.getRequestHeader().containsValue(headerXMashapeValue));
			
		} catch (Exception e) {
			Assert.fail();
		}
	}
	
	
	@Test
	public void validateSetRequestMethods(){
		
		try {
			httpURLConnection.setRequestMethod(HTTPMethods.GET.value());
			Assert.assertTrue(httpURLConnection.getRequestMethod().equals("GET"));
			
			httpURLConnection.setRequestMethod(HTTPMethods.POST.value());
			Assert.assertTrue(httpURLConnection.getRequestMethod().equals("POST"));
			
			
		} catch (Exception e) {
			Assert.fail();
		}
	}
	
	@Test
	public void validateSendRequestHttp(){
		InputStream inputStreanResponse = new ByteArrayInputStream( "{\"id\":188,\"name\":\"Joe\",\"link\":\"https://www.deezer.com/artist/188\",\"share\":\"http://www.deezer.com/artist/188?utm_source=deezer&utm_content=artist-188&utm_term=0_1467782509&utm_medium=web\",\"picture\":\"https://api.deezer.com/artist/188/image\",\"picture_small\":\"https://cdns-images.dzcdn.net/images/artist/c7a3ecb32c06a934d12dab642fa14cce/56x56-000000-80-0-0.jpg\",\"picture_medium\":\"https://cdns-images.dzcdn.net/images/artist/c7a3ecb32c06a934d12dab642fa14cce/250x250-000000-80-0-0.jpg\",\"picture_big\":\"https://cdns-images.dzcdn.net/images/artist/c7a3ecb32c06a934d12dab642fa14cce/500x500-000000-80-0-0.jpg\",\"nb_album\":43,\"nb_fan\":73008,\"radio\":true,\"tracklist\":\"https://api.deezer.com/artist/188/top?limit=50\",\"type\":\"artist\"}".getBytes() );
		BufferedReader response = new BufferedReader(new InputStreamReader(inputStreanResponse));
		JsonObject jsonData = null;
		int expectedValue = 188;
		try {
		
			httpURLConnection.addHeader(HttpHeaders.ACCEPT, MediaType.TEXT_PLAIN);
			httpURLConnection.addHeader(HttpHeaders.CONTENT_TYPE , MediaType.APPLICATION_JSON);
			httpURLConnection.addHeader(HttpURLConnections.headerXMashapeKey, headerXMashapeValue);
			httpURLConnection.setRequestMethod(HTTPMethods.GET.value());
		
			
			Mockito.when( httpURLConnection.getResponseHttp() ).thenReturn(response);
			jsonData = new JsonParser().parse(httpURLConnection.sendRequestHttp()).getAsJsonObject();
			
			Assert.assertTrue(jsonData.get("id").getAsInt() == expectedValue );
			
			
		} catch (Exception e) {
			Assert.fail();
		}
	}
}
