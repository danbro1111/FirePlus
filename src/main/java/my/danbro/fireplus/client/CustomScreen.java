package my.danbro.fireplus.client;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

public class CustomScreen extends Screen {
    private boolean toggleState = false;
    public static boolean showExtraText = false; // Делаем переменную статической для доступа из другого класса

    protected CustomScreen() {
        super(Text.of("Custom GUI"));
    }

    public static int getArgb(int alpha, int red, int green, int blue) {
        return (alpha << 24) | (red << 16) | (green << 8) | blue;
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);

        int x = this.width / 2 - 75;
        int y = this.height / 2 - 50;
        int width = 150;
        int height = 100;

        // Окно
        fill(matrices, x, y, x + width, y + height, getArgb(180, 30, 30, 30));

        // Текст
        this.textRenderer.draw(matrices, "ToggleSprintSneak", x + 10, y + 20, 0xFFFFFF);
        drawToggleButton(matrices, x + width - 30, y + 18, toggleState);

        this.textRenderer.draw(matrices, "Show Extra Text", x + 10, y + 50, 0xFFFFFF);
        drawToggleButton(matrices, x + width - 30, y + 48, showExtraText);
    }

    private void drawToggleButton(MatrixStack matrices, int x, int y, boolean state) {
        int buttonSize = 12;
        int buttonX = state ? x + 12 : x;
        int buttonColor = state ? 0xFF00FF00 : 0xFFFF0000;

        // Граница (черный прямоугольник)
        fill(matrices, x - 2, y - 2, x + 14, y + buttonSize + 2, 0xFF000000);
        // Сам переключатель
        fill(matrices, buttonX, y, buttonX + buttonSize, y + buttonSize, buttonColor);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int buttonX1 = toggleState ? this.width / 2 + 62 : this.width / 2 + 50;
        int buttonY1 = this.height / 2 - 32;
        int buttonSize = 12;

        // Переключатель ToggleSprintSneak
        if (mouseX >= buttonX1 && mouseX <= buttonX1 + buttonSize && mouseY >= buttonY1 && mouseY <= buttonY1 + buttonSize) {
            toggleState = !toggleState;
            return true;
        }

        int buttonX2 = showExtraText ? this.width / 2 + 62 : this.width / 2 + 50;
        int buttonY2 = this.height / 2 - 2;

        // Переключатель Show Extra Text
        if (mouseX >= buttonX2 && mouseX <= buttonX2 + buttonSize && mouseY >= buttonY2 && mouseY <= buttonY2 + buttonSize) {
            showExtraText = !showExtraText;
            FirePlusClient.showText = !FirePlusClient.showText;
            return true;
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }
}
