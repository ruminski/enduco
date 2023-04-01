package notion.to.social.enduco.notion;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
public class Post {
    private String id;
    @JsonProperty("created_time")
    private LocalDateTime createdTime;
    private String content;
    private String channel;

    @JsonProperty("properties")
    @SuppressWarnings("unchecked")
    private void unpackNested(Map<String, Object> properties) {
        Map<String, List<?>> content = (Map<String, List<?>>) properties.get("Content");
        List<Map<String,String>> richText = (List<Map<String,String>>) content.get("rich_text");
        if(richText.isEmpty()) return;
        this.content = richText.get(0).get("plain_text");
        Map<String, List<?>> channel = (Map<String, List<?>>) properties.get("Channel");
        List<Map<String,String>> multiSelect = (List<Map<String,String>>) channel.get("multi_select");
        if(multiSelect.isEmpty()) return;
        this.channel = multiSelect.get(0).get("name").toUpperCase(); //later support multiple channels for a post
    }
}
