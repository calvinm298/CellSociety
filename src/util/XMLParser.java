package util;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
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
	private int sizeX;
	private int sizeY;
	private Document doc;
	
	public XMLParser(String file) {
		this.filename = file;
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(file);
			doc.getDocumentElement().normalize();
			parseSettings(doc);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void parseSettings(Document doc) {
		this.type = doc.getElementsByTagName("type").item(0).getTextContent();
		this.title = doc.getElementsByTagName("title").item(0).getTextContent();
		this.author = doc.getElementsByTagName("author").item(0).getTextContent();
		this.sizeX = Integer.parseInt(doc.getElementsByTagName("size_x").item(0).getTextContent());
		this.sizeY = Integer.parseInt(doc.getElementsByTagName("size_y").item(0).getTextContent());
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
	public static void main(String[] args) {
		XMLParser parser = new XMLParser("data\\XMLFiles\\ctest2.xml");
		System.out.println(parser.getType());
		System.out.println(parser.getTitle());
		System.out.println(parser.getAuthor());
		System.out.println(parser.getSizeX());
		System.out.println(parser.getSizeY());
	}
}
