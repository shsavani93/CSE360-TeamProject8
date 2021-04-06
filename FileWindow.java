import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileWindow 
{
	private JFileChooser fileChoose;
	private int selection;
	private JButton open, save;
	FileNameExtensionFilter filter;
	private String outFilePath, inFilePath;
	
	public FileWindow(boolean mode) //mode refers to load or save, 0 or 1 respectively 
	{
		//shared properties b/t save and open windows
		fileChoose = new JFileChooser();
		fileChoose.setCurrentDirectory(new File(System.getProperty("user.home")));
		filter = new FileNameExtensionFilter("CSV FILES", "csv", "xlsx");
		fileChoose.setFileFilter(filter);
		//Open a Open File Window
		if(mode) //if mode == 1, read csv
		{
			fileChoose.setDialogTitle("Open File");
			selection = fileChoose.showOpenDialog(open);
			
			if(selection == JFileChooser.APPROVE_OPTION)
			{
				inFilePath = fileChoose.getSelectedFile().getAbsolutePath();
			}
		}
		//Open a Save File Window
		else //if mode == 0, save csv
		{
			fileChoose.setDialogTitle("Save File");
			selection = fileChoose.showSaveDialog(save);
			fileChoose.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			if(selection == JFileChooser.APPROVE_OPTION)
			{
				outFilePath = fileChoose.getSelectedFile().getAbsolutePath();
			}
		}
		
		
	}	
	//return the input file path in a string form
	public String getInputFilePath()
	{
		return inFilePath;
	}
	//return the output file path in a string form
	public String getOutputFilePath()
	{
		return outFilePath;
	}
}


