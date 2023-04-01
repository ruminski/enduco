package notion.to.social.enduco.channel;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class DiscordService implements SocialNetworkConnector {

    private ChannelProperties channelProperties;

    @Autowired
    public DiscordService(ChannelProperties channelProperties) {
        this.channelProperties = channelProperties;
    }

    @Override
    public boolean publish(String message) {
        return true;
    }

}
