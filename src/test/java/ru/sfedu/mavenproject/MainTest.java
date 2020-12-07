package ru.sfedu.mavenproject;

import org.junit.Test;

import java.io.IOException;

class MainTest {
    @Test
    void logBasicInfo() throws IOException {
        Main main = new Main();
        main.logBasicInfo();
    }

}