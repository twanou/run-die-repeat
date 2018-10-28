package net.daneau.libgdxjam.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import net.daneau.libgdxjam.Main;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 700;
        config.height = 700;
        config.title = "Run Die Repeat";
        new LwjglApplication(new Main(), config);
    }
}
