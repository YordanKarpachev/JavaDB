package softuni.exam.models.entity;

import javax.persistence.*;
import java.util.List;


@Entity(name ="stars" )
public class Star {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(name = "light_years", nullable = false)
    private double lightYears;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, name = "star_type")
    @Enumerated(EnumType.STRING)
    private StartTypeEnum starType;

    @ManyToOne
    private Constellation constellation;

   @OneToMany(targetEntity = Astronomer.class, mappedBy = "star")
    private List<Astronomer> astronomer;

    public Star() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLightYears() {
        return lightYears;
    }

    public void setLightYears(double lightYears) {
        this.lightYears = lightYears;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public Constellation getConstellation() {
        return constellation;
    }

    public void setConstellation(Constellation constellation) {
        this.constellation = constellation;
    }

    public StartTypeEnum getStarType() {
        return starType;
    }

    public void setStarType(StartTypeEnum starType) {
        this.starType = starType;
    }

    public List<Astronomer> getAstronomer() {
        return astronomer;
    }

    public void setAstronomer(Astronomer astronomer) {
        this.astronomer.add(astronomer);
    }


    @Override
    public String toString() {
        return  "Star: " + this.name + "\n" +
        "   *Distance: "+ lightYears+ " light years" + "\n" +
        "   **Description: "+description + "\n" +
        "   ***Constellation: "+  constellation.getName();
    }
}
