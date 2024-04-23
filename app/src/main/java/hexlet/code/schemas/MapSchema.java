package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema<Map<String, String>> {

    public final MapSchema required() {
        Predicate<Map<String, String>> req =  map -> {
            if (map == null) {
                return false;
            }
            for (String value : map.values()) {
                if (value == null) {
                    return false;
                }
            }
            return true;
        };
        addCheck("required", req);
        return this;
    }

    public final MapSchema sizeof(int num) {
        Predicate<Map<String, String>> siz = map -> map.size() == num;
        addCheck("sizeof", siz);
        return this;
    }
}
