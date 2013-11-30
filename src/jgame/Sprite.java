package jgame;

import java.awt.Graphics;

import jgame.math.Vector2;

public abstract class Sprite implements Renderable, Updatable {
	protected Animation activeAnim;
	protected Vector2 position;
	
	public Sprite(Vector2 position) {
		loadAnimations();
		this.position = position;
	}
	
	public abstract void loadAnimations();
	
	public void draw(Graphics g) {
		draw(g, (int) position.x, (int) position.y);
	}
}