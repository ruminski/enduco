package notion.to.social.enduco.scheduler;

import notion.to.social.enduco.channel.PostService;
import notion.to.social.enduco.notion.NotionFetcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class SchedulerService {

    private final NotionFetcherService notionService;
    private final PostService postService;

    @Autowired
    public SchedulerService(NotionFetcherService notionService, PostService postService) {
        this.notionService = notionService;
        this.postService = postService;
    }

    /*
     To consider persistence of posts fetched from Notion:
     - to avoid duplicates in channels
     - to avoid missing messages in channel in case channel not available
     */
    @Scheduled(fixedDelay = 3, timeUnit = TimeUnit.SECONDS)
    public void execute() {
        notionService.fetchScheduledPosts();
        postService.publishPost();
    }

}
