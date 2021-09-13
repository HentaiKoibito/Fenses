package model;

import java.io.Serializable;

public class Billboard implements Serializable{
	private static final long serialVersionUID = 1L;
	private double height;
	private double width;
	private boolean inUse;
	private String brand;
	
	public Billboard(double w, double h, boolean iU, String b) {
		width = w;
		height = h;
		inUse = iU;
		brand = b;
	}
	
	public double calculateArea(double h, double w) {
		double area = h*w;
		return area;
	}

	public double getHeight() {
		return height;
	}

	public double getWidth() {
		return width;
	}

	public boolean getInUse() {
		return inUse;
	}

	public String getBrand() {
		return brand;
	}
	
}