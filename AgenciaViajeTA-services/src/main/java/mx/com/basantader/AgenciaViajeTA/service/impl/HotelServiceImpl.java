package mx.com.basantader.AgenciaViajeTA.service.impl;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.basantader.AgenciaViajeTA.dto.CityDTO;
import mx.com.basantader.AgenciaViajeTA.dto.HotelDTO;
import mx.com.basantader.AgenciaViajeTA.dto.RespuestaDTO;
import mx.com.basantader.AgenciaViajeTA.dto.RoomDTO;
import mx.com.basantader.AgenciaViajeTA.exceptions.BusinessException;
import mx.com.basantader.AgenciaViajeTA.model.CityEntity;
import mx.com.basantader.AgenciaViajeTA.model.HotelEntity;
import mx.com.basantader.AgenciaViajeTA.model.RoomEntity;
import mx.com.basantader.AgenciaViajeTA.repository.CityRepository;
import mx.com.basantader.AgenciaViajeTA.repository.HotelRepository;
import mx.com.basantader.AgenciaViajeTA.service.HotelService;
import java.util.Base64;

@Service
public class HotelServiceImpl implements HotelService {
	private static final Logger log = LoggerFactory.getLogger(HotelServiceImpl.class);

	@Autowired
	private HotelRepository hotelRepository;

	@Autowired
	private CityRepository cityRepository;

	@Override
	@Transactional
	public List<HotelDTO> getHotels() {
		List<HotelEntity> lstHotel = hotelRepository.findAll();
		List<HotelDTO> lstReturn = new ArrayList<>();
		HotelDTO hotel;
		List<RoomDTO> lstRoom = new ArrayList<>();
		RoomDTO room;
		for (HotelEntity aux : lstHotel) {
			hotel = new HotelDTO();
			hotel.setIdHotel(aux.getIdHotel());
			hotel.setNameHotel(aux.getNameHotel());
			hotel.setCity(aux.getCityD().getIdCitie());
			hotel.setAddressHotel(aux.getAddressHotel());
			hotel.setLogo(aux.getLogo());
			hotel.setStatusHotel(aux.getStatusHotel());
			hotel.setCodeHotel(aux.getCodeHotel());

			Optional<CityEntity> CityDst = cityRepository.findById(aux.getCityD().getIdCitie());
			CityDTO cityDst = new CityDTO();
			cityDst.setIdCity(CityDst.get().getIdCitie());
			cityDst.setName(CityDst.get().getName());
			cityDst.setContinen_name(CityDst.get().getContinentName());
			hotel.setCityDestination(cityDst);

			lstRoom = new ArrayList<RoomDTO>();
			if (aux.getLstRoom() != null) {
				for (RoomEntity aux2 : aux.getLstRoom()) {
					room = new RoomDTO();
					room.setIdRoom(aux2.getIdRoom());
					room.setNameRoom(aux2.getNameRoom());
					room.setDescription(aux2.getDescription());
					room.setNoPeople(aux2.getNoPeople());
					room.setPrice(aux2.getPrice());
					room.setStatus(aux2.getStatus());
					lstRoom.add(room);
				}
				hotel.setLstRoom(lstRoom);
			}
			lstReturn.add(hotel);
		}
		return lstReturn;
	}

	@Override
	@Transactional
	public HotelDTO getHotel(Long idHotel) {

		Optional<HotelEntity> hotelEntity = hotelRepository.findById(idHotel);
		if (!hotelEntity.isPresent()) {
			throw new BusinessException(29);
		}

		HotelDTO lstReturn = new HotelDTO();
		lstReturn.setIdHotel(hotelEntity.get().getIdHotel());
		lstReturn.setNameHotel(hotelEntity.get().getNameHotel());
		lstReturn.setAddressHotel(hotelEntity.get().getAddressHotel());
		lstReturn.setLogo(hotelEntity.get().getLogo());
		lstReturn.setCodeHotel(hotelEntity.get().getCodeHotel());
		lstReturn.setStatusHotel(hotelEntity.get().getStatusHotel());
		lstReturn.setCity(hotelEntity.get().getCityD().getIdCitie());

		Optional<CityEntity> cityDsts = cityRepository.findById(hotelEntity.get().getCityD().getIdCitie());
		CityDTO cityDst = new CityDTO();
		cityDst.setIdCity(cityDsts.get().getIdCitie());
		cityDst.setName(cityDsts.get().getName());
		cityDst.setContinen_name(cityDsts.get().getContinentName());
		lstReturn.setCityDestination(cityDst);

		List<RoomDTO> lstRoom = new ArrayList<>();
		RoomDTO room;
		lstRoom = new ArrayList<RoomDTO>();
		if (hotelEntity.get().getLstRoom() != null) {
			for (RoomEntity aux2 : hotelEntity.get().getLstRoom()) {
				room = new RoomDTO();
				room.setIdRoom(aux2.getIdRoom());
				room.setNameRoom(aux2.getNameRoom());
				room.setDescription(aux2.getDescription());
				room.setNoPeople(aux2.getNoPeople());
				room.setPrice(aux2.getPrice());
				room.setStatus(aux2.getStatus());
				lstRoom.add(room);
			}
			lstReturn.setLstRoom(lstRoom);
		}
		return lstReturn;
	}

