package com.example.demo.entities;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private static int idCounter = 1;
    private final int id;
    private final String name;
    private final List<Song> songs;

    public Playlist(int id, String name, List<Song> songs) {
        this.id = id;
        this.name = name;
        this.songs = new ArrayList<>(songs);
    }

    public Playlist(String name) {
        this.id = generateId();
        this.name = name;
        this.songs = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Song> getSongs() {
        return new ArrayList<>(songs);
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public boolean removeSong(Song song) {
        return songs.remove(song);
    }

    @Override
    public String toString() {
        return "Playlist [id=" + id + "]";
    }

    private int generateId() {
        return idCounter++;
    }
}