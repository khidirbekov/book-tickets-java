package ru.sfedu.mavenproject.models;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import ru.sfedu.mavenproject.utils.CsvConverterCity;
import ru.sfedu.mavenproject.utils.CsvConverterIds;
import ru.sfedu.mavenproject.utils.CsvConverterPlace;

import java.util.List;
import java.util.Objects;

/**
 * Class Event
 */
@Root
public class Event {

  //
  // Fields
  //
  @CsvBindByName
  @Attribute
  private long id;
  @CsvBindByName
  @Element
  private String name;
  @CsvBindByName
  @Element
  private String date;
  @CsvBindByName
  @Element
  private String time;
  @CsvBindByName
  @Element
  private String price;
  @CsvCustomBindByName(column = "photos", converter = CsvConverterIds.class)
  @ElementList(type = String.class)
  private List<String> photos;
  @CsvCustomBindByName(column = "place", converter = CsvConverterPlace.class)
  @Element
  private Place place;
  
  //
  // Constructors
  //
  public Event () { };
  
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
   * Set the value of date
   * @param newVar the new value of date
   */
  public void setDate (String newVar) {
    date = newVar;
  }

  /**
   * Get the value of date
   * @return the value of date
   */
  public String getDate () {
    return date;
  }

  /**
   * Set the value of time
   * @param newVar the new value of time
   */
  public void setTime (String newVar) {
    time = newVar;
  }

  /**
   * Get the value of time
   * @return the value of time
   */
  public String getTime () {
    return time;
  }

  /**
   * Set the value of price
   * @param newVar the new value of price
   */
  public void setPrice (String newVar) {
    price = newVar;
  }

  /**
   * Get the value of price
   * @return the value of price
   */
  public String getPrice () {
    return price;
  }

  /**
   * Set the value of photos
   * @param newVar the new value of photos
   */
  public void setPhotos (List<String> newVar) {
    photos = newVar;
  }

  /**
   * Get the value of photos
   * @return the value of photos
   */
  public List<String> getPhotos () {
    return photos;
  }

  /**
   * Set the value of place
   * @param newVar the new value of place
   */
  public void setPlace (Place newVar) {
    place = newVar;
  }

  /**
   * Get the value of place
   * @return the value of place
   */
  public Place getPlace () {
    return place;
  }

  //
  // Other methods
  //


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Event event = (Event) o;
    return id == event.id &&
            Objects.equals(name, event.name) &&
            Objects.equals(date, event.date) &&
            Objects.equals(time, event.time) &&
            Objects.equals(price, event.price) &&
            Objects.equals(photos, event.photos) &&
            Objects.equals(place, event.place);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, date, time, price, photos, place);
  }

  @Override
  public String toString() {
    return "Event{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", date='" + date + '\'' +
            ", time='" + time + '\'' +
            ", price='" + price + '\'' +
            ", photos=" + photos +
            ", place=" + place +
            '}';
  }
}
