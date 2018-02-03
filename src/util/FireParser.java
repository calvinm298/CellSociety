package util;

/**
 * @author August Ning Specific parser used for Spreading Fire
 */
public class FireParser extends XMLParser {
	private double probCatch;

	public FireParser(String file) {
		super(file);
		this.parseGameConstants();
		}
	public void parseGameConstants() {
		this.probCatch = Double.parseDouble(this.getDoc().getElementsByTagName("probcatch").item(0).getTextContent());
	}
	public double getProbCatch() {
		return this.probCatch;
	}
	public static void main(String[] args) {
		FireParser parser = new FireParser("data\\XMLFiles\\ftest1.xml");
		parser.printCells();
	}

}
