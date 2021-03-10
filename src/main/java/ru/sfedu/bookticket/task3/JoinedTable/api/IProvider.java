package ru.sfedu.bookticket.task3.JoinedTable.api;

import java.util.Optional;

public interface IProvider {
    public <T> Optional<T> getByID(Class<T> entity, long id);
    public <T> Long save(T entity);
    public <T> boolean update(T entity);
    public <T> boolean delete(Class<T> entity, Long id);
}
