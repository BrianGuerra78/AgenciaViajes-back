package mx.com.basantader.AgenciaViajeTA.service;

import java.util.List;


import mx.com.basantader.AgenciaViajeTA.dto.CityDTO;
import mx.com.basantader.AgenciaViajeTA.dto.RespuestaDTO;

public interface CityService {
	
	List<CityDTO> getCities();
	List<CityDTO> searchCities(String name, String continentName );
	CityDTO createCity(CityDTO newCity);
	CityDTO updateCity(CityDTO newCity);
	RespuestaDTO deleteCity(Long id);
	CityDTO getInformation(Long id);

}
