package com.example.coverflow;
//For some global values
public class GlobalPrefs {
	
	static int density;
	static int width;
	int height;
	

	public static int getDensity() {
		// TODO Auto-generated method stub
		return density;
	}

	public static float getScreenWidth() {
		// TODO Auto-generated method stub
		return width;
	}

	public static void setDensity(float dense) {
		// TODO Auto-generated method stub
		density = (int)dense;
	}

	public static void setScreenWidth(int widthPixels) {
		// TODO Auto-generated method stub
		width = widthPixels;
	}
	
	

}
