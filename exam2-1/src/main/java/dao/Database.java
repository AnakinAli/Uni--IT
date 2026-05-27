package dao;

import entities.Shipment;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

@Stateless
public class Database {

    @PersistenceContext(unitName = "JPADefaultDataBaseConnection")
    private EntityManager em;

    public void addShipment(Shipment shipment) {
        em.persist(shipment);
    }
    public List<Shipment> getShipments() {
        TypedQuery<Shipment> query = em.createQuery(
                "SELECT s FROM Shipment s", Shipment.class);
        return query.getResultList();
    }
    public List<Shipment> getDeliveredShipments() {
        TypedQuery<Shipment> query = em.createQuery(
                "SELECT s FROM Shipment s WHERE s.delivered = true", Shipment.class);
        return query.getResultList();
    }

    public List<Shipment> getUndeliveredShipments() {
        TypedQuery<Shipment> query = em.createQuery(
                "SELECT s FROM Shipment s WHERE s.delivered = false", Shipment.class);
        return query.getResultList();
    }

    public void markAsDelivered(int id) {
        Shipment shipment = em.find(Shipment.class, id);
        if (shipment != null) {
            shipment.setDelivered(true);
        }
    }
}
