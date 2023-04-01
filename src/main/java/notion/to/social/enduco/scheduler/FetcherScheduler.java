package notion.to.social.enduco.scheduler;

import lombok.extern.log4j.Log4j2;
import notion.to.social.enduco.channel.PostService;
import notion.to.social.enduco.notion.NotionFetcherService;
import notion.to.social.enduco.notion.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Log4j2
public class FetcherScheduler {

    private final NotionFetcherService notionService;

    @Autowired
    public FetcherScheduler(NotionFetcherService notionService) {
        this.notionService = notionService;
    }

    @Scheduled(fixedDelay = 60, timeUnit = TimeUnit.SECONDS)
    public void execute() {
        notionService.fetchScheduledPosts();
    }

}
