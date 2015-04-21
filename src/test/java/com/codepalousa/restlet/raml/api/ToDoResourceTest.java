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
package com.codepalousa.restlet.raml.api;

import static org.mockito.Mockito.*;
import static org.mockito.Matchers.*;

import com.codepalousa.restlet.raml.data.ToDoDAO;
import com.codepalousa.restlet.raml.types.ToDo;
import java.util.concurrent.ConcurrentMap;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.restlet.Request;
import org.restlet.resource.ResourceException;

/**
 *
 * @author <a href="https://github.com/InfoSec812">Deven Phillips</a>
 */
public class ToDoResourceTest {
  
  @Mock
  private ToDoDAO dao;
  
  @Mock
  private Request request;
  
  @InjectMocks
  private ToDoResource res;
  
  private ConcurrentMap<String, Object> attr = mock(ConcurrentMap.class);
  
  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    res.setRequest(request);
  }

  /**
   * Test of doInit method, of class ToDoResource.
   */
  @Test
  public void testDoInit() {
    when(request.getAttributes()).thenReturn(attr);
    when(attr.get(anyString())).thenReturn("A");
    try {
      res.doInit();
      fail("When the requested ToDo ID is not a number, getToDo MUST throw an exception");
    } catch (Exception re) {
      assertTrue("When 'id' field is not a number, getToDo MUST throw ResourceException", ResourceException.class.isInstance(re));
    }
    reset(request);
    reset(attr);
    
    when(request.getAttributes()).thenReturn(attr);
    when(attr.get(anyString())).thenReturn("1");
    when(dao.getToDo(anyLong())).thenReturn(mock(ToDo.class));
    res.doInit();
    verify(request).getAttributes();
    verify(attr).get(anyString());
    reset(request);
    reset(attr);
    reset(dao);
  }

  /**
   * Test of getToDo method, of class ToDoResource.
   */
  @Test
  public void testGetToDo() {
    res.id = 1L;
    when(dao.getToDo(anyLong())).thenReturn(null);
    try {
      ToDo result = res.getToDo();
      fail("getToDo MUST throw ResourceException when dao returns null");
    } catch (Exception re) {
      assertTrue("getToDo MUST throw ResourceException when dao returns null", ResourceException.class.isInstance(re));
    }
    verify(dao).getToDo(anyLong());
    reset(dao);
    
    when(dao.getToDo(anyLong())).thenReturn(mock(ToDo.class));
    ToDo result = res.getToDo();
    assertNotNull("getToDo MUST NOT return a null value when DAO returns a valid object.", result);
    verify(dao).getToDo(anyLong());
    reset(dao);
  }

  /**
   * Test of updateToDo method, of class ToDoResource.
   */
  @Test
  public void testUpdateToDo() {
    res.id = 1L;
    when(dao.updateToDo(any(ToDo.class))).thenReturn(null);
    try {
      ToDo result = res.updateToDo(mock(ToDo.class));
      fail("getToDo MUST throw ResourceException when dao returns null");
    } catch (Exception re) {
      assertTrue("getToDo MUST throw ResourceException when dao returns null", ResourceException.class.isInstance(re));
    }
    verify(dao).updateToDo(any(ToDo.class));
    reset(dao);
    
    when(dao.updateToDo(any(ToDo.class))).thenReturn(mock(ToDo.class));
    ToDo result = res.updateToDo(mock(ToDo.class));
    assertNotNull("getToDo MUST NOT return a null value when DAO returns a valid object.", result);
    verify(dao).updateToDo(any(ToDo.class));
    reset(dao);
  }

  /**
   * Test of deleteToDo method, of class ToDoResource.
   */
  @Test
  public void testDeleteToDo() {
    res.id = 1L;
    doReturn(false).when(dao).deleteToDo(anyLong());
    try {
      res.deleteToDo();
      fail("getToDo MUST throw ResourceException when dao returns null");
    } catch (Exception re) {
      assertTrue("getToDo MUST throw ResourceException when dao returns null", ResourceException.class.isInstance(re));
    }
    verify(dao).deleteToDo(anyLong());
    reset(dao);
    
    doReturn(true).when(dao).deleteToDo(anyLong());
    res.deleteToDo();
    verify(dao).deleteToDo(anyLong());
    reset(dao);
  }
  
}
