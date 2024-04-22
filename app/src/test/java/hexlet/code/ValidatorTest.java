package hexlet.code;

import hexlet.code.schemas.StringSchema;
import hexlet.code.schemas.NumberSchema;
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

    @Test
    public void numberTest() {
        Validator validator = new Validator();
        boolean actual = validator.number() instanceof NumberSchema;
        boolean expected = true;

        Assertions.assertEquals(actual, expected);
    }
}
