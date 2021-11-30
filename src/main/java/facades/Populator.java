/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.Menu.MenuDTO;
import entities.*;
//import entities.Order;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import utils.EMF_Creator;

import java.util.Date;

public class Populator {
    public static void populate() {
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        Menu m1 = new Menu("24/12/2021", 7,"Hellerupvej");
        Course c1 = new Course("Pasta", "google.dk", 1);
        Course c2 = new Course("Pizza", "google.dk", 2);
        Course c3 = new Course("Burger", "google.dk", 3);
        m1.addToMenu(c1);
        m1.addToMenu(c2);
        m1.addToMenu(c3);
        User u1 = new User("testUser", "password");
        u1.addMenu(m1);
        em.getTransaction().begin();
        em.persist(u1);
        em.persist(m1);
        em.getTransaction().commit();


    }

    public static void testCreate() {
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        MenuFacade facade = MenuFacade.getMenuFacade(emf);
        User u2 = new User("customer", "password");
        Role customerRole = new Role("customer");
        u2.addRole(customerRole);

        Menu m2 = new Menu("31/12/2021",4,"Bikini bottom");
        Course c1 = new Course("Fisk", "google.dk", 4);
        Course c2 = new Course("Kylling", "google.dk", 5);
        Course c3 = new Course("Okse", "google.dk", 6);
        m2.addToMenu(c1);
        m2.addToMenu(c2);
        m2.addToMenu(c3);

        facade.createMenu(u2.getUserName(), new MenuDTO(m2));
    }

    public static void main(String[] args) {
        // populate();
        testCreate();
    }
}
