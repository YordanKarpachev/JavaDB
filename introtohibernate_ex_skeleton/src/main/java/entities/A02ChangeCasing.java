package entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.List;

public class A02ChangeCasing {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();

        List<Town> selectTownFromTown = em.createQuery("select t from Town t", Town.class).getResultList();
        selectTownFromTown.stream().forEach(a -> {
                if(a.getName().length() < 5){
                    a.setName(a.getName().toUpperCase());
                    em.detach(a);
                }
        });

        em.getTransaction().commit();

        em.close();
    }
}
