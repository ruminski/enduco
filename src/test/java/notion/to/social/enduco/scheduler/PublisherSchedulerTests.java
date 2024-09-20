package notion.to.social.enduco.scheduler;

import notion.to.social.enduco.channel.PostService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class PublisherSchedulerTests {

    @Mock
    private PostService postService;

    @InjectMocks
    private PublisherScheduler publisherScheduler;

    @Test
    void execute() {
        MockitoAnnotations.openMocks(this);
        publisherScheduler.execute();
        verify(postService, times(1)).publishPosts();
    }
}



