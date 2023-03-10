package mx.com.basantader.AgenciaViajeTA.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightDTO {
	
	  private Long idFlight;
	    private CityDTO fkIdCitieOrigin;
	    private CityDTO fkIdCitieDestination;
	    private AirlineDTO fkIdAirline;
	    private Integer status;
	    private Double cost;
	    private LocalDateTime departureTime;
	    private LocalDateTime arrivalTime;
	    private String code;
	    
	    private Long IdCitieOrigin;
	    private Long IdCitieDestination;
	    private Long IdAirline;
	    
	    private List<ReservationsDTO> lstReservations;

}
