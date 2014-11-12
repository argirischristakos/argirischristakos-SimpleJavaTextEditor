package FinalProject;

import javax.swing.text.*;


public class EECodeFormater implements Runnable{
	EEFrame frame;
	
	//constructor
	public  EECodeFormater(EEFrame frame1){super(); this.frame = frame1;}
	
	public void run()
	{
		do
		{
			try
			{

	            
	            // Prota tsekaro an matsarei to regular expression regexkeyword to opoio ekfrazei opoiadipote desmeumeni leksi

				String regexkeyword = "abstract|continue|for|new|switch|assert|default|goto|package|synchronized|boolean|do|if|private|this|break|double|implements|protected|throw|byte|else|import|public|throws|case|enum|instanceof|return|transient|catch|extends|int|short|try|char|final|interface|static|void|class|finally|long|strictfp|volatile|const|float|native|super|while";
	            int begin = 0;
	            int end = 0;
	            int size = 0;
	            frame.getTextPane().getDocument().putProperty(DefaultEditorKit.EndOfLineStringProperty, "\r\n");
	            String[] splittext = frame.getTextPane().getText().split(" |\n", -1);
	            for (String text1 : splittext) {
	            	//System.out.print(text1);
	            	size = text1.length();
	            	end = begin +size;
	            	if (text1.matches(regexkeyword) ){
	            		frame.getTextPane().getStyledDocument().setCharacterAttributes(begin, size, frame.getTextPane().getStyle("keyword"), true);
	            	}
	            	begin = end+1;
	            };
	            
	            //String regexLiteral = "\"[a-zA-Z0-9\\s\"#$%&'()*+,./;:=?_@>-].*\"";
	            
	            //Meta xrisimopoio to " san delimeter stin spit gia na bro to periexomeno anamesa se duo " kai xromatizo to 2o,to 4o,to 6o
	            //ktl
	            int count = 0;
	            int begin2 = 0;
  
	            String[] splittext2 = frame.getTextPane().getText().split("\"", -1);
	            for (String text2 : splittext2) {
	            	count ++;
	            	
	            	if (count%2 == 0 ){
	            		frame.getTextPane().getStyledDocument().setCharacterAttributes(begin2-1, text2.length()+2, frame.getTextPane().getStyle("literal"), true);
	            	}
	            	begin2 = begin2 + text2.length()+1;
	            }
	            
	            
		          /* 
	            /int count3 = 0;
	            int begin3 = 0;
	            String[] splittext3 = frame.getTextPane().getText().split("//|\n", -1);
	            for (String text3 : splittext3) {
	            	count3 ++;
	            	
	            	if (count3%2 == 0 ){
	            		frame.getTextPane().getStyledDocument().setCharacterAttributes(begin3-1, text3.length()+2, frame.getTextPane().getStyle("comment"), true);
	            	}
	            	begin3 = begin3 + text3.length()+1;
	            }*/

	            //telos molis sinantiso duo sunexomenous xaraktires // xromatizo otidipote meta apo autous kai mexri to telos tis grammis
	            for (int i = 1; i < frame.getTextPane().getText().length(); i++) 
	            {
	                // if two continuous characters are the comment characters.
	               	if ( frame.getTextPane().getText().charAt(i) == '/' && frame.getTextPane().getText().charAt(i-1) == '/') {
	               		int j = i; 
	            	    for ( j = i; j < frame.getTextPane().getText().length(); j++) {if ( frame.getTextPane().getText().charAt(j) == '\n' ) {break;}}
	                	frame.getTextPane().getStyledDocument().setCharacterAttributes(i-1, j-i+1, frame.getTextPane().getStyle("comment"), true);  	
	                }				    	
	            }
	            
			
				Thread.sleep(1000);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}while(true);
		
	
	}

}
