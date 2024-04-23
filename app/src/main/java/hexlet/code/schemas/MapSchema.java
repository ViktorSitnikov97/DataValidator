package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Predicate;

public final class MapSchema<K, V> extends BaseSchema<Map<K, V>> {

    public MapSchema<K, V> required() {
        Predicate<Map<K, V>> req =  map -> {
            if (map == null) {
                return false;
            }
            for (V value : map.values()) {
                if (value == null) {
                    return false;
                }
            }
            return true;
        };
        addCheck("required", req);
        return this;
    }

    public MapSchema<K, V> sizeof(int size) {
        Predicate<Map<K, V>> siz = map -> map == null || map.size() == size;
        addCheck("sizeof", siz);
        return this;
    }

    public MapSchema<K, V> shape(Map<K, BaseSchema<V>> mapWithSettings) {
        Predicate<Map<K, V>> sh = map -> mapWithSettings.entrySet().stream()
                .allMatch(
                        entry -> {
                            BaseSchema<V> schema =  mapWithSettings.get(entry.getKey());
                            V currentValue = map.get(entry.getKey());
                            return schema.isValid(currentValue);
                        });
        addCheck("shape", sh);
        return this;
    }
}
