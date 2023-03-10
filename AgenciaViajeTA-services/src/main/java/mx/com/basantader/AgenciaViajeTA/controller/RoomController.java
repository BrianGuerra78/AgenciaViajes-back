package mx.com.basantader.AgenciaViajeTA.controller;

import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import mx.com.basantader.AgenciaViajeTA.dto.RespuestaDTO;
import mx.com.basantader.AgenciaViajeTA.dto.RoomDTO;
import mx.com.basantader.AgenciaViajeTA.exceptions.BusinessException;
import mx.com.basantader.AgenciaViajeTA.service.RoomService;

@RestController
@RequestMapping("/API")
@Api(value = "Aplicacion AgenciaViajeTA")
public class RoomController {
	private static final Logger log = LoggerFactory.getLogger(RoomController.class);

	@Autowired
	private RoomService roomService;

	@GetMapping(value = "/listarCuartos")
	@ResponseBody
	public List<RoomDTO> getAllRooms() {
		log.debug("Trying to retrieve all Rooms");
		return roomService.getRooms();
	}

	@GetMapping(value = "/getRoom/{idRoom}")
	public RoomDTO getRoom(@PathVariable Long idRoom) {
		return roomService.getRoom(idRoom);

	}

	@GetMapping(value = "/getRoomByIdHotel/{idHotel}")
	public List<RoomDTO> getRoomByIdHotel(@PathVariable Long idHotel) {
		return roomService.getRoomByIdHotel(idHotel);
	}

	@GetMapping(value = "/getRoomByName")
	@ResponseBody
	public List<RoomDTO> searchByNameRoom(@RequestParam(required = true) String nameRoom) {
		return roomService.getRoomByName(nameRoom);
	}

	@GetMapping(value = "/getRoomByCode")
	@ResponseBody
	public List<RoomDTO> searchByCodeRoom(@RequestParam(required = true) String codeRoom) {
		return roomService.getRoomByCode(codeRoom);
	}

	@GetMapping(value = "/getRoomByCamp")
	public List<RoomDTO> searchByCamp(@RequestParam(required = false, value = "") Long idRoom,
			@RequestParam(required = false, value = "") String nameRoom,
			@RequestParam(required = false, value = "") String codeRoom) {
		return roomService.getRoomByCamp(idRoom, nameRoom, codeRoom);
	}

	@PostMapping(value = "/createRoom/{idHotel}")
	public RoomDTO createRoom(@PathVariable("idHotel") Long idHotel, @Valid @RequestBody RoomDTO request) {

		if (request.getNameRoom() == null || request.getNameRoom().trim().isEmpty()) {
			throw new BusinessException(36);
		}
		if (request.getCodeRoom() == null || request.getCodeRoom().trim().isEmpty()) {
			throw new BusinessException(57);
		}
		if (request.getNoPeople() == null) {
			throw new BusinessException(37);
		}
		if (request.getPrice() == null) {
			throw new BusinessException(38);
		}
		if (request.getNameRoom().toString().length() > 50) {
			throw new BusinessException(68);
		}
		if (request.getDescription().toString().length() > 100) {
			throw new BusinessException(69);
		}
		if (request.getCodeRoom().toString().length() > 20) {
			throw new BusinessException(66);
		}
		if (request.getNoPeople().toString().length() > 38) {
			throw new BusinessException(70);
		}
		if (request.getStatus() == null) {
			request.setStatus(1);
		}
		if (request.getStatus() != 1 && request.getStatus() != 0) {
			throw new BusinessException(67);
		}

		return roomService.createRoom(idHotel, request);
	}

	@PostMapping(value = "updateRoom/{idRoom}")
	public void updateRoom(@PathVariable("idRoom") Long idRoom, @Valid @RequestBody RoomDTO request) {

		if (request.getNameRoom() == null || request.getNameRoom().trim().isEmpty()) {
			throw new BusinessException(36);
		}
		if (request.getCodeRoom() == null || request.getCodeRoom().trim().isEmpty()) {
			throw new BusinessException(57);
		}
		if (request.getNoPeople() == null) {
			throw new BusinessException(37);
		}
		if (request.getPrice() == null) {
			throw new BusinessException(38);
		}
		if (request.getNameRoom().toString().length() > 50) {
			throw new BusinessException(68);
		}
		if (request.getDescription().toString().length() > 100) {
			throw new BusinessException(69);
		}
		if (request.getCodeRoom().toString().length() > 20) {
			throw new BusinessException(66);
		}
		if (request.getNoPeople().toString().length() > 38) {
			throw new BusinessException(70);
		}

		request.setIdRoom(idRoom);
		roomService.updateRoom(request);
	}

	@DeleteMapping("/deleteRoom/{idRoom}")
	public RespuestaDTO deleteRoom(@PathVariable Long idRoom) {
		return roomService.deleteRoom(idRoom);
	}

	@PostMapping(value = "/updateRoomStatus/{idRoom}")
	public RoomDTO updateRoomStatus(@PathVariable Long idRoom, @RequestBody(required = false) RoomDTO request) {

		if (request.getStatus().toString().length() > 1) {
			throw new BusinessException(67);
		}
		if (request.getStatus() != 1 && request.getStatus() != 0) {
			throw new BusinessException(67);
		}

		request.setIdRoom(idRoom);
		return roomService.updateRoomStatus(request);
	}

}
