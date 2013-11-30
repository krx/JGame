package jgame.input;

import java.util.ArrayList;

import jgame.math.Vector2;

public class Mouse{
	protected Vector2 position;
	protected static boolean left, middle, right;
	
	//Action Lists
	protected static ArrayList<MouseAction> mClick = new ArrayList<MouseAction>();
	protected static ArrayList<MouseAction> mPress = new ArrayList<MouseAction>();
	protected static ArrayList<MouseAction> mRelease = new ArrayList<MouseAction>();
	protected static ArrayList<MouseAction> mMove = new ArrayList<MouseAction>();
	protected static ArrayList<MouseAction> mDrag = new ArrayList<MouseAction>();
	
	public Mouse(){
		position = new Vector2();
		left = false;
		middle = false;
		right = false;
	}
	
	public static boolean isLeftDown() {
		return left;
	}
	
	public static boolean isMiddleDown() {
		return middle;
	}
	
	public static boolean isRightDown() {
		return right;
	}
	
	public static void addMouseAction(MouseEventType type, MouseAction action){
		switch(type){
			case MOUSE_CLICKED:
				mClick.add(action);
				break;
			case MOUSE_DRAGGED:
				mDrag.add(action);
				break;
			case MOUSE_MOVED:
				mMove.add(action);
				break;
			case MOUSE_PRESSED:
				mPress.add(action);
				break;
			case MOUSE_RELEASED:
				mRelease.add(action);
				break;
		}
	}
}