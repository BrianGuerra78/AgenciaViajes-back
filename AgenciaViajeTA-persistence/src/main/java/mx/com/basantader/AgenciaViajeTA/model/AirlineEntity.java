package mx.com.basantader.AgenciaViajeTA.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "AIRLINE_TA")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AirlineEntity implements Serializable{
	
	@Column(name = "NAME", nullable = false)    
	private String name;
   

    @Id
    @SequenceGenerator(name = "sequAirline", sequenceName = "AIRLINE_SEQ", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "sequAirline")
    private Long idAirline;
    
    
    @OneToMany(mappedBy="airline",cascade = CascadeType.ALL, fetch = FetchType.LAZY,  orphanRemoval = true)
    private List<FlightEntity> fligth;

}
