package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NumberSchemaTest {

    static NumberSchema schema;

    @BeforeEach
    void beforeEach() {
        Validator validator = new Validator();
        schema = validator.number();
    }

    @Test
    void isValidTest1() {

        boolean expected1 = true;
        boolean expected2 = false;

        boolean actual1 =  schema.isValid(null);
        Assertions.assertEquals(actual1, expected1);

        schema.required();

        boolean actual2 = schema.isValid(null);
        Assertions.assertEquals(actual2, expected2);

    }

    @Test
    void isValidTest2() {

        boolean actual1 =  schema.positive().isValid(null);

        Assertions.assertEquals(actual1, true);
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
