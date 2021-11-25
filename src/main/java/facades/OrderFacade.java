package facades;

import dtos.CateringOrder.CateringOrderDTO;
import dtos.CateringOrder.CateringOrdersDTO;
import entities.CateringOrder;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

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




    public CateringOrdersDTO getAll(){
        EntityManager em = emf.createEntityManager();

        try {
            TypedQuery query = em.createQuery("SELECT o from CateringOrder o", CateringOrder.class);
            List<CateringOrder> results = query.getResultList();

            return new CateringOrdersDTO(results);

        } finally {
            em.close();
        }
    }

   public CateringOrderDTO getOrderById(int id){
        EntityManager em = emf.createEntityManager();

        try {
            CateringOrder cateringOrder = em.find(CateringOrder.class, id);

            return new CateringOrderDTO(cateringOrder);
        } finally {
            em.close();
        }
   }






}
