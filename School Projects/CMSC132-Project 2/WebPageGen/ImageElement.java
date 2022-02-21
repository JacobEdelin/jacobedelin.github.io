package model;

/**
 * Represents an &lt;img&gt; tag.  For this project
 * you can assume we will not update any of the attributes associated with
 * this tag.
 * @author UMCP
 *
 */
public class ImageElement extends TagElement {
	private String imageURL;
	
	public ImageElement(String imageURL, int width, int height, String alt, String attributes) {
		super("img", false, null, null);
		String attr;
		this.imageURL = imageURL;
		attr =  " src=\"" + this.imageURL + "\" "; 
		attr += "width=\"" + width + "\""; 
		attr += " height=\"" + height + "\" ";
		attr += "alt=\"" + alt + "\"";
		attr += ((attributes == null) ? "" : attributes);
		
		this.setAttributes(attr);
	}
	
	public String getImageURL() {
		return imageURL;
	}
}
