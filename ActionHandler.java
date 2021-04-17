import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionHandler implements ActionListener
{
	private GUI gui;
	private String id, lname, fname, date, location, type, inFilePath, outFilePath;
	private FileWindow fw;
	
	public ActionHandler(GUI g)
	{
		gui = g;
		
	}
		
	
	public void actionPerformed(ActionEvent e) {
//LOGIC EVENTS
//These are action events triggered by the USER clicking buttons or entering information. Logic CAN go here, but should be brief 
//method calls or data assignment to a method call at most. 
		//The User Clicked the Add Entry Button in the Add Data Tab
		if(e.getActionCommand().equals("Add Entry"))
		{
			//assign variables
			id = gui.getDataEntry()[0];
			lname = gui.getDataEntry()[1];
			fname = gui.getDataEntry()[2];
			type = gui.getDataEntry()[3];
			date = gui.getDataEntry()[4];
			location = gui.getDataEntry()[5];
					
			//***************************************************************************************
			//if valid input (put conditional for determining if valid input here
			// TODO if(correct syntax)
			if (true)
			{
				Object rowData [] = new  Object[]{id, lname, fname, type, date, location};
				gui.validEntry(rowData); //GUI EVENT
			}
			else
			{
				gui.invalidEntry();//GUI EVENT
			}
		}
			//TODO do something with the filepath.... saved in String infilePath
			if(e.getActionCommand().equals("Open File"))
			{
				fw = new FileWindow(true);
				inFilePath = fw.getInputFilePath();
				gui.openFile(inFilePath);
				
			}
			//TODO do something with the filepath.... saved in String outfilePath
			if(e.getActionCommand().equals("Save Data"))
			{
				gui.saveDataInitiate();

				//fw = new FileWindow(false);
				//outFilePath = fw.getOutputFilePath();
				//System.out.println(outFilePath);
				gui.saveData();
				gui.saveDataComplete();
			}		
			//TODO VISUALIZE EVENTS
			if(e.getActionCommand().equals("Display Histogram"))
			{
				//todo pass chart parameter?
				gui.showBarChart();
			}
			//TODO VISUALIZE EVENTS
			if (e.getActionCommand().equals("Display Pie Chart"))
			{
				//todo pass chart parameter?
				gui.showPieChart();
			}
		
		
// FINISHED
// GUI MENU EVENTS				
//These are action events to trigger Frame Changes in the GUI. Button Clicks will trigger a method call of the GUI object to accordingly
//change the frame in focus. This is GUI and not logic. DO NOT put logic here. 
		//The User Clicked the About Menu Bar; Change the display frame to the about frame
		if(e.getActionCommand().equals("About"))
		{
			gui.aboutTab();
		}
		//The User Clicked the Visualize Load Menu Bar; Change the display frame to the load frame
		if(e.getActionCommand().equals("Load Data"))
		{
			gui.loadTab();
		}
		//The User Clicked the add Data Menu Bar; Change the display frame to the add data frame
		if(e.getActionCommand().equals("Add Data"))
		{
			gui.addTab();
		}
		//The User Clicked the Visualize Data Menu Bar; Change the display frame to the visualize frame
		if(e.getActionCommand().equals("Visualize Data"))
		{
			gui.visualizeTab();
		}
	}
}