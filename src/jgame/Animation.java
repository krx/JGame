package jgame;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import jgame.util.FileIOHelper;

//TODO: Add sprite sheet compatibility 
public class Animation implements Renderable {
	private ArrayList<BufferedImage> frames = new ArrayList<BufferedImage>();
	private int currentFrame;
	private int tick;
	private int rate;
	private boolean flippedH = false;
	private boolean flippedV = false;
	
	public Animation(BufferedImage... animFrames) {
		addFrames(animFrames);
	}
	
	public Animation(String... paths) {
		addFrames(paths);
	}
	
	public void addFrames(BufferedImage... animFrames) {
		for (BufferedImage i : animFrames)
			frames.add(i);
	}
	
	public void addFrames(String... paths) {
		for (String s : paths) {
			try {
				frames.add(ImageIO.read(FileIOHelper.loadResource(s)));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void setFrame(int frame){
		currentFrame = frame;
	}
	
	public boolean isFlippedH() {
		return flippedH;
	}
	
	public boolean isFlippedV() {
		return flippedV;
	}
	
	public int getRate() {
		return rate;
	}
	
	public void insertFrame(int index, BufferedImage frame) {
		frames.add(index, frame);
	}
	
	public void insertFrame(int index, String path) {
		try {
			frames.add(index, ImageIO.read(FileIOHelper.loadResource(path)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void removeFrame(int index) {
		frames.remove(index);
	}
	
	public void setFlippedH(boolean flippedH) {
		this.flippedH = flippedH;
	}
	
	public void setFlippedV(boolean flippedV) {
		this.flippedV = flippedV;
	}
	
	public void setRate(int rate) {
		this.rate = rate;
	}
	
	public int getCurrentFrame() {
		return currentFrame;
	}
	
	public int getLength() {
		return frames.size();
	}
	
	public void draw(Graphics g, int x, int y) {
		if (frames.isEmpty()) {
			return;
		}
		BufferedImage frame = frames.get(currentFrame);
		BufferedImage f = new BufferedImage(frame.getWidth(), frame.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics fg = f.getGraphics();
		if (flippedH && flippedV) { // Horizontal and Vertical flip = 180 degree rotation
			AffineTransform tx = AffineTransform.getRotateInstance(Math.PI, frame.getWidth() / 2, frame.getHeight() / 2);
			AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
			f = op.filter(frame, null);
		} else if (flippedH) { // Horizontal flip
			fg.drawImage(frame, 0, 0, f.getWidth(), f.getHeight(), f.getWidth(), 0, 0, f.getHeight(), null);
		} else if (flippedV) { // Vertical flip
			fg.drawImage(frame, 0, 0, f.getWidth(), f.getHeight(), 0, f.getHeight(), f.getWidth(), 0, null);
		} else { // No changes, draw the original
			fg.drawImage(frame, 0, 0, null);
		}
		g.drawImage(f, x - f.getWidth() / 2, y - f.getHeight() / 2, null);
		if(++tick >= rate) {
			tick = 0;
			currentFrame++;
			currentFrame %= frames.size();
		}
	}
}