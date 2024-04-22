package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {

    protected Map<String, Predicate<T>> checkMap = new LinkedHashMap<>();

    protected final void addCheck(String key, Predicate<T> pd) {
        checkMap.put(key, pd);
    }

    public final boolean isValid(T value) {

        for (Predicate<T> check : checkMap.values()) {
            if (!check.test(value)) {
                return false;
            }
        }
        return true;
    }
}
