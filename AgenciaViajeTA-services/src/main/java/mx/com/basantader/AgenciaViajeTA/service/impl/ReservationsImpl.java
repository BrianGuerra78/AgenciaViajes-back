package mx.com.basantader.AgenciaViajeTA.service.impl;

import mx.com.basantader.AgenciaViajeTA.commons.Constantes;
import mx.com.basantader.AgenciaViajeTA.dto.*;
import mx.com.basantader.AgenciaViajeTA.exceptions.BusinessException;
import mx.com.basantader.AgenciaViajeTA.model.*;
import mx.com.basantader.AgenciaViajeTA.repository.*;
import mx.com.basantader.AgenciaViajeTA.service.ResrvationService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.*;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static java.util.concurrent.TimeUnit.DAYS;

@Service
public class ReservationsImpl implements ResrvationService {

    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    CuartoRepository cuartoRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    FlightRepository flightRepository;
    @Autowired
    HotelRepository hotelRepository;

    @Autowired
    CityRepository cityRepository;
    @Override
    public List<ReservationsDTO> listResrvations() {
        List<ReservationEntity> listReservations =  reservationRepository.findAll();
        List<ReservationsDTO> listReturn = new ArrayList<>();
        ReservationsDTO aux;
        for(ReservationEntity app : listReservations){
            aux = new ReservationsDTO();
            aux.setIdReservation(app.getId());
            aux.setName(app.getName());
            aux.setLastNameDad(app.getLastNameDad());
            aux.setLastNameMom(app.getLastNameMom());
            aux.setDescription(app.getDescription());
            aux.setCreateDate(app.getCreateDate());
            aux.setStartDate(app.getStartDate());
            aux.setEndDate(app.getEndDate());
            long startTime = app.getStartDate().getTime();//fechaDesde.getTime();
            long endTime = app.getEndDate().getTime();
            long diasDesde = (long) Math.floor(startTime / (1000*60*60*24)); // convertimos a dias, para que no afecten cambios de hora
            long diasHasta = (long) Math.floor(endTime / (1000*60*60*24)); // convertimos a dias, para que no afecten cambios de hora
            long dias = diasHasta - diasDesde;
            Optional<RoomEntity> searchRoom = cuartoRepository.findById(app.getReservation().getIdRoom());
            RoomDTO room = new RoomDTO();
            room.setIdRoom(searchRoom.get().getIdRoom());
            room.setNameRoom(searchRoom.get().getNameRoom());
            room.setPrice(searchRoom.get().getPrice());
            room.setStatus(searchRoom.get().getStatus());
            room.setDescription(searchRoom.get().getDescription());
            room.setNoPeople(searchRoom.get().getNoPeople());
            room.setCodeRoom(searchRoom.get().getCodeRoom());
            aux.setTotal(dias * searchRoom.get().getPrice());
            Optional<HotelEntity> searchHotel = hotelRepository.findById(searchRoom.get().getIdHotel().getIdHotel());
            HotelDTO hotel = new HotelDTO();
            hotel.setIdHotel(searchHotel.get().getIdHotel());
            hotel.setNameHotel(searchHotel.get().getNameHotel());
            hotel.setLogo(searchHotel.get().getLogo());
            hotel.setCity(searchHotel.get().getCityD().getIdCitie());
            hotel.setAddressHotel(searchHotel.get().getAddressHotel());
            hotel.setCodeHotel(searchHotel.get().getCodeHotel());
            hotel.setStatusHotel(searchHotel.get().getStatusHotel());
            room.setIdHotel(hotel);
            aux.setRoom(room);
            Optional<UserEntity> searchUser = userRepository.findById(app.getReservationuser().getIdUser());
            UserDTO user = new UserDTO();
            user.setIdUser(searchUser.get().getIdUser());
            user.setName(searchUser.get().getName());
            user.setLastnameDad(searchUser.get().getLastnameDad());
            user.setLastnameMom(searchUser.get().getLastnameMom());
            user.setFk_idRole(searchUser.get().getRoleItem().getIdRole());
            aux.setUser(user);
            Optional<FlightEntity> searchFlight = flightRepository.findById(app.getReservationflight().getIdFlight());
            FlightDTO flight = new FlightDTO();
            flight.setIdFlight(searchFlight.get().getIdFlight());
            flight.setStatus(searchFlight.get().getStatus());
            flight.setArrivalTime(searchFlight.get().getArrivalTime());
            flight.setDepartureTime(searchFlight.get().getDepartureTime());
            flight.setCost(searchFlight.get().getCost());
            Optional<CityEntity> searchCityDestination = cityRepository.findById(searchFlight.get().getCityDestination().getIdCitie());
            CityDTO cityDestiaton = new CityDTO();
            cityDestiaton.setIdCity(searchCityDestination.get().getIdCitie());
            cityDestiaton.setName(searchCityDestination.get().getName());
            cityDestiaton.setContinen_name(searchCityDestination.get().getContinentName());
            flight.setFkIdCitieDestination(cityDestiaton);
            Optional<CityEntity> searchCityOrigin = cityRepository.findById(searchFlight.get().getCityOrigin().getIdCitie());
            CityDTO cityOrigin = new CityDTO();
            cityOrigin.setIdCity(searchCityOrigin.get().getIdCitie());
            cityOrigin.setName(searchCityOrigin.get().getName());
            cityOrigin.setContinen_name(searchCityOrigin.get().getContinentName());
            flight.setFkIdCitieOrigin(cityOrigin);
            aux.setFlight(flight);
            listReturn.add(aux);
        }
        return listReturn;
    }

