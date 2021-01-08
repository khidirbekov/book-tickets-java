package ru.sfedu.bookticket.models;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import ru.sfedu.bookticket.utils.CsvConverterEvent;

import java.util.Objects;

/**
 * Class Ticket
 */
@Root
public class Ticket {

  //
  // Fields
  //
  @Attribute
  @CsvBindByName
  private long id;
  @Element
  @CsvBindByName
  private String buyerName;
  @Element
  @CsvBindByName
  private String payTime;
  @Element
  @CsvCustomBindByName(column = "event", converter = CsvConverterEvent.class)
  private Event event;
  
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
   * Set the value of buyerName
   * @param newVar the new value of buyerName
   */
  public void setBuyerName (String newVar) {
    buyerName = newVar;
  }

  /**
   * Get the value of buyerName
   * @return the value of buyerName
   */
  public String getBuyerName () {
    return buyerName;
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

  /**
   * Set the value of event
   * @param newVar the new value of event
   */
  public void setEvent (Event newVar) {
    event = newVar;
  }

  /**
   * Get the value of event
   * @return the value of event
   */
  public Event getEvent () {
    return event;
  }

  //
  // Other methods
  //


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Ticket ticket = (Ticket) o;
    return id == ticket.id &&
            Objects.equals(buyerName, ticket.buyerName) &&
            Objects.equals(payTime, ticket.payTime) &&
            Objects.equals(event, ticket.event);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, buyerName, payTime, event);
  }

  @Override
  public String toString() {
    return "Ticket{" +
            "id=" + id +
            ", buyerName='" + buyerName + '\'' +
            ", payTime='" + payTime + '\'' +
            ", event=" + event +
            '}';
  }
}
