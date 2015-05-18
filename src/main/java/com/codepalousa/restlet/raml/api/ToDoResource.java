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

import static javax.servlet.http.HttpServletResponse.*;
import static javax.ws.rs.core.Response.Status.*;

import com.codepalousa.restlet.raml.data.ToDoDAO;
import com.codepalousa.restlet.raml.types.ToDo;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author <a href="https://github.com/InfoSec812">Deven Phillips</a>
 */
@Path("/todo")
@Api(value = "/todo", basePath = "http://localhost:8180/rest/v1")
public class ToDoResource {
  Long id;
  
  @Inject
  private ToDoDAO dao;

  @GET
  @Path("/{id}")
  @Produces(value = {"application/json", "application/xml"})
  @ApiOperation(value = "Get ToDo entity specified by 'id'", produces = "application/json, application/xml", 
                response = ToDo.class)
  @ApiResponses({
    @ApiResponse(code = SC_NOT_FOUND, message = "NOT FOUND"),
    @ApiResponse(code = SC_INTERNAL_SERVER_ERROR, message = "SERVER ERROR"),
    @ApiResponse(code = SC_BAD_REQUEST, message = "Invalid ID value")
  })
  public ToDo getToDo(@ApiParam(value = "The unique ID of the ToDo entity", name = "id") @PathParam("id") Long id) {
    ToDo todo = dao.getToDo(id);
    if (todo==null) {
      throw new WebApplicationException(String.format("No item found with id '%d'", id), BAD_REQUEST);
    }
    return todo;
  }
  
  @PUT
  @Path("/{id}")
  @Produces(value = {"application/json", "application/xml"})
  @ApiOperation(value = "Update/Modify ToDo entity specified by 'id'", produces = "application/json, application/xml", 
                response = ToDo.class, consumes = "application/xml, application/json")
  @ApiResponses({
    @ApiResponse(code = SC_INTERNAL_SERVER_ERROR, message = "SERVER ERROR"),
    @ApiResponse(code = SC_BAD_REQUEST, message = "Invalid ID value")
  })
  public ToDo updateToDo(@ApiParam( value = "The unique ID of the ToDo entity", name = "id") @PathParam("id") Long id, 
                                    ToDo item) {
    ToDo todo = dao.updateToDo(item);
    if (todo==null) {
      throw new WebApplicationException(String.format("Unable to update item with id '%d'", id), BAD_REQUEST);
    }
    return todo;
  }
  
  @DELETE
  @Path("/{id}")
  @ApiOperation(value = "Delete ToDo entity specified by 'id'")
  @ApiResponses({
    @ApiResponse(code = SC_ACCEPTED, message = "OK", response = Void.class),
    @ApiResponse(code = SC_INTERNAL_SERVER_ERROR, message = "SERVER ERROR"),
    @ApiResponse(code = SC_BAD_REQUEST, message = "Invalid ID value")
  })
  public void deleteToDo(@ApiParam(value = "The unique ID of the ToDo entity", name = "id") @PathParam("id") Long id) {
    if (!dao.deleteToDo(id)) {
      throw new WebApplicationException(String.format("Unable to delete item with id '%d'", id), BAD_REQUEST);
    }
  }

  @GET
  @Produces(value = {"application/json", "application/xml"})
  @ApiOperation(value = "Get all ToDo entities", produces = "application/json, application/xml", response = ToDo.class, 
                responseContainer = "List")
  @ApiResponses({
    @ApiResponse(code = SC_INTERNAL_SERVER_ERROR, message = "SERVER ERROR"),
    @ApiResponse(code = SC_BAD_REQUEST, message = "Invalid ID value")
  })
  public List<ToDo> getAllToDo() {
    List<ToDo> todos = dao.getAllToDos();
    return todos;
  }
  
  @POST
  @Produces(value = {"application/json", "application/xml"})
  @ApiOperation(value = "Create a new ToDo entity", produces = "application/json, application/xml", 
                response = ToDo.class, consumes = "application/xml, application/json")
  @ApiResponses({
    @ApiResponse(code = SC_INTERNAL_SERVER_ERROR, message = "SERVER ERROR"),
    @ApiResponse(code = SC_BAD_REQUEST, message = "Invalid ID value")
  })
  public ToDo addToDo(ToDo item) {
    ToDo todo = dao.addToDo(item);
    if (todo==null || todo.id()==null) {
      throw new WebApplicationException(String.format("Unable to add item", id), BAD_REQUEST);
    }
    return todo;
  }
}
