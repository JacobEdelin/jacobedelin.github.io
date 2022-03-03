package model;

import java.util.*;

/**
 * Represents a web page.  Web page elements are
 * stored in an ArrayList of Element objects.  A title
 * is associated with every page.  This class implements
 * the Comparable interface.  Pages will be compared
 * based on the title.
 * @author UMCP
 *
 */
public class WebPage implements Comparable<WebPage> {
	private ArrayList<Element> elements;
	private String title;
	
	private int listCount, paragraphCount, tableCount;
	
	private static boolean choices; //Only used for JUnit tests
	
	public WebPage(String title) {
		this.title = title;
		elements = new ArrayList<Element>();
	}
	
	public int addElement(Element element) {
		elements.add(element);
		
		if(element instanceof TagElement == true) {
			return ((TagElement)element).getId();
		} else {
			return -1;
		}
	}
	
	public String getWebPageHTML(int indentation) {
		String html = "";
		String indent = Utilities.spaces(indentation);

		html += "<!doctype html>" + "\n";
		html += "<html>" + "\n";
		html += indent + "<head>" + "\n" + indent;
		html += indent + "<meta charset=\"utf-8\"/>" + "\n" + indent;
		html += indent + "<title>" + title + "</title>" + "\n";
		html += indent + "</head>" + "\n";
		html += indent + "<body>" + "\n";
		for (Element elem : elements) {
			if (elem instanceof HeadingElement == true) {
				html += indent + elem.genHTML(0) + "\n";
			} else if (elem instanceof ParagraphElement == true) {
				html += indent + elem.genHTML(0) + "\n";
				paragraphCount++;
			} else if (elem instanceof TableElement == true) {
				html += indent + elem.genHTML(0) + "\n";
				tableCount++;
			} else if (elem instanceof ListElement == true) {
				html += indent + elem.genHTML(0) + "\n";
				listCount++;
			}
		}
	
		html += indent + "</body>" + "\n";
		html += "</html>";

		return html;
	}
	
	public void writeToFile(String filename, int indentation) {
		Utilities.writeToFile(filename += ".html", getWebPageHTML(0));
	}
	
	public Element findElem(int id) {
		return (Element)elements;
	}
	
	public String stats() {
		String stats = "";
		double utilNum = 0.0;
		stats += "List Count: " + listCount + "\n";
		stats += "Paragraph Count: " + paragraphCount + "\n";
		stats += "Table Count: " + tableCount + "\n";
		for (Element utilElem : elements) {
			if (utilElem instanceof TableElement == true) {
				utilNum = ((TableElement)utilElem).getTableUtilization();
			}
		}
		stats += "TableElement Utilization: " + utilNum;
	
		return stats;
	}
	
	public int compareTo(WebPage webPage) {
		return title.compareTo(webPage.title);
	}
	
	public static void enableId(boolean choice) {
		choices = choice;
	}
}