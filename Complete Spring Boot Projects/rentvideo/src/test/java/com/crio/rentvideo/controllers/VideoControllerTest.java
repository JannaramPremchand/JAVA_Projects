package com.crio.rentvideo.controllers;

import com.crio.rentvideo.models.Video;
import com.crio.rentvideo.services.VideoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

public class VideoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private VideoService videoService;

    @InjectMocks
    private VideoController videoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(videoController).build();
    }

    @Test
    void testGetAllVideos() throws Exception {
        Video video1 = new Video();
        video1.setTitle("Video 1");
        video1.setDirector("Director 1");
        video1.setGenre("Action");

        Video video2 = new Video();
        video2.setTitle("Video 2");
        video2.setDirector("Director 2");
        video2.setGenre("Comedy");

        List<Video> videos = Arrays.asList(video1, video2);

        when(videoService.getAllVideos()).thenReturn(videos);

        mockMvc.perform(get("/api/videos")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'title':'Video 1', 'director':'Director 1', 'genre':'Action'}, {'title':'Video 2', 'director':'Director 2', 'genre':'Comedy'}]"));
    }

    @Test
    void testAddVideo() throws Exception {
        Video video = new Video();
        video.setTitle("New Video");
        video.setDirector("New Director");
        video.setGenre("Drama");

        when(videoService.addVideo(any(Video.class))).thenReturn(video);

        mockMvc.perform(post("/api/videos/manage")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\": \"New Video\", \"director\": \"New Director\", \"genre\": \"Drama\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    void testDeleteVideo() throws Exception {
        Long videoId = 1L;

        doNothing().when(videoService).deleteVideo(videoId);

        mockMvc.perform(delete("/api/videos/manage/{id}", videoId))
                .andExpect(status().isOk());
    }
}
