/**
 * Copyright (C) 2015 Deven Phillips (dphillips@zanclus.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.codepalousa.restlet.raml.data;

import com.codepalousa.restlet.raml.types.ToDo;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

/**
 * A class which contains all of the CRUD methods for database operations.
 * @author <a href="https://github.com/InfoSec812">Deven Phillips</a>
 */
@Slf4j
@Dependent
public class ToDoDAO implements Serializable {

    @Inject
    private EntityManager em;

    public ToDoDAO() {
    }

    /**
     * Get all ToDo entities from the database
     * @return 
     */
    @Transactional
    public List<ToDo> getAllToDos() {
        try {
            return em.createQuery("FROM ToDo", ToDo.class).getResultList();
        } catch (Exception e) {
            LOG.error("Error getting ToDo list", e);
            return null;
        }
    }

    /**
     * Get a single ToDo entity whose primary key matches the provided ID
     * @param id The unique primary key value
     * @return An instance of {@link ToDo} which corresponds to the ID or NULL if it is not found.
     */
    @Transactional
    public ToDo getToDo(Long id) {
        try {
            return em.find(ToDo.class, id);
        } catch (Exception e) {
            LOG.error("Error getting ToDo", e);
            return null;
        }
    }

    /**
     * Add a new ToDo entity to the persistence context.
     * @param item The ToDo item to be stored.
     * @return The persisted ToDo entity with the ID field populated if needed or NULL if the insert failed.
     */
    @Transactional
    public ToDo addToDo(ToDo item) {
        try {
            ToDo retVal = em.merge(item);
            return retVal;
        } catch (Exception e) {
            LOG.error("Error adding ToDo", e);
            return null;
        }
    }

    /**
     * Modify an existing {@link ToDo} entity
     * @param item The {@link ToDo} entity with the new values to be updated.
     * @return The persisted {@link ToDo} instance
     */
    @Transactional
    public ToDo updateToDo(ToDo item) {
        try {
            ToDo retVal = em.merge(item);
            return retVal;
        } catch (Exception e) {
            LOG.error("Error updating ToDo", e);
            return null;
        }
    }

    /**
     * Delete the {@link ToDo} entity which corresponds to the given ID
     * @param id The unique ID of the entity to be deleted
     * @return True if the entity was deleted, otherwise returns false.
     */
    @Transactional
    public boolean deleteToDo(Long id) {
        try {
            em.remove(em.find(ToDo.class, id));
        } catch (Exception e) {
            LOG.error("Error removing ToDo item", e);
            return false;
        }
        return true;
    }

}