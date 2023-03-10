package mx.com.basantader.AgenciaViajeTA.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.basantader.AgenciaViajeTA.commons.Constantes;
import mx.com.basantader.AgenciaViajeTA.dto.AirlineDTO;
import mx.com.basantader.AgenciaViajeTA.dto.CityDTO;
import mx.com.basantader.AgenciaViajeTA.dto.FlightDTO;
import mx.com.basantader.AgenciaViajeTA.dto.RespuestaDTO;
import mx.com.basantader.AgenciaViajeTA.exceptions.BusinessException;
import mx.com.basantader.AgenciaViajeTA.model.AirlineEntity;
import mx.com.basantader.AgenciaViajeTA.model.CityEntity;
import mx.com.basantader.AgenciaViajeTA.model.FlightEntity;
import mx.com.basantader.AgenciaViajeTA.repository.CityRepository;
import mx.com.basantader.AgenciaViajeTA.repository.FlightRepository;
import mx.com.basantader.AgenciaViajeTA.service.CityService;

@Service
public class CityServiceImpl implements CityService{
	
private static final Logger log = LoggerFactory.getLogger(CityServiceImpl.class);
    
    @Autowired
    private CityRepository cityRepository;
   
   

	@Override
	public List<CityDTO> getCities() {
		List<CityEntity> lstCities = cityRepository.findAll();
		List<CityDTO> lstReturn = new ArrayList<CityDTO>();
		List<FlightDTO> lstFlights = new ArrayList<FlightDTO>();
		CityDTO aux;
		for(CityEntity city: lstCities) {
			aux = new CityDTO();
			aux.setIdCity(city.getIdCitie());
			aux.setName(city.getName());
			aux.setContinen_name(city.getContinentName());
			aux.setLstFlights(lstFlights);
			lstReturn.add(aux);
			}
		   return lstReturn;
	}
	
	public List<CityDTO> searchCities(String name, String continentName){
		List<CityEntity> lstCities = cityRepository.findByNameOrContinentName(name, continentName);
		if(lstCities.isEmpty()) {
			
			 throw new BusinessException(Constantes.CODIGO_ERROR_VACIO);
		}
		List<CityDTO> lstReturn = new ArrayList<CityDTO>();
		List<FlightDTO> lstFlights = new ArrayList<FlightDTO>();
		CityDTO aux;
		for(CityEntity city: lstCities) {
			aux = new CityDTO();
			aux.setIdCity(city.getIdCitie());
			aux.setName(city.getName());
			aux.setContinen_name(city.getContinentName());
			aux.setLstFlights(lstFlights);
			lstReturn.add(aux);
			}
		   return lstReturn;
    }
	
	@Override
	@Transactional
	public CityDTO createCity(CityDTO newCity) {
		List<CityEntity> lstBusqueda = cityRepository.findByNameAndContinentName(newCity.getName(), newCity.getContinen_name());
		if(!lstBusqueda.isEmpty()) {
			
			 throw new BusinessException(Constantes.CODIGO_ERROR_CIUDAD_EXISTE);
		}
		CityEntity  newRegister = new CityEntity();
		newRegister.setName(newCity.getName());
		newRegister.setContinentName(newCity.getContinen_name());
		cityRepository.save(newRegister);
		newCity.setIdCity(newRegister.getIdCitie());
		return newCity;
	}
	
	@Override
	@Transactional
	public RespuestaDTO deleteCity(Long id) {
		RespuestaDTO respuesta= new RespuestaDTO();
		Optional<CityEntity> registro = cityRepository.findById(id);
		if (!registro.isPresent()) {
			throw new BusinessException(Constantes.CODIGO_ERROR_CIUDAD_NO_EXISTE);
		}
		cityRepository.deleteById(id);
		respuesta.setMensajeRespuesta("Registro eliminado correctamente");
		return respuesta;
	}
	
	@Override
	@Transactional
	public CityDTO updateCity(CityDTO newCity) {
		
		Optional<CityEntity> register = cityRepository.findById(newCity.getIdCity());
		if (!register.isPresent()) {
			throw new BusinessException(Constantes.CODIGO_ERROR_CIUDAD_NO_EXISTE);
		}
		
		CityEntity  newRegister = register.get();
		newRegister.setName(newCity.getName());
		newRegister.setContinentName(newCity.getContinen_name());
		cityRepository.save(newRegister);
		newCity.setIdCity(newRegister.getIdCitie());
		return newCity;
	}
	

	@Override
	public CityDTO getInformation(Long id){
		CityDTO retorno = new CityDTO();
		Optional<CityEntity> registro = cityRepository.findById(id);
		if (!registro.isPresent()) {
			throw new BusinessException(Constantes.CODIGO_ERROR_CIUDAD_NO_EXISTE);
			}
		retorno.setIdCity(registro.get().getIdCitie());
		retorno.setName(registro.get().getName());
		retorno.setContinen_name(registro.get().getContinentName());
		
		return retorno;
	}
	
   

}
