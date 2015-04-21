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

import javax.persistence.EntityManager;
import static org.mockito.Mockito.*;
import static org.mockito.Matchers.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnitUtil;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author dphillips
 */
public class CDIProducerTest {
  
  @InjectMocks
  private CDIProducer producer;
  
  @Mock
  private EntityManagerFactory emf;
  
  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    producer.emf = emf;
  }

  /**
   * Test of create method, of class CDIProducer.
   */
  @Test
  public void testCreate() {
    when(emf.createEntityManager()).thenReturn(mock(EntityManager.class));
    EntityManager em = producer.create();
    assertNotNull("EntityManager MUST NOT be null", em);
    verify(emf).createEntityManager();
    reset(emf);
  }

  /**
   * Test of getUtil method, of class CDIProducer.
   */
  @Test
  public void testGetUtil() {
    when(emf.getPersistenceUnitUtil()).thenReturn(mock(PersistenceUnitUtil.class));
    PersistenceUnitUtil util = producer.getUtil();
    assertNotNull("PersistenceUnitUtil MUST NOT be null", util);
    verify(emf).getPersistenceUnitUtil();
    reset(emf);
  }

  /**
   * Test of dispose method, of class CDIProducer.
   */
  @Test
  public void testDispose() {
    EntityManager em = mock(EntityManager.class);
    doReturn(false).when(em).isOpen();
    producer.dispose(em);
    verify(em).isOpen();
    verify(em, never()).close();
    reset(em);
    
    doReturn(true).when(em).isOpen();
    doNothing().when(em).close();
    producer.dispose(em);
    verify(em).isOpen();
    verify(em).close();
    reset(em);
  }
  
}
