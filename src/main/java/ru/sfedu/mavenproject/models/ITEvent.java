package ru.sfedu.mavenproject.models;

import com.opencsv.bean.CsvBindByName;
import org.simpleframework.xml.Element;

import java.util.Objects;

/**
 * Class ITEvent
 */
public class ITEvent extends Event {

  //
  // Fields
  //
  @CsvBindByName
  @Element
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


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    ITEvent itEvent = (ITEvent) o;
    return Objects.equals(stack, itEvent.stack);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), stack);
  }

  @Override
  public String toString() {
    return "ITEvent{" + super.toString() +
            "stack='" + stack + '\'' +
            '}';
  }
}
