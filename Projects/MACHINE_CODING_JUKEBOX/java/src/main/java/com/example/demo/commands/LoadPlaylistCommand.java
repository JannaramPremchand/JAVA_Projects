package com.example.demo.commands;

import com.example.demo.services.PlaylistService;

import java.util.List;

public class LoadPlaylistCommand implements ICommand {
    private final PlaylistService playlistService;

    public LoadPlaylistCommand(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @Override
    public void invoke(List<String> tokens) {
        if (tokens.size() < 2) {
            System.out.println("Error: Not enough arguments for LOAD_PLAYLIST command.");
            return;
        }

        String playlistName = tokens.get(1);
        playlistService.loadPlaylist(playlistName);
    }
}
