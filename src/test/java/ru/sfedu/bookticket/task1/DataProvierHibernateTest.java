package ru.sfedu.bookticket.task1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sfedu.bookticket.BaseTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataProviderHibernateTest extends BaseTest {
    DataProviderHibernate provider = new DataProviderHibernate();

    @Test
    void getCurrentUser() {
        String result = provider.getCurrentUser();
        assertNotNull(result);
    }

    @Test
    void getDBSizes() {
        List result = provider.getDBSizes();
        assertNotNull(result);
    }

    @Test
    void getDBNames() {
        List result = provider.getDBNames();
        assertNotNull(result);
    }

    @Test
    void getAllSchemas() {
        List result = provider.getAllSchemas();
        assertNotNull(result);
    }
}