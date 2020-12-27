package ru.sfedu.mavenproject.api;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.mavenproject.enums.ResponseStatus;
import ru.sfedu.mavenproject.models.*;
import ru.sfedu.mavenproject.utils.BaseUtil;
import ru.sfedu.mavenproject.utils.ConfigurationUtil;
import ru.sfedu.mavenproject.Constants;
import ru.sfedu.mavenproject.utils.Response;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class DataProviderCSV implements DataProvider {
    private CSVReader reader;
    private CSVWriter writer;

    private final String PATH = ConfigurationUtil.getConfigurationEntry(Constants.CSV_PATH);
    private final String EXTENSION = Constants.CSV_EXTENSION;

    private Logger log = LogManager.getLogger(DataProviderCSV.class);

    public DataProviderCSV() throws IOException {
    }

    @Override
    public void initDataSource(String path) throws IOException {

    }

    @Override
    public <T> List<T> getList(Class cl) throws IOException {
        try {
            startReader(cl);
            CsvToBean csvToBean = new CsvToBeanBuilder<T>(reader)
                    .withType(cl)
                    .build();
            List<T> list = csvToBean.parse();
            return list;
        } catch (IOException e) {
            log.debug(e);
        } finally {
            stopReader();
        }
        return null;
    }

    @Override
    public <T> void setList(List<T> list, Class cl) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
        try {
            startWriter(cl);
            StatefulBeanToCsv<T> beanToCsv = new StatefulBeanToCsvBuilder<T>(writer)
                    .withApplyQuotesToAll(false)
                    .build();
            beanToCsv.write(list);
        } catch (CsvDataTypeMismatchException e) {
            log.error(e);
        } catch (CsvRequiredFieldEmptyException e) {
            log.error(e);
        } finally {
            stopWriter();
        }
    }

    public void startReader(Class cl) throws FileNotFoundException, IOException {
        try {
            String filePath = getPath(cl);
            if (!isFileExist(filePath)) {
                createNewFile(filePath);
            }
            FileReader reader = new FileReader(filePath);
            this.reader = new CSVReader(reader);
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
            FileWriter writer = new FileWriter(filePath);
            this.writer = new CSVWriter(writer);
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
        FileWriter file = new FileWriter(getPath(cl));
        file.flush();
    }

    @Override
    public String getPath(Class c) {
        return (PATH + getClassName(c) + EXTENSION);
    }
    public String getClassName(Class c) {
        return c.getSimpleName().toLowerCase();
    }

    @Override
    public Response getEvent(long eventId, String eventType) throws Exception {
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
        return new Response(null, ResponseStatus.ERROR, "");
    }

    @Override
    public Response getFootballEvent(long eventId) throws Exception {
        if (isEventExist(eventId, Constants.FOOTBALL)) {
            List<FootballEvent> footballEvents = getList(FootballEvent.class);
            FootballEvent footballEvent = footballEvents.stream().filter(el -> el.getId() == eventId)
                    .findFirst().orElse(null);
            log.info(footballEvent);
            return new Response(footballEvent, ResponseStatus.SUCCESS, "");
        }
        return new Response(null, ResponseStatus.ERROR, "");
    }

    @Override
    public Response getITEvent(long eventId) throws Exception {
        if (isEventExist(eventId, Constants.IT)) {
            List<Event> itEvents = getList(ITEvent.class);
            Event itEvent = itEvents.stream().filter(el -> el.getId() == eventId)
                    .findFirst().orElse(null);
            log.info(itEvent);
            return new Response(itEvent, ResponseStatus.SUCCESS, "");
        }
        return new Response(null, ResponseStatus.ERROR, "");
    }

    @Override
    public Response getMusicEvent(long eventId) throws Exception {
        if (isEventExist(eventId, Constants.MUSIC)) {
            List<MusicEvent> musicEvents = getList(MusicEvent.class);
            MusicEvent musicEvent = musicEvents.stream().filter(el -> el.getId() == eventId)
                    .findFirst().orElse(null);
            log.info(musicEvent);
            return new Response(musicEvent, ResponseStatus.SUCCESS, "");
        }
        return new Response(null, ResponseStatus.ERROR, "");
    }

    @Override
    public Response bookTicket(long eventId, String eventType, String buyerName) throws Exception {
        if (isEventExist(eventId, eventType)) {
            List<Ticket> tickets = getList(Ticket.class);
            Ticket ticket = new Ticket();
            long id = BaseUtil.generateID();
            ticket.setId(id);
            Object event = getEvent(eventId, eventType).getBody();
            ticket.setEvent((Event) event);
            ticket.setBuyerName(buyerName);
            String time = String.valueOf(LocalDateTime.now());
            ticket.setPayTime(time);
            tickets.add(ticket);
            setList(tickets, Ticket.class);
            return new Response(ticket, ResponseStatus.SUCCESS, "");
        }
        return new Response(null, ResponseStatus.ERROR, "");
    }

    @Override
    public Response getTicket(long ticketId) throws Exception {
        if (isTicketExist(ticketId)) {
            List<Ticket> tickets = getList(Ticket.class);
            Ticket ticket = tickets.stream().filter(el -> el.getId() == ticketId)
                    .findFirst().orElse(null);
            log.info(ticket);
            return new Response(ticket, ResponseStatus.SUCCESS, "");
        }
        return new Response(null, ResponseStatus.SUCCESS, "");
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
    public Response getCity(long cityId) throws Exception {
        if (isCityExist(cityId)) {
            List<City> cities = getList(City.class);
            City city = cities.stream().filter(el -> el.getId() == cityId)
                    .findFirst().orElse(null);
            log.info(city);
            return new Response(city, ResponseStatus.SUCCESS, "");
        }
        return new Response(null, ResponseStatus.ERROR, "");
    }

    @Override
    public Response createCity(String name) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        if (BaseUtil.isEmpty(name)) {
            List<City> cities = getList(City.class);
            City city = new City();
            long id = BaseUtil.generateID();
            city.setId(id);
            city.setName(name);
            cities.add(city);
            setList(cities, City.class);
            log.info(cities);
            return new Response(city, ResponseStatus.SUCCESS, "SUCCESS");
        }
        return new Response(null, ResponseStatus.ERROR, "");
    }

    @Override
    public Response updateCity(long cityId, String name) throws Exception {
        if (isCityExist(cityId)) {
            List<City> cities = getList(City.class);
            List<City> updatedCities = cities.stream().map(el -> {
                if (el.getId() == cityId) {
                    el.setName(name);
                }
                return el;
            }).collect(Collectors.toList());
            setList(updatedCities, City.class);
            return new Response(updatedCities, ResponseStatus.SUCCESS, "");
        }
        return new Response(null, ResponseStatus.ERROR, "");
    }

    @Override
    public Response deleteCity(long cityId) throws Exception {
        if (isCityExist(cityId)) {
            List<City> cities = getList(City.class);
            List<City> updatedCities = cities.stream()
                    .filter(el -> el.getId() != cityId)
                    .collect(Collectors.toList());
            setList(updatedCities, City.class);
            return new Response(updatedCities, ResponseStatus.SUCCESS, "");
        }
        return new Response(null, ResponseStatus.ERROR, "");
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
    public Response updateEvent(String eventType, long id, String name, long placeId, String date, String time, String price, String photos, String eventSpecialArgs) throws Exception {
            switch (eventType) {
                case Constants.FOOTBALL: {
                    String team1 = new BaseUtil<String>()
                            .transformStringArgumentsToList(eventSpecialArgs).get(0);
                    String team2 = new BaseUtil<String>()
                            .transformStringArgumentsToList(eventSpecialArgs).get(1);
                    updateFootballEvent(id, name, placeId, date, time, price, photos, team1, team2);
                    return new Response(null, ResponseStatus.SUCCESS, "");
                }
                case Constants.IT: {
                    updateITEvent(id, name, placeId, date, time, price, photos, eventSpecialArgs);
                    return new Response(null, ResponseStatus.SUCCESS, "");
                }
                case Constants.MUSIC: {
                    updateMusicEvent(id, name, placeId, date, time, price, photos, eventSpecialArgs);
                    return new Response(null, ResponseStatus.SUCCESS, "");
                }
            }
            return new Response(null, ResponseStatus.ERROR, "");
    }

    @Override
    public Response updateFootballEvent(long id, String name, long placeId, String date, String time, String price, String photos, String team1, String team2) throws Exception {
        if (isEventExist(id, Constants.FOOTBALL)) {
            if (isPlaceExist(placeId)) {
                List<FootballEvent> footballEvents = getList(FootballEvent.class);
                List<String> photosList = new BaseUtil<String>().transformStringArgumentsToList(photos);
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
                        el.setPhotos(photosList);
                        el.setTeam1(team1);
                        el.setTeam2(team2);
                    }
                    return el;
                }).collect(Collectors.toList());
                setList(updatedFootballEvents, FootballEvent.class);
                return new Response(updatedFootballEvents, ResponseStatus.SUCCESS, "SUCCESS");
            } else {
                return new Response(null, ResponseStatus.ERROR, "");
            }
        }
        return new Response(null, ResponseStatus.ERROR, "");
    }

    @Override
    public Response updateITEvent(long id, String name, long placeId, String date, String time, String price, String photos, String stack) throws Exception {
        if (isEventExist(id, Constants.IT)) {
            if (isPlaceExist(placeId)) {
                List<ITEvent> itEvents = getList(ITEvent.class);
                List<String> photosList = new BaseUtil<String>().transformStringArgumentsToList(photos);
                List<ITEvent> updatedITEvents = itEvents.stream().map(el -> {
                    if (el.getId() == id) {
                        el.setName(name);
                        log.info("here");

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
                        el.setPhotos(photosList);
                        el.setStack(stack);
                    }
                    return el;
                }).collect(Collectors.toList());
                setList(updatedITEvents, ITEvent.class);
                return new Response(updatedITEvents, ResponseStatus.SUCCESS, "SUCCESS");
            } else {
                return new Response(null, ResponseStatus.ERROR, "");
            }
        }
        return new Response(null, ResponseStatus.ERROR, "");
    }

    @Override
    public Response updateMusicEvent(long id, String name, long placeId, String date, String time, String price, String photos, String artist) throws Exception {
        if (isEventExist(id, Constants.MUSIC)) {
            if (isPlaceExist(placeId)) {
                List<MusicEvent> musicEvents = getList(MusicEvent.class);
                List<String> photosList = new BaseUtil<String>().transformStringArgumentsToList(photos);
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
                        el.setPhotos(photosList);
                        el.setArtist(artist);
                    }
                    return el;
                }).collect(Collectors.toList());
                setList(updatedMusicEvents, MusicEvent.class);
                return new Response(updatedMusicEvents, ResponseStatus.SUCCESS, "SUCCESS");
            } else {
                return new Response(null, ResponseStatus.ERROR, "");
            }
        }
        return new Response(null, ResponseStatus.ERROR, "");
    }

    @Override
    public Response createEvent(String eventType, String name, long placeId, String date, String time, String price, String photos, String eventSpecialArgs) throws Exception {
        switch (eventType) {
            case Constants.FOOTBALL: {
                String team1 = new BaseUtil<String>().transformStringArgumentsToList(eventSpecialArgs).get(0);
                String team2 = new BaseUtil<String>().transformStringArgumentsToList(eventSpecialArgs).get(1);
                createFootballEvent(name, placeId, date, time, price, photos, team1, team2);
                return new Response(null, ResponseStatus.SUCCESS, "");
            }
            case Constants.IT: {
                createITEvent(name, placeId, date, time, price, photos, eventSpecialArgs);
                return new Response(null, ResponseStatus.SUCCESS, "");
            }
            case Constants.MUSIC: {
                createMusicEvent(name, placeId, date, time, price, photos, eventSpecialArgs);
                return new Response(null, ResponseStatus.SUCCESS, "");
            }
        }
        return new Response(null, ResponseStatus.ERROR, "Incorrect event type");
    }

    @Override
    public Response createFootballEvent(String name, long placeId, String date, String time, String price, String photos, String team1, String team2) throws Exception {
        if (isPlaceExist(placeId)) {
            List<FootballEvent> footballEvents = getList(FootballEvent.class);
            List<String> photosList = new BaseUtil<String>().transformStringArgumentsToList(photos);
            FootballEvent footballEvent = new FootballEvent();
            long id = BaseUtil.generateID();
            footballEvent.setId(id);
            footballEvent.setName(name);
            Place place = (Place) getPlace(placeId).getBody();
            footballEvent.setPlace(place);
            footballEvent.setDate(date);
            footballEvent.setTime(time);
            footballEvent.setPrice(price);
            footballEvent.setPhotos(photosList);
            footballEvent.setTeam1(team1);
            footballEvent.setTeam2(team2);
            footballEvents.add(footballEvent);
            setList(footballEvents, FootballEvent.class);
            log.info(footballEvent);
            return new Response(footballEvent, ResponseStatus.SUCCESS, "success");
        }
        return new Response(null, ResponseStatus.ERROR, "");
    }

    @Override
    public Response createITEvent(String name, long placeId, String date, String time, String price, String photos, String stack) throws Exception {
        if (isPlaceExist(placeId)) {
            List<ITEvent> itEvents = getList(ITEvent.class);
            List<String> photosList = new BaseUtil<String>().transformStringArgumentsToList(photos);
            ITEvent itEvent = new ITEvent();
            long id = BaseUtil.generateID();
            itEvent.setId(id);
            itEvent.setName(name);
            Place place = (Place) getPlace(placeId).getBody();
            itEvent.setPlace(place);
            itEvent.setDate(date);
            itEvent.setTime(time);
            itEvent.setPrice(price);
            itEvent.setPhotos(photosList);
            itEvent.setStack(stack);
            itEvents.add(itEvent);
            setList(itEvents, ITEvent.class);
            log.info(itEvent);
            return new Response(itEvent, ResponseStatus.SUCCESS, "success");
        }
        return new Response(null, ResponseStatus.ERROR, "");
    }

    @Override
    public Response createMusicEvent(String name, long placeId, String date, String time, String price, String photos, String artist) throws Exception {
        if (isPlaceExist(placeId)) {
            List<MusicEvent> musicEvents = getList(MusicEvent.class);
            List<String> photosList = new BaseUtil<String>().transformStringArgumentsToList(photos);
            MusicEvent musicEvent = new MusicEvent();
            long id = BaseUtil.generateID();
            musicEvent.setId(id);
            musicEvent.setName(name);
            Place place = (Place) getPlace(placeId).getBody();
            musicEvent.setPlace(place);
            musicEvent.setDate(date);
            musicEvent.setTime(time);
            musicEvent.setPrice(price);
            musicEvent.setPhotos(photosList);
            musicEvent.setArtist(artist);
            musicEvents.add(musicEvent);
            setList(musicEvents, MusicEvent.class);
            log.info(musicEvent);
            return new Response(musicEvent, ResponseStatus.SUCCESS, "success");
        }
        return new Response(null, ResponseStatus.ERROR, "");
    }

    @Override
    public Response createPlace(String address, long cityId) throws Exception {
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
            log.info(places);
            return new Response(place, ResponseStatus.SUCCESS, "");
        }
        return new Response(null, ResponseStatus.ERROR, "");
    }

    @Override
    public Response updatePlace(long placeId, String address, long cityId) throws Exception {
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
                    }
                    return el;
                }).collect(Collectors.toList());
                setList(updatedPlaces, Place.class);
                return new Response(updatedPlaces, ResponseStatus.SUCCESS, "");
            } else {
                return new Response(null, ResponseStatus.ERROR, "");
            }
        }
        return new Response(null, ResponseStatus.ERROR, "");
    }

    @Override
    public Response deletePlace(long placeId) throws Exception {
        if (isPlaceExist(placeId)) {
            List<Place> places = getList(Place.class);
            List<Place> updatedPlaces = places.stream()
                    .filter(el -> el.getId() != placeId)
                    .collect(Collectors.toList());
            setList(updatedPlaces, Place.class);
            return new Response(updatedPlaces, ResponseStatus.SUCCESS, "");
        }
        return new Response(null, ResponseStatus.ERROR, "");
    }

    @Override
    public Response getPlace(long placeId) throws Exception {
        if (isPlaceExist(placeId)) {
            List<Place> places = getList(Place.class);
            Place place = places.stream().filter(el -> el.getId() == placeId)
                    .findFirst().orElse(null);
            log.info(place);
            return new Response(place, ResponseStatus.SUCCESS, "success");
        }
        return new Response(null, ResponseStatus.ERROR, "");
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
            log.info(photos);
            return new Response(photo, ResponseStatus.SUCCESS, "");
        }
        return new Response(null, ResponseStatus.ERROR, "");
    }

    @Override
    public Response deletePhoto(long photoId) throws Exception {
        List<Photo> photos = getList(Photo.class);
        List<Photo> updatedPhotos = photos.stream()
                .filter(el -> el.getId() != photoId)
                .collect(Collectors.toList());
        setList(updatedPhotos, Photo.class);
        return null;
    }

    @Override
    public Response getPhoto(long photoId) throws Exception {
        List<Photo> photos = getList(Photo.class);
        Photo photo = photos.stream().filter(el -> el.getId() == photoId)
                .findFirst().orElse(null);
        log.info(photo);
        return new Response(photo, ResponseStatus.SUCCESS, "Get successfully");
    }

    @Override
    public Response createPhotographer(String name, long expirence, String site) throws Exception {
        List<Photographer> photographers = getList(Photographer.class);
        Photographer photographer = new Photographer();
        long id = BaseUtil.generateID();
        photographer.setId(id);
        photographer.setName(name);
        photographer.setExpirence(expirence);
        photographer.setSite(site);
        photographers.add(photographer);
        setList(photographers, Photographer.class);
        log.info(photographers);
        return new Response(photographer, ResponseStatus.SUCCESS, "");
    }

    @Override
    public Response updatePhotographer(long id, String name, long expirence, String site) throws Exception {
        if (isPhotographerExist(id)) {
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
            return new Response(updatedPhotographers, ResponseStatus.SUCCESS, "");
        }
        return new Response(null, ResponseStatus.ERROR, "");
    }

    @Override
    public Response deletePhotographer(long id) throws Exception {
        if (isPhotographerExist(id)) {
            List<Photographer> photographers = getList(Photographer.class);
            List<Photographer> updatedPhotographers = photographers.stream()
                    .filter(el -> el.getId() != id)
                    .collect(Collectors.toList());
            setList(updatedPhotographers, Photographer.class);
            return new Response(updatedPhotographers, ResponseStatus.SUCCESS, "");
        }
        return new Response(null, ResponseStatus.ERROR, "");
    }

    @Override
    public Response getPhotographer(long id) throws Exception {
        if (isPhotographerExist(id)) {
            List<Photographer> photographers = getList(Photographer.class);
            Photographer photographer = photographers.stream()
                    .filter(el -> el.getId() == id)
                    .findFirst().orElse(null);
            log.info(photographer);
            return new Response(photographer, ResponseStatus.SUCCESS, "Get successfully");
        }
        return new Response(null, ResponseStatus.ERROR, "");
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
        if (isEventExist(id, eventType)) {
            switch (eventType) {
                case Constants.FOOTBALL: {
                    List<FootballEvent> footballEvents = getList(FootballEvent.class);
                    List<FootballEvent> updatedFootballEvents = footballEvents.stream()
                            .filter(el -> el.getId() != id)
                            .collect(Collectors.toList());
                    setList(updatedFootballEvents, FootballEvent.class);
                    return new Response(updatedFootballEvents, ResponseStatus.SUCCESS, "SUCCESS");
                }
                case Constants.IT: {
                    List<ITEvent> itEvents = getList(ITEvent.class);
                    List<ITEvent> updatedITEvents = itEvents.stream()
                            .filter(el -> el.getId() != id)
                            .collect(Collectors.toList());
                    setList(updatedITEvents, ITEvent.class);
                    return new Response(updatedITEvents, ResponseStatus.SUCCESS, "SUCCESS");
                }
                case Constants.MUSIC: {
                    List<MusicEvent> musicEvents = getList(MusicEvent.class);
                    List<MusicEvent> updatedMusicEvents = musicEvents.stream()
                            .filter(el -> el.getId() != id)
                            .collect(Collectors.toList());
                    setList(updatedMusicEvents, MusicEvent.class);
                    return new Response(updatedMusicEvents, ResponseStatus.SUCCESS, "SUCCESS");
                }
            }
        }
        return new Response(null, ResponseStatus.ERROR, "ERROR");
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
