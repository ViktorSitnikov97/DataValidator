package hexlet.code;

import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class MapSchemaTest {
    static MapSchema schema;

    @BeforeEach
    void beforeEach() {
        Validator validator = new Validator();
        schema = validator.map();
    }

    @Test
    void isValidTest1() {

        Assertions.assertTrue(schema.isValid(null));

        schema.required();

        Assertions.assertFalse(schema.isValid(null));
        Assertions.assertTrue(schema.isValid(new HashMap<>()));
    }

    @Test
    void isValidTest2() {

        var data = new HashMap<String, String>();
        data.put("key1", "value1");
        Assertions.assertTrue(schema.isValid(data));

        schema.sizeof(3);

        data.put("key2", "value18");
        Assertions.assertFalse(schema.isValid(data));
        data.put("key3", "value");
        Assertions.assertTrue(schema.isValid(data));

    }

}
