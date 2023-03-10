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
import mx.com.basantader.AgenciaViajeTA.dto.RespuestaDTO;
import mx.com.basantader.AgenciaViajeTA.exceptions.BusinessException;
import mx.com.basantader.AgenciaViajeTA.service.AirlineService;
import mx.com.basantader.AgenciaViajeTA.service.CityService;

@RestController
@RequestMapping("/API")
@Api(value = "Agencia de viajes")
public class AirlineController {
	
	private static final Logger log = LoggerFactory.getLogger(AirlineController.class);

    @Autowired
    private AirlineService airlineService;
    
    @GetMapping (value="/getAirlines")
    public List<AirlineDTO> getCities(){
    	return airlineService.getAirlines();
    }
    
    @GetMapping(value="/searchAirlines")
    public List<AirlineDTO> searchAirlines(@RequestParam(required=false) String name){
    	return airlineService.searchAirlines(name);
    }
    
    @PostMapping(value= "/createAirline")
    public AirlineDTO createAirline(@RequestBody AirlineDTO newRegister) {

    	if(newRegister.getName()==null || newRegister.getName().isEmpty()) {
    		
    		throw new BusinessException(6);
    	}
    	
    	return airlineService.createAirline(newRegister);
    }
    
    @PostMapping(value= "/updateAirline/{id}")
    public AirlineDTO updateAirline(@PathVariable Long id, @RequestBody AirlineDTO newAirline) {
    if(newAirline.getName()==null || newAirline.getName().isEmpty()) {
    		
    		throw new BusinessException(6);
    	}
    	newAirline.setIdAirline(id);
    	return airlineService.updateAirline(newAirline);
    }
    
    @DeleteMapping(value = "/deleteAirline/{id}")
    public RespuestaDTO deleteAirline(@PathVariable Long id) {
    	return airlineService.deleteAirline(id);
    }
    
    @GetMapping(value="/getInformation/{id}")
    public AirlineDTO getInformation(@PathVariable Long id) {
    	return airlineService.getInformation(id);
    }


}
