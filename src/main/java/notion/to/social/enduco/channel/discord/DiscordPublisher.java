package notion.to.social.enduco.channel.discord;

import lombok.extern.log4j.Log4j2;
import notion.to.social.enduco.channel.*;
import notion.to.social.enduco.error.ErrorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@Log4j2
public class DiscordPublisher {

    private final PostRepository repository;
    private final WebClient client;
    private final ErrorHandler errorHandler;

    @Autowired
    public DiscordPublisher(PostRepository repository, WebClient client, ErrorHandler errorHandler) {
        this.repository = repository;
        this.client = client;
        this.errorHandler = errorHandler;
    }

    @Transactional
    public boolean publish(PostEntity post, String url) {
        post.setStatus(PostStatus.POSTED);
        repository.save(post);
        String emptyDiscordResp = postToDiscord(post, url);
        log.info("Successfully posted to Discord: {}, response: ", post, emptyDiscordResp);
        return true;
    }

    private String postToDiscord(PostEntity post, String url) {
        WebClient.RequestHeadersSpec<?> request = client.post()
                .uri(url)
                .bodyValue(post);

        Mono<String> postResp = request.exchangeToMono(response -> {
            if (response.statusCode().is2xxSuccessful()) {
                return response.bodyToMono(String.class);
            } else {
                return errorHandler.handleRemoteCallError(response, "Failed to post to Discord");
            }
        });
        return postResp.block();
    }


}
