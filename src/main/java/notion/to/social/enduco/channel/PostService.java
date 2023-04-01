package notion.to.social.enduco.channel;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Log4j2
public class PostService {

    private final Map<String, SocialNetworkConnector> pepAdapterMap;

    @Autowired
    public PostService(Map<String, SocialNetworkConnector> pepAdapterMap) {
        this.pepAdapterMap = pepAdapterMap;
    }

    public boolean publishPost(List<String> messages) {
        log.info("Publish {} messages to: {}", messages.size(), pepAdapterMap.keySet());
        String msg = messages.get(0);
        pepAdapterMap.values().stream().forEach(connector -> connector.publish(msg));
        return true;
    }


}
