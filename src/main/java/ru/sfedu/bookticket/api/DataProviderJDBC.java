package ru.sfedu.bookticket.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.bookticket.enums.ResponseStatus;
import ru.sfedu.bookticket.models.*;
import ru.sfedu.bookticket.utils.ConfigurationUtil;
import ru.sfedu.bookticket.utils.Response;
import ru.sfedu.bookticket.Constants;
import ru.sfedu.bookticket.utils.BaseUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DataProviderJDBC implements DataProvider {
    private String JDBC_PATH = ConfigurationUtil.getConfigurationEntry(Constants.JDBC_PATH);
    private final String JDBC_NAME = ConfigurationUtil.getConfigurationEntry(Constants.JDBC_NAME);

    private final Logger log = LogManager.getLogger(DataProviderJDBC.class);

    public void setJDBC_PATH(String JDBC_PATH) {
        this.JDBC_PATH = JDBC_PATH;
    }

    public DataProviderJDBC() throws IOException {
    }

    public void initDataSource() throws IOException {
        try {
            if (!isDBExist()) {
                log.debug(Constants.DEBUG_JDBC_INIT_DATA_SOURCE);
                createDB();
                generateData();
                log.info(Constants.JDBC_DATA_SOURCE_MESSAGE);
            }
        } catch (SQLException | IOException e) {
            log.error(e);
        }
    }

    private void createDB() throws IOException, SQLException {
        Connection connection = connect();
        String createDb = new String(
                Files.readAllBytes(
                        Paths.get(ConfigurationUtil
                                .getConfigurationEntry(Constants.INIT_TABLES)
                        )
                )
        );
        Statement st = connection.createStatement();
        st.executeUpdate(createDb);
        st.close();
        connection.close();
    }

    public Connection connect() throws SQLException, IOException {
        final String USER = ConfigurationUtil.getConfigurationEntry(Constants.USER);
        final String PASSWORD = ConfigurationUtil.getConfigurationEntry(Constants.PASSWORD);
        final String PROTOCOL = ConfigurationUtil.getConfigurationEntry(Constants.JDBC_PROTOCOL);

        return DriverManager.getConnection(PROTOCOL + getPath(),USER, PASSWORD);
    }

    public boolean isDBExist() {
        File file = new File(getPath() + Constants.DB_EXTENSION);
        return file.exists();
    }

    public String getPath() {
        return JDBC_PATH + JDBC_NAME;
    }

    public String getClassName(Class c) {
        return c.getSimpleName().toLowerCase();
    }

    public void flushTable(Class c) {
        try{
            Connection connection = connect();
            Statement st = connection.createStatement();
            st.execute(Constants.DElETE + getClassName(c));
            connection.close();
            st.close();
        } catch (SQLException | IOException e) {
            log.error(e);
        }
    }

    public void flushTable(String s) {
        try{
            Connection connection = connect();
            Statement st = connection.createStatement();
            st.execute(Constants.DElETE + s);
            connection.close();
            st.close();
        } catch (SQLException | IOException e) {
            log.error(e);
        }
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
        return new Response(null, ResponseStatus.ERROR, Constants.ERROR_GET_EVENT);
    }

    @Override
    public Response getFootballEvent(long eventId) throws Exception {
        log.debug(Constants.DEBUG_GET_FOOTBALL_EVENT);
        Connection connection = connect();
        if (isExist(FootballEvent.class, eventId, connection)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    Constants.SELECT_ALL +
                            getClassName(FootballEvent.class) +
                            Constants.WHERE +
                            Constants.ID + eventId);
            FootballEvent footballEvent = new FootballEvent();
            if (resultSet.next()) {
                footballEvent.setId(resultSet.getLong(1));
                footballEvent.setName(resultSet.getString(2));
                footballEvent.setDate(resultSet.getString(3));
                footballEvent.setTime(resultSet.getString(4));
                footballEvent.setPrice(resultSet.getString(5));
                List<String> photosList = BaseUtil.transformSQLArrayToList(resultSet.getArray(6));
                footballEvent.setPhotos(photosList);
                Place place = (Place) getPlace(resultSet.getLong(7)).getBody();
                footballEvent.setPlace(place);
                footballEvent.setTeam1(resultSet.getString(8));
                footballEvent.setTeam2(resultSet.getString(9));
            }
            connection.close();
            log.info(footballEvent);
            return new Response(footballEvent, ResponseStatus.SUCCESS, Constants.SUCCESS_GET_FOOTBALL_EVENT);
        } else {
            connection.close();
            return new Response(null, ResponseStatus.ERROR, Constants.ERROR_GET_FOOTBALL_EVENT);
        }
    }

    @Override
    public Response getITEvent(long eventId) throws Exception {
        log.debug(Constants.DEBUG_GET_IT_EVENT);
        Connection connection = connect();
        if (isExist(ITEvent.class, eventId, connection)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    Constants.SELECT_ALL +
                            getClassName(ITEvent.class) +
                            Constants.WHERE +
                            Constants.ID + eventId);
            ITEvent itEvent = new ITEvent();
            if (resultSet.next()) {
                itEvent.setId(resultSet.getLong(1));
                itEvent.setName(resultSet.getString(2));
                itEvent.setDate(resultSet.getString(3));
                itEvent.setTime(resultSet.getString(4));
                itEvent.setPrice(resultSet.getString(5));
                List<String> photosList = BaseUtil.transformSQLArrayToList(resultSet.getArray(6));
                itEvent.setPhotos(photosList);
                Place place = (Place) getPlace(resultSet.getLong(7)).getBody();
                itEvent.setPlace(place);
                itEvent.setStack(resultSet.getString(8));
            }
            connection.close();
            log.info(itEvent);
            return new Response(itEvent, ResponseStatus.SUCCESS, Constants.SUCCESS_GET_IT_EVENT);
        } else {
            connection.close();
            return new Response(null, ResponseStatus.ERROR, Constants.ERROR_GET_IT_EVENT);
        }
    }

    @Override
    public Response getMusicEvent(long eventId) throws Exception {
        log.debug(Constants.DEBUG_GET_MUSIC_EVENT);
        Connection connection = connect();
        if (isExist(MusicEvent.class, eventId, connection)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    Constants.SELECT_ALL +
                            getClassName(MusicEvent.class) +
                            Constants.WHERE +
                            Constants.ID + eventId);
            MusicEvent musicEvent = new MusicEvent();
            if (resultSet.next()) {
                musicEvent.setId(resultSet.getLong(1));
                musicEvent.setName(resultSet.getString(2));
                musicEvent.setDate(resultSet.getString(3));
                musicEvent.setTime(resultSet.getString(4));
                musicEvent.setPrice(resultSet.getString(5));
                List<String> photosList = BaseUtil.transformSQLArrayToList(resultSet.getArray(6));
                musicEvent.setPhotos(photosList);
                Place place = (Place) getPlace(resultSet.getLong(7)).getBody();
                musicEvent.setPlace(place);
                musicEvent.setArtist(resultSet.getString(8));
            }
            connection.close();
            log.info(musicEvent);
            return new Response(musicEvent, ResponseStatus.SUCCESS, Constants.SUCCESS_GET_MUSIC_EVENT);
        } else {
            connection.close();
            return new Response(null, ResponseStatus.ERROR, Constants.ERROR_GET_MUSIC_EVENT);
        }
    }

    @Override
    public Response bookTicket(long eventId, String eventType, String buyerName) throws Exception {
        log.debug(Constants.DEBUG_BOOK_TICKET);
        Connection connection = connect();
        if (isEventExist(eventType, eventId, connection)) {
            List ticketEvent = getTicketEventByEventId(eventType, eventId, connection);
            long ticketEventId;
            if (ticketEvent == null) {
                ticketEventId = BaseUtil.generateID();
                PreparedStatement pst1 = connection.prepareStatement(Constants.INSERT_TICKET_EVENT);
                pst1.setLong(1, ticketEventId);
                pst1.setLong(2, eventId);
                pst1.setString(3, eventType);
                pst1.execute();
                pst1.close();
            } else {
                ticketEventId = (long) ticketEvent.get(0);
            }

            PreparedStatement pst2 = connection.prepareStatement(Constants.INSERT_TICKET);
            Ticket ticket = new Ticket();
            ticket.setId(BaseUtil.generateID());
            ticket.setBuyerName(buyerName);
            String time = String.valueOf(LocalDateTime.now());
            ticket.setPayTime(time);
            Event event = (Event) getEvent(eventId, eventType).getBody();
            ticket.setEvent(event);
            pst2.setLong(1, ticket.getId());
            pst2.setString(2, buyerName);
            pst2.setString(3, time);
            pst2.setLong(4, ticketEventId);
            pst2.execute();
            connection.close();
            pst2.close();
            log.info(ticket);
            return new Response(ticket, ResponseStatus.SUCCESS, Constants.SUCCESS_BOOK_TICKET);
        }
        return new Response(null, ResponseStatus.ERROR, Constants.ERROR_BOOK_TICKET);
    }

    public List getTicketEventByEventId(String eventType, long eventId, Connection connection) {
        try {
            boolean isExist;
            Statement st = connection.createStatement();
            List ticketEvent = new ArrayList<>();
            ResultSet resultSet = st.executeQuery(
                    Constants.SELECT_ALL + Constants.TICKET_EVENT +
                            Constants.WHERE +
                                Constants.EVENT_ID + eventId + Constants.AND + Constants.EVENT_TYPE + eventType);
            isExist = resultSet.next();
            if (isExist) {
                ticketEvent.add(resultSet.getLong(1));
                ticketEvent.add(resultSet.getLong(2));
                ticketEvent.add(resultSet.getString(3));
                return ticketEvent;
            }
            st.close();
            return null;
        } catch (SQLException e) {
            log.error(e);
            return null;
        }
    }

    public List getTicketEventById(long id, Connection connection) {
        try {
            boolean isExist;
            Statement st = connection.createStatement();
            List ticketEvent = new ArrayList<>();
            ResultSet resultSet = st.executeQuery(
                    Constants.SELECT_ALL + Constants.TICKET_EVENT +
                            Constants.WHERE +
                            Constants.ID + id);
            isExist = resultSet.next();
            if (isExist) {
                ticketEvent.add(resultSet.getLong(1));
                ticketEvent.add(resultSet.getLong(2));
                ticketEvent.add(resultSet.getString(3));
                return ticketEvent;
            }
            st.close();
            return null;
        } catch (SQLException e) {
            log.error(e);
            return null;
        }
    }

    public boolean isEventExist(String eventType, long eventId, Connection connection) {
        switch (eventType) {
            case Constants.FOOTBALL: {
                return isExist(FootballEvent.class, eventId, connection);
            }
            case Constants.IT: {
                return isExist(ITEvent.class, eventId, connection);
            }
            case Constants.MUSIC: {
                return isExist(MusicEvent.class, eventId, connection);
            }
        }
        return false;
    }

    @Override
    public Response getTicket(long ticketId) throws Exception {
        log.debug(Constants.DEBUG_GET_EVENT);
        Connection connection = connect();
        if (isExist(Ticket.class, ticketId, connection)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    Constants.SELECT_ALL +
                            getClassName(Ticket.class) +
                            Constants.WHERE +
                            Constants.ID + ticketId);
            Ticket ticket = new Ticket();
            if (resultSet.next()) {
                ticket.setId(resultSet.getLong(1));
                ticket.setBuyerName(resultSet.getString(2));
                ticket.setPayTime(resultSet.getString(3));
                List ticketEvent = getTicketEventById(resultSet.getLong(4), connection);
                Event event = (Event) getEvent((long) ticketEvent.get(1),(String) ticketEvent.get(2)).getBody();
                ticket.setEvent(event);
            }
            connection.close();
            log.info(ticket);
            return new Response(ticket, ResponseStatus.SUCCESS, Constants.SUCCESS_GET_TICKET);
        } else {
            connection.close();
            return new Response(null, ResponseStatus.ERROR, Constants.ERROR_GET_TICKET);
        }
    }

    @Override
    public Response getCity(long cityId) throws Exception {
        log.debug(Constants.DEBUG_GET_CITY);
        Connection connection = connect();
        if (isExist(City.class, cityId, connection)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    Constants.SELECT_ALL +
                            getClassName(City.class) +
                            Constants.WHERE +
                            Constants.ID + cityId);
            City city = new City();
            while (resultSet.next()) {
                city.setId(resultSet.getLong(1));
                city.setName(resultSet.getString(2));
            }
            connection.close();
            log.info(city);
            return new Response(city, ResponseStatus.SUCCESS, Constants.SUCCESS_GET_CITY);
        } else {
            connection.close();
            return new Response(null, ResponseStatus.ERROR, Constants.ERROR_GET_CITY);
        }
    }

    @Override
    public Response createCity(String name) throws Exception {
        log.debug(Constants.DEBUG_CREATE_CITY);
        if (BaseUtil.isNotEmpty(name)) {
            Connection connection = connect();
            PreparedStatement pst = connection.prepareStatement(Constants.INSERT_CITY);
            City city = new City();
            city.setId(BaseUtil.generateID());
            city.setName(name);
            pst.setLong(1, city.getId());
            pst.setString(2, city.getName());
            pst.execute();
            connection.close();
            pst.close();
            return new Response(city, ResponseStatus.SUCCESS, Constants.SUCCESS_CREATE_CITY);
        }
        return new Response(null, ResponseStatus.ERROR, Constants.ERROR_CREATE_CITY);
    }

    @Override
    public Response updateCity(long cityId, String name) throws Exception {
        Connection connection = connect();
        if (isExist(City.class, cityId, connection)) {
            PreparedStatement pst = connection.prepareStatement(Constants.UPDATE_CITY + cityId);
            City city = (City) getCity(cityId).getBody();
            city.setName(name);
            pst.setString(1, name);
            pst.execute();
            connection.close();
            pst.close();
            return new Response(city, ResponseStatus.SUCCESS, Constants.SUCCESS_UPDATE_CITY);
        } else {
            connection.close();
            return new Response(null, ResponseStatus.ERROR, Constants.ERROR_UPDATE_CITY);
        }
    }

    @Override
    public Response deleteCity(long cityId) throws Exception {
        log.debug(Constants.DEBUG_DELETE_CITY);
        Connection connection = connect();
        if (isExist(City.class, cityId, connection)) {
            Statement st = connection.createStatement();
            st.execute(Constants.DElETE + getClassName(City.class) + Constants.WHERE + Constants.ID + cityId);
            connection.close();
            st.close();
            return new Response(null, ResponseStatus.SUCCESS, Constants.SUCCESS_DELETE_CITY);
        } else {
            connection.close();
            return new Response(null, ResponseStatus.ERROR, Constants.ERROR_DELETE_CITY);
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
        Connection connection = connect();
        if (isExist(Place.class, placeId, connection) && isExist(FootballEvent.class, id, connection)) {
            PreparedStatement pst = connection.prepareStatement(Constants.UPDATE_FOOTBALL_EVENT + id);
            FootballEvent footballEvent = (FootballEvent) getEvent(id, Constants.FOOTBALL).getBody();
            footballEvent.setName(name);
            Place place = (Place) getPlace(placeId).getBody();
            footballEvent.setPlace(place);
            footballEvent.setDate(date);
            footballEvent.setTime(time);
            footballEvent.setPrice(price);
            footballEvent.setPhotos(photos);
            footballEvent.setTeam1(team1);
            footballEvent.setTeam2(team2);

            pst.setString(1, name);
            pst.setLong(2, placeId);
            pst.setString(3, date);
            pst.setString(4, time);
            pst.setString(5, price);
            pst.setArray(6, connection.createArrayOf(Constants.VARCHAR, photos.toArray()));
            pst.setString(7, team1);
            pst.setString(8, team2);
            pst.execute();
            connection.close();
            pst.close();
            log.info(footballEvent);
            return new Response(footballEvent, ResponseStatus.SUCCESS, Constants.SUCCESS_UPDATE_FOOTBALL_EVENT);
        } else {
            connection.close();
            return new Response(null, ResponseStatus.ERROR, Constants.ERROR_UPDATE_FOOTBALL_EVENT);
        }
    }

    @Override
    public Response updateITEvent(long id, String name, long placeId, String date, String time, String price, List<String> photos, String stack) throws Exception {
        log.debug(Constants.DEBUG_UPDATE_IT_EVENT);
        Connection connection = connect();
        if (isExist(Place.class, placeId, connection) && isExist(ITEvent.class, id, connection)) {
            PreparedStatement pst = connection.prepareStatement(Constants.UPDATE_IT_EVENT + id);
            ITEvent itEvent = (ITEvent) getEvent(id, Constants.IT).getBody();
            itEvent.setName(name);
            Place place = (Place) getPlace(placeId).getBody();
            itEvent.setPlace(place);
            itEvent.setDate(date);
            itEvent.setTime(time);
            itEvent.setPrice(price);
            itEvent.setPhotos(photos);
            itEvent.setStack(stack);

            pst.setString(1, name);
            pst.setLong(2, placeId);
            pst.setString(3, date);
            pst.setString(4, time);
            pst.setString(5, price);
            pst.setArray(6, connection.createArrayOf(Constants.VARCHAR, photos.toArray()));
            pst.setString(7, stack);
            pst.execute();
            connection.close();
            pst.close();
            log.info(itEvent);
            return new Response(itEvent, ResponseStatus.SUCCESS, Constants.SUCCESS_UPDATE_IT_EVENT);
        } else {
            connection.close();
            return new Response(null, ResponseStatus.ERROR, Constants.ERROR_UPDATE_IT_EVENT);
        }
    }

    @Override
    public Response updateMusicEvent(long id, String name, long placeId, String date, String time, String price, List<String> photos, String artist) throws Exception {
        log.debug(Constants.DEBUG_UPDATE_MUSIC_EVENT);
        Connection connection = connect();
        if (isExist(Place.class, placeId, connection) && isExist(MusicEvent.class, id, connection)) {
            PreparedStatement pst = connection.prepareStatement(Constants.UPDATE_MUSIC_EVENT + id);
            MusicEvent musicEvent = (MusicEvent) getEvent(id, Constants.MUSIC).getBody();
            musicEvent.setName(name);
            Place place = (Place) getPlace(placeId).getBody();
            musicEvent.setPlace(place);
            musicEvent.setDate(date);
            musicEvent.setTime(time);
            musicEvent.setPrice(price);;
            musicEvent.setPhotos(photos);
            musicEvent.setArtist(artist);

            pst.setString(1, name);
            pst.setLong(2, placeId);
            pst.setString(3, date);
            pst.setString(4, time);
            pst.setString(5, price);
            pst.setArray(6, connection.createArrayOf(Constants.VARCHAR, photos.toArray()));
            pst.setString(7, artist);
            pst.execute();
            connection.close();
            pst.close();
            log.info(musicEvent);
            return new Response(musicEvent, ResponseStatus.SUCCESS, Constants.SUCCESS_UPDATE_MUSIC_EVENT);
        } else {
            connection.close();
            return new Response(null, ResponseStatus.ERROR, Constants.ERROR_UPDATE_MUSIC_EVENT);
        }
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
        Connection connection = connect();
        if (isExist(Place.class, placeId, connection)) {
            PreparedStatement pst = connection.prepareStatement(Constants.INSERT_FOOTBALL_EVENT);
            FootballEvent footballEvent = new FootballEvent();
            footballEvent.setId(BaseUtil.generateID());
            footballEvent.setName(name);
            Place place = (Place) getPlace(placeId).getBody();
            footballEvent.setPlace(place);
            footballEvent.setDate(date);
            footballEvent.setTime(time);
            footballEvent.setPrice(price);
            footballEvent.setPhotos(photos);
            footballEvent.setTeam1(team1);
            footballEvent.setTeam2(team2);

            pst.setLong(1, footballEvent.getId());
            pst.setString(2, name);
            pst.setLong(3, placeId);
            pst.setString(4, date);
            pst.setString(5, time);
            pst.setString(6, price);
            pst.setArray(7, connection.createArrayOf(Constants.VARCHAR, photos.toArray()));
            pst.setString(8, team1);
            pst.setString(9, team2);
            pst.execute();
            connection.close();
            pst.close();
            log.info(footballEvent);
            return new Response(footballEvent, ResponseStatus.SUCCESS, Constants.SUCCESS_CREATE_FOOTBALL_EVENT);
        }
        return new Response(null, ResponseStatus.ERROR, Constants.ERROR_PLACE_NOT_EXIST);
    }

    @Override
    public Response createITEvent(String name, long placeId, String date, String time, String price, List<String> photos, String stack) throws Exception {
        log.debug(Constants.DEBUG_CREATE_IT_EVENT);
        Connection connection = connect();
        if (isExist(Place.class, placeId, connection)) {
            PreparedStatement pst = connection.prepareStatement(Constants.INSERT_IT_EVENT);
            ITEvent itEvent = new ITEvent();
            itEvent.setId(BaseUtil.generateID());
            itEvent.setName(name);
            Place place = (Place) getPlace(placeId).getBody();
            itEvent.setPlace(place);
            itEvent.setDate(date);
            itEvent.setTime(time);
            itEvent.setPrice(price);
            itEvent.setPhotos(photos);
            itEvent.setStack(stack);

            pst.setLong(1, itEvent.getId());
            pst.setString(2, name);
            pst.setLong(3, placeId);
            pst.setString(4, date);
            pst.setString(5, time);
            pst.setString(6, price);
            pst.setArray(7, connection.createArrayOf(Constants.VARCHAR, photos.toArray()));
            pst.setString(8, stack);
            pst.execute();
            connection.close();
            pst.close();
            log.info(itEvent);
            return new Response(itEvent, ResponseStatus.SUCCESS, Constants.SUCCESS_CREATE_FOOTBALL_EVENT);
        }
        return new Response(null, ResponseStatus.ERROR, Constants.ERROR_PLACE_NOT_EXIST);
    }

    @Override
    public Response createMusicEvent(String name, long placeId, String date, String time, String price, List<String> photos, String artist) throws Exception {
        log.debug(Constants.DEBUG_CREATE_MUSIC_EVENT);
        Connection connection = connect();
        if (isExist(Place.class, placeId, connection)) {
            PreparedStatement pst = connection.prepareStatement(Constants.INSERT_MUSIC_EVENT);
            MusicEvent musicEvent = new MusicEvent();
            musicEvent.setId(BaseUtil.generateID());
            musicEvent.setName(name);
            Place place = (Place) getPlace(placeId).getBody();
            musicEvent.setPlace(place);
            musicEvent.setDate(date);
            musicEvent.setTime(time);
            musicEvent.setPrice(price);
            musicEvent.setPhotos(photos);
            musicEvent.setArtist(artist);

            pst.setLong(1, musicEvent.getId());
            pst.setString(2, name);
            pst.setLong(3, placeId);
            pst.setString(4, date);
            pst.setString(5, time);
            pst.setString(6, price);
            pst.setArray(7, connection.createArrayOf(Constants.VARCHAR, photos.toArray()));
            pst.setString(8, artist);
            pst.execute();
            connection.close();
            pst.close();
            log.info(musicEvent);
            return new Response(musicEvent, ResponseStatus.SUCCESS, Constants.SUCCESS_CREATE_MUSIC_EVENT);
        }
        return new Response(null, ResponseStatus.ERROR, Constants.ERROR_PLACE_NOT_EXIST);
    }

    @Override
    public Response createPlace(String address, long cityId) throws Exception {
        log.debug(Constants.DEBUG_CREATE_PLACE);
        Connection connection = connect();
        if (isExist(City.class, cityId, connection)) {
            PreparedStatement pst = connection.prepareStatement(Constants.INSERT_PLACE);
            Place place = new Place();
            place.setId(BaseUtil.generateID());
            place.setAddress(address);
            City city = (City) getCity(cityId).getBody();
            place.setCity(city);
            pst.setLong(1, place.getId());
            pst.setString(2, place.getAddress());
            pst.setLong(3, cityId);
            pst.execute();
            connection.close();
            pst.close();
            log.info(place);
            return new Response(place, ResponseStatus.SUCCESS, Constants.SUCCESS_CREATE_PLACE);
        }
        return new Response(null, ResponseStatus.ERROR, Constants.ERROR_CITY_NOT_EXIST);
    }

    @Override
    public Response updatePlace(long placeId, String address, long cityId) throws Exception {
        log.debug(Constants.DEBUG_UPDATE_PLACE);
        Connection connection = connect();
        if (isExist(Place.class, placeId, connection) && isExist(City.class, cityId, connection)) {
            PreparedStatement pst = connection.prepareStatement(Constants.UPDATE_PLACE + placeId);
            Place place = (Place) getPlace(placeId).getBody();
            place.setAddress(address);
            City city = (City) getCity(cityId).getBody();
            place.setCity(city);
            pst.setString(1, address);
            pst.setLong(2, cityId);
            pst.execute();
            connection.close();
            pst.close();
            log.info(place);
            return new Response(place, ResponseStatus.SUCCESS, Constants.SUCCESS_UPDATE_PLACE);
        } else {
            connection.close();
            return new Response(null, ResponseStatus.ERROR, Constants.ERROR_PLACE_NOT_EXIST);
        }
    }

    @Override
    public Response deletePlace(long placeId) throws Exception {
        log.debug(Constants.DEBUG_DELETE_PLACE);
        Connection connection = connect();
        if (isExist(Place.class, placeId, connection)) {
            Statement st = connection.createStatement();
            st.execute(Constants.DElETE + getClassName(Place.class) + Constants.WHERE + Constants.ID + placeId);
            connection.close();
            st.close();
            return new Response(null, ResponseStatus.SUCCESS, Constants.SUCCESS_DELETE_PLACE);
        } else {
            connection.close();
            return new Response(null, ResponseStatus.ERROR, Constants.ERROR_PLACE_NOT_EXIST);
        }
    }

    @Override
    public Response getPlace(long placeId) throws Exception {
        log.debug(Constants.DEBUG_GET_PLACE);
        Connection connection = connect();
        if (isExist(Place.class, placeId, connection)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    Constants.SELECT_ALL +
                            getClassName(Place.class) +
                            Constants.WHERE +
                            Constants.ID + placeId);
            Place place = new Place();
            while (resultSet.next()) {
                place.setId(resultSet.getLong(1));
                place.setAddress(resultSet.getString(2));
                City city = (City) getCity(resultSet.getLong(3)).getBody();
                place.setCity(city);
            }
            connection.close();
            log.info(place);
            return new Response(place, ResponseStatus.SUCCESS, Constants.SUCCESS_GET_PLACE);
        } else {
            connection.close();
            return new Response(null, ResponseStatus.ERROR, Constants.ERROR_PLACE_NOT_EXIST);
        }
    }

    @Override
    public Response createPhoto(String name, String path, long photographerId) throws Exception {
        log.debug(Constants.DEBUG_CREATE_PHOTO);
        Connection connection = connect();
        if (isExist(Photographer.class, photographerId, connection)) {
            PreparedStatement pst = connection.prepareStatement(Constants.INSERT_PHOTO);
            Photo photo = new Photo();
            photo.setId(BaseUtil.generateID());
            photo.setName(name);
            photo.setPath(path);
            Photographer photographer = (Photographer) getPhotographer(photographerId).getBody();
            photo.setPhotographer(photographer);
            pst.setLong(1, photo.getId());
            pst.setString(2, photo.getName());
            pst.setString(3, photo.getPath());
            pst.setLong(4, photographerId);
            pst.execute();
            connection.close();
            pst.close();
            log.info(photo);
            return new Response(photo, ResponseStatus.SUCCESS, Constants.SUCCESS_CREATE_PHOTO);
        }
        return new Response(null, ResponseStatus.ERROR, Constants.ERROR_PHOTOGRAPHER_NOT_EXIST);
    }

    @Override
    public Response deletePhoto(long photoId) throws Exception {
        log.debug(Constants.DEBUG_DELETE_PHOTO);
        Connection connection = connect();
        if (isExist(Photo.class, photoId, connection)) {
            Statement st = connection.createStatement();
            st.execute(Constants.DElETE + getClassName(Photo.class) + Constants.WHERE + Constants.ID + photoId);
            connection.close();
            st.close();
            return new Response(null, ResponseStatus.SUCCESS, Constants.SUCCESS_DELETE_PHOTO);
        } else {
            connection.close();
            return new Response(null, ResponseStatus.ERROR, Constants.ERROR_PHOTO_NOT_EXIST);
        }
    }

    @Override
    public Response getPhoto(long photoId) throws Exception {
        log.debug(Constants.DEBUG_GET_PHOTO);
        Connection connection = connect();
        if (isExist(Photo.class, photoId, connection)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    Constants.SELECT_ALL +
                            getClassName(Photo.class) +
                            Constants.WHERE +
                            Constants.ID + photoId);
            Photo photo = new Photo();
            while (resultSet.next()) {
                photo.setId(resultSet.getLong(1));
                photo.setName(resultSet.getString(2));
                photo.setPath(resultSet.getString(3));
                Photographer photographer = (Photographer) getPhotographer(resultSet.getLong(4)).getBody();
                photo.setPhotographer(photographer);
            }
            connection.close();
            log.info(photo);
            return new Response(photo, ResponseStatus.SUCCESS, Constants.SUCCESS_GET_PHOTO);
        } else {
            connection.close();
            return new Response(null, ResponseStatus.ERROR, Constants.ERROR_PHOTO_NOT_EXIST);
        }
    }

    @Override
    public Response createPhotographer(String name, long expirence, String site) throws Exception {
        log.debug(Constants.DEBUG_CREATE_PHOTOGRAPHER);
        if (BaseUtil.isNotEmpty(name)) {
            Connection connection = connect();
            PreparedStatement pst = connection.prepareStatement(Constants.INSERT_PHOTOGRAPHER);
            Photographer photographer = new Photographer();
            photographer.setId(BaseUtil.generateID());
            photographer.setName(name);
            photographer.setExpirence(expirence);
            photographer.setSite(site);

            pst.setLong(1, photographer.getId());
            pst.setString(2, photographer.getName());
            pst.setLong(3, photographer.getExpirence());
            pst.setString(4, photographer.getSite());
            pst.execute();
            connection.close();
            pst.close();
            log.info(photographer);
            return new Response(photographer, ResponseStatus.SUCCESS, Constants.SUCCESS_CREATE_PHOTOGRAPHER);
        }
        return new Response(null, ResponseStatus.ERROR, Constants.ERROR_CREATE_PHOTOGRAPHER);
    }

    @Override
    public Response updatePhotographer(long id, String name, long expirence, String site) throws Exception {
        log.debug(Constants.DEBUG_UPDATE_PHOTOGRAPHER);
        Connection connection = connect();
        if (isExist(Photographer.class, id, connection)) {
            PreparedStatement pst = connection.prepareStatement(Constants.UPDATE_PHOTOGRAPHER + id);
            Photographer photographer = (Photographer) getPhotographer(id).getBody();
            photographer.setName(name);
            photographer.setExpirence(expirence);
            photographer.setSite(site);

            pst.setString(1, name);
            pst.setLong(2, expirence);
            pst.setString(3, site);
            pst.execute();
            connection.close();
            pst.close();
            log.info(photographer);
            return new Response(photographer, ResponseStatus.SUCCESS, Constants.SUCCESS_UPDATE_PHOTOGRAPHER);
        } else {
            connection.close();
            return new Response(null, ResponseStatus.ERROR, Constants.ERROR_PHOTOGRAPHER_NOT_EXIST);
        }
    }

    @Override
    public Response deletePhotographer(long id) throws Exception {
        log.debug(Constants.DEBUG_DELETE_PHOTOGRAPHER);
        Connection connection = connect();
        if (isExist(Photographer.class, id, connection)) {
            Statement st = connection.createStatement();
            st.execute(Constants.DElETE + getClassName(Photographer.class) + Constants.WHERE + Constants.ID + id);
            connection.close();
            st.close();
            return new Response(null, ResponseStatus.SUCCESS, Constants.SUCCESS_DELETE_PHOTOGRAPHER);
        } else {
            connection.close();
            return new Response(null, ResponseStatus.ERROR, Constants.ERROR_PHOTOGRAPHER_NOT_EXIST);
        }
    }

    @Override
    public Response getPhotographer(long id) throws Exception {
        log.debug(Constants.DEBUG_GET_PHOTOGRAPHER);
        Connection connection = connect();
        if (isExist(Photographer.class, id, connection)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    Constants.SELECT_ALL +
                            getClassName(Photographer.class) +
                            Constants.WHERE +
                            Constants.ID + id);
            Photographer photographer = new Photographer();
            while (resultSet.next()) {
                photographer.setId(resultSet.getLong(1));
                photographer.setName(resultSet.getString(2));
                photographer.setExpirence(resultSet.getLong(3));
                photographer.setSite(resultSet.getString(4));
            }
            connection.close();
            log.info(photographer);
            return new Response(photographer, ResponseStatus.SUCCESS, Constants.SUCCESS_GET_PHOTOGRAPHER);
        } else {
            connection.close();
            return new Response(null, ResponseStatus.ERROR, Constants.ERROR_PHOTOGRAPHER_NOT_EXIST);
        }
    }

    @Override
    public Response deleteEvent(long id, String eventType) throws Exception {
        log.debug(Constants.DEBUG_DELETE_EVENT);
        Connection connection = connect();
        switch (eventType) {
            case Constants.FOOTBALL: {
                if (isExist(FootballEvent.class, id, connection)) {
                    Statement st = connection.createStatement();
                    st.execute(Constants.DElETE + getClassName(FootballEvent.class) + Constants.WHERE + Constants.ID + id);
                    connection.close();
                    st.close();
                    return new Response(null, ResponseStatus.SUCCESS, Constants.SUCCESS_DELETE_EVENT);
                } else {
                    connection.close();
                    return new Response(null, ResponseStatus.ERROR, Constants.ERROR_DELETE_EVENT);
                }
            }
            case Constants.IT: {
                if (isExist(ITEvent.class, id, connection)) {
                    Statement st = connection.createStatement();
                    st.execute(Constants.DElETE + getClassName(ITEvent.class) + Constants.WHERE + Constants.ID + id);
                    connection.close();
                    st.close();
                    return new Response(null, ResponseStatus.SUCCESS, Constants.SUCCESS_DELETE_EVENT);
                } else {
                    connection.close();
                    return new Response(null, ResponseStatus.ERROR, Constants.ERROR_DELETE_EVENT);
                }
            }
            case Constants.MUSIC: {
                if (isExist(MusicEvent.class, id, connection)) {
                    Statement st = connection.createStatement();
                    st.execute(Constants.DElETE + getClassName(MusicEvent.class) + Constants.WHERE + Constants.ID + id);
                    connection.close();
                    st.close();
                    return new Response(null, ResponseStatus.SUCCESS, Constants.SUCCESS_DELETE_EVENT);
                } else {
                    connection.close();
                    return new Response(null, ResponseStatus.ERROR, Constants.ERROR_DELETE_EVENT);
                }
            }
        }
        return new Response(null, ResponseStatus.ERROR, Constants.ERROR_WRONG_EVENT_TYPE);
    }

    public boolean isExist(Class cl, long id, Connection connection) {
        try {
            Statement st = connection.createStatement();
            ResultSet resultSet = st.executeQuery(Constants.SELECT_ALL + getClassName(cl) + Constants.WHERE + Constants.ID + id);
            boolean isExist = resultSet.next();
            st.close();
            return isExist;
        } catch (SQLException e) {
            log.error(e);
            return false;
        }
    }

    public void generateData() throws IOException, SQLException {
        generateCity();
        generatePlace();
        generatePhotographer();
        generatePhoto();
        generateFootballEvent();
        generateITEvent();
        generateMusicEvent();
        generateTicket();
    }

    private void generateCity() throws IOException, SQLException {
        Connection connection = connect();
        PreparedStatement pst = connection.prepareStatement(Constants.INSERT_CITY);
        pst.setLong(1, 1);
        pst.setString(2, "test");
        pst.execute();
        connection.close();
        pst.close();
    }

    private void generatePlace() throws IOException, SQLException {
        Connection connection = connect();
        PreparedStatement pst = connection.prepareStatement(Constants.INSERT_PLACE);
        pst.setLong(1, 1);
        pst.setString(2, "test");
        pst.setLong(3,1);
        pst.execute();
        connection.close();
        pst.close();
    }

    private void generatePhotographer() throws IOException, SQLException {
        Connection connection = connect();
        PreparedStatement pst = connection.prepareStatement(Constants.INSERT_PHOTOGRAPHER);
        pst.setLong(1, 1);
        pst.setString(2, "test");
        pst.setLong(3,100);
        pst.setString(4, "test.com");
        pst.execute();
        connection.close();
        pst.close();
    }

    private void generatePhoto() throws IOException, SQLException {
        Connection connection = connect();
        PreparedStatement pst = connection.prepareStatement(Constants.INSERT_PHOTO);
        pst.setLong(1, 1);
        pst.setString(2, "test");
        pst.setString(3,"testtt.com");
        pst.setLong(4, 1);
        pst.execute();
        connection.close();
        pst.close();
    }

    private void generateFootballEvent() throws IOException, SQLException {
        Connection connection = connect();
        PreparedStatement pst = connection.prepareStatement(Constants.INSERT_FOOTBALL_EVENT);
        pst.setLong(1, 1);
        pst.setString(2, "Football");
        pst.setLong(3, 1);
        pst.setString(4,"01-01-1970");
        pst.setString(5,"00:00");
        pst.setString(6,"100S");
        List<String> photos = new BaseUtil<String>().transformStringArgumentsToList("1");
        pst.setArray(7, connection.createArrayOf(Constants.VARCHAR, photos.toArray()));
        pst.setString(8, "team1");
        pst.setString(9,"team2");
        pst.execute();
        connection.close();
        pst.close();
    }

    private void generateITEvent() throws IOException, SQLException {
        Connection connection = connect();
        PreparedStatement pst = connection.prepareStatement(Constants.INSERT_IT_EVENT);
        pst.setLong(1, 2);
        pst.setString(2, "IT");
        pst.setLong(3, 1);
        pst.setString(4,"01-01-1970");
        pst.setString(5,"00:00");
        pst.setString(6,"100S");
        List<String> photos = new BaseUtil<String>().transformStringArgumentsToList("1");
        pst.setArray(7, connection.createArrayOf(Constants.VARCHAR, photos.toArray()));
        pst.setString(8, "stack");
        pst.execute();
        connection.close();
        pst.close();
    }

    private void generateMusicEvent() throws IOException, SQLException {
        Connection connection = connect();
        PreparedStatement pst = connection.prepareStatement(Constants.INSERT_MUSIC_EVENT);
        pst.setLong(1, 3);
        pst.setString(2, "Music");
        pst.setLong(3, 1);
        pst.setString(4,"01-01-1970");
        pst.setString(5,"00:00");
        pst.setString(6,"100S");
        List<String> photos = new BaseUtil<String>().transformStringArgumentsToList("1");
        pst.setArray(7, connection.createArrayOf(Constants.VARCHAR, photos.toArray()));
        pst.setString(8, "artist");
        pst.execute();
        connection.close();
        pst.close();
    }

    private void generateTicket() throws IOException, SQLException {
        Connection connection = connect();

        PreparedStatement pst1 = connection.prepareStatement(Constants.INSERT_TICKET_EVENT);
        pst1.setLong(1, 1);
        pst1.setLong(2, 1);
        pst1.setString(3,"FOOTBALL");
        pst1.execute();
        pst1.close();

        PreparedStatement pst2 = connection.prepareStatement(Constants.INSERT_TICKET);
        pst2.setLong(1, 1);
        pst2.setString(2, "buyer");
        pst2.setString(3,"01-01-1970 00:00");
        pst2.setLong(4,1);
        pst2.execute();
        pst2.close();
        connection.close();
    }
}