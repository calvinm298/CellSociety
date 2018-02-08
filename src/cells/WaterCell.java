package cells;

import objects.wator_objects.WaterAnimals;
import objects.wator_objects.Fish;
import objects.wator_objects.Shark;

/**
 * Subclass for WatorAnimal Objects - will take into consideration the two objects (Sharks and Fish) and the interactions
 * between cells depending on the objects they have
 *   
 * @author Calvin Ma
 */


public class WaterCell extends Cell {
	
	private String animalTypeString = "Water";
	private WaterAnimals animalType = null;
	private boolean willBeTakenByFish = false;
	private boolean willBeTakenByShark = false;
	
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
		if(animalType != null)
			this.animalTypeString = animalType.getAnimalTypeString();
	}
	
	public WaterCell() {
		
	}
	
	public String getAnimalTypeString() {
		return this.animalTypeString;
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
	
	public void setFish(Fish fish) {
		this.animalType = fish; //probably add a private instance string of some sort for comparing purposes
		this.animalTypeString = "Fish";
	}
	
	public void setShark(Shark shark) {
		this.animalType = shark; //probably add a private instance string of some sort for comparing purposes
		this.animalTypeString = "Shark";
	}
	
	public boolean getWillBeTakenByFish() {
		return this.willBeTakenByFish;
	}
	
	public boolean getWillBeTakenByShark() {
		return this.willBeTakenByShark;
	}
	
	public boolean isTakenByFish() {
		return this.getAnimalTypeString().equals("Fish");
	}
	
	public boolean isTakenByShark() {
		return this.getAnimalTypeString().equals("Shark");
	}

	public void reset() {
		this.willBeTakenByFish = false;
		this.willBeTakenByFish = false;
		this.animalType = null;
	}

	public void setasFish() {
		this.animalType = new Fish(0, 0);
		this.animalTypeString = "Fish";
	}	
}