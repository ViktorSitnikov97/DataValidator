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

        String emptyString = "";
        String nullString = null;
        boolean expected1 = true;
        boolean expected2 = false;

        Assertions.assertEquals(schema.isValid(emptyString), expected1);
        Assertions.assertEquals(schema.isValid(nullString), expected1);

        schema.required();

        Assertions.assertEquals(schema.isValid(emptyString), expected2);
        Assertions.assertEquals(schema.isValid(nullString), expected2);
    }

    @Test
    public void isValidTest2() {

        boolean expected1 = true;
        boolean expected2 = false;

        schema.required();

        Assertions.assertEquals(schema.isValid("what does the fox say"), expected1);
        Assertions.assertEquals(schema.isValid("Hexlet"), expected1);
        Assertions.assertEquals(schema.contains("wh").isValid("what does the fox say"), expected1);
        Assertions.assertEquals(schema.contains("what").isValid("what does the fox say"), expected1);
        Assertions.assertEquals(schema.contains("whatthe").isValid("what does the fox say"), expected2);

        Assertions.assertEquals(schema.isValid("what does the fox say"), expected2);

    }

    @Test
    public void isValidTest3() {
        boolean expected1 = true;
        Assertions.assertEquals(schema.minLength(10).minLength(4).isValid("Hexlet"), expected1);
        Assertions.assertEquals(schema.contains("help").contains("Hex").isValid("Hexlet"), expected1);
    }

}
