package notion.to.social.enduco.channel.other;

import lombok.extern.log4j.Log4j2;
import notion.to.social.enduco.channel.ConnectorType;
import notion.to.social.enduco.channel.SocialNetworkConnector;
import org.springframework.stereotype.Service;

@Service("OTHER")
@Log4j2
public class OtherService implements SocialNetworkConnector {

    @Override
    public void publish() {
        log.trace("Sent to {} publisher", ConnectorType.OTHER.name());
    }

}
