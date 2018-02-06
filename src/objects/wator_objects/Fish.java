package objects.wator_objects;

/**
 * This class will provide the necessary methods that the game class and cell class will 
 * @author Calvin 
 */
public class Fish extends WaterAnimals {

	private final int giveEnergy;
	
	public Fish(int maxChrononCount, int givenEnergy) {
		animalString = "Fish";
		CurrChrononCount = 0;
		MaxChrononCount = maxChrononCount;
		this.giveEnergy = givenEnergy;
	}
	
	public int giveEnergy() {
		return this.giveEnergy;
	}


	
}
