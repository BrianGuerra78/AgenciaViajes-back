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
public class CityDTO {
	
	private String name;

    private String continen_name;
    
    private Long idCity;
    
    private List<FlightDTO> lstFlights;

}
