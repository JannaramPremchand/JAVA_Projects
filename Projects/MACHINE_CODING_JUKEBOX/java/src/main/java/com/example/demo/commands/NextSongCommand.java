package com.example.demo.commands;

import java.util.List;
import com.example.demo.services.PlaylistService;

public class NextSongCommand implements ICommand {
    private final PlaylistService playlistService;

    public NextSongCommand(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @Override
    public void invoke(List<String> tokens) {
        playlistService.nextSong();
        
    }
}
