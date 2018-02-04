package objects.wator_objects;

import java.awt.Point;

public class WaterAnimals {
	
	protected static String animalString = null;
	protected Point nextLocation = new Point(-1, -1);
	protected int MaxChrononCount;
	protected int CurrChrononCount;
	
	
	public String getAnimalTypeString() {
		return this.animalString;
	}


	public void setNextLocation(int x, int y) {
		this.nextLocation.setLocation(x, y);
		
	}


	public Point getNextLocation() {
		return this.nextLocation;
	}


	public int getChrononCount() {
		// TODO Auto-generated method stub
		return 0;
	}


	public void setChrononCount(int i) {
		// TODO Auto-generated method stub
		
	}


	public int getMaxChrononCount() {
		// TODO Auto-generated method stub
		return 0;
	}


	
	
	
}
