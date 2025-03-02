package my.danbro.fireplus.client;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.c2s.play.CustomPayloadC2SPacket;
import net.minecraft.util.Identifier;

public class PlaceholderRequester {
    private static final Identifier CHANNEL = new Identifier("fireplus", "placeholder_request");

    public static void requestPlaceholder(String placeholder) {
        MinecraftClient client = MinecraftClient.getInstance();

        // Проверяем, подключён ли игрок к серверу
        if (client.getNetworkHandler() == null) {
            System.out.println("Игрок не подключён к серверу!");
            return;
        }

        // Проверяем, зарегистрирован ли канал на сервере
        if (!ClientPlayNetworking.canSend(new Identifier("fireplus", "placeholder"))) {
            System.out.println("Сервер не поддерживает placeholder-канал!");
            return;
        }

        // Отправляем запрос, если всё нормально
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        buf.writeString(placeholder);

        client.getNetworkHandler().sendPacket(new CustomPayloadC2SPacket(new Identifier("fireplus", "placeholder"), buf));
    }
}