    @Override
    public List<ReservationsDTO> listaOrigen(String origen) {
        List<ReservationEntity> listReservations =  reservationRepository.findAll();
        List<ReservationsDTO> listReturn = new ArrayList<>();
        ReservationsDTO aux;
        for(ReservationEntity app : listReservations){
            aux = new ReservationsDTO();
            aux.setIdReservation(app.getId());
            aux.setName(app.getName());
            aux.setLastNameDad(app.getLastNameDad());
            aux.setLastNameMom(app.getLastNameMom());
            aux.setDescription(app.getDescription());
            aux.setCreateDate(app.getCreateDate());
            aux.setStartDate(app.getStartDate());
            aux.setEndDate(app.getEndDate());
            long startTime = app.getStartDate().getTime();//fechaDesde.getTime();
            long endTime = app.getEndDate().getTime();
            long diasDesde = (long) Math.floor(startTime / (1000*60*60*24)); // convertimos a dias, para que no afecten cambios de hora
            long diasHasta = (long) Math.floor(endTime / (1000*60*60*24)); // convertimos a dias, para que no afecten cambios de hora
            long dias = diasHasta - diasDesde;
            Optional<RoomEntity> searchRoom = cuartoRepository.findById(app.getReservation().getIdRoom());
            RoomDTO room = new RoomDTO();
            room.setIdRoom(searchRoom.get().getIdRoom());
            room.setNameRoom(searchRoom.get().getNameRoom());
            room.setPrice(searchRoom.get().getPrice());
            room.setStatus(searchRoom.get().getStatus());
            room.setDescription(searchRoom.get().getDescription());
            room.setNoPeople(searchRoom.get().getNoPeople());
            room.setCodeRoom(searchRoom.get().getCodeRoom());
            aux.setTotal(dias * searchRoom.get().getPrice());
            Optional<HotelEntity> searchHotel = hotelRepository.findById(searchRoom.get().getIdHotel().getIdHotel());
            HotelDTO hotel = new HotelDTO();
            hotel.setIdHotel(searchHotel.get().getIdHotel());
            hotel.setNameHotel(searchHotel.get().getNameHotel());
            hotel.setLogo(searchHotel.get().getLogo());
            hotel.setCity(searchHotel.get().getCityD().getIdCitie());
            hotel.setAddressHotel(searchHotel.get().getAddressHotel());
            hotel.setCodeHotel(searchHotel.get().getCodeHotel());
            hotel.setStatusHotel(searchHotel.get().getStatusHotel());
            room.setIdHotel(hotel);
            aux.setRoom(room);
            Optional<UserEntity> searchUser = userRepository.findById(app.getReservationuser().getIdUser());
            UserDTO user = new UserDTO();
            user.setIdUser(searchUser.get().getIdUser());
            user.setName(searchUser.get().getName());
            user.setLastnameDad(searchUser.get().getLastnameDad());
            user.setLastnameMom(searchUser.get().getLastnameMom());
            user.setFk_idRole(searchUser.get().getRoleItem().getIdRole());
            aux.setUser(user);
            Optional<FlightEntity> searchFlight = flightRepository.findById(app.getReservationflight().getIdFlight());
            FlightDTO flight = new FlightDTO();
            flight.setIdFlight(searchFlight.get().getIdFlight());
            flight.setStatus(searchFlight.get().getStatus());
            flight.setArrivalTime(searchFlight.get().getArrivalTime());
            flight.setDepartureTime(searchFlight.get().getDepartureTime());
            flight.setCost(searchFlight.get().getCost());
            Optional<CityEntity> searchCityDestination = cityRepository.findById(searchFlight.get().getCityDestination().getIdCitie());
            CityDTO cityDestiaton = new CityDTO();
            cityDestiaton.setIdCity(searchCityDestination.get().getIdCitie());
            cityDestiaton.setName(searchCityDestination.get().getName());
            cityDestiaton.setContinen_name(searchCityDestination.get().getContinentName());
            flight.setFkIdCitieDestination(cityDestiaton);
            Optional<CityEntity> searchCityOrigin = cityRepository.findById(searchFlight.get().getCityOrigin().getIdCitie());
            CityDTO cityOrigin = new CityDTO();
            cityOrigin.setIdCity(searchCityOrigin.get().getIdCitie());
            cityOrigin.setName(searchCityOrigin.get().getName());
            cityOrigin.setContinen_name(searchCityOrigin.get().getContinentName());
            flight.setFkIdCitieOrigin(cityOrigin);
            aux.setFlight(flight);
            if(searchCityOrigin.get().getName().equals(origen)){
                listReturn.add(aux);
            }
        }
        if (listReturn.isEmpty()){
            throw new BusinessException(Constantes.CODIGO_ERROR_ORIGEN_INEXISTS);
        }
        return listReturn;
    }
    @Override
    public List<ReservationsDTO> listaDestino(String destino) {
        List<ReservationEntity> listReservations =  reservationRepository.findAll();
        List<ReservationsDTO> listReturn = new ArrayList<>();
        ReservationsDTO aux;
        for(ReservationEntity app : listReservations){
            aux = new ReservationsDTO();
            aux.setIdReservation(app.getId());
            aux.setName(app.getName());
            aux.setLastNameDad(app.getLastNameDad());
            aux.setLastNameMom(app.getLastNameMom());
            aux.setDescription(app.getDescription());
            aux.setCreateDate(app.getCreateDate());
            aux.setStartDate(app.getStartDate());
            aux.setEndDate(app.getEndDate());
            long startTime = app.getStartDate().getTime();//fechaDesde.getTime();
            long endTime = app.getEndDate().getTime();
            long diasDesde = (long) Math.floor(startTime / (1000*60*60*24)); // convertimos a dias, para que no afecten cambios de hora
            long diasHasta = (long) Math.floor(endTime / (1000*60*60*24)); // convertimos a dias, para que no afecten cambios de hora
            long dias = diasHasta - diasDesde;
            Optional<RoomEntity> searchRoom = cuartoRepository.findById(app.getReservation().getIdRoom());
            RoomDTO room = new RoomDTO();
            room.setIdRoom(searchRoom.get().getIdRoom());
            room.setNameRoom(searchRoom.get().getNameRoom());
            room.setPrice(searchRoom.get().getPrice());
            room.setStatus(searchRoom.get().getStatus());
            room.setDescription(searchRoom.get().getDescription());
            room.setNoPeople(searchRoom.get().getNoPeople());
            room.setCodeRoom(searchRoom.get().getCodeRoom());
            aux.setTotal(dias * searchRoom.get().getPrice());
            Optional<HotelEntity> searchHotel = hotelRepository.findById(searchRoom.get().getIdHotel().getIdHotel());
            HotelDTO hotel = new HotelDTO();
            hotel.setIdHotel(searchHotel.get().getIdHotel());
            hotel.setNameHotel(searchHotel.get().getNameHotel());
            hotel.setLogo(searchHotel.get().getLogo());
            hotel.setCity(searchHotel.get().getCityD().getIdCitie());
            hotel.setAddressHotel(searchHotel.get().getAddressHotel());
            hotel.setCodeHotel(searchHotel.get().getCodeHotel());
            hotel.setStatusHotel(searchHotel.get().getStatusHotel());
            room.setIdHotel(hotel);
            aux.setRoom(room);
            Optional<UserEntity> searchUser = userRepository.findById(app.getReservationuser().getIdUser());
            UserDTO user = new UserDTO();
            user.setIdUser(searchUser.get().getIdUser());
            user.setName(searchUser.get().getName());
            user.setLastnameDad(searchUser.get().getLastnameDad());
            user.setLastnameMom(searchUser.get().getLastnameMom());
            user.setFk_idRole(searchUser.get().getRoleItem().getIdRole());
            aux.setUser(user);
            Optional<FlightEntity> searchFlight = flightRepository.findById(app.getReservationflight().getIdFlight());
            FlightDTO flight = new FlightDTO();
            flight.setIdFlight(searchFlight.get().getIdFlight());
            flight.setStatus(searchFlight.get().getStatus());
            flight.setArrivalTime(searchFlight.get().getArrivalTime());
            flight.setDepartureTime(searchFlight.get().getDepartureTime());
            flight.setCost(searchFlight.get().getCost());
            Optional<CityEntity> searchCityDestination = cityRepository.findById(searchFlight.get().getCityDestination().getIdCitie());
            CityDTO cityDestiaton = new CityDTO();
            cityDestiaton.setIdCity(searchCityDestination.get().getIdCitie());
            cityDestiaton.setName(searchCityDestination.get().getName());
            cityDestiaton.setContinen_name(searchCityDestination.get().getContinentName());
            flight.setFkIdCitieDestination(cityDestiaton);
            Optional<CityEntity> searchCityOrigin = cityRepository.findById(searchFlight.get().getCityOrigin().getIdCitie());
            CityDTO cityOrigin = new CityDTO();
            cityOrigin.setIdCity(searchCityOrigin.get().getIdCitie());
            cityOrigin.setName(searchCityOrigin.get().getName());
            cityOrigin.setContinen_name(searchCityOrigin.get().getContinentName());
            flight.setFkIdCitieOrigin(cityOrigin);
            aux.setFlight(flight);
            if(searchCityDestination.get().getName().equals(destino)){
                listReturn.add(aux);
            }
        }
        if (listReturn.isEmpty()){
            throw new BusinessException(Constantes.CODIGO_ERROR_ORIGEN_INEXISTS);
        }
        return listReturn;
    }

