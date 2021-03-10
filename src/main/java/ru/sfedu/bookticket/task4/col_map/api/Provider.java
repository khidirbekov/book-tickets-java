package ru.sfedu.bookticket.task4.col_map.api;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.TransientObjectException;
import ru.sfedu.bookticket.Constants;
import ru.sfedu.bookticket.enums.ResponseStatus;
import ru.sfedu.bookticket.task4.col_map.model.Event;
import ru.sfedu.bookticket.utils.HibernateUtil;
import ru.sfedu.bookticket.utils.Response;

import java.util.Map;
import java.util.Optional;

public class Provider implements IProvider {
    public Response createEvent(String name, String price, Map<String, String> photos) {
        Event event = new Event();
        event.setName(name);
        event.setPrice(price);
        event.setPhotos(photos);
        save(event);
        return new Response(event, ResponseStatus.SUCCESS, Constants.CREATE_EVENT);
    }

    public Response updateEvent(long id, String name, String price, Map<String, String> photos) {
        try {
            Event event = getByID(Event.class, id).get();
            event.setName(name);
            event.setPrice(price);
            event.setPhotos(photos);
            update(event);
            return new Response(event, ResponseStatus.SUCCESS, Constants.SUCCESS_UPDATE_EVENT);
        } catch (NullPointerException e) {
            return new Response(null, ResponseStatus.ERROR, null);
        }
    }

    public Response getEvent(long id) {
        try {
            Event event = getByID(Event.class, id).get();
            return new Response(event, ResponseStatus.SUCCESS, null);
        } catch (NullPointerException e) {
            return new Response(null, ResponseStatus.ERROR, null);
        }
    }

    public Response deleteEvent(long id) {
        try {
            delete(Event.class, id);
            return new Response(null, ResponseStatus.SUCCESS, null);
        } catch (NullPointerException e) {
            return new Response(null, ResponseStatus.ERROR, null);
        }
    }

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
        T entity = getByID(entityClass, id).get();
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(entity);
        transaction.commit();
        session.close();
        return true;
    }
}
