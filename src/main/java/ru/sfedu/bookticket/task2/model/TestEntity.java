package ru.sfedu.bookticket.task2.model;

import javax.persistence.*;
import javax.persistence.Embedded;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "Test")
public class TestEntity implements Serializable {
    public TestEntity(){}
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Description")
    private String description;

    @Column(name = "Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Embedded
    private Embedded embedded;

    @Column
    private boolean checking;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = new java.util.Date((date.getTime()/1000)*1000);
    }

    public boolean getChecking() {
        return checking;
    }

    public void setChecking(boolean check) {
        this.checking = check;
    }

    public Embedded getEmbedded() {
        return embedded;
    }

    public void setEmbedded(Embedded embedded) {
        this.embedded = embedded;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestEntity that = (TestEntity) o;
        return checking == that.checking &&
                Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(date, that.date) &&
                Objects.equals(embedded, that.embedded);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, date, embedded, checking);
    }

    @Override
    public String toString() {
        return "TestEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", embedded=" + embedded +
                ", checking=" + checking +
                '}';
    }
}