    @Override
    public List<ReservationsDTO> listaNumeroDeAerolinea(String nombre) {
        List<ReservationEntity> listReservations =  reservationRepository.findAll();
        List<ReservationsDTO> listReturn = new ArrayList<>();
        ReservationsDTO aux;
        for(ReservationEntity app : listReservations){
            aux = new ReservationsDTO();
            aux.setIdReservation(app.getId());
            aux.setName(app.getName());
            aux.setLastNameDad(app.getLastNameDad());
            aux.setLastNameMom(app.getLastNameMom());
            aux.setDescription(app.getDescription());
            aux.setCreateDate(app.getCreateDate());
            aux.setStartDate(app.getStartDate());
            aux.setEndDate(app.getEndDate());
            long startTime = app.getStartDate().getTime();//fechaDesde.getTime();
            long endTime = app.getEndDate().getTime();
            long diasDesde = (long) Math.floor(startTime / (1000*60*60*24)); // convertimos a dias, para que no afecten cambios de hora
            long diasHasta = (long) Math.floor(endTime / (1000*60*60*24)); // convertimos a dias, para que no afecten cambios de hora
            long dias = diasHasta - diasDesde;
            Optional<RoomEntity> searchRoom = cuartoRepository.findById(app.getReservation().getIdRoom());
            RoomDTO room = new RoomDTO();
            room.setIdRoom(searchRoom.get().getIdRoom());
            room.setNameRoom(searchRoom.get().getNameRoom());
            room.setPrice(searchRoom.get().getPrice());
            room.setStatus(searchRoom.get().getStatus());
            room.setDescription(searchRoom.get().getDescription());
            room.setNoPeople(searchRoom.get().getNoPeople());
            room.setCodeRoom(searchRoom.get().getCodeRoom());
            aux.setTotal(dias * searchRoom.get().getPrice());
            Optional<HotelEntity> searchHotel = hotelRepository.findById(searchRoom.get().getIdHotel().getIdHotel());
            HotelDTO hotel = new HotelDTO();
            hotel.setIdHotel(searchHotel.get().getIdHotel());
            hotel.setNameHotel(searchHotel.get().getNameHotel());
            hotel.setLogo(searchHotel.get().getLogo());
            hotel.setCity(searchHotel.get().getCityD().getIdCitie());
            hotel.setAddressHotel(searchHotel.get().getAddressHotel());
            hotel.setCodeHotel(searchHotel.get().getCodeHotel());
            hotel.setStatusHotel(searchHotel.get().getStatusHotel());
            room.setIdHotel(hotel);
            aux.setRoom(room);
            Optional<UserEntity> searchUser = userRepository.findById(app.getReservationuser().getIdUser());
            UserDTO user = new UserDTO();
            user.setIdUser(searchUser.get().getIdUser());
            user.setName(searchUser.get().getName());
            user.setLastnameDad(searchUser.get().getLastnameDad());
            user.setLastnameMom(searchUser.get().getLastnameMom());
            user.setFk_idRole(searchUser.get().getRoleItem().getIdRole());
            aux.setUser(user);
            Optional<FlightEntity> searchFlight = flightRepository.findById(app.getReservationflight().getIdFlight());
            FlightDTO flight = new FlightDTO();
            flight.setIdFlight(searchFlight.get().getIdFlight());
            flight.setStatus(searchFlight.get().getStatus());
            flight.setArrivalTime(searchFlight.get().getArrivalTime());
            flight.setDepartureTime(searchFlight.get().getDepartureTime());
            flight.setCost(searchFlight.get().getCost());
            Optional<CityEntity> searchCityDestination = cityRepository.findById(searchFlight.get().getCityDestination().getIdCitie());
            CityDTO cityDestiaton = new CityDTO();
            cityDestiaton.setIdCity(searchCityDestination.get().getIdCitie());
            cityDestiaton.setName(searchCityDestination.get().getName());
            cityDestiaton.setContinen_name(searchCityDestination.get().getContinentName());
            flight.setFkIdCitieDestination(cityDestiaton);
            Optional<CityEntity> searchCityOrigin = cityRepository.findById(searchFlight.get().getCityOrigin().getIdCitie());
            CityDTO cityOrigin = new CityDTO();
            cityOrigin.setIdCity(searchCityOrigin.get().getIdCitie());
            cityOrigin.setName(searchCityOrigin.get().getName());
            cityOrigin.setContinen_name(searchCityOrigin.get().getContinentName());
            flight.setFkIdCitieOrigin(cityOrigin);
            aux.setFlight(flight);
            if(searchFlight.get().getAirline().getName().equals(nombre)){
                listReturn.add(aux);
            }
        }
        if (listReturn.isEmpty()){
            throw new BusinessException(Constantes.CODIGO_ERROR_ORIGEN_INEXISTS);
        }
        return listReturn;
    }

