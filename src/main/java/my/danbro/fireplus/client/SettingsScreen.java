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
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices); // Затемнённый фон
        drawCenteredText(matrices, textRenderer, this.title, this.width / 2, 20, 0xFFFFFF);
        super.render(matrices, mouseX, mouseY, delta);
    }
}