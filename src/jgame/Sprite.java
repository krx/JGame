package jgame;

import java.awt.Graphics;
import java.awt.Rectangle;

import jgame.math.Vector2;

public abstract class Sprite implements Renderable, Updatable {
	protected Animation activeAnim;
	protected Vector2 position;
	protected Vector2 size;
	
	public Sprite(Vector2 position) {
		loadAnimations();
		this.position = position;
	}
	
	public abstract void loadAnimations();
	
	public void draw(Graphics g) {
		draw(g, (int) position.x, (int) position.y);
	}
	
	public void draw(Graphics g, Camera c) {
		draw(g, (int) (position.x-c.off.x), (int) (position.y-c.off.y));
	}
	
	public Vector2 getPosition() {
		return new Vector2(position);
	}
	
	public Vector2 getSize() {
		return new Vector2(size);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int) position.x, (int) position.y, (int) size.x, (int) size.y);
	}
}