    @Override
    public List<ReservationsDTO> listaHotel(String nombre) {
        List<ReservationEntity> listReservations =  reservationRepository.findAll();
        List<ReservationsDTO> listReturn = new ArrayList<>();
        ReservationsDTO aux;
        for(ReservationEntity app : listReservations){
            aux = new ReservationsDTO();
            aux.setIdReservation(app.getId());
            aux.setName(app.getName());
            aux.setLastNameDad(app.getLastNameDad());
            aux.setLastNameMom(app.getLastNameMom());
            aux.setDescription(app.getDescription());
            aux.setCreateDate(app.getCreateDate());
            aux.setStartDate(app.getStartDate());
            aux.setEndDate(app.getEndDate());
            long startTime = app.getStartDate().getTime();//fechaDesde.getTime();
            long endTime = app.getEndDate().getTime();
            long diasDesde = (long) Math.floor(startTime / (1000*60*60*24)); // convertimos a dias, para que no afecten cambios de hora
            long diasHasta = (long) Math.floor(endTime / (1000*60*60*24)); // convertimos a dias, para que no afecten cambios de hora
            long dias = diasHasta - diasDesde;
            Optional<RoomEntity> searchRoom = cuartoRepository.findById(app.getReservation().getIdRoom());
            RoomDTO room = new RoomDTO();
            room.setIdRoom(searchRoom.get().getIdRoom());
            room.setNameRoom(searchRoom.get().getNameRoom());
            room.setPrice(searchRoom.get().getPrice());
            room.setStatus(searchRoom.get().getStatus());
            room.setDescription(searchRoom.get().getDescription());
            room.setNoPeople(searchRoom.get().getNoPeople());
            room.setCodeRoom(searchRoom.get().getCodeRoom());
            aux.setTotal(dias * searchRoom.get().getPrice());
            Optional<HotelEntity> searchHotel = hotelRepository.findById(searchRoom.get().getIdHotel().getIdHotel());
            HotelDTO hotel = new HotelDTO();
            hotel.setIdHotel(searchHotel.get().getIdHotel());
            hotel.setNameHotel(searchHotel.get().getNameHotel());
            hotel.setLogo(searchHotel.get().getLogo());
            hotel.setCity(searchHotel.get().getCityD().getIdCitie());
            hotel.setAddressHotel(searchHotel.get().getAddressHotel());
            hotel.setCodeHotel(searchHotel.get().getCodeHotel());
            hotel.setStatusHotel(searchHotel.get().getStatusHotel());
            room.setIdHotel(hotel);
            aux.setRoom(room);
            Optional<UserEntity> searchUser = userRepository.findById(app.getReservationuser().getIdUser());
            UserDTO user = new UserDTO();
            user.setIdUser(searchUser.get().getIdUser());
            user.setName(searchUser.get().getName());
            user.setLastnameDad(searchUser.get().getLastnameDad());
            user.setLastnameMom(searchUser.get().getLastnameMom());
            user.setFk_idRole(searchUser.get().getRoleItem().getIdRole());
            aux.setUser(user);
            Optional<FlightEntity> searchFlight = flightRepository.findById(app.getReservationflight().getIdFlight());
            FlightDTO flight = new FlightDTO();
            flight.setIdFlight(searchFlight.get().getIdFlight());
            flight.setStatus(searchFlight.get().getStatus());
            flight.setArrivalTime(searchFlight.get().getArrivalTime());
            flight.setDepartureTime(searchFlight.get().getDepartureTime());
            flight.setCost(searchFlight.get().getCost());
            Optional<CityEntity> searchCityDestination = cityRepository.findById(searchFlight.get().getCityDestination().getIdCitie());
            CityDTO cityDestiaton = new CityDTO();
            cityDestiaton.setIdCity(searchCityDestination.get().getIdCitie());
            cityDestiaton.setName(searchCityDestination.get().getName());
            cityDestiaton.setContinen_name(searchCityDestination.get().getContinentName());
            flight.setFkIdCitieDestination(cityDestiaton);
            Optional<CityEntity> searchCityOrigin = cityRepository.findById(searchFlight.get().getCityOrigin().getIdCitie());
            CityDTO cityOrigin = new CityDTO();
            cityOrigin.setIdCity(searchCityOrigin.get().getIdCitie());
            cityOrigin.setName(searchCityOrigin.get().getName());
            cityOrigin.setContinen_name(searchCityOrigin.get().getContinentName());
            flight.setFkIdCitieOrigin(cityOrigin);
            aux.setFlight(flight);
            if(searchHotel.get().getNameHotel().equals(nombre)){
                listReturn.add(aux);
            }
        }
        if (listReturn.isEmpty()){
            throw new BusinessException(Constantes.CODIGO_ERROR_ORIGEN_INEXISTS);
        }
        return listReturn;
    }

