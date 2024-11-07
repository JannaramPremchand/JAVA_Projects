package com.example.demo.services;

import com.example.demo.entities.Song;
import com.example.demo.repositories.ISongRepository;

import java.util.ArrayList;
import java.util.List;

public class SongService {
    private final ISongRepository songRepository;

    public SongService(ISongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public void addSong(Song song) {
        songRepository.addSong(song);
    }

    public List<Song> listSongs() {
        return songRepository.getAllSongs();
    }

    public Song getSongById(int id) {
        return songRepository.getAllSongs().stream()
                .filter(song -> song.getId() == id)
                .findFirst()
                .orElse(null);
    }
    public List<Song> getAllSongs() {
        return songRepository.getAllSongs();
    }
    
}
