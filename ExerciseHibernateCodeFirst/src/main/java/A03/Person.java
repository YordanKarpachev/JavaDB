package A03;

import javax.persistence.*;

@MappedSuperclass()
public abstract class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "first_name", nullable = false)
    protected String firstName;
    @Column(name = "last_name", nullable = false)
    protected String lastName;
    @Column(name = "phone_number", nullable = false)
    protected String phoneNumber;


    protected Person(String firstName, String lastName, String phoneNumber) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;

    }

    protected Person() {
    }
}
