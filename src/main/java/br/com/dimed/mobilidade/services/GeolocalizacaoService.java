package br.com.dimed.mobilidade.services;

import org.springframework.stereotype.Service;

@Service
public class GeolocalizacaoService {

	public  Double distanciaEntrePonto(Double latPonto, Double lngPonto, Double latUsuario, Double lonUsuario) {
		Double theta = lngPonto - lonUsuario;
		Double dist = Math.sin(deg2rad(latPonto)) * Math.sin(deg2rad(latUsuario))
				+ Math.cos(deg2rad(latPonto)) * Math.cos(deg2rad(latUsuario)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist *= 60 * 1.1515;
		dist *= 1.609344;
		return (dist);
	}

	private Double deg2rad(Double deg) {
		return (deg * Math.PI / 180.0);
	}

	private Double rad2deg(Double rad) {
		return (rad * 180 / Math.PI);
	}
}
