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

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author <a href="">Deven Phillips</a>
 */
public class DateTimeDeserializeConverter implements Converter<String, Date> {

  private final DateTimeFormatter formatter;
  
  public DateTimeDeserializeConverter() {
    formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
  }
  
  @Override
  public Date convert(String value) {
    return Date.from(formatter.parse(value, OffsetDateTime::from).toInstant());
  }

  @Override
  public JavaType getInputType(TypeFactory typeFactory) {
    return typeFactory.constructType(String.class);
  }

  @Override
  public JavaType getOutputType(TypeFactory typeFactory) {
    return typeFactory.constructType(Date.class);
  }

}
