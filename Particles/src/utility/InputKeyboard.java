package utility;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * This class handles all keyboard input from the user.
 * 
 * @author Valkryst
 * --- Last Edit 23-Sep-2013
 */
public class InputKeyboard implements KeyListener {
	private boolean[] isPressed = new boolean[128];
	
	/**
	 * Checks if the specified key is currently pressed.
	 * @param x The keyEvent.getKeyCode() number of the key.
	 * @return Return whether the specified key is pressed or not.
	 */
	public boolean isKeyPressed(final int x) {
		return isPressed[x];
	}
	
	/**
	 * When a key is pressed then set that key as pressed.
	 */
	public void keyPressed(final KeyEvent e) {
		final int KEY_CODE = e.getKeyCode();
		if (KEY_CODE < isPressed.length)
			isPressed[KEY_CODE] = true;
	}
	
	/**
	 * When a key is released then set that key as released.
	 */
	public void keyReleased(final KeyEvent e) {
		final int KEY_CODE = e.getKeyCode();
		if (KEY_CODE < isPressed.length)
			isPressed[KEY_CODE] = false;
	}

	public void keyTyped(final KeyEvent e) {
	}
}
