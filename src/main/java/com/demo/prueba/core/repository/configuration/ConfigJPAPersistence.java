package com.demo.prueba.core.repository.configuration;


import com.demo.prueba.client.repository.configuration.IConfigJPAPersistence;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.io.Serializable;
import java.util.Collection;

public abstract class ConfigJPAPersistence<T> extends QuerydslRepositorySupport implements
        IConfigJPAPersistence<T> {
    protected static final boolean STATUS_ACTIVE = Boolean.TRUE;
    protected static final boolean STATUS_INACTIVE = Boolean.FALSE;

    private final Class<T> domainClass;

    /**
     * Constructor.
     *
     * @param domainClass - Clase Base.
     */
    public ConfigJPAPersistence(Class<T> domainClass){
        super(domainClass);
        this.domainClass = domainClass;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(T obj) {
        getEntityManager().persist(obj);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveAll(Collection<T> objs) {
        objs.forEach(obj -> getEntityManager().persist(obj));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(T obj) {
        getEntityManager().merge(obj);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T findById(Serializable id) {
        return getEntityManager().find(domainClass, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(T obj) {
        getEntityManager().remove(obj);
    }
}
