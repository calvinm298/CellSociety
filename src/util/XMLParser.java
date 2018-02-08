package util;

import java.awt.Point;
import java.io.IOException;
import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @author August Ning
 * Super class that will initiate the XMLparser for each type of simulation
 */
public class XMLParser {
	private String filename;
	private String type;
	private String title;
	private String author;
	private String cellShape;
	private String consideredNeighbors;
	private boolean showGrid;
	private int sizeX;
	private int sizeY;
	private Document doc;
	private HashMap<String, ArrayList<Point>> cellLocations;
	
	public XMLParser(String file) {
		this.filename = file;
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(file);
			doc.getDocumentElement().normalize();
			parseSettings(doc);
			this.parseCells();
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			throw new IllegalArgumentException();
		}

	}
	
	public void parseSettings(Document doc) {
		try {
		this.type = doc.getElementsByTagName("type").item(0).getTextContent();
		this.title = doc.getElementsByTagName("title").item(0).getTextContent();
		this.author = doc.getElementsByTagName("author").item(0).getTextContent();
		this.sizeX = Integer.parseInt(doc.getElementsByTagName("size_x").item(0).getTextContent());
		this.sizeY = Integer.parseInt(doc.getElementsByTagName("size_y").item(0).getTextContent());
		this.cellShape = doc.getElementsByTagName("shape").item(0).getTextContent();
		this.consideredNeighbors = doc.getElementsByTagName("considered_neighbors").item(0).getTextContent();
		this.showGrid = Boolean.parseBoolean(doc.getElementsByTagName("considered_neighbors").item(0).getTextContent());
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}
	}
	public void parseCells() {
		NodeList cellList = this.getDoc().getElementsByTagName("block");
		cellLocations = new HashMap<>();
		for (int i = 0; i < cellList.getLength(); i++) {
			Node cell = cellList.item(i);
			if (cell.getNodeType() == Node.ELEMENT_NODE) {
				Element cellinfo = (Element) cell;
				String cellClass = cellinfo.getElementsByTagName("class").item(0).getTextContent();
				int xLoc = Integer.parseInt(cellinfo.getElementsByTagName("loc_x").item(0).getTextContent());
				int yLoc = Integer.parseInt(cellinfo.getElementsByTagName("loc_y").item(0).getTextContent());
				if (!cellLocations.containsKey(cellClass)) {
					cellLocations.put(cellClass, new ArrayList<Point>());
				}
				ArrayList<Point> getArray = cellLocations.get(cellClass);
				getArray.add(new Point(xLoc, yLoc));
				cellLocations.put(cellClass, getArray);
			}
		}
	}
	public void printCells() {
		for (String s : this.cellLocations.keySet()) {
			System.out.println("Cell Type: " + s);
			for (Point p : this.cellLocations.get(s)) {
				System.out.println("x: " + p.getX());
				System.out.println("y: " + p.getY());
			}
		}
	}
	public List<Point> getCells(String cellclass) {
		if (!this.cellLocations.containsKey(cellclass)) throw new IllegalArgumentException();
		return this.cellLocations.get(cellclass);
	}
	public String getFileName() {
		return this.filename;
	}
	public String getType() {
		return this.type;
	}
	public String getTitle() {
		return this.title;
	}
	public String getAuthor() {
		return this.author;
	}
	
	public int getSizeX() {
		return this.sizeX;
	}
	public int getSizeY() {
		return this.sizeY;
	}
	public Document getDoc() {
		return this.doc;
	}
	public void parseGameConstants() {}
	public static void main(String[] args) {
		XMLParser parser = new XMLParser("data\\XMLFiles\\wtest1.xml");
		System.out.println(parser.getType());
		System.out.println(parser.getTitle());
		System.out.println(parser.getAuthor());
		System.out.println(parser.getSizeX());
		System.out.println(parser.getSizeY());
	}
}
