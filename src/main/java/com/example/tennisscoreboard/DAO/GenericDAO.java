package com.example.tennisscoreboard.DAO;

import com.example.tennisscoreboard.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public abstract class GenericDAO<T, ID> {
    private final Class<T> entityClass;

    protected GenericDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public void save(T entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
    }

    public T findById(ID id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        T entity = session.get(entityClass, (Integer) id);
        session.close();
        return entity;
    }

    public List<T> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<T> entities = session.createQuery("FROM " + entityClass.getSimpleName(), entityClass).list();
        session.close();
        return entities;
    }

    public int countAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Long count = session.createQuery("SELECT COUNT(e) FROM " + entityClass.getSimpleName() + " e", Long.class)
                .uniqueResult();
        session.close();
        return count.intValue();
    }

    public void delete(T entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(entity);
        transaction.commit();
        session.close();
    }
}
