package ru.sfedu.bookticket.task3.MappedSuperclass.model;

import javax.persistence.Entity;

@Entity(name = "MusicEventMapped")
public class MusicEvent extends Event {
    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    private String artist;

}
