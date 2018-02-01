package util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @author August Sorts files by type of simulations
 */
public class FileSorter {
	private File directory;
	private File[] files;
	private HashMap<String, ArrayList<String>> sortedFiles;

	public FileSorter(String location) {
		directory = new File(location);
		files = directory.listFiles();
		sortFiles();
	}

	public void sortFiles() {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		sortedFiles = new HashMap<>();
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			for (File f : files) {
				Document doc = dBuilder.parse(f);
				doc.getDocumentElement().normalize();
				NodeList type = doc.getElementsByTagName("type");
				String simType = type.item(0).getTextContent();
				if (!sortedFiles.containsKey(simType)) {
					sortedFiles.put(simType, new ArrayList<String>());
				}
				ArrayList<String> getArray = sortedFiles.get(simType);
				getArray.add(f.getPath());
				sortedFiles.put(simType, getArray);
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void printMap() {
		for (String s : sortedFiles.keySet()) {
			System.out.println(s);
			for (String t : sortedFiles.get(s)) {
				System.out.println(t);
			}
		}
	}

	public static void main(String[] args) {
		FileSorter f = new FileSorter("data/XMLFiles");
		f.printMap();
	}
}
