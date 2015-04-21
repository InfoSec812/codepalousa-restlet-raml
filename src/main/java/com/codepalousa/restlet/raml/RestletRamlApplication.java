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
import javax.enterprise.context.ApplicationScoped;
import org.apache.deltaspike.core.api.provider.BeanProvider;
import org.restlet.Application;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.Restlet;
import org.restlet.ext.raml.RamlApplication;
import org.restlet.ext.raml.RamlSpecificationRestlet;
import org.restlet.resource.Finder;
import org.restlet.resource.ServerResource;
import org.restlet.routing.Router;

/**
 *
 * @author <a href="https://github.com/InfoSec812">Deven Phillips</a>
 */
@ApplicationScoped
public class RestletRamlApplication extends RamlApplication {

  @Override
  public synchronized Restlet createInboundRoot() {
    Router router = newRouter();
    
    router.attach("/todo", ToDosResource.class);
    router.attach("/todo/{id}", ToDoResource.class);
    
    RamlSpecificationRestlet raml = getRamlSpecificationRestlet(getContext());
    raml.setApiVersion("1");
    raml.setAuthor("Deven Phillips");
    raml.setDescription("ReST API For a ToDo list");
    raml.setName("todo");
    raml.setOwner("Deven Phillips <dphillips@zanclus.com>");
    raml.setBasePath("http://localhost:8180/rest/v1");
    raml.attach(router);
    
    return router;
  }


    /**
     * Wraps a {@link Finder} returned by {@link #createFinder(Class)} to do
     * field injection using {@link BeanProvider#injectFields(java.lang.Object)}.
     * 
     * @param finder
     *            The finder.
     * @return A wrapped {@link Finder}.
     */
    public static Finder wrapFinderWithInjection(final Finder finder) {
        return new Finder(finder.getContext(), finder.getTargetClass()) {
            @Override
            public ServerResource find(Request request, Response response) {
                ServerResource res = finder.find(request, response);
                BeanProvider.injectFields(res);
                return res;
            }
        };
    }

    @Override
    public Finder createFinder(Class<? extends ServerResource> targetClass) {
        Finder finder = super.createFinder(targetClass);
        return wrapFinderWithInjection(finder);
    }

    /**
     * Returns a new instance of {@link Router} linked to this application.
     * 
     * @return A new instance of {@link Router}.
     */
    public Router newRouter() {
        final Application app = this;
        return new Router(getContext()) {
            @Override
            public Application getApplication() {
                return app;
            }
        };
    }
  
}
