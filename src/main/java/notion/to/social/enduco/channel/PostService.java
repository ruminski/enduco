package notion.to.social.enduco.channel;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Log4j2
public class PostService {

    private final Map<String, SocialNetworkConnector> pepAdapterMap;

    @Autowired
    public PostService(Map<String, SocialNetworkConnector> pepAdapterMap) {
        this.pepAdapterMap = pepAdapterMap;
    }

    public void publishPosts() {
        log.info("Publish posts to: {}", pepAdapterMap.keySet());
        pepAdapterMap.values().forEach(SocialNetworkConnector::publish);
    }


}
