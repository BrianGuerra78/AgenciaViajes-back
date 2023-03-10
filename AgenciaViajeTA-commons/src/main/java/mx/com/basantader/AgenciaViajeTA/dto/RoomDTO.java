package mx.com.basantader.AgenciaViajeTA.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomDTO {

	private Long idRoom;
	private String nameRoom;
	private String description;
	private Long noPeople;
	private Double price;
	private Integer status;
	private String codeRoom;

	private HotelDTO idHotel;

	private List<ReservationsDTO> lstResrvations;

}
