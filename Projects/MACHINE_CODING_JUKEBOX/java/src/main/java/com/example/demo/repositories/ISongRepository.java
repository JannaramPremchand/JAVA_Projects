package com.example.demo.repositories;

import com.example.demo.entities.Song;

import java.util.List;

public interface ISongRepository {
    void addSong(Song song);
    List<Song> getAllSongs();
    Song getSongById(int id);
}
