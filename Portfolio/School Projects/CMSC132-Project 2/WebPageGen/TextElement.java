package model;

/**
 * 
 * Represents text that can appear in an HTML document
 * @author UMCP
 *
 */
public class TextElement implements Element {
	private String text;
	
	public TextElement(String text) {
		this.text = text;
	}
	
	@Override
	public String genHTML(int indentation) {
		String indent = Utilities.spaces(indentation);
		return indent + text;
	}
	
}
