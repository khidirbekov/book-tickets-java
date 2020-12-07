package ru.sfedu.mavenproject.beans;

/**
 * Class ITTicket
 */
public class ITTicket extends Ticket {

  //
  // Fields
  //

  private ITEvent itEvent;
  
  //
  // Constructors
  //
  public ITTicket () { };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of itEvent
   * @param newVar the new value of itEvent
   */
  public void setItEvent (ITEvent newVar) {
    itEvent = newVar;
  }

  /**
   * Get the value of itEvent
   * @return the value of itEvent
   */
  public ITEvent getItEvent () {
    return itEvent;
  }

  //
  // Other methods
  //

}
