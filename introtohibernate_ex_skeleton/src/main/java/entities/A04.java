package entities;


import javax.persistence.Persistence;


public class A04 {
    public static void main(String[] args) {
        Persistence.createEntityManagerFactory("soft_uni").createEntityManager()
                .createQuery("Select e.firstName from Employee e where salary > 50000", String.class)
                .getResultList().forEach(System.out::println);

    }
}
