package jgame.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import jgame.util.StringUtils;

public class TextMenu extends GUIObject {
	private static final int PADDING = 20;
	
	private String title;
	private String[] messages;
	private float titleSize;
	private Color fill;
	private Color text;

	public TextMenu(Font font, float fontSize, int winWidth, int winHeight, String title, float titleSize, Color fill, Color text, String[] messages) {
		super(font, fontSize, winWidth, winHeight);
		this.title = title;
		this.messages = messages;
		this.titleSize = titleSize;
		this.fill = fill;
		this.text = text;
	}
	
	public void draw(Graphics gg) {
		Graphics2D g = (Graphics2D) gg;
		Font oldFont = g.getFont();
		
		if(font != null) {
			g.setFont(font);
		}
		g.setFont(g.getFont().deriveFont(titleSize));
		FontMetrics fm = g.getFontMetrics();
		
		g.setColor(fill);
		g.fillRect(0, 0, winWidth, winHeight);
		
		int dy = PADDING;
		g.setColor(text);
		g.drawString(title, winWidth/2 - fm.stringWidth(title)/2, dy += fm.getHeight());
		g.setFont(g.getFont().deriveFont(fontSize));
		fm = g.getFontMetrics();
		
		ArrayList<String> lines = new ArrayList<String>();
		for(String m : messages) {
			lines.addAll(StringUtils.wrap(m, fm, winWidth - 2*PADDING));
		}
		
		for(int i = 0; i < lines.size(); i++) {
			g.drawString(lines.get(i), winWidth/2 - (fm.stringWidth(lines.get(i)))/2, dy += fm.getHeight());
		}
		
		g.setFont(oldFont);
	}
}