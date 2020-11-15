package com.geekbrains.spring.hibernate.services;

import com.geekbrains.spring.PrepareDataApp;
import com.geekbrains.spring.hibernate.model.Customer;
import com.geekbrains.spring.hibernate.model.Order;
import com.geekbrains.spring.hibernate.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static String result;

    public static void main(String[] args) {
        PrepareDataApp.forcePrepareData();
        Scanner scanner = new Scanner(System.in);
        SessionFactory factory = new Configuration()
                .configure("hibernate/hibernate.cfg.xml")
                .buildSessionFactory();
        Session session = null;

        startProgram(scanner, session, factory);
    }

    private static void startProgram(Scanner scanner, Session session, SessionFactory factory) {
        do {
            System.out.println("Выберите пункт меню:");
            System.out.println("1. Посмотреть, какие товар покупал клиент\n" +
                    "2. Посмотреть, какие клиенты купили определенный товар\n" +
                    "3. Удалить покупателя/товар из базы данных\n" +
                    "4. Завершить программу");
            result = scanner.next();
        } while (result == null);
        switch (result) {
            case "1":
                showProducts(scanner, session, factory);
                break;
            case "2":
                showCustomers(scanner, session, factory);
                break;
            case "3":
                removeData(scanner, session, factory);
            case "4":
                exit(scanner, session, factory);
        }
    }

    private static void removeData(Scanner scanner, Session session, SessionFactory factory) {
        result = null;
        do {
            System.out.println("Выберите пункт для удаления:");
            System.out.println("1. Клиент\n2. Продукт\n3. Выйти назад");
            result = scanner.next();
        } while (result == null);
        switch (result) {
            case "1":
                deleteCustomer(scanner, session, factory);
                break;
            case "2":
                deleteProduct(scanner, session, factory);
                break;
            case "3":
                startProgram(scanner, session, factory);
        }
    }

    private static void deleteProduct(Scanner scanner, Session session, SessionFactory factory) {
        result = null;
        do {
            System.out.println("Введите id товара для удаления:");
            result = scanner.next();
        } while (result == null);
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            Product product = session.get(Product.class, Long.parseLong(result));
            session.remove(product);
            System.out.println("Список товаров: ");
            System.out.println(session.createQuery("SELECT p FROM Product p").getResultList().toString());
            session.getTransaction().commit();
            System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _");
        } finally {
            if (session != null) {
                session.close();
            }
            startProgram(scanner, session, factory);
        }
    }

    private static void deleteCustomer(Scanner scanner, Session session, SessionFactory factory) {
        result = null;
        do {
            System.out.println("Введите id клиента для удаления:");
            result = scanner.next();
        } while (result == null);
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            Customer customer = session.get(Customer.class, Long.parseLong(result));
            session.remove(customer);
            System.out.println("Список покупателей: ");
            System.out.println(session.createQuery("SELECT c FROM Customer c").getResultList().toString());
            session.getTransaction().commit();
            System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _");
        } finally {
            if (session != null) {
                session.close();
            }
            startProgram(scanner, session, factory);
        }
    }

    private static void showCustomers(Scanner scanner, Session session, SessionFactory factory) {
        result = null;
        do {
            System.out.println("Введите id товара либо введите exit для выхода назад");
            result = scanner.next();
        } while (result == null);
        if (result.equals("exit")) {
            startProgram(scanner, session, factory);
        } else {
            try {
                session = factory.getCurrentSession();
                session.beginTransaction();
                Product product = session.get(Product.class, Long.parseLong(result));
                List<Order> orders = session.createQuery("SELECT o FROM Order o WHERE o.productId =" + product.getId()).getResultList();
                System.out.println("Покупатели данного товара: ");
                for (Customer customer : product.getCustomers()) {
                    System.out.println(customer.getName());
                }
                System.out.println("Заказы, в которых присутствуют товары: ");
                for (Order order : orders) {
                    System.out.println(order.toString());
                }
                session.getTransaction().commit();
                System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _");
            } finally {
                if (session != null) {
                    session.close();
                }
                startProgram(scanner, session, factory);
            }
        }
    }

    private static void showProducts(Scanner scanner, Session session, SessionFactory factory) {
        result = null;
        do {
            System.out.println("Введите id покупателя либо введите exit для выхода назад");
            result = scanner.next();
        } while (result == null);
        if (result.equals("exit")) {
            startProgram(scanner, session, factory);
        } else {
            try {
                session = factory.getCurrentSession();
                session.beginTransaction();
                Customer customer = session.get(Customer.class, Long.parseLong(result));
                System.out.println("Товары, которые покупал данный покупатель: ");
                System.out.println(customer.getProducts());
                Long customer_id = customer.getId();
                List<Order> orders = session.createQuery("SELECT o FROM Order o WHERE o.customerId =" + customer_id).getResultList();
                System.out.println("Заказы: ");
                for (Order order : orders) {
                    System.out.println(order.toString());
                }
                session.getTransaction().commit();
                System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _");
            }
            finally {
                if (session != null) {
                    session.close();
                }
                startProgram(scanner, session, factory);
            }
        }
    }

    private static void exit(Scanner scanner, Session session, SessionFactory factory) {
        scanner.close();
        factory.close();
        if (session != null) {
            session.close();
        }
    }
}