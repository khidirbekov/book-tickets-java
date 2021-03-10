package ru.sfedu.bookticket.task1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import ru.sfedu.bookticket.Constants;
import ru.sfedu.bookticket.utils.HibernateUtil;

import java.io.IOException;
import java.util.List;

public class DataProviderHibernate implements DataProvider {
    private Logger log = LogManager.getLogger(DataProviderHibernate.class);


    private Session getSession() throws IOException {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        return sessionFactory.openSession();
    }

    @Override
    public String getCurrentUser() {
        try {
            Session session = this.getSession();
            NativeQuery query = session.createSQLQuery(Constants.SQL_CURRENT_USER);
            String user = query.getResultList().get(0).toString();
            log.debug("Current user: "+ user);
            session.close();
            return user;
        } catch (IOException e) {
            log.error(e);
            return null;
        }

    }

    @Override
    public List getDBSizes() {
        try {
            Session session = this.getSession();
            NativeQuery query = session.createSQLQuery(Constants.SQL_DB_SIZES);
            List sizes = query.getResultList();
            session.close();
            log.debug("DB size: " + sizes.toString());
            return sizes;
        }
        catch (IOException e) {
            log.error(e);
            return null;
        }

    }

    @Override
    public List getDBNames() {
        try {
            Session session = this.getSession();
            NativeQuery query = session.createSQLQuery(Constants.SQL_DB_NAMES);
            List names = query.getResultList();
            session.close();
            log.debug("DB names: " + names.toString());
            return names;
        }
        catch (IOException e) {
            log.error(e);
            return null;
        }

    }

    @Override
    public List getAllSchemas() {
        try {
            Session session = this.getSession();
            NativeQuery query = session.createSQLQuery(Constants.SQL_ALL_SCHEMAS);
            List resList = query.getResultList();
            log.debug(resList);
            session.close();
            return resList;
        }
        catch (IOException e) {
            log.error(e);
            return null;
        }
    }
}
