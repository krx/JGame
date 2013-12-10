package jgame.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.SwingUtilities;

public class EventHandler implements KeyListener, MouseListener, MouseMotionListener {
	protected static Keyboard keyboard = new Keyboard();
	protected static Mouse mouse = new Mouse();
	
	public void mouseDragged(MouseEvent e) {
		mouse.position.set(e.getX(), e.getY());
		
		for(MouseAction a : Mouse.mDrag)
			a.actionPerformed(e);
	}
	
	public void mouseMoved(MouseEvent e) {
		mouse.position.set(e.getX(), e.getY());
		
		for(MouseAction a : Mouse.mMove)
			a.actionPerformed(e);
	}
	
	public void mousePressed(MouseEvent e) {
		if(SwingUtilities.isLeftMouseButton(e))
			Mouse.left = true;
		if(SwingUtilities.isMiddleMouseButton(e))
			Mouse.middle = true;
		if(SwingUtilities.isRightMouseButton(e))
			Mouse.right = true;
		
		for(MouseAction a : Mouse.mPress)
			a.actionPerformed(e);
	}
	
	public void mouseReleased(MouseEvent e) {
		if(SwingUtilities.isLeftMouseButton(e))
			Mouse.left = false;
		if(SwingUtilities.isMiddleMouseButton(e))
			Mouse.middle = false;
		if(SwingUtilities.isRightMouseButton(e))
			Mouse.right = false;
		
		for(MouseAction a : Mouse.mRelease)
			a.actionPerformed(e);
	}
	
	public void mouseClicked(MouseEvent e) {
		for(MouseAction a : Mouse.mClick)
			a.actionPerformed(e);
	}
	
	public void keyPressed(KeyEvent e) {
		Keyboard.keys.put(e.getKeyCode(), true);
		
		for(KeyboardAction a : Keyboard.kPress)
			a.actionPerformed(e);
	}
	
	public void keyReleased(KeyEvent e) {
		Keyboard.keys.put(e.getKeyCode(), false);
		
		for(KeyboardAction a : Keyboard.kRelease)
			a.actionPerformed(e);
	}
	
	public void keyTyped(KeyEvent e) {
	}
	
	public void mouseEntered(MouseEvent e) {
	}
	
	public void mouseExited(MouseEvent e) {
	}
}