    @Override
    public List<ReservationsDTO> listaCompleta(String origen, String destino, String nombreAerolinea, String nombreHotel) {
        List<ReservationEntity> listReservations =  reservationRepository.findAll();
        List<ReservationsDTO> listReturn = new ArrayList<>();
        ReservationsDTO aux;
        for(ReservationEntity app : listReservations){
            aux = new ReservationsDTO();
            aux.setIdReservation(app.getId());
            aux.setName(app.getName());
            aux.setLastNameDad(app.getLastNameDad());
            aux.setLastNameMom(app.getLastNameMom());
            aux.setDescription(app.getDescription());
            aux.setCreateDate(app.getCreateDate());
            aux.setStartDate(app.getStartDate());
            aux.setEndDate(app.getEndDate());
            long startTime = app.getStartDate().getTime();//fechaDesde.getTime();
            long endTime = app.getEndDate().getTime();
            long diasDesde = (long) Math.floor(startTime / (1000*60*60*24)); // convertimos a dias, para que no afecten cambios de hora
            long diasHasta = (long) Math.floor(endTime / (1000*60*60*24)); // convertimos a dias, para que no afecten cambios de hora
            long dias = diasHasta - diasDesde;
            Optional<RoomEntity> searchRoom = cuartoRepository.findById(app.getReservation().getIdRoom());
            RoomDTO room = new RoomDTO();
            room.setIdRoom(searchRoom.get().getIdRoom());
            room.setNameRoom(searchRoom.get().getNameRoom());
            room.setPrice(searchRoom.get().getPrice());
            room.setStatus(searchRoom.get().getStatus());
            room.setDescription(searchRoom.get().getDescription());
            room.setNoPeople(searchRoom.get().getNoPeople());
            room.setCodeRoom(searchRoom.get().getCodeRoom());
            aux.setTotal(dias * searchRoom.get().getPrice());
            Optional<HotelEntity> searchHotel = hotelRepository.findById(searchRoom.get().getIdHotel().getIdHotel());
            HotelDTO hotel = new HotelDTO();
            hotel.setIdHotel(searchHotel.get().getIdHotel());
            hotel.setNameHotel(searchHotel.get().getNameHotel());
            hotel.setLogo(searchHotel.get().getLogo());
            hotel.setCity(searchHotel.get().getCityD().getIdCitie());
            hotel.setAddressHotel(searchHotel.get().getAddressHotel());
            hotel.setCodeHotel(searchHotel.get().getCodeHotel());
            hotel.setStatusHotel(searchHotel.get().getStatusHotel());
            room.setIdHotel(hotel);
            aux.setRoom(room);
            Optional<UserEntity> searchUser = userRepository.findById(app.getReservationuser().getIdUser());
            UserDTO user = new UserDTO();
            user.setIdUser(searchUser.get().getIdUser());
            user.setName(searchUser.get().getName());
            user.setLastnameDad(searchUser.get().getLastnameDad());
            user.setLastnameMom(searchUser.get().getLastnameMom());
            user.setFk_idRole(searchUser.get().getRoleItem().getIdRole());
            aux.setUser(user);
            Optional<FlightEntity> searchFlight = flightRepository.findById(app.getReservationflight().getIdFlight());
            FlightDTO flight = new FlightDTO();
            flight.setIdFlight(searchFlight.get().getIdFlight());
            flight.setStatus(searchFlight.get().getStatus());
            flight.setArrivalTime(searchFlight.get().getArrivalTime());
            flight.setDepartureTime(searchFlight.get().getDepartureTime());
            flight.setCost(searchFlight.get().getCost());
            Optional<CityEntity> searchCityDestination = cityRepository.findById(searchFlight.get().getCityDestination().getIdCitie());
            CityDTO cityDestiaton = new CityDTO();
            cityDestiaton.setIdCity(searchCityDestination.get().getIdCitie());
            cityDestiaton.setName(searchCityDestination.get().getName());
            cityDestiaton.setContinen_name(searchCityDestination.get().getContinentName());
            flight.setFkIdCitieDestination(cityDestiaton);
            Optional<CityEntity> searchCityOrigin = cityRepository.findById(searchFlight.get().getCityOrigin().getIdCitie());
            CityDTO cityOrigin = new CityDTO();
            cityOrigin.setIdCity(searchCityOrigin.get().getIdCitie());
            cityOrigin.setName(searchCityOrigin.get().getName());
            cityOrigin.setContinen_name(searchCityOrigin.get().getContinentName());
            flight.setFkIdCitieOrigin(cityOrigin);
            aux.setFlight(flight);
            if(searchCityOrigin.get().getName().equals(origen)){
                listReturn.add(aux);
            }
            if(searchCityDestination.get().getName().equals(destino)){
                listReturn.add(aux);
            }
            if(searchFlight.get().getAirline().getName().equals(nombreAerolinea)){
                listReturn.add(aux);
            }
            if(searchHotel.get().getNameHotel().equals(nombreHotel)){
                listReturn.add(aux);
            }
        }
        if (listReturn.isEmpty()){
            throw new BusinessException(Constantes.CODIGO_ERROR_ORIGEN_INEXISTS);
        }
        return listReturn;
    }

