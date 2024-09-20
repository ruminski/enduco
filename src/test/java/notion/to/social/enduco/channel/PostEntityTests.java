package notion.to.social.enduco.channel;

import notion.to.social.enduco.notion.Post;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PostEntityTests {

    @Test
    void testToEntity() {
        Post post = new Post();
        post.setId("testId");
        post.setCreatedTime(java.time.LocalDateTime.now());
        post.setContent("testContent");
        post.setChannel("DISCORD");
        PostEntity entity = PostEntity.toEntity(post);
        assertEquals("testId", entity.getId());
        assertEquals(post.getCreatedTime(), entity.getCreationTime());
        assertEquals("testContent", entity.getContent());
        assertEquals(ConnectorType.DISCORD, entity.getChannel());
        assertEquals(PostStatus.NEW, entity.getStatus());
    }
} 
