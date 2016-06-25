// var myURI = "/Mapas/rest/rutas/1"; Original
//var myURI = "rest/coordenadas"; Funcionando
var myURI = "rest/rutas/1";
var mapProp = {
	//center : new google.maps.LatLng(-34.9038055, -57.9392111, 18),
	zoom : 14,
	mapTypeId : google.maps.MapTypeId.ROADMAP
};

var ok=false;
var puntos = [];
var map;
// Evento
google.maps.event.addDomListener(window, 'load', initialize);

/**
 * Inicializa el mapa
 */
function initialize() {
	if (ok==false){
		obtenerPrimerCoordenada();
	}
	
		map = new google.maps.Map(document.getElementById("googleMap"), mapProp);

		map.addListener('click', function(e) {
			console.log("click");
			agregarMarker(e.latLng);

		});

	puntos = [];
	obtenerMarkers();
}

// Metodo añadido por Alex e Ignacio
function obtenerPrimerCoordenada(){
		$.ajax({
			dataType : "json",
			url : myURI+"/coordenadas/primerCoordenada",
			type : "GET",
			success : function(result) {
				console.log(result.lat);
				mapProp.center= new google.maps.LatLng(result.lat, result.lon);
				ok=true;
				//initialize();
				
				map.setCenter(new google.maps.LatLng(result.lat, result.lon));
			}
			
		});
	
}

// Obtiene markers y los dibuja
function obtenerMarkers(dibujar) {

	$.ajax({
		dataType : "json",
		url : myURI+"/coordenadas",
		success : function(result) {
			//console.log("result " + result);
			puntos = [];
			$.each(result, function(i, dato) {
				//console.log("dato" + i + dato.lat);
				dibujarMarker(dato);

			});
			dibujarRecorrido();
		}
	});
}

function dibujarMarker(dato) {

	var position = new google.maps.LatLng(dato.lat, dato.lon);

	var marker = new google.maps.Marker({
		position : position,
		icon: {
		      path: google.maps.SymbolPath.CIRCLE,
		      scale: 3
		    },
		id : dato.id
	});

	marker.addListener("rightclick", function(point) {
		console.log("rigthclick");
		borrarMarker(dato.id);
		marker.setMap(null);
	});

	puntos[puntos.length] = position;

	marker.setMap(map);
}

function agregarMarker(latLng) {

	var punto = {
		lat : latLng.lat(),
		lon : latLng.lng()
	};
	$.ajax({
		data : punto,
		
		url : myURI+"/coordenadas?lat="+latLng.lat()+"&lon="+latLng.lng()+"",
		
		type : "POST",
		success : function(result) {
			$("#limpiarMapa").show();
			obtenerMarkers();
		},
		error: function(){
			console.log("errorAjax");
		}
	});

}

function dibujarRecorrido() {

	var flightPath = new google.maps.Polyline({
		path : puntos,
		strokeColor : "#0000FF",
		strokeOpacity : 0.8,
		strokeWeight : 2
	});

	flightPath.setMap(map);
}

function dibujarRecorridoCircular() {

	markers = puntos;
	markers[markers.length] = puntos[0];

	var flightPath = new google.maps.Polyline({
		path : markers,
		strokeColor : "#0000FF",
		strokeOpacity : 0.8,
		strokeWeight : 2
	});

	flightPath.setMap(map);
}

function limpiarMapa() {

	punto = {
		id : null
	};
	$.ajax({
		data : punto,
		dataType : "json",
		url : myURI+"/coordenadas/eliminarTodo",
		type : "DELETE",
		success : function(result) {
			mapProp.center= new google.maps.LatLng(result.lat, result.lon);
			
			$("#limpiarMapa").hide();
			initialize();
		}
	});

}

function borrarMarker(id) {
	console.log("borrar marker " + id);
	punto = {
		id : id
	};
	$.ajax({
		data : punto,
		dataType : "json",
		url : myURI+"/coordenadas?id_coordenada="+id+"",
		type : "DELETE",
		success : function(result) {
			mapProp.center= new google.maps.LatLng(result.lat, result.lon);
					
				
			
			initialize();
		}
	});
}