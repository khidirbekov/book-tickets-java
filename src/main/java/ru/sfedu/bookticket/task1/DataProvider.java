package ru.sfedu.bookticket.task1;

import java.util.List;

public interface DataProvider {
    List getDBSizes();
    List getDBNames();
    List getAllSchemas();
    String getCurrentUser();
}
