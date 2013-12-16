package jgame;

import java.awt.Rectangle;

import jgame.math.Vector2;
import jgame.util.MathUtils;

public class Camera {
	public Rectangle viewport;
	public Rectangle outerBounds;
	public Vector2 off;
	public Vector2 center;
	
	public Camera(int width, int height, Rectangle outerBounds) {
		viewport = new Rectangle(width, height);
		this.outerBounds = outerBounds;
		if(viewport.width > outerBounds.width || viewport.height > outerBounds.height) {
			throw new JGameException("Camera viewport cannot be larger than its outer bounds.");
		}
		off = new Vector2();
		center = new Vector2(width / 2, height / 2);
	}
	
	private boolean canMoveX(int dx) {
		if(dx < 0) { // Moving left
			return off.x + dx >= outerBounds.x;
		} else if(dx > 0) { // Moving right
			return off.x + viewport.width + dx <= outerBounds.x + outerBounds.width;
		}
		return false; // No movement
	}
	
	private boolean canMoveY(int dy) {
		if(dy < 0) { // Moving up
			return off.y + dy >= outerBounds.y;
		} else if(dy > 0) { // Moving down
			return off.y + viewport.height + dy <= outerBounds.y + outerBounds.height;
		}
		return false; // No movement
	}
	
	private void tryMove(int dx, int dy) {
		if(canMoveX(dx)) {
			off.x += dx;
			viewport.x = (int) off.x;
		}
		if(canMoveY(dy)) {
			off.y += dy;
			viewport.y = (int) off.y;
		}
	}
	
	public void follow(Vector2 p, int maxSpeed) {
		follow((int) p.x, (int) p.y, maxSpeed);
	}
	
	public void follow(int x, int y, int maxSpeed) {
		center.x = viewport.width / 2 + off.x;
		center.y = viewport.height / 2 + off.y;
		if(center.x != x || center.y != y) {
			int dx = (int) (x - center.x);
			int dy = (int) (y - center.y);
			tryMove(MathUtils.clampi(dx, -maxSpeed, maxSpeed), MathUtils.clampi(dy, -maxSpeed, maxSpeed));
		}
	}
}