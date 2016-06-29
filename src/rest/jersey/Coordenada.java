package rest.jersey;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Coordenada {
	private String id;
	private double lat;
	private double lon;
	public static int id_coordenada=1;
	public Coordenada(double lat,double lon){
		
		this.id=String.valueOf(id_coordenada);
		id_coordenada++;
		this.lat=lat;
		this.lon=lon;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public double getLat() {
		return lat;
	}
	
	public void setLat(double lat) {
		this.lat = lat;
	}
	
	public double getLon() {
		return lon;
	}
	
	public void setLon(double lon) {
		this.lon = lon;
	}
}
