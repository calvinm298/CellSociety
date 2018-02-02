/**
 * 
 */
package util;

import java.awt.Point;
import java.util.ArrayList;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author August Ning
 * Specific parser used for Conway's Game of Life simulation
 */
public class ConwayParser extends XMLParser {
	private ArrayList<Point> cellLocations;
	public ConwayParser(String file) {
		super(file);
		parseCells();
	}
	public void parseCells() {
		NodeList cellList = this.getDoc().getElementsByTagName("block");
		cellLocations = new ArrayList<>();
		for (int i = 0; i < cellList.getLength(); i++) {
			Node cell = cellList.item(i);
			if (cell.getNodeType() == Node.ELEMENT_NODE) {
				Element cellinfo = (Element) cell;
				int xLoc = Integer.parseInt(cellinfo.getElementsByTagName("loc_x").item(0).getTextContent());
				int yLoc = Integer.parseInt(cellinfo.getElementsByTagName("loc_y").item(0).getTextContent());
				cellLocations.add(new Point(xLoc, yLoc));
			}
		}
	}
	
	public void printCells() {
		for (Point p : cellLocations) {
			System.out.println("x: "+ p.getX());
			System.out.println("y: "+ p.getY());
		}
	}
	public ArrayList<Point> getCells() {
		return this.cellLocations;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ConwayParser parser = new ConwayParser("data\\XMLFiles\\ctest1.xml");
		parser.printCells();
	}

}
