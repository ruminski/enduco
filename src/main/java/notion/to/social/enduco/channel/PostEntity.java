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

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime creationTime;

    private String content;

    @Enumerated(EnumType.STRING)
    private PostStatus status;

    @Enumerated(EnumType.STRING)
    private ConnectorType channel;
    public static PostEntity toEntity(Post post) {
        return PostEntity.builder()
                .id(post.getId())
                .creationTime(post.getCreatedTime())
                .content(post.getContent())
                .channel(ConnectorType.valueOf(post.getChannel()))
                .status(PostStatus.NEW)
                .build();
    }
}
