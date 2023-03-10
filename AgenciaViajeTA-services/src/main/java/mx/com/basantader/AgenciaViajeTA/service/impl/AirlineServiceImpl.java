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
import mx.com.basantader.AgenciaViajeTA.repository.AirlineRepository;
import mx.com.basantader.AgenciaViajeTA.repository.CityRepository;
import mx.com.basantader.AgenciaViajeTA.service.AirlineService;

@Service
public class AirlineServiceImpl implements AirlineService {
	private static final Logger log = LoggerFactory.getLogger(AirlineServiceImpl.class);
	
	 @Autowired
	    private AirlineRepository airlineRepository;
	 @Autowired
	    private CityRepository cityRepository;
	   
	   

		@Override
		public List<AirlineDTO> getAirlines() {
			List<AirlineEntity> lstAirlines = airlineRepository.findAll();
			List<AirlineDTO> lstReturn = new ArrayList<AirlineDTO>();
			List<FlightDTO> lstFlights = new ArrayList<FlightDTO>();
			AirlineDTO aux;
			for(AirlineEntity airline: lstAirlines) {
				aux = new AirlineDTO();
				aux.setIdAirline(airline.getIdAirline());
				aux.setName(airline.getName());
				aux.setLstFlights(lstFlights);
				lstReturn.add(aux);
				}
			   return lstReturn;
		}
		
		public List<AirlineDTO> searchAirlines(String name){
			List<AirlineEntity> lstAirlines = airlineRepository.findByName(name);
			if(lstAirlines.isEmpty()) {
				
				 throw new BusinessException(Constantes.CODIGO_ERROR_VACIO);
			}
			List<AirlineDTO> lstReturn = new ArrayList<AirlineDTO>();
			List<FlightDTO> lstFlights = new ArrayList<FlightDTO>();
			AirlineDTO aux;
			for(AirlineEntity airline: lstAirlines) {
				aux = new AirlineDTO();
				aux.setIdAirline(airline.getIdAirline());
				aux.setName(airline.getName());
				aux.setLstFlights(lstFlights);
				lstReturn.add(aux);
				}
			   return lstReturn;
	    }
		
		@Override
		@Transactional
		public AirlineDTO createAirline(AirlineDTO newAirline) {
			List<AirlineEntity> lstBusqueda = airlineRepository.findByName(newAirline.getName());
			if(!lstBusqueda.isEmpty()) {
				
				 throw new BusinessException(Constantes.CODIGO_ERROR_AIRLINE_EXISTS);
			}
			AirlineEntity  newRegister = new AirlineEntity();
			
			newRegister.setName(newAirline.getName());
			airlineRepository.save(newRegister);
			newAirline.setIdAirline(newRegister.getIdAirline());
			return newAirline;
		}
		
		@Override
		@Transactional
		public AirlineDTO updateAirline(AirlineDTO newAirline) {
			
			Optional<AirlineEntity> register = airlineRepository.findById(newAirline.getIdAirline());
			if (!register.isPresent()) {
				throw new BusinessException(Constantes.CODIGO_ERROR_AIRLINE_NO_EXISTS);
			}
			
			AirlineEntity  newRegister = register.get();
			newRegister.setName(newAirline.getName());
			airlineRepository.save(newRegister);
			newAirline.setIdAirline(newRegister.getIdAirline());
			return newAirline;
		}
		
		@Override
		@Transactional
		public RespuestaDTO deleteAirline(Long id) {
			RespuestaDTO respuesta= new RespuestaDTO();
			Optional<AirlineEntity> registro = airlineRepository.findById(id);
			if (!registro.isPresent()) {
				throw new BusinessException(Constantes.CODIGO_ERROR_AIRLINE_NO_EXISTS);
			}
			airlineRepository.deleteById(id);
			respuesta.setMensajeRespuesta("Registro eliminado correctamente");
			return respuesta;
		}
		
		@Override
		public AirlineDTO getInformation(Long id){
			AirlineDTO retorno = new AirlineDTO();
			FlightDTO auxFlight;
			List<FlightDTO> lstFlights = new ArrayList<FlightDTO>();
			Optional<AirlineEntity> registro = airlineRepository.findById(id);
			if (!registro.isPresent()) {
				throw new BusinessException(Constantes.CODIGO_ERROR_AIRLINE_NO_EXISTS);
				}
			retorno.setIdAirline(registro.get().getIdAirline());
			retorno.setName(registro.get().getName());
			if(registro.get().getFligth() != null) {
				for(FlightEntity flight: registro.get().getFligth()) {
					auxFlight= new FlightDTO();
					auxFlight.setIdFlight(flight.getIdFlight());
					auxFlight.setCode(flight.getCode());
					auxFlight.setIdCitieOrigin(flight.getCityOrigin().getIdCitie());
					auxFlight.setIdCitieDestination(flight.getCityDestination().getIdCitie());
					auxFlight.setDepartureTime(flight.getDepartureTime());
					auxFlight.setArrivalTime(flight.getArrivalTime());
					auxFlight.setCost(flight.getCost());
					auxFlight.setStatus(flight.getStatus());
					Optional<CityEntity> CityOrigin = cityRepository.findById(flight.getCityOrigin().getIdCitie());
		            CityDTO cityO = new CityDTO();
		            cityO.setIdCity(CityOrigin.get().getIdCitie());
		            cityO.setName(CityOrigin.get().getName());
		            cityO.setContinen_name(CityOrigin.get().getContinentName());
		            auxFlight.setFkIdCitieOrigin(cityO);
		            Optional<CityEntity> CityD = cityRepository.findById(flight.getCityDestination().getIdCitie());
		            CityDTO cityD = new CityDTO();
		            cityD.setIdCity(CityD.get().getIdCitie());
		            cityD.setName(CityD.get().getName());
		            cityD.setContinen_name(CityD.get().getContinentName());
		            auxFlight.setFkIdCitieDestination(cityD);
					lstFlights.add(auxFlight);
				}
				retorno.setLstFlights(lstFlights);
			}
			return retorno;
			
	    }
}
		
		

