/**
 * 
 */
package util;

//import java.awt.Point;
//import java.util.ArrayList;
//
//import org.w3c.dom.Element;
//import org.w3c.dom.Node;
//import org.w3c.dom.NodeList;

/**
 * @author August Ning
 * Specific parser used for Conway's Game of Life simulation
 */
public class ConwayParser extends XMLParser {
//	private ArrayList<Point> cellLocationsArray;
	public ConwayParser(String file) {
		super(file);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ConwayParser parser = new ConwayParser("data\\XMLFiles\\ctest1.xml");
		parser.printCells();
	}

}
