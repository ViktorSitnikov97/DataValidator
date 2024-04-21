package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class MainTest {

    @Test
    public void caclTest() {
        int actual = 0;

        assertEquals(Main.calc(), actual);
    }
}
