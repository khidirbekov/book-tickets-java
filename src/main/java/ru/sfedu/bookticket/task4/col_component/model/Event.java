package ru.sfedu.bookticket.task4.col_component.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Class Event
 */
@Entity(name = "event_comp")
public class Event {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private long id;
  private String name;
  private String price;
  @ElementCollection
  @CollectionTable(name = "photo_comp")
  @AttributeOverride(
          name = "photo_name",
          column = @Column(name = "photo_name", nullable = false)
  )
  private Set<Photo> photos;
  //
  // Constructors
  //
  public Event () { };

  //
  // Accessor methods
  //

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public  Set<Photo> getPhotos() {
    return photos;
  }

  public void setPhotos( Set<Photo> photos) {
    this.photos = photos;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Event event = (Event) o;
    return id == event.id &&
            Objects.equals(name, event.name) &&
            Objects.equals(price, event.price) &&
            Objects.equals(photos, event.photos);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, price, photos);
  }

  @Override
  public String toString() {
    return "Event{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", price='" + price + '\'' +
            ", photos=" + photos +
            '}';
  }
}
