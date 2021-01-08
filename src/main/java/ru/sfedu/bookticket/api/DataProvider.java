package ru.sfedu.bookticket.api;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import ru.sfedu.bookticket.utils.Response;

import java.io.IOException;
import java.util.List;

public interface DataProvider {
    /**
     * Метод инициализирует дата соурс
     * @throws IOException
     * @throws CsvDataTypeMismatchException
     * @throws CsvRequiredFieldEmptyException
     */
    public void initDataSource() throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException;

    /**
     * Метод позволяет получить entity bean
     * FootballEvent, ITEvent или MusicEvent.
     * Расширяющий метод getFootballEvent вызывается когда в методе getEvent параметр eventType == “FOOTBALL”
     * Расширяющий метод getITEvent вызывается когда в методе getEvent параметр eventType == “IT”
     * Расширяющий метод getMusicEvent вызывается когда в методе getEvent параметр eventType == “MUSIC”
     * @param eventId
     * @param eventType
     * @return Response
     *
     * Если метод выполнился успешно:
     *  Body: FootballEvent | ITEVent | MusicEvent
     *  Status: SUCCESS
     *  Message: “Get event success”
     *
     * Если метод выполнился неудачно:
     *  Status: ERROR
     *  Message: “Get event error!”
    * */
    public Response getEvent(long eventId, String eventType) throws Exception;

    /**
     * Метод позволяет получить entity bean FootballEvent.
     * Вызывается когда параметр eventType = “FOOTBALL” в родительском методе getEvent.
     * Метод получает параметры из родительского метода getEvent
     * @param eventId
     * @return Response
     * @throws Exception
     *
     * Если метод выполнился успешно:
     *  Body: FootballEvent
     *  Status: SUCCESS
     *  Message: “Get football event success”
     *
     * Если метод выполнился неудачно:
     *  Status: ERROR
     *  Message: “Get football event error!”
     */
    public Response getFootballEvent(long eventId) throws Exception;

    /**
     * Метод позволяет получить entity bean ITEvent.
     * Вызывается когда параметр eventType = “IT” в родительском методе getEvent.
     * Метод получает параметры из родительского метода getEvent
     * @param eventId
     * @return Response
     * @throws Exception
     *
     * Если метод выполнился успешно:
     *  Body: ITEvent
     *  Status: SUCCESS
     *  Message: “Get it event success”
     *
     * Если метод выполнился неудачно:
     *  Status: ERROR
     *  Message: “Get it event error!”
     */
    public Response getITEvent(long eventId) throws Exception;

    /**
     * Метод позволяет получить entity bean MusicEvent.
     * Вызывается когда параметр eventType = “MUSIC” в родительском методе getEvent.
     * Метод получает параметры из родительского метода getEvent
     * @param eventId
     * @return Response
     * @throws Exception
     *
     * Если метод выполнился успешно:
     *  Body: MusicEvent
     *  Status: SUCCESS
     *  Message: “Get music event success”
     *
     * Если метод выполнился неудачно:
     *  Status: ERROR
     *  Message: “Get music event error!”
     */
    public Response getMusicEvent(long eventId) throws Exception;

    /**
     * Метод позволяет бронировать билет. Создается entity bean Ticket
     * @param eventId
     * @param eventType
     * @param buyerName
     * @return Response
     * @throws Exception
     *
     * Если метод выполнился успешно:
     *  Body: Ticket
     *  Status: SUCCESS
     *  Message: “Book ticket success”
     *
     * Если метод выполнился неудачно:
     *  Status: ERROR
     *  Message: “Book ticket error”
     */
    public Response bookTicket(long eventId, String eventType, String buyerName) throws Exception;

    /**
     * Метод позволяет получить entity bean Ticket
     * @param ticketId
     * @return Response
     * @throws Exception
     *
     * Если метод выполнился успешно:
     *  Body: Ticket
     *  Status: SUCCESS
     *  Message: “Get ticket success”
     *
     * Если метод выполнился неудачно:
     *  Status: ERROR
     *  Message: “Get ticket error”
     */
    public Response getTicket(long ticketId) throws Exception;

    /**
     * Метод позволяет получить entity bean City
     * @param cityId
     * @return Response
     * @throws Exception
     * Если метод выполнился успешно:
     *  Body: City
     *  Status: SUCCESS
     *  Message: “Get city success”
     *
     * Если метод выполнился неудачно:
     *  Status: ERROR
     *  Message: “Get city error”
     */
    public Response getCity(long cityId) throws Exception;

