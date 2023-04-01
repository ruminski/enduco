package notion.to.social.enduco.channel;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("OTHER")
@Log4j2
public class OtherService implements SocialNetworkConnector {

    @Override
    public boolean publish(String message) {
        log.trace("Sent to {} publisher", ConnectorType.OTHER.name());
        return true;
    }

}
