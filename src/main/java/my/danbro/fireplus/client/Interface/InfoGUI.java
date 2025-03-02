package my.danbro.fireplus.client.Interface;

import my.danbro.fireplus.client.PlaceholderAPIHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class InfoGUI extends Screen {
    private final List<String> categories = new ArrayList<>();
    private String selectedCategory = ""; // Храним выбранную категорию

    public InfoGUI() {
        super(Text.of("Custom"));
    }

    public static int getArgb(int alpha, int red, int green, int blue) {
        return (alpha << 24) | (red << 16) | (green << 8) | blue;
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        renderInfo(matrices);
        renderCategories(matrices);
        renderCategoryInfo(matrices);
    }

    public void renderInfo(MatrixStack matrices) {
        int x = this.width / 2 - 50;
        int y = this.height / 2 - 100;
        int width = 250;
        int height = 200;
        fill(matrices, x, y, x + width, y + height, getArgb(180, 30, 30, 30));
    }
    public void renderCategoryInfo(MatrixStack matrices) {
        if (selectedCategory.isEmpty()) return; // Если категория не выбрана, ничего не рисуем.

        switch (selectedCategory) {
            case "Уровень":
                CategoryLevel(matrices);
                break;
            case "Ребитх":
                CategoryRebirth(matrices);
                break;
        }
    }

    public void renderCategories(MatrixStack matrices) {
        int x = this.width / 2 - 150;
        int y = this.height / 2 - 100;
        int width = 80;
        int height = 200;

        // Отрисовка фона категории
        fill(matrices, x, y, x + width, y + height, getArgb(180, 30, 30, 30));

        // Отрисовка заголовка
        this.textRenderer.draw(matrices, "Категории", x + 13, y + 5, 0xFFFFFF);

        // Добавление полоски под заголовком
        int lineY = y + 16;
        int lineHeight = 1;
        fill(matrices, x + 5, lineY, x + width - 5, lineY + lineHeight, getArgb(255, 255, 255, 255));
        if(categories.isEmpty()){
            categories.add("Уровень");
            categories.add("Ребитх");
        }

        int addY = lineY + 10;
        for (String category : categories) {
            drawButton(matrices, x + 12, addY, category);
            addY += 22;
        }
    }

    private void drawButton(MatrixStack matrices, int x, int y, String text) {
        int buttonWidth = 68;
        int buttonHeight = 18;
        int leftButtonX = x - 6;
        int rightButtonX = x + 62;
        int leftButtonY = y - 4;
        int rightButtonY = y + 14;

        // Граница (черный прямоугольник)
        fill(matrices, leftButtonX, leftButtonY, rightButtonX, rightButtonY, getArgb(69, 69, 69, 1));

        // Рисуем текст по центру кнопки
        int textX = x + (buttonWidth - this.textRenderer.getWidth(text)) / 2 - 6;
        int textY = y + (buttonHeight - this.textRenderer.fontHeight) / 2 - 4;
        this.textRenderer.draw(matrices, text, textX, textY, 0xFFFFFF);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int xStart = this.width / 2 - 150;
        int yStart = this.height / 2 - 100 + 16 + 10;
        int buttonWidth = 68;
        int buttonHeight = 18;
        int spacing = 22;

        for (int i = 0; i < categories.size(); i++) {
            int buttonX1 = xStart + 12;
            int buttonX2 = buttonX1 + buttonWidth;
            int buttonY1 = yStart + (i * spacing);
            int buttonY2 = buttonY1 + buttonHeight;

            if (mouseX >= buttonX1 && mouseX <= buttonX2 &&
                    mouseY >= buttonY1 && mouseY <= buttonY2) {

                client.player.playSound(SoundEvents.UI_BUTTON_CLICK, 1.0F, 1.0F);
                selectedCategory = categories.get(i); // Запоминаем категорию
                return true;
            }
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }
    public void CategoryLevel(MatrixStack matrices) {
        int x = this.width / 2 - 100;
        int y = this.height / 2 - 50;
        int width = 200;
        int height = 60;

        this.textRenderer.draw(matrices, "Что это и зачем это нужно:", x + 60, y - 45, 0xFFFFFF);
        int lineY = y - 34;
        int lineHeight = 1;
        fill(matrices, x + 58, lineY, x + width + 90, lineY + lineHeight, getArgb(255, 255, 255, 255));

        int textBoxY = y - 25;
        fill(matrices, x + 58, textBoxY, x + width + 90, textBoxY + height, getArgb(180, 60, 60, 60));

        String text = "Уровень нужен для того чтобы сделать ребитх, ребитх делается каждые 7 уровней.";

        // Уменьшаем ширину для точного переноса
        int textWidth = width + 15;
        List<OrderedText> wrappedText = this.textRenderer.wrapLines(Text.of(text), textWidth);

        // Рисуем каждую строку с небольшим увеличением отступа
        int textStartY = textBoxY + 5;
        int lineSpacing = 12; // Немного увеличиваем расстояние между строками
        for (int i = 0; i < wrappedText.size(); i++) {
            if (textStartY + (i * lineSpacing) > textBoxY + height - 5) {
                break; // Останавливаем рисование, если текст выходит за пределы серого блока
            }
            this.textRenderer.draw(matrices, wrappedText.get(i), x + 65, textStartY + (i * lineSpacing), 0xFFFFFF);
        }
        this.textRenderer.draw(matrices, "Требования:", x + 60, y + 50, 0xFFFFFF);
        int lineY2 = y + 60;
        int lineHeight2 = 1;
        fill(matrices, x + 58, lineY2, x + width + 90, lineY2 + lineHeight2, getArgb(255, 255, 255, 255));

        int textBoxY2 = y + 70;
        fill(matrices, x + 58, textBoxY2, x + width + 90, textBoxY2 + height, getArgb(180, 60, 60, 60));

        String text2 = "Блоки: %%\nМонеты: %%\nРебитх: %%";
        List<OrderedText> wrappedText2 = this.textRenderer.wrapLines(Text.of(text2), width + 15);

        int secondTextStartY = lineY2 + 20;
        for (int i = 0; i < wrappedText2.size(); i++) {
            if (secondTextStartY + (i * lineSpacing) > lineY2 + height - 5) break;
            this.textRenderer.draw(matrices, wrappedText2.get(i), x + 65, secondTextStartY + (i * lineSpacing), 0xFFFFFF);
        }
    }
    public void CategoryRebirth(MatrixStack matrices) {
        int x = this.width / 2 - 100;
        int y = this.height / 2 - 50;
        int width = 200;
        int height = 60;

        this.textRenderer.draw(matrices, "Что это и зачем это нужно:", x + 60, y - 45, 0xFFFFFF);
        int lineY = y - 34;
        int lineHeight = 1;
        fill(matrices, x + 58, lineY, x + width + 90, lineY + lineHeight, getArgb(255, 255, 255, 255));

        int textBoxY = y - 25;
        fill(matrices, x + 58, textBoxY, x + width + 90, textBoxY + height, getArgb(180, 60, 60, 60));

        String text = "Ребитх нужен для открытия новых локаций и дает бусты, которые помогают в дальнейших прокачках.";

        // Уменьшаем ширину для точного переноса
        int textWidth = width + 15;
        List<OrderedText> wrappedText = this.textRenderer.wrapLines(Text.of(text), textWidth);

        // Рисуем каждую строку с небольшим увеличением отступа
        int textStartY = textBoxY + 5;
        int lineSpacing = 12; // Немного увеличиваем расстояние между строками
        for (int i = 0; i < wrappedText.size(); i++) {
            if (textStartY + (i * lineSpacing) > textBoxY + height - 5) {
                break; // Останавливаем рисование, если текст выходит за пределы серого блока
            }
            this.textRenderer.draw(matrices, wrappedText.get(i), x + 65, textStartY + (i * lineSpacing), 0xFFFFFF);
        }
        this.textRenderer.draw(matrices, "Требования:", x + 60, y + 50, 0xFFFFFF);
        int lineY2 = y + 60;
        int lineHeight2 = 1;
        fill(matrices, x + 58, lineY2, x + width + 90, lineY2 + lineHeight2, getArgb(255, 255, 255, 255));

        int textBoxY2 = y + 70;
        fill(matrices, x + 58, textBoxY2, x + width + 90, textBoxY2 + height, getArgb(180, 60, 60, 60));

        String text2 = "Уровень: " + PlaceholderAPIHelper.getStatsLevel();
        List<OrderedText> wrappedText2 = this.textRenderer.wrapLines(Text.of(text2), width + 15);

        int secondTextStartY = lineY2 + 35;
        for (int i = 0; i < wrappedText2.size(); i++) {
            if (secondTextStartY + (i * lineSpacing) > lineY2 + height - 5) break;
            this.textRenderer.draw(matrices, wrappedText2.get(i), x + 65, secondTextStartY + (i * lineSpacing), 0xFFFFFF);
        }
    }
}