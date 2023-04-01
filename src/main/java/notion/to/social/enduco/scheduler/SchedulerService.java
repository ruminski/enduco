package notion.to.social.enduco.scheduler;

import lombok.extern.log4j.Log4j2;
import notion.to.social.enduco.channel.PostService;
import notion.to.social.enduco.notion.NotionFetcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Log4j2
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
        List<String> posts = notionService.fetchScheduledPosts();
        boolean isSuccess = postService.publishPost(posts);
        if(!isSuccess) {
            log.error("Not all messages were successfully sent to all connectors");
        }
    }

}