    @Override
    public ReservationsDTO listarId(Long id) {
        Optional<ReservationEntity> listReservations =  reservationRepository.findById(id);
        ReservationsDTO aux = new ReservationsDTO();
        if(!listReservations.isPresent()){
            throw new BusinessException("El registro no se ecuentra");
        }
                aux = new ReservationsDTO();
                aux.setIdReservation(listReservations.get().getId());
                aux.setName(listReservations.get().getName());
                aux.setLastNameDad(listReservations.get().getLastNameDad());
                aux.setLastNameMom(listReservations.get().getLastNameMom());
                aux.setDescription(listReservations.get().getDescription());
                aux.setCreateDate(listReservations.get().getCreateDate());
                aux.setStartDate(listReservations.get().getStartDate());
                aux.setEndDate(listReservations.get().getEndDate());
                long startTime = listReservations.get().getStartDate().getTime();//fechaDesde.getTime();
                long endTime = listReservations.get().getEndDate().getTime();
                long diasDesde = (long) Math.floor(startTime / (1000 * 60 * 60 * 24)); // convertimos a dias, para que no afecten cambios de hora
                long diasHasta = (long) Math.floor(endTime / (1000 * 60 * 60 * 24)); // convertimos a dias, para que no afecten cambios de hora
                long dias = diasHasta - diasDesde;
                Optional<RoomEntity> searchRoom = cuartoRepository.findById(listReservations.get().getReservation().getIdRoom());
                RoomDTO room = new RoomDTO();
                room.setIdRoom(searchRoom.get().getIdRoom());
                room.setNameRoom(searchRoom.get().getNameRoom());
                room.setPrice(searchRoom.get().getPrice());
                room.setStatus(searchRoom.get().getStatus());
                room.setDescription(searchRoom.get().getDescription());
                room.setNoPeople(searchRoom.get().getNoPeople());
                room.setCodeRoom(searchRoom.get().getCodeRoom());
                aux.setTotal(dias * searchRoom.get().getPrice());
                Optional<HotelEntity> searchHotel = hotelRepository.findById(searchRoom.get().getIdHotel().getIdHotel());
                HotelDTO hotel = new HotelDTO();
                hotel.setIdHotel(searchHotel.get().getIdHotel());
                hotel.setNameHotel(searchHotel.get().getNameHotel());
                hotel.setLogo(searchHotel.get().getLogo());
                hotel.setCity(searchHotel.get().getCityD().getIdCitie());
                hotel.setAddressHotel(searchHotel.get().getAddressHotel());
                hotel.setCodeHotel(searchHotel.get().getCodeHotel());
                hotel.setStatusHotel(searchHotel.get().getStatusHotel());
                room.setIdHotel(hotel);
                aux.setRoom(room);
                Optional<UserEntity> searchUser = userRepository.findById(listReservations.get().getReservationuser().getIdUser());
                UserDTO user = new UserDTO();
                user.setIdUser(searchUser.get().getIdUser());
                user.setName(searchUser.get().getName());
                user.setLastnameDad(searchUser.get().getLastnameDad());
                user.setLastnameMom(searchUser.get().getLastnameMom());
                user.setFk_idRole(searchUser.get().getRoleItem().getIdRole());
                aux.setUser(user);
                Optional<FlightEntity> searchFlight = flightRepository.findById(listReservations.get().getReservationflight().getIdFlight());
                FlightDTO flight = new FlightDTO();
                flight.setIdFlight(searchFlight.get().getIdFlight());
                flight.setStatus(searchFlight.get().getStatus());
                flight.setArrivalTime(searchFlight.get().getArrivalTime());
                flight.setDepartureTime(searchFlight.get().getDepartureTime());
                flight.setCost(searchFlight.get().getCost());
                Optional<CityEntity> searchCityDestination = cityRepository.findById(searchFlight.get().getCityDestination().getIdCitie());
                CityDTO cityDestiaton = new CityDTO();
                cityDestiaton.setIdCity(searchCityDestination.get().getIdCitie());
                cityDestiaton.setName(searchCityDestination.get().getName());
                cityDestiaton.setContinen_name(searchCityDestination.get().getContinentName());
                flight.setFkIdCitieDestination(cityDestiaton);
                Optional<CityEntity> searchCityOrigin = cityRepository.findById(searchFlight.get().getCityOrigin().getIdCitie());
                CityDTO cityOrigin = new CityDTO();
                cityOrigin.setIdCity(searchCityOrigin.get().getIdCitie());
                cityOrigin.setName(searchCityOrigin.get().getName());
                cityOrigin.setContinen_name(searchCityOrigin.get().getContinentName());
                flight.setFkIdCitieOrigin(cityOrigin);
                aux.setFlight(flight);


        return aux;
    }

