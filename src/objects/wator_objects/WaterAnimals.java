package objects.wator_objects;

import java.awt.Point;

public class WaterAnimals {

	protected String animalString = "Water";
	protected Point nextLocation;
	protected int MaxChrononCount;
	protected int CurrChrononCount;

	public WaterAnimals() {

	}

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

		return this.CurrChrononCount;
	}

	public void setChrononCount(int i) {
		this.CurrChrononCount = i;
	}

	public int getMaxChrononCount() {
		return this.MaxChrononCount;
	}

}
