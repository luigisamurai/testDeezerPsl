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

	@GET
	@Path("findAlbumForArtist/{artistName}")
	@Consumes("text/plain")
	@Produces("application/json")
	public Response findAlbumForArtist(@PathParam("artistName") String artistName) throws Exception {
		DeezerApiLogic deezerApiLogic = null;
		JsonObject jsonResponse = null;
		try {
			deezerApiLogic = new DeezerApiLogic();
			jsonResponse = deezerApiLogic.queryForArtistName(artistName);
			return Response.status(200).entity(jsonResponse.toString()).build();	
		} catch (Exception e) {
			throw e;
		}
	}
}