    @Override
    @Transactional
    public RespuestaEliminarReservaDTO deleteReservation(Long id) {
        Optional<ReservationEntity> deleteRegister = reservationRepository.findById(id);
        if(!deleteRegister.isPresent()){
            throw new BusinessException(Constantes.CODIGO_ERROR_GIO);
        }
        RespuestaEliminarReservaDTO message = new RespuestaEliminarReservaDTO();
        reservationRepository.deleteById(id);
        message.setMensajeRespuesta("Reservacion No: " + id + " Eliminada correctamente");
        return message;
    }

    @Override
    @Transactional
    public ReservationsDTO modifyReservation(ReservationsDTO register) {
        Optional<ReservationEntity> reservationRegister = reservationRepository.findById(register.getIdReservation());
        if(!reservationRegister.isPresent()){
            throw new BusinessException("ID no encontrado en la base de datos");
        }
        ReservationEntity reservation = reservationRegister.get();
        reservation.setCreateDate(register.getCreateDate());
        reservation.setDescription(register.getDescription());
        reservation.setName(register.getName());
        reservation.setLastNameDad(register.getLastNameDad());
        reservation.setLastNameMom(register.getLastNameMom());
        reservation.setStartDate(register.getStartDate());
        reservation.setEndDate(register.getEndDate());
        Optional<RoomEntity> searchRoom = cuartoRepository.findById(register.getRoom().getIdRoom());//register.getRoom().getIdRoom());
        RoomEntity modifyRoom = searchRoom.get(); //searchRoom.get();
        RoomDTO nuevoRoom = register.getRoom();
        reservation.setReservation(modifyRoom);
        //Optional<HotelEntity> modifyHotel = hotelRepository.findById(modifyRoom.getIdHotel().getIdHotel());
        // HotelEntity pruebaEntity = modifyHotel.get();
        //pruebaEntity.setIdHotel(pruebaEntity.getIdHotel());
        // modifyRoom.setIdHotel(pruebaEntity);
        Optional<FlightEntity> flightId = flightRepository.findById(register.getFlight().getIdFlight());
        FlightEntity modifyFlight = flightId.get();
        FlightDTO nuevoFlight = register.getFlight();
        modifyFlight.setIdFlight(nuevoFlight.getIdFlight());
        // Optional<CityEntity> pruebaModificarCiudad = cityRepository.findById(nuevoFlight.getFkIdCitieDestination().getIdCity());
        // CityEntity ciudadDestino = pruebaModificarCiudad.get();
        //modifyFlight.setCityDestination(ciudadDestino);
        //Optional<CityEntity> pruebaModificarCiudadOrigen = cityRepository.findById(nuevoFlight.getFkIdCitieOrigin().getIdCity());
        //CityEntity ciudadOrigen = pruebaModificarCiudadOrigen.get();
        //modifyFlight.setCityOrigin(ciudadOrigen);
        reservation.setReservationflight(modifyFlight);
        Optional<UserEntity> usuarioEntidad = userRepository.findById(register.getUser().getIdUser());
        UserEntity nuevoUsuario = usuarioEntidad.get();
        reservation.setReservationuser(nuevoUsuario);
        reservation.setReservation(modifyRoom);
        if(searchRoom.get().getStatus() != 1){
            throw new BusinessException(200);
        }
        if(flightId.get().getStatus() != 1){
            throw new BusinessException(201);
        }
        modifyRoom.setStatus(0);
        reservationRepository.save(reservation);
        register.setIdReservation(reservation.getId());
        long startTime = register.getStartDate().getTime();//fechaDesde.getTime();
        long endTime = register.getEndDate().getTime();
        long diasDesde = (long) Math.floor(startTime / (1000*60*60*24)); // convertimos a dias, para que no afecten cambios de hora
        long diasHasta = (long) Math.floor(endTime / (1000*60*60*24)); // convertimos a dias, para que no afecten cambios de hora
        long dias = diasHasta - diasDesde;
        register.setTotal(dias * searchRoom.get().getPrice());
        return register;
    }

