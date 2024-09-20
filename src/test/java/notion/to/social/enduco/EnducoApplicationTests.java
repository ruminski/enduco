package notion.to.social.enduco;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SuppressWarnings("EmptyMethod")
@SpringBootTest
@Disabled
class EnducoApplicationTests {

	@BeforeAll
	static void setUp() {
		System.setProperty("NOTION_TOKEN", "NOTION_TOKEN");
	}

	@Test
	void contextLoads() {
	}

}
