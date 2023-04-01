package notion.to.social.enduco.channel;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class PostService {

    private ChannelProperties channelProperties;

    @Autowired
    public PostService(ChannelProperties channelProperties) {
        this.channelProperties = channelProperties;
    }

    public void publishPost() {
        log.info("Publish to: {}", channelProperties.getChannelUrl("discord"));
        log.info("    and to: {}", channelProperties.getChannelUrl("other"));
    }

}
