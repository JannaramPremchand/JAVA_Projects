package com.example.demo.services;

import com.example.demo.entities.Playlist;
import com.example.demo.entities.Song;
import com.example.demo.repositories.ISongRepository;
import com.example.demo.repositories.IPlaylistRepository;

import java.util.ArrayList;
import java.util.List;

public class PlaylistService {
    private final List<Playlist> playlists = new ArrayList<>();
    private final IPlaylistRepository playlistRepository;
    private final ISongRepository songRepository;
    private int playlistIdCounter = 1;
    private Playlist currentPlaylist;
    private int currentIndex = -1;
    private boolean isPlaying = false;

    public PlaylistService(IPlaylistRepository playlistRepository, ISongRepository songRepository) {
        this.playlistRepository = playlistRepository;
        this.songRepository = songRepository;
        this.playlists.addAll(playlistRepository.getAllPlaylists());
    }

    public Playlist createPlaylist(String name, List<Song> songs) {
        Playlist newPlaylist = new Playlist(playlistIdCounter++, name, songs);
        playlists.add(newPlaylist);
        playlistRepository.addPlaylist(newPlaylist);
        return newPlaylist;
    }

    public Playlist getPlaylistByName(String name) {
        for (Playlist playlist : playlists) {
            if (playlist.getName().equals(name)) {
                return playlist;
            }
        }
        return null;
    }

    public void loadPlaylist(String name) {
        Playlist playlist = getPlaylistByName(name);
        if (playlist != null) {
            currentPlaylist = playlist;
            System.out.println("Playlist " + name + " is loaded!");
            currentIndex = -1;
            isPlaying = false;
        } else {
            System.out.println("Playlist not found.");
        }
    }

    public List<Playlist> listPlaylists() {
        return playlists;
    }

    public void playSong() {
        if (currentPlaylist == null) {
            System.out.println("No playlist loaded.");
            return;
        }
        if (currentPlaylist.getSongs().isEmpty()) {
            System.out.println("The playlist is empty.");
            return;
        }

        if (currentIndex == -1) {
            currentIndex = 0;
            isPlaying = true;
        } else {
            if (isPlaying) {
                System.out.println("Song [id=" + currentPlaylist.getSongs().get(currentIndex).getId() + "] is paused!");
                return;
            } else {
                isPlaying = true;
            }
        }

        Song currentSong = currentPlaylist.getSongs().get(currentIndex);
        System.out.println("Song [id=" + currentSong.getId() + "] is playing!");
    }

    public void nextSong() {
        if (currentPlaylist == null) {
            System.out.println("No playlist loaded.");
            return;
        }
        if (currentPlaylist.getSongs().isEmpty()) {
            System.out.println("The playlist is empty.");
            return;
        }

        if (currentIndex == -1) {
            currentIndex = 0;
        } else {
            currentIndex = (currentIndex + 1) % currentPlaylist.getSongs().size();
        }

        Song nextSong = currentPlaylist.getSongs().get(currentIndex);
        System.out.println("Song [id=" + nextSong.getId() + "] is playing!");
    }

    public void previousSong() {
        if (currentPlaylist == null) {
            System.out.println("No playlist loaded.");
            return;
        }
        if (currentPlaylist.getSongs().isEmpty()) {
            System.out.println("The playlist is empty.");
            return;
        }

        if (currentIndex == -1) {
            System.out.println("No song is currently playing.");
            return;
        }

        currentIndex = (currentIndex - 1 + currentPlaylist.getSongs().size()) % currentPlaylist.getSongs().size();
        
        Song previousSong = currentPlaylist.getSongs().get(currentIndex);
        System.out.println("Song [id=" + previousSong.getId() + "] is playing!");
    }

    public void stopSong() {
        if (currentPlaylist == null || currentIndex == -1) { 
            System.out.println("No song is currently playing."); 
            return; 
        }

        System.out.println("Song [id=" + currentPlaylist.getSongs().get(currentIndex).getId() + "] is stopped!");
        
        currentIndex = -1; 
        isPlaying = false; 
    }
    
    public boolean deletePlaylist(String playlistName) {
        return playlistRepository.deleteByName(playlistName);
    }

    public boolean addSongToPlaylist(String playlistName, int songId) {
        Playlist playlist = playlistRepository.getPlaylistByName(playlistName);
        Song song = songRepository.getSongById(songId);
    
        if (playlist != null && song != null) {
            playlist.addSong(song);
            playlistRepository.updatePlaylist(playlist);
            return true; 
        }
    
        return false; 
    }
    
    public boolean deleteSongFromPlaylist(String playlistName, int songId) {
        Playlist playlist = playlistRepository.getPlaylistByName(playlistName);
    
        if (playlist == null) {
            System.out.println("Playlist " + playlistName + " Chands not exist.");
            return false;
        }
    
        Song songToRemove = songRepository.getSongById(songId);
        
        if (songToRemove == null) {
            System.out.println("Song [id=" + songId + "] Chands not exist.");
            return false;
        }
    
        boolean isRemoved = playlist.removeSong(songToRemove);
    
        if (isRemoved) {
            playlistRepository.updatePlaylist(playlist);
            return true;
        } else {
            System.out.println("Song [id=" + songId + "] not found in playlist " + playlistName + ".");
            return false;
        }
    }
}