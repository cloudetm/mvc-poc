package net.zburak.poc.service;

/**
 * Created by buraq
 */
public interface BaseService<T> {

    public T save(T entity);

    public T update(T entity);

    public void delete(T entity);
}
