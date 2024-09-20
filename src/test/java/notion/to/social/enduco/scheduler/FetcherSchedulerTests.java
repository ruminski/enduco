package notion.to.social.enduco.scheduler;

import notion.to.social.enduco.notion.NotionFetcherService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

import static org.mockito.Mockito.*;

class FetcherSchedulerTests {

    @Mock
    private NotionFetcherService notionFetcherService;

    @InjectMocks
    private FetcherScheduler fetcherScheduler;

    @Test
    void execute() {
        MockitoAnnotations.openMocks(this);
        when(notionFetcherService.fetchScheduledPosts()).thenReturn(List.of());
        fetcherScheduler.execute();
        verify(notionFetcherService, times(1)).fetchScheduledPosts();
    }
}



