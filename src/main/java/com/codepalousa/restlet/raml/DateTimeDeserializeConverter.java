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
