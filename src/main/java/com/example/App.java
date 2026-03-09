package com.example;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class App {

    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        // ---------- INSERT ----------
        Transaction tx = session.beginTransaction();

        Product p1 = new Product("Keyboard","Mechanical Keyboard",3000,15);
        session.save(p1);

        tx.commit();
        System.out.println("Product inserted");

        // ---------- READ ----------
        Product product = session.get(Product.class, 1);
        System.out.println("Retrieved Product: " + product.getName());

        // ---------- UPDATE ----------
        tx = session.beginTransaction();

        Product updateProduct = session.get(Product.class,1);
        updateProduct.setPrice(95000);
        session.update(updateProduct);

        tx.commit();
        System.out.println("Product updated");

        // ---------- DELETE ----------
        tx = session.beginTransaction();

        Product deleteProduct = session.get(Product.class,2);
        session.delete(deleteProduct);

        tx.commit();
        System.out.println("Product deleted");

        session.close();
    }
}
