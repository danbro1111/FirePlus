package my.danbro.fireplus.client;

import net.minecraft.client.MinecraftClient;
import java.util.UUID;

public class PlaceholderAPIHelper {
    public static String getStatsLevel() {
        UUID playerId = MinecraftClient.getInstance().player.getUuid();
        PlaceholderRequester.requestPlaceholder("rms_stats_level");
        return PlaceholderCache.getPlaceholder(playerId, "rms_stats_level");
    }

    public static String getStatsRebirth() {
        UUID playerId = MinecraftClient.getInstance().player.getUuid();
        PlaceholderRequester.requestPlaceholder("rms_stats_rebirth");
        return PlaceholderCache.getPlaceholder(playerId, "rms_stats_rebirth");
    }
}