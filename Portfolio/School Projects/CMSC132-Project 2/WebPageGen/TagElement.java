package model;

/**
 * 
 * Represents an HTML tag element (<&lt;p&gt;, &lt;ul&gt;, etc.).
 * Each tag has an id (ids start at 1).  By default the start tag
 * will have an id (e.g., <&lt;p id="a1"&gt;&lt;/p&gt;) when
 * the HTML for the tag is generated.  This can be disabled by
 * using enableId.
 * @author UMCP
 *
 */
public class TagElement implements Element {
	private String tagName, attributes;
	private boolean endTag;
	private Element content;
	private int id;
	
	private static int uniqueId = 0;
	private static boolean choices;

	
	public TagElement(String tagName, boolean endTag, Element content, String attributes) {			
		if (choices == true) {
			this.id = ++uniqueId;
		}
			this.tagName = tagName;
			this.content = content;
			this.attributes = attributes;
			this.endTag = endTag;
	}
	
	public int getId() {
		return id;
	}
	
	public String getStringId() {
		return tagName + id;
	}
	
	public String getStartTag() {
		String startTag = "<" + tagName;
		if (choices) {
			startTag += " id=\"" + tagName + id + "\"";		
		}
		if (attributes == null) {
			startTag += ">";
		} else {
			startTag += attributes + ">";
		}

		return startTag;
	}
	
	public String getEndTag() {
		if (endTag == true) {
			return "</" + tagName + ">";
		} else {
			return "";
		}
	}
	
	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}
	
	public static void resetIds() {
		uniqueId = 0;
	}
	
	public static void enableId(boolean choice) {
		choices = choice;
	}

	@Override
	public String genHTML(int indentation) {
		String indent = Utilities.spaces(indentation);
		String html = "";
		html += indent + getStartTag();
		if (content != null) {
			html += content.genHTML(0) + getEndTag();
		} 
		return html;
	}
}