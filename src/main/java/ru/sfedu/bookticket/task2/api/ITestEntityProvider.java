package ru.sfedu.bookticket.task2.api;

import ru.sfedu.bookticket.task2.model.TestEntity;

import java.util.Optional;

public interface ITestEntityProvider {
    public Optional<TestEntity> getByID(Class<TestEntity> entity, long id);
    public Long save(TestEntity entity);
    public boolean update(TestEntity entity);
    public boolean delete(Long id);
}
