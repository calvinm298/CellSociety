package util;

public class SegregationParser extends XMLParser {
	private double similarPercentage;
	private double bluePercentage;
	private double redPercentage;
	public SegregationParser(String file) {
		super(file);
		this.parseGameConstants();
		// TODO Auto-generated constructor stub
	}
	@Override
	public void parseGameConstants() {
		try {
		this.similarPercentage = Double.parseDouble(this.getDoc().getElementsByTagName("similar").item(0).getTextContent());
		this.bluePercentage = Double.parseDouble(this.getDoc().getElementsByTagName("blue_percent").item(0).getTextContent());
		this.redPercentage = Double.parseDouble(this.getDoc().getElementsByTagName("red_percent").item(0).getTextContent());
		} catch (NullPointerException e) {
			throw new IllegalArgumentException();
		}
		}
		
	public double getSimilar() {
		return this.similarPercentage;
	}
	public double getBluePercentage() {
		return this.bluePercentage;
	}
	public double getRedPercentage() {
		return this.redPercentage;
	}
	public static void main(String[] args) {
		SegregationParser parser = new SegregationParser("data\\XMLFiles\\stest1.xml");
		parser.printCells();
		System.out.println(parser.getSimilar());
	}

}
