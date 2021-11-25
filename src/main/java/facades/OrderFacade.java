package facades;

import dtos.CateringOrder.CateringOrderDTO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class OrderFacade {
    private static EntityManagerFactory emf;
    private static OrderFacade instance;

    private OrderFacade() {
    }

    public static OrderFacade getOrderFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new OrderFacade();
        }
        return instance;
    }

    public CateringOrderDTO createOrder() {
        EntityManager em = emf.createEntityManager();
        try {
            return null;
        } finally {
            em.close();
        }
    }


    
}
