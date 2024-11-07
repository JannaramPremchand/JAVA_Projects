package com.example.demo.entities;

public class Song {
    private final int id;
    private final String name;
    private final String artist;
    private final String album;
    private final String genre;

    public Song(int id, String name, String artist, String album, String genre) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Song [id=" + id + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Song song = (Song) o;
        return id == song.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

}