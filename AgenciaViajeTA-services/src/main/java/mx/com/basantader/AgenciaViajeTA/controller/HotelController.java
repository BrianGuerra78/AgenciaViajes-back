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
import mx.com.basantader.AgenciaViajeTA.dto.HotelDTO;
import mx.com.basantader.AgenciaViajeTA.dto.RespuestaDTO;
import mx.com.basantader.AgenciaViajeTA.exceptions.BusinessException;
import mx.com.basantader.AgenciaViajeTA.service.HotelService;

@RestController
@RequestMapping("/API")
@Api(value = "Aplicacion AgenciaViajeTA")
public class HotelController {
	private static final Logger log = LoggerFactory.getLogger(HotelController.class);

	@Autowired
	private HotelService hotelService;

	@GetMapping(value = "/listarHoteles")
	@ResponseBody
	public List<HotelDTO> getAllHotels() {
		log.debug("Show all Hotels");
		return hotelService.getHotels();
	}

	@GetMapping(value = "/getHotel/{idHotel}")
	public HotelDTO getHotel(@PathVariable Long idHotel) {
		return hotelService.getHotel(idHotel);
	}

	@GetMapping(value = "/getHotelByName")
	@ResponseBody
	public List<HotelDTO> searchByNameHotel(@RequestParam(required = true) String nameHotel) {
		return hotelService.getHotelByName(nameHotel);
	}

	@GetMapping(value = "/getHotelByCode")
	@ResponseBody
	public List<HotelDTO> searchByCodeHotel(@RequestParam(required = true) String codeHotel) {
		return hotelService.getHotelByCode(codeHotel);
	}

	@GetMapping(value = "/getHotelByCity")
	@ResponseBody
	public List<HotelDTO> searchByCityD(@RequestParam(required = true) String city) {
		return hotelService.getHotelByCity(city);
	}

	@GetMapping(value = "/getHotelByCamp")
	public List<HotelDTO> searchByCamp(@RequestParam(required = false, value = "") Long idHotel,
			@RequestParam(required = false, value = "") String nameHotel,
			@RequestParam(required = false, value = "") String codeHotel,
			@RequestParam(required = false, value = "") String city) {
		return hotelService.getHotelByCamp(idHotel, nameHotel, codeHotel, city);
	}

	@PostMapping(value = "/createHotel")
	public void createHotel(@Valid @RequestBody HotelDTO request) {

		if (request == null) {
			throw new BusinessException(28);
		}
		if (request.getNameHotel() == null || request.getNameHotel().trim().isEmpty()) {
			throw new BusinessException(33);
		}
		if (request.getAddressHotel() == null || request.getAddressHotel().trim().isEmpty()) {
			throw new BusinessException(35);
		}
		if (request.getCodeHotel() == null || request.getCodeHotel().trim().isEmpty()) {
			throw new BusinessException(56);
		}
		if (request.getNameHotel().length() > 50) {
			throw new BusinessException(63);
		}
		if (request.getStatusHotel() == null) {
			request.setStatusHotel(1);
		}
		if (request.getAddressHotel().length() > 50) {
			throw new BusinessException(65);
		}
		if (request.getCodeHotel().length() > 20) {
			throw new BusinessException(66);
		}
		if (request.getStatusHotel() != 1 && request.getStatusHotel() != 0) {
			throw new BusinessException(67);
		}
		if (request.getStatusHotel().toString().length() > 1) {
			throw new BusinessException(67);
		}

		hotelService.createHotel(request);
	}

	@PostMapping(value = "updateHotel/{idHotel}")
	public HotelDTO updateHotel(@PathVariable Long idHotel, @RequestBody HotelDTO request) {

		if (request.getNameHotel() == null || request.getNameHotel().trim().isEmpty()) {
			throw new BusinessException(33);
		}
		if (request.getAddressHotel() == null || request.getAddressHotel().trim().isEmpty()) {
			throw new BusinessException(35);
		}
		if (request.getCodeHotel() == null || request.getCodeHotel().trim().isEmpty()) {
			throw new BusinessException(56);
		}
		if (request.getNameHotel().length() > 50) {
			throw new BusinessException(63);
		}
		if (request.getAddressHotel().length() > 50) {
			throw new BusinessException(65);
		}
		if (request.getCodeHotel().length() > 20) {
			throw new BusinessException(66);
		}
		request.setIdHotel(idHotel);
		return hotelService.updateHotel(request);
	}

	@DeleteMapping(value = "/deleteHotel/{idHotel}")
	public RespuestaDTO deleteHotel(@PathVariable Long idHotel) {
		return hotelService.deleteHotel(idHotel);
	}

	@PostMapping(value = "/updateHotelStatus/{idHotel}")
	public HotelDTO updateHotelStatus(@PathVariable Long idHotel, @RequestBody(required = false) HotelDTO request) {

		if (request.getStatusHotel().toString().length() > 1) {
			throw new BusinessException(67);
		}
		if (request.getStatusHotel() != 1 && request.getStatusHotel() != 0) {
			throw new BusinessException(67);
		}

		request.setIdHotel(idHotel);
		return hotelService.updateHotelStatus(request);
	}

}
