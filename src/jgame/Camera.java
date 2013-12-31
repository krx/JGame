package jgame;

import java.awt.Rectangle;

import jgame.math.Vector2;
import jgame.util.MathUtils;

/**
 * 2D Camera that follows a given point
 * Works by moving a viewport rectangle bound by a larger rectangle
 * 
 * @author Kareem El-Faramawi
 */
public class Camera {
	// Camera viewport, typically the screen size
	public Rectangle viewport;
	// Outer bound for the camera viewport, typically the size of the map or world
	public Rectangle outerBounds;
	// Offsets from default position
	public Vector2 off;
	// Center of camera
	public Vector2 center;
	
	/**
	 * Creates a camera with a viewport of the given size, bounded by a given Rectangle.
	 * The Width and height of the viewport cannot be larger than the width and height of the outer bounds
	 * respectively.
	 * 
	 * @param width Width of viewport
	 * @param height Height of viewport
	 * @param outerBounds Outer bounds of the viewport
	 */
	public Camera( int width, int height, Rectangle outerBounds ) {
		viewport = new Rectangle( width, height );
		this.outerBounds = outerBounds;
		if ( viewport.width > outerBounds.width || viewport.height > outerBounds.height ) {
			throw new JGameException( "Camera viewport cannot be larger than its outer bounds." );
		}
		off = new Vector2();
		center = new Vector2( width / 2, height / 2 );
	}
	
	/**
	 * Checks if moving by the given amount horizontally will pass the left or right outer bounds
	 * 
	 * @param dx Amount to move on x-axis
	 * @return True if the viewport can move without passing its bounds
	 */
	private boolean canMoveX( int dx ) {
		if ( dx < 0 ) { // Moving left
			return off.x + dx >= outerBounds.x;
		} else if ( dx > 0 ) { // Moving right
			return off.x + viewport.width + dx <= outerBounds.x + outerBounds.width;
		}
		return false; // No movement
	}
	
	/**
	 * Checks if moving by the given amount vertically will pass the top or bottom outer bounds
	 * 
	 * @param dx Amount to move on y-axis
	 * @return True if the viewport can move without passing its bounds
	 */
	private boolean canMoveY( int dy ) {
		if ( dy < 0 ) { // Moving up
			return off.y + dy >= outerBounds.y;
		} else if ( dy > 0 ) { // Moving down
			return off.y + viewport.height + dy <= outerBounds.y + outerBounds.height;
		}
		return false; // No movement
	}
	
	/**
	 * Moves the camera viewport by the given x and y amounts. Will only move in each direction if the
	 * viewport will stay inside its bounds
	 * 
	 * @param dx X distance to move
	 * @param dy Y distance to move
	 */
	private void tryMove( int dx, int dy ) {
		if ( canMoveX( dx ) ) {
			off.x += dx;
			viewport.x = (int) off.x;
		}
		if ( canMoveY( dy ) ) {
			off.y += dy;
			viewport.y = (int) off.y;
		}
	}
	
	/**
	 * Follows a point at the given maximum speed
	 * 
	 * @param p Point to follow
	 * @param maxSpeed Maximum speed of movement
	 */
	public void follow( Vector2 p, int maxSpeed ) {
		follow( (int) p.x, (int) p.y, maxSpeed );
	}
	
	/**
	 * Follows a point at the given maximum speed.
	 * <p>
	 * The distance the camera will move in each axis is clamped to be within the range [-maxSpeed, maxSpeed]
	 * to smooth out movement
	 * </p>
	 * 
	 * @param x X coordinate to follow
	 * @param y Y coordiante to follow
	 * @param maxSpeed Maximum speed of movement
	 */
	public void follow( int x, int y, int maxSpeed ) {
		center.x = viewport.width / 2 + off.x;
		center.y = viewport.height / 2 + off.y;
		// If we're not already centered
		if ( center.x != x || center.y != y ) {
			int dx = (int) ( x - center.x );
			int dy = (int) ( y - center.y );
			tryMove( MathUtils.clampi( dx, -maxSpeed, maxSpeed ), MathUtils.clampi( dy, -maxSpeed, maxSpeed ) );
		}
	}
}