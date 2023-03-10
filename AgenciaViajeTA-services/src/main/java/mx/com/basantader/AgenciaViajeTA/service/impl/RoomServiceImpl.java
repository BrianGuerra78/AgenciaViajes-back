package mx.com.basantader.AgenciaViajeTA.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.basantader.AgenciaViajeTA.commons.Constantes;
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
import mx.com.basantader.AgenciaViajeTA.repository.RoomRepository;
import mx.com.basantader.AgenciaViajeTA.service.RoomService;

@Service
public class RoomServiceImpl implements RoomService {
	private static final Logger log = LoggerFactory.getLogger(HotelServiceImpl.class);

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private HotelRepository hotelRepository;

	@Autowired
	private CityRepository cityRepository;

	@Override
	@Transactional
	public List<RoomDTO> getRooms() {
		List<RoomDTO> lstReturn = new ArrayList<>();
		List<RoomEntity> lstRoom = roomRepository.findAll();
		RoomDTO room;
		for (RoomEntity aux : lstRoom) {
			room = new RoomDTO();
			room.setIdRoom(aux.getIdRoom());
			room.setNameRoom(aux.getNameRoom());
			room.setDescription(aux.getDescription());
			room.setNoPeople(aux.getNoPeople());
			room.setPrice(aux.getPrice());
			room.setStatus(aux.getStatus());
			room.setCodeRoom(aux.getCodeRoom());
			Optional<HotelEntity> entity = hotelRepository.findById(aux.getIdHotel().getIdHotel());
			HotelDTO entitys = new HotelDTO();
			entitys.setIdHotel(entity.get().getIdHotel());
			entitys.setNameHotel(entity.get().getNameHotel());
			entitys.setCity(entity.get().getCityD().getIdCitie());
			entitys.setAddressHotel(entity.get().getAddressHotel());
			entitys.setLogo(entity.get().getLogo());
			entitys.setCodeHotel(entity.get().getCodeHotel());
			entitys.setStatusHotel(entity.get().getStatusHotel());

			Optional<CityEntity> CityDst = cityRepository.findById(entity.get().getCityD().getIdCitie());
			CityDTO cityDst = new CityDTO();
			cityDst.setIdCity(CityDst.get().getIdCitie());
			cityDst.setName(CityDst.get().getName());
			cityDst.setContinen_name(CityDst.get().getContinentName());
			entitys.setCityDestination(cityDst);

			room.setIdHotel(entitys);
			lstReturn.add(room);
		}
		return lstReturn;
	}

	@Override
	@Transactional
	public RoomDTO getRoom(Long idRoom) {

		Optional<RoomEntity> roomEntity = roomRepository.findById(idRoom);
		if (!roomEntity.isPresent()) {
			throw new BusinessException(30);
		}

		RoomDTO lstReturn = new RoomDTO();
		lstReturn = new RoomDTO();
		lstReturn.setIdRoom(roomEntity.get().getIdRoom());
		lstReturn.setNameRoom(roomEntity.get().getNameRoom());
		lstReturn.setDescription(roomEntity.get().getDescription());
		lstReturn.setNoPeople(roomEntity.get().getNoPeople());
		lstReturn.setPrice(roomEntity.get().getPrice());
		lstReturn.setStatus(roomEntity.get().getStatus());
		lstReturn.setCodeRoom(roomEntity.get().getCodeRoom());
		Optional<HotelEntity> entity = hotelRepository.findById(roomEntity.get().getIdHotel().getIdHotel());
		HotelDTO entitys = new HotelDTO();
		entitys.setIdHotel(entity.get().getIdHotel());
		entitys.setNameHotel(entity.get().getNameHotel());
		entitys.setCity(entity.get().getCityD().getIdCitie());
		entitys.setAddressHotel(entity.get().getAddressHotel());
		entitys.setLogo(entity.get().getLogo());
		entitys.setCodeHotel(entity.get().getCodeHotel());
		entitys.setStatusHotel(entity.get().getStatusHotel());

		Optional<CityEntity> CityDst = cityRepository.findById(entity.get().getCityD().getIdCitie());
		CityDTO cityDst = new CityDTO();
		cityDst.setIdCity(CityDst.get().getIdCitie());
		cityDst.setName(CityDst.get().getName());
		cityDst.setContinen_name(CityDst.get().getContinentName());
		entitys.setCityDestination(cityDst);

		lstReturn.setIdHotel(entitys);

		return lstReturn;
	}

