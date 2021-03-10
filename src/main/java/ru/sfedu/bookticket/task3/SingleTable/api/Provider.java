package ru.sfedu.bookticket.task3.SingleTable.api;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.TransientObjectException;
import ru.sfedu.bookticket.utils.HibernateUtil;

import java.util.Optional;

public class Provider implements IProvider {
    private Session getSession() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        return sessionFactory.openSession();
    }

    @Override
    public <T> Optional<T> getByID(Class<T> entityClass, long id) {
        try {
            Session session = this.getSession();
            T entity = session.get(entityClass, id);
            session.close();
            return Optional.of(entity);
        } catch (NullPointerException e) {
            return null;
        }
    }

    @Override
    public <T> Long save(T entity) {
        try {
            Session session = this.getSession();
            Transaction transaction = session.beginTransaction();
            Long id = (Long) session.save(entity);
            transaction.commit();
            session.close();
            return id;
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    @Override
    public <T> boolean update(T entity) {
        try {
            Session session = this.getSession();
            Transaction transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
            session.close();
            return true;
        } catch (TransientObjectException e) {
            return false;
        }
    }

    @Override
    public <T> boolean delete(Class<T> entityClass, Long id) {
        try {
            T entity = getByID(entityClass, id).get();
            Session session = this.getSession();
            Transaction transaction = session.beginTransaction();
            session.delete(entity);
            transaction.commit();
            session.close();
            return true;
        } catch (NullPointerException e) {
            return false;
        }
    }
}
