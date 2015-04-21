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
package com.codepalousa.restlet.raml;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnitUtil;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author <a href="https://github.com/InfoSec812">Deven Phillips</a>
 */
@ApplicationScoped
@Slf4j
public class CDIProducer {

  EntityManagerFactory emf;

  public CDIProducer() {
    createPU();
  }

  @Produces
  @Default
  public EntityManager create() {
    createPU();
    return this.emf.createEntityManager();
  }

  @Produces
  @Default
  public PersistenceUnitUtil getUtil() {
    createPU();
    return emf.getPersistenceUnitUtil();
  }

  private void createPU() {
    if (emf == null) {
      LOG.debug("Creating persistence unit 'todo'");
      emf = Persistence.createEntityManagerFactory("todo");
    }
  }

  public void dispose(@Disposes @Default EntityManager em) {
    if (em.isOpen()) {
      LOG.debug("Disposing of EntityManager");
      em.close();
    }
  }
}
