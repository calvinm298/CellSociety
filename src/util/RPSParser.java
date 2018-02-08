package util;

/**
 * @author August Ning
 *
 */
public class RPSParser extends XMLParser {
	private double probRock;
	private double probPaper;
	private double probScissors;

	public RPSParser(String file) {
		super(file);
		// TODO Auto-generated constructor stub
		this.parseGameConstants();
	}

	public void parseGameConstants() {
		try {
			this.probRock = Double
					.parseDouble(this.getDoc().getElementsByTagName("prob_rock").item(0).getTextContent());
			this.probPaper = Double
					.parseDouble(this.getDoc().getElementsByTagName("prob_paper").item(0).getTextContent());
			this.probScissors = Double
					.parseDouble(this.getDoc().getElementsByTagName("prob_scissors").item(0).getTextContent());
		} catch (NullPointerException e) {
			throw new IllegalArgumentException();
		}
	}
	public double getProbRock() {
		return this.probRock;
	}
	public double getProbPaper() {
		return this.probPaper;
	}
	public double getProbScissors() {
		return this.probScissors;
	}

}