	@Override
	@Transactional
	public List<RoomDTO> getRoomByName(String nameRoom) {

		Optional<RoomEntity> roomEntity = roomRepository.findByNameRoom(nameRoom);
		if (!roomEntity.isPresent()) {
			throw new BusinessException(72);
		}

		List<RoomDTO> lstReturn = new ArrayList<>();
		List<RoomEntity> lstRoom = roomRepository.searchByNameRoom(nameRoom);
		RoomDTO room;
		for (RoomEntity aux : lstRoom) {
			room = new RoomDTO();
			room.setIdRoom(aux.getIdRoom());
			room.setNameRoom(aux.getNameRoom());
			room.setDescription(aux.getDescription());
			room.setNoPeople(aux.getNoPeople());
			room.setPrice(aux.getPrice());
			room.setStatus(aux.getStatus());
			room.setCodeRoom(aux.getCodeRoom());
			Optional<HotelEntity> entity = hotelRepository.findById(aux.getIdHotel().getIdHotel());
			HotelDTO entitys = new HotelDTO();
			entitys.setIdHotel(entity.get().getIdHotel());
			entitys.setNameHotel(entity.get().getNameHotel());
			entitys.setCity(entity.get().getCityD().getIdCitie());
			entitys.setAddressHotel(entity.get().getAddressHotel());
			entitys.setLogo(entity.get().getLogo());
			entitys.setCodeHotel(entity.get().getCodeHotel());
			entitys.setStatusHotel(entity.get().getStatusHotel());

			Optional<CityEntity> CityDst = cityRepository.findById(entity.get().getCityD().getIdCitie());
			CityDTO cityDst = new CityDTO();
			cityDst.setIdCity(CityDst.get().getIdCitie());
			cityDst.setName(CityDst.get().getName());
			cityDst.setContinen_name(CityDst.get().getContinentName());
			entitys.setCityDestination(cityDst);

			room.setIdHotel(entitys);
			lstReturn.add(room);
		}
		return lstReturn;
	}

	@Override
	@Transactional
	public RoomDTO createRoom(Long idHotel, RoomDTO request) {
		Optional<HotelEntity> hotelEntity = hotelRepository.findById(idHotel);

		if (!hotelEntity.isPresent()) {
			throw new BusinessException(29);
		}

		List<RoomEntity> roomEntity = roomRepository.searchByNameRoom(request.getNameRoom().toUpperCase());
		if (roomEntity != null && !roomEntity.isEmpty()) {
			log.info("This Room already exists");
			throw new BusinessException(27);
		}
		List<RoomEntity> rooms = roomRepository.searchByCodeRoom(request.getCodeRoom().toUpperCase());
		if (rooms != null && !rooms.isEmpty()) {
			log.info("This Code already exists");
			throw new BusinessException(60);
		}

		HotelEntity hotel = hotelEntity.get();
		hotel.setNameHotel(hotelEntity.get().getNameHotel());
		hotel.setAddressHotel(hotelEntity.get().getAddressHotel());
		hotel.setCodeHotel(hotelEntity.get().getCodeHotel());
		hotel.setLogo(hotelEntity.get().getLogo());
		hotel.setStatusHotel(hotelEntity.get().getStatusHotel());
		hotel.setIdHotel(hotelEntity.get().getIdHotel());

		Optional<CityEntity> cityDst = cityRepository.findById(hotel.getCityD().getIdCitie());
		if (!cityDst.isPresent()) {
			throw new BusinessException(Constantes.CODIGO_ERROR_CIUDAD_NO_EXISTE);
		}
		hotel.setCityD(cityDst.get());

		RoomEntity room = new RoomEntity();
		// room.setIdHotel(hotel);
		room.setStatus(1);
		room.setNameRoom(request.getNameRoom().toUpperCase());
		room.setDescription(request.getDescription().toUpperCase());
		room.setCodeRoom(request.getCodeRoom().toUpperCase());
		room.setNoPeople(request.getNoPeople());
		room.setPrice(request.getPrice());
		room.setIdHotel(hotel);
		roomRepository.save(room);
		request.setIdRoom(request.getIdRoom());
		// request.setIdHotel(request.getIdHotel());
		return request;

	}

