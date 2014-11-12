package FinalProject;


import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

public class EEFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1709009137090877913L;
	private GridBagLayout layout;
	private GridBagConstraints constraints;
	private EEMenuBar menuBar;
	private EETextPane editor;
	private EEConsole console;
	private EEStatusBar statusBar;
	private File file;
	//FileReader inputStream = null;
	private FileWriter outputStream = null;
	private BufferedWriter outputBuffer = null;
	
	public EEFrame() throws HeadlessException {
		super("Elearn Editor");
		
		JScrollPane scrollPane;
		
		layout = new GridBagLayout();
		setLayout(layout);
		
		constraints = new GridBagConstraints();
		
		menuBar = new EEMenuBar();
		setJMenuBar(menuBar);
		
		editor = new EETextPane();
		
		scrollPane = new JScrollPane(editor);
		scrollPane.setBorder(new TitledBorder("Editor"));
		
		setConstraints(1, 100, GridBagConstraints.BOTH);
		addComponent(scrollPane, 0, 0, 1, 1);
		
		console = new EEConsole();
		
		scrollPane = new JScrollPane(console);
		scrollPane.setBorder(new TitledBorder("Console"));
		
		setConstraints(1, 40, GridBagConstraints.BOTH);
		addComponent(scrollPane, 1 ,0 ,1, 1);
		
		statusBar = new EEStatusBar();
		setConstraints(1, 0, GridBagConstraints.BOTH);
		addComponent(statusBar, 2, 0, 1, 1);
		
		
		file = null;
	}
	
	public JTextPane getTextPane() {
		return this.editor;
	}

	public void setLines(int lines) {
		this.statusBar.setLines(lines);
	}
	
	public void setWords(int words) {
		this.statusBar.setJavaWords(words);
	}
	
	private void setConstraints(int weightx, int weighty, int fill) {
		constraints.weightx = weightx;
		constraints.weighty = weighty;
		constraints.fill = fill;
	}
	
	private void addComponent(Component component, int row, int column, int width, int height) {
		constraints.gridx = column;
		constraints.gridy = row;
		constraints.gridwidth = width;
		constraints.gridheight = height;
		layout.setConstraints(component, constraints);
		add(component);
	}
	
	private class EEMenuBar extends JMenuBar {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = -2176624051362992835L;
		private JMenu fileMenu, compilationMenu;
		private JMenuItem newItem, openItem, saveItem, saveAsItem, exportItem, compileProcessItem, compileClassItem;
		
		public EEMenuBar() {
			super();
			
			fileMenu = new JMenu("File");
			
			newItem = new JMenuItem("New");
			
			newItem.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					  JFileChooser chooser = new JFileChooser();
					    FileNameExtensionFilter filter = new FileNameExtensionFilter(
					        "java & class & txt files", "java", "class", "txt");
					    chooser.setFileFilter(filter);
					    int returnVal = chooser.showDialog(getParent(),"Save");
					    if(returnVal == JFileChooser.APPROVE_OPTION) {
					       System.out.println("You chose to create a file with name: " + chooser.getSelectedFile().getAbsolutePath());
					       try{
					    	   file = new File(chooser.getSelectedFile().getAbsolutePath());
					    	   file.createNewFile();
					       }catch(IOException exc){
					    	   exc.printStackTrace();
					       }
					    }
				
				}
				
			});
			
			fileMenu.add(newItem);
			
			openItem = new JMenuItem("Open");
			
			openItem.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					/*TODO Open existing java source file*/
				    JFileChooser chooser = new JFileChooser();
				    FileNameExtensionFilter filter = new FileNameExtensionFilter(
				        "java source files", "java", "class", "txt");
				    chooser.setFileFilter(filter);
				    int returnVal = chooser.showOpenDialog(getParent());
				    if(returnVal == JFileChooser.APPROVE_OPTION) {
				       System.out.println("You chose to open this file: " + chooser.getSelectedFile().getAbsolutePath());
				       try{
				    	   file = new File(chooser.getSelectedFile().getAbsolutePath());
				    	   editor.setPage(file.toURI().toURL());
				       }catch(IOException exc){
				    	   exc.printStackTrace();
				       }
				    }
					
				}
				
			});
			
			fileMenu.add(openItem);
			
			saveItem = new JMenuItem("Save");
			saveItem.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					/*TODO save changes to file*/	
					
				       try{
				    	   //file already points to the correct file from open or new
				    	   outputStream = new FileWriter(file);
				    	   outputBuffer = new BufferedWriter(outputStream);
				    	   outputBuffer.write(editor.getText());
				    	   outputBuffer.close();
				    	   System.out.println("file " + file.getName() + "was saved");
				       }catch(IOException exc){
				    	   exc.printStackTrace();
				       }

				}
				
			});
			
			fileMenu.add(saveItem);
			
			saveAsItem = new JMenuItem("Save As");
			
			saveAsItem.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					/*TODO Save as new java source file*/
					
					/* TODO Dispay dialog with inputs class name and file path */
				    JFileChooser chooser = new JFileChooser();
				    FileNameExtensionFilter filter = new FileNameExtensionFilter("Java source files", "java", "class", "txt");
					    chooser.setFileFilter(filter);
				    int returnVal = chooser.showSaveDialog(getParent());
				    if(returnVal == JFileChooser.APPROVE_OPTION) {
				       System.out.println("You chose to save the file with name: " + chooser.getSelectedFile().getAbsolutePath());
				       //System.out.println("conetnts of the editor :" + editor.getText());
				       try{
				    	   file = new File(chooser.getSelectedFile().getAbsolutePath());
				    	   file.createNewFile();
				    	   outputStream = new FileWriter(file);
				    	   outputBuffer = new BufferedWriter(outputStream);
				    	   outputBuffer.write(editor.getText());
				    	   outputBuffer.close();
				       }catch(IOException exc){
				    	   exc.getStackTrace();
				       }
				    }
				}				
			});
			
			fileMenu.add(saveAsItem);
			
			exportItem = new JMenuItem("Export to pdf");
			
			exportItem.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO save as pdf(formatted)
					
				}
			});
			
			fileMenu.add(exportItem);			
			
			add(fileMenu);
			
			compilationMenu = new JMenu("Compilation");
			
			compileProcessItem = new JMenuItem("Compile with system jdk");
			
			compileProcessItem.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					/*TODO Compile java source code and show results in teminal(inside editor)*/
				}
				
			});
					
			compilationMenu.add(compileProcessItem);
			
			compileClassItem = new JMenuItem("Compile with JavaCompiler Class");
			
			compileClassItem.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					/*TODO Call system compiler for file*/
				}
			});
			
			compilationMenu.add(compileClassItem);
			
			add(compilationMenu);
			
		}
	}
	
	private class EETextPane extends JTextPane {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = -7437561302249475096L;

		public EETextPane() {
			super();
			
			//add styles to document
			Style def = StyleContext.getDefaultStyleContext().getStyle( StyleContext.DEFAULT_STYLE );
			StyleConstants.setForeground(def, Color.BLACK);
			StyleConstants.setFontFamily(def, "Courier");
			StyleConstants.setFontSize(def, 12);
			 
			Style keyword = addStyle("keyword", def);
			StyleConstants.setForeground(keyword, Color.BLUE);
			
			Style literal = addStyle("literal", def);
			StyleConstants.setForeground(literal, Color.ORANGE);
			
			Style comment = addStyle("comment", def);
			StyleConstants.setForeground(comment, Color.GREEN);
		}
	}
	
	private class EEConsole extends JTextPane {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = -5968199559991291905L;

		public EEConsole() {
			super();
			
			//add styles to document
			Style def = StyleContext.getDefaultStyleContext().getStyle( StyleContext.DEFAULT_STYLE );
			StyleConstants.setForeground(def, Color.BLACK);
			StyleConstants.setFontFamily(def, "Courier");
			StyleConstants.setFontSize(def, 12);
			 
			Style keyword = addStyle("error", def);
			StyleConstants.setForeground(keyword, Color.RED);
			
			Style literal = addStyle("success", def);
			StyleConstants.setForeground(literal, Color.GREEN);
		}
		
	}
	
	private class EEStatusBar extends JPanel {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 185007911993347696L;
		private BoxLayout layout;
		private JLabel linesLabel, lines, wordsLabel, words;
		
		public EEStatusBar() {
			super();
						
			layout = new BoxLayout(this, BoxLayout.X_AXIS);
			setLayout(layout);
			
			linesLabel = new JLabel("Lines : ");
			linesLabel.setAlignmentX(LEFT_ALIGNMENT);
			add(linesLabel);
			
			lines = new JLabel();
			lines.setAlignmentX(LEFT_ALIGNMENT);
			add(lines);
			
			
			add(Box.createRigidArea(new Dimension(10,10)));
			
			wordsLabel = new JLabel("Java Words : ");
			wordsLabel.setAlignmentX(LEFT_ALIGNMENT);
			add(wordsLabel);
			
			words = new JLabel();
			words.setAlignmentX(LEFT_ALIGNMENT);
			add(words);
			

		}
		

		
		public void setLines(int lines1) {
			/*TODO set line numbers */
			this.lines.setText(Integer.toString(lines1));
		}
		
		public void setJavaWords(int words1) {
			/*TODO set java keyword numbers*/
			this.words.setText(Integer.toString(words1));
		}
	}
}