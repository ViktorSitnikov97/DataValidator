package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ValidatorTest {

    @Test
    public void stringTest() {
        Validator validator = new Validator();
        boolean actual = validator.string() instanceof StringSchema;
        boolean expected = true;

        Assertions.assertEquals(actual, expected);
    }
}
