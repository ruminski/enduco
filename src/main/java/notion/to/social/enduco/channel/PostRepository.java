package notion.to.social.enduco.channel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, String> {
    @NonNull
    Optional<PostEntity> findById(@NonNull String id);

    @NonNull
    List<PostEntity> findByChannelAndStatus(@NonNull ConnectorType channel, @NonNull PostStatus status);
}