	@Override
	@Transactional
	public RoomDTO updateRoom(RoomDTO request) {
		Optional<RoomEntity> room = roomRepository.findById(request.getIdRoom());

		if (!room.isPresent()) {
			throw new BusinessException(30);
		}

		String roomExistente = room.get().getNameRoom().toUpperCase();
		String codigoExistente = room.get().getCodeRoom().toUpperCase();

		List<RoomEntity> entity = roomRepository.searchByNameRoom(request.getNameRoom().toUpperCase());
		RoomEntity roomEntity = room.get();
		roomEntity.setNameRoom(request.getNameRoom().toUpperCase());
		String roomNuevo = roomEntity.getNameRoom().toUpperCase();

		// to prevent update a room name if already exists, but can update its own
		if (!roomExistente.equalsIgnoreCase(roomNuevo)) {
			if (entity != null && !entity.isEmpty()) {
				log.info("This Room already exists");
				throw new BusinessException(62);
			}
		}

		List<RoomEntity> entitys = roomRepository.searchByCodeRoom(request.getCodeRoom().toUpperCase());
		RoomEntity roomEntitys = room.get();
		roomEntitys.setCodeRoom(request.getCodeRoom().toUpperCase());
		String codigoNuevo = roomEntitys.getCodeRoom().toUpperCase();

		// to prevent update a room code if already exists, but can update its own
		if (!codigoExistente.equalsIgnoreCase(codigoNuevo)) {
			if (entitys != null && !entitys.isEmpty()) {
				log.info("This code already exists");
				throw new BusinessException(60);
			}
		}

		RoomEntity item = room.get();
		item.setNameRoom(request.getNameRoom().toUpperCase());
		item.setDescription(request.getDescription().toUpperCase());
		item.setNoPeople(request.getNoPeople());
		item.setPrice(request.getPrice());
		item.setCodeRoom(request.getCodeRoom().toUpperCase());

		roomRepository.save(item);
		return request;
	}

	@Override
	@Transactional
	public RespuestaDTO deleteRoom(Long idRoom) {
		RespuestaDTO respuesta = new RespuestaDTO();
		Optional<RoomEntity> roomEntity = roomRepository.findById(idRoom);
		if (!roomEntity.isPresent()) {
			throw new BusinessException(30);
		}

		RoomEntity aux = roomEntity.get();
		if (aux.getReservationroom().isEmpty()) {
			roomRepository.deleteById(idRoom);
			respuesta.setMensajeRespuesta("Cuarto eliminado correctamente");
		} else {
			respuesta.setMensajeRespuesta("El cuarto tiene reservaciones activas");
			// throw new BusinessException(32);
		}
		return respuesta;
	}

	@Override
	@Transactional
	public RoomDTO updateRoomStatus(RoomDTO request) {
		Optional<RoomEntity> roomEntity = roomRepository.findById(request.getIdRoom());
		if (!roomEntity.isPresent()) {
			throw new BusinessException(30);
		}
		RoomEntity room = roomEntity.get();
		room.setStatus(request.getStatus());

		roomRepository.save(room);
		request.setIdRoom(room.getIdRoom());
		return request;
	}

	@Override
	public List<RoomDTO> getRoomByCode(String codeRoom) {
		Optional<RoomEntity> roomEntity = roomRepository.findByCodeRoom(codeRoom);
		if (!roomEntity.isPresent()) {
			throw new BusinessException(72);
		}

		List<RoomDTO> lstReturn = new ArrayList<>();
		List<RoomEntity> lstRoom = roomRepository.searchByCodeRoom(codeRoom);
		RoomDTO room;
		for (RoomEntity aux : lstRoom) {
			room = new RoomDTO();
			room.setIdRoom(aux.getIdRoom());
			room.setNameRoom(aux.getNameRoom());
			room.setDescription(aux.getDescription());
			room.setNoPeople(aux.getNoPeople());
			room.setPrice(aux.getPrice());
			room.setStatus(aux.getStatus());
			room.setCodeRoom(aux.getCodeRoom());
			Optional<HotelEntity> entity = hotelRepository.findById(aux.getIdHotel().getIdHotel());
			HotelDTO hotel = new HotelDTO();
			hotel.setIdHotel(entity.get().getIdHotel());
			hotel.setNameHotel(entity.get().getNameHotel());
			hotel.setCity(entity.get().getCityD().getIdCitie());
			hotel.setAddressHotel(entity.get().getAddressHotel());
			hotel.setLogo(entity.get().getLogo());
			hotel.setCodeHotel(entity.get().getCodeHotel());
			hotel.setStatusHotel(entity.get().getStatusHotel());

			/*
			 * Optional<CityEntity> CityDst =
			 * cityRepository.findById(entity.get().getCityD().getIdCitie()); CityDTO
			 * cityDst = new CityDTO(); cityDst.setIdCity(CityDst.get().getIdCitie());
			 * cityDst.setName(CityDst.get().getName());
			 * cityDst.setContinen_name(CityDst.get().getContinentName());
			 * hotel.setCityDestination(cityDst);
			 */
			room.setIdHotel(hotel);
			lstReturn.add(room);
		}
		return lstReturn;
	}

