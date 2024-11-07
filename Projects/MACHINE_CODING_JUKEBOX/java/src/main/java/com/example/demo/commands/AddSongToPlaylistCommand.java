package com.example.demo.commands;

import java.util.List;
import com.example.demo.entities.Song;
import com.example.demo.services.PlaylistService;

public class AddSongToPlaylistCommand implements ICommand {
    private final PlaylistService playlistService;

    public AddSongToPlaylistCommand(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @Override
    public void invoke(List<String> tokens) {
        if (tokens.size() < 2) {
            System.out.println("Playlist name and song ID must be provided.");
            return;
        }

        String playlistName = tokens.get(1); 
        int songId;

        try {
            songId = Integer.parseInt(tokens.get(2)); 
        } catch (NumberFormatException e) {
            System.out.println("Invalid song ID.");
            return;
        }

        boolean isAdded = playlistService.addSongToPlaylist(playlistName, songId);
        
        if (isAdded) {
            var updatedPlaylist = playlistService.getPlaylistByName(playlistName);
            System.out.println("Playlist " + playlistName + " is revised with " + updatedPlaylist.getSongs());
        } else {
            System.out.println("Could not add Song [id=" + songId + "] to Playlist " + playlistName + ".");
        }
    }
}
