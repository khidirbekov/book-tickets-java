package ru.sfedu.bookticket.task3.TablePerClass.model;

import javax.persistence.Entity;

@Entity(name = "MusicEventTPC")
public class MusicEvent extends Event {
    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    private String artist;

}
