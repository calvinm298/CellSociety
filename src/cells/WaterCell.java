package cells;

import objects.wator_objects.WaterAnimals;

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
		return this.animalType;
	}
	
	
}
