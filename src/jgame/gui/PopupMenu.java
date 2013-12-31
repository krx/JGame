package jgame.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Stroke;

public class PopupMenu extends GUIObject {
	private final static int PADDING = 20;
	private final static int RADIUS = 75;
	
	private String title;
	private String[] options;
	private int sIndex;
	private Color border;
	private Color fill;
	private Color selected;
	private Color unSelected;
	
	
	public PopupMenu(Font font, float fontSize, int winWidth, int winHeight, String title, Color border, Color fill, Color selected, Color unselected, String[] options) {
		super(font, fontSize, winWidth, winHeight);
		this.title = title;
		this.options = options;
		this.border = border;
		this.fill = fill;
		this.selected = selected;
		this.unSelected = unselected;
		sIndex = 0;
	}
	
	public void selectUp() {
		sIndex--;
		if(sIndex < 0) {
			sIndex = options.length - 1;
		}
	}
	
	public void selectDown() {
		sIndex++;
		if(sIndex >= options.length) {
			sIndex = 0;
		}
	}
	
	public int getSelectIndex() {
		return sIndex;
	}
	
	public void setSelectIndex(int sIndex) {
		this.sIndex = sIndex;
	}
	
	public void draw(Graphics gg) {
		Graphics2D g = (Graphics2D) gg;
		Font oldFont = g.getFont();
		Stroke oldStroke = g.getStroke();
		
		if(font != null) {
			g.setFont(font);
		}
		g.setFont(g.getFont().deriveFont(fontSize));
		g.setStroke(new BasicStroke(8));
		
		FontMetrics fm = g.getFontMetrics();
		Rectangle menu = new Rectangle();
		menu.height = fm.getHeight() * (options.length + 1) + (2 * PADDING);
		menu.width = fm.stringWidth(title) + (2 * PADDING);
		for(String o : options) {
			menu.width = Math.max(menu.width, fm.stringWidth(o) + (2 * PADDING));
		}
		menu.setLocation((winWidth - menu.width) / 2, (winHeight - menu.height) / 2);
		
		g.setColor(border);
		g.drawRoundRect(menu.x, menu.y, menu.width, menu.height, RADIUS, RADIUS);
		g.setColor(fill);
		g.fillRoundRect(menu.x, menu.y, menu.width, menu.height, RADIUS, RADIUS);
		
		
		int dx = winWidth / 2;
		int dy = menu.y;
		g.setColor(selected);
		g.drawString(title, dx - fm.getStringBounds(title, g).getBounds().width / 2, dy += fm.getHeight());
		for(int i = 0; i < options.length; i++) {
			g.setColor(i == sIndex ? selected : unSelected);
			g.drawString(options[i], dx - fm.getStringBounds(options[i], g).getBounds().width / 2, dy += fm.getHeight());
		}
		
		g.setStroke(oldStroke);
		g.setFont(oldFont);
	}
}