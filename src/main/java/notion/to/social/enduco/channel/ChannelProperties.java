package notion.to.social.enduco.channel;

import jakarta.annotation.PostConstruct;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import notion.to.social.enduco.configuration.InvalidConfigurationException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

@Log4j2
@Configuration
@ConfigurationProperties("channel")
@Setter
public class ChannelProperties {

    private Map<String, String> urls;

    @PostConstruct
    private void validateConfiguration() {
        try {
            urls.keySet().forEach(ConnectorType::valueOf);
        } catch (IllegalArgumentException e) {
            handleConfigException("Invalid 'channel:urls' configuration - connector type. " + e.getMessage());
        }

        urls.values().forEach(url -> {
            try {
                //noinspection deprecation
                new URL(url);
            } catch (MalformedURLException e) {
                handleConfigException("Invalid 'channel.urls' configuration - URL. " + url);
            }
        });

    }

    private static void handleConfigException(String message) {
        log.error(message);
        throw new InvalidConfigurationException(message);
    }

    public String getChannelUrl(String channelName) {
        return urls.getOrDefault(channelName, "");
    }
}
