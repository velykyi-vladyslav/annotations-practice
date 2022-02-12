package velykyi.vladyslav.annotations;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
class AnnotationsApplicationTests {

	@Test
	void contextLoads() {
		assertThat(true, is(true));
	}
}
