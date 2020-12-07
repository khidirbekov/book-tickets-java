package ru.sfedu.mavenproject.beans;

/**
 * Class Event
 */
abstract public class Event {

  //
  // Fields
  //

  private long id;
  private String name;
  private City city;
  private String date;
  private String time;
  private String price;
  
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

  //
  // Other methods
  //

}
