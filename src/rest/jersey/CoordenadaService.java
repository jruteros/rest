package rest.jersey;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CoordenadaService {
	CoordenadaDAO coordenadaDAO;

	public CoordenadaService() {
		coordenadaDAO = new CoordenadaDAO();
	}

	public void crearCoordenada(Coordenada coordenada) {
		coordenadaDAO.agregar(coordenada);
	}

	public List<Coordenada> getCoordenadasList() {
		return coordenadaDAO.recuperarTodos();
	}

	public void eliminarTodo() {
		coordenadaDAO.eliminarTodo();
	}

	public void eliminarCoordenada(String id) {
		coordenadaDAO.eliminar(id);
	}

	public Coordenada recuperarCoordenada(String id) {
		return coordenadaDAO.recuperar(id);
	}

	public Coordenada obtenerAnteriorCoordenada(String id) {
		return coordenadaDAO.recuperarAnteriorCoordenada(id);
	}
}
