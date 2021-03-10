package ru.sfedu.bookticket.task3.TablePerClass.api;

import org.junit.jupiter.api.Test;
import ru.sfedu.bookticket.task3.TablePerClass.model.FootballEvent;
import ru.sfedu.bookticket.task3.TablePerClass.model.MusicEvent;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ProviderTest {
    Provider provider = new Provider();

    @Test
    void getByID() {
        Optional<FootballEvent> footballEvent = provider.getByID(FootballEvent.class, 0);
        assertNull(footballEvent);
        Optional<MusicEvent> musicEvent = provider.getByID(MusicEvent.class, 0);
        assertNull(musicEvent);
    }

    @Test
    void saveSuccess() {
        FootballEvent event = new FootballEvent();
        event.setName("test");
        Long id = provider.save(event);
        Optional<FootballEvent> result = provider.getByID(FootballEvent.class, id);
        assertNotNull(result.get());
    }

    @Test
    void saveFail() {
        FootballEvent event = null;
        Long id = provider.save(event);
        assertNull(id);
    }

    @Test
    void updateSuccess() {
        FootballEvent event = new FootballEvent();
        event.setName("update test");
        provider.save(event);
        event.setName("updated test");
        boolean status = provider.update(event);
        assertEquals(status, true);
    }

    @Test
    void updateFail() {
        FootballEvent event = new FootballEvent();
        event.setName("unknon");
        boolean status = provider.update(event);
        assertEquals(status, false);
    }

    @Test
    void deleteSuccess() {
        FootballEvent event = new FootballEvent();
        event.setName("delete entity");
        Long id = provider.save(event);
        boolean status = provider.delete(FootballEvent.class, id);
        assertEquals(status, true);
    }

    @Test
    void deleteFail() {
        boolean status = provider.delete(FootballEvent.class, (long) 0);
        assertEquals(status, false);
    }
}