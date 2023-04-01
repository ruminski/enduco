package notion.to.social.enduco.channel;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("DISCORD")
@Log4j2
public class DiscordService implements SocialNetworkConnector {

    private ChannelProperties channelProperties;

    @Autowired
    public DiscordService(ChannelProperties channelProperties) {
        this.channelProperties = channelProperties;
    }

    @Override
    public boolean publish(String message) {
        String channelUrl = channelProperties.getChannelUrl(ConnectorType.DISCORD.name());
        log.trace("Sent to: {} publisher at: {}", ConnectorType.DISCORD.name(), channelUrl);
        String channelUrlOther = channelProperties.getChannelUrl(ConnectorType.OTHER.name());
        log.info(channelUrlOther);
        return true;
    }

}
