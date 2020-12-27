package ru.sfedu.mavenproject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.mavenproject.api.DataProviderXML;
import ru.sfedu.mavenproject.utils.Response;

public class Main {

    //    logger
    private static Logger log = LogManager.getLogger(Main.class);
    //    initialization logger
    public  Main() {

    }


    public static void main(String[] args) throws Exception {
        DataProviderXML provider = new DataProviderXML();
//        provider.createPlace("Myurego", 2845650);
        Response rostov = provider.deleteEvent(8093772, "FOOTBALL");
//        Report test = provider.getEvent(8093772, "FOOTBALL");
    }
}