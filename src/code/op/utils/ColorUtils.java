package code.op.utils;

import org.bukkit.Color;

public class ColorUtils {

	public final static int BLACK = 1644825;
	public final static int RED = 13388876;
	public final static int GREEN = 8375321;
	public final static int BLUE = 3368652;
	public final static int WHITE = 16777215;
	public final static int PURPLE = 13421772;
	public final static int TEAL = 52394;
	public final static int PINK = 14357690;
	public final static int ORANGE = 16737843;
	public final static int YELLOW = 16776960;
	
	public static Color getColorByName(String name)
	{
		if(name.equalsIgnoreCase("Black"))
			return Color.fromRGB(BLACK);
		else if(name.equalsIgnoreCase("Red"))
			return Color.fromRGB(RED);
		else if(name.equalsIgnoreCase("Green"))
			return Color.fromRGB(GREEN);
		else if(name.equalsIgnoreCase("Blue"))
			return Color.fromRGB(BLUE);
		else if(name.equalsIgnoreCase("White"))
			return Color.fromRGB(WHITE);
		else if(name.equalsIgnoreCase("Purple"))
			return Color.fromRGB(PURPLE);
		else if(name.equalsIgnoreCase("Teal"))
			return Color.fromRGB(TEAL);
		else if(name.equalsIgnoreCase("Pink"))
			return Color.fromRGB(PINK);
		else if(name.equalsIgnoreCase("Orange"))
			return Color.fromRGB(ORANGE);
		else if(name.equalsIgnoreCase("Yellow"))
			return Color.fromRGB(YELLOW);
		else
			return null;
	}
	
}
