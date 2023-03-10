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
import mx.com.basantader.AgenciaViajeTA.dto.ReservationsDTO;
import mx.com.basantader.AgenciaViajeTA.dto.RespuestaDTO;
import mx.com.basantader.AgenciaViajeTA.exceptions.BusinessException;
import mx.com.basantader.AgenciaViajeTA.model.AirlineEntity;
import mx.com.basantader.AgenciaViajeTA.model.CityEntity;
import mx.com.basantader.AgenciaViajeTA.model.FlightEntity;
import mx.com.basantader.AgenciaViajeTA.model.RoomEntity;
import mx.com.basantader.AgenciaViajeTA.repository.AirlineRepository;
import mx.com.basantader.AgenciaViajeTA.repository.CityRepository;
import mx.com.basantader.AgenciaViajeTA.repository.FlightRepository;
import mx.com.basantader.AgenciaViajeTA.service.FlightService;

@Service
public class FlightServiceImpl implements FlightService{
	
	private static final Logger log = LoggerFactory.getLogger(FlightServiceImpl.class);
	
	 @Autowired
	    private FlightRepository flightRepository;
	 @Autowired
	    private AirlineRepository airlineRepository;
	 @Autowired
	    private CityRepository cityRepository;
	
	 
	 @Override
		public List<FlightDTO> getFlights() {
			List<FlightEntity> lstFlights = flightRepository.findAll();
			List<FlightDTO> lstReturn = new ArrayList<FlightDTO>();
			List<ReservationsDTO> lstReservations = new ArrayList<ReservationsDTO>();
			FlightDTO aux;
			for(FlightEntity flight: lstFlights) {
				aux = new FlightDTO();
				aux.setIdFlight(flight.getIdFlight());
				aux.setArrivalTime(flight.getArrivalTime());
				aux.setDepartureTime(flight.getDepartureTime());
				aux.setCost(flight.getCost());
				aux.setStatus(flight.getStatus());
				aux.setCode(flight.getCode());
				aux.setIdCitieOrigin(flight.getCityOrigin().getIdCitie());
				aux.setIdCitieDestination(flight.getCityDestination().getIdCitie());
				aux.setIdAirline(flight.getAirline().getIdAirline());;
				aux.setLstReservations(lstReservations);
				Optional<CityEntity> CityOrigin = cityRepository.findById(flight.getCityOrigin().getIdCitie());
	            CityDTO cityO = new CityDTO();
	            cityO.setIdCity(CityOrigin.get().getIdCitie());
	            cityO.setName(CityOrigin.get().getName());
	            cityO.setContinen_name(CityOrigin.get().getContinentName());
	            aux.setFkIdCitieOrigin(cityO);
	            Optional<CityEntity> CityD = cityRepository.findById(flight.getCityDestination().getIdCitie());
	            CityDTO cityD = new CityDTO();
	            cityD.setIdCity(CityD.get().getIdCitie());
	            cityD.setName(CityD.get().getName());
	            cityD.setContinen_name(CityD.get().getContinentName());
	            aux.setFkIdCitieDestination(cityD);
	            Optional<AirlineEntity> airline = airlineRepository.findById(flight.getAirline().getIdAirline());
	            AirlineDTO airlineDto = new AirlineDTO();
	            airlineDto.setIdAirline(airline.get().getIdAirline());
	            airlineDto.setName(airline.get().getName());
	            aux.setFkIdAirline(airlineDto);
				lstReturn.add(aux);
				}
			   return lstReturn;
		}
	 @Override
	 public List<FlightDTO> searchFlightAirline(String airline){
		 List<FlightEntity> lstFlights = flightRepository.findAll();
		 
			List<FlightDTO> lstReturn = new ArrayList<FlightDTO>();
			List<ReservationsDTO> lstReservations = new ArrayList<ReservationsDTO>();
			FlightDTO aux;
			for(FlightEntity flight: lstFlights) {
				aux = new FlightDTO();
				aux.setIdFlight(flight.getIdFlight());
				aux.setArrivalTime(flight.getArrivalTime());
				aux.setDepartureTime(flight.getDepartureTime());
				aux.setCost(flight.getCost());
				aux.setStatus(flight.getStatus());
				aux.setCode(flight.getCode());
				aux.setIdCitieOrigin(flight.getCityOrigin().getIdCitie());
				aux.setIdCitieDestination(flight.getCityDestination().getIdCitie());
				aux.setIdAirline(flight.getAirline().getIdAirline());
				aux.setLstReservations(lstReservations);
				Optional<CityEntity> CityOrigin = cityRepository.findById(flight.getCityOrigin().getIdCitie());
	            CityDTO cityO = new CityDTO();
	            cityO.setIdCity(CityOrigin.get().getIdCitie());
	            cityO.setName(CityOrigin.get().getName());
	            cityO.setContinen_name(CityOrigin.get().getContinentName());
	            aux.setFkIdCitieOrigin(cityO);
	            Optional<CityEntity> CityD = cityRepository.findById(flight.getCityDestination().getIdCitie());
	            CityDTO cityD = new CityDTO();
	            cityD.setIdCity(CityD.get().getIdCitie());
	            cityD.setName(CityD.get().getName());
	            cityD.setContinen_name(CityD.get().getContinentName());
	            aux.setFkIdCitieDestination(cityD);
	            Optional<AirlineEntity> airlines = airlineRepository.findById(flight.getAirline().getIdAirline());
	            AirlineDTO airlineDto = new AirlineDTO();
	            airlineDto.setIdAirline(airlines.get().getIdAirline());
	            airlineDto.setName(airlines.get().getName());
	            aux.setFkIdAirline(airlineDto);
				Optional<AirlineEntity> ltsAirline = airlineRepository.findById(flight.getAirline().getIdAirline());
				if(ltsAirline.get().getName().equals(airline))
				{
					lstReturn.add(aux);
				}
					
				}
			   if(lstReturn.isEmpty())
			   {
				   throw new BusinessException(Constantes.CODIGO_ERROR_NOMBRE_VACIO);
			   }
			   return lstReturn;
		 
	 }
	 
