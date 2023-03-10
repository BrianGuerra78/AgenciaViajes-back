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
import mx.com.basantader.AgenciaViajeTA.service.CityService;

@RestController
@RequestMapping("/API")
@Api(value = "Agencia de viajes")
public class CityController {
	private static final Logger log = LoggerFactory.getLogger(CityController.class);

    @Autowired
    private CityService cityService;
    
    @GetMapping (value="/getCities")
    public List<CityDTO> getCities(){
    	return cityService.getCities();
    }
    
    @GetMapping(value="/searchCities")
    public List<CityDTO> searchCities(@RequestParam(required=false) String name,@RequestParam(required=false) String continentName){
    	return cityService.searchCities(name, continentName);
    }
    
    @PostMapping(value= "/createCity")
    public CityDTO createCity(@RequestBody CityDTO newRegister) {

    	if(newRegister.getName()==null || newRegister.getName().isEmpty()) {
    		
    		throw new BusinessException(6);
    	}
    	if(newRegister.getContinen_name()==null || newRegister.getContinen_name().trim().isEmpty()) {
    		throw new BusinessException(45);
    	}
    	return cityService.createCity(newRegister);
    }
    
    @DeleteMapping(value = "/deleteCity/{id}")
    public RespuestaDTO deleteCity(@PathVariable Long id) {
    	return cityService.deleteCity(id);
    }
    
    @PostMapping(value= "/updateCity/{id}")
    public CityDTO updateCity(@PathVariable Long id, @RequestBody CityDTO newCity) {
       if(newCity.getName()==null || newCity.getName().isEmpty()) {
    		
    		throw new BusinessException(6);
    	}
    	if(newCity.getContinen_name()==null || newCity.getContinen_name().trim().isEmpty()) {
    		throw new BusinessException(45);
    	}
    	newCity.setIdCity(id);
    	return cityService.updateCity(newCity);
    }
    
    @GetMapping(value="/City/getInformation/{id}")
    public CityDTO getInformation(@PathVariable Long id) {
    	return cityService.getInformation(id);
    }

}
