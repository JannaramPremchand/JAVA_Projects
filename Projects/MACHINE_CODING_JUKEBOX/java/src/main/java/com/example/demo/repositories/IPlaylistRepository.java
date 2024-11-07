package com.example.demo.repositories;
import java.util.*;
import com.example.demo.entities.Playlist;

public interface IPlaylistRepository {
    void addPlaylist(Playlist playlist);
    Playlist getPlaylistByName(String name);
    List<Playlist> getAllPlaylists();
    boolean deleteByName(String name);
    void updatePlaylist(Playlist playlist);
}
