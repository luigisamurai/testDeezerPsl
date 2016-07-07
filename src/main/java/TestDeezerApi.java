

import java.util.Arrays;

import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import testdeezerapi.controller.DeezerApiController;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class TestDeezerApi extends SpringBootServletInitializer {
	private static final Logger logger = LoggerFactory.getLogger(TestDeezerApi.class);

	 @Override
	 protected SpringApplicationBuilder configure( SpringApplicationBuilder application) {
	  return application.sources(TestDeezerApi.class);
	 }
	 
	 /**
	  * Metodo de inicial que despliega la aplicacion en el servidor Tomcat
	  * @param  args  Array de parametros de entrada para metodo inicial
	*/
	 public static void main(String[] args) {
	   ApplicationContext ctx = SpringApplication.run(TestDeezerApi.class, args);
	   logger.info("Let's inspect the beans provided by Spring Boot:");

	    String[] beanNames = ctx.getBeanDefinitionNames();
	    Arrays.sort(beanNames);
	    
	    for (String beanName : beanNames) {
	     logger.info(beanName);
	    }
	 }

}

@RestController
class AppController {
	
	/**
    * Servicio que se comunica con la logica de la aplicacion para consumir el servicio Deezer 
    * @param  artistName  especifica el nombre del artista que se desea buscar
    * @return  Retorna el response de la peticion Http con el formato application/json
   */
    @RequestMapping("findAlbumForArtist/{artistName}")
    public Response findAlbumForArtist(@PathVariable String artistName) throws Exception {
    	return new DeezerApiController().findAlbumForArtist(artistName);
    }
} 
