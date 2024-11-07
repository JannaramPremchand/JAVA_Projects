package com.example.demo.repositories;

import com.example.demo.entities.Song;

import java.util.ArrayList;
import java.util.List;

public class SongRepository implements ISongRepository {
    private final List<Song> songs = new ArrayList<>();

    @Override
    public void addSong(Song song) {
        songs.add(song);
    }

    @Override
    public List<Song> getAllSongs() {
        return new ArrayList<>(songs);
    }

    @Override
    public Song getSongById(int id) {
        for (Song song : songs) {
            if (song.getId() == id) {
                return song; 
            }
        }
        return null; 
    }
}
