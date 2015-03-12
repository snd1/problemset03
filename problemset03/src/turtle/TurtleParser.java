package turtle;

import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class TurtleParser extends Component {
	
	public TurtleParser() {
		
	}

	public String[] parseIt(String oldstrg) {
		// First split string
		String[] lines = oldstrg.split(System.getProperty("line.separator"));

		
		String string = lines[lines.length-1];
		String[] nwstrg = new String[2];
		String cmd = null;
		char charone;
		char chartwo;
		char charthree;
		charone = string.charAt(string.length()-1);
		chartwo = string.charAt(string.length()-2);
		charthree = string.charAt(string.length()-3);
		
		if ( Character.isWhitespace(charone) ) {
			throw new ParserException();
		}	else if ( Character.isWhitespace(chartwo)) {
			nwstrg[1] = Character.toString(charone);
		} 	else if ( Character.isWhitespace(charthree)) {
			nwstrg[1] = Character.toString(chartwo) + Character.toString(charone);
		}
		
		if ( string.contains("right")) {
			nwstrg[0] = "right";
			
		}	else if ( string.contains("left")) {
			nwstrg[0] = "left";

		}	else if ( string.contains("jump")) {
			nwstrg[0] = "jump";
			
		}	else if ( string.contains("down")) {
			nwstrg[0] = "down";
			
		}
		
		
		return nwstrg;

	}

}