    @Override
    @Transactional
    public ReservationsDTO newReservation(ReservationsDTO register) {
        ReservationEntity reservation = new ReservationEntity();
        reservation.setCreateDate(new Date());
        reservation.setDescription(register.getDescription());
        reservation.setName(register.getName());
        reservation.setLastNameDad(register.getLastNameDad());
        reservation.setLastNameMom(register.getLastNameMom());
        reservation.setStartDate(register.getStartDate());
        reservation.setEndDate(register.getEndDate());
        Optional<RoomEntity> searchRoom = cuartoRepository.findById(register.getRoom().getIdRoom());//register.getRoom().getIdRoom());
        RoomEntity modifyRoom = searchRoom.get(); //searchRoom.get();
        RoomDTO nuevoRoom = register.getRoom();
        reservation.setReservation(modifyRoom);
        //Optional<HotelEntity> modifyHotel = hotelRepository.findById(modifyRoom.getIdHotel().getIdHotel());
       // HotelEntity pruebaEntity = modifyHotel.get();
        //pruebaEntity.setIdHotel(pruebaEntity.getIdHotel());
       // modifyRoom.setIdHotel(pruebaEntity);
        Optional<FlightEntity> flightId = flightRepository.findById(register.getFlight().getIdFlight());
        FlightEntity modifyFlight = flightId.get();
        FlightDTO nuevoFlight = register.getFlight();
        modifyFlight.setIdFlight(nuevoFlight.getIdFlight());
       // Optional<CityEntity> pruebaModificarCiudad = cityRepository.findById(nuevoFlight.getFkIdCitieDestination().getIdCity());
       // CityEntity ciudadDestino = pruebaModificarCiudad.get();
        //modifyFlight.setCityDestination(ciudadDestino);
        //Optional<CityEntity> pruebaModificarCiudadOrigen = cityRepository.findById(nuevoFlight.getFkIdCitieOrigin().getIdCity());
        //CityEntity ciudadOrigen = pruebaModificarCiudadOrigen.get();
        //modifyFlight.setCityOrigin(ciudadOrigen);
        reservation.setReservationflight(modifyFlight);
        Optional<UserEntity> usuarioEntidad = userRepository.findById(register.getUser().getIdUser());
        UserEntity nuevoUsuario = usuarioEntidad.get();
        reservation.setReservationuser(nuevoUsuario);
        reservation.setReservation(modifyRoom);
        if(searchRoom.get().getStatus() != 1){
            throw new BusinessException(200);
        }
        if(flightId.get().getStatus() != 1){
            throw new BusinessException(201);
        }
        modifyRoom.setStatus(0);
        reservationRepository.save(reservation);
        register.setIdReservation(reservation.getId());
        long startTime = register.getStartDate().getTime();//fechaDesde.getTime();
        long endTime = register.getEndDate().getTime();
        long diasDesde = (long) Math.floor(startTime / (1000*60*60*24)); // convertimos a dias, para que no afecten cambios de hora
        long diasHasta = (long) Math.floor(endTime / (1000*60*60*24)); // convertimos a dias, para que no afecten cambios de hora
        long dias = diasHasta - diasDesde;
        register.setTotal(dias * searchRoom.get().getPrice());
        return register;
    }

}
