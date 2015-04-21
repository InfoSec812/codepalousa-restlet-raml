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

import com.codepalousa.restlet.raml.data.ToDoDAO;
import com.codepalousa.restlet.raml.types.ToDo;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import org.restlet.data.Status;
import org.restlet.ext.guice.SelfInjectingServerResource;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ResourceException;

/**
 *
 * @author <a href="https://github.com/InfoSec812">Deven Phillips</a>
 */
public class ToDosResource extends SelfInjectingServerResource implements Serializable {
  
  @Inject
  private ToDoDAO dao;

  @Get("json|xml")
  public List<ToDo> getAllToDo() {
    List<ToDo> todos = dao.getAllToDos();
    return todos;
  }
  
  @Put("json|xml")
  public ToDo addToDo(ToDo item) throws ResourceException {
    ToDo todo = dao.addToDo(item);
    if (todo==null || todo.id()==null) {
      throw new ResourceException(Status.SERVER_ERROR_INTERNAL, "Failed to persist the ToDo entity");
    }
    return todo;
  }
}
