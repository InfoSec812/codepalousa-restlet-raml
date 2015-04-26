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
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codepalousa.restlet.raml;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.util.Calendar;
import static java.util.Calendar.DATE;
import static java.util.Calendar.HOUR_OF_DAY;
import static java.util.Calendar.MINUTE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.SECOND;
import static java.util.Calendar.YEAR;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author <a href="https://github.com/InfoSec812">Deven Phillips</a>
 */
public class DateTimeSerializeConverterTest {
  
  private DateTimeSerializeConverter conv;
  
  @Before
  public void setup() {
    conv = new DateTimeSerializeConverter();
  }

  /**
   * Test of convert method, of class DateTimeSerializeConverter.
   */
  @Test
  public void testConvert() {
    Calendar cal = GregorianCalendar.getInstance();
    cal.setTimeZone(TimeZone.getTimeZone("GMT"));
//    cal.setTime(Date.from(Instant.now(Clock.systemUTC())));
    String result = conv.convert(cal.getTime());
    assertNotNull("Result MUST NOT be null", result);
    String expected = String.format("%4d-%02d-%02dT%02d:%02d:%02d+0000", 
                                    cal.get(YEAR), cal.get(MONTH)+1, cal.get(DATE), 
                                    cal.get(HOUR_OF_DAY), cal.get(MINUTE), cal.get(SECOND));
    assertEquals(String.format("Result '%2$s' MUST match '%1$s'", expected, result), expected, result);
  }

  /**
   * Test of getInputType method, of class DateTimeSerializeConverter.
   */
  @Test
  public void testGetInputType() {
    JavaType type = conv.getOutputType(TypeFactory.defaultInstance());
    assertNotNull("Input type MUST NOT be null", type);
    assertTrue(String.format("Type of type MUST be Date, but is '%s'", type.getRawClass().getSimpleName()), String.class.equals(type.getRawClass()));
  }

  /**
   * Test of getOutputType method, of class DateTimeSerializeConverter.
   */
  @Test
  public void testGetOutputType() {
    JavaType type = conv.getInputType(TypeFactory.defaultInstance());
    assertNotNull("Input type MUST NOT be null", type);
    assertTrue(String.format("Type of type MUST be String, but is '%s'", type.getRawClass().getSimpleName()), Date.class.equals(type.getRawClass()));
  }
  
}