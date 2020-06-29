package com.akn.game.desktop;

import com.akn.game.Main;
import com.akn.game.data.Constants;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width  = Constants.DESKTOP_WIDTH;
		config.height = Constants.DESKTOP_HEIGHT;
//		config.resizable = false;
		new LwjglApplication(new Main(), config);
	}
}
