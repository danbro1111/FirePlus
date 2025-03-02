package my.danbro.fireplus.client;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Identifier;

public class PlaceholderReceiver {
    private static final Identifier CHANNEL = new Identifier("fireplus", "placeholder_response");

    public static void registerReceiver() {
        ClientPlayNetworking.registerGlobalReceiver(CHANNEL, (client, handler, buf, responseSender) -> {
            String parsedPlaceholder = buf.readString(32767);
            client.execute(() -> {
                if (client.player != null) {
                    client.player.sendMessage(new LiteralText("Placeholder: " + parsedPlaceholder), false);
                }
            });
        });
    }
}