    /**
     * Метод позволяет создать entity bean City
     * @param name
     * @return Response
     * @throws Exception
     *
     * Если метод выполнился успешно:
     *  Body: City
     *  Status: SUCCESS
     *  Message: “Create city success”
     *
     * Если метод выполнился неудачно:
     *  Status: ERROR
     *  Message: “Create city error”
     */
    public Response createCity(String name) throws Exception;

    /**
     * Метод позволяет изменить entity bean City
     * @param cityId
     * @param name
     * @return Response
     * @throws Exception
     *
     * Если метод выполнился успешно:
     *  Body: City
     *  Status: SUCCESS
     *  Message: “Update city success”
     *
     * Если метод выполнился неудачно:
     *  Status: ERROR
     *  Message: “Update city error”
     */
    public Response updateCity(long cityId, String name) throws Exception;

    /**
     * Метод позволяет удалить entity bean City
     * @param cityId
     * @return Response
     * @throws Exception
     *
     * Если метод выполнился успешно:
     *  Status: SUCCESS
     *  Message: “Delete city success”
     *
     * Если метод выполнился неудачно:
     *  Status: ERROR
     *  Message: “Delete city error”
     */
    public Response deleteCity(long cityId) throws Exception;

    /**
     * Метод позволяет изменить entity bean FootballEvent, ITEvent или MusicEvent.
     * Расширяющий метод updateFootballEvent вызывается когда в методе updateEvent параметр eventType == “FOOTBALL”
     * Расширяющий метод updateITEvent вызывается когда в методе updateEvent параметр eventType == “IT”
     * Расширяющий метод updateMusicEvent вызывается когда в методе updateEvent параметр eventType == “MUSIC”
     * @param eventType
     * @param id
     * @param name
     * @param placeId
     * @param date
     * @param time
     * @param price
     * @param photos
     * @param eventSpecialArgs
     * @return Response
     * @throws Exception
     *
     * Если метод выполнился успешно:
     *  Body: FootballEvent | ITEvent | MusicEvent
     *  Status: SUCCESS
     *  Message: “Update (football | it | music) event success”
     *
     * Если метод выполнился неудачно:
     *  Status: ERROR
     *  Message: “Update (football | it | music) event error”
     */
    public Response updateEvent(String eventType, long id, String name, long placeId, String date, String time, String price, List<String> photos, String eventSpecialArgs) throws Exception;

    /**
     * Метод позволяет изменить entity bean FootballEvent.
     * Вызывается когда параметр eventType = “FOOTBALL”  в родительском методе updateEvent.
     * Метод получает параметры из родительского метода updateEvent.
     * team1 и team2 берутся из eventSpecialArgs полученного из родительского метода updateEvent
     * @param id
     * @param name
     * @param placeId
     * @param date
     * @param time
     * @param price
     * @param photos
     * @param team1
     * @param team2
     * @return Response
     * @throws Exception
     *
     * Если метод выполнился успешно:
     *  Body: FootballEvent
     *  Status: SUCCESS
     *  Message: “Update football event success”
     *
     * Если метод выполнился неудачно:
     *  Status: ERROR
     *  Message: “Update football event error”
     */
    public Response updateFootballEvent(long id, String name, long placeId, String date, String time, String price, List<String> photos, String team1, String team2) throws Exception;

    /**
     * Метод позволяет изменить entity bean ITEvent.
     * Вызывается когда параметр eventType = “IT” в родительском методе updateEvent.
     * Метод получает параметры из родительского метода updateEvent.
     * stack берется из  eventSpecialArgs полученного из родительского метода updateEvent
     * @param id
     * @param name
     * @param placeId
     * @param date
     * @param time
     * @param price
     * @param photos
     * @param stack
     * @return Response
     * @throws Exception
     *
     * Если метод выполнился успешно:
     *  Body: ITEvent
     *  Status: SUCCESS
     *  Message: “Update it event success”
     *
     * Если метод выполнился неудачно:
     *  Status: ERROR
     *  Message: “Update it event error”
     */
    public Response updateITEvent(long id, String name, long placeId, String date, String time, String price, List<String> photos, String stack) throws Exception;

    /**
     * Метод позволяет изменить entity bean MusicEvent.
     * Вызывается когда параметр eventType = “MUSIC” в родительском методе updateEvent.
     * Метод получает параметры из родительского метода updateEvent.
     * artist берется из eventSpecialArgs полученного из родительского метода updateEvent
     * @param id
     * @param name
     * @param placeId
     * @param date
     * @param time
     * @param price
     * @param photos
     * @param artist
     * @return Response
     * @throws Exception
     *
     * Если метод выполнился успешно:
     *  Body: MusicEvent
     *  Status: SUCCESS
     *  Message: “Update music event success”
     *
     * Если метод выполнился неудачно:
     *  Status: ERROR
     *  Message: “Update music event error”
     */
    public Response updateMusicEvent(long id, String name, long placeId, String date, String time, String price, List<String> photos, String artist) throws Exception;

