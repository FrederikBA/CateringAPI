package utils;


import entities.Role;
import entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class SetupTestUsers {

  public static void main(String[] args) {

    EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
    EntityManager em = emf.createEntityManager();
    
    // IMPORTAAAAAAAAAANT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    // This breaks one of the MOST fundamental security rules in that it ships with default users and passwords
    // CHANGE the three passwords below, before you uncomment and execute the code below
    // Also, either delete this file, when users are created or rename and add to .gitignore
    // Whatever you do DO NOT COMMIT and PUSH with the real passwords

    User customer = new User("customer", "test1");
    User admin = new User("admin", "test2");
    User both = new User("customer_admin", "test3");

    if(admin.getUserPass().equals("test")||customer.getUserPass().equals("test")||both.getUserPass().equals("test"))
      throw new UnsupportedOperationException("You have not changed the passwords");

    em.getTransaction().begin();
    Role customerRole = new Role("customer");
    Role adminRole = new Role("admin");
    customer.addRole(customerRole);
    admin.addRole(adminRole);
    both.addRole(customerRole);
    both.addRole(adminRole);
    em.persist(customerRole);
    em.persist(adminRole);
    em.persist(customer);
    em.persist(admin);
    em.persist(both);
    em.getTransaction().commit();
    System.out.println("PW: " + customer.getUserPass());
    System.out.println("Testing user with OK password: " + customer.verifyPassword("test"));
    System.out.println("Testing user with wrong password: " + customer.verifyPassword("test1"));
    System.out.println("Created TEST Users");
   
  }

}
