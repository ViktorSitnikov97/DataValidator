package hexlet.code;


import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class MapSchemaTest {

    static Validator validator;
    static MapSchema schema;


    @BeforeEach
    void beforeEach() {
        validator = new Validator();
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

    @Test
    void isValidTest3() {

        Map<String, BaseSchema<String>> schemas1 = new HashMap<>();

        schemas1.put("firstName", validator.string().required().minLength(5));
        schemas1.put("lastName", validator.string().required().minLength(9));

        schema.shape(schemas1);

        Map<String, String> human1 = new HashMap<>();
        human1.put("firstName", "Django");
        human1.put("lastName", "Unchained");

        Assertions.assertTrue(schema.isValid(human1));

        Map<String, BaseSchema<String>> schemas2 = new HashMap<>();
        schemas2.put("firstName", validator.string().required().minLength(10));
        schemas2.put("lastName", validator.string().required().minLength(10));

        schema.shape(schemas2);

        Map<String, String> human2 = new HashMap<>();
        human1.put("firstName", "Quentin");
        human1.put("lastName", "Tarantino");

        Assertions.assertFalse(schema.isValid(human2));

        Map<String, BaseSchema<Integer>> schemas3 = new HashMap<>();
        final int minH = 170;
        final int maxH = 180;
        final int minW = 70;
        final int maxW = 85;
        schemas3.put("height", validator.number().required().positive().range(minH, maxH));
        schemas3.put("weight", validator.number().required().positive().range(minW, maxW));

        schema.shape(schemas3);

        Map<String, Integer> human3 = new HashMap<>();
        human3.put("height", 175);
        human3.put("weight", 75);

        Assertions.assertTrue(schema.isValid(human3));

    }
}
