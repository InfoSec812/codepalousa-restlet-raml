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
package com.codepalousa.restlet.raml.types;

import com.codepalousa.restlet.raml.DateTimeDeserializeConverter;
import com.codepalousa.restlet.raml.DateTimeSerializeConverter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;

/**
 *
 * @author <a href="https://github.com/InfoSec812">Deven Phillips</a>
 */
@Data
@Entity
@Table(name = "todos")
@XmlRootElement(name = "todo")
@JsonTypeName("todo")
public class ToDo implements Serializable {
  
  /**
   * Unique ID of the ToDo item
   */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @XmlAttribute(name="id", required = false)
  @JsonProperty
  @JsonPropertyDescription("Unique ID")
  private Long id;

  /**
   * Short title of the ToDo item
   */
  @XmlElement(name = "title", required = true, nillable = false)
  @JsonProperty
  @JsonPropertyDescription("Short title of the ToDo item")
  private String title;
  
  /**
   * Long description of the ToDo item
   */
  @XmlElement(name = "description", nillable = true)
  @JsonProperty
  @JsonPropertyDescription("Long description of the ToDo item")
  private String description;
  
  /**
   * The date/time at which the ToDo item was created.
   */
  @XmlElement(name = "created", required = true, nillable = false)
  @JsonProperty
  @JsonPropertyDescription("The date/time when the ToDo item was created")
  @JsonSerialize(contentConverter = DateTimeSerializeConverter.class)
  @JsonDeserialize(contentConverter = DateTimeDeserializeConverter.class)
  @Temporal(TemporalType.TIMESTAMP)
  private Date created = new Date();
  
  /**
   * The date/time at which the ToDo item is due for completion.
   */
  @XmlElement(name = "due", nillable = true)
  @JsonProperty
  @JsonPropertyDescription("The date/time when the ToDo item is due to be completed")
  @JsonSerialize(contentConverter = DateTimeSerializeConverter.class)
  @JsonDeserialize(contentConverter = DateTimeDeserializeConverter.class)
  @Temporal(TemporalType.TIMESTAMP)
  private Date dueDate;
}
