package hexlet.code.schemas;

import java.util.Objects;
import java.util.function.Predicate;

public final class StringSchema extends BaseSchema<String> {

    public StringSchema required() {
        Predicate<String> req = str -> !Objects.equals(str, null) && !str.isEmpty();
        checkMap.put("required", req);
        return this;
    }

    public StringSchema minLength(int length) {
        Predicate<String> len = str -> str.length() >= length;
        checkMap.put("minLength", len);
        return this;
    }

    public StringSchema contains(String currentString) {
        Predicate<String> con = str -> str.contains(currentString);
        checkMap.put("contains", con);
        return this;
    }

}
