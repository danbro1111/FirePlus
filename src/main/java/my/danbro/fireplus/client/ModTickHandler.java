package my.danbro.fireplus.client;

import net.minecraft.client.MinecraftClient;

public class ModTickHandler {
    private static int tickCounter = 0;

    public static void onClientTick(MinecraftClient client) {
        if (++tickCounter >= 200) { // 200 тиков = 10 секунд
            tickCounter = 0;
            PlaceholderRequester.sendPlaceholderRequest("%player_name%");
        }
    }
}
