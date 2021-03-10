package ru.sfedu.bookticket.task4.col_component.api;

import org.junit.jupiter.api.Test;
import ru.sfedu.bookticket.enums.ResponseStatus;
import ru.sfedu.bookticket.task4.col_component.api.Provider;
import ru.sfedu.bookticket.task4.col_component.model.Event;
import ru.sfedu.bookticket.task4.col_component.model.Photo;
import ru.sfedu.bookticket.utils.Response;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ProviderTest {
    Provider provider = new Provider();
    @Test
    void createEventSuccess() {
        Set<Photo> photos = new HashSet<Photo>();
        Photo photo = new Photo();
        photo.setName("mountain");
        photos.add(photo);
        Response response = provider.createEvent( "test", "100", photos);
        assertEquals(response.getStatus(), ResponseStatus.SUCCESS);
    }

    @Test
    void updateEventSuccess() {
        Set<Photo> photos = new HashSet<Photo>();
        Photo photo = new Photo();
        photo.setName("mountain");
        photos.add(photo);
        Event event = (Event) provider.createEvent("test", "100", photos).getBody();
        Response response = provider.updateEvent(event.getId(),"update", "200", photos);
        assertEquals(response.getStatus(),ResponseStatus.SUCCESS);
    }

    @Test
    void updateEventFail() {
        Set<Photo> photos = new HashSet<Photo>();
        Photo photo = new Photo();
        photo.setName("mountain");
        photos.add(photo);
        Response response = provider.updateEvent(0,"update", "200", photos);
        assertEquals(response.getStatus(),ResponseStatus.ERROR);
    }

    @Test
    void getEventSuccess() {
        Set<Photo> photos = new HashSet<Photo>();
        Photo photo = new Photo();
        photo.setName("mountain");
        photos.add(photo);
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
        Set<Photo> photos = new HashSet<Photo>();
        Photo photo = new Photo();
        photo.setName("");
        photos.add(photo);
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