	@Override
	@Transactional
	public List<HotelDTO> getHotelByName(String nameHotel) {

		Optional<HotelEntity> hotelEntity = hotelRepository.findByNameHotel(nameHotel);
		if (!hotelEntity.isPresent()) {
			throw new BusinessException(71);
		}

		List<HotelDTO> lstReturn = new ArrayList<>();
		List<HotelEntity> lstHotel = hotelRepository.searchByNameHotel(nameHotel);
		HotelDTO hotel;
		List<RoomDTO> lstRoom = new ArrayList<>();
		RoomDTO room;
		for (HotelEntity aux : lstHotel) {
			hotel = new HotelDTO();
			hotel.setIdHotel(aux.getIdHotel());
			hotel.setNameHotel(aux.getNameHotel());
			hotel.setCity(aux.getCityD().getIdCitie());
			hotel.setAddressHotel(aux.getAddressHotel());
			hotel.setLogo(aux.getLogo());
			hotel.setStatusHotel(aux.getStatusHotel());
			hotel.setCodeHotel(aux.getCodeHotel());

			Optional<CityEntity> cityDsts = cityRepository.findById(hotelEntity.get().getCityD().getIdCitie());
			CityDTO cityDst = new CityDTO();
			cityDst.setIdCity(cityDsts.get().getIdCitie());
			cityDst.setName(cityDsts.get().getName());
			cityDst.setContinen_name(cityDsts.get().getContinentName());
			hotel.setCityDestination(cityDst);

			lstRoom = new ArrayList<RoomDTO>();
			if (aux.getLstRoom() != null) {
				for (RoomEntity aux2 : aux.getLstRoom()) {
					room = new RoomDTO();
					room.setIdRoom(aux2.getIdRoom());
					room.setNameRoom(aux2.getNameRoom());
					room.setDescription(aux2.getDescription());
					room.setNoPeople(aux2.getNoPeople());
					room.setPrice(aux2.getPrice());
					room.setStatus(aux2.getStatus());
					lstRoom.add(room);
				}
				hotel.setLstRoom(lstRoom);
			}
			lstReturn.add(hotel);
		}
		return lstReturn;
	}

	@Override
	@Transactional
	public HotelDTO createHotel(HotelDTO request) {

		List<HotelEntity> hotelEntity = hotelRepository.searchByNameHotel(request.getNameHotel().toUpperCase());
		if (hotelEntity != null && !hotelEntity.isEmpty()) {
			log.info("This Hotel already exists");
			throw new BusinessException(26);
		}
		List<HotelEntity> hoteles = hotelRepository.searchByCodeHotel(request.getCodeHotel().toUpperCase());
		if (hoteles != null && !hoteles.isEmpty()) {
			log.info("This Code already exists");
			throw new BusinessException(59);
		}
		HotelEntity hotel = new HotelEntity();

		hotel.setNameHotel(request.getNameHotel().toUpperCase());
		hotel.setCodeHotel(request.getCodeHotel().toUpperCase());
		hotel.setAddressHotel(request.getAddressHotel().toUpperCase());
		hotel.setLogo(request.getLogo());
		hotel.setStatusHotel(1);

		Optional<CityEntity> cityDst = cityRepository.findById(request.getCityDestination().getIdCity());
		if (!cityDst.isPresent()) {
			throw new BusinessException(22);
		}
		hotel.setCityD(cityDst.get());

		hotelRepository.save(hotel);
		request.setIdHotel(request.getIdHotel());
		return request;

	}

