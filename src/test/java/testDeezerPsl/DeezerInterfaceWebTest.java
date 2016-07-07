package testDeezerPsl;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@RunWith(Parameterized.class)
public class DeezerInterfaceWebTest {
	
	private FirefoxDriver webDriver;
	private WebElement inputArtist;
	private WebElement submitButton;
	private List<WebElement>  resultCells;
	private WebElement labelArtistName;
	private WebElement labelDeezerProfile;
	private WebElement labelAlbums;
	private WebElement labelDeezerFans	;
	private final String URL = "http://localhost:8080/testdeezerapi/"; 
	private final String linkDeezerProfile = "http://www.deezer.com/artist";
	private String filterArtistName = null;
	
	public DeezerInterfaceWebTest(String filterArtistName){
		this.filterArtistName = filterArtistName;
	}
	
	@Parameterized.Parameters
	   public static Collection filtersArtistName() {
	      return Arrays.asList(new Object[][] {
	         { "Gilberto" },
	         { "Fonseca" },
	         { "Pedro" }
	      });
	}
	
	@Before
    public void initialize(){
	  webDriver    = new FirefoxDriver();
	  webDriver.get(URL);	
	  submitButton = (new WebDriverWait(webDriver, 5)).until(ExpectedConditions.presenceOfElementLocated(By.xpath( "//button[@type='submit']" ) ));
	
	  inputArtist  = webDriver.findElementById("search-track-input"); 
	  resultCells  = webDriver.findElementsByClassName("ng-binding");
	  labelArtistName 	= resultCells.get(0);
	  labelDeezerProfile  = resultCells.get(1);
	  labelAlbums 		= resultCells.get(2);
	  labelDeezerFans 	= resultCells.get(3);
	}
	
	@After
    public void finalize(){
		webDriver.quit();
	}
		
	/**
	  * Prueba End to End que especifica un nombre de artista, consulta y valida los datos album
	  * @param  	 artistName  an absolute URL giving the base location of the image
	*/
	@Test
    public void searchAlbumsInDeezer(){
	  inputArtist.sendKeys(filterArtistName);
	  submitButton.click();
	
	  (new WebDriverWait(webDriver, 5)).until(new ExpectedCondition<Boolean>() {
	    public Boolean apply(WebDriver driver) {
			boolean successResponse = labelArtistName.getText().equals("")
					&& labelDeezerProfile.getText().equals("")
					&& labelAlbums.getText().equals("")
					&& labelDeezerFans.getText().equals("");
			return !successResponse;
		}
     });
	}
	
	/**
	  * Prueba End to End busca por el nombre del artista y accede al link de deezer obtenido 
	  * en la consulta, se valida que la redireccion al perfil del artista fue exitosa
	  * @param  	 artistName  an absolute URL giving the base location of the image
	*/
	@Test
    public void loadPageProfileDeezerArtist(){
	  inputArtist.sendKeys(filterArtistName);
	  submitButton.click();
	
	  WebElement partialLinkDeezer = (new WebDriverWait(webDriver, 5)).
	    until(ExpectedConditions.presenceOfElementLocated( By.partialLinkText("https://www.deezer.com/artist")  ));
	
	  partialLinkDeezer.click();
	  (new WebDriverWait(webDriver, 5)).until(new ExpectedCondition<Boolean>() {
		public Boolean apply(WebDriver driver) {
			return driver.getCurrentUrl().startsWith(linkDeezerProfile);
		}
      });
		
	}
	
}
