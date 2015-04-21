package com.codepalousa.restlet.raml;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author <a href="">Deven Phillips</a>
 */
public class DateTimeSerializeConverter implements Converter<Date, String> {

  private final DateTimeFormatter formatter;
  
  public DateTimeSerializeConverter() {
    formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
  }
  
  @Override
  public String convert(Date value) {
    return LocalDateTime.ofInstant(value.toInstant(), ZoneId.systemDefault()).format(formatter);
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
