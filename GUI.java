import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;

public class GUI
{
	private JFrame windowFrame;
	private JButton aboutTab,loadTab,addTab, saveTab,visualizeTab,addData, openFile;
	private JPanel windowPanel, displayPanel, aboutPanel, loadPanel, addPanel, visualizePanel, graph;
	private JTextField id, lastName, firstName, vaccineType, vaccineDate, vaccineLocation;
	private JLabel addStatus;
	private JTable table;
	
	
	
	
	JLabel placeholder; // TODO TEMPORARY
	
	
	
	
	final String[] columnLabels = {"ID", "Last Name", "First Name", "Vaccine Type", "Vaccination Date", "Vaccination Location"};
	private String[][] data = { {"", "", "", "" , "", ""}};
	final Color main = Color.decode("#1F2933"); //color of background of panels
	final Color secondary = Color.decode("#323F4B"); //menu bar color
	final Color tertiary = Color.decode("#9AA5B1"); //menu bar color
	final Color selected = Color.decode("#616E7C"); //selected menu option color
	final Color textPrimary = Color.decode("#E4E7EB"); //color of text
	final Color textSecondary = Color.decode("#F5F7FA");
	final Color affirmatory = Color.decode("#00FF00");
	final Color negatory = Color.decode("#FF0000");
	final Font title = new Font("Helvetica", Font.PLAIN, 30);
	final Font names = new Font("Helvetica", Font.PLAIN, 25);
	
