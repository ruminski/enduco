package notion.to.social.enduco.channel.discord;

import lombok.extern.log4j.Log4j2;
import notion.to.social.enduco.channel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("DISCORD")
@Log4j2
public class DiscordService implements SocialNetworkConnector {

    private final ChannelProperties channelProperties;
    private final PostRepository repository;
    private final DiscordPublisher publisher;

    @Autowired
    public DiscordService(ChannelProperties channelProperties, PostRepository repository, DiscordPublisher publisher) {
        this.channelProperties = channelProperties;
        this.repository = repository;
        this.publisher = publisher;
    }

    @Override
    public void publish() {
        List<PostEntity> posts = repository.findByChannelAndStatus(ConnectorType.DISCORD, PostStatus.NEW);
        if (posts.isEmpty()) {
            log.info("Nothing to publish to Discord channel");
        } else {
            final String url = channelProperties.getChannelUrl(ConnectorType.DISCORD.name());
            log.info("Start to send to: {} publisher at: {}", ConnectorType.DISCORD.name(), url);
            posts.forEach(e -> publisher.publish(e, url));
        }
    }



}
