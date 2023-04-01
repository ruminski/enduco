package notion.to.social.enduco.channel;

import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Log4j2
@Configuration
@ConfigurationProperties("channel")
@Data
public class ChannelProperties {

    private Map<String, String> urls;

    public String getChannelUrl(String channelName) {
        return urls.getOrDefault(channelName, "");
    }
}
