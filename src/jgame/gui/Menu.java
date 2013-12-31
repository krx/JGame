package jgame.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

import jgame.util.MathUtils;

public class Menu extends GUIObject {
	public static final String BLANK = "";
	private static final int PADDING = 20;
	
	private String title;
	private String[] options;
	private float titleSize;
	private int sIndex;
	private Color fill;
	private Color selected;
	private Color unSelected;
	
	public Menu(Font font, float fontSize, int winWidth, int winHeight, String title, float titleSize, Color fill, Color selected, Color unselected, String[] options) {
		super(font, fontSize, winWidth, winHeight);
		this.title = title;
		this.options = options;
		this.titleSize = titleSize;
		this.fill = fill;
		this.selected = selected;
		this.unSelected = unselected;
		resetSelector();
	}
	
	private int getBlanksBefore(int index) {
		index = MathUtils.clampi(index, 0, options.length-1);
		int blank = 0;
		for(int i=0; i<=index; i++) {
			if (options[i] == BLANK) {
				blank++;
			}
		}
		return blank;
	}
	
	public void resetSelector() {
		sIndex = 0;
		if(options[sIndex] == BLANK) {
			selectDown();
		}
	}
	
	public void selectUp() {
		if(--sIndex < 0) {
			sIndex = options.length - 1;
		}
		
		if(options[sIndex] == BLANK) {
			selectUp();
		}
	}
	
	public void selectDown() {
		if(++sIndex >= options.length) {
			sIndex = 0;
		}
		
		if(options[sIndex] == BLANK) {
			selectDown();
		}
	}
	
	public int getSelectIndex() {
		return sIndex - getBlanksBefore(sIndex);
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
		g.setColor(selected);
		g.drawString(title, winWidth/2 - fm.stringWidth(title)/2, dy += fm.getHeight());
		g.setFont(g.getFont().deriveFont(fontSize));
		fm = g.getFontMetrics();
		for(int i = 0; i < options.length; i++) {
			g.setColor(i == sIndex ? selected : unSelected);
			g.drawString(options[i], winWidth/2 - (fm.stringWidth(options[i]))/2, dy += fm.getHeight());
		}
		
		g.setFont(oldFont);
	}
}