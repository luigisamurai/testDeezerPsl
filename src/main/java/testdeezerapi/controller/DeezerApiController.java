package testdeezerapi.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import com.google.gson.JsonObject;
import testdeezerapi.logic.DeezerApiLogic;


@Path("/deezerApi")
public class DeezerApiController {
	
	private DeezerApiLogic deezerApiLogic;
	
	public DeezerApiController(){
		this.deezerApiLogic = new DeezerApiLogic();
	}
	
   /**
    * Servicio que se comunica con la logica de la aplicacion para consumir el servicio Deezer 
    * @param  artistName  especifica el nombre del artista que se desea buscar
    * @return  Retorna el response de la peticion Http con el formato application/json
   */
	@GET
	@Path("findAlbumForArtist/{artistName}")
	@Consumes("text/plain")
	@Produces("application/json")
	public Response findAlbumForArtist(@PathParam("artistName") String artistName) throws Exception {
		JsonObject jsonResponse = null;
		try {
			jsonResponse = this.deezerApiLogic.queryForArtistName(artistName);
			return Response.status(200).entity(jsonResponse.toString()).build();	
		} catch (Exception e) {
			throw e;
		}
	}
}
