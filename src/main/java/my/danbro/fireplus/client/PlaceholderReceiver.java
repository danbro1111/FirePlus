package my.danbro.fireplus.client;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.util.Identifier;
import java.util.UUID;

public class PlaceholderReceiver {
    private static final Identifier CHANNEL = new Identifier("fireplus", "placeholder");

    public static void registerReceiver() {
        ClientPlayNetworking.registerGlobalReceiver(CHANNEL, (client, handler, buf, responseSender) -> {
            try {
                String response = buf.readString(32767); // Получаем "имя:значение"
                System.out.println("[PlaceholderReceiver] Получен ответ: " + response);
                System.out.println("[PlaceholderReceiver] Длина пакета: " + buf.readableBytes());

                String[] parts = response.split(":", 2);
                if (parts.length < 2) {
                    System.err.println("[PlaceholderReceiver] Ошибка: некорректный формат ответа!");
                    return;
                }

                String placeholder = parts[0];
                String value = parts[1];

                // Проверяем, что client.player не null
                if (client.player == null) {
                    System.err.println("[PlaceholderReceiver] Ошибка: client.player == null");
                    return;
                }

                UUID playerId = client.player.getUuid();

                client.execute(() -> {
                    System.out.println("[PlaceholderReceiver] Кешируем плейсхолдер: " + placeholder + " = " + value);
                    PlaceholderCache.setPlaceholder(playerId, placeholder, value);
                });
            } catch (Exception e) {
                System.err.println("[PlaceholderReceiver] Ошибка при обработке пакета!");
                e.printStackTrace();
            }
        });
    }
}