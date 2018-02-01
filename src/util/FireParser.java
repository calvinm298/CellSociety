package util;

import java.awt.Point;
import java.util.*;

/**
 * @author August Ning
 * Specific parser used for Spreading Fire
 */
public class FireParser extends XMLParser {
	private double probCatch;
	private HashMap<String, ArrayList<Point>> cellLocations;
	public FireParser(String file) {
		super(file);
		parseProbCatch();
		parseCells();
	}
	public void parseProbCatch() {
		this.probCatch = Double.parseDouble(this.getDoc().getElementsByTagName("probcatch").item(0).getTextContent());
	}
	public void parseCells() {
		
	}

}
