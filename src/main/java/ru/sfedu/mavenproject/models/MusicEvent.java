package ru.sfedu.mavenproject.models;

import com.opencsv.bean.CsvBindByName;
import org.simpleframework.xml.Element;

import java.util.Objects;

/**
 * Class MusicEvent
 */
public class MusicEvent extends Event {

  //
  // Fields
  //
  @CsvBindByName
  @Element
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


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    MusicEvent that = (MusicEvent) o;
    return Objects.equals(artist, that.artist);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), artist);
  }

  @Override
  public String toString() {
    return "MusicEvent{" + super.toString() +
            "artist='" + artist + '\'' +
            '}';
  }
}
