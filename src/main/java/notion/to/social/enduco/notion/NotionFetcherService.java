package notion.to.social.enduco.notion;

import lombok.extern.log4j.Log4j2;
import notion.to.social.enduco.channel.PostEntity;
import notion.to.social.enduco.channel.PostRepository;
import notion.to.social.enduco.error.ErrorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;

@Service
@Log4j2
public class NotionFetcherService {

    // obviously there should be model for input
    // for test simplification read Posted not Scheduled as required,
    // read all the posts rather only created since last scheduler run again for ease of POC development
    private static final String TEST_BODY = """
            {
              "filter": {
                "and": [
                  {
                    "property": "Status",
                    "status": {
                      "equals": "Posted"
                    }
                  },
                  {
                    "property": "Channel",
                    "multi_select": {
                      "contains": "Discord"
                    }
                  }
                ]
              }
            }""";

    //move static fields to configuration
    @SuppressWarnings("UastIncorrectHttpHeaderInspection")
    private static final String NOTION_VERSION = "Notion-Version";
    private static final String LATEST_NOTION_VERSION = "2022-06-28";

    @Value("${notion.url}")
    private String url;

    @Value("${notion.token}")
    private String authToken;

    private final WebClient client;
    private final ErrorHandler errorHandler;
    private final PostRepository repository;

    @Autowired
    public NotionFetcherService(WebClient client, ErrorHandler errorHandler, PostRepository repository) {
        this.client = client;
        this.errorHandler = errorHandler;
        this.repository = repository;
    }

    public List<Post> fetchScheduledPosts() {
        log.info("Fetch from: {} with token: {}", url, authToken);
        WebClient.RequestHeadersSpec<?> request = client.post()
                .uri(url)
                .bodyValue(TEST_BODY)
                .headers(h -> h.setBearerAuth(authToken))
                .headers(h -> h.set(NOTION_VERSION, LATEST_NOTION_VERSION));

        Mono<PostsResponse> notionResp = request.exchangeToMono(response -> {
            if (response.statusCode().is2xxSuccessful()) {
                return response.bodyToMono(PostsResponse.class);
            } else {
                return errorHandler.handleRemoteCallError(response, "Failed to get fetch posts from Notion");
            }
        });

        List<Post> posts = Objects.requireNonNull(notionResp.block()).getResults();
        posts.stream().map(PostEntity::toEntity)
                .forEach(repository::save);
        log.info("Saved {} posts for processing", posts.size());

        return posts;
    }


}
