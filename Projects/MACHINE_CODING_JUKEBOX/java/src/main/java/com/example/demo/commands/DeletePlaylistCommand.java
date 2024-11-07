package com.example.demo.commands;

import java.util.List;
import com.example.demo.services.PlaylistService;

public class DeletePlaylistCommand implements ICommand {
    private final PlaylistService playlistService;

    public DeletePlaylistCommand(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @Override
    public void invoke(List<String> tokens) {
        if (tokens.size() < 1) {
            System.out.println("Playlist name must be provided.");
            return;
        }

        String playlistName = tokens.get(1);
        boolean isDeleted = playlistService.deletePlaylist(playlistName);

        if (isDeleted) {
            System.out.println("Playlist " + playlistName + " is deleted!");
        } else {
            System.out.println("Playlist " + playlistName + " not found!");
        }
    }
}
