package ru.sfedu.mavenproject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.mavenproject.utils.ConfigurationUtil;

import java.io.IOException;

public class Main {

    //    logger
    private static Logger log = LogManager.getLogger(Main.class);
    //    initialization logger
    public  Main() {
        log.debug("ProjectApp: Starting application");
    }
    public static void logBasicInfo() throws IOException {
        log.debug(ConfigurationUtil.getConfigurationEntry(Constants.SOURCE));
        log.info("Launching application:");
        log.info("Operating System " + System.getProperty("os.name") + " " +
                System.getProperty("os.version"));
        log.info("JRE version " + System.getProperty("java.version"));
        log.info("Java launched from " + System.getProperty("java.home") );
        log.info("User Home Directory: " + System.getProperty("user.home"));
        log.info("Java Class :" + System.getProperty("java.class.path"));
        log.info("Java Library: " + System.getProperty("java.library.path"));
        log.info("Test INFO logging.");
    }

    public static void main(String[] args) throws IOException {
        log.debug("NAME");
        logBasicInfo();
        log.error("THIS IS ERROR");
    }
}