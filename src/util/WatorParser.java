package util;


/**
 * @author August Ning
 * Specific parser used for Wa-Tor World
 *
 */
public class WatorParser extends XMLParser {
	private int maxChronCount;
	private int sharkStartingEnergy;
	private int sharkMaxEnergy;
	private int fishEnergy;
	public WatorParser(String file) {
		super(file);
		this.parseGameConstants();
		// TODO Auto-generated constructor stub
	}
	@Override
	public void parseGameConstants() {
		try {
		this.maxChronCount = (Integer.parseInt(this.getDoc().getElementsByTagName("max_chron_count").item(0).getTextContent()));
		this.sharkStartingEnergy = (Integer.parseInt(this.getDoc().getElementsByTagName("shark_starting_energy").item(0).getTextContent()));
		this.sharkMaxEnergy = Integer.parseInt(this.getDoc().getElementsByTagName("shark_max_energy").item(0).getTextContent());
		this.fishEnergy = Integer.parseInt(this.getDoc().getElementsByTagName("fish_energy").item(0).getTextContent());
		} catch (NullPointerException e) {
			return;
		}
		}
	public int getMaxChronCount() {
		return this.maxChronCount;
	}
	public int getSharkStartingEnergy() {
		return this.sharkStartingEnergy;
	}
	public int getSharkMaxEnergy() {
		return this.sharkMaxEnergy;
	}
	public int getFishEnergy() {
		return this.fishEnergy;
	}
	public static void main (String[] args) {
		WatorParser parser = new WatorParser("data\\XMLFiles\\wtest1.xml");
		parser.printCells();
	}
}
