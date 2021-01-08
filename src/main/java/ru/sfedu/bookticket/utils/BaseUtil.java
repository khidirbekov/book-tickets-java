package ru.sfedu.bookticket.utils;

import ru.sfedu.bookticket.Constants;
import ru.sfedu.bookticket.models.*;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BaseUtil<T> {
    public static long generateID() {
        final long max = 1000000;
        final long min = 100000;
        return (long) (System.currentTimeMillis() / (Math.random() * (max - min + 1) + min));
    }

    public static List<String> parseCsvObject(String value) {
        String args = value.substring(
                value.indexOf(Constants.OBJECT_LEFT_SEPARATOR) + 1,
                value.lastIndexOf(Constants.OBJECT_RIGHT_SEPARATOR));
        List<String> values = Stream.of(args.split(Constants.OBJECT_FIELD_SEPARATOR))
                .map(el -> el.split(Constants.OBJECT_VALUE_SEPARATOR)[1]
                        .replaceAll(Constants.STRING_SEPARATOR, Constants.EMPTY)
                ).collect(Collectors.toList());
        return values;
    }

    public List<T> transformStringArgumentsToList(String args) {
        return Stream.of(args.split(Constants.OBJECT_FIELD_SEPARATOR))
                .map(el -> (T) el.trim()).collect(Collectors.toList());
    }

    public static List transformSQLArrayToList(Array array) {
        List list = new ArrayList<>();

        try {
            Object[] objectArray = (Object[]) array.getArray();
            for (Object el: objectArray) {
                list.add(el);
            }
            return list;
        } catch (SQLException throwables) {
            return new ArrayList<>();
        }
    }

    public static boolean isNotEmpty(String value) {
        return value.trim().length() > 0;
    }

    public static List<City> generateCities() {
        List<City> cities = new ArrayList<>();
        City city = new City();
        city.setId(1);
        city.setName("test");
        cities.add(city);
        return cities;
    }

    public static List<Place> generatePlaces() {
        List<Place> places = new ArrayList<>();
        Place place = new Place();
        place.setId(1);
        place.setAddress("test");
        City city = new City();
        city.setId(1);
        city.setName("test");
        place.setCity(city);
        places.add(place);
        return places;
    }

    public static List<Photographer> generatePhotographers() {
        List<Photographer> photographers = new ArrayList<>();
        Photographer photographer = new Photographer();
        photographer.setId(1);
        photographer.setName("test");
        photographer.setExpirence(10);
        photographer.setSite("www.test.com");
        photographers.add(photographer);
        return photographers;
    }

    public static List<Photo> generatePhoto() {
        List<Photo> photos = new ArrayList<>();
        Photo photo = new Photo();
        photo.setId(1);
        photo.setName("test");
        photo.setPath("www.test.com");

        Photographer photographer = new Photographer();
        photographer.setId(1);
        photographer.setName("test");
        photographer.setExpirence(10);
        photographer.setSite("www.test.com");
        photo.setPhotographer(photographer);
        photos.add(photo);
        return photos;
    }

    public static List<FootballEvent> generateFootballEvent() {
        List<FootballEvent> footballEvents = new ArrayList<>();
        FootballEvent footballEvent = new FootballEvent();
        footballEvent.setId(1);
        footballEvent.setName("test");
        footballEvent.setDate("01-01-1970");
        footballEvent.setTime("00:00");
        footballEvent.setPrice("100");
        List<String> photos = new ArrayList<String>(Collections.singleton("1"));
        footballEvent.setPhotos(photos);


        Place place = new Place();
        place.setId(1);
        place.setAddress("test");
        City city = new City();
        city.setId(1);
        city.setName("test");
        place.setCity(city);
        footballEvent.setPlace(place);

        footballEvent.setTeam1("Real Madrid");
        footballEvent.setTeam2("Barcelona");

        footballEvents.add(footballEvent);
        return footballEvents;
    }

    public static List<ITEvent> generateITEvent() {
        List<ITEvent> itEvents = new ArrayList<>();
        ITEvent itEvent = new ITEvent();
        itEvent.setId(2);
        itEvent.setName("test");
        itEvent.setDate("01-01-1970");
        itEvent.setTime("00:00");
        itEvent.setPrice("100");
        List<String> photos = new ArrayList<String>(Collections.singleton("1"));
        itEvent.setPhotos(photos);


        Place place = new Place();
        place.setId(1);
        place.setAddress("test");
        City city = new City();
        city.setId(1);
        city.setName("test");
        place.setCity(city);
        itEvent.setPlace(place);

        itEvent.setStack("Frontend");

        itEvents.add(itEvent);
        return itEvents;
    }

    public static List<MusicEvent> generateMusicEvent() {
        List<MusicEvent> musicEvents = new ArrayList<>();
        MusicEvent musicEvent = new MusicEvent();
        musicEvent.setId(3);
        musicEvent.setName("test");
        musicEvent.setDate("01-01-1970");
        musicEvent.setTime("00:00");
        musicEvent.setPrice("100");
        List<String> photos = new ArrayList<String>(Collections.singleton("1"));
        musicEvent.setPhotos(photos);

        Place place = new Place();
        place.setId(1);
        place.setAddress("test");
        City city = new City();
        city.setId(1);
        city.setName("test");
        place.setCity(city);
        musicEvent.setPlace(place);

        musicEvent.setArtist("50 cent");

        musicEvents.add(musicEvent);
        return musicEvents;
    }

    public static List<Ticket> generateTickets() {
        List<Ticket> tickets = new ArrayList<>();
        Ticket ticket = new Ticket();
        ticket.setId(1);
        ticket.setPayTime("01-01-1970 00:00");
        ticket.setBuyerName("test");
        Event event = new Event();
        event.setId(1);
        event.setName("test");
        Place place = new Place();
        place.setId(1);
        place.setAddress("test");
        City city = new City();
        city.setId(1);
        city.setName("test");
        place.setCity(city);
        event.setPlace(place);
        event.setDate("01-01-1970");
        event.setTime("00:00");
        event.setPrice("100");
        List<String> photos = new ArrayList<String>(Collections.singleton("1"));
        event.setPhotos(photos);
        ticket.setEvent(event);
        tickets.add(ticket);
        return tickets;
    }
}
