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
					
			
			boolean isValid = isValidInput(id, fname, lname, type.toLowerCase(), date, location);
			
			//***************************************************************************************
			//if valid input (put conditional for determining if valid input here
			// TODO if(correct syntax)
			if (isValid)
			{
				Object rowData [] = new  Object[]{id, lname, fname, type, date, location};
				gui.validEntry(rowData); //GUI EVENT
			}
			else
			{
				gui.invalidEntry();
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
	public boolean isValidInput(String id, String fname, String lname, String type, String date, String location)
	{
		//set to false
		boolean result = false;
		boolean dateBad = false;
		boolean nameBad = false;
		boolean idBad = false;
		boolean vaxBad = true;
		

		//if it matches a vaccine type set to true
		String[] vaccineTypes = { "sinovac" ,"pfizer", "moderna", "johnson&johnson", "astrazeneca", "novavax"};
		for(int i = 0; i < vaccineTypes.length; i++) {
			if(vaccineTypes[i].equals(type)) {result = true; vaxBad = false;}
		}
		//if fname, lname, location is null or empty set result to false
		if(lname == null || lname.equals("")) { result = false; nameBad = true; }
		if(fname == null || fname.equals("")) { result = false; nameBad = true;}
		if(location == null || location.equals("")) { result = false; nameBad = true;}
		
		
		//if id is not 5 numerics set result to false
		if(id.length() != 5 || ! isANumber(id)) { result = false; idBad = true; }
		
		//if date is not correct mm/dd/yyyy set result to false
		String[] dateSplit = null;
		
		
		
		try { dateSplit = date.split("/"); }
		catch(Exception e) { result = false; dateBad = true;}
		System.out.println(verifyDate(date));
		
		if(dateSplit != null)
		{
			if(dateSplit.length == 3){
				if(dateSplit[0].length() != 2 || ! isANumber(dateSplit[0])) { result = false; dateBad = true; }
				if(dateSplit[1].length() != 2 || ! isANumber(dateSplit[1])) { result = false; dateBad = true;}
				if(dateSplit[2].length() != 4 || ! isANumber(dateSplit[2])) { result = false; dateBad = true;}
				if(!verifyDate(date)) { result = false; dateBad = true;}
			}
			else {result = false; dateBad = true;}
		}
		
		else {result = false; dateBad = true;}
		if(nameBad)
		{
			System.out.println("bad name/loc");
		}
		if(idBad)
		{
			System.out.println("bad id");
		}
		if(dateBad)
		{
			System.out.println("bad date");
		}
		if(vaxBad)
		{
			System.out.println("bad vax");
		}
		return result;
	}
    public boolean isANumber(String line) {
        if (line == null) { return false;}
        try { int num = Integer.parseInt(line);} 
        catch (NumberFormatException nfe) { return false;}
        return true;}
    public boolean verifyDate(String date)
    {
    	String[] dateSplit = date.split("/"); 
    	String month = dateSplit[0];
    	String day = dateSplit[1];
    	String year = dateSplit[2];
    	int dayNumber;
    	int yearNumber;
    	int monthNumber;
        boolean leap = false;
    	
    	yearNumber = Integer.parseInt(year);
    	if(day.substring(0,0) == "0") { dayNumber = Integer.parseInt(day.substring(1,1)); }
    	else { dayNumber = Integer.parseInt(day); }
    	
    	if(month.substring(0,0) == "0") { monthNumber = Integer.parseInt(month.substring(1,1)); }
    	else { monthNumber = Integer.parseInt(month); }
    	
    	
    	if (yearNumber % 4 == 0) 
    	{
    		if (yearNumber % 100 == 0) 
    		{

    			if (yearNumber % 400 == 0)
    			{
    				leap = true;
    			}
    			else
    			{
    				leap = false;
    			}
    		}
    		else
    		{
    			leap = true;
    		}
    	}
    	else
    	{
    		leap = false;
    	}
    	switch(monthNumber){
    	case 1: 
    		if (dayNumber < 0 || dayNumber > 31) { return false;}
    		return true;	//1 to 31
    	case 2: 
    		if(leap) { 
    			if (dayNumber < 0 || dayNumber > 29) { return false;} }
    			else { if (dayNumber < 0 || dayNumber > 28) { return false;}}
    		return true;
    	case 3:
    		if (dayNumber < 0 || dayNumber > 31) { return false;}//1 to 31
    		return true;
    	case 4:
    		if (dayNumber < 0 || dayNumber > 30) { return false;}//1 to 30
    		return true;
    	case 5:
    		if (dayNumber < 0 || dayNumber > 31) { return false;}//1 to 31
    		return true;
    	case 6:
    		if (dayNumber < 0 || dayNumber > 30) { return false;}//1 to 30
    		return true;
    	case 7:
    		if (dayNumber < 0 || dayNumber > 31) { return false;}//1 to 31
    		return true;
    	case 8:
    		if (dayNumber < 0 || dayNumber > 31) { return false;}//1 to 31
    		return true;
    	case 9:
    		if (dayNumber < 0 || dayNumber > 30) { return false;}//1 to 30
    		return true;
    	case 10:
    		if (dayNumber < 0 || dayNumber > 31) { return false;}//1 to 31
    		return true;
    	case 11:
    		if (dayNumber < 0 || dayNumber > 30) { return false;} //1 to 30
    		return true;
    	case 12:
    		if (dayNumber < 0 || dayNumber > 31) {return false;}//1 to 31
    		return true;
    	default: return false;
    	}
    }
    
}