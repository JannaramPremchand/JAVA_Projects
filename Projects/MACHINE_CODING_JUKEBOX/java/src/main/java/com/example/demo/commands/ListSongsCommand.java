package com.example.demo.commands;

import com.example.demo.entities.Song;
import com.example.demo.services.SongService;

import java.util.List;
import java.util.stream.Collectors;

public class ListSongsCommand implements ICommand {
    private final SongService songService;

    public ListSongsCommand(SongService songService) {
        this.songService = songService;
    }

    @Override
    public void invoke(List<String> tokens) {
        List<Song> songs = songService.listSongs();
        
        if (songs.isEmpty()) {
            System.out.println("No songs found.");
        } else {
            String output = songs.stream()
                .map(Song::toString)
                .collect(Collectors.joining(", ", "[", "]"));
                
            System.out.println(output);
        }
    }
}
