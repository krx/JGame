/**
 * @author Kareem El-Faramawi
 * Represents a two-dimensional vector
 * Provides 2D vector operations
 */

package jgame.math;

public class Vector2 {
	public double x = 0;
	public double y = 0;
	
	/**
	 * Initializes this vector to 0
	 */
	public Vector2(){
		this(0,0);
	}
	
	/**
	 * Initializes this vector to the given coordinates
	 * @param x x-coordinate of the vector
	 * @param y y-coordinate of the vector
	 */
	public Vector2(double x, double y) {
		set(x, y);
	}

	/**
	 * Initializes this vector as a copy of another
	 * @param v Vector to copy
	 */
	public Vector2(Vector2 v) {
		set(v);
	}
	
	/**
	 * Sets the coordinates of this vector to those of another
	 * @param v Vector to copy coordinates from
	 * @return Vector with new coordinates
	 */
	public Vector2 set(Vector2 v){
		x = v.x;
		y = v.y;
		return this;
	}
	
	/**
	 * Sets the coordinates of this vector to the supplied coordinates
	 * @param vx x-coordinate
	 * @param vy y-coordinate
	 * @return Vector with new coordinates
	 */
	public Vector2 set(double vx, double vy){
		x=vx;
		y=vy;
		return this;
	}
	
	/**
	 * Returns the angle of this vector in degrees in the range [0,360), measured relative to the x-axis, going counter-clockwise (Standard position)
	 * @return The angle of this vector in degrees
	 */
	public double angle(){
		double angle = Math.toDegrees(Math.atan2(y, x));
		if(angle < 0) angle += 360;
		return angle;
	}
	
	/**
	 * @return The magnitude of this vector
	 */
	public double mag(){
		return Math.sqrt(x*x + y*y);
	}
	
	/**
	 * @return The squared magnitude of this vector
	 */
	public double mag2(){
		return x*x + y*y;
	}
	
	/**
	 * Calculates the dot product of this vector and another
	 * @param v Vector to dot
	 * @return The dot product of two vectors
	 */
	public double dot(Vector2 v){
		return x*v.x + y*v.y;
	}
	
	/**
	 * Calculates the dot product of this vector and another
	 * @param vx x-coordinate of second vector
	 * @param vy y-coordinate of second vector
	 * @return The dot product of two vectors
	 */
	public double dot(double vx, double vy){
		return x*vx + y*vy;
	}
	
	/**
	 * Calculates the Euclidean distance between this vector and another
	 * @param v Vector to find the distance to
	 * @return The Euclidean distance between two vectors
	 */
	public double dist(Vector2 v){
		double dx = v.x-x;
		double dy = v.y-y;
		return Math.sqrt(dx*dx + dy*dy);
	}
	
	/**
	 * Calculates the Euclidean distance between this vector and another
	 * @param vx x-coordinate of the second vector
	 * @param vy y-coordinate of the second vector
	 * @return The Euclidean distance between two vectors
	 */
	public double dist(double vx, double vy){
		double dx = vx-x;
		double dy = vy-y;
		return Math.sqrt(dx*dx + dy*dy);
	}
	
	/**
	 * Calculates the squared Euclidean distance between this vector and another
	 * @param v Vector to find the distance to
	 * @return The squared Euclidean distance between two vectors
	 */
	public double dist2(Vector2 v){
		double dx = v.x-x;
		double dy = v.y-y;
		return dx*dx + dy*dy;
	}
	
	/**
	 * Calculates the squared Euclidean distance between this vector and another
	 * @param vx x-coordinate of the second vector
	 * @param vy y-coordinate of the second vector
	 * @return The squared Euclidean distance between two vectors
	 */
	public double dist2(double vx, double vy){
		double dx = vx-x;
		double dy = vy-y;
		return dx*dx + dy*dy;
	}
	
	/**
	 * Normalizes this vector, setting its magnitude to 1
	 * @return Normalized vector
	 */
	public Vector2 normalize(){
		double mag = mag();
		if(mag != 0) {
			x /= mag;
			y /= mag;
		}
		return this;
	}
	
	/**
	 * Reverses the direction of this vector
	 * @return A vector with reversed direction
	 */
	public Vector2 negate(){
		x = -x;
		y = -y;
		return this;
	}

	/**
	 * Adds another vector to this one
	 * @param v Vector to add
	 * @return Sum of the two vectors
	 */
	public Vector2 add(Vector2 v) {
		x += v.x;
		y += v.y;
		return this;
	}
	
