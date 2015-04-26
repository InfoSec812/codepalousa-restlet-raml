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
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import org.restlet.data.Status;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

/**
 * Expose top-level operations for {@link ToDo} entities
 * @author <a href="https://github.com/InfoSec812">Deven Phillips</a>
 */
@Dependent
public class ToDosResource extends ServerResource implements Serializable {
  
  @Inject
  private ToDoDAO dao;

  /**
   * Return a list of all {@link ToDo} entities
   * @return 
   */
  @Get("json|xml|csv")
  public List<ToDo> getAllToDo() {
    List<ToDo> todos = dao.getAllToDos();
    this.setStatus(Status.SUCCESS_OK);
    return todos;
  }
  
  /**
   * Add a new {@link ToDo} entity
   * @param item The {@link ToDo} item to be added.
   * @return The {@link ToDo} instance with the ID field populated if needed.
   * @throws ResourceException If there is a server-side error
   */
  @Post("json|xml|csv")
  public ToDo addToDo(ToDo item) throws ResourceException {
    ToDo todo = dao.addToDo(item);
    if (todo==null || todo.id()==null) {
      throw new ResourceException(Status.SERVER_ERROR_INTERNAL, "Failed to persist the ToDo entity");
    }
    this.setStatus(Status.SUCCESS_ACCEPTED);
    return todo;
  }
}
