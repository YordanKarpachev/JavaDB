import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManager codeFirst = Persistence.createEntityManagerFactory("CodeFirst").createEntityManager();
        codeFirst.getTransaction().begin();



        codeFirst.getTransaction().commit();
        codeFirst.close();
        System.out.println();
    }
}
