package com.example.demo.commands;

import com.example.demo.entities.Playlist;
import com.example.demo.entities.Song;
import com.example.demo.services.PlaylistService;
import com.example.demo.services.SongService;

import java.util.List;
import java.util.stream.Collectors;

public class CreatePlaylistCommand implements ICommand {
    private final PlaylistService playlistService;
    private final SongService songService;

    public CreatePlaylistCommand(PlaylistService playlistService, SongService songService) {
        this.playlistService = playlistService;
        this.songService = songService;
    }

    @Override
    public void invoke(List<String> tokens) {
        if (tokens.size() < 3) {
            System.out.println("Error: Not enough arguments for CREATE_PLAYLIST command.");
            return;
        }

        String playlistName = tokens.get(1);
        List<Integer> songIds = tokens.subList(2, tokens.size()).stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        List<Song> songs = songIds.stream()
                .map(songService::getSongById) 
                .filter(song -> song != null)
                .collect(Collectors.toList());

        if (songs.isEmpty()) {
            System.out.println("Error: No valid songs found for the playlist.");
            return;
        }

        Playlist newPlaylist = playlistService.createPlaylist(playlistName, songs);
        System.out.println(newPlaylist);
    }
}
