package com.example.demo;

import com.example.demo.commands.*;
import com.example.demo.repositories.*;
import com.example.demo.services.*;

public class Configuration {
    private static Configuration instance = new Configuration();

    private Configuration() {}

    public static Configuration getInstance() {
        return instance;
    }

    private final ISongRepository songRepository = new SongRepository();
    private final PlaylistRepository playlistRepository = new PlaylistRepository();
    private final SongService songService = new SongService(songRepository);
    private final PlaylistService playlistService = new PlaylistService(playlistRepository,songRepository);
    private final AddSongCommand addSongCommand = new AddSongCommand(songService);
    private final ListSongsCommand listSongsCommand = new ListSongsCommand(songService);
    private final CreatePlaylistCommand createPlaylistCommand = new CreatePlaylistCommand(playlistService, songService);
    private final LoadPlaylistCommand loadPlaylistCommand = new LoadPlaylistCommand(playlistService);
    private final PlaySongCommand playSongCommand = new PlaySongCommand(playlistService);
    private final NextSongCommand nextSongCommand = new NextSongCommand(playlistService);
    private final PreviousSongCommand previousSongCommand = new PreviousSongCommand(playlistService);
    private final StopSongCommand stopSongCommand = new StopSongCommand(playlistService);
    private final DeletePlaylistCommand deletePlaylistCommand = new DeletePlaylistCommand(playlistService);
    private final AddSongToPlaylistCommand addSongToPlaylistCommand = new AddSongToPlaylistCommand(playlistService);
    private final DeleteSongFromPlaylistCommand deleteSongFromPlaylistCommand = new DeleteSongFromPlaylistCommand(playlistService);
    private final CommandRegistry commandRegistry = new CommandRegistry();

    private void registerCommands() {
        commandRegistry.registerCommand("ADD_SONG", addSongCommand);
        commandRegistry.registerCommand("LIST_SONGS", listSongsCommand);
        commandRegistry.registerCommand("CREATE_PLAYLIST", createPlaylistCommand);
        commandRegistry.registerCommand("LOAD_PLAYLIST", loadPlaylistCommand);
        commandRegistry.registerCommand("PLAY_SONG", playSongCommand);
        commandRegistry.registerCommand("NEXT_SONG", nextSongCommand);
        commandRegistry.registerCommand("PREVIOUS_SONG", previousSongCommand);
        commandRegistry.registerCommand("STOP_SONG", stopSongCommand);
        commandRegistry.registerCommand("DELETE_PLAYLIST", deletePlaylistCommand);
        commandRegistry.registerCommand("ADD_SONG_TO_PLAYLIST", addSongToPlaylistCommand);
        commandRegistry.registerCommand("DELETE_SONG_FROM_PLAYLIST", deleteSongFromPlaylistCommand);
    }
    
    public CommandRegistry getCommandRegistry() {
        registerCommands();
        return commandRegistry;
    }
}
