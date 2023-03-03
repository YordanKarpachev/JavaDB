package entities;

import javax.persistence.Persistence;
import java.util.List;

public class A05 {
    public static void main(String[] args) {

        Persistence.createEntityManagerFactory("soft_uni")
                .createEntityManager()
                .createQuery("select e from Employee e  where  e.department = '6' order by salary, id", Employee.class)
                .getResultList().forEach(a -> System.out.printf("%s %s from %s - $%.2f%n", a.getFirstName(), a.getLastName(), a.getDepartment().getName(), a.getSalary()));

    }
}
