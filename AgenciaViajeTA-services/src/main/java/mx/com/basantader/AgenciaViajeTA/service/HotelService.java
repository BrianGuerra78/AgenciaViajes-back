package mx.com.basantader.AgenciaViajeTA.service;

import java.util.List;

import mx.com.basantader.AgenciaViajeTA.dto.HotelDTO;
import mx.com.basantader.AgenciaViajeTA.dto.RespuestaDTO;

public interface HotelService {

	List<HotelDTO> getHotels();

	 HotelDTO getHotel(Long idHotel);

	List<HotelDTO> getHotelByName(String nameHotel);
	
	List<HotelDTO> getHotelByCode(String codeHotel);
	
	List<HotelDTO> getHotelByCity(String city);
	
	List<HotelDTO> getHotelByCamp(Long idHotel, String nameHotel, String codeHotel, String city);

	HotelDTO createHotel(HotelDTO request);

	HotelDTO updateHotel(HotelDTO request);

	RespuestaDTO deleteHotel(Long idHotel);
	
	HotelDTO updateHotelStatus(HotelDTO request);

}
