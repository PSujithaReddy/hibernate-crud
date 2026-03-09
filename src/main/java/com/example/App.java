package com.example;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class App {
    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        // Sorting by price ASC
        Query<Product> q1 = session.createQuery(
        "FROM Product ORDER BY price ASC", Product.class);

        List<Product> list1 = q1.list();
        System.out.println("Price ASC:");
        list1.forEach(p -> System.out.println(p.getName()+" "+p.getPrice()));

        // Sorting by price DESC
        Query<Product> q2 = session.createQuery(
        "FROM Product ORDER BY price DESC", Product.class);

        List<Product> list2 = q2.list();
        System.out.println("Price DESC:");
        list2.forEach(p -> System.out.println(p.getName()+" "+p.getPrice()));

        // Pagination (first 3)
        Query<Product> q3 = session.createQuery("FROM Product", Product.class);
        q3.setFirstResult(0);
        q3.setMaxResults(3);

        System.out.println("First 3 Products:");
        q3.list().forEach(p -> System.out.println(p.getName()));

        // Pagination (next 3)
        Query<Product> q4 = session.createQuery("FROM Product", Product.class);
        q4.setFirstResult(3);
        q4.setMaxResults(3);

        System.out.println("Next 3 Products:");
        q4.list().forEach(p -> System.out.println(p.getName()));

        // Aggregate Count
        Query<Long> q5 = session.createQuery(
        "SELECT COUNT(*) FROM Product", Long.class);

        System.out.println("Total Products: "+q5.uniqueResult());

        // Min & Max price
        Query<Object[]> q6 = session.createQuery(
        "SELECT MIN(price), MAX(price) FROM Product");

        Object[] result = q6.uniqueResult();

        System.out.println("Min Price: "+result[0]);
        System.out.println("Max Price: "+result[1]);

        tx.commit();
        session.close();
    }
}
