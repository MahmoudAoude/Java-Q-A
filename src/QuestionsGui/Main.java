package QuestionsGui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
	
public static void main(String[] args) throws IOException  {
	
	
	Questions q;
	q = new Questions();
	q.setSize(800,400);  
	q.setTitle("Quiz"); 
	q.setVisible(true); 
	q.addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}
	});

	
	}
}