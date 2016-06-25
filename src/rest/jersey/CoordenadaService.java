package rest.jersey;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CoordenadaService {
CoordenadaDAO coordenadaDAO;


public CoordenadaService(){
	coordenadaDAO = new CoordenadaDAO();
}

public void crearCoordenada(Coordenada coordenada){
	/*System.out.println("Tamaño :"+coordenadaDAO.getCoordenadas().size());
	System.out.println("id_coordenada: "+coordenada.getId());
	System.out.println("lat: "+coordenada.getLat());
	System.out.println("lon: "+coordenada.getLon());*/
	coordenadaDAO.getCoordenadas().put(coordenada.getId(), coordenada);
//	System.out.println(coordenada.getId());
	/*
	for (Map.Entry<String, Coordenada> entry : coordenadaDAO.getCoordenadas().entrySet())
	{
	    System.out.println(entry.getKey() + "/" + entry.getValue().getId());
	}
	*/
//	System.out.println("Tamaño :"+coordenadaDAO.getCoordenadas().size());
	
}

public List<Coordenada> getAnimalsasList(){
	List<Coordenada> listaCoordenadas = new ArrayList<Coordenada>();
	listaCoordenadas.addAll(coordenadaDAO.getCoordenadas().values());
	return listaCoordenadas;
}

public void borrarTodo() {
	coordenadaDAO.getCoordenadas().clear();
	
}

public void borrarCoordenada(String id_coordenada) {
	coordenadaDAO.getCoordenadas().remove(id_coordenada);
	
}
}
