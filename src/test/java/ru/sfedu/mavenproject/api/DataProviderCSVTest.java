package ru.sfedu.mavenproject.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sfedu.mavenproject.enums.ResponseStatus;
import ru.sfedu.mavenproject.models.*;
import ru.sfedu.mavenproject.utils.Response;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataProviderCSVTest {
    private DataProviderCSV provider = new DataProviderCSV();

    DataProviderCSVTest() throws IOException {
    }

    @BeforeEach
    public void initTestData() throws IOException {
        provider.flushFile(FootballEvent.class);
        provider.flushFile(ITEvent.class);
        provider.flushFile(MusicEvent.class);
        provider.flushFile(City.class);
        provider.flushFile(Place.class);
        provider.flushFile(Photo.class);
        provider.flushFile(Photographer.class);
        provider.flushFile(Ticket.class);
    }


    @Test
    void createCitySuccess() throws Exception {
        Response response = provider.createCity("test");
        City city = (City) response.getBody();
        List<City> cities = provider.getList(City.class);
        boolean isExist = cities.stream().filter(el -> el.getId() == city.getId()).findFirst().isPresent();

        assertEquals(isExist, true);
        assertEquals(response.getStatus(), ResponseStatus.SUCCESS);
    }

    @Test
    void createCityFail() throws Exception {
        Response response = provider.createCity("");
        assertEquals(response.getStatus(), ResponseStatus.ERROR);
    }

    @Test
    void getEventSuccess() {
    }
    @Test
    void getEventFail() {
    }

    @Test
    void getFootballEventSuccess() {
    }

    @Test
    void getFootballEventFail() {
    }

    @Test
    void getITEventSuccess() {
    }

    @Test
    void getITEventFail() {
    }

    @Test
    void getMusicEventSuccess() {
    }

    @Test
    void getMusicEventFail() {
    }

    @Test
    void bookTicketSuccess() {
    }

    @Test
    void bookTicketFail() {
    }

    @Test
    void getTicketSuccess() {
    }

    @Test
    void getTicketFail() {
    }

    @Test
    void getCitySuccess() {
    }

    @Test
    void getCityFail() {
    }


    @Test
    void updateCitySuccess() {
    }

    @Test
    void updateCityFail() {
    }

    @Test
    void deleteCitySuccess() {
    }

    @Test
    void deleteCityFail() {
    }

    @Test
    void updateEventSuccess() {
    }

    @Test
    void updateEventFail() {
    }

    @Test
    void updateFootballEventSuccess() {
    }

    @Test
    void updateFootballEventFail() {
    }

    @Test
    void updateITEventSuccess() {
    }

    @Test
    void updateITEventFail() {
    }

    @Test
    void updateMusicEventSuccess() {
    }

    @Test
    void updateMusicEventFail() {
    }

    @Test
    void createEventSuccess() {
    }

    @Test
    void createEventFail() {
    }

    @Test
    void createFootballEventSuccess() {
    }

    @Test
    void createFootballEventFail() {
    }

    @Test
    void createITEventSuccess() {
    }

    @Test
    void createITEventFail() {
    }

    @Test
    void createMusicEventSuccess() {
    }

    @Test
    void createMusicEventFail() {
    }

    @Test
    void createPlaceSuccess() throws IOException {
//        DataProviderCSV provider = new DataProviderCSV();
//        Place place = (City) provider.createPlace("test").getBody();
//        List<City> cities = provider.getList(City.class);
//        boolean isExist = cities.stream().filter(el -> el.getId() == city.getId()).findFirst().isPresent();
//
//        assertEquals(isExist, true);
    }

    @Test
    void createPlaceFail() {
    }

    @Test
    void updatePlaceSuccess() {
    }

    @Test
    void updatePlaceFail() {
    }

    @Test
    void deletePlaceSuccess() {
    }

    @Test
    void deletePlaceFail() {
    }

    @Test
    void getPlaceSuccess() {
    }

    @Test
    void getPlaceFail() {
    }

    @Test
    void createPhotoSuccess() {
    }

    @Test
    void createPhotoFail() {
    }

    @Test
    void deletePhotoSuccess() {
    }

    @Test
    void deletePhotoFail() {
    }

    @Test
    void getPhotoSuccess() {
    }

    @Test
    void getPhotoFail() {
    }

    @Test
    void createPhotographerSuccess() {
    }

    @Test
    void createPhotographerFail() {
    }

    @Test
    void updatePhotographerSuccess() {
    }

    @Test
    void updatePhotographerFail() {
    }

    @Test
    void deletePhotographerSuccess() {
    }

    @Test
    void deletePhotographerFail() {
    }

    @Test
    void getPhotographerSuccess() {
    }

    @Test
    void getPhotographerFail() {
    }

    @Test
    void deleteEventSuccess() {
    }

    @Test
    void deleteEventFail() {
    }
}