	 @Override
	 public List<FlightDTO> searchFlightCityOrigin(String cityOrigin){
		 List<FlightEntity> lstFlights = flightRepository.findAll();
			List<FlightDTO> lstReturn = new ArrayList<FlightDTO>();
			List<ReservationsDTO> lstReservations = new ArrayList<ReservationsDTO>();
			FlightDTO aux;
			for(FlightEntity flight: lstFlights) {
				aux = new FlightDTO();
				aux.setIdFlight(flight.getIdFlight());
				aux.setArrivalTime(flight.getArrivalTime());
				aux.setDepartureTime(flight.getDepartureTime());
				aux.setCost(flight.getCost());
				aux.setStatus(flight.getStatus());
				aux.setCode(flight.getCode());
				aux.setIdCitieOrigin(flight.getCityOrigin().getIdCitie());
				aux.setIdCitieDestination(flight.getCityDestination().getIdCitie());
				aux.setIdAirline(flight.getAirline().getIdAirline());
				aux.setLstReservations(lstReservations);
				Optional<CityEntity> CityOrigin = cityRepository.findById(flight.getCityOrigin().getIdCitie());
	            CityDTO cityO = new CityDTO();
	            cityO.setIdCity(CityOrigin.get().getIdCitie());
	            cityO.setName(CityOrigin.get().getName());
	            cityO.setContinen_name(CityOrigin.get().getContinentName());
	            aux.setFkIdCitieOrigin(cityO);
	            Optional<CityEntity> CityD = cityRepository.findById(flight.getCityDestination().getIdCitie());
	            CityDTO cityD = new CityDTO();
	            cityD.setIdCity(CityD.get().getIdCitie());
	            cityD.setName(CityD.get().getName());
	            cityD.setContinen_name(CityD.get().getContinentName());
	            aux.setFkIdCitieDestination(cityD);
	            Optional<AirlineEntity> airline = airlineRepository.findById(flight.getAirline().getIdAirline());
	            AirlineDTO airlineDto = new AirlineDTO();
	            airlineDto.setIdAirline(airline.get().getIdAirline());
	            airlineDto.setName(airline.get().getName());
	            aux.setFkIdAirline(airlineDto);
				Optional<CityEntity> ltsCity = cityRepository.findById(flight.getCityOrigin().getIdCitie());
				if(ltsCity.get().getName().equals(cityOrigin))
				{
					lstReturn.add(aux);
				}
					
				}
			   if(lstReturn.isEmpty())
			   {
				   throw new BusinessException(Constantes.CODIGO_ERROR_NOMBRE_VACIO);
			   }
			   return lstReturn;
		 
	 }
	 @Override
	 public List<FlightDTO> searchFlightCityDestination(String cityDestination){
		 List<FlightEntity> lstFlights = flightRepository.findAll();
			List<FlightDTO> lstReturn = new ArrayList<FlightDTO>();
			List<ReservationsDTO> lstReservations = new ArrayList<ReservationsDTO>();
			FlightDTO aux;
			for(FlightEntity flight: lstFlights) {
				aux = new FlightDTO();
				aux.setIdFlight(flight.getIdFlight());
				aux.setArrivalTime(flight.getArrivalTime());
				aux.setDepartureTime(flight.getDepartureTime());
				aux.setCost(flight.getCost());
				aux.setStatus(flight.getStatus());
				aux.setCode(flight.getCode());
				aux.setIdCitieOrigin(flight.getCityOrigin().getIdCitie());
				aux.setIdCitieDestination(flight.getCityDestination().getIdCitie());
				aux.setIdAirline(flight.getAirline().getIdAirline());
				aux.setLstReservations(lstReservations);
				Optional<CityEntity> CityOrigin = cityRepository.findById(flight.getCityOrigin().getIdCitie());
	            CityDTO cityO = new CityDTO();
	            cityO.setIdCity(CityOrigin.get().getIdCitie());
	            cityO.setName(CityOrigin.get().getName());
	            cityO.setContinen_name(CityOrigin.get().getContinentName());
	            aux.setFkIdCitieOrigin(cityO);
	            Optional<CityEntity> CityD = cityRepository.findById(flight.getCityDestination().getIdCitie());
	            CityDTO cityD = new CityDTO();
	            cityD.setIdCity(CityD.get().getIdCitie());
	            cityD.setName(CityD.get().getName());
	            cityD.setContinen_name(CityD.get().getContinentName());
	            aux.setFkIdCitieDestination(cityD);
	            Optional<AirlineEntity> airline = airlineRepository.findById(flight.getAirline().getIdAirline());
	            AirlineDTO airlineDto = new AirlineDTO();
	            airlineDto.setIdAirline(airline.get().getIdAirline());
	            airlineDto.setName(airline.get().getName());
	            aux.setFkIdAirline(airlineDto);
				Optional<CityEntity> ltsCity = cityRepository.findById(flight.getCityDestination().getIdCitie());
				if(ltsCity.get().getName().equals(cityDestination))
				{
					lstReturn.add(aux);
				}
					
				}
			   if(lstReturn.isEmpty())
			   {
				   throw new BusinessException(Constantes.CODIGO_ERROR_NOMBRE_VACIO);
			   }
			   return lstReturn;
		 
	 }
	 
