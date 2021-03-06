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

import com.codepalousa.restlet.raml.data.ToDoDAOTest;
import com.codepalousa.restlet.raml.types.ToDoTest;
import org.apache.deltaspike.testcontrol.api.junit.CdiTestSuiteRunner;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author <a href="https://github.com/InfoSec812">Deven Phillips</a>
 */
@RunWith(CdiTestSuiteRunner.class)
@Suite.SuiteClasses({
  ToDoDAOTest.class, 
  ToDoTest.class, 
  CDIProducerTest.class
})
public class CdiSuiteLevelTest {

}
