package ru.sfedu.mavenproject.beans;

/**
 * Class FootballEvent
 */
public class FootballEvent extends Event {

  //
  // Fields
  //

  private String team1;
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

}