	 @Override
		@Transactional
		public FlightDTO createFlight(FlightDTO newFlight) {
			List<FlightEntity> lstBusqueda = flightRepository.findByCode(newFlight.getCode());
			if(!lstBusqueda.isEmpty()) {
				
				 throw new BusinessException(Constantes.CODIGO_ERROR_FLIGHT_EXISTS );
			}
			FlightEntity  newRegister = new FlightEntity();
			newRegister.setArrivalTime(newFlight.getArrivalTime());
			newRegister.setDepartureTime(newFlight.getDepartureTime());
			newRegister.setCode(newFlight.getCode());
			newRegister.setCost(newFlight.getCost());
			newRegister.setStatus(1);
			
			Optional<CityEntity> cityOrigin = cityRepository.findById(newFlight.getFkIdCitieOrigin().getIdCity());
			if (!cityOrigin.isPresent()) {
				throw new BusinessException(Constantes.CODIGO_ERROR_CIUDAD_NO_EXISTE);
			}
			newRegister.setCityOrigin(cityOrigin.get());
			
			Optional<CityEntity> cityDestination = cityRepository.findById(newFlight.getFkIdCitieDestination().getIdCity());
			if (!cityDestination.isPresent()) {
				throw new BusinessException(Constantes.CODIGO_ERROR_CIUDAD_NO_EXISTE);
			}
			newRegister.setCityDestination(cityDestination.get());
			
			Optional<AirlineEntity> airline = airlineRepository.findById(newFlight.getFkIdAirline().getIdAirline());
			if (!airline.isPresent()) {
				throw new BusinessException(Constantes.CODIGO_ERROR_AIRLINE_NO_EXISTS);
			}
			newRegister.setAirline(airline.get());
			
			flightRepository.save(newRegister);
			newFlight.setIdFlight(newRegister.getIdFlight());
			return newFlight;
		}
	 
