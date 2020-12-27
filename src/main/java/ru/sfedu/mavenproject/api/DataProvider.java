package ru.sfedu.mavenproject.api;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import ru.sfedu.mavenproject.utils.Response;
import java.io.IOException;
import java.util.List;

public interface DataProvider {
    public void initDataSource(String path) throws IOException;

    <T> void setList(List<T> list, Class cl) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException;

    public String getPath(Class cn) throws IOException;
    public <T> List<T> getList(Class c) throws IOException;

    public Response getEvent(long eventId, String eventType) throws Exception;
    public Response getFootballEvent(long eventId) throws Exception;
    public Response getITEvent(long eventId) throws Exception;
    public Response getMusicEvent(long eventId) throws Exception;
    public Response bookTicket(long eventId, String eventType, String buyerName) throws Exception;
    public Response getTicket(long ticketId) throws Exception;
    public Response getCity(long cityId) throws Exception;
    public Response createCity(String name) throws Exception;
    public Response updateCity(long cityId, String name) throws Exception;
    public Response deleteCity(long cityId) throws Exception;

    public Response updateEvent(String eventType, long id, String name, long placeId, String date, String time, String price, String photos, String eventSpecialArgs) throws Exception;

    public Response updateFootballEvent(long id, String name, long placeId, String date, String time, String price, String photos, String team1, String team2) throws Exception;
    public Response updateITEvent(long id, String name, long placeId, String date, String time, String price, String photos, String stack) throws Exception;
    public Response updateMusicEvent(long id, String name, long placeId, String date, String time, String price, String photos, String artist) throws Exception;

    public Response createEvent(String eventType, String name, long placeId, String date, String time, String price, String photos, String eventSpecialArgs) throws Exception;

    public Response createFootballEvent(String name, long placeId, String date, String time, String price, String photos, String team1, String team2) throws Exception;
    public Response createITEvent(String name, long placeId, String date, String time, String price, String photos, String stack) throws Exception;
    public Response createMusicEvent(String name, long placeId, String date, String time, String price, String photos, String artist) throws Exception;
    public Response createPlace(String address, long cityId) throws Exception;
    public Response updatePlace(long placeId, String address, long cityId) throws Exception;
    public Response deletePlace(long placeId) throws Exception;
    public Response getPlace(long placeId) throws Exception;
    public Response createPhoto(String name, String path, long photographerId) throws Exception;

    public Response deletePhoto(long photoId) throws Exception;
    public Response getPhoto(long photoId) throws Exception;
    public Response createPhotographer(String name, long expirence, String site) throws Exception;
    public Response updatePhotographer(long id, String name, long expirence, String site) throws Exception;
    public Response deletePhotographer(long id) throws Exception;
    public Response getPhotographer(long id) throws Exception;
    public Response deleteEvent(long id, String eventType) throws Exception;
}