package entities;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.Scanner;

public class A06 {
    public static void main(String[] args) {

        EntityManager em = Persistence.createEntityManagerFactory("soft_uni").createEntityManager();

        String lastName = new Scanner(System.in).nextLine();

        em.getTransaction().begin();

        em.createQuery("update Employee e set e.address = 'Vitoshka 15' where e.lastName = :lName")
                .setParameter("lName", lastName);
        em.getTransaction().commit();
        em.close();

    }
}
