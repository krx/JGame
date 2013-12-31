package jgame.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.util.ArrayList;

import jgame.util.StringUtils;

public class Dialog extends GUIObject {
	private final static int PADDING = 10;
	private final static int RADIUS = 50;
	
	private String[] messages;
	private int mIndex;
	private int cIndex;
	private boolean scroll;
	private Color border;
	private Color fill;
	private Color text;
	
	public Dialog(Font font, float fontSize, int winWidth, int winHeight, boolean scroll, Color border, Color fill, Color text, String[] messages) {
		super(font, fontSize, winWidth, winHeight);
		this.messages = messages;
		this.scroll = scroll;
		this.border = border;
		this.fill = fill;
		this.text = text;
		reset();
	}
	
	public void reset() {
		mIndex = 0;
		cIndex = 0;
	}
	
	public boolean done() {
		return mIndex >= messages.length;
	}
	
	public void iterateMessages() {
		if(cIndex == messages[mIndex].length() || !scroll) {
			mIndex++;
			cIndex = 0;
		} else {
			cIndex = messages[mIndex].length();
		}
	}
	
	public void iterateCharacters() {
		cIndex = Math.min(cIndex + 1, messages[mIndex].length());
	}
	
	public void draw(Graphics gg) {
		Graphics2D g = (Graphics2D) gg;
		Font oldFont = g.getFont();
		Stroke oldStroke = g.getStroke();
		
		if(font != null) {
			g.setFont(font);
		}
		g.setFont(g.getFont().deriveFont(fontSize));
		g.setStroke(new BasicStroke(5));
		FontMetrics fm = g.getFontMetrics();
		ArrayList<String> lines = StringUtils.wrap(messages[mIndex], fm, winWidth - 4*PADDING);
		int height = fm.getHeight() * lines.size();

		Rectangle dialog = new Rectangle(PADDING, winHeight - 3*PADDING - height, winWidth - 2*PADDING, height + 2*PADDING);
		
		g.setColor(border);
		g.drawRoundRect(dialog.x, dialog.y, dialog.width, dialog.height, RADIUS, RADIUS);
		g.setColor(fill);
		g.fillRoundRect(dialog.x, dialog.y, dialog.width, dialog.height, RADIUS, RADIUS);
		
		g.setColor(text);
		
		int dy = winHeight - (4 * PADDING) - height;
		if(scroll) {
			drawString(g, lines, 2 * PADDING, dy);
			iterateCharacters();
		} else {
			for(String line : lines) {
				g.drawString(line, 2 * PADDING, dy += g.getFontMetrics().getHeight());
			}
		}
		
		g.setFont(g.getFont().deriveFont(14f));
		g.drawString("Space...", dialog.x + dialog.width - g.getFontMetrics().stringWidth("Space...") - PADDING, dialog.y + dialog.height - PADDING);
		
		g.setStroke(oldStroke);
		g.setFont(oldFont);
		
	}
	
	private void drawString(Graphics g, ArrayList<String> lines, int x, int y) {
		int charDraw = cIndex;
		
		for(int i = 0; i < lines.size(); i++) {
			String line = lines.get(i);
			if(charDraw > line.length()) {
				charDraw -= line.length();
			} else {
				lines.set(i, line.substring(0, charDraw));
				charDraw = 0;
			}
		}
		
		for(String line : lines) {
			g.drawString(line, x, y += g.getFontMetrics().getHeight());
		}
	}
}