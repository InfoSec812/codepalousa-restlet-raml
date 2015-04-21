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
import javax.inject.Inject;
import org.restlet.data.Status;
import org.restlet.ext.guice.SelfInjectingServerResource;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ResourceException;

/**
 *
 * @author <a href="https://github.com/InfoSec812">Deven Phillips</a>
 */
public class ToDoResource extends SelfInjectingServerResource implements Serializable {
  Long id;
  
  @Inject
  private ToDoDAO dao;

  @Override
  protected void doInit() throws ResourceException {
    try {
      id = Long.parseLong(getAttribute("id"));
    } catch (NumberFormatException nfe) {
      throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, String.format("ToDo ID of '%d' is not a valid long integer.", id), nfe);
    }
  }

  @Get("json|xml")
  public ToDo getToDo() {
    ToDo todo = dao.getToDo(id);
    if (todo==null) {
      throw new ResourceException(Status.CLIENT_ERROR_NOT_FOUND);
    }
    return todo;
  }
  
  @Put("json|xml")
  public ToDo updateToDo(ToDo item) {
    ToDo todo = dao.updateToDo(item);
    if (todo==null) {
       throw new ResourceException(Status.SERVER_ERROR_INTERNAL, "Failed to update ToDo entity.");
    }
    return todo;
  }
  
  @Delete
  public void deleteToDo() {
    if (!dao.deleteToDo(id)) {
      throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
    }
  }
}
