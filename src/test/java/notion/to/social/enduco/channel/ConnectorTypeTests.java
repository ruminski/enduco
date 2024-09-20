package notion.to.social.enduco.channel;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ConnectorTypeTests {

    @Test
    void testValues() {
        assertEquals(ConnectorType.DISCORD, ConnectorType.DISCORD);
        assertEquals(ConnectorType.OTHER, ConnectorType.OTHER);
    }
} 
