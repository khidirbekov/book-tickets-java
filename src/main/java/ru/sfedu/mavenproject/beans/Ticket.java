package ru.sfedu.mavenproject.beans;

/**
 * Class Ticket
 */
abstract public class Ticket {

  //
  // Fields
  //

  private long id;
  private String payTime;
  
  //
  // Constructors
  //
  public Ticket () { };
  
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
   * Set the value of payTime
   * @param newVar the new value of payTime
   */
  public void setPayTime (String newVar) {
    payTime = newVar;
  }

  /**
   * Get the value of payTime
   * @return the value of payTime
   */
  public String getPayTime () {
    return payTime;
  }

  //
  // Other methods
  //

}
