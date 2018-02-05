package objects.wator_objects;

import java.awt.Point;

public class WaterAnimals {

	protected String animalString = "Water";
	protected Point nextLocation = new Point(6, 6);
	protected int MaxChrononCount = 5;
	protected int CurrChrononCount = 5;

	public WaterAnimals() {

	}

	public String getAnimalTypeString() {
		return this.animalString;
	}

	public void setNextLocation(int x, int y) {
		this.nextLocation = new Point(x, y);
	}

	public Point getNextLocation() {
		return this.nextLocation;
	}

	public int getChrononCount() {

		return this.CurrChrononCount;
	}

	public void setChrononCount(int i) {
		this.CurrChrononCount = i;
	}

	public int getMaxChrononCount() {
		return this.MaxChrononCount;
	}

	public void setCurrentLocation(int i, int j) {
		// TODO Auto-generated method stub
		
	}

}
