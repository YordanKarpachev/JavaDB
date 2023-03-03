package entities;

import javax.persistence.*;
import java.util.List;
import java.util.Scanner;

public class A03ContainsEmployee {
    public static void main(String[] args) {
   EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");

   EntityManager entityManager = entityManagerFactory.createEntityManager();


   String[] scanner = new Scanner(System.in).nextLine().split(" ");

        Long singleResult =
                entityManager.createQuery("select count(e) from Employee e where firstName = :firstName and lastName = :lastName", Long.class)
                        .setParameter("firstName", scanner[0])
                        .setParameter("lastName", scanner[1])
                        .getSingleResult();

        System.out.println(singleResult > 0 ?  "Yes"  : "NO");
    }
}