	@Override
	public List<RoomDTO> getRoomByCamp(Long idRoom, String nameRoom, String codeRoom) {
		List<RoomEntity> lstRoom = roomRepository.findAll();
		List<RoomDTO> lstReturn = new ArrayList<>();
		RoomDTO room;
		for (RoomEntity aux : lstRoom) {
			room = new RoomDTO();
			room.setIdRoom(aux.getIdRoom());
			room.setNameRoom(aux.getNameRoom());
			room.setDescription(aux.getDescription());
			room.setNoPeople(aux.getNoPeople());
			room.setPrice(aux.getPrice());
			room.setStatus(aux.getStatus());
			room.setCodeRoom(aux.getCodeRoom());

			Optional<HotelEntity> entity = hotelRepository.findById(aux.getIdHotel().getIdHotel());
			HotelDTO hotel = new HotelDTO();
			hotel.setIdHotel(entity.get().getIdHotel());
			hotel.setNameHotel(entity.get().getNameHotel());
			hotel.setCity(entity.get().getCityD().getIdCitie());
			hotel.setAddressHotel(entity.get().getAddressHotel());
			hotel.setLogo(entity.get().getLogo());
			hotel.setCodeHotel(entity.get().getCodeHotel());
			hotel.setStatusHotel(entity.get().getStatusHotel());

			Optional<CityEntity> CityDst = cityRepository.findById(entity.get().getCityD().getIdCitie());
			CityDTO cityDst = new CityDTO();
			cityDst.setIdCity(CityDst.get().getIdCitie());
			cityDst.setName(CityDst.get().getName());
			cityDst.setContinen_name(CityDst.get().getContinentName());
			hotel.setCityDestination(cityDst);

			room.setIdHotel(hotel);

			if (aux.getCodeRoom().equals(codeRoom)) {
				lstReturn.add(room);
			}
			if (aux.getIdRoom().equals(idRoom)) {
				lstReturn.add(room);
			}
			if (aux.getNameRoom().equals(nameRoom)) {
				lstReturn.add(room);
			}

		}
		if (lstReturn.isEmpty()) {
			throw new BusinessException(72);
		}

		return lstReturn;
	}

	@Override
	public List<RoomDTO> getRoomByIdHotel(Long idHotel) {
		List<RoomEntity> lstRoom = roomRepository.findAll();
		List<RoomDTO> lstReturn = new ArrayList<>();
		RoomDTO room;
		for (RoomEntity aux : lstRoom) {
			room = new RoomDTO();
			room.setIdRoom(aux.getIdRoom());
			room.setNameRoom(aux.getNameRoom());
			room.setDescription(aux.getDescription());
			room.setNoPeople(aux.getNoPeople());
			room.setPrice(aux.getPrice());
			room.setStatus(aux.getStatus());
			room.setCodeRoom(aux.getCodeRoom());
			Optional<HotelEntity> entity = hotelRepository.findById(aux.getIdHotel().getIdHotel());
			HotelDTO entitys = new HotelDTO();
			entitys.setIdHotel(entity.get().getIdHotel());
			entitys.setNameHotel(entity.get().getNameHotel());
			entitys.setCity(entity.get().getCityD().getIdCitie());
			entitys.setAddressHotel(entity.get().getAddressHotel());
			entitys.setLogo(entity.get().getLogo());
			entitys.setCodeHotel(entity.get().getCodeHotel());
			entitys.setStatusHotel(entity.get().getStatusHotel());

			Optional<CityEntity> CityDst = cityRepository.findById(entity.get().getCityD().getIdCitie());
			CityDTO cityDst = new CityDTO();
			cityDst.setIdCity(CityDst.get().getIdCitie());
			cityDst.setName(CityDst.get().getName());
			cityDst.setContinen_name(CityDst.get().getContinentName());
			entitys.setCityDestination(cityDst);

			room.setIdHotel(entitys);

			if (entity.get().getIdHotel().equals(idHotel)) {
				lstReturn.add(room);
			}
		}
		if (lstReturn.isEmpty()) {
			throw new BusinessException(73);
		}
		return lstReturn;
	}
}
