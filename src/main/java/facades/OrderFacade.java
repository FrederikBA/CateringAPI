package facades;

import dtos.CateringOrder.CateringOrderDTO;
import entities.CateringOrder;

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

    public CateringOrderDTO createOrder(CateringOrderDTO cateringOrderDTO) {
        EntityManager em = emf.createEntityManager();
        CateringOrder order = new CateringOrder(cateringOrderDTO.getDeliveryDate());
        
        try {
            return null;
        } finally {
            em.close();
        }
    }
}
