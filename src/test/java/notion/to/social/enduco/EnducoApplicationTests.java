package notion.to.social.enduco;

import notion.to.social.enduco.notion.NotionFetcherService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EnducoApplicationTests {

	@BeforeAll
	static void setUp() {
		System.setProperty("NOTION_TOKEN", "NOTION_TOKEN");
	}

	@Test
	void contextLoads() {
	}

}
