package hexlet.code;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public final class StringSchema {

    private boolean isInclude = false;
    private final Object minLength = new Object();
    private final Object contains = new Object();
    private Map<Object, Predicate<Object>> map = new HashMap<>();

    public StringSchema required() {
        isInclude = true;
        return this;
    }

    public StringSchema minLength(int length) {
        Predicate<Object> len = str -> str.toString().length() >= length;
        map.put(minLength, len);
        return this;
    }

    public StringSchema contains(String currentString) {
        Predicate<Object> con = str -> str.toString().contains(currentString);
        map.put(contains, con);
        return this;
    }

    public boolean isValid(String str) {
        if (isInclude && (Objects.equals(str, null) || str.equals(""))) {
            return false;
        }
        String string = str;

        if (!map.isEmpty()) {
            for (Map.Entry<Object, Predicate<Object>> pair : map.entrySet()) {
                Predicate<Object> value = pair.getValue();
                if (!value.test(string)) {
                    return false;
                }
            }
        }

        return true;
    }
}
