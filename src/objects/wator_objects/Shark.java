package objects.wator_objects;

public class Shark extends WaterAnimals {
	
	
	private int Energy;
	private int MaxEnergy;
	
	
	
	public Shark (int maxEnergy, int startEnergy, int maxChrononCount) {
		animalString = "Shark";
		this.Energy = startEnergy;
		this.MaxEnergy = maxEnergy;
		this.MaxChrononCount = maxChrononCount;
		CurrChrononCount = 0;
		
	}
	
	public int getEnergy() {
		return this.Energy;
	}

	public void changeEnergy(int i) {
		this.Energy = this.Energy + i;
	}
	
	public int getMaxEnergy() {
		return this.MaxEnergy;
	}
	
	public void setEnergy(int i) {
		this.Energy = i;
	}

	public boolean checkLivingEnergy() {
		return Energy > 0;
	}

}
