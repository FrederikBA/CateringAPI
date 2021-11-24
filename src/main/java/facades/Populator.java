/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.RenameMeDTO;
import entities.Course;
import entities.Menu;
import entities.RenameMe;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import utils.EMF_Creator;

public class Populator {
    public static void populate() {
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        MenuFacade facade = MenuFacade.getMenuFacade(emf);
        Menu m1 = new Menu();
        m1.addToMenu(new Course("pizza", "g.dk"));

        em.getTransaction().begin();
        em.persist(m1);
        em.getTransaction().commit();

    }

    public static void main(String[] args) {
        populate();
    }
}