	@Override
	@Transactional
	public HotelDTO updateHotel(HotelDTO request) {
		Optional<HotelEntity> hotel = hotelRepository.findById(request.getIdHotel());

		if (!hotel.isPresent()) {
			throw new BusinessException(29);
		}

		String hotelExistente = hotel.get().getNameHotel().toUpperCase();
		String codigoExistente = hotel.get().getCodeHotel().toUpperCase();

		List<HotelEntity> entity = hotelRepository.searchByNameHotel(request.getNameHotel().toUpperCase());
		HotelEntity hotelEntity = hotel.get();
		hotelEntity.setNameHotel(request.getNameHotel().toUpperCase());
		String hotelNuevo = hotelEntity.getNameHotel().toUpperCase();

		// to prevent update a Hotel name if already exists, but can update its own
		if (!hotelExistente.equalsIgnoreCase(hotelNuevo)) {
			if (entity != null && !entity.isEmpty()) {
				log.info("This Hotel already exists");
				throw new BusinessException(61);
			}
		}

		List<HotelEntity> entitys = hotelRepository.searchByCodeHotel(request.getCodeHotel().toUpperCase());
		HotelEntity hotelEntitys = hotel.get();
		hotelEntitys.setCodeHotel(request.getCodeHotel().toUpperCase());
		String codigoNuevo = hotelEntitys.getCodeHotel().toUpperCase();

		// to prevent update a Hotel code if already exists, but can update its own
		if (!codigoExistente.equalsIgnoreCase(codigoNuevo)) {
			if (entitys != null && !entitys.isEmpty()) {
				log.info("This code already exists");
				throw new BusinessException(59);
			}
		}

		HotelEntity item = hotel.get();
		item.setNameHotel(request.getNameHotel().toUpperCase());
		item.setAddressHotel(request.getAddressHotel().toUpperCase());
		item.setLogo(request.getLogo());
		item.setCodeHotel(request.getCodeHotel().toUpperCase());

		Optional<CityEntity> cityDst = cityRepository.findById(request.getCityDestination().getIdCity());
		if (!cityDst.isPresent()) {
			throw new BusinessException(22);
		}
		item.setCityD(cityDst.get());

		hotelRepository.save(item);
		request.setIdHotel(request.getIdHotel());
		return request;
	}

	@Override
	@Transactional
	public RespuestaDTO deleteHotel(Long idHotel) {
		RespuestaDTO respuesta = new RespuestaDTO();
		Optional<HotelEntity> hotelEntity = hotelRepository.findById(idHotel);
		if (!hotelEntity.isPresent()) {
			throw new BusinessException(29);
		}

		HotelEntity aux = hotelEntity.get();
		if (aux.getLstRoom().isEmpty()) {
			hotelRepository.deleteById(idHotel);
			respuesta.setMensajeRespuesta("Hotel eliminado correctamente");
		} else {
			respuesta.setMensajeRespuesta("El hotel tiene cuartos activos");
			// throw new BusinessException(31);
		}
		return respuesta;
	}

	@Override
	@Transactional
	public HotelDTO updateHotelStatus(HotelDTO request) {
		Optional<HotelEntity> hotelEntity = hotelRepository.findById(request.getIdHotel());
		if (!hotelEntity.isPresent()) {
			throw new BusinessException(29);
		}
		HotelEntity hotel = hotelEntity.get();
		hotel.setStatusHotel(request.getStatusHotel());

		hotelRepository.save(hotel);
		request.setIdHotel(hotel.getIdHotel());
		return request;
	}

	@Override
	public List<HotelDTO> getHotelByCode(String codeHotel) {
		Optional<HotelEntity> hotelEntity = hotelRepository.findByCodeHotel(codeHotel);
		if (!hotelEntity.isPresent()) {
			throw new BusinessException(71);
		}

		List<HotelDTO> lstReturn = new ArrayList<>();
		List<HotelEntity> lstHotel = hotelRepository.searchByCodeHotel(codeHotel);
		HotelDTO hotel;
		List<RoomDTO> lstRoom = new ArrayList<>();
		RoomDTO room;
		for (HotelEntity aux : lstHotel) {
			hotel = new HotelDTO();
			hotel.setIdHotel(aux.getIdHotel());
			hotel.setNameHotel(aux.getNameHotel());
			hotel.setAddressHotel(aux.getAddressHotel());
			hotel.setCity(aux.getCityD().getIdCitie());
			hotel.setLogo(aux.getLogo());
			hotel.setStatusHotel(aux.getStatusHotel());
			hotel.setCodeHotel(aux.getCodeHotel());

			Optional<CityEntity> CityDst = cityRepository.findById(aux.getCityD().getIdCitie());
			CityDTO cityDst = new CityDTO();
			cityDst.setIdCity(CityDst.get().getIdCitie());
			cityDst.setName(CityDst.get().getName());
			cityDst.setContinen_name(CityDst.get().getContinentName());
			hotel.setCityDestination(cityDst);

			lstRoom = new ArrayList<RoomDTO>();
			if (aux.getLstRoom() != null) {
				for (RoomEntity aux2 : aux.getLstRoom()) {
					room = new RoomDTO();
					room.setIdRoom(aux2.getIdRoom());
					room.setNameRoom(aux2.getNameRoom());
					room.setDescription(aux2.getDescription());
					room.setNoPeople(aux2.getNoPeople());
					room.setPrice(aux2.getPrice());
					room.setStatus(aux2.getStatus());
					lstRoom.add(room);
				}
				hotel.setLstRoom(lstRoom);
			}
			lstReturn.add(hotel);
		}
		return lstReturn;
	}

