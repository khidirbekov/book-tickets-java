package ru.sfedu.bookticket.models;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import ru.sfedu.bookticket.utils.CsvConverterCity;

import java.util.Objects;

/**
 * Class Place
 */
@Root
public class Place {

  //
  // Fields
  //
  @CsvBindByName
  @Attribute
  private long id;
  @CsvBindByName
  @Element(type = String.class)
  private String address;
  @CsvCustomBindByName(column = "city", converter = CsvConverterCity.class)
  @Element (type = City.class)
  private City city;
  
  //
  // Constructors
  //
  public Place () { };
  
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
   * Set the value of address
   * @param newVar the new value of address
   */
  public void setAddress (String newVar) {
    address = newVar;
  }

  /**
   * Get the value of address
   * @return the value of address
   */
  public String getAddress () {
    return address;
  }

  /**
   * Set the value of city
   * @param newVar the new value of city
   */
  public void setCity (City newVar) {
    city = newVar;
  }

  /**
   * Get the value of city
   * @return the value of city
   */
  public City getCity () {
    return city;
  }

  //
  // Other methods
  //


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Place place = (Place) o;
    return id == place.id &&
            Objects.equals(address, place.address) &&
            Objects.equals(city, place.city);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, address, city);
  }

  @Override
  public String toString() {
    return "Place{" +
            "id=" + id +
            ", address='" + address + '\'' +
            ", city=" + city +
            '}';
  }
}
