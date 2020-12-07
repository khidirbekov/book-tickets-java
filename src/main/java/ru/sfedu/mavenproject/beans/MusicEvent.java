package ru.sfedu.mavenproject.beans;

/**
 * Class MusicEvent
 */
public class MusicEvent extends Event {

  //
  // Fields
  //

  private String artist;
  
  //
  // Constructors
  //
  public MusicEvent () { };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of artist
   * @param newVar the new value of artist
   */
  public void setArtist (String newVar) {
    artist = newVar;
  }

  /**
   * Get the value of artist
   * @return the value of artist
   */
  public String getArtist () {
    return artist;
  }

  //
  // Other methods
  //

}
