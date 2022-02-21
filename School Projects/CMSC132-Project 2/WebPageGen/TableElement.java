package model;

/**
 * Represents the &lt;table&gt tag. A two dimensional array is used to keep
 * track of the Element objects of table.
 * 
 * @author UMCP
 *
 */
public class TableElement extends TagElement {
	private Element[][] items;

	private int rows, cols;

	public TableElement(int rows, int cols, String attributes) {
		super("table", true, null, attributes);
		this.rows = rows;
		this.cols = cols;
		items = new Element[rows][cols];
	}

	public void addItem(int rowIndex, int colIndex, Element item) {
				items[rowIndex][colIndex] = item;
			}
		
	public double getTableUtilization() {
		int usedCells = 0;
		for (int row = 0; row < rows - 1; row++) {
			for (int col = 0; col < cols + 1; col++) {
					usedCells++;
				}
			}
		return ((usedCells * 100.0) / (rows * cols));
	}

	@Override
	public String genHTML(int indentation) {
		String html = "";
		String indent = Utilities.spaces(indentation);
		html += indent + getStartTag() + "\n";
	
		for (Element[] row : items) {
			html += indent + Utilities.spaces(Utilities.DEFAULT_INDENTATION) + "<tr>";
			for (Element col : row) {
				if (col != null) {
					html += "<td>" + col.genHTML(0) + "</td>";
				} else {
					html += "<td></td>";
				}
			}
			html += "</tr>" + "\n";
		}
	
		html += indent + getEndTag();
		return html;
	}
}