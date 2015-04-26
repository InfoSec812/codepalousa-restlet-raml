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

import com.codepalousa.restlet.raml.api.ToDoResource;
import com.codepalousa.restlet.raml.api.ToDosResource;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.restlet.Restlet;
import org.restlet.routing.Router;

/**
 *
 * @author dphillips
 */
public class RestletRamlApplicationTest {
  
  private RestletRamlApplication app;
  
  @Before
  public void setup() {
    app = new RestletRamlApplication();
  }

  /**
   * Test of createInboundRoot method, of class RestletRamlApplication.
   */
  @Test
  public void testCreateInboundRoot() {
    Restlet router = app.createInboundRoot();
    assertTrue("Returned object MUST be instance of Router", Router.class.isInstance(router));
    int endpointCount = ((Router)router).getRoutes().size();
    assertTrue(String.format("Router MUST have 3 mapped endpoints but actually has %d endpoints", endpointCount), endpointCount==3);
  }
  
}
