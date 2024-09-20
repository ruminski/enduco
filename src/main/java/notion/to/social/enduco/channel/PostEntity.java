package notion.to.social.enduco.channel;

import jakarta.persistence.*;
import lombok.*;
import notion.to.social.enduco.notion.Post;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostEntity {

    @Id
    @EqualsAndHashCode.Exclude
    private String id;

    private LocalDateTime creationTime;
    private String content;
    private PostStatus status;
    private ConnectorType channel;

    public static PostEntity from(Post post) {
        return builder()
                .id(post.getId())
                .creationTime(post.getCreatedTime())
                .content(post.getContent())
                .channel(ConnectorType.valueOf(post.getChannel()))
                .status(PostStatus.NEW)
                .build();
    }
}