	/**
	 * Adds another vector to this one
	 * @param vx x-coordinate of the second vector
	 * @param vy y-coordinate of the second vector
	 * @return Sum of the two vectors
	 */
	public Vector2 add(double vx, double vy){
		x += vx;
		y += vy;
		return this;
	}
	
	/**
	 * Subtracts another vector from this one
	 * @param v Vector to subtract
	 * @return Resulting vector of subtraction
	 */
	public Vector2 subtract(Vector2 v) {
		x -= v.x;
		y -= v.y;
		return this;
	}
	
	/**
	 * Subtracts another vector from this one
	 * @param vx x-coordinate of the second vector
	 * @param vy y-coordinate of the second vector
	 * @return Resulting vector of subtraction
	 */
	public Vector2 subtract(double vx, double vy){
		x -= vx;
		y -= vy;
		return this;
	}
	
	/**
	 * Multiplies this vector by a scalar value
	 * @param scalar Scalar value to multiply by
	 * @return Scaled vector
	 */
	public Vector2 scale(double scalar){
		x *= scalar;
		y *= scalar;
		return this;
	}
	
	/**
	 * Multiplies this vector by another
	 * @param v Vector to multiply by
	 * @return Product of the two vectors
	 */
	public Vector2 scale(Vector2 v){
		x *= v.x;
		y *= v.y;
		return this;
	}
	
	/**
	 * Multiplies this vector by another
	 * @param vx x-coordinate of the second vector
	 * @param vy y-coordinate of the second vector
	 * @return Product of the two vectors
	 */
	public Vector2 scale(double vx, double vy){
		x *= vx;
		y *= vy;
		return this;
	}
	
	/**
	 * Divides this vector by a scalar value
	 * @param scalar Scalar to divide by
	 * @return Quotient vector
	 */
	public Vector2 divide(double scalar){
		return scale(1/scalar);
	}
	
	/**
	 * Divides this vector by another
	 * @param v Vector to divide by
	 * @return Quotient of the two vectors
	 */
	public Vector2 divide(Vector2 v){
		return scale(1/v.x, 1/v.y);
	}
	
	/**
	 * Divides this vector by another
	 * @param vx x-coordinate of the second vector
	 * @param vy y-coordinate of the second vector
	 * @return Quotient of the two vectors
	 */
	public Vector2 divide(double vx, double vy){
		return scale(1/vx, 1/vy);
	}
	
	/**
	 * Calculates the cross product of two vectors
	 * @param v Vector to cross
	 * @return Cross product of two vectors
	 */
	public double cross(Vector2 v){
		return x*v.y - y*v.x;
	}
	
	/**
	 * Calculates the cross product of two vectors
	 * @param vx x-coordinate of the second vector
	 * @param vy y-coordinate of the second vector
	 * @return Cross product of two vectors
	 */
	public double cross(double vx, double vy){
		return x*vy - y*vx;
	}
	
	/**
	 * Limits the magnitude of this vector to the given length
	 * @param limit Maximum length
	 * @return Vector with limited magnitude
	 */
	public Vector2 limit(double limit){
		if(mag() > limit)
			normalize().scale(limit);
		return this;
	}
	
	/**
	 * Clamps the magnitude of this vector; forces the magnitude to be in the range [min, max]
	 * @param min Minimum magnitude
	 * @param max Maximum magnitude
	 * @return Clamped vector
	 */
	public Vector2 clamp(double min, double max){
		double mag = mag();
		if(mag > max)
			return normalize().scale(max);
		if(mag < min)
			return normalize().scale(min);
		return this;
	}
	
	/**
	 * Sets the angle of this vector to the given angle in degrees
	 * @param theta Angle in degrees
	 * @return Vector with the new angle
	 */
	public Vector2 setAngle(double theta){
		set(mag(), 0);
		rotate(theta);
		return this;
	}
	
	/**
	 * Rotates this vector by a given angle in degrees
	 * @param theta Angle in degrees to rotate by
	 * @return Rotated vector
	 */
	public Vector2 rotate(double theta){
		double rad = Math.toRadians(theta);
		double cos = Math.cos(rad);
		double sin = Math.sin(rad);
		
		double rx = x*cos - y*sin;
		double ry = y*cos + x*sin;
		
		x = rx;
		y = ry;
		
		return this;
	}
	
	@Override
	public boolean equals(Object o) {
		Vector2 v = (Vector2) o;
		return x == v.x && y == v.y;
	}
	
	@Override
	public String toString() {
		return "[" + x + " : " + y + "]";
	}
}