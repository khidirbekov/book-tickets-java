package ru.sfedu.mavenproject.beans;

/**
 * Class ITEvent
 */
public class ITEvent extends Event {

  //
  // Fields
  //

  private String stack;
  
  //
  // Constructors
  //
  public ITEvent () { };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of stack
   * @param newVar the new value of stack
   */
  public void setStack (String newVar) {
    stack = newVar;
  }

  /**
   * Get the value of stack
   * @return the value of stack
   */
  public String getStack () {
    return stack;
  }

  //
  // Other methods
  //

}
