package ru.sfedu.mavenproject.models;


import com.opencsv.bean.CsvBindByName;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.util.Objects;

/**
 * Class Photographer
 */
@Root
public class Photographer {

  //
  // Fields
  //
  @Attribute
  @CsvBindByName
  private long id;
  @Element
  @CsvBindByName
  private String name;
  @Element
  @CsvBindByName
  private long expirence;
  @Element
  @CsvBindByName
  private String site;
  
  //
  // Constructors
  //
  public Photographer () { };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of id
   * @param newVar the new value of id
   */
  public void setId (long newVar) {
    id = newVar;
  }

  /**
   * Get the value of id
   * @return the value of id
   */
  public long getId () {
    return id;
  }

  /**
   * Set the value of name
   * @param newVar the new value of name
   */
  public void setName (String newVar) {
    name = newVar;
  }

  /**
   * Get the value of name
   * @return the value of name
   */
  public String getName () {
    return name;
  }

  /**
   * Set the value of expirence
   * @param newVar the new value of expirence
   */
  public void setExpirence (long newVar) {
    expirence = newVar;
  }

  /**
   * Get the value of expirence
   * @return the value of expirence
   */
  public long getExpirence () {
    return expirence;
  }

  /**
   * Set the value of site
   * @param newVar the new value of site
   */
  public void setSite (String newVar) {
    site = newVar;
  }

  /**
   * Get the value of site
   * @return the value of site
   */
  public String getSite () {
    return site;
  }

  //
  // Other methods
  //


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Photographer that = (Photographer) o;
    return id == that.id &&
            expirence == that.expirence &&
            Objects.equals(name, that.name) &&
            Objects.equals(site, that.site);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, expirence, site);
  }

  @Override
  public String toString() {
    return "Photographer{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", expirence=" + expirence +
            ", site='" + site + '\'' +
            '}';
  }
}
