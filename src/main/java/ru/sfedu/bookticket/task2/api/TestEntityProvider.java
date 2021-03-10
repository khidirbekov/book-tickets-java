package ru.sfedu.bookticket.task2.api;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.TransientObjectException;
import ru.sfedu.bookticket.task2.model.TestEntity;
import ru.sfedu.bookticket.utils.HibernateUtil;

import java.io.IOException;
import java.util.Optional;

public class TestEntityProvider implements ITestEntityProvider {
    private Session getSession() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        return sessionFactory.openSession();
    }

    @Override
    public Optional<TestEntity> getByID(Class<TestEntity> entity, long id) {
            try {
                Session session = this.getSession();
                TestEntity testEntity = session.get(entity, id);
                session.close();
                return Optional.of(testEntity);
            } catch (NullPointerException e) {
                return null;
            }
    }

    @Override
    public Long save(TestEntity entity) {
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
    public boolean update(TestEntity entity) {
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
    public boolean delete(Long id) {
        try {
            TestEntity testEntity = getByID(TestEntity.class, id).get();
            Session session = this.getSession();
            Transaction transaction = session.beginTransaction();
            session.delete(testEntity);
            transaction.commit();
            session.close();
            return true;
        } catch (NullPointerException e) {
            return false;
        }
    }
}
