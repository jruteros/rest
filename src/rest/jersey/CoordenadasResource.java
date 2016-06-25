package rest.jersey;

import java.util.List;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/rutas/1/coordenadas")
@Singleton
public class CoordenadasResource {
	
	CoordenadaService coordenadaService;
	
	public CoordenadasResource(){
		coordenadaService = new CoordenadaService();
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Coordenada> getAnimalsAsHtml() {
		/*for(Coordenada c:coordenadaService.getAnimalsasList()){
			System.out.println("Coordenada");
			System.out.println(c.getLat());
			System.out.println(c.getLon());
			
		} */
		return coordenadaService.getAnimalsasList();
		
	}
	
	@GET
	@Path("/primerCoordenada")
	@Produces(MediaType.APPLICATION_JSON)
	public Coordenada obtenerPrimerCoordenada() {
		if (!(coordenadaService.getAnimalsasList().isEmpty())){
			return coordenadaService.getAnimalsasList().get(0);
		}
		else{
			Coordenada coordenada = new Coordenada(-34.9038055,-57.9392111);
			return coordenada;
		}
	}
	
	@POST
	public void agregarCoordenada(@QueryParam("lat") Double lat,
            @QueryParam("lon") Double lon ){
		/*System.out.println("PASO?");
		System.out.println("Latitud: "+ lat );
		System.out.println("Longitud: "+ lon);*/
		Coordenada coordenada = new Coordenada(lat,lon);
		coordenadaService.crearCoordenada(coordenada);
	}
	
	@DELETE
	@Path("eliminarTodo")
	@Produces(MediaType.APPLICATION_JSON)
	public Coordenada eliminarTodo(){
		Coordenada coordenada = coordenadaService.getAnimalsasList().get(0);
		coordenadaService.borrarTodo();
		return coordenada;
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Coordenada eliminarCoordenada(@QueryParam("id_coordenada") String id_coordenada){
		coordenadaService.borrarCoordenada(id_coordenada);
		return coordenadaService.getAnimalsasList().get(coordenadaService.getAnimalsasList().size()-1); //Devuelve la ultima coordenada para que el mapa quede centrado y no se vaya a otro lado
	}
}
