/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.CateringOrder;
import entities.Course;
import entities.Menu;
//import entities.Order;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import utils.EMF_Creator;

import java.util.Date;

public class Populator {
    public static void populate() {
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        Menu m1 = new Menu();
        Course c1 = new Course("Pasta", "google.dk",1);
        Course c2 = new Course("Pizza", "google.dk",2);
        Course c3 = new Course("Burger", "google.dk",3);
        m1.addToMenu(c1);
        m1.addToMenu(c2);
        m1.addToMenu(c3);
        CateringOrder o1 = new CateringOrder("2021-11-22");
        em.getTransaction().begin();
        o1.setMenu(m1);
        em.persist(m1);
        em.persist(o1);
        em.getTransaction().commit();


    }

    public static void main(String[] args) {
        populate();
    }
}
