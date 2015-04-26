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
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import org.restlet.data.Status;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

/**
 * Expose ID specific {@link ToDo} resources as ReST endpoints
 * @author <a href="https://github.com/InfoSec812">Deven Phillips</a>
 */
@Dependent
public class ToDoResource extends ServerResource implements Serializable {
  /**
   * The ID specified in the request URI's {id} parameter
   */
  Long id;
  
  @Inject
  private ToDoDAO dao;

  /**
   * When a new request is received, parse the path parameter for the ID and store it in a local property
   * @throws ResourceException 
   */
  @Override
  protected void doInit() throws ResourceException {
    try {
      id = Long.parseLong(getAttribute("id"));
    } catch (NumberFormatException nfe) {
      throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, String.format("ToDo ID of '%d' is not a valid long integer.", id), nfe);
    }
  }

  /**
   * Return the {@link ToDo} entity identified by the ID in the request URL
   * @return An instance of {@link ToDo} containing the requested entity
   */
  @Get("json|xml")
  public ToDo getToDo() {
    ToDo todo = dao.getToDo(id);
    if (todo==null) {
      throw new ResourceException(Status.CLIENT_ERROR_NOT_FOUND);
    }
    this.setStatus(Status.SUCCESS_OK);
    return todo;
  }
  

  /**
   * Update the {@link ToDo} entity identified by the ID in the request URL
   * @param item The {@link ToDo} object containing the updated information
   * @return An instance of {@link ToDo} containing the updated entity
   */
  @Put("json|xml")
  public ToDo updateToDo(ToDo item) {
    ToDo todo = dao.updateToDo(item);
    if (todo==null) {
       throw new ResourceException(Status.SERVER_ERROR_INTERNAL, "Failed to update ToDo entity.");
    }
    this.setStatus(Status.SUCCESS_ACCEPTED);
    return todo;
  }
  

  /**
   * Delete the {@link ToDo} entity identified by the ID in the request URL
   */
  @Delete
  public void deleteToDo() {
    if (!dao.deleteToDo(id)) {
      throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
    }
    this.setStatus(Status.SUCCESS_NO_CONTENT);
  }
}
