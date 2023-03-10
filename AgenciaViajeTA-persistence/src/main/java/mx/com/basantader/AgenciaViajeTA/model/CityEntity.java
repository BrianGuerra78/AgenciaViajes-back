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
@Table(name = "CITIES_TA")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CityEntity implements Serializable {
	
	@Column(name = "NAME", nullable = false)    
	private String name;

    @Column(name = "CONTINENT_NAME", nullable = false)
    private String continentName;
   

    @Id
    @SequenceGenerator(name = "sequCity", sequenceName = "CITY_SEQ", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "sequCity")
    private Long idCitie;
    
    
    @OneToMany(mappedBy="cityOrigin",cascade = CascadeType.ALL, fetch = FetchType.LAZY,  orphanRemoval = true)
    private List<FlightEntity> fligthO;
    
    @OneToMany(mappedBy="cityDestination",cascade = CascadeType.ALL, fetch = FetchType.LAZY,  orphanRemoval = true)
    private List<FlightEntity> fligthD;
    
    @OneToMany(mappedBy="cityD",cascade = CascadeType.ALL, fetch = FetchType.LAZY,  orphanRemoval = true)
    private List<HotelEntity> hotelD;

}
