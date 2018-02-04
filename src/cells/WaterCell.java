package cells;

import objects.wator_objects.WaterAnimals;
import objects.wator_objects.Fish;

/**
 * Subclass for WatorAnimal Objects - will take into consideration the two objects (Sharks and Fish) and the interactions
 * between cells depending on the objects they have
 *   
 * @author Calvin Ma
 */


public class WaterCell extends Cell {

	private static WaterAnimals animalType = null;
	private static boolean willBeTakenByFish = false;
	private static boolean willBeTakenByShark = false;
	
	/**
	 * This is a constructor to create a WaterCell. Each Watercell will contain the water
	 * animal type and what type of animal it will be taken in the next evolution. 
	 * @param animalType
	 * @param willBeTakenByFish
	 * @param willBeTakenByShark
	 */
	
	public WaterCell (WaterAnimals animalType, boolean willBeTakenByFish, boolean willBeTakenByShark) {
		this.animalType = animalType;
		this.willBeTakenByFish = willBeTakenByFish;
		this.willBeTakenByShark = willBeTakenByShark;
	}
	
	/**Checks to see if this cell will be taken by a fish in the next evolution
	 */
	public void markWillBeTakenByFish() {
		this.willBeTakenByFish = true;
	}
	/**Checks to see if this cell will be taken by a shark in the next evolution
	 */
	public void markWillBeTakenByShark() {
		this.willBeTakenByShark = true;
	}
	
	public WaterAnimals getAnimal() {
		return this.animalType; //probably add a private instance string of some sort for comparing purposes
	}
	
	public void setAnimal(WaterAnimals animal) {
		this.animalType = animal; //probably add a private instance string of some sort for comparing purposes
	}
	
	public boolean getWillBeTakenByFish() {
		return this.willBeTakenByFish == true;
	}
	
	public boolean getWillBeTakenByShark() {
		return this.willBeTakenByShark == true;
	}
	
	public boolean isTakenByFish() {
		return this.animalType.getAnimalTypeString().equals("Fish");
	}
	
	public boolean isTakenByShark() {
		return this.animalType.getAnimalTypeString().equals("Shark");
	}

	
}
