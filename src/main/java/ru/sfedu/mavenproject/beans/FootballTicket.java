package ru.sfedu.mavenproject.beans;

/**
 * Class FootballTicket
 */
public class FootballTicket extends Ticket {

  //
  // Fields
  //

  private FootballEvent footballEvent;
  
  //
  // Constructors
  //
  public FootballTicket () { };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of footballEvent
   * @param newVar the new value of footballEvent
   */
  public void setFootballEvent (FootballEvent newVar) {
    footballEvent = newVar;
  }

  /**
   * Get the value of footballEvent
   * @return the value of footballEvent
   */
  public FootballEvent getFootballEvent () {
    return footballEvent;
  }

  //
  // Other methods
  //

}
