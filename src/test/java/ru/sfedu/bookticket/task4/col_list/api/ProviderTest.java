package ru.sfedu.bookticket.task4.col_list.api;

import org.junit.jupiter.api.Test;
import ru.sfedu.bookticket.enums.ResponseStatus;
import ru.sfedu.bookticket.task4.col_list.model.Event;
import ru.sfedu.bookticket.utils.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ProviderTest {
    Provider provider = new Provider();
    @Test
    void createEventSuccess() {
        List<String> photos = new ArrayList<String>();
        photos.add("mountain");
        photos.add("car");
        Response response = provider.createEvent( "test", "100", photos);
        assertEquals(response.getStatus(), ResponseStatus.SUCCESS);
    }

    @Test
    void updateEventSuccess() {
        List<String> photos = new ArrayList<String>();
        photos.add("mountain");
        photos.add("car");
        Event event = (Event) provider.createEvent("test", "100", photos).getBody();
        Response response = provider.updateEvent(event.getId(),"update", "200", photos);
        assertEquals(response.getStatus(),ResponseStatus.SUCCESS);
    }

    @Test
    void updateEventFail() {
        List<String> photos = new ArrayList<String>();
        photos.add("car");
        Response response = provider.updateEvent(0,"update", "200", photos);
        assertEquals(response.getStatus(),ResponseStatus.ERROR);
    }

    @Test
    void getEventSuccess() {
        List<String> photos = new ArrayList<String>();
        photos.add("car");
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
        List<String> photos = new ArrayList<String>();
        photos.add("car");
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