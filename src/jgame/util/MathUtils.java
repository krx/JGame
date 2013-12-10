package jgame.util;

public class MathUtils {
	public static double clamp(double value, double min, double max) {
		return Math.max(min, Math.min(max, value));
	}
	
	public static int clampi(int value, int min, int max) {
		return Math.max(min, Math.min(max, value));
	}
	
	public static double lerp(double val1, double val2, double percent) {
		return val1 + (val2 - val1) * percent;
	}
}