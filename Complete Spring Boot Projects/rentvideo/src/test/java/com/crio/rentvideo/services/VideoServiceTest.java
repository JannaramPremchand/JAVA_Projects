package com.crio.rentvideo.services;

import com.crio.rentvideo.models.Video;
import com.crio.rentvideo.repositories.VideoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

public class VideoServiceTest {

    @Mock
    private VideoRepository videoRepository;

    @InjectMocks
    private VideoService videoService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddVideo() {
        Video video = new Video();
        video.setTitle("Inception");
        video.setDirector("Christopher Nolan");
        video.setGenre("Sci-Fi");
        
        when(videoRepository.save(video)).thenReturn(video);

        Video addedVideo = videoService.addVideo(video);

        assertNotNull(addedVideo);
        assertEquals("Inception", addedVideo.getTitle());
        verify(videoRepository, times(1)).save(video);
    }

    @Test
    public void testGetVideoById() {
        Long videoId = 1L;
        Video video = new Video();
        video.setId(videoId);
        video.setTitle("Inception");

        when(videoRepository.findById(videoId)).thenReturn(java.util.Optional.of(video));

        Video foundVideo = videoService.getVideoById(videoId).orElse(null);

        assertNotNull(foundVideo);
        assertEquals(videoId, foundVideo.getId());
    }

    @Test
    public void testDeleteVideo() {
        Long videoId = 1L;

        videoService.deleteVideo(videoId);

        verify(videoRepository, times(1)).deleteById(videoId);
    }
}
