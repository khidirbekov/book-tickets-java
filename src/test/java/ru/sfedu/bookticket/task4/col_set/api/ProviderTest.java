package ru.sfedu.bookticket.task4.col_set.api;

import org.junit.jupiter.api.Test;
import ru.sfedu.bookticket.enums.ResponseStatus;
import ru.sfedu.bookticket.task4.col_set.api.Provider;
import ru.sfedu.bookticket.task4.col_set.model.Event;
import ru.sfedu.bookticket.utils.Response;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ProviderTest {
    Provider provider = new Provider();
    @Test
    void createEventSuccess() {
        Set<String> photos = new HashSet<String>();
        photos.add("mountain");
        photos.add("car");
        Response response = provider.createEvent( "test", "100", photos);
        assertEquals(response.getStatus(), ResponseStatus.SUCCESS);
    }

    @Test
    void updateEventSuccess() {
        Set<String> photos = new HashSet<String>();
        photos.add("mountain");
        photos.add("car");
        Event event = (Event) provider.createEvent("test", "100", photos).getBody();
        Response response = provider.updateEvent(event.getId(),"update", "200", photos);
        assertEquals(response.getStatus(),ResponseStatus.SUCCESS);
    }

    @Test
    void updateEventFail() {
        Set<String> photos = new HashSet<String>();
        photos.add("car");
        Response response = provider.updateEvent(0,"update", "200", photos);
        assertEquals(response.getStatus(),ResponseStatus.ERROR);
    }

    @Test
    void getEventSuccess() {
        Set<String> photos = new HashSet<String>();
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
        Set<String> photos = new HashSet<String>();
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