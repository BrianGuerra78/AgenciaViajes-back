package mx.com.basantader.AgenciaViajeTA.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AirlineDTO {
	
	private String name;
    private Long idAirline;
    
    private List<FlightDTO> lstFlights;

}
