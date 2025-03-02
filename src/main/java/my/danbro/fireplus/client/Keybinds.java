package my.danbro.fireplus.client;

import my.danbro.fireplus.client.Interface.InfoGUI;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class Keybinds {
    public static final KeyBinding OPEN_SETTINGS = KeyBindingHelper.registerKeyBinding(
            new KeyBinding("Найстроки мода", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_RIGHT_SHIFT, "FirePlus")
    );
    public static final KeyBinding OPEN_GUI = KeyBindingHelper.registerKeyBinding(
            new KeyBinding("Найстройка функций", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_LEFT_ALT, "FirePlus")
    );
    public static final KeyBinding INFO_GUI = KeyBindingHelper.registerKeyBinding(
            new KeyBinding("Дополнительная ннформация", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_G, "FirePlus")
    );

    public static void register() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (OPEN_SETTINGS.wasPressed()) {
                if (client.currentScreen == null) {
                    client.openScreen(new SettingsScreen()); // Открываем GUI настроек
                }
            }
        });
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (OPEN_GUI.wasPressed()) {
                if (client.currentScreen == null) {
                    MinecraftClient.getInstance().openScreen(new CustomScreen()); // Открываем GUI настроек
                }
            }
        });
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (INFO_GUI.wasPressed()) {
                if (client.currentScreen == null) {
                    client.openScreen(new InfoGUI()); // Открываем GUI настроек
                }
            }
        });
    }
}