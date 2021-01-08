package ru.sfedu.bookticket.api;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import ru.sfedu.bookticket.enums.ResponseStatus;
import ru.sfedu.bookticket.models.*;
import ru.sfedu.bookticket.utils.ConfigurationUtil;
import ru.sfedu.bookticket.utils.Response;
import ru.sfedu.bookticket.utils.WrapperXML;
import ru.sfedu.bookticket.Constants;
import ru.sfedu.bookticket.utils.BaseUtil;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DataProviderXML implements DataProvider {
    private FileReader reader;
    private FileWriter writer;
    private Serializer serializer;

    private String PATH = ConfigurationUtil.getConfigurationEntry(Constants.XML_PATH);
    private final String EXTENSION = Constants.XML_EXTENSION;

    private final Logger log = LogManager.getLogger(DataProviderXML.class);

    public DataProviderXML() throws IOException {
    }

    public void setPATH(String path) {
        this.PATH = path;
    }

    @Override
    public void initDataSource() throws IOException {
        if (!isFileExist(getPath(City.class))) {
            setList(BaseUtil.generateCities(), City.class);
        }
        if (!isFileExist(getPath(Place.class))) {
            setList(BaseUtil.generatePlaces(), Place.class);
        }
        if (!isFileExist(getPath(Photographer.class))) {
            setList(BaseUtil.generatePhotographers(), Photographer.class);
        }
        if (!isFileExist(getPath(Photo.class))) {
            setList(BaseUtil.generatePhoto(), Photo.class);
        }
        if (!isFileExist(getPath(FootballEvent.class))) {
            setList(BaseUtil.generateFootballEvent(), FootballEvent.class);
        }
        if (!isFileExist(getPath(ITEvent.class))) {
            setList(BaseUtil.generateITEvent(), ITEvent.class);
        }
        if (!isFileExist(getPath(MusicEvent.class))) {
            setList(BaseUtil.generateMusicEvent(), MusicEvent.class);
        }
        if (!isFileExist(getPath(Ticket.class))) {
            setList(BaseUtil.generateTickets(), Ticket.class);
        }
    }

    public String getPath(Class c) {
        return (PATH + getClassName(c) + EXTENSION);
    }
    public String getClassName(Class c) {
        return c.getSimpleName().toLowerCase();
    }

    public <T> void setList(List<T> list, Class cl) throws IOException {
        try {
            startWriter(cl);
            WrapperXML<T> xml = new WrapperXML<T>();
            xml.setList(list);
            this.serializer.write(xml, this.writer);
        } catch (IndexOutOfBoundsException e) {
            log.error(e);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            stopWriter();
        }
    }

    public <T> List<T> getList(Class cl) throws IOException {
        try {
            startReader(cl);
            WrapperXML<T> xml = this.serializer.read(WrapperXML.class, this.reader);
            if (xml.getList() == null) xml.setList(new ArrayList<T>());
            return xml.getList();
        } catch (Exception e) {
            return new ArrayList<T>();
        } finally {
            stopReader();
        }
    }

    public void startReader(Class cl) {
        try {
            String filePath = getPath(cl);
            if (!isFileExist(filePath)) {
                createNewFile(filePath);
            }
            this.reader = new FileReader(filePath);
            this.serializer = new Persister();
        } catch (FileNotFoundException e) {
            log.debug(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isFileExist(String path) {
        return new File(path).exists();
    }

    public void createNewFile(String path) throws IOException {
        File file = new File(path);
        Path dirPath = Paths.get(PATH);
        Files.createDirectories(dirPath);
        file.createNewFile();
    }

    public void startWriter(Class cl) {
        try {
            String filePath = getPath(cl);
            if (!isFileExist(filePath)) {
                createNewFile(filePath);
            }
            this.writer = new FileWriter(filePath);
            this.serializer = new Persister();
        } catch (FileNotFoundException e) {
            log.debug(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stopReader() throws IOException{
        if (this.reader != null) {
            this.reader.close();
        }
    }

    public void stopWriter() throws IOException{
        if (this.writer != null) {
            this.writer.close();
        }
    }

    public void flushFile(Class cl) throws IOException {
        String filePath = getPath(cl);
        if (!isFileExist(filePath)) {
            createNewFile(filePath);
        }
        FileWriter file = new FileWriter(filePath);
        file.flush();
    }

    @Override
    public Response getEvent(long eventId, String eventType) throws Exception {
        log.debug(Constants.DEBUG_GET_EVENT);
        switch (eventType) {
            case Constants.FOOTBALL: {
                return getFootballEvent(eventId);
            }
            case Constants.IT: {
                return getITEvent(eventId);
            }
            case Constants.MUSIC: {
                return getMusicEvent(eventId);
            }
        }
        return new Response(null, ResponseStatus.ERROR,  Constants.ERROR_GET_EVENT);
    }

    @Override
    public Response getFootballEvent(long eventId) {
        log.debug(Constants.DEBUG_GET_FOOTBALL_EVENT);
        if (isEventExist(eventId, Constants.FOOTBALL)) {
            try {
                List<FootballEvent> footballEvents = getList(FootballEvent.class);
                FootballEvent footballEvent = footballEvents.stream().filter(el -> el.getId() == eventId)
                        .findFirst().orElse(null);
                log.info(footballEvent);
                return new Response(footballEvent, ResponseStatus.SUCCESS, Constants.SUCCESS_GET_FOOTBALL_EVENT);
            } catch (IOException e) {
                log.error(e);
            }
        }
        return new Response(null, ResponseStatus.ERROR, Constants.ERROR_GET_FOOTBALL_EVENT);
    }

    @Override
    public Response getITEvent(long eventId) {
        log.debug(Constants.DEBUG_GET_IT_EVENT);
        if (isEventExist(eventId, Constants.IT)) {
            try {
                List<Event> itEvents = getList(ITEvent.class);
                Event itEvent = itEvents.stream().filter(el -> el.getId() == eventId)
                        .findFirst().orElse(null);
                log.info(itEvent);
                return new Response(itEvent, ResponseStatus.SUCCESS, Constants.SUCCESS_GET_IT_EVENT);
            } catch (IOException e) {
                log.error(e);
            }
        }
        return new Response(null, ResponseStatus.ERROR, Constants.ERROR_GET_IT_EVENT);
    }

    @Override
    public Response getMusicEvent(long eventId) {
        log.debug(Constants.DEBUG_GET_MUSIC_EVENT);
        if (isEventExist(eventId, Constants.MUSIC)) {
            try {
                List<MusicEvent> musicEvents = getList(MusicEvent.class);
                MusicEvent musicEvent = musicEvents.stream().filter(el -> el.getId() == eventId)
                        .findFirst().orElse(null);
                log.info(musicEvent);
                return new Response(musicEvent, ResponseStatus.SUCCESS, Constants.SUCCESS_GET_MUSIC_EVENT);
            } catch (IOException e) {
                log.error(e);
            }
        }
        return new Response(null, ResponseStatus.ERROR, Constants.ERROR_GET_MUSIC_EVENT);
    }

    @Override
    public Response bookTicket(long eventId, String eventType, String buyerName) {
        log.debug(Constants.DEBUG_BOOK_TICKET);
        if (isEventExist(eventId, eventType)) {
            try {
                List<Ticket> tickets = getList(Ticket.class);
                Ticket ticket = new Ticket();
                long id = BaseUtil.generateID();
                ticket.setId(id);
                Event event = (Event) getEvent(eventId, eventType).getBody();
                ticket.setEvent(event);
                ticket.setBuyerName(buyerName);
                String time = String.valueOf(LocalDateTime.now());
                ticket.setPayTime(time);
                tickets.add(ticket);
                setList(tickets, Ticket.class);
                return new Response(ticket, ResponseStatus.SUCCESS, Constants.SUCCESS_BOOK_TICKET);
            } catch (Exception e) {
                log.error(e);
            }
        }
        return new Response(null, ResponseStatus.ERROR, Constants.ERROR_BOOK_TICKET);
    }

    @Override
    public Response getTicket(long ticketId) {
        log.debug(Constants.DEBUG_GET_TICKET);
        if (isTicketExist(ticketId)) {
            try {
                List<Ticket> tickets = getList(Ticket.class);
                Ticket ticket = tickets.stream().filter(el -> el.getId() == ticketId)
                        .findFirst().orElse(null);
                log.info(ticket);
                return new Response(ticket, ResponseStatus.SUCCESS, Constants.SUCCESS_GET_TICKET);
            } catch (IOException e) {
                log.error(e);
            }
        }
        return new Response(null, ResponseStatus.ERROR, Constants.ERROR_GET_TICKET);
    }

    private boolean isTicketExist(long id) {
        try {
            List<Ticket> tickets = getList(Ticket.class);
            return tickets.stream().filter(el -> el.getId() == id).findFirst().isPresent();
        } catch (IOException e) {
            log.error(e);
            return false;
        }
    }

    @Override
    public Response getCity(long cityId) {
        log.debug(Constants.DEBUG_GET_CITY);
        if (isCityExist(cityId)) {
            try {
                List<City> cities = getList(City.class);
                City city = cities.stream().filter(el -> el.getId() == cityId)
                        .findFirst().orElse(null);
                log.info(city);
                return new Response(city, ResponseStatus.SUCCESS, Constants.SUCCESS_GET_CITY);
            } catch (IOException e) {
                log.error(e);
            }
        }
        return new Response(null, ResponseStatus.ERROR, Constants.ERROR_GET_CITY);
    }

    @Override
    public Response createCity(String name) {
        log.debug(Constants.DEBUG_CREATE_CITY);
        if (BaseUtil.isNotEmpty(name)) {
            try {
                List<City> cities = getList(City.class);
                City city = new City();
                long id = BaseUtil.generateID();
                city.setId(id);
                city.setName(name);
                cities.add(city);
                setList(cities, City.class);
                log.info(cities);
                return new Response(city, ResponseStatus.SUCCESS, Constants.SUCCESS_CREATE_CITY);
            } catch (IOException e) {
                log.error(e);
            }
        }
        return new Response(null, ResponseStatus.ERROR, Constants.ERROR_CREATE_CITY);
    }

    @Override
    public Response updateCity(long cityId, String name) {
        log.debug(Constants.DEBUG_UPDATE_CITY);
        if (isCityExist(cityId)) {
            try {
                List<City> cities = getList(City.class);
                List<City> updatedCities = cities.stream().map(el -> {
                    if (el.getId() == cityId) {
                        el.setName(name);
                    }
                    return el;
                }).collect(Collectors.toList());
                setList(updatedCities, City.class);
                return new Response(updatedCities, ResponseStatus.SUCCESS, Constants.SUCCESS_UPDATE_CITY);
            } catch (IOException e) {
                log.error(e);
            }
        }
        return new Response(null, ResponseStatus.ERROR, Constants.ERROR_UPDATE_CITY);
    }

    @Override
    public Response deleteCity(long cityId) {
        log.debug(Constants.DEBUG_DELETE_CITY);
        if (isCityExist(cityId)) {
            try {
                List<City> cities = getList(City.class);
                List<City> updatedCities = cities.stream()
                        .filter(el -> el.getId() != cityId)
                        .collect(Collectors.toList());
                setList(updatedCities, City.class);
                return new Response(null, ResponseStatus.SUCCESS, Constants.SUCCESS_DELETE_CITY);
            } catch (IOException e) {
                log.error(e);
            }
        }
        return new Response(null, ResponseStatus.ERROR, Constants.ERROR_DELETE_CITY);
    }

    private boolean isCityExist(long id) {
        try {
            List<City> cities = getList(City.class);
            return cities.stream().filter(el -> el.getId() == id).findFirst().isPresent();
        } catch (IOException e) {
            log.error(e);
            return false;
        }
    }

    @Override
    public Response updateEvent(String eventType, long id, String name, long placeId, String date, String time, String price, List<String> photos, String eventSpecialArgs) throws Exception {
        log.debug(Constants.DEBUG_UPDATE_EVENT);
        switch (eventType) {
            case Constants.FOOTBALL: {
                String team1 = new BaseUtil<String>()
                        .transformStringArgumentsToList(eventSpecialArgs).get(0);
                String team2 = new BaseUtil<String>()
                        .transformStringArgumentsToList(eventSpecialArgs).get(1);
                return updateFootballEvent(id, name, placeId, date, time, price, photos, team1, team2);
            }
            case Constants.IT: {
                return updateITEvent(id, name, placeId, date, time, price, photos, eventSpecialArgs);
            }
            case Constants.MUSIC: {
                return updateMusicEvent(id, name, placeId, date, time, price, photos, eventSpecialArgs);
            }
        }
        return new Response(null, ResponseStatus.ERROR, Constants.ERROR_UPDATE_EVENT);
    }

    @Override
    public Response updateFootballEvent(long id, String name, long placeId, String date, String time, String price, List<String> photos, String team1, String team2) throws Exception {
        log.debug(Constants.DEBUG_UPDATE_FOOTBALL_EVENT);
        if (isEventExist(id, Constants.FOOTBALL)) {
            if (isPlaceExist(placeId)) {
                List<FootballEvent> footballEvents = getList(FootballEvent.class);
                List<FootballEvent> updatedFootballEvents = footballEvents.stream().map(el -> {
                    if (el.getId() == id) {
                        el.setName(name);
                        try {
                            Place place = (Place) getPlace(placeId).getBody();
                            el.setPlace(place);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        el.setDate(date);
                        el.setTime(time);
                        el.setPrice(price);
                        el.setPhotos(photos);
                        el.setTeam1(team1);
                        el.setTeam2(team2);
                        log.info(el);
                    }
                    return el;
                }).collect(Collectors.toList());
                setList(updatedFootballEvents, FootballEvent.class);
                return new Response(updatedFootballEvents, ResponseStatus.SUCCESS, Constants.SUCCESS_UPDATE_FOOTBALL_EVENT);
            } else {
                return new Response(null, ResponseStatus.ERROR, Constants.ERROR_PLACE_NOT_EXIST);
            }
        }
        return new Response(null, ResponseStatus.ERROR, Constants.ERROR_UPDATE_FOOTBALL_EVENT);
    }

    @Override
    public Response updateITEvent(long id, String name, long placeId, String date, String time, String price, List<String> photos, String stack) throws Exception {
        log.debug(Constants.DEBUG_UPDATE_IT_EVENT);
        if (isEventExist(id, Constants.IT)) {
            if (isPlaceExist(placeId)) {
                List<ITEvent> itEvents = getList(ITEvent.class);
                List<ITEvent> updatedITEvents = itEvents.stream().map(el -> {
                    if (el.getId() == id) {
                        el.setName(name);
                        try {
                            Place place = (Place) getPlace(placeId).getBody();
                            log.info(place);
                            el.setPlace(place);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        el.setDate(date);
                        el.setTime(time);
                        el.setPrice(price);
                        el.setPhotos(photos);
                        el.setStack(stack);
                        log.info(el);
                    }
                    return el;
                }).collect(Collectors.toList());
                setList(updatedITEvents, ITEvent.class);
                return new Response(updatedITEvents, ResponseStatus.SUCCESS, Constants.SUCCESS_UPDATE_IT_EVENT);
            } else {
                return new Response(null, ResponseStatus.ERROR, Constants.ERROR_PLACE_NOT_EXIST);
            }
        }
        return new Response(null, ResponseStatus.ERROR, Constants.ERROR_UPDATE_IT_EVENT);
    }

    @Override
    public Response updateMusicEvent(long id, String name, long placeId, String date, String time, String price, List<String> photos, String artist) throws Exception {
        log.debug(Constants.DEBUG_UPDATE_MUSIC_EVENT);
        if (isEventExist(id, Constants.MUSIC)) {
            if (isPlaceExist(placeId)) {
                List<MusicEvent> musicEvents = getList(MusicEvent.class);
                List<MusicEvent> updatedMusicEvents = musicEvents.stream().map(el -> {
                    if (el.getId() == id) {
                        el.setName(name);
                        try {
                            Place place = (Place) getPlace(placeId).getBody();
                            el.setPlace(place);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        el.setDate(date);
                        el.setTime(time);
                        el.setPrice(price);
                        el.setPhotos(photos);
                        el.setArtist(artist);
                        log.info(el);
                    }
                    return el;
                }).collect(Collectors.toList());
                setList(updatedMusicEvents, MusicEvent.class);
                return new Response(updatedMusicEvents, ResponseStatus.SUCCESS, Constants.SUCCESS_UPDATE_MUSIC_EVENT);
            } else {
                return new Response(null, ResponseStatus.ERROR, Constants.ERROR_PLACE_NOT_EXIST);
            }
        }
        return new Response(null, ResponseStatus.ERROR, Constants.ERROR_UPDATE_MUSIC_EVENT);
    }

    @Override
    public Response createEvent(String eventType, String name, long placeId, String date, String time, String price, List<String> photos, String eventSpecialArgs) throws Exception {
        log.debug(Constants.DEBUG_CREATE_EVENT);
        switch (eventType) {
            case Constants.FOOTBALL: {
                String team1 = new BaseUtil<String>().transformStringArgumentsToList(eventSpecialArgs).get(0);
                String team2 = new BaseUtil<String>().transformStringArgumentsToList(eventSpecialArgs).get(1);
                return createFootballEvent(name, placeId, date, time, price, photos, team1, team2);
            }
            case Constants.IT: {
                return createITEvent(name, placeId, date, time, price, photos, eventSpecialArgs);
            }
            case Constants.MUSIC: {
                return createMusicEvent(name, placeId, date, time, price, photos, eventSpecialArgs);
            }
        }
        return new Response(null, ResponseStatus.ERROR, Constants.ERROR_CREATE_EVENT);
    }

    @Override
    public Response createFootballEvent(String name, long placeId, String date, String time, String price, List<String> photos, String team1, String team2) throws Exception {
        log.debug(Constants.DEBUG_CREATE_FOOTBALL_EVENT);
        if (isPlaceExist(placeId)) {
            List<FootballEvent> footballEvents = getList(FootballEvent.class);
            FootballEvent footballEvent = new FootballEvent();
            long id = BaseUtil.generateID();
            footballEvent.setId(id);
            footballEvent.setName(name);
            Place place = (Place) getPlace(placeId).getBody();
            footballEvent.setPlace(place);
            footballEvent.setDate(date);
            footballEvent.setTime(time);
            footballEvent.setPrice(price);
            footballEvent.setPhotos(photos);
            footballEvent.setTeam1(team1);
            footballEvent.setTeam2(team2);
            footballEvents.add(footballEvent);
            setList(footballEvents, FootballEvent.class);
            log.info(footballEvent);
            return new Response(footballEvent, ResponseStatus.SUCCESS, Constants.SUCCESS_CREATE_FOOTBALL_EVENT);
        }
        return new Response(null, ResponseStatus.ERROR, Constants.ERROR_PLACE_NOT_EXIST);
    }

    @Override
    public Response createITEvent(String name, long placeId, String date, String time, String price, List<String> photos, String stack) throws Exception {
        log.debug(Constants.DEBUG_CREATE_IT_EVENT);
        if (isPlaceExist(placeId)) {
            List<ITEvent> itEvents = getList(ITEvent.class);
            ITEvent itEvent = new ITEvent();
            long id = BaseUtil.generateID();
            itEvent.setId(id);
            itEvent.setName(name);
            Place place = (Place) getPlace(placeId).getBody();
            itEvent.setPlace(place);
            itEvent.setDate(date);
            itEvent.setTime(time);
            itEvent.setPrice(price);
            itEvent.setPhotos(photos);
            itEvent.setStack(stack);
            itEvents.add(itEvent);
            setList(itEvents, ITEvent.class);
            log.info(itEvent);
            return new Response(itEvent, ResponseStatus.SUCCESS, Constants.SUCCESS_CREATE_IT_EVENT);
        }
        return new Response(null, ResponseStatus.ERROR, Constants.ERROR_PLACE_NOT_EXIST);
    }

    @Override
    public Response createMusicEvent(String name, long placeId, String date, String time, String price, List<String> photos, String artist) throws Exception {
        log.debug(Constants.DEBUG_CREATE_MUSIC_EVENT);
        if (isPlaceExist(placeId)) {
            List<MusicEvent> musicEvents = getList(MusicEvent.class);
            MusicEvent musicEvent = new MusicEvent();
            long id = BaseUtil.generateID();
            musicEvent.setId(id);
            musicEvent.setName(name);
            Place place = (Place) getPlace(placeId).getBody();
            musicEvent.setPlace(place);
            musicEvent.setDate(date);
            musicEvent.setTime(time);
            musicEvent.setPrice(price);
            musicEvent.setPhotos(photos);
            musicEvent.setArtist(artist);
            musicEvents.add(musicEvent);
            setList(musicEvents, MusicEvent.class);
            log.info(musicEvent);
            return new Response(musicEvent, ResponseStatus.SUCCESS, Constants.SUCCESS_CREATE_MUSIC_EVENT);
        }
        return new Response(null, ResponseStatus.ERROR, Constants.ERROR_PLACE_NOT_EXIST);
    }

    @Override
    public Response createPlace(String address, long cityId) throws Exception {
        log.debug(Constants.DEBUG_CREATE_PLACE);
        if (isCityExist(cityId)) {
            List<Place> places = getList(Place.class);
            Place place = new Place();
            long id = BaseUtil.generateID();
            place.setId(id);
            place.setAddress(address);
            City city = (City) getCity(cityId).getBody();
            place.setCity(city);
            places.add(place);
            setList(places, Place.class);
            log.info(place);
            return new Response(place, ResponseStatus.SUCCESS, Constants.SUCCESS_CREATE_PLACE);
        }
        return new Response(null, ResponseStatus.ERROR, Constants.ERROR_CITY_NOT_EXIST);
    }

    @Override
    public Response updatePlace(long placeId, String address, long cityId) throws Exception {
        log.debug(Constants.DEBUG_UPDATE_PLACE);
        if (isPlaceExist(placeId)) {
            if (isCityExist(cityId)) {
                List<Place> places = getList(Place.class);
                List<Place> updatedPlaces = places.stream().map(el -> {
                    if (el.getId() == placeId) {
                        el.setAddress(address);
                        try {
                            City city = (City) getCity(cityId).getBody();
                            el.setCity(city);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        log.info(el);
                    }
                    return el;
                }).collect(Collectors.toList());
                setList(updatedPlaces, Place.class);
                return new Response(updatedPlaces, ResponseStatus.SUCCESS, Constants.SUCCESS_UPDATE_PLACE);
            } else {
                return new Response(null, ResponseStatus.ERROR, Constants.ERROR_CITY_NOT_EXIST);
            }
        }
        return new Response(null, ResponseStatus.ERROR, Constants.ERROR_PLACE_NOT_EXIST);
    }

    @Override
    public Response deletePlace(long placeId) throws Exception {
        log.debug(Constants.DEBUG_DELETE_PLACE);
        if (isPlaceExist(placeId)) {
            List<Place> places = getList(Place.class);
            List<Place> updatedPlaces = places.stream()
                    .filter(el -> el.getId() != placeId)
                    .collect(Collectors.toList());
            setList(updatedPlaces, Place.class);
            return new Response(null, ResponseStatus.SUCCESS, Constants.SUCCESS_DELETE_PLACE);
        }
        return new Response(null, ResponseStatus.ERROR, Constants.ERROR_PLACE_NOT_EXIST);
    }

    @Override
    public Response getPlace(long placeId) throws Exception {
        log.debug(Constants.DEBUG_GET_PLACE);
        if (isPlaceExist(placeId)) {
            List<Place> places = getList(Place.class);
            Place place = places.stream().filter(el -> el.getId() == placeId)
                    .findFirst().orElse(null);
            log.info(place);
            return new Response(place, ResponseStatus.SUCCESS, Constants.SUCCESS_GET_PLACE);
        }
        return new Response(null, ResponseStatus.ERROR, Constants.ERROR_PLACE_NOT_EXIST);
    }

    private boolean isPlaceExist(long id) {
        try {
            List<Place> places = getList(Place.class);
            return places.stream().filter(el -> el.getId() == id).findFirst().isPresent();
        } catch (IOException e) {
            log.error(e);
            return false;
        }
    }

    @Override
    public Response createPhoto(String name, String path, long photographerId) throws Exception {
        log.debug(Constants.DEBUG_CREATE_PHOTO);
        if (isPhotographerExist(photographerId)) {
            List<Photo> photos = getList(Photo.class);
            Photo photo = new Photo();
            long id = BaseUtil.generateID();
            photo.setId(id);
            photo.setName(name);
            photo.setPath(path);
            Photographer photographer = (Photographer) getPhotographer(photographerId).getBody();
            photo.setPhotographer(photographer);
            photos.add(photo);
            setList(photos, Photo.class);
            log.info(photo);
            return new Response(photo, ResponseStatus.SUCCESS, Constants.SUCCESS_CREATE_PHOTO);
        }
        return new Response(null, ResponseStatus.ERROR, Constants.ERROR_PHOTOGRAPHER_NOT_EXIST);
    }

    @Override
    public Response deletePhoto(long photoId) throws Exception {
        log.debug(Constants.DEBUG_DELETE_PHOTO);
        if (isPhotoExist(photoId)) {
            List<Photo> photos = getList(Photo.class);
            List<Photo> updatedPhotos = photos.stream()
                    .filter(el -> el.getId() != photoId)
                    .collect(Collectors.toList());
            setList(updatedPhotos, Photo.class);
            return new Response(null, ResponseStatus.SUCCESS, Constants.SUCCESS_DELETE_PHOTO);
        }
        return new Response(null, ResponseStatus.ERROR, Constants.ERROR_PHOTO_NOT_EXIST);
    }

    @Override
    public Response getPhoto(long photoId) throws Exception {
        log.debug(Constants.DEBUG_GET_PHOTO);
        if (isPhotoExist(photoId)) {
            List<Photo> photos = getList(Photo.class);
            Photo photo = photos.stream().filter(el -> el.getId() == photoId)
                    .findFirst().orElse(null);
            log.info(photo);
            return new Response(photo, ResponseStatus.SUCCESS, Constants.SUCCESS_GET_PHOTO);
        }
        return new Response(null, ResponseStatus.ERROR, Constants.ERROR_PHOTO_NOT_EXIST);
    }

    public boolean isPhotoExist(long id) {
        try {
            List<Photo> photos = getList(Photo.class);
            return photos.stream().filter(el -> el.getId() == id).findFirst().isPresent();
        } catch (IOException e) {
            log.error(e);
            return false;
        }
    }

    @Override
    public Response createPhotographer(String name, long expirence, String site) throws Exception {
        log.debug(Constants.DEBUG_CREATE_PHOTOGRAPHER);
        if (BaseUtil.isNotEmpty(name)) {
            List<Photographer> photographers = getList(Photographer.class);
            Photographer photographer = new Photographer();
            long id = BaseUtil.generateID();
            photographer.setId(id);
            photographer.setName(name);
            photographer.setExpirence(expirence);
            photographer.setSite(site);
            photographers.add(photographer);
            setList(photographers, Photographer.class);
            log.info(photographer);
            return new Response(photographer, ResponseStatus.SUCCESS, Constants.SUCCESS_CREATE_PHOTOGRAPHER);
        }
        return new Response(null, ResponseStatus.ERROR, Constants.ERROR_CREATE_PHOTOGRAPHER);
    }

    @Override
    public Response updatePhotographer(long id, String name, long expirence, String site) throws Exception {
        log.debug(Constants.DEBUG_UPDATE_PHOTOGRAPHER);
        if (isPhotographerExist(id)) {
            if (BaseUtil.isNotEmpty(name)) {
                List<Photographer> photographers = getList(Photographer.class);
                List<Photographer> updatedPhotographers = photographers.stream().map(el -> {
                    if (el.getId() == id) {
                        el.setName(name);
                        el.setExpirence(expirence);
                        el.setSite(site);
                    }
                    return el;
                }).collect(Collectors.toList());
                setList(updatedPhotographers, Photographer.class);
                return new Response(updatedPhotographers, ResponseStatus.SUCCESS, Constants.SUCCESS_UPDATE_PHOTOGRAPHER);
            } else {
                return new Response(null, ResponseStatus.ERROR, Constants.ERROR_UPDATE_PHOTOGRAPHER);
            }
        }
        return new Response(null, ResponseStatus.ERROR, Constants.ERROR_PHOTOGRAPHER_NOT_EXIST);
    }

    @Override
    public Response deletePhotographer(long id) throws Exception {
        log.debug(Constants.DEBUG_DELETE_PHOTOGRAPHER);
        if (isPhotographerExist(id)) {
            List<Photographer> photographers = getList(Photographer.class);
            List<Photographer> updatedPhotographers = photographers.stream()
                    .filter(el -> el.getId() != id)
                    .collect(Collectors.toList());
            setList(updatedPhotographers, Photographer.class);
            return new Response(null, ResponseStatus.SUCCESS, Constants.SUCCESS_DELETE_PHOTOGRAPHER);
        }
        return new Response(null, ResponseStatus.ERROR, Constants.ERROR_PHOTOGRAPHER_NOT_EXIST);
    }

    @Override
    public Response getPhotographer(long id) throws Exception {
        log.debug(Constants.DEBUG_GET_PHOTOGRAPHER);
        if (isPhotographerExist(id)) {
            List<Photographer> photographers = getList(Photographer.class);
            Photographer photographer = photographers.stream()
                    .filter(el -> el.getId() == id)
                    .findFirst().orElse(null);
            log.info(photographer);
            return new Response(photographer, ResponseStatus.SUCCESS, Constants.SUCCESS_GET_PHOTOGRAPHER);
        }
        return new Response(null, ResponseStatus.ERROR, Constants.ERROR_PHOTOGRAPHER_NOT_EXIST);
    }

    private boolean isPhotographerExist(long id) {
        try {
            List<Photographer> photographers = getList(Photographer.class);
            return photographers.stream().filter(el -> el.getId() == id).findFirst().isPresent();
        } catch (IOException e) {
            log.error(e);
            return false;
        }
    }

    @Override
    public Response deleteEvent(long id, String eventType) throws Exception {
        log.debug(Constants.DEBUG_DELETE_EVENT);
        if (isEventExist(id, eventType)) {
            switch (eventType) {
                case Constants.FOOTBALL: {
                    List<FootballEvent> footballEvents = getList(FootballEvent.class);
                    List<FootballEvent> updatedFootballEvents = footballEvents.stream()
                            .filter(el -> el.getId() != id)
                            .collect(Collectors.toList());
                    setList(updatedFootballEvents, FootballEvent.class);
                    return new Response(null, ResponseStatus.SUCCESS, Constants.SUCCESS_DELETE_EVENT);
                }
                case Constants.IT: {
                    List<ITEvent> itEvents = getList(ITEvent.class);
                    List<ITEvent> updatedITEvents = itEvents.stream()
                            .filter(el -> el.getId() != id)
                            .collect(Collectors.toList());
                    setList(updatedITEvents, ITEvent.class);
                    return new Response(null, ResponseStatus.SUCCESS, Constants.SUCCESS_DELETE_EVENT);
                }
                case Constants.MUSIC: {
                    List<MusicEvent> musicEvents = getList(MusicEvent.class);
                    List<MusicEvent> updatedMusicEvents = musicEvents.stream()
                            .filter(el -> el.getId() != id)
                            .collect(Collectors.toList());
                    setList(updatedMusicEvents, MusicEvent.class);
                    return new Response(null, ResponseStatus.SUCCESS, Constants.SUCCESS_DELETE_EVENT);
                }
            }
        }
        return new Response(null, ResponseStatus.ERROR, Constants.ERROR_DELETE_EVENT);
    }

    private boolean isEventExist(long id, String eventType) {
        switch (eventType) {
            case Constants.FOOTBALL: {
                try {
                    List<FootballEvent> footballEvents = getList(FootballEvent.class);
                    return footballEvents.stream().filter(el -> el.getId() == id).findFirst().isPresent();
                } catch (IOException e) {
                    log.error(e);
                    return false;
                }
            }
            case Constants.IT: {
                try {
                    List<ITEvent> itEvents = getList(ITEvent.class);
                    return itEvents.stream().filter(el -> el.getId() == id).findFirst().isPresent();
                } catch (IOException e) {
                    log.error(e);
                    return false;
                }
            }
            case Constants.MUSIC: {
                try {
                    List<MusicEvent> musicEvents = getList(MusicEvent.class);
                    return musicEvents.stream().filter(el -> el.getId() == id).findFirst().isPresent();
                } catch (IOException e) {
                    log.error(e);
                    return false;
                }
            }
        }
        return false;
    }
}