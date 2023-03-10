package mx.com.basantader.AgenciaViajeTA.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationsDTO {
    private long idReservation;
    private String name;
    private String lastNameDad;
    private String lastNameMom;
    private String description;
    private Date createDate;
    private Date startDate;
    private Date endDate;
    private long idUser;
    private RoomDTO room;
    private UserDTO user;
    private FlightDTO flight;
    private double total;



}
