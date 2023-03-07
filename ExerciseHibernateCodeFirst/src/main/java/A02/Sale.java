package A02;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sale")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;


    @ManyToOne(optional = false)
    @JoinColumn(name = "store_location_id", referencedColumnName = "id")
    StoreLocation storeLocation;

    public Sale() {
    }

    public int getId() {
        return id;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")

    private Customer customer;



}
