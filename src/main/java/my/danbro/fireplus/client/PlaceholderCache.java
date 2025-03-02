package my.danbro.fireplus.client;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class PlaceholderCache {
    private static final Map<UUID, Map<String, String>> placeholderResults = new ConcurrentHashMap<>();

    public static void setPlaceholder(UUID playerId, String placeholder, String value) {
        placeholderResults.computeIfAbsent(playerId, k -> new ConcurrentHashMap<>()).put(placeholder, value);
    }

    public static String getPlaceholder(UUID playerId, String placeholder) {
        return placeholderResults.getOrDefault(playerId, Collections.emptyMap()).getOrDefault(placeholder, "N/A");
    }
}