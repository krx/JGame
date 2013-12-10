package jgame;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import jgame.util.FileIOHelper;

public class SpriteSheet {
	private BufferedImage[][] subImages;
	int rows;
	int cols;

	public SpriteSheet(BufferedImage img, int row, int col, int w, int h, int sx, int sy, int px, int py) {
		rows = row - 1;
		cols = col - 1;
		subImages = new BufferedImage[row][col];
		int r=0, c=0;
		for(int y=sy; y<Math.min(img.getHeight(), row * (h + py - 1)); y+=h+py) {
			for(int x=sx; x<Math.min(img.getWidth(), col * (w + px -1)); x+=w+px) {
				int ex = Math.min(img.getWidth(), x+w);
				int ey = Math.min(img.getHeight(), y+h);
				subImages[r][c] = img.getSubimage(x, y, ex-x, ey-y);
				c++;
			}
			c=0;
			r++;
		}
	}
	
	public SpriteSheet(String path, int row, int col, int w, int h, int sx, int sy, int px, int py) throws IOException {
			this(ImageIO.read(FileIOHelper.loadResource(path)), row,  col,  w,  h,  sx,  sy, px,  py);
	}
	
	public BufferedImage getSubImage(int x, int y) {
		if(x < 0 || x > cols || y < 0 || y > rows) {
			throw new RuntimeException(new JGameException("Sub-Image index out of range!"));
		}
		return subImages[y][x];
	}
	
	public BufferedImage[] getRow(int row) {
		if(row < 0 || row > rows) {
			throw new RuntimeException(new JGameException("Row index out of range!"));
		}
		return subImages[row];
	}
}