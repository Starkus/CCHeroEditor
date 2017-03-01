package net.starkus.cceditor.util;

public class MathUtils {
	
	public static float map(float value, 
			float fromLow, float fromHigh, 
			float toLow, float toHigh) {
		
		return (value - fromLow) * (toHigh - toLow) / (fromHigh - fromLow) + toLow;
	}

	public static float clamp01(float value) {
		
		return Math.min(1, Math.max(0, value));
	}
}
