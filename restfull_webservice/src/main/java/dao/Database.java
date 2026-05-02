package dao;

import beans.User;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

@Stateless
public class Database {

    @PersistenceContext(unitName = "JPADefaultDataBaseConnection")
    private EntityManager em;

    public void addUser(User u) {
        em.persist(u);
    }

    public List<User> getAllUsers() {
        TypedQuery<User> query = em.createQuery(
                "SELECT u FROM User u ORDER BY u.id",
                User.class
        );

        return query.getResultList();
    }

    public User getUserById(int id) {
        return em.find(User.class, id);
    }

    public User deleteUserById(int id) {
        User user = getUserById(id);

        if (user != null) {
            em.remove(user);
        }

        return user;
    }

    public User editUser(User u) {
        return em.merge(u);
    }
}