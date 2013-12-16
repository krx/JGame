package jgame.util;

/**
 * Provides some math utility functions
 * @author Kareem El-Faramawi
 *
 */
public class MathUtils {
	
	/**
	 * Forces a value to be within a specified range
	 * @param value Value to be clamped
	 * @param min Minimum value of range
	 * @param max Maximum value of range
	 * @return 	If value > max, max is returned <br>
	 * 			If value < min, min is returned <br>
	 * 			If min <= value <= max, value is returned.
	 */
	public static double clamp(double value, double min, double max) {
		return Math.max(min, Math.min(max, value));
	}
	
	/**
	 * Forces an integer value to be within a specified range
	 * @param value Integer value to be clamped
	 * @param min Minimum value of range
	 * @param max Maximum value of range
	 * @return 	If value > max, max is returned <br>
	 * 			If value < min, min is returned <br>
	 * 			If min <= value <= max, value is returned.
	 */
	public static int clampi(int value, int min, int max) {
		return Math.max(min, Math.min(max, value));
	}
	
	/**
	 * Linear interpolation - Calculates a value between the 1st and 2nd values based on a percentage int the range [0,1]
	 * @param val1 First value
	 * @param val2 Second value
	 * @param percent Percentage
	 * @return Interpolated value
	 */
	public static double lerp(double val1, double val2, double percent) {
		return val1 + (val2 - val1) * percent;
	}
}