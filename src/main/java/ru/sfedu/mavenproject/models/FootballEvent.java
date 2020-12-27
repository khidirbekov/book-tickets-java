package ru.sfedu.mavenproject.models;

import com.opencsv.bean.CsvBindByName;
import org.simpleframework.xml.Element;

import java.util.Objects;

/**
 * Class FootballEvent
 */
public class FootballEvent extends Event {

  //
  // Fields
  //
  @CsvBindByName
  @Element
  private String team1;
  @CsvBindByName
  @Element
  private String team2;
  
  //
  // Constructors
  //
  public FootballEvent () { };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of team1
   * @param newVar the new value of team1
   */
  public void setTeam1 (String newVar) {
    team1 = newVar;
  }

  /**
   * Get the value of team1
   * @return the value of team1
   */
  public String getTeam1 () {
    return team1;
  }

  /**
   * Set the value of team2
   * @param newVar the new value of team2
   */
  public void setTeam2 (String newVar) {
    team2 = newVar;
  }

  /**
   * Get the value of team2
   * @return the value of team2
   */
  public String getTeam2 () {
    return team2;
  }

  //
  // Other methods
  //


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    FootballEvent that = (FootballEvent) o;
    return Objects.equals(team1, that.team1) &&
            Objects.equals(team2, that.team2);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), team1, team2);
  }

  @Override
  public String toString() {
    return "FootballEvent{" + super.toString() +
            "team1='" + team1 + '\'' +
            ", team2='" + team2 + '\'' +
            '}';
  }
}
