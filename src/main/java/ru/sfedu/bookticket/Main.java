package ru.sfedu.bookticket;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.bookticket.api.DataProvider;
import ru.sfedu.bookticket.api.DataProviderCSV;
import ru.sfedu.bookticket.api.DataProviderJDBC;
import ru.sfedu.bookticket.api.DataProviderXML;
import ru.sfedu.bookticket.utils.BaseUtil;
import ru.sfedu.bookticket.utils.Response;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class Main {
    private static Logger log = LogManager.getLogger(Main.class);
    public  Main() {}

    private static DataProvider selectDataProvider(String arg) {
        switch (arg.toLowerCase(Locale.ROOT)) {
            case Constants.CSV: {
                try {
                    DataProviderCSV provider = new DataProviderCSV();
                    provider.initDataSource();
                    return provider;
                } catch (IOException | CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
                    log.error(e);
                    return null;
                }
            }
            case Constants.XML: {
                try {
                    DataProviderXML provider = new DataProviderXML();
                    provider.initDataSource();
                    return provider;
                } catch (IOException e) {
                    log.error(e);
                    return null;
                }
            }
            case Constants.JDBC: {
                try {
                    DataProviderJDBC provider = new DataProviderJDBC();
                    provider.initDataSource();
                    return provider;
                } catch (IOException e) {
                    log.error(e);
                    return null;                }
            }
        }
        log.error(Constants.WRONG_PROVIDER);
        return null;
    }

    private static Response callMethod(DataProvider provider, List<String> arguments) {
        String method = arguments.get(1);
        switch (method.toLowerCase(Locale.ROOT)) {
            case Constants.GET_EVENT: {
                long id = Long.parseLong(arguments.get(2));
                String eventType = arguments.get(3);
                try {
                    return provider.getEvent(id, eventType);
                } catch (Exception e) {
                    log.error(e);
                    return null;
                }
            }
            case Constants.GET_FOOTBALL_EVENT: {
                long id = Long.parseLong(arguments.get(2));
                try {
                    return provider.getFootballEvent(id);
                } catch (Exception e) {
                    log.error(e);
                    return null;
                }
            }
            case Constants.GET_IT_EVENT: {
                long id = Long.parseLong(arguments.get(2));
                try {
                    return provider.getITEvent(id);
                } catch (Exception e) {
                    log.error(e);
                    return null;
                }
            }
            case Constants.GET_MUSIC_EVENT: {
                long id = Long.parseLong(arguments.get(2));
                try {
                    return provider.getMusicEvent(id);
                } catch (Exception e) {
                    log.error(e);
                    return null;
                }
            }
            case Constants.BOOK_TICKET: {
                long id = Long.parseLong(arguments.get(2));
                String eventType = arguments.get(3);
                String buyerName = arguments.get(4);
                try {
                    return provider.bookTicket(id, eventType, buyerName);
                } catch (Exception e) {
                    log.error(e);
                    return null;
                }
            }
            case Constants.GET_TICKET: {
                long id = Long.parseLong(arguments.get(2));
                try {
                    return provider.getTicket(id);
                } catch (Exception e) {
                    log.error(e);
                    return null;
                }
            }
            case Constants.GET_CITY: {
                long id = Long.parseLong(arguments.get(2));
                try {
                    return provider.getCity(id);
                } catch (Exception e) {
                    log.error(e);
                    return null;
                }
            }
            case Constants.CREATE_CITY: {
                String name = arguments.get(2);
                try {
                    return provider.createCity(name);
                } catch (Exception e) {
                    log.error(e);
                    return null;
                }
            }
            case Constants.PUT_CITY: {
                long id = Long.parseLong(arguments.get(2));
                String name = arguments.get(3);
                try {
                    return provider.updateCity(id, name);
                } catch (Exception e) {
                    log.error(e);
                    return null;
                }
            }
            case Constants.DELETE_CITY: {
                long id = Long.parseLong(arguments.get(2));
                try {
                    return provider.deleteCity(id);
                } catch (Exception e) {
                    log.error(e);
                    return null;
                }
            }
            case Constants.PUT_EVENT: {
                String eventType = arguments.get(2);
                long eventId = Long.parseLong(arguments.get(3));
                String name = arguments.get(4);
                long placeId = Long.parseLong(arguments.get(5));
                String date = arguments.get(6);
                String time = arguments.get(7);
                String price = arguments.get(8);
                List<String> photos = new BaseUtil<String>().transformStringArgumentsToList(arguments.get(9));
                String eventSpecialArgs = arguments.get(10);
                try {
                    return provider.updateEvent(eventType, eventId, name, placeId, date, time, price, photos, eventSpecialArgs);
                } catch (Exception e) {
                    log.error(e);
                    return null;
                }
            }
            case Constants.PUT_FOOTBALL_EVENT: {
                long eventId = Long.parseLong(arguments.get(2));
                String name = arguments.get(3);
                long placeId = Long.parseLong(arguments.get(4));
                String date = arguments.get(5);
                String time = arguments.get(6);
                String price = arguments.get(7);
                List<String> photos = new BaseUtil<String>().transformStringArgumentsToList(arguments.get(8));
                String team1 = arguments.get(9);
                String team2 = arguments.get(10);
                try {
                    return provider.updateFootballEvent(eventId, name, placeId, date, time, price, photos, team1, team2);
                } catch (Exception e) {
                    log.error(e);
                    return null;
                }
            }
            case Constants.PUT_IT_EVENT: {
                long eventId = Long.parseLong(arguments.get(2));
                String name = arguments.get(3);
                long placeId = Long.parseLong(arguments.get(4));
                String date = arguments.get(5);
                String time = arguments.get(6);
                String price = arguments.get(7);
                List<String> photos = new BaseUtil<String>().transformStringArgumentsToList(arguments.get(8));
                String stack = arguments.get(9);
                try {
                    return provider.updateITEvent(eventId, name, placeId, date, time, price, photos, stack);
                } catch (Exception e) {
                    log.error(e);
                    return null;
                }
            }
            case Constants.PUT_MUSIC_EVENT: {
                long eventId = Long.parseLong(arguments.get(2));
                String name = arguments.get(3);
                long placeId = Long.parseLong(arguments.get(4));
                String date = arguments.get(5);
                String time = arguments.get(6);
                String price = arguments.get(7);
                List<String> photos = new BaseUtil<String>().transformStringArgumentsToList(arguments.get(8));
                String artist = arguments.get(9);
                try {
                    return provider.updateMusicEvent(eventId, name, placeId, date, time, price, photos, artist);
                } catch (Exception e) {
                    log.error(e);
                    return null;
                }
            }
            case Constants.CREATE_EVENT: {
                String eventType = arguments.get(2);
                String name = arguments.get(3);
                long placeId = Long.parseLong(arguments.get(4));
                String date = arguments.get(5);
                String time = arguments.get(6);
                String price = arguments.get(7);
                List<String> photos = new BaseUtil<String>().transformStringArgumentsToList(arguments.get(8));
                String eventSpecialArgs = arguments.get(9);
                try {
                    return provider.createEvent(eventType, name, placeId, date, time, price, photos, eventSpecialArgs);
                } catch (Exception e) {
                    log.error(e);
                    return null;
                }
            }
            case Constants.CREATE_FOOTBALL_EVENT: {
                String name = arguments.get(2);
                long placeId = Long.parseLong(arguments.get(3));
                String date = arguments.get(4);
                String time = arguments.get(5);
                String price = arguments.get(6);
                List<String> photos = new BaseUtil<String>().transformStringArgumentsToList(arguments.get(7));
                String team1 = arguments.get(8);
                String team2 = arguments.get(9);
                try {
                    return provider.createFootballEvent(name, placeId, date, time, price, photos, team1, team2);
                } catch (Exception e) {
                    log.error(e);
                    return null;
                }
            }
            case Constants.CREATE_IT_EVENT: {
                String name = arguments.get(2);
                long placeId = Long.parseLong(arguments.get(3));
                String date = arguments.get(4);
                String time = arguments.get(5);
                String price = arguments.get(6);
                List<String> photos = new BaseUtil<String>().transformStringArgumentsToList(arguments.get(7));
                String stack = arguments.get(8);
                try {
                    return provider.createITEvent(name, placeId, date, time, price, photos, stack);
                } catch (Exception e) {
                    log.error(e);
                    return null;
                }
            }
            case Constants.CREATE_MUSIC_EVENT: {
                String name = arguments.get(2);
                long placeId = Long.parseLong(arguments.get(3));
                String date = arguments.get(4);
                String time = arguments.get(5);
                String price = arguments.get(6);
                List<String> photos = new BaseUtil<String>().transformStringArgumentsToList(arguments.get(7));
                String artist = arguments.get(8);
                try {
                    return provider.createMusicEvent(name, placeId, date, time, price, photos, artist);
                } catch (Exception e) {
                    log.error(e);
                    return null;
                }
            }
            case Constants.CREATE_PLACE: {
                String address = arguments.get(2);
                long cityId = Long.parseLong(arguments.get(3));
                try {
                    return provider.createPlace(address, cityId);
                } catch (Exception e) {
                    log.error(e);
                    return null;
                }
            }
            case Constants.PUT_PLACE: {
                long placeId = Long.parseLong(arguments.get(2));
                String address = arguments.get(3);
                long cityId = Long.parseLong(arguments.get(4));
                try {
                    return provider.updatePlace(placeId, address, cityId);
                } catch (Exception e) {
                    log.error(e);
                    return null;
                }
            }
            case Constants.DELETE_PLACE: {
                long placeId = Long.parseLong(arguments.get(2));
                try {
                    return provider.deletePlace(placeId);
                } catch (Exception e) {
                    log.error(e);
                    return null;
                }
            }
            case Constants.GET_PLACE: {
                long placeId = Long.parseLong(arguments.get(2));
                try {
                    return provider.getPlace(placeId);
                } catch (Exception e) {
                    log.error(e);
                    return null;
                }
            }
            case Constants.CREATE_PHOTO: {
                String name = arguments.get(2);
                String path = arguments.get(3);
                Long photographerId = Long.parseLong(arguments.get(4));
                try {
                    return provider.createPhoto(name, path, photographerId);
                } catch (Exception e) {
                    log.error(e);
                    return null;
                }
            }
            case Constants.DELETE_PHOTO: {
                long photoId = Long.parseLong(arguments.get(2));
                try {
                    return provider.deletePhoto(photoId);
                } catch (Exception e) {
                    log.error(e);
                    return null;
                }
            }
            case Constants.GET_PHOTO: {
                long photoId = Long.parseLong(arguments.get(2));
                try {
                    return provider.getPhoto(photoId);
                } catch (Exception e) {
                    log.error(e);
                    return null;
                }
            }
            case Constants.CREATE_PHOTOGRAPHER: {
                String name = arguments.get(2);
                Long expirence = Long.parseLong(arguments.get(3));
                String site = arguments.get(4);
                try {
                    return provider.createPhotographer(name, expirence, site);
                } catch (Exception e) {
                    log.error(e);
                    return null;
                }
            }
            case Constants.PUT_PHOTOGRAPHER: {
                Long id = Long.parseLong(arguments.get(2));
                String name = arguments.get(3);
                Long expirence = Long.parseLong(arguments.get(4));
                String site = arguments.get(5);
                try {
                    return provider.updatePhotographer(id, name, expirence, site);
                } catch (Exception e) {
                    log.error(e);
                    return null;
                }
            }
            case Constants.DELETE_PHOTOGRAPHER: {
                Long id = Long.parseLong(arguments.get(2));
                try {
                    return provider.deletePhotographer(id);
                } catch (Exception e) {
                    log.error(e);
                    return null;
                }
            }
            case Constants.GET_PHOTOGRAPHER: {
                Long id = Long.parseLong(arguments.get(2));
                try {
                    return provider.getPhotographer(id);
                } catch (Exception e) {
                    log.error(e);
                    return null;
                }
            }
            case Constants.DELETE_EVENT: {
                Long id = Long.parseLong(arguments.get(2));
                String eventType = arguments.get(3);
                try {
                    return provider.deleteEvent(id, eventType);
                } catch (Exception e) {
                    log.error(e);
                    return null;
                }
            }
        }
        log.error(Constants.WRONG_METHOD);
        return null;
    }

    public static void main(String[] args) {
        try {
            List<String> listArgs = Arrays.asList(args);
            if (listArgs.size() == 0) {
                log.error(Constants.ARGS_IS_EMPTY);
                System.exit(1);
            }
            String providerType = listArgs.get(0);
            DataProvider provider = selectDataProvider(providerType);
            if (provider == null) System.exit(1);
            Response response = callMethod(provider, listArgs);
            if (response == null) System.exit(1);
            log.info(response);
        } catch (ArrayIndexOutOfBoundsException e) {
            log.error(Constants.WRONG_ARGS);
            System.exit(1);
        }
    }
}