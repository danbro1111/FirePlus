package my.danbro.fireplus.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.util.Identifier;

public class PlaceholderRequester {
    private static final Identifier CHANNEL = new Identifier("fireplus", "placeholder_request");

    public static String sendPlaceholderRequest(String placeholder) {
        MinecraftClient client = MinecraftClient.getInstance();

        // Проверяем, подключён ли игрок к серверу
        if (client.getNetworkHandler() == null) {
            System.out.println("Ошибка: клиент не подключён к серверу!");
            return placeholder;
        }

        // Получаем информацию о сервере
        ServerInfo serverInfo = client.getCurrentServerEntry();
        if (serverInfo == null || !serverInfo.address.equals("donator26.gamely.pro:20624")) { // Укажите IP или домен нужного сервера
            System.out.println("Ошибка: не тот сервер!");
            return placeholder;
        }

        // Отправка запроса, если подключены к нужному серверу
        PlaceholderRequester.sendPlaceholderRequest(placeholder);
        return placeholder;
    }
}