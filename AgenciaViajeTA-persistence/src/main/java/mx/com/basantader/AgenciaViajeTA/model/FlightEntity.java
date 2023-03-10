package mx.com.basantader.AgenciaViajeTA.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "FLIGHTS_TA")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightEntity implements Serializable {
	
	@Column(name = "STATUS", nullable = false)    
	private int status;

	@Column(name = "COST", nullable = false)    
	private double cost;
	
    @Column(name = "DEPARTURE_TIME", nullable = false)
    private LocalDateTime departureTime;
    
    @Column(name = "ARRIVAL_TIME", nullable = false)
    private LocalDateTime arrivalTime;
    
    @Column(name = "CODIGO", nullable = false)
    private String code;

    @Id
    @Column(name = "ID_FLIGHTS")
    @SequenceGenerator(name = "seqFlight", sequenceName = "FLIGHT_SEQ", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "seqFlight")
    private Long idFlight;
    
    @ManyToOne
    @JoinColumn(name = "FK_IDCITIE_ORIGIN")
    private CityEntity cityOrigin;
    
    @ManyToOne
    @JoinColumn(name = "FK_IDCITIE_DESTINATION")
    private CityEntity cityDestination;
    
    @ManyToOne
    @JoinColumn(name = "FK_IDAIRLINE")
    private AirlineEntity airline;
    
    @OneToMany(mappedBy="reservationflight",cascade = CascadeType.ALL, fetch = FetchType.LAZY,  orphanRemoval = true)
    private List<ReservationEntity> reservationflight;

}
