package ru.sfedu.bookticket.task2.model;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Embeddable
public class Embedded implements Serializable {
    public Embedded(){};

    @Column(name="title")
    private String name;
    @Column(name="subtitle")
    private String subtitle;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Embedded that = (Embedded) o;
        return Objects.equals(name, that.name) && Objects.equals(subtitle, that.subtitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, subtitle);
    }

    @Override
    public String toString() {
        return "Embedded{" +
                "name='" + name + '\'' +
                ", subtitle='" + subtitle + '\'' +
                '}';
    }
}
