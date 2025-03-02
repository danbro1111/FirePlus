package my.danbro.fireplus.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

public class FirePlusClient implements ClientModInitializer {


    public static boolean showText = false;
    public boolean getShowText(){
        return showText;
    }

    @Override
    public void onInitializeClient() {
        try{
            PlaceholderReceiver.registerReceiver();
            Keybinds.register(); // Регистрируем обработчик клавиш

            HudRenderCallback.EVENT.register((matrices, tickDelta) -> {
                MinecraftClient client = MinecraftClient.getInstance();
                if (client.player != null) {
                    drawFireSunText(matrices);
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void drawFireSunText(MatrixStack matrices) {

        if (showText) {
            MinecraftClient client = MinecraftClient.getInstance();

            int x = 10;  // Текст слева
            int y = 10;  // Отступ сверху

            // Основной текст Fire+
            client.textRenderer.drawWithShadow(matrices, Text.of("Fire+"), x, y, 0xFFAA00);

            // Дополнительный текст с переменными
            y += 15; // Смещаем вниз для нового текста
            client.textRenderer.drawWithShadow(matrices, Text.of("Блоков: %javascript_level-currentblocks%"), x, y, 0xFFFFFF);

            y += 15; // Смещаем вниз
            client.textRenderer.drawWithShadow(matrices, Text.of("Монет: %javascript_level-currentmoney%"), x, y, 0xFFFF00);

            y += 15; // Смещаем вниз
            client.textRenderer.drawWithShadow(matrices, Text.of("Ребитх: %javascript_levelrebirth%"), x, y, 0xFF55FF);
        }
    }
}