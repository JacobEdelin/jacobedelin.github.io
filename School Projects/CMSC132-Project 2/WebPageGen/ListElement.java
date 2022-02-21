package model;

import java.util.ArrayList;

/**
 * Represents the &lt;ul&gt and the &lt;ol&gt; tags.
 * An ArrayList is used to keep track of the Element objects of the list.
 * @author UMCP
 *
 */
public class ListElement extends TagElement {
	private ArrayList<Element> items;
	
	public ListElement(boolean ordered, String attributes) {
		super((ordered == true) ? "ol" : "ul", true, null, attributes);
		items = new ArrayList<Element>();
	}
	
	public void addItem(Element item) {
		items.add(item);
	}
	
	public String genHTML(int indentation) {
		String html = "";
		String indent = Utilities.spaces(indentation);
		html += indent + getStartTag() + "\n";

		for (Element elements : items) {
			html += indent + Utilities.spaces(Utilities.DEFAULT_INDENTATION) + "<li>" + "\n" + indent;
			html += Utilities.spaces(Utilities.DEFAULT_INDENTATION) + Utilities.spaces(Utilities.DEFAULT_INDENTATION) + elements.genHTML(0) + "\n";
			html += indent + Utilities.spaces(Utilities.DEFAULT_INDENTATION) + "</li>" + "\n";
		}

		html += indent + getEndTag();

		return html;

	}

}
