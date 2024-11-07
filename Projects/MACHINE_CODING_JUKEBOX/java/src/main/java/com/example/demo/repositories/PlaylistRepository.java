package com.example.demo.repositories;

import com.example.demo.entities.Playlist;

import java.util.ArrayList;
import java.util.List;

public class PlaylistRepository implements IPlaylistRepository {
    private final List<Playlist> playlists = new ArrayList<>();

    @Override
    public void addPlaylist(Playlist playlist) {
        playlists.add(playlist);
    }
    @Override
    public Playlist getPlaylistByName(String name) {
        for (Playlist playlist : playlists) {
            if (playlist.getName().equals(name)) {
                return playlist;
            }
        }
        return null;
    }

    @Override
    public List<Playlist> getAllPlaylists() {
        return new ArrayList<>(playlists);
    }
    @Override
    public boolean deleteByName(String name) {
        return playlists.removeIf(playlist -> playlist.getName().equals(name));
    }
    @Override
    public void updatePlaylist(Playlist updatedPlaylist) {
        for (int i = 0; i < playlists.size(); i++) {
            if (playlists.get(i).getId() == updatedPlaylist.getId()) {
                playlists.set(i, updatedPlaylist);
                return; 
            }
        }
    }
}
