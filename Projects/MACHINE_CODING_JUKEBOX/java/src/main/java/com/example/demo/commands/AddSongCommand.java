package com.example.demo.commands;

import com.example.demo.entities.Song;
import com.example.demo.services.SongService;

import java.util.List;

public class AddSongCommand implements ICommand {
    private final SongService songService;
    private static int idCounter = 1;

    public AddSongCommand(SongService songService) {
        this.songService = songService;
    }

    @Override
    public void invoke(List<String> tokens) {
        if (tokens.size() < 5) {
            System.out.println("Error: Not enough arguments for ADD_SONG command.");
            return;
        }

        String songName = tokens.get(1);
        String artist = tokens.get(2);
        String album = tokens.get(3);
        String genre = tokens.get(4);

        
        Song newSong = new Song(idCounter++, songName, artist, album, genre);
        songService.addSong(newSong);
        System.out.println(newSong);
    }
}
