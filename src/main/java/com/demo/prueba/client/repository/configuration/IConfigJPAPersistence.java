package com.demo.prueba.client.repository.configuration;

import java.io.Serializable;
import java.util.Collection;

public interface IConfigJPAPersistence<T> {

    /**
     * Salva una entidad.
     *
     * @param obj entity.
     */
    void save(T obj);

    /**
     * Salvar una colleccion de entidad.
     *
     * @param objs Las entidades
     */
    void saveAll(Collection<T> objs);

    /**
     * Actualiza una entidad.
     *
     * @param obj La entidad
     */
    void update(T obj);

    /**
     * Buscar por id una entidad.
     *
     * @param id El id
     * @return La entidad
     */
    T findById(Serializable id);

    /**
     * Eliminar una entidad por id.
     *
     * @param obj El id de la entidad
     * @return
     */

    void delete(T obj);
}
