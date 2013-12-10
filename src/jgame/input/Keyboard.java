package jgame.input;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;

public class Keyboard {
	protected static HashMap<Integer, Boolean> keys;
	private static final int CSTART = Character.MIN_VALUE;
	private static final int CEND = Character.MAX_VALUE;
	
	// Action Lists
	protected static ArrayList<KeyboardAction> kPress = new ArrayList<KeyboardAction>();
	protected static ArrayList<KeyboardAction> kRelease = new ArrayList<KeyboardAction>();
	
	public Keyboard() {
		keys = new HashMap<Integer, Boolean>();
		for(int i = CSTART; i <= CEND; i++)
			keys.put(i, false);
	}
	
	public static ArrayList<Integer> getKeysDown() {
		ArrayList<Integer> k = new ArrayList<Integer>();
		for(int i = CSTART; i <= CEND; i++)
			if(keys.get(i))
				k.add(i);
		return k;
	}
	
	public static boolean keyDown(int code) {
		return keys.get(code);
	}
	
	public static boolean keyDown(char key) {
		return keys.get(KeyEvent.getExtendedKeyCodeForChar(key));
	}
	
	public static void addKeyAction(KeyEventType type, KeyboardAction action) {
		switch(type) {
			case KEY_PRESSED:
				kPress.add(action);
				break;
			case KEY_RELEASED:
				kRelease.add(action);
				break;
		}
	}
}