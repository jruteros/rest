package rest.jersey;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Singleton;


public class CoordenadaDAO {
	private Map<String, Coordenada> coordenadas = new LinkedHashMap<String,Coordenada>();
	public CoordenadaDAO() {
		//pumping-in some default data
		
		Coordenada coordenada1 = new Coordenada(-34.92112891716017,-57.9544950487301);
		coordenadas.put(coordenada1.getId(), coordenada1);
		Coordenada coordenada2 = new Coordenada(-34.91620244328586,-57.96149024984094);
		coordenadas.put(coordenada2.getId(), coordenada2);
		Coordenada coordenada3 = new Coordenada(-34.910848, -57.955218);
		coordenadas.put(coordenada3.getId(), coordenada3);
		
		}
	


public Map<String, Coordenada> getCoordenadas() {
	return coordenadas;
}


}