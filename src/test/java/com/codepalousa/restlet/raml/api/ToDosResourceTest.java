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
import static org.junit.Assert.*;

import com.codepalousa.restlet.raml.data.ToDoDAO;
import com.codepalousa.restlet.raml.types.ToDo;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.restlet.resource.ResourceException;

/**
 *
 * @author <a href="https://github.com/InfoSec812">Deven Phillips</a>
 */
public class ToDosResourceTest {

  @InjectMocks
  private ToDosResource res;
  
  @Mock
  private ToDoDAO dao;
  
  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  /**
   * Test of getAllToDo method, of class ToDosResource.
   */
  @Test
  public void testGetAllToDo() {
    when(dao.getAllToDos()).thenReturn(new ArrayList<>());
    List<ToDo> todos = res.getAllToDo();
    assertTrue("List MUST be empty.", todos.isEmpty());
    verify(dao).getAllToDos();
    reset(dao);
    
    ArrayList<ToDo> result = new ArrayList<>();
    result.add(mock(ToDo.class));
    result.add(mock(ToDo.class));
    when(dao.getAllToDos()).thenReturn(result);
    todos = res.getAllToDo();
    assertTrue("List MUST be of size 2", todos.size()==2);
    verify(dao).getAllToDos();
    reset(dao);
  }

  /**
   * Test of addToDo method, of class ToDosResource.
   */
  @Test
  public void testAddToDo() {
    when(dao.addToDo(any(ToDo.class))).thenReturn(null);
    try {
      res.addToDo(mock(ToDo.class));
      fail("When dao returns null, resource MUST throw ResourceException");
    } catch (Exception e) {
      assertTrue("Exception type MUST be ResourceException", ResourceException.class.isInstance(e));
    }
    verify(dao).addToDo(any(ToDo.class));
    reset(dao);
    
    ToDo todoMock = mock(ToDo.class);
    when(dao.addToDo(any(ToDo.class))).thenReturn(todoMock);
    when(todoMock.id()).thenReturn(null);
    try {
      res.addToDo(mock(ToDo.class));
      fail("When dao returns entity with null ID, resource MUST throw ResourceException");
    } catch (Exception e) {
      assertTrue("Exception type MUST be ResourceException", ResourceException.class.isInstance(e));
    }
    verify(dao).addToDo(any(ToDo.class));
    verify(todoMock).id();
    reset(dao);
    reset(todoMock);
    
    when(dao.addToDo(any(ToDo.class))).thenReturn(todoMock);
    when(todoMock.id()).thenReturn(1L);
    ToDo result = res.addToDo(mock(ToDo.class));
    assertEquals("Mock object and result MUST be equal.", result, todoMock);
    verify(dao).addToDo(any(ToDo.class));
    verify(todoMock).id();
    reset(dao);
    reset(todoMock);
  }
  
}
