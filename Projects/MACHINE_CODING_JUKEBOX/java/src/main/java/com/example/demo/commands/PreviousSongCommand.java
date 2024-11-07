package com.example.demo.commands;

import java.util.List;
import com.example.demo.services.PlaylistService;

public class PreviousSongCommand implements ICommand {
    private final PlaylistService playlistService;

    public PreviousSongCommand(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @Override
    public void invoke(List<String> tokens) {
        playlistService.previousSong();
        
    }
}
