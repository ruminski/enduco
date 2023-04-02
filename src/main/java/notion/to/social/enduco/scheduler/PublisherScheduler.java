package notion.to.social.enduco.scheduler;

import lombok.extern.log4j.Log4j2;
import notion.to.social.enduco.channel.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Log4j2
public class PublisherScheduler {

    private final PostService postService;

    @Autowired
    public PublisherScheduler(PostService postService) {
        this.postService = postService;
    }

    @Scheduled(fixedDelay = 3, initialDelay = 2, timeUnit = TimeUnit.SECONDS)
    public void execute() {
        postService.publishPosts();
    }

}
