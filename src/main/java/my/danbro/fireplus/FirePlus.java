package my.danbro.fireplus;

import my.danbro.fireplus.client.Keybinds;
import net.fabricmc.api.ModInitializer;

public class FirePlus implements ModInitializer {
    @Override
    public void onInitialize() {
        Keybinds.register(); // Регистрируем клавиши
    }
}