    /**
     * Метод позволяет создать entity bean FootballEvent, ITEvent, MusicEvent в зависимости от входного параметра eventType.
     * Расширяющий метод createFootballEvent вызывается когда в методе createEvent параметр eventType == “FOOTBALL”
     * Расширяющий метод createITEvent вызывается когда в методе createEvent параметр eventType == “IT”
     * Расширяющий метод createMusicEvent вызывается когда в методе createEvent параметр eventType == “MUSIC”
     * @param eventType
     * @param name
     * @param placeId
     * @param date
     * @param time
     * @param price
     * @param photos
     * @param eventSpecialArgs
     * @return Response
     * @throws Exception
     *
     * Если метод выполнился успешно:
     *  Body: FootballEvent | ITEvent | MusicEvent
     *  Status: SUCCESS
     *  Message: “Create (football | it | music) event success”
     *
     * Если метод выполнился неудачно:
     *  Status: ERROR
     *  Message: “Create (football | it | music) event error”
     */
    public Response createEvent(String eventType, String name, long placeId, String date, String time, String price, List<String> photos, String eventSpecialArgs) throws Exception;

    /**
     * Метод позволяет создать entity bean FootballEvent.
     * Вызывается когда параметр eventType = “FOOTBALL”  в родительском методе createEvent.
     * Метод получает параметры из родительского метода createEvent.
     * team1 и team2 берутся из  eventSpecialArgs полученного из родительского метода createEvent
     * @param name
     * @param placeId
     * @param date
     * @param time
     * @param price
     * @param photos
     * @param team1
     * @param team2
     * @return Response
     * @throws Exception
     *
     * Если метод выполнился успешно:
     *  Body: FootballEvent
     *  Status: SUCCESS
     *  Message: “Create football event success”
     *
     * Если метод выполнился неудачно:
     *  Status: ERROR
     *  Message: “Create football event error”
     */
    public Response createFootballEvent(String name, long placeId, String date, String time, String price, List<String> photos, String team1, String team2) throws Exception;

    /**
     * Метод позволяет создать entity bean ITEvent.
     * Вызывается когда параметр eventType = “IT”  в родительском методе createEvent.
     * Метод получает параметры из родительского метода createEvent.
     * stack берется из eventSpecialArgs полученного из родительского метода createEvent
     * @param name
     * @param placeId
     * @param date
     * @param time
     * @param price
     * @param photos
     * @param stack
     * @return Response
     * @throws Exception
     *
     * Если метод выполнился успешно:
     *  Body: ITEvent
     *  Status: SUCCESS
     *  Message: “Create it event success”
     *
     * Если метод выполнился неудачно:
     *  Status: ERROR
     *  Message: “Create it event error”
     */
    public Response createITEvent(String name, long placeId, String date, String time, String price, List<String> photos, String stack) throws Exception;

    /**
     * Метод позволяет создать entity bean MusicEvent.
     * Вызывается когда параметр eventType = “MUSIC”  в родительском методе createEvent.
     * Метод получает параметры из родительского метода createEvent.
     * artist берется из eventSpecialArgs полученного из родительского метода createEvent
     * @param name
     * @param placeId
     * @param date
     * @param time
     * @param price
     * @param photos
     * @param artist
     * @return Response
     * @throws Exception
     *
     * Если метод выполнился успешно:
     *  Body: MusicEvent
     *  Status: SUCCESS
     *  Message: “Create music event success”
     *
     * Если метод выполнился неудачно:
     *  Status: ERROR
     *  Message: “Create music event error”
     */
    public Response createMusicEvent(String name, long placeId, String date, String time, String price, List<String> photos, String artist) throws Exception;

    /**
     * Метод позволяет создать entity bean Place.
     * @param address
     * @param cityId
     * @return Response
     * @throws Exception
     *
     * Если метод выполнился успешно:
     *  Body: Place
     *  Status: SUCCESS
     *  Message: “Create place success”
     *
     * Если метод выполнился неудачно:
     *  Status: ERROR
     *  Message: “Create place error”
     */
    public Response createPlace(String address, long cityId) throws Exception;

    /**
     * Метод позволяет изменить entity bean Place.
     * @param placeId
     * @param address
     * @param cityId
     * @return Response
     * @throws Exception
     *
     * Если метод выполнился успешно:
     *  Body: Place
     *  Status: SUCCESS
     *  Message: “Update place success”
     *
     * Если метод выполнился неудачно:
     *  Status: ERROR
     *  Message: “Update place error”
     */
    public Response updatePlace(long placeId, String address, long cityId) throws Exception;

