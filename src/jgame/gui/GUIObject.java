package jgame.gui;

import java.awt.Font;
import java.awt.Graphics;

import jgame.Renderable;
import jgame.util.FileIOHelper;

public abstract class GUIObject implements Renderable {
	protected Font font = null;
	protected float fontSize;
	protected int winWidth;
	protected int winHeight;
	
	public GUIObject(Font font, float fontSize, int winWidth, int winHeight) {
		this.font = font;
		this.fontSize = fontSize;
		this.winWidth = winWidth;
		this.winHeight = winHeight;
	}
	
	public void setFont(Font font) {
		this.font = font;
	}
	
	public void setFont(String path) {
		font = FileIOHelper.loadFont(path);
	}
	
	public void setFontSize(float size) {
		this.fontSize = size;
	}
	
	public void draw(Graphics g, int x, int y) {
		draw(g);
	}
	
	public abstract void draw(Graphics g);
}