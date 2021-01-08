package ru.sfedu.bookticket.models;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import ru.sfedu.bookticket.utils.CsvConverterPhotographer;

import java.util.Objects;

/**
 * Class Photo
 */
@Root
public class Photo {

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
  private String path;
  @Element
  @CsvCustomBindByName(column = "photographer", converter = CsvConverterPhotographer.class)
  private Photographer photographer;

  //
  // Constructors
  //
  public Photo () { };
  
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
   * Set the value of path
   * @param newVar the new value of path
   */
  public void setPath (String newVar) {
    path = newVar;
  }

  /**
   * Get the value of path
   * @return the value of path
   */
  public String getPath () {
    return path;
  }

  /**
   * Set the value of photographer
   * @param newVar the new value of photographer
   */
  public void setPhotographer (Photographer newVar) {
    photographer = newVar;
  }

  /**
   * Get the value of photographer
   * @return the value of photographer
   */
  public Photographer getPhotographer () {
    return photographer;
  }

  //
  // Other methods
  //


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Photo photo = (Photo) o;
    return id == photo.id &&
            Objects.equals(name, photo.name) &&
            Objects.equals(path, photo.path) &&
            Objects.equals(photographer, photo.photographer);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, path, photographer);
  }

  @Override
  public String toString() {
    return "Photo{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", path='" + path + '\'' +
            ", photographer=" + photographer +
            '}';
  }
}
