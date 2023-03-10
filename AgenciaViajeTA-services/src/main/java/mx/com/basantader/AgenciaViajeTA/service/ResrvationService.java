package mx.com.basantader.AgenciaViajeTA.service;

import mx.com.basantader.AgenciaViajeTA.dto.ReservationsDTO;
import mx.com.basantader.AgenciaViajeTA.dto.RespuestaEliminarReservaDTO;

import java.util.List;
import java.util.Optional;

public interface ResrvationService {
    public List<ReservationsDTO> listResrvations();
    public RespuestaEliminarReservaDTO deleteReservation(Long id);
    public ReservationsDTO modifyReservation(ReservationsDTO register);
    public ReservationsDTO newReservation(ReservationsDTO register);
    public List<ReservationsDTO> listaOrigen(String origen);
    public List<ReservationsDTO> listaDestino(String destino);
    public List<ReservationsDTO> listaNumeroDeAerolinea(String nombre);
    public List<ReservationsDTO> listaHotel(String nombre);
    public List<ReservationsDTO> listaCompleta(String origen, String destino, String nombreAerolinea, String nombreHotel);
    public ReservationsDTO listarId(Long id);
}
