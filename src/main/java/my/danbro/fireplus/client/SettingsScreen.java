package my.danbro.fireplus.client;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

public class SettingsScreen extends Screen {
    private boolean toggleState = false;
    protected SettingsScreen() {
        super(Text.of("Настройки FirePlus"));
    }

    @Override
    protected void init() {
//        int centerX = this.width / 2;
//        int centerY = this.height / 2;
//
//        // Кнопка "Закрыть"
//        this.addButton(new ButtonWidget(centerX - 50, centerY + 40, 100, 20, new TranslatableText("Закрыть"), button -> {
//            this.client.openScreen(null);
//        }));
//        this.addButton(new ButtonWidget(
//                this.width / 2 - 50, this.height / 2 - 10,
//                100, 20,
//                Text.of("Нажми меня"),
//                button -> {
//                    MinecraftClient.getInstance().player.sendMessage(Text.of("Ай тигр!"),false);
//                }
//        ));
//
//        // Добавь здесь больше кнопок с настройками
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices); // Затемнённый фон
        drawCenteredText(matrices, textRenderer, this.title, this.width / 2, 20, 0xFFFFFF);
        super.render(matrices, mouseX, mouseY, delta);
    }
}