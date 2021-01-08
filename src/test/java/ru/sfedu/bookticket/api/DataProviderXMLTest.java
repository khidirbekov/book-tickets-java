package ru.sfedu.bookticket.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sfedu.bookticket.BaseTest;
import ru.sfedu.bookticket.Constants;
import ru.sfedu.bookticket.enums.ResponseStatus;
import ru.sfedu.bookticket.models.*;
import ru.sfedu.bookticket.utils.BaseUtil;
import ru.sfedu.bookticket.utils.ConfigurationUtil;
import ru.sfedu.bookticket.utils.Response;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class DataProviderXMLTest extends BaseTest {
    private DataProviderXML provider = new DataProviderXML();

    DataProviderXMLTest() throws IOException {
        this.provider.setPATH(ConfigurationUtil.getConfigurationEntry(Constants.XML_TEST_PATH));
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
        provider.setList(BaseUtil.generateCities(), City.class);
        provider.setList(BaseUtil.generatePlaces(), Place.class);
        provider.setList(BaseUtil.generatePhotographers(), Photographer.class);
        provider.setList(BaseUtil.generatePhoto(), Photo.class);
        provider.setList(BaseUtil.generateFootballEvent(), FootballEvent.class);
        provider.setList(BaseUtil.generateITEvent(), ITEvent.class);
        provider.setList(BaseUtil.generateMusicEvent(), MusicEvent.class);
        provider.setList(BaseUtil.generateTickets(), Ticket.class);
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
    void getEventSuccess() throws Exception {
        Response response1 = provider.getEvent(1, "FOOTBALL");
        assertEquals(response1.getStatus(), ResponseStatus.SUCCESS);
        Response response2 = provider.getEvent(2, "IT");
        assertEquals(response2.getStatus(), ResponseStatus.SUCCESS);
        Response response3 = provider.getEvent(3, "MUSIC");
        assertEquals(response3.getStatus(), ResponseStatus.SUCCESS);
    }
    @Test
    void getEventFail() throws Exception {
        Response response1 = provider.getEvent(0, "FOOTBALL");
        assertEquals(response1.getStatus(), ResponseStatus.ERROR);
        Response response2 = provider.getEvent(0, "IT");
        assertEquals(response2.getStatus(), ResponseStatus.ERROR);
        Response response3 = provider.getEvent(0, "MUSIC");
        assertEquals(response3.getStatus(), ResponseStatus.ERROR);
        Response response4 = provider.getEvent(0, "WRONG");
        assertEquals(response4.getStatus(), ResponseStatus.ERROR);
        Response response5 = provider.getEvent(1, "WRONG");
        assertEquals(response5.getStatus(), ResponseStatus.ERROR);
    }

    @Test
    void getFootballEventSuccess() throws Exception {
        Response response = provider.getFootballEvent(1);
        assertEquals(response.getStatus(), ResponseStatus.SUCCESS);
    }

    @Test
    void getFootballEventFail() throws Exception {
        Response response = provider.getFootballEvent(0);
        assertEquals(response.getStatus(), ResponseStatus.ERROR);
    }

    @Test
    void getITEventSuccess() throws Exception {
        Response response = provider.getITEvent(2);
        assertEquals(response.getStatus(), ResponseStatus.SUCCESS);
    }

    @Test
    void getITEventFail() throws Exception {
        Response response = provider.getFootballEvent(0);
        assertEquals(response.getStatus(), ResponseStatus.ERROR);
    }

    @Test
    void getMusicEventSuccess() throws Exception {
        Response response = provider.getMusicEvent(3);
        assertEquals(response.getStatus(), ResponseStatus.SUCCESS);
    }

    @Test
    void getMusicEventFail() throws Exception {
        Response response = provider.getFootballEvent(0);
        assertEquals(response.getStatus(), ResponseStatus.ERROR);
    }

    @Test
    void bookTicketSuccess() throws Exception {
        Response response1 = provider.bookTicket(1, "FOOTBALL", "test");
        Response response2 = provider.bookTicket(2, "IT", "test");
        Response response3 = provider.bookTicket(3, "MUSIC", "test");

        assertEquals(response1.getStatus(), ResponseStatus.SUCCESS);
        assertEquals(response2.getStatus(), ResponseStatus.SUCCESS);
        assertEquals(response3.getStatus(), ResponseStatus.SUCCESS);
    }

    @Test
    void bookTicketFail() throws Exception {
        Response response1 = provider.bookTicket(0, "FOOTBALL", "test");
        assertEquals(response1.getStatus(), ResponseStatus.ERROR);
        Response response2 = provider.bookTicket(0, "IT", "test");
        assertEquals(response2.getStatus(), ResponseStatus.ERROR);
        Response response3 = provider.bookTicket(0, "MUSIC", "test");
        assertEquals(response3.getStatus(), ResponseStatus.ERROR);
        Response response4 = provider.bookTicket(1, "WRONG", "test");
        assertEquals(response4.getStatus(), ResponseStatus.ERROR);
    }

    @Test
    void getTicketSuccess() throws Exception {
        Response response = provider.getTicket(1);
        assertEquals(response.getStatus(), ResponseStatus.SUCCESS);
    }

    @Test
    void getTicketFail() throws Exception {
        Response response = provider.getTicket(0);
        assertEquals(response.getStatus(), ResponseStatus.ERROR);
    }

    @Test
    void getCitySuccess() throws Exception {
        Response response = provider.getCity(1);
        assertEquals(response.getStatus(), ResponseStatus.SUCCESS);
    }

    @Test
    void getCityFail() throws Exception {
        Response response = provider.getCity(0);
        assertEquals(response.getStatus(), ResponseStatus.ERROR);
    }


    @Test
    void updateCitySuccess() throws Exception {
        Response response = provider.updateCity(1, "test1");
        assertEquals(response.getStatus(), ResponseStatus.SUCCESS);
    }

    @Test
    void updateCityFail() throws Exception {
        Response response = provider.updateCity(0, "test1");
        assertEquals(response.getStatus(), ResponseStatus.ERROR);
    }

    @Test
    void deleteCitySuccess() throws Exception {
        Response response = provider.deleteCity(1);
        assertEquals(response.getStatus(), ResponseStatus.SUCCESS);
    }

    @Test
    void deleteCityFail() throws Exception {
        Response response = provider.deleteCity(0);
        assertEquals(response.getStatus(), ResponseStatus.ERROR);
    }

    @Test
    void updateEventSuccess() throws Exception {
        List<String> photos = new BaseUtil<String>().transformStringArgumentsToList("2, 3");
        Response response1 = provider.updateEvent(
                "FOOTBALL",
                1,
                "test1",
                1,
                "01-01-1971",
                "00:01",
                "200",
                photos,
                "Rostov, Moscow");
        assertEquals(response1.getStatus(), ResponseStatus.SUCCESS);
        Response response2 = provider.updateEvent(
                "IT",
                2,
                "test1",
                1,
                "01-01-1971",
                "00:01",
                "200",
                photos,
                "Rostov, Moscow");
        assertEquals(response2.getStatus(), ResponseStatus.SUCCESS);
        Response response3 = provider.updateEvent(
                "MUSIC",
                3,
                "test1",
                1,
                "01-01-1971",
                "00:01",
                "200",
                photos,
                "Rostov, Moscow");
        assertEquals(response3.getStatus(), ResponseStatus.SUCCESS);
    }

    @Test
    void updateEventFail() throws Exception {
        List<String> photos = new BaseUtil<String>().transformStringArgumentsToList("2, 3");

        Response response = provider.updateEvent(
                "WRONG",
                1,
                "test1",
                1,
                "01-01-1971",
                "00:01",
                "200",
                photos,
                "Rostov, Moscow");
        assertEquals(response.getStatus(), ResponseStatus.ERROR);
    }

    @Test
    void updateFootballEventSuccess() throws Exception {
        List<String> photos = new BaseUtil<String>().transformStringArgumentsToList("2, 3");
        Response response = provider.updateFootballEvent(
                1,
                "test1",
                1,
                "01-01-1971",
                "00:01",
                "200",
                photos,
                "Rostov",
                "Moscow");
        assertEquals(response.getStatus(), ResponseStatus.SUCCESS);
    }

    @Test
    void updateFootballEventFail() throws Exception {
        List<String> photos = new BaseUtil<String>().transformStringArgumentsToList("2, 3");
        Response response1 = provider.updateFootballEvent(
                0,
                "test1",
                1,
                "01-01-1971",
                "00:01",
                "200",
                photos,
                "Rostov",
                "Moscow");
        assertEquals(response1.getStatus(), ResponseStatus.ERROR);
        Response response2 = provider.updateFootballEvent(
                1,
                "test1",
                0,
                "01-01-1971",
                "00:01",
                "200",
                photos,
                "Rostov",
                "Moscow");
        assertEquals(response2.getStatus(), ResponseStatus.ERROR);
    }

    @Test
    void updateITEventSuccess() throws Exception {
        List<String> photos = new BaseUtil<String>().transformStringArgumentsToList("2, 3");
        Response response = provider.updateITEvent(
                2,
                "test1",
                1,
                "01-01-1971",
                "00:01",
                "200",
                photos,
                "Backend");
        assertEquals(response.getStatus(), ResponseStatus.SUCCESS);
    }

    @Test
    void updateITEventFail() throws Exception {
        List<String> photos = new BaseUtil<String>().transformStringArgumentsToList("2, 3");
        Response response1 = provider.updateITEvent(
                0,
                "test1",
                1,
                "01-01-1971",
                "00:01",
                "200",
                photos,
                "Backend");
        assertEquals(response1.getStatus(), ResponseStatus.ERROR);
        Response response2 = provider.updateITEvent(
                0,
                "test1",
                1,
                "01-01-1971",
                "00:01",
                "200",
                photos,
                "Backend");
        assertEquals(response2.getStatus(), ResponseStatus.ERROR);
    }

    @Test
    void updateMusicEventSuccess() throws Exception {
        List<String> photos = new BaseUtil<String>().transformStringArgumentsToList("2, 3");
        Response response = provider.updateMusicEvent(
                3,
                "test1",
                1,
                "01-01-1971",
                "00:01",
                "200",
                photos,
                "David Guetta");
        assertEquals(response.getStatus(), ResponseStatus.SUCCESS);
    }

    @Test
    void updateMusicEventFail() throws Exception {
        List<String> photos = new BaseUtil<String>().transformStringArgumentsToList("2, 3");
        Response response1 = provider.updateMusicEvent(
                0,
                "test1",
                1,
                "01-01-1971",
                "00:01",
                "200",
                photos,
                "David Guetta");
        assertEquals(response1.getStatus(), ResponseStatus.ERROR);
        Response response2 = provider.updateITEvent(
                0,
                "test1",
                1,
                "01-01-1971",
                "00:01",
                "200",
                photos,
                "David Guetta");
        assertEquals(response2.getStatus(), ResponseStatus.ERROR);
    }

    @Test
    void createEventSuccess() throws Exception {
        List<String> photos = new BaseUtil<String>().transformStringArgumentsToList("2, 3");
        Response response1 = provider.createEvent(
                "FOOTBALL",
                "test1",
                1,
                "01-01-1971",
                "00:01",
                "200",
                photos,
                "Rostov, Moscow");
        assertEquals(response1.getStatus(), ResponseStatus.SUCCESS);
        Response response2 = provider.createEvent(
                "IT",
                "test1",
                1,
                "01-01-1971",
                "00:01",
                "200",
                photos,
                "Rostov, Moscow");
        assertEquals(response2.getStatus(), ResponseStatus.SUCCESS);
        Response response3 = provider.createEvent(
                "MUSIC",
                "test1",
                1,
                "01-01-1971",
                "00:01",
                "200",
                photos,
                "Rostov, Moscow");
        assertEquals(response3.getStatus(), ResponseStatus.SUCCESS);
    }

    @Test
    void createEventFail() throws Exception {
        List<String> photos = new BaseUtil<String>().transformStringArgumentsToList("2, 3");
        Response response = provider.createEvent(
                "WRONG",
                "test1",
                1,
                "01-01-1971",
                "00:01",
                "200",
                photos,
                "Rostov, Moscow");
        assertEquals(response.getStatus(), ResponseStatus.ERROR);
    }

    @Test
    void createFootballEventSuccess() throws Exception {
        List<String> photos = new BaseUtil<String>().transformStringArgumentsToList("2, 3");
        Response response = provider.createFootballEvent(
                "test1",
                1,
                "01-01-1971",
                "00:01",
                "200",
                photos,
                "Rostov",
                "Moscow");
        assertEquals(response.getStatus(), ResponseStatus.SUCCESS);
    }

    @Test
    void createFootballEventFail() throws Exception {
        List<String> photos = new BaseUtil<String>().transformStringArgumentsToList("2, 3");
        Response response2 = provider.createFootballEvent(
                "test1",
                0,
                "01-01-1971",
                "00:01",
                "200",
                photos,
                "Rostov",
                "Moscow");
        assertEquals(response2.getStatus(), ResponseStatus.ERROR);
    }

    @Test
    void createITEventSuccess() throws Exception {
        List<String> photos = new BaseUtil<String>().transformStringArgumentsToList("2, 3");
        Response response = provider.createITEvent(
                "test1",
                1,
                "01-01-1971",
                "00:01",
                "200",
                photos,
                "Backend");
        assertEquals(response.getStatus(), ResponseStatus.SUCCESS);
    }

    @Test
    void createITEventFail() throws Exception {
        List<String> photos = new BaseUtil<String>().transformStringArgumentsToList("2, 3");
        Response response2 = provider.createITEvent(
                "test1",
                0,
                "01-01-1971",
                "00:01",
                "200",
                photos,
                "Backend");
        assertEquals(response2.getStatus(), ResponseStatus.ERROR);
    }

    @Test
    void createMusicEventSuccess() throws Exception {
        List<String> photos = new BaseUtil<String>().transformStringArgumentsToList("2, 3");
        Response response = provider.createMusicEvent(
                "test1",
                1,
                "01-01-1971",
                "00:01",
                "200",
                photos,
                "Akon");
        assertEquals(response.getStatus(), ResponseStatus.SUCCESS);
    }

    @Test
    void createMusicEventFail() throws Exception {
        List<String> photos = new BaseUtil<String>().transformStringArgumentsToList("2, 3");
        Response response = provider.createMusicEvent(
                "test1",
                0,
                "01-01-1971",
                "00:01",
                "200",
                photos,
                "Akon");
        assertEquals(response.getStatus(), ResponseStatus.ERROR);
    }

    @Test
    void createPlaceSuccess() throws Exception {
        Response response = provider.createPlace("Moscow", 1);
        assertEquals(response.getStatus(), ResponseStatus.SUCCESS);
    }

    @Test
    void createPlaceFail() throws Exception {
        Response response = provider.createPlace("Moscow", 0);
        assertEquals(response.getStatus(), ResponseStatus.ERROR);
    }

    @Test
    void updatePlaceSuccess() throws Exception {
        Response response = provider.updatePlace(1, "Moscow", 1);
        assertEquals(response.getStatus(), ResponseStatus.SUCCESS);
    }

    @Test
    void updatePlaceFail() throws Exception {
        Response response1 = provider.updatePlace(0, "Moscow", 1);
        assertEquals(response1.getStatus(), ResponseStatus.ERROR);
        Response response2 = provider.updatePlace(1, "Moscow", 0);
        assertEquals(response2.getStatus(), ResponseStatus.ERROR);
    }

    @Test
    void deletePlaceSuccess() throws Exception {
        Response response = provider.deletePlace(1);
        assertEquals(response.getStatus(), ResponseStatus.SUCCESS);
    }

    @Test
    void deletePlaceFail() throws Exception {
        Response response = provider.deletePlace(0);
        assertEquals(response.getStatus(), ResponseStatus.ERROR);
    }

    @Test
    void getPlaceSuccess() throws Exception {
        Response response = provider.getPlace(1);
        assertEquals(response.getStatus(), ResponseStatus.SUCCESS);
        assertNotEquals(null, response.getBody());
    }

    @Test
    void getPlaceFail() throws Exception {
        Response response = provider.getPlace(0);
        assertEquals(response.getStatus(), ResponseStatus.ERROR);
    }

    @Test
    void createPhotoSuccess() throws Exception {
        Response response = provider.createPhoto("test", "google.com", 1);
        assertEquals(response.getStatus(), ResponseStatus.SUCCESS);
        assertNotEquals(null, response.getBody());
    }

    @Test
    void createPhotoFail() throws Exception {
        Response response = provider.createPhoto("test", "google.com", 0);
        assertEquals(response.getStatus(), ResponseStatus.ERROR);
        assertEquals(null, response.getBody());
    }

    @Test
    void deletePhotoSuccess() throws Exception {
        Response response = provider.deletePhoto(1);
        assertEquals(response.getStatus(), ResponseStatus.SUCCESS);
    }

    @Test
    void deletePhotoFail() throws Exception {
        Response response = provider.deletePhoto(0);
        assertEquals(response.getStatus(), ResponseStatus.ERROR);
        assertEquals(null, response.getBody());
    }

    @Test
    void getPhotoSuccess() throws Exception {
        Response response = provider.getPhoto(1);
        assertEquals(response.getStatus(), ResponseStatus.SUCCESS);
        assertNotEquals(null, response.getBody());
    }

    @Test
    void getPhotoFail() throws Exception {
        Response response = provider.getPhoto(0);
        assertEquals(response.getStatus(), ResponseStatus.ERROR);
        assertEquals(null, response.getBody());
    }

    @Test
    void createPhotographerSuccess() throws Exception {
        Response response = provider.createPhotographer("test", 100, "vk.com");
        assertEquals(response.getStatus(), ResponseStatus.SUCCESS);
        assertNotEquals(null, response.getBody());
    }

    @Test
    void createPhotographerFail() throws Exception {
        Response response = provider.createPhotographer("", 100, "vk.com");
        assertEquals(response.getStatus(), ResponseStatus.ERROR);
        assertEquals(null, response.getBody());
    }

    @Test
    void updatePhotographerSuccess() throws Exception {
        Response response = provider.updatePhotographer(1, "test", 100, "vk.com");
        assertEquals(response.getStatus(), ResponseStatus.SUCCESS);
        assertNotEquals(null, response.getBody());
    }

    @Test
    void updatePhotographerFail() throws Exception {
        Response response1 = provider.updatePhotographer(0,"test", 100, "vk.com");
        assertEquals(response1.getStatus(), ResponseStatus.ERROR);
        assertEquals(null, response1.getBody());
        Response response2 = provider.updatePhotographer(1,"", 100, "vk.com");
        assertEquals(response2.getStatus(), ResponseStatus.ERROR);
        assertEquals(null, response2.getBody());
    }

    @Test
    void deletePhotographerSuccess() throws Exception {
        Response response = provider.deletePhotographer(1);
        assertEquals(response.getStatus(), ResponseStatus.SUCCESS);
    }

    @Test
    void deletePhotographerFail() throws Exception {
        Response response = provider.deletePhotographer(0);
        assertEquals(response.getStatus(), ResponseStatus.ERROR);
        assertEquals(null, response.getBody());
    }

    @Test
    void getPhotographerSuccess() throws Exception {
        Response response = provider.getPhotographer(1);
        assertEquals(response.getStatus(), ResponseStatus.SUCCESS);
        assertNotEquals(null, response.getBody());
    }

    @Test
    void getPhotographerFail() throws Exception {
        Response response = provider.getPhotographer(0);
        assertEquals(response.getStatus(), ResponseStatus.ERROR);
        assertEquals(null, response.getBody());
    }

    @Test
    void deleteEventSuccess() throws Exception {
        Response response1 = provider.deleteEvent(1, "FOOTBALL");
        Response response2 = provider.deleteEvent(2, "IT");
        Response response3 = provider.deleteEvent(3, "MUSIC");

        assertEquals(response1.getStatus(), ResponseStatus.SUCCESS);
        assertEquals(response2.getStatus(), ResponseStatus.SUCCESS);
        assertEquals(response3.getStatus(), ResponseStatus.SUCCESS);
    }

    @Test
    void deleteEventFail() throws Exception {
        Response response1 = provider.deleteEvent(0, "FOOTBALL");
        Response response2 = provider.deleteEvent(0, "IT");
        Response response3 = provider.deleteEvent(0, "MUSIC");
        Response response4 = provider.deleteEvent(1, "WRONG");

        assertEquals(response1.getStatus(), ResponseStatus.ERROR);
        assertEquals(response2.getStatus(), ResponseStatus.ERROR);
        assertEquals(response3.getStatus(), ResponseStatus.ERROR);
        assertEquals(response4.getStatus(), ResponseStatus.ERROR);

        assertEquals(response1.getBody(), null);
        assertEquals(response2.getBody(), null);
        assertEquals(response3.getBody(), null);
        assertEquals(response4.getBody(), null);
    }
}