package notion.to.social.enduco.error;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import reactor.core.publisher.Mono;

@Component
@Log4j2
public class ErrorHandler {

    public  <T> Mono<T> handleRemoteCallError(ClientResponse response, String message) {
        return response.createException().flatMap(
                resp -> {
                    log.error("{}, code:  {}, response: {}", message, response.statusCode(), resp.getResponseBodyAsString());
                    return Mono.error(new RemoteCallException(message));
                }
        );
    }

}
