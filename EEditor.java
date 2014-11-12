package FinalProject;


import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import FinalProject.EEFrame;
import FinalProject.EECodeFormater;
import FinalProject.EEJavaWordCounter;
import FinalProject.EELineCounter;

public class EEditor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
            // Set cross-platform Java L&F (also called "Metal")
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
	
			//Set Motif L&F
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
			
			//Set Nimbus L&F
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			
			//Set System L&F
			//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	
			//Set GTK L&F --> Same as System L&F on Linux and Solaris\
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
			
			//Set Windows L&F --> Same as System L&F on Windows
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} 
		catch (UnsupportedLookAndFeelException e) {
			// handle exception
		}
		catch (ClassNotFoundException e) {
			// handle exception
		}
		catch (InstantiationException e) {
			// handle exception
		}
		catch (IllegalAccessException e) {
			// handle exception
		}
		
		EEFrame frame = new EEFrame();
		
		frame.setSize(500, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		
		/* TODO Instatiate Threads */
		EEJavaWordCounter wc = new EEJavaWordCounter(frame);
		Thread JavaWordCounterThread = new Thread(wc, "JavaWordCounter");
		JavaWordCounterThread.start();
		
		/*TODO Start Threads */
		EELineCounter lc = new EELineCounter(frame);
		Thread LineCounterThread = new Thread(lc, "LineCounter");
		LineCounterThread.start();
		
		/*TODO Start Threads */
		EECodeFormater cf = new EECodeFormater(frame);
		Thread EECodeFormaterThread = new Thread(cf, "LineCounter");
		EECodeFormaterThread.start();
	}

}