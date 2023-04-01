package notion.to.social.enduco.notion;

import lombok.Data;

import java.util.List;

@Data
public class PostsResponse {
    private List<Post> results;
}
