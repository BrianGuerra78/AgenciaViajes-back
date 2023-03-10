package mx.com.basantader.AgenciaViajeTA.controller;


import io.swagger.annotations.Api;
import mx.com.basantader.AgenciaViajeTA.commons.Constantes;
import mx.com.basantader.AgenciaViajeTA.dto.AirlineDTO;
import mx.com.basantader.AgenciaViajeTA.dto.ReservationsDTO;
import mx.com.basantader.AgenciaViajeTA.dto.RespuestaEliminarReservaDTO;
import mx.com.basantader.AgenciaViajeTA.exceptions.BusinessException;
import mx.com.basantader.AgenciaViajeTA.service.ResrvationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/API")
@Api(value = "Applciation AgenciaViajeTA")
public class ReservationsController {
    private static final Logger log = LoggerFactory.getLogger(ReservationsController.class);
    @Autowired
    private ResrvationService resrvationService;
    @GetMapping(value = "/listar")
    public List<ReservationsDTO> obtenerReservations(){
        return resrvationService.listResrvations();
    }
    @GetMapping(value = "/buscarOrigen")
    public List<ReservationsDTO> obtenerReservationsOrigen(@RequestParam(required = false, value = "") String nombre){
        return resrvationService.listaOrigen(nombre);
    }
    @GetMapping(value = "/buscarDestino")
    public List<ReservationsDTO> obtenerReservationsDestino(@RequestParam(required = false, value = "") String nombre){
        return resrvationService.listaDestino(nombre);
    }
    @GetMapping(value = "/buscarAerolinea")
    public List<ReservationsDTO> obtenerReservationsAerolinea(@RequestParam(required = false, value = "") String nombre){
        return resrvationService.listaNumeroDeAerolinea(nombre);
    }
    @GetMapping(value = "/buscarHotel")
    public List<ReservationsDTO> obtenerReservationsHotel(@RequestParam(required = false, value = "") String nombre){
        return resrvationService.listaHotel(nombre);
    }
    @GetMapping(value = "/buscarPorCampo")
    public List<ReservationsDTO> obtenerReservationsCampo(@RequestParam(required = false, value = "") String origen,
                                                          @RequestParam(required = false, value = "") String destino,
                                                          @RequestParam(required = false, value = "") String aerolinea,
                                                          @RequestParam(required = false, value = "") String hotel){
        return resrvationService.listaCompleta(origen,destino, aerolinea, hotel);
    }
    @GetMapping(value="/getInformationReservaciones/{id}")
    public ReservationsDTO getInformation(@PathVariable Long id) {
        return resrvationService.listarId(id);
    }
    @DeleteMapping(value = "/eliminarReservacion/{id}")
    public RespuestaEliminarReservaDTO deletereservation(@PathVariable Long id){
        return resrvationService.deleteReservation(id);
    }
    @PostMapping(value = "/modificarResrvacion/{id}")
    public ReservationsDTO modificarReservacion(@PathVariable Long id, @RequestBody ReservationsDTO newRegister){
        newRegister.setIdReservation(id);
        if(newRegister.getName() == null || newRegister.getName().equals("")){
            throw new BusinessException(Constantes.CODIGO_ERROR_NOMBRE_REQUERIDO);
        }
        if(newRegister.getEndDate() == null || newRegister.getEndDate().equals("")){
            throw new BusinessException(Constantes.CODIGO_ERROR_FECHA_FIN_REQUERIDA);
        }
        if(newRegister.getDescription() == null || newRegister.getDescription().equals("")){
            throw new BusinessException(Constantes.CODIGO_DESCRIPCION_REQUERIDA);
        }
        if(newRegister.getRoom() == null || newRegister.getRoom().equals("")){
            throw new BusinessException(Constantes.CODIGO_ERROR_CUARTO_REQUERIDO);
        }
        
        if(newRegister.getUser() == null || newRegister.getUser().equals("")){
            throw new BusinessException(Constantes.CODIGO_ERROR_USUARIO_REQUERIDO);
        }
        if(newRegister.getStartDate() == null || newRegister.getStartDate().equals("")){
            throw new BusinessException(Constantes.CODIGO_ERROR_FECHA_INICI_REQUERIDO);
        }
        return resrvationService.modifyReservation(newRegister);
    }
    @PostMapping(value = "/AgregarReserva")
    public ReservationsDTO newReserva(@RequestBody ReservationsDTO newRegister){
        if(newRegister.getName() == null || newRegister.getName().equals("")){
            throw new BusinessException(Constantes.CODIGO_ERROR_NOMBRE_REQUERIDO);
        }
        if(newRegister.getEndDate() == null || newRegister.getEndDate().equals("")){
            throw new BusinessException(Constantes.CODIGO_ERROR_FECHA_FIN_REQUERIDA);
        }
        if(newRegister.getDescription() == null || newRegister.getDescription().equals("")){
            throw new BusinessException(Constantes.CODIGO_DESCRIPCION_REQUERIDA);
        }
        if(newRegister.getRoom() == null || newRegister.getRoom().equals("")){
            throw new BusinessException(Constantes.CODIGO_ERROR_CUARTO_REQUERIDO);
        }

        if(newRegister.getUser() == null || newRegister.getUser().equals("")){
            throw new BusinessException(Constantes.CODIGO_ERROR_USUARIO_REQUERIDO);
        }
        if(newRegister.getStartDate() == null || newRegister.getStartDate().equals("")){
            throw new BusinessException();
        }
        return resrvationService.newReservation(newRegister);
    }


}
