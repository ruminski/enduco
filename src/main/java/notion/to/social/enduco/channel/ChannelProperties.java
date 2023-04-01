package notion.to.social.enduco.channel;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.Set;

@Log4j2
@Configuration
@ConfigurationProperties("channel")
@Setter
public class ChannelProperties {

    private Map<String, String> urls;

    public String getChannelUrl(String channelName) {
        return urls.getOrDefault(channelName, "");
    }

    public Set<String> getChannels() {
        return urls.keySet();
    }
}
