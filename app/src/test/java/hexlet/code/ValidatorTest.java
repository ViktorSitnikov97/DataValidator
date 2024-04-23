package hexlet.code;

import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.StringSchema;
import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ValidatorTest {

    @Test
    public void validator() {

        Validator validator = new Validator();

        Assertions.assertInstanceOf(StringSchema.class, validator.string());
        Assertions.assertInstanceOf(NumberSchema.class, validator.number());
        Assertions.assertInstanceOf(MapSchema.class, validator.map());
    }
}
