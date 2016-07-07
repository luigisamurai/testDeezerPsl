package testDeezerPsl;

import javax.ws.rs.core.MediaType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import com.google.common.net.HttpHeaders;
import com.sun.research.ws.wadl.HTTPMethods;
import testdeezerapi.utilities.HttpURLConnections;

@RunWith(MockitoJUnitRunner.class)
public class HttpURLConnectionsTest {
	
	@Mock  
	private HttpURLConnections mockHttpURLConnections;
	
	private HttpURLConnections httpURLConnection;
	
	private String headerXMashapeValue = null;
	
	@Before
	public void setup(){
      headerXMashapeValue = "WILX7s8CIPmshwtyE6SxV6fR1PYPp1XRuE1jsnW5TBnXCEN164";
	  httpURLConnection = new HttpURLConnections();
      MockitoAnnotations.initMocks(this);
	}
	
	/**
	  * Prueba unitaria que verifica que las llaves de propiedades de la cabecera se adicionan
	  * correctamente al objecto httpURLConnection 
	*/
	@Test
	public void validateAddKeyHeader(){
	  httpURLConnection.addHeader(HttpHeaders.ACCEPT, MediaType.TEXT_PLAIN);
	  httpURLConnection.addHeader(HttpHeaders.CONTENT_TYPE , MediaType.APPLICATION_JSON);
	  httpURLConnection.addHeader(HttpURLConnections.headerXMashapeKey, headerXMashapeValue);
		
	  Assert.assertTrue(httpURLConnection.getRequestHeader().containsKey(HttpHeaders.ACCEPT));
	  Assert.assertTrue(httpURLConnection.getRequestHeader().containsKey(HttpHeaders.CONTENT_TYPE));
	  Assert.assertTrue(httpURLConnection.getRequestHeader().containsKey(HttpURLConnections.headerXMashapeKey));
	}
	
	/**
	  * Prueba unitaria que verifica que los valores de propiedades de la cabecera se adicionan
	  * correctamente al objecto httpURLConnection 
	*/
	@Test
	public void validateAddValueHeader(){
	  httpURLConnection.addHeader(HttpHeaders.ACCEPT, MediaType.TEXT_PLAIN);
	  httpURLConnection.addHeader(HttpHeaders.CONTENT_TYPE , MediaType.APPLICATION_JSON);
	  httpURLConnection.addHeader(HttpURLConnections.headerXMashapeKey, headerXMashapeValue);
		
	  Assert.assertTrue(httpURLConnection.getRequestHeader().containsValue(MediaType.TEXT_PLAIN));
	  Assert.assertTrue(httpURLConnection.getRequestHeader().containsValue(MediaType.APPLICATION_JSON));
	  Assert.assertTrue(httpURLConnection.getRequestHeader().containsValue(headerXMashapeValue));
	}
	
	/**
	  * Prueba unitaria que verifica que los se establece correctamente
	  * los metodos de la peticion al objecto httpURLConnection 
	*/
	@Test
	public void validateSetRequestMethods(){
	  httpURLConnection.setRequestMethod(HTTPMethods.GET.value());
	  Assert.assertTrue(httpURLConnection.getRequestMethod().equals("GET"));
	  
	  httpURLConnection.setRequestMethod(HTTPMethods.POST.value());
	  Assert.assertTrue(httpURLConnection.getRequestMethod().equals("POST"));
	}
	
	
}