	public static void main(String[] args)
	{	
		new GUI();
	}
	public GUI()
	{
		//Window 
		windowFrame = new JFrame();
		windowPanel = new JPanel();
		
		//initialize Panels and Layouts
		JPanel menu = new JPanel();	
		displayPanel = new JPanel();
		aboutPanel = new JPanel();
		loadPanel = new JPanel();
		addPanel = new JPanel();
		visualizePanel = new JPanel();
		displayPanel.setLayout(new BorderLayout() );
		windowPanel.setLayout(new BorderLayout());
		displayPanel.setBackground(main);
		windowPanel.setBackground(main);
		
		//initialize menu tabs, set colors to default
		aboutTab = new JButton("About");
		loadTab = new JButton("Load Data");
		addTab = new JButton("Add Data");
		saveTab = new JButton("Save Data");
		visualizeTab = new JButton("Visualize Data");
		resetMenuBar();
		
		//about Panel design
		aboutPanel.setBackground(new Color(255, 255, 255));
		aboutPanel.setLayout(new BorderLayout());
		JTextPane aboutTeam = new JTextPane();
		JTextPane teamName = new JTextPane();
		aboutTeam.setText("\tBrandon Downs\n\tSameet Krishna Kumar\n\tSaurabh Rane\n\tSneha Savani\n\tGovind Venugopal\n");
		teamName.setText("CSE360 Team #8");
		teamName.setEditable(false);
		aboutTeam.setEditable(false);
		aboutTeam.setFont(names);
		teamName.setFont(title);
		aboutTeam.setBackground(main);
		teamName.setBackground(main);
		aboutTeam.setForeground(textPrimary);
		teamName.setForeground(textPrimary);
		aboutPanel.add(teamName, BorderLayout.NORTH);
		aboutPanel.add(aboutTeam, BorderLayout.CENTER);
	
		//add Panel Design
		addStatus = new JLabel("");
		JPanel addButtonPanel = new JPanel(new GridLayout(0,1));
		addPanel.setLayout(new BoxLayout(addPanel, BoxLayout.Y_AXIS));
		addData = new JButton("Add Entry");
		id = new JTextField("",50);
		lastName = new JTextField("",50);
		firstName = new JTextField("",50);
		vaccineType = new JTextField("",50);  
		vaccineDate = new JTextField("",50);
		vaccineLocation = new JTextField("",50);
		JPanel ids = new JPanel(new FlowLayout());
		JPanel dates = new JPanel(new FlowLayout());
		JPanel lastNames = new JPanel(new FlowLayout());
		JPanel firstNames = new JPanel(new FlowLayout());
		JPanel vaccineTypes = new JPanel(new FlowLayout());
		JPanel vaccineLocations = new JPanel(new FlowLayout());
		JLabel idLabel = new JLabel("ID:");
		JLabel vaccineDateLabel = new JLabel("Vaccine Date:");
		JLabel lastNameLabel = new JLabel("Last Name:");
		JLabel firstNameLabel = new JLabel("First Name:");
		JLabel vaccineTypeLabel = new JLabel("Vaccine Type:");
		JLabel vaccineLocationLabel = new JLabel("Vaccine Location:");
		idLabel.setForeground(textPrimary);
		vaccineDateLabel.setForeground(textPrimary);
		firstNameLabel.setForeground(textPrimary);
		vaccineTypeLabel.setForeground(textPrimary);
		vaccineLocationLabel.setForeground(textPrimary);
		lastNameLabel.setForeground(textPrimary);
		idLabel.setForeground(textPrimary);
		addData.setForeground(textPrimary);
		addData.setBackground(secondary);
		dates.setForeground(main);
		dates.setBackground(main);
		ids.setBackground(main);
		lastNames.setBackground(main);
		firstNames.setBackground(main);
		vaccineTypes.setBackground(main);
		vaccineLocations.setBackground(main);
		addStatus.setBackground(main);
		addStatus.setForeground(textPrimary);
		dates.add(vaccineDateLabel); dates.add(vaccineDate); 
		ids.add(idLabel); ids.add(id);
		lastNames.add(lastNameLabel); lastNames.add(lastName); 
		firstNames.add(firstNameLabel); firstNames.add(firstName); 
		vaccineTypes.add(vaccineTypeLabel); vaccineTypes.add(vaccineType); 
		vaccineLocations.add(vaccineLocationLabel); vaccineLocations.add(vaccineLocation); 
		addPanel.add(dates);
		addPanel.add(ids);
		addPanel.add(lastNames);
		addPanel.add(firstNames);
		addPanel.add(vaccineTypes);
		addPanel.add(vaccineLocations);
		addPanel.add(addStatus);
		addPanel.setBackground(main);
		addButtonPanel.add(addData);
		addPanel.add(addButtonPanel);
		addData.addActionListener(new ActionHandler(this));
		
		//load Panel Design
		JPanel graphPanel = new JPanel();
		graphPanel.setLayout(new GridLayout(0,1));
		table = new JTable(data, columnLabels);
		graphPanel.add(table.getTableHeader());
		graphPanel.add(table);
		table.setDefaultEditor(Object.class, null);
		table.getTableHeader().setReorderingAllowed(false);
		openFile = new JButton("Open File");
		loadPanel.setLayout(new BorderLayout());
		loadPanel.add(graphPanel, BorderLayout.NORTH);
		loadPanel.add(openFile, BorderLayout.SOUTH);	
		loadPanel.setBackground(main);
		graphPanel.setBackground(main);	
		table.getTableHeader().setBackground(selected);
		table.setBackground(tertiary);
		table.setForeground(textSecondary);
		table.getTableHeader().setForeground(textSecondary);
		openFile.setBackground(secondary);
		openFile.setForeground(textPrimary);
		openFile.addActionListener(new ActionHandler(this));
		
		//visualize Panel Design
		visualizePanel.setBackground(main);
		visualizePanel.setLayout(new BorderLayout());
		JPanel chartButtonPanel = new JPanel(new GridLayout(1,0));
		JButton pie = new JButton("Display Pie Chart");
		JButton histogram = new JButton("Display Histogram");
		pie.setBackground(secondary);
		histogram.setBackground(secondary);
		histogram.setForeground(textPrimary);
		pie.setForeground(textPrimary);
		pie.addActionListener(new ActionHandler(this));
		histogram.addActionListener(new ActionHandler(this));
		
		//TODO the panel for graph object???? *****************************************************
		graph = new JPanel();
		graph.setBackground(main);
		placeholder = new JLabel("Placeholder for no chart");
		placeholder.setForeground(textPrimary);
		graph.add(placeholder);
		chartButtonPanel.add(pie);
		chartButtonPanel.add(histogram);
		visualizePanel.add(graph, BorderLayout.CENTER);
		visualizePanel.add(chartButtonPanel, BorderLayout.SOUTH);
		
		
		
		
		//add tabs to the menu
		menu.setLayout(new GridLayout(0,1));
		menu.add(aboutTab);
		menu.add(loadTab);
		menu.add(addTab);
		menu.add(saveTab);
		menu.add(visualizeTab);
		
		//action listeners to change frame
		aboutTab.addActionListener(new ActionHandler(this));
		loadTab.addActionListener(new ActionHandler(this));
		addTab.addActionListener(new ActionHandler(this));
		saveTab.addActionListener(new ActionHandler(this));
		visualizeTab.addActionListener(new ActionHandler(this));
	
		//add menu and current activity display to window
		windowPanel.add(menu, BorderLayout.WEST);
		windowPanel.add(displayPanel, BorderLayout.CENTER);
		
		//quality of life, border control, layout control of window
		windowPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		windowFrame.add(windowPanel, BorderLayout.CENTER);
		windowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		windowFrame.setTitle("Team 8 Project");
		windowFrame.setSize(700, 700);
		windowFrame.setVisible(true);
	}	
	//reset the menu bar colors
	public void resetMenuBar()
	{
		aboutTab.setBackground(secondary);
		loadTab.setBackground(secondary);
		addTab.setBackground(secondary);
		saveTab.setBackground(secondary);
		visualizeTab.setBackground(secondary);
		aboutTab.setForeground(textPrimary);
		loadTab.setForeground(textPrimary);
		addTab.setForeground(textPrimary);
		saveTab.setForeground(textPrimary);
		visualizeTab.setForeground(textPrimary);
		windowFrame.repaint();
	}
	//switch display view to about tab
	public void aboutTab()
	{
		resetMenuBar();
		displayPanel.removeAll();
		displayPanel.add(aboutPanel, BorderLayout.CENTER);
		aboutTab.setBackground(selected);
		windowFrame.setVisible(true);
	}
	//switch display view to add tab
	public void addTab()
	{
		resetMenuBar();
		displayPanel.removeAll();
		displayPanel.add(addPanel, BorderLayout.CENTER);
		addTab.setBackground(selected);
		windowFrame.setVisible(true);
	}
	//switch display view to load tab
	public void loadTab()
	{
		resetMenuBar();
		displayPanel.removeAll();
		displayPanel.add(loadPanel, BorderLayout.CENTER);
		loadTab.setBackground(selected);
		windowFrame.setVisible(true);
	}
	//switch display view to visualize tab
	public void visualizeTab()
	{
		resetMenuBar();
		displayPanel.removeAll();
		displayPanel.add(visualizePanel, BorderLayout.CENTER);
		visualizeTab.setBackground(selected);
		windowFrame.setVisible(true);
	}
	//get the data from the add tab
	public String[] getDataEntry()
	{
		String data[] = { id.getText(), lastName.getText(), firstName.getText(), vaccineType.getText(),vaccineDate.getText(), vaccineLocation.getText() };
		return data;
	}
	//correct add tab syntax gui event
	public void validEntry()
	{
		addStatus.setText("Entry successfully added.");
		addStatus.setForeground(affirmatory);
		id.setText("");
		lastName.setText("");
		firstName.setText("");
		vaccineType.setText("");
		vaccineDate.setText("");
		vaccineLocation.setText("");
	}
	//incorrect add tab syntax gui event
	public void invalidEntry()
	{
		addStatus.setText("Entry failed. Incorrect Format.");
		addStatus.setForeground(negatory);
	}
	//save data gui event
	public void saveDataInitiate()
	{
		saveTab.setBackground(selected);
		windowFrame.setVisible(true);
	}
	//save data finish gui event
	public void saveDataComplete()
	{
		saveTab.setBackground(secondary);
		windowFrame.setVisible(true);
	}
	public void showHistogram()
	{
		placeholder.setText("Histogram placeholder");
	}
	public void showPieChart()
	{
		placeholder.setText("Pie Chart placeholder");
	}
}
