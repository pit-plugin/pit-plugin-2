import org.junit.Test;

import java.io.IOException;

public class SimpleTest {

    @Test
    public void test() throws IOException, InterruptedException {
        PitRunner runner = new PitRunner();
        runner.runMutation();
    }
}