	    @Override
		@Transactional
		public FlightDTO updateFlight(FlightDTO newFlight) {
			
			Optional<FlightEntity> register = flightRepository.findById(newFlight.getIdFlight());
			if (!register.isPresent()) {
				throw new BusinessException(Constantes.CODIGO_ERROR_FLIGHT_NO_EXISTS);
			}
			
			FlightEntity  newRegister = register.get();
			newRegister.setArrivalTime(newFlight.getArrivalTime());
			newRegister.setDepartureTime(newFlight.getDepartureTime());
			newRegister.setCode(newFlight.getCode());
			newRegister.setCost(newFlight.getCost());
			Optional<CityEntity> cityOrigin = cityRepository.findById(newFlight.getFkIdCitieOrigin().getIdCity());
			if (!cityOrigin.isPresent()) {
				throw new BusinessException(Constantes.CODIGO_ERROR_CIUDAD_NO_EXISTE);
			}
			newRegister.setCityOrigin(cityOrigin.get());
			
			Optional<CityEntity> cityDestination = cityRepository.findById(newFlight.getFkIdCitieDestination().getIdCity());
			if (!cityDestination.isPresent()) {
				throw new BusinessException(Constantes.CODIGO_ERROR_CIUDAD_NO_EXISTE);
			}
			newRegister.setCityDestination(cityDestination.get());
			
			Optional<AirlineEntity> airline = airlineRepository.findById(newFlight.getFkIdAirline().getIdAirline());
			if (!airline.isPresent()) {
				throw new BusinessException(Constantes.CODIGO_ERROR_AIRLINE_NO_EXISTS);
			}
			newRegister.setAirline(airline.get());
			flightRepository.save(newRegister);
			newFlight.setIdFlight(newRegister.getIdFlight());
			return newFlight;
		}
	 

		@Override
		@Transactional
		public RespuestaDTO deleteFlight(Long id) {
			RespuestaDTO respuesta= new RespuestaDTO();
			Optional<FlightEntity> registro = flightRepository.findById(id);
			if (!registro.isPresent()) {
				throw new BusinessException(Constantes.CODIGO_ERROR_FLIGHT_NO_EXISTS);
			}
			if(registro.get().getReservationflight().isEmpty())
			{
			flightRepository.deleteById(id);
			respuesta.setMensajeRespuesta("Registro eliminado correctamente");
			}
			else {
				respuesta.setMensajeRespuesta("El vuelo tiene reservaciones registradas");
			}
			
			
			return respuesta;
		}
		
		
		@Override
		@Transactional
		
		
		
		public FlightDTO getInformation(Long id){
		    FlightDTO retorno = new    FlightDTO();
		    List<FlightDTO> lstReturn = new ArrayList<FlightDTO>();
		    List<ReservationsDTO> lstReservations = new ArrayList<ReservationsDTO>();
			Optional<FlightEntity> registro = flightRepository.findById(id);
			if (!registro.isPresent()) {
				throw new BusinessException(Constantes.CODIGO_ERROR_FLIGHT_NO_EXISTS);
				}
			retorno.setIdFlight(registro.get().getIdFlight());
			retorno.setCode(registro.get().getCode());
			retorno.setCost(registro.get().getCost());
			retorno.setDepartureTime(registro.get().getDepartureTime());
			retorno.setArrivalTime(registro.get().getArrivalTime());
			retorno.setStatus(registro.get().getStatus());
			retorno.setIdAirline(registro.get().getAirline().getIdAirline());
			retorno.setIdCitieDestination(registro.get().getCityDestination().getIdCitie());
			retorno.setIdCitieOrigin(registro.get().getCityOrigin().getIdCitie());
			retorno.setLstReservations(lstReservations);
			Optional<CityEntity> CityOrigin = cityRepository.findById(registro.get().getCityOrigin().getIdCitie());
            CityDTO cityO = new CityDTO();
            cityO.setIdCity(CityOrigin.get().getIdCitie());
            cityO.setName(CityOrigin.get().getName());
            cityO.setContinen_name(CityOrigin.get().getContinentName());
            retorno.setFkIdCitieOrigin(cityO);
            Optional<CityEntity> CityD = cityRepository.findById(registro.get().getCityDestination().getIdCitie());
            CityDTO cityD = new CityDTO();
            cityD.setIdCity(CityD.get().getIdCitie());
            cityD.setName(CityD.get().getName());
            cityD.setContinen_name(CityD.get().getContinentName());
            retorno.setFkIdCitieDestination(cityD);
            Optional<AirlineEntity> airline = airlineRepository.findById(registro.get().getAirline().getIdAirline());
            AirlineDTO airlineDto = new AirlineDTO();
            airlineDto.setIdAirline(airline.get().getIdAirline());
            airlineDto.setName(airline.get().getName());
            retorno.setFkIdAirline(airlineDto);
			//lstReturn.add(aux);
			
			return retorno;
		}
		
		@Override
		@Transactional
            public FlightDTO updateStatus(FlightDTO newFlight) {
			
			Optional<FlightEntity> register = flightRepository.findById(newFlight.getIdFlight());
			if (!register.isPresent()) {
				throw new BusinessException(Constantes.CODIGO_ERROR_FLIGHT_NO_EXISTS);
			}
			
			FlightEntity  newRegister = register.get();
			
				newRegister.setStatus(newFlight.getStatus());
	        
			flightRepository.save(newRegister);
			newFlight.setIdFlight(newRegister.getIdFlight());
			return newFlight;
		}
		
		
		
		
		
	   
	   

}
