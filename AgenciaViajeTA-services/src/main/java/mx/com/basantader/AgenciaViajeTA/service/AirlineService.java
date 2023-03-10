package mx.com.basantader.AgenciaViajeTA.service;

import java.util.List;



import mx.com.basantader.AgenciaViajeTA.dto.AirlineDTO;
import mx.com.basantader.AgenciaViajeTA.dto.RespuestaDTO;

public interface AirlineService {
	List<AirlineDTO> getAirlines();
	List<AirlineDTO> searchAirlines(String name);
	AirlineDTO createAirline(AirlineDTO newAirline);
	AirlineDTO updateAirline(AirlineDTO newAirline);
	RespuestaDTO deleteAirline(Long id);
	AirlineDTO getInformation(Long id);

}
