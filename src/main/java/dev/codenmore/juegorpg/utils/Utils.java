package main.java.dev.codenmore.juegorpg.utils;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Utils {
	
	public static String loadFileAsString(String path){
		StringBuilder builder = new StringBuilder();
		
		try{
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line;
			while((line = br.readLine()) != null)
				builder.append(line + "\n");
			
			br.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return builder.toString();
	}
	
	public static int parseInt(String number){
		try {
			return Integer.parseInt(number);
		}catch(NumberFormatException e){
			e.printStackTrace();
			return 0;
		}
	}

    public static int toIndex(int x, int y, int width) {
        return x + y * width;
    }

    public static Direction getStraightDirection(Vector2D vec) {
        double rads = Math.atan2(vec.getY(), vec.getX());
        double degs = rads * 180 / Math.PI;
        degs = Math.floorMod((int) degs, 360);

        if (degs >= 360 - 45 || degs < 45)
            return Direction.RIGHT;
        else if (degs < 135)
            return Direction.DOWN;
        else if (degs < 225)
            return Direction.LEFT;
        else
            return Direction.UP;
    }
}
