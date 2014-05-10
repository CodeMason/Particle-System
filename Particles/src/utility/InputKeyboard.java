package utility;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * This class handles all keyboard input from the user.
 * 
 * @author Valkryst
 * --- Last Edit 10-May-2014
 */
public class InputKeyboard implements KeyListener {
    /** Contains a true or false value for keys 0 to 127 which represent whether or not the key is currently pressed. */
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
     * @param e An event which indicates that a keystroke occurred in a component.
	 */
	public void keyPressed(final KeyEvent e) {
		final int KEY_CODE = e.getKeyCode();
		if (KEY_CODE < isPressed.length)
			isPressed[KEY_CODE] = true;
	}
	
	/**
	 * When a key is released then set that key as released.
     * @param e An event which indicates that a keystroke occurred in a component.
	 */
	public void keyReleased(final KeyEvent e) {
		final int KEY_CODE = e.getKeyCode();
		if (KEY_CODE < isPressed.length)
			isPressed[KEY_CODE] = false;
	}

    /**
     * When the unicode character represented by
     * e.getKeyChar() is sent by the keyboard
     * to the system.
     *
     * This has never been used in any of my (Valkryst's) programs,
     * therefore this utility class does not include any functionality
     * for this method. It is simply included because it's required
     * to be included by the KeyListener class.
     * @param e An event which indicates that a keystroke occurred in a component.
     */
	public void keyTyped(final KeyEvent e) {}
}
