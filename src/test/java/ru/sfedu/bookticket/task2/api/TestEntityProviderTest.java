package ru.sfedu.bookticket.task2.api;

import org.junit.jupiter.api.Test;
import ru.sfedu.bookticket.task2.model.TestEntity;

import javax.swing.text.html.parser.Entity;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TestEntityProviderTest {
    TestEntityProvider provider = new TestEntityProvider();

    @Test
    void getByID() {
        Optional<TestEntity> testEntity = provider.getByID(TestEntity.class, 0);
        assertNull(testEntity);
    }

    @Test
    void saveSuccess() {
        TestEntity entity = new TestEntity();
        entity.setName("test");
        Long id = provider.save(entity);
        Optional<TestEntity> result = provider.getByID(TestEntity.class, id);
        assertEquals(entity, result.get());
    }

    @Test
    void saveFail() {
        TestEntity entity = null;
        Long id = provider.save(entity);
        assertNull(id);
    }

    @Test
    void updateSuccess() {
        TestEntity entity = new TestEntity();
        entity.setName("update test");
        provider.save(entity);
        entity.setName("updated test");
        boolean status = provider.update(entity);
        assertEquals(status, true);
    }

    @Test
    void updateFail() {
        TestEntity entity = new TestEntity();
        entity.setName("unknon");
        boolean status = provider.update(entity);
        assertEquals(status, false);
    }

    @Test
    void deleteSuccess() {
        TestEntity entity = new TestEntity();
        entity.setName("delete entity");
        Long id = provider.save(entity);
        boolean status = provider.delete(id);
        assertEquals(status, true);
    }

    @Test
    void deleteFail() {
        TestEntity entity = new TestEntity();
        boolean status = provider.delete((long) 0);
        assertEquals(status, false);
    }
}