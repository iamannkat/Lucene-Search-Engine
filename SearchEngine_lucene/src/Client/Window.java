package Client;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.HighlightPainter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

public class Window implements ActionListener{
		
	JFrame frame;
	JTextArea textArea;
	JButton button;
	JScrollPane scroll;

	private String[] results;
	private String[] path;
	private int counter = 0;
	private String word;
	private Highlighter highlighter;
    private HighlightPainter painter; 
	
	public void setMovieTitle(String[] path) {
		this.path = path;
	}
	
	public void set(String[] results) {
		this.results = results;
	}
	
	public void setWord(String word) {
		this.word = word;
	}
	
	public void closeWindow() {
		frame.dispose();
	}
	
	
	public void createWindow() {
	    	
	       //Create and set up the window.
	       frame = new JFrame("Search Results");
	       textArea = new JTextArea(20, 50);
	       button = new JButton("Show more results");
	       
	       Font font = new Font("comic sans", Font.BOLD, 15);
	       textArea.setFont(font);
	       
	       scroll = new JScrollPane(textArea);
	       
	       scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
	       scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	       
	       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	      
	       button.addActionListener(this);
	       
	       frame.add(scroll);
	       frame.add("South", button);
	       
	       frame.setSize(340,270);
	       frame.setLocationRelativeTo(null);
	       frame.pack();
	       frame.setVisible(true);
		   frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\user\\Desktop\\EclipseProjects\\SearchEngine_lucene\\src\\logol.png"));
		   
	}
	   
	 
	public void addFirstTen() throws BadLocationException {

		//highlighter = textArea.getHighlighter();
		//painter = new DefaultHighlighter.DefaultHighlightPainter(Color.yellow);
		textArea.append("======================== *** SEARCH RESULTS *** =========================\n\n");

		for (int i = 0; i < 10; i++) {
			if(i < results.length) {
			
       		    textArea.append("Movie Title :: " + path[i] + "\n");
       		 	textArea.append(results[i] + "\n");
       		    textArea.append("-----------------------------------------------------------------\n\n");

       	 	}else {
       	 		textArea.append("End of Results " + "\n");
       	 		break;
       	 	}
        }
//		String text = textArea.getText();
//		String[]lines = text.split(System.getProperty("line.separator"));
//		
//		for (String line : lines) {
//			String[] strlist = line.split(" ");
//			
//			for(int i=0;i<strlist.length;i++) {
//				//System.out.println(word);
//
//				System.out.println(strlist[i]);
//				if(strlist[i].toLowerCase().equals(word)){
//					int p0 = text.indexOf(word);
//					System.out.println(p0);
//
//				    int p1 = p0 + word.length();
//					System.out.println(p1);
//
//					highlighter.addHighlight(p0, p1, painter);
//				}
//			}
//		
//		}
		counter +=10;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
 		       
         for (int i = counter; i < counter+10; i++) {
        	 if(i < results.length) {
        		 textArea.append("Movie Title :: " + path[i] + "\n");
        		 textArea.append(results[i] + "\n\n");
        		 textArea.append("--------------------------------------------------------------------\n");

        	 }else {
        		 textArea.append("End of Results " + "\n");
        		 break;
        	 }
         }
		 counter +=10;

 	
    }
	    
}
