package model;

import java.util.ArrayList;

/**
 * 
 * Represents a paragraph (&lt;p&gt;) tag.  It relies on an
 * ArrayList in order to keep track of the set of Element objects
 * that are part of the paragraph.
 * @author UMCP
 *
 */
public class ParagraphElement extends TagElement {
	private ArrayList<Element> items;
	
	public ParagraphElement(String attributes) {
		super("p", true, null, attributes);
		items = new ArrayList<Element>();
	}
	
	public void addItem(Element item) {
		items.add(item);
	}
	
	@Override
	public String genHTML(int indentation) {
	String html = "";
	String indent = Utilities.spaces(indentation);
	html += indent + getStartTag() + "\n";
	
	for (Element elements: items) {
		html += indent + Utilities.spaces(Utilities.DEFAULT_INDENTATION) + elements.genHTML(0) + "\n";
	}
	html += indent + getEndTag();
	
	return html;
	}
}