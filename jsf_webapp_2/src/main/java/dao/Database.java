package dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import entities.Message;
import entities.Order;
import entities.Product;
import entities.Subscription;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Stateless
public class Database {

    @PersistenceContext(unitName = "JPADefaultDataBaseConnection")
    private EntityManager em;

    private static ArrayList<Order> orders = new ArrayList<Order>();
    private static ArrayList<Product> products = initProducts();
    private static ArrayList<Subscription> subscriptions = new ArrayList<Subscription>();

    private static ArrayList<Product> initProducts() {
        ArrayList<Product> products = new ArrayList<Product>();

        products.add(new Product(0, "Марио", 1.2, "mario_keychain.png"));
        products.add(new Product(1, "Луиджи", 1.3, "luigi_keychain.png"));
        products.add(new Product(2, "Гъбата", 1.5, "mushroom_keychain.png"));

        return products;
    }

    public List<Message> getMessages() {
        return em.createQuery("SELECT m FROM Message m", Message.class)
                .getResultList();
    }

    public void addMessage(Message msg) {
        em.persist(msg);
    }

    public List<Message> getMessagesFilteredByEmail(String filterText) {
        TypedQuery<Message> q = em.createQuery(
                "SELECT m FROM Message m WHERE m.email = :emailFilter",
                Message.class
        );

        q.setParameter("emailFilter", filterText);

        return q.getResultList();
    }

    public List<Message> getMessagesFilteredByName(String filterText) {
        TypedQuery<Message> q = em.createQuery(
                "SELECT m FROM Message m WHERE m.senderName = :nameFilter",
                Message.class
        );

        q.setParameter("nameFilter", filterText);

        return q.getResultList();
    }

    public List<Message> getMessagesFilteredByEmailWithLike(String filterText) {
        TypedQuery<Message> q = em.createQuery(
                "SELECT m FROM Message m WHERE m.email LIKE :emailFilter",
                Message.class
        );

        q.setParameter("emailFilter", "%" + filterText + "%");

        return q.getResultList();
    }

    public List<Message> getMessagesFilteredByNameWithLike(String filterText) {
        TypedQuery<Message> q = em.createQuery(
                "SELECT m FROM Message m WHERE m.senderName LIKE :nameFilter",
                Message.class
        );

        q.setParameter("nameFilter", "%" + filterText + "%");

        return q.getResultList();
    }

    public void deleteMessage(int id) {
        Message m = em.find(Message.class, id);

        if (m != null) {
            em.remove(m);
        }
    }

    public static ArrayList<Order> getOrders() {
        return orders;
    }

    public static void addOrder(Order order) {
        order.setId(orders.size());
        orders.add(order);
    }

    public static ArrayList<Product> getProducts() {
        return products;
    }

    public static ArrayList<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public static void addSubscription(Subscription subscription) {
        subscriptions.add(subscription);
    }

    public static List<String> getSubscribtionCategories() {
        return Arrays.asList("Новини", "Продукти");
    }

    public static List<String> getSubscribtionSubCategories(String subscribtionCategory) {
        if (subscribtionCategory.equals(getSubscribtionCategories().get(0))) {
            return Arrays.asList(
                    "Новини от последните 24 ч",
                    "Новини от последните 48 часа"
            );
        }

        return Arrays.asList(
                "Нови продукти",
                "Промоции на продукти"
        );
    }
}