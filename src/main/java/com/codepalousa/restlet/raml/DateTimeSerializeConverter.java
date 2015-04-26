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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 *
 * @author <a href="https://github.com/InfoSec812">Deven Phillips</a>
 */
public class DateTimeSerializeConverter implements Converter<Date, String> {

  private final SimpleDateFormat formatter;
  
  public DateTimeSerializeConverter() {
    formatter = (SimpleDateFormat)SimpleDateFormat.getDateTimeInstance();
    formatter.applyPattern("yyyy-MM-dd'T'HH:mm:ssZ");
    formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
  }
  
  @Override
  public String convert(Date value) {
    return formatter.format(value);
  }

  @Override
  public JavaType getInputType(TypeFactory typeFactory) {
    return typeFactory.constructType(Date.class);
  }

  @Override
  public JavaType getOutputType(TypeFactory typeFactory) {
    return typeFactory.constructType(String.class);
  }

}
