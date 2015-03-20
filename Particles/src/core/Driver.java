package core;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Driver {
	public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Particle Test";
        config.width = 512;
        config.height = 512;
        new LwjglApplication(new Screen(), config);
	}
}
