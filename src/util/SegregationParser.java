package util;

public class SegregationParser extends XMLParser {
	private double similarPercentage;
	public SegregationParser(String file) {
		super(file);
		this.parseGameConstants();
		// TODO Auto-generated constructor stub
	}
	public void parseGameConstants() {
		this.similarPercentage = Double.parseDouble(this.getDoc().getElementsByTagName("similar").item(0).getTextContent());
	}
	public double getSimilar() {
		return this.similarPercentage;
	}
	public static void main(String[] args) {
		SegregationParser parser = new SegregationParser("data\\XMLFiles\\stest1.xml");
		parser.printCells();
		System.out.println(parser.getSimilar());
	}

}
