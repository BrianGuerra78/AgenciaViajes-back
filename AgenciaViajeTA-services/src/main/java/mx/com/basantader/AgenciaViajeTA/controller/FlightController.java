package mx.com.basantader.AgenciaViajeTA.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import mx.com.basantader.AgenciaViajeTA.commons.Constantes;
import mx.com.basantader.AgenciaViajeTA.dto.AirlineDTO;
import mx.com.basantader.AgenciaViajeTA.dto.CityDTO;
import mx.com.basantader.AgenciaViajeTA.dto.FlightDTO;
import mx.com.basantader.AgenciaViajeTA.dto.RespuestaDTO;
import mx.com.basantader.AgenciaViajeTA.exceptions.BusinessException;
import mx.com.basantader.AgenciaViajeTA.service.AirlineService;
import mx.com.basantader.AgenciaViajeTA.service.FlightService;

@RestController
@RequestMapping("/API")
@Api(value = "Agencia de viajes")
public class FlightController {
	private static final Logger log = LoggerFactory.getLogger(FlightController.class);

    @Autowired
    private FlightService flightService;
    
    @GetMapping (value="/getFlights")
    public List<FlightDTO> getFlights(){
    	return flightService.getFlights();
    }
    
    
    @GetMapping(value="/searchFlightsAirline")
    public List<FlightDTO> searchFlightsAirline(@RequestParam(required=true) String airline){
    	return flightService.searchFlightAirline(airline);
    }
    @GetMapping(value="/searchFlightsCityOrigin")
    public List<FlightDTO> searchFlightsCityOrigin(@RequestParam(required=true) String cityOrigin){
    	return flightService.searchFlightCityOrigin(cityOrigin);
    }
    @GetMapping(value="/searchFlightsCityDestination")
    public List<FlightDTO> searchFlightsCityDestination(@RequestParam(required=true) String cityDestination){
    	return flightService.searchFlightCityDestination(cityDestination);
    }
    
    @PostMapping(value= "/createFlight")
    public FlightDTO createFlight(@RequestBody FlightDTO newRegister) {
       if(newRegister.getCode()==null || newRegister.getCode().isEmpty()) {
    		
    		throw new BusinessException(47);
    	}
    	if(newRegister.getDepartureTime()==null ) {
    		throw new BusinessException(51);
    	}
        if(newRegister.getArrivalTime()==null ) {
    		
    		throw new BusinessException(52);
    	}
    	if(newRegister.getCost()==null || newRegister.getCost().equals(0)) {
    		throw new BusinessException(53);
    	}
    	
    	
    	return flightService.createFlight(newRegister);
    	
    }
    
    @PostMapping(value= "/updateFlight/{id}")
    public FlightDTO updateFlight(@PathVariable Long id, @RequestBody FlightDTO newFlight) {
    	 if(newFlight.getCode()==null || newFlight.getCode().isEmpty()) {
     		
     		throw new BusinessException(47);
     	}
     	if(newFlight.getDepartureTime()==null ) {
     		throw new BusinessException(51);
     	}
         if(newFlight.getArrivalTime()==null ) {
     		
     		throw new BusinessException(52);
     	}
     	if(newFlight.getCost()==null || newFlight.getCost().equals(0)) {
     		throw new BusinessException(53);
     	}
     	
    	newFlight.setIdFlight(id);
    	return flightService.updateFlight(newFlight);
    }
    
    @DeleteMapping(value = "/deleteFlight/{id}")
    public RespuestaDTO deleteFlight(@PathVariable Long id) {
    	return flightService.deleteFlight(id);
    }
    
    @PostMapping(value = "/updateStatusFlight/{id}")
    public FlightDTO updateStatusFlight(@PathVariable Long id,  @RequestBody FlightDTO newFlight) {
    	newFlight.setIdFlight(id);
    	return flightService.updateStatus(newFlight);
    }
    
    @GetMapping(value="/Flight/getInformation/{id}")
    public FlightDTO getInformation(@PathVariable Long id) {
    	return flightService.getInformation(id);
    }

}
