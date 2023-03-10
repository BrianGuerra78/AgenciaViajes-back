package mx.com.basantader.AgenciaViajeTA.service;

import java.util.List;

import mx.com.basantader.AgenciaViajeTA.dto.RespuestaDTO;
import mx.com.basantader.AgenciaViajeTA.dto.RoomDTO;

public interface RoomService {

	List<RoomDTO> getRooms();

	RoomDTO getRoom(Long idRoom);
	
	List<RoomDTO> getRoomByIdHotel(Long idHotel);

	List<RoomDTO> getRoomByName(String nameRoom);
	
	List<RoomDTO> getRoomByCode(String codeRoom);
	
	List<RoomDTO> getRoomByCamp(Long idRoom, String nameRoom, String codeRoom);

	RoomDTO createRoom(Long idHotel, RoomDTO request);

	RoomDTO updateRoom(RoomDTO request);

	RespuestaDTO deleteRoom(Long idRoom);
	
	RoomDTO updateRoomStatus(RoomDTO request);

}
