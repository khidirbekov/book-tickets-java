package ru.sfedu.bookticket.utils;

import com.sun.xml.bind.v2.runtime.reflect.opt.Const;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import ru.sfedu.bookticket.Constants;
import ru.sfedu.bookticket.api.DataProviderCSV;
import ru.sfedu.bookticket.task2.model.Embedded;
import ru.sfedu.bookticket.task2.model.TestEntity;
import ru.sfedu.bookticket.task3.JoinedTable.model.Event;
import ru.sfedu.bookticket.task3.JoinedTable.model.FootballEvent;
import ru.sfedu.bookticket.task3.JoinedTable.model.MusicEvent;
import ru.sfedu.bookticket.task4.col_comp_map.model.Photo;

import java.io.File;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    private static final String CONFIG_PATH = System.getProperty(Constants.ENV_HIBERNATE);
    private static final String DEFAULT_CONFIG_PATH = Constants.DEFAULT_HIBERNATE_CONFIG;

    /**
     * Создание фабрики
     *
     */
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            // loads configuration and mappings
            File file;
            if (CONFIG_PATH == null) {
                file = new File(DEFAULT_CONFIG_PATH);
            } else {
                file = new File(CONFIG_PATH);
            }
            Configuration configuration = new Configuration().configure(file);
            ServiceRegistry serviceRegistry
                    = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();


            MetadataSources metadataSources =
                    new MetadataSources(serviceRegistry);

            addEntities(metadataSources);
//            metadataSources.addAnnotatedClass(.class);// Аннотированная сущность
//            metadataSources.addResource("named-queries.hbm.xml");// Именованные запросы
            sessionFactory = metadataSources.buildMetadata().buildSessionFactory();
        }

        return sessionFactory;
    }

    private static void addEntities(MetadataSources metadataSources) {
        metadataSources.addAnnotatedClass(TestEntity.class);
        metadataSources.addAnnotatedClass(Event.class);
        metadataSources.addAnnotatedClass(ru.sfedu.bookticket.task3.SingleTable.model.Event.class);
        metadataSources.addAnnotatedClass(ru.sfedu.bookticket.task3.MappedSuperclass.model.Event.class);
        metadataSources.addAnnotatedClass(ru.sfedu.bookticket.task3.TablePerClass.model.Event.class);
        metadataSources.addAnnotatedClass(ru.sfedu.bookticket.task4.col_map.model.Event.class);
        metadataSources.addAnnotatedClass(ru.sfedu.bookticket.task4.col_comp_map.model.Event.class);
        metadataSources.addAnnotatedClass(ru.sfedu.bookticket.task4.col_component.model.Event.class);
        metadataSources.addAnnotatedClass(ru.sfedu.bookticket.task4.col_set.model.Event.class);
        metadataSources.addAnnotatedClass(ru.sfedu.bookticket.task4.col_list.model.Event.class);
        metadataSources.addAnnotatedClass(FootballEvent.class);
        metadataSources.addAnnotatedClass(ru.sfedu.bookticket.task3.SingleTable.model.FootballEvent.class);
        metadataSources.addAnnotatedClass(ru.sfedu.bookticket.task3.TablePerClass.model.FootballEvent.class);
        metadataSources.addAnnotatedClass(ru.sfedu.bookticket.task3.MappedSuperclass.model.FootballEvent.class);
        metadataSources.addAnnotatedClass(MusicEvent.class);
        metadataSources.addAnnotatedClass(ru.sfedu.bookticket.task3.SingleTable.model.MusicEvent.class);
        metadataSources.addAnnotatedClass(ru.sfedu.bookticket.task3.TablePerClass.model.MusicEvent.class);
        metadataSources.addAnnotatedClass(ru.sfedu.bookticket.task3.MappedSuperclass.model.MusicEvent.class);
        metadataSources.addAnnotatedClass(Photo.class);
        metadataSources.addAnnotatedClass(ru.sfedu.bookticket.task4.col_component.model.Photo.class);
    }
}
