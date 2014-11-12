package FinalProject;

//import java.util.Scanner;

public class EEJavaWordCounter implements Runnable{
	private EEFrame frame;
	public EEJavaWordCounter(EEFrame frame1){super(); this.frame = frame1;}
	
public void run()
{	
		do{
			try
			{
					//String [] words = frame.getTextPane().getText().split(" ");
					
				    int wordCount = 0;
				    boolean word = false;
				    int endOfLine = frame.getTextPane().getText().length() - 1;

				    for (int i = 0; i < frame.getTextPane().getText().length(); i++) 
				    {
				        // if the char is a letter, word = true.
				        if (Character.isLetter(frame.getTextPane().getText().charAt(i)) && i != endOfLine)
				        {
				            word = true;
				            // if char isn't a letter and there have been letters before,
				            // counter goes up.
				        } 
				        else if (!Character.isLetter(frame.getTextPane().getText().charAt(i)) && word) 
				        {
				            wordCount++;
				            word = false;
				            // last word of String; if it doesn't end with a non letter, it
				            // wouldn't count without this.
				        } 
				        else if (Character.isLetter(frame.getTextPane().getText().charAt(i)) && i == endOfLine) 
				        {
				            wordCount++;
				        }
				    }
					
					frame.setWords(wordCount); 
					Thread.sleep(1000);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}			
			}while(true);
	}

}
