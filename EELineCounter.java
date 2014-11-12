package FinalProject;

//import java.util.Scanner;

public class EELineCounter implements Runnable{
	
	EEFrame frame;
	public EELineCounter(EEFrame frame1){super(); this.frame = frame1;}
	
	public void run()
	{			
		do{
			try
			{
			    int lineCount = 1;
			    for (int i = 0; i < frame.getTextPane().getText().length(); i++) 
			    {
			        // if the char is a \n, then lineCount++.
			        if ( frame.getTextPane().getText().charAt(i) == '\n') {lineCount++; } 
			    }
				frame.setLines(lineCount);
				Thread.sleep(1000);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}while(true);
		

	}

}
