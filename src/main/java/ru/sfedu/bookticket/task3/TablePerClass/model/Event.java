package ru.sfedu.bookticket.task3.TablePerClass.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "EventTPC")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Event implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private String name;
    private String address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
