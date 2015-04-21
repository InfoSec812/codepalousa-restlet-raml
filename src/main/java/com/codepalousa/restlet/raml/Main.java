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

import org.apache.deltaspike.cdise.api.CdiContainer;
import org.apache.deltaspike.cdise.api.CdiContainerLoader;
import org.apache.deltaspike.cdise.api.ContextControl;
import org.restlet.Application;
import org.restlet.Component;
import org.restlet.data.Protocol;

/**
 *
 * @author <a href="https://github.com/InfoSec812">Deven Phillips</a>
 */
public class Main {

  public static void main(String[] args) throws Exception {
    CdiContainer cdiContainer = CdiContainerLoader.getCdiContainer();
    cdiContainer.boot();

    // Starting the all contexts
    ContextControl contextControl = cdiContainer.getContextControl();
    contextControl.startContexts();
    // You can use CDI here

    Component component = new Component();

    component.getServers().add(Protocol.HTTP, 8180);
    
    Application app = new RestletRamlApplication();

    component.getDefaultHost().attach("/rest/v1", app);

    component.start();
  }
}