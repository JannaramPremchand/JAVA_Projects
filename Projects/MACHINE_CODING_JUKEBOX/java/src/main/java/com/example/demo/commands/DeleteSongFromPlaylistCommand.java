package com.example.demo.commands;

import com.example.demo.entities.Playlist;
import com.example.demo.services.PlaylistService;

import java.util.List;

public class DeleteSongFromPlaylistCommand implements ICommand {
    private final PlaylistService playlistService;

    public DeleteSongFromPlaylistCommand(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @Override
    public void invoke(List<String> tokens) {
        if (tokens.size() < 3) {
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

        boolean isRemoved = playlistService.deleteSongFromPlaylist(playlistName, songId);

        if (isRemoved) {
            Playlist updatedPlaylist = playlistService.getPlaylistByName(playlistName);
            System.out.println("Playlist " + playlistName + " is revised with " + updatedPlaylist.getSongs());
        } else {
            System.out.println("Could not delete Song [id=" + songId + "] from Playlist " + playlistName + ".");
        }
    }
}