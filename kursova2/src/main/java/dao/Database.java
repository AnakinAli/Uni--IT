package dao;

import entities.Vehicle;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

@Stateless
public class Database {

    @PersistenceContext(unitName = "JPADefaultDataBaseConnection")
    private EntityManager em;

    public void addVehicle(Vehicle vehicle) {
        em.persist(vehicle);
    }

    public List<Vehicle> getVehicles() {
        TypedQuery<Vehicle> query = em.createQuery(
                "SELECT v FROM Vehicle v",
                Vehicle.class
        );

        return query.getResultList();
    }

    public Vehicle getVehicleById(int id) {
        return em.find(Vehicle.class, id);
    }
}