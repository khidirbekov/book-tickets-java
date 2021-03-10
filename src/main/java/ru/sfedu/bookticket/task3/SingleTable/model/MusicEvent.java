package ru.sfedu.bookticket.task3.SingleTable.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "MusicEventST")
@DiscriminatorValue("ME")
public class MusicEvent extends Event {
    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    private String artist;

}
