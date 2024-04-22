package hexlet.code.schemas;

import java.util.Objects;
import java.util.function.Predicate;

public class NumberSchema extends BaseSchema<Integer> {

    public NumberSchema required() {
        Predicate<Integer> req = number -> !Objects.equals(number, null);
        checkMap.put("required", req);
        return this;
    }

    public NumberSchema positive() {
        Predicate<Integer> pos = number -> number == null || number > 0;
        checkMap.put("positive", pos);
        return this;
    }

    public NumberSchema range(int bottomBound, int upperBound) {
        Predicate<Integer> ran = number -> bottomBound <= number && number <= upperBound;
        checkMap.put("range", ran);
        return this;
    }

}