    /**
     * Метод позволяет удалить entity bean Place
     * @param placeId
     * @return Response
     * @throws Exception
     *
     * Если метод выполнился успешно:
     *  Status: SUCCESS
     *  Message: “Delete place success”
     *
     * Если метод выполнился неудачно:
     *  Status: ERROR
     *  Message: “Delete place error”
     */
    public Response deletePlace(long placeId) throws Exception;

    /**
     * Метод позволяет получить entity bean Place.
     * @param placeId
     * @return Response
     * @throws Exception
     *
     * Если метод выполнился успешно:
     *  Body: Place
     *  Status: SUCCESS
     *  Message: “Get place success”
     *
     * Если метод выполнился неудачно:
     *  Status: ERROR
     *  Message: “Get place error”
     */
    public Response getPlace(long placeId) throws Exception;

    /**
     * Метод позволяет создать entity bean Photo.
     * @param name
     * @param path
     * @param photographerId
     * @return Response
     * @throws Exception
     *
     * Если метод выполнился успешно:
     *  Body: Photo
     *  Status: SUCCESS
     *  Message: “Create photo success”
     *
     * Если метод выполнился неудачно:
     *  Status: ERROR
     *  Message: “Create photo error”
     */
    public Response createPhoto(String name, String path, long photographerId) throws Exception;

    /**
     * Метод позволяет удалить entity bean Photo
     * @param photoId
     * @return Response
     * @throws Exception
     *
     * Если метод выполнился успешно:
     *  Status: SUCCESS
     *  Message: “Delete photo success”
     *
     * Если метод выполнился неудачно:
     *  Status: ERROR
     *  Message: “Delete photo error”
     */
    public Response deletePhoto(long photoId) throws Exception;

    /**
     * Метод позволяет получить entity bean Photo.
     * @param photoId
     * @return Response
     * @throws Exception
     *
     * Если метод выполнился успешно:
     *  Body: Photo
     *  Status: SUCCESS
     *  Message: “Get photo success”
     *
     * Если метод выполнился неудачно:
     *  Status: ERROR
     *  Message: “Get photo error”
     */
    public Response getPhoto(long photoId) throws Exception;

    /**
     * Метод позволяет создать entity bean Photographer.
     * @param name
     * @param expirence
     * @param site
     * @return Response
     * @throws Exception
     *
     * Если метод выполнился успешно:
     *  Body: Photographer
     *  Status: SUCCESS
     *  Message: “Create photographer success”
     *
     * Если метод выполнился неудачно:
     *  Status: ERROR
     *  Message: “Create photographer error”
     */
    public Response createPhotographer(String name, long expirence, String site) throws Exception;

    /**
     * Метод позволяет изменить entity bean Photographer.
     * @param id
     * @param name
     * @param expirence
     * @param site
     * @return Response
     * @throws Exception
     *
     * Если метод выполнился успешно:
     *  Body: Photographer
     *  Status: SUCCESS
     *  Message: “Update photographer success”
     *
     * Если метод выполнился неудачно:
     *  Status: ERROR
     *  Message: “Update photographer error”
     */
    public Response updatePhotographer(long id, String name, long expirence, String site) throws Exception;

    /**
     * Метод позволяет удалить entity bean Photographer
     * @param id
     * @return Response
     * @throws Exception
     *
     * Если метод выполнился успешно:
     *  Status: SUCCESS
     *  Message: “Delete photographer success”
     *
     * Если метод выполнился неудачно:
     *  Status: ERROR
     *  Message: “Delete photographer error”
     */
    public Response deletePhotographer(long id) throws Exception;

    /**
     * Метод позволяет получить entity bean Photographer.
     * @param id
     * @return Response
     * @throws Exception
     *
     * Если метод выполнился успешно:
     *  Body: Photographer
     *  Status: SUCCESS
     *  Message: “Get photographer success”
     *
     * Если метод выполнился неудачно:
     *  Status: ERROR
     *  Message: “Get photographer error”
     */
    public Response getPhotographer(long id) throws Exception;

    /**
     * Метод позволяет удалить entity bean FootballEvent, ITEvent, MusicEvent в зависимости от входного параметра eventType.
     * Если входной параметр eventType == “FOOTBALL” удалится FootballEvent,
     * Если входной параметр eventType == “IT” удалится ITEvent,
     * Если входной параметр eventType == “MUSIC” удалится MusicEvent
     * @param id
     * @param eventType
     * @return Response
     * @throws Exception
     *
     * Если метод выполнился успешно:
     *  Status: SUCCESS
     *  Message: “Delete event success”
     *
     * Если метод выполнился неудачно:
     *  Status: ERROR
     *  Message: “Delete event error”
     */
    public Response deleteEvent(long id, String eventType) throws Exception;
}