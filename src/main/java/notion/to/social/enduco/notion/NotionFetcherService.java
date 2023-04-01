package notion.to.social.enduco.notion;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class NotionFetcherService {

    @Value("${notion.url}")
    private String notionUrl;

    @Value("${notion.token}")
    private String notionToken;

    public List<String> fetchScheduledPosts() {
        log.info("Fetch from: {} with token: {}", notionUrl, notionToken);
        return List.of("fake");
    }

}
