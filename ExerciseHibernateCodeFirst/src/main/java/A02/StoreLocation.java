package A02;


import javax.persistence.*;
import java.util.Set;

@Table(name = "store_location")
@Entity
public class StoreLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "location_name")
    private String locationName;


    @OneToMany(targetEntity = Sale.class, mappedBy = "id")

    Set<Sale> sales;


}
