package ru.sfedu.mavenproject.beans;

/**
 * Class MusicTicket
 */
public class MusicTicket extends Ticket {

  //
  // Fields
  //

  private MusicEvent musicEvent;
  
  //
  // Constructors
  //
  public MusicTicket () { };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of musicEvent
   * @param newVar the new value of musicEvent
   */
  public void setMusicEvent (MusicEvent newVar) {
    musicEvent = newVar;
  }

  /**
   * Get the value of musicEvent
   * @return the value of musicEvent
   */
  public MusicEvent getMusicEvent () {
    return musicEvent;
  }

  //
  // Other methods
  //

}