	@Override
	public List<HotelDTO> getHotelByCity(String city) {
		List<HotelDTO> lstReturn = new ArrayList<>();
		List<HotelEntity> lstHotel = hotelRepository.findAll();
		HotelDTO hotel;
		lstReturn = new ArrayList<HotelDTO>();
		for (HotelEntity aux : lstHotel) {
			hotel = new HotelDTO();
			hotel.setIdHotel(aux.getIdHotel());
			hotel.setNameHotel(aux.getNameHotel());
			hotel.setAddressHotel(aux.getAddressHotel());
			hotel.setLogo(aux.getLogo());
			hotel.setCity(aux.getCityD().getIdCitie());
			hotel.setStatusHotel(aux.getStatusHotel());
			hotel.setCodeHotel(aux.getCodeHotel());

			Optional<CityEntity> CityDst = cityRepository.findByName(aux.getCityD().getName());
			CityDTO cityDst = new CityDTO();
			cityDst.setIdCity(CityDst.get().getIdCitie());
			cityDst.setName(CityDst.get().getName());
			cityDst.setContinen_name(CityDst.get().getContinentName());
			hotel.setCityDestination(cityDst);

			List<RoomDTO> lstRoom = new ArrayList<>();
			RoomDTO room;
			lstRoom = new ArrayList<RoomDTO>();
			for (RoomEntity aux2 : aux.getLstRoom()) {
				room = new RoomDTO();
				room.setIdRoom(aux2.getIdRoom());
				room.setNameRoom(aux2.getNameRoom());
				room.setDescription(aux2.getDescription());
				room.setNoPeople(aux2.getNoPeople());
				room.setPrice(aux2.getPrice());
				room.setStatus(aux2.getStatus());
				lstRoom.add(room);
			}
			hotel.setLstRoom(lstRoom);
			if (aux.getCityD().getName().equals(city)) {
				lstReturn.add(hotel);
			}

		}
		if (lstReturn.isEmpty()) {
			throw new BusinessException(71);
		}
		return lstReturn;
	}

	@Override
	public List<HotelDTO> getHotelByCamp(Long idHotel, String nameHotel, String codeHotel, String city) {
		List<HotelEntity> hotelEntity = hotelRepository.findAll();
		List<HotelDTO> lstReturn = new ArrayList<>();
		HotelDTO hotel;
		for (HotelEntity aux : hotelEntity) {
			hotel = new HotelDTO();
			hotel.setIdHotel(aux.getIdHotel());
			hotel.setNameHotel(aux.getNameHotel());
			hotel.setAddressHotel(aux.getAddressHotel());
			hotel.setLogo(aux.getLogo());
			hotel.setCity(aux.getCityD().getIdCitie());
			hotel.setStatusHotel(aux.getStatusHotel());
			hotel.setCodeHotel(aux.getCodeHotel());

			Optional<CityEntity> CityDst = cityRepository.findByName(aux.getCityD().getName());
			CityDTO cityDst = new CityDTO();
			cityDst.setIdCity(CityDst.get().getIdCitie());
			cityDst.setName(CityDst.get().getName());
			cityDst.setContinen_name(CityDst.get().getContinentName());
			hotel.setCityDestination(cityDst);

			List<RoomDTO> lstRoom = new ArrayList<>();
			RoomDTO room;
			lstRoom = new ArrayList<RoomDTO>();
			for (RoomEntity aux2 : aux.getLstRoom()) {
				room = new RoomDTO();
				room.setIdRoom(aux2.getIdRoom());
				room.setNameRoom(aux2.getNameRoom());
				room.setDescription(aux2.getDescription());
				room.setNoPeople(aux2.getNoPeople());
				room.setPrice(aux2.getPrice());
				room.setStatus(aux2.getStatus());
				lstRoom.add(room);
			}
			hotel.setLstRoom(lstRoom);

			if (aux.getIdHotel().equals(idHotel)) {
				lstReturn.add(hotel);
			}
			if (aux.getNameHotel().equals(nameHotel)) {
				lstReturn.add(hotel);
			}
			if (aux.getCodeHotel().equals(codeHotel)) {
				lstReturn.add(hotel);
			}
			if (aux.getCityD().getName().equals(city)) {
				lstReturn.add(hotel);
			}

		}
		if (lstReturn.isEmpty()) {
			throw new BusinessException(71);
		}
		return lstReturn;
	}

}
