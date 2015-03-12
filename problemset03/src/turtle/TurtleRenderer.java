package turtle;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public class TurtleRenderer extends Panel implements  DocumentListener {
	
	private static final long serialVersionUID = 1;

	static final Dimension WINDOW_SIZE = new Dimension(1150, 1150);
	
	boolean [][] board;
	final BoardMaker boardMaker;
	private static TurtleParser turtleParser = new TurtleParser();
	
	public TurtleRenderer() throws OutOfBoundsException {
		boardMaker = new BoardMaker();
		board = boardMaker.initialBoard();
	}

	static public void main(String args[]) throws Exception {
		JFrame frame = new JFrame("Display image");
		JPanel panel = new JPanel();
		TurtleRenderer image = new TurtleRenderer();
		image.setPreferredSize(WINDOW_SIZE);
		JScrollPane textArea = makeTextArea(image);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.add(image);
		buildRightPanel(panel, textArea);
		frame.setSize(WINDOW_SIZE);
		frame.addWindowListener(new WindowAdapter(){
	        public void windowClosing(WindowEvent we){
	            System.exit(0);
	        }});
		frame.getContentPane().add(panel);
		frame.setVisible(true);

	}

	static void buildRightPanel(JPanel panel,JComponent textArea) {
		JLabel label = new JLabel("Your program:");
		label.setPreferredSize(new Dimension(150, 20));
		JPanel right = new JPanel();
		textArea.setPreferredSize(new Dimension(150,500));
		right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
		right.add(label);
		right.add(textArea);
		panel.add(right);
	}

	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(Color.white);
		g.fillRect(0, 0, WINDOW_SIZE.width, WINDOW_SIZE.width);
		if(board == null) 
			return;
		g2d.setColor(Color.red);
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board.length;j++) {
				if(board[i][j])
					g2d.fillRect(9*i+1, 9*j+1, 6, 6);
			}
		}
	}
	
	static JScrollPane makeTextArea(TurtleRenderer image) {
		JTextArea textArea = new JTextArea();
		textArea.getDocument().addDocumentListener(image);
		textArea.setVisible(true);
		JScrollPane areaScrollPane = new JScrollPane(textArea);
		areaScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		areaScrollPane.setBorder(BorderFactory.createLineBorder(Color.black));
		return areaScrollPane;
	}
	
	@Override
	public void insertUpdate(DocumentEvent e) {
		try {
			changed(e);
		} catch (OutOfBoundsException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		try {
			changed(e);
		} catch (OutOfBoundsException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		String description;
		Document document = e.getDocument();
		
		try {
			changed(e);
		} catch (OutOfBoundsException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			description = document.getText(0, document.getLength());
		} catch (BadLocationException e1) {
			throw new RuntimeException(e1);
		}
		if (description.endsWith("\n")) {
			try {
				board = boardMaker.makeBoardFrom(description);
			} catch(ParserException pe) {
				board = null;
			} catch (OutOfBoundsException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else {
			return;
		}
		this.repaint();
	}
	
	void changed(DocumentEvent de) throws OutOfBoundsException {
		String description;
		Document document = de.getDocument();
			
		try {
			description = document.getText(0, document.getLength());
		} catch (BadLocationException e) {
			throw new RuntimeException(e);
		}
		if (description.endsWith("\n")) {
			try {
				System.out.println(description);
				board = boardMaker.makeBoardFrom(description);
			} catch(ParserException pe) {
				board = null;
			}
		} else {
			return;
		}
		this.repaint();
	}
}
