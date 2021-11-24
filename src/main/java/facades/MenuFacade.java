package facades;

import javax.persistence.EntityManagerFactory;

public class MenuFacade {
    private static EntityManagerFactory emf;
    private static MenuFacade instance;

    private MenuFacade() {
    }

    public static MenuFacade getMenuFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MenuFacade();
        }
        return instance;
    }
}
