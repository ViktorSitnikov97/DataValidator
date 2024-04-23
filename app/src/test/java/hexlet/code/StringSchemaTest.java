package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StringSchemaTest {

    static StringSchema schema;

    @BeforeEach
    public void beforeEach() {
        Validator validator = new Validator();
        schema = validator.string();
    }
    @Test
    public void isValidTest1() {

        Assertions.assertTrue(schema.isValid(""));
        Assertions.assertTrue(schema.isValid(null));

        schema.required();

        Assertions.assertFalse(schema.isValid(""));
        Assertions.assertFalse(schema.isValid(null));
    }

    @Test
    public void isValidTest2() {

        schema.required();

        Assertions.assertTrue(schema.isValid("what does the fox say"));
        Assertions.assertTrue(schema.isValid("Hexlet"));
        Assertions.assertTrue(schema.contains("wh").isValid("what does the fox say"));
        Assertions.assertTrue(schema.contains("what").isValid("what does the fox say"));
        Assertions.assertFalse(schema.contains("whatthe").isValid("what does the fox say"));

        Assertions.assertFalse(schema.isValid("what does the fox say"));
    }

    @Test
    public void isValidTest3() {

        Assertions.assertTrue(schema.minLength(10).minLength(4).isValid("Hexlet"));
        Assertions.assertTrue(schema.contains("help").contains("Hex").isValid("Hexlet"));
    }
}
