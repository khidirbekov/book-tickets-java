package ru.sfedu.bookticket.task3.JoinedTable.api;

import org.junit.jupiter.api.Test;
import ru.sfedu.bookticket.task2.model.TestEntity;
import ru.sfedu.bookticket.task3.JoinedTable.model.Event;
import ru.sfedu.bookticket.task3.JoinedTable.model.FootballEvent;
import ru.sfedu.bookticket.task3.JoinedTable.model.MusicEvent;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ProviderTest {
    Provider provider = new Provider();

    @Test
    void getByID() {
        Optional<Event> event = provider.getByID(Event.class, 0);
        assertNull(event);
        Optional<FootballEvent> footballEvent = provider.getByID(FootballEvent.class, 0);
        assertNull(footballEvent);
        Optional<MusicEvent> musicEvent = provider.getByID(MusicEvent.class, 0);
        assertNull(musicEvent);
    }

    @Test
    void saveSuccess() {
        Event event = new Event();
        event.setName("test");
        Long id = provider.save(event);
        Optional<Event> result = provider.getByID(Event.class, id);
        assertNotNull(result.get());
    }

    @Test
    void saveFail() {
        Event event = null;
        Long id = provider.save(event);
        assertNull(id);
    }

    @Test
    void updateSuccess() {
        Event event = new Event();
        event.setName("update test");
        provider.save(event);
        event.setName("updated test");
        boolean status = provider.update(event);
        assertEquals(status, true);
    }

    @Test
    void updateFail() {
        Event event = new Event();
        event.setName("unknon");
        boolean status = provider.update(event);
        assertEquals(status, false);
    }

    @Test
    void deleteSuccess() {
        Event event = new Event();
        event.setName("delete entity");
        Long id = provider.save(event);
        boolean status = provider.delete(Event.class, id);
        assertEquals(status, true);
    }

    @Test
    void deleteFail() {
        boolean status = provider.delete(Event.class, (long) 0);
        assertEquals(status, false);
    }
}