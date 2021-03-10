package ru.sfedu.bookticket.task4.col_map.api;

import org.junit.jupiter.api.Test;
import ru.sfedu.bookticket.enums.ResponseStatus;
import ru.sfedu.bookticket.task4.col_map.model.Event;
import ru.sfedu.bookticket.utils.Response;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ProviderTest {
    Provider provider = new Provider();
    @Test
    void createEventSuccess() {
        Map<String, String> photos = new HashMap<String, String>();
        photos.put("photos_key", "mountain");
        photos.put("photos_key", "car");
        Response response = provider.createEvent( "test", "100", photos);
        assertEquals(response.getStatus(), ResponseStatus.SUCCESS);
    }

    @Test
    void updateEventSuccess() {
        Map<String, String> photos = new HashMap<String, String>();
        photos.put("photos_key", "city");
        Event event = (Event) provider.createEvent("test", "100", photos).getBody();
        Response response = provider.updateEvent(event.getId(),"update", "200", photos);
        assertEquals(response.getStatus(),ResponseStatus.SUCCESS);
    }

    @Test
    void updateEventFail() {
        Map<String, String> photos = new HashMap<String, String>();
        photos.put("photos_key", "city");
        Response response = provider.updateEvent(0,"update", "200", photos);
        assertEquals(response.getStatus(),ResponseStatus.ERROR);
    }

    @Test
     void getEventSuccess() {
        Map<String, String> photos = new HashMap<String, String>();
        photos.put("photos_key", "city");
        Event event = (Event) provider.createEvent("test", "100", photos).getBody();
        Response response = provider.getEvent(event.getId());
        assertNotNull(response.getBody());
    }

    @Test
    void getEventFail() {
        Response response = provider.getEvent(0);
        assertNull(response.getBody());
    }

    @Test
    void deleteEventSuccess() {
        Map<String, String> photos = new HashMap<String, String>();
        photos.put("photos_key", "city");
        Event event = (Event) provider.createEvent("test", "100", photos).getBody();
        Response response = provider.deleteEvent(event.getId());
        assertEquals(response.getStatus(), ResponseStatus.SUCCESS);
    }

    @Test
    void deleteEventFail() {
        Response response = provider.deleteEvent(0);
        assertEquals(response.getStatus(), ResponseStatus.ERROR);
    }

}