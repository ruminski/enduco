package notion.to.social.enduco.channel;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PostStatusTests {
    @Test
    void testValues() {
        assertEquals(2, PostStatus.values().length);
    }
} 
