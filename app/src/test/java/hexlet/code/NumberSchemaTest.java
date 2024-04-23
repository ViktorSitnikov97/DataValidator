package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public final class NumberSchemaTest {

    private static NumberSchema schema;

    @BeforeEach
    void beforeEach() {
        Validator validator = new Validator();
        schema = validator.number();
    }

    @Test
    void isValidTest1() {

        Assertions.assertEquals(schema.isValid(null), true);

        schema.required();

        Assertions.assertEquals(schema.isValid(null), false);

    }

    @Test
    void isValidTest2() {

        Assertions.assertEquals(schema.positive().isValid(null), true);
        Assertions.assertTrue(schema.isValid(10));

        schema.required();

        Assertions.assertTrue(schema.isValid(10));
        Assertions.assertFalse(schema.isValid(0));
        Assertions.assertFalse(schema.isValid(-10));

        Assertions.assertTrue(schema.range(10, 20).isValid(17));
        Assertions.assertFalse(schema.range(10, 20).isValid(9));
        Assertions.assertFalse(schema.range(10, 20).isValid(21));
        Assertions.assertTrue(schema.range(5, 10).
                range(2, 4).isValid(3));
    }
}
