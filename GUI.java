import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.*;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.category.CategoryDataset;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import javax.swing.Timer;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class GUI
{
	private JFrame windowFrame;
	private JButton aboutTab,loadTab,addTab, saveTab,visualizeTab,addData, openFile;
	private JPanel windowPanel, displayPanel, aboutPanel, loadPanel, addPanel, visualizePanel, graph, chartPanel;
	private JTextField id, lastName, firstName, vaccineType, vaccineDate, vaccineLocation;
	private JLabel addStatus;
	private JTable table;
	private DefaultTableModel tableModel;
	
	
	
	
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
		graphPanel.setLayout(new BoxLayout(graphPanel, BoxLayout.PAGE_AXIS));
		tableModel = new DefaultTableModel(data, columnLabels);
		table = new JTable(tableModel);
		JScrollPane scroll = new JScrollPane(table);
		scroll.add(table.getTableHeader());
		graphPanel.add(scroll);
		scroll.getViewport().getView().setBackground(main);
		scroll.getViewport().getView().setForeground(main);
		scroll.setBorder(BorderFactory.createEmptyBorder());
		table.setFillsViewportHeight(true);
		table.setDefaultEditor(Object.class, null);
		table.getTableHeader().setReorderingAllowed(false);
		openFile = new JButton("Open File");
		loadPanel.setLayout(new BorderLayout());
		loadPanel.add(graphPanel, BorderLayout.NORTH);
		loadPanel.add(graphPanel, BorderLayout.CENTER);
		loadPanel.add(openFile, BorderLayout.SOUTH);	
		loadPanel.setForeground(main);
		graphPanel.setBackground(main);	
		table.getTableHeader().setBackground(selected);
		table.setBackground(secondary);
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
		placeholder = new JLabel("Please Select a Chart to Display");
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
	// load data from csv file and display in table
	public void openFile(String inFilePath) {
		// Storing CSV data
		String csvData [][] = CSV.readCSV(inFilePath);
		Object tableData [] = new Object[6];
		// building the table row
		for (int i = 1; i < csvData.length; i++) {
			for (int j = 0; j < csvData[i].length; j++) {
				if (csvData[i][j] == null || csvData[i][j].isEmpty()) {
					tableData[j] = "-";
				} else {
					tableData[j] = csvData[i][j];
				}
			}
			Object rowData [] = new  Object[]{tableData[0],tableData[1],tableData[2],tableData[3],tableData[4],tableData[5] };
			tableModel.addRow(rowData);
		}
	}
	//get the data from the add tab
	public String[] getDataEntry()
	{
		String data[] = { id.getText(), lastName.getText(), firstName.getText(), vaccineType.getText(),vaccineDate.getText(), vaccineLocation.getText() };
		return data;
	}
	//correct add tab syntax gui event
	public void validEntry(Object rowData[] )
	{
		tableModel.addRow(rowData);
		addStatus.setText("Entry successfully added.");
		addStatus.setForeground(affirmatory);
		id.setText("");
		lastName.setText("");
		firstName.setText("");
		vaccineType.setText("");
		vaccineDate.setText("");
		vaccineLocation.setText("");
		Timer timer = new Timer(2000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ae) {
				addStatus.setText("");
			}
		});
		timer.setInitialDelay(2000);
		timer.start();
	}
	//incorrect add tab syntax gui event
	public void invalidEntry()
	{
		addStatus.setText("Entry failed. Incorrect Format.");
		addStatus.setForeground(negatory);
		Timer timer = new Timer(2000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ae) {
				addStatus.setText("");
			}
		});
		timer.setInitialDelay(2000);
		timer.start();
	}

	//Extracts the data in the JTable into a 2D array

	public Object[][] getTableData (JTable table) {
		TableModel dtm = table.getModel();
		int nRow = dtm.getRowCount(), nCol = dtm.getColumnCount();
		Object[][] tableData = new Object[nRow][nCol];
		for (int i = 0 ; i < nRow ; i++)
			for (int j = 0 ; j < nCol ; j++)
				tableData[i][j] = dtm.getValueAt(i,j);
		return tableData;
	}

	//save data gui event
	public void saveData()
	{
		Object[][] tableData = getTableData(table);
		String[][] modifiedTableData = new String[table.getRowCount()+1][table.getColumnCount()];

		for(int j = 0; j < table.getColumnCount(); j++) {
			modifiedTableData[0][j] = table.getColumnName(j);
		}
		for (int i = 0; i < tableData.length; i++) {
			for (int j = 0; j < tableData[i].length; j++) {
				modifiedTableData[i+1][j] = (String) tableData[i][j];
			}
		}
		
		JFileChooser fc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV FILES", "csv", "csv");
		fc.setFileFilter(filter);
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnVal = fc.showSaveDialog(saveTab);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			String fc1 = fc.getSelectedFile().getAbsolutePath();
			String output = fc1.concat(".csv");
			CSV.writeCSV(modifiedTableData, output);
			}
		
		/*
		 * BRANDON FIX?
		 
		FileWindow saveFile = new FileWindow(false);
		String filePath = saveFile.getOutputFilePath() + ".csv";
		CSV.writeCSV(modifiedTableData, filePath);
		
		*/
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

	/**
	 * This is the method which display bar chart on visualize tab.
	 * @param no params passed
	 * @return void
	 */
	public void showBarChart()
	{
		resetMenuBar();
		if(graph.getParent() == visualizePanel)
		{
			visualizePanel.remove(graph);
		}
		if(chartPanel != null)
		{
			if(chartPanel.getParent() == visualizePanel)
			{
				visualizePanel.remove(chartPanel);
			}
		}
		//displayPanel.removeAll();

		// create map of vaccine type to number of doses for each type
		Map<Object, Integer> mapVaccineTypeToDoses = new HashMap<Object, Integer>();

		for(int i = 1; i < tableModel.getRowCount() ; i++) {
			Object vaccineType = tableModel.getValueAt(i, 3);
			int numberOfDoses = mapVaccineTypeToDoses.containsKey(vaccineType) ? mapVaccineTypeToDoses.get(vaccineType) : 0;
			mapVaccineTypeToDoses.put(vaccineType, numberOfDoses + 1);
		}

		// create bar chart
		JFreeChart barChart = ChartFactory.createBarChart(
				"Doses by Vaccine type", // title
				"Vaccine Type", // categoryAxis (X) label
				"Number of doses", // valueAxis (Y) label
				createDataSet(mapVaccineTypeToDoses), //create dataset
				PlotOrientation.VERTICAL,
				true, true, false);

		chartPanel =  new ChartPanel( barChart );
		chartPanel.setPreferredSize(new java.awt.Dimension( 560 , 367 ) );
		visualizePanel.add(chartPanel,BorderLayout.CENTER);
		//displayPanel.add(chartPanel, BorderLayout.CENTER);
		visualizeTab.setBackground(selected);
		windowFrame.setVisible(true);
	}

	/**
	 * This is the method which display pie chart on visualize tab.
	 * @param no params passed
	 * @return void
	 */
	public void showPieChart()
	{
		resetMenuBar();
		if(graph.getParent() == visualizePanel)
		{
			visualizePanel.remove(graph);
		}
		if(chartPanel != null)
		{
			if(chartPanel.getParent() == visualizePanel)
			{
				visualizePanel.remove(chartPanel);
			}
		}
		//displayPanel.removeAll();

		// create map of vaccine locations to number of doses for each type
		Map<Object, Integer> mapLocationToDoses = new HashMap<Object, Integer>();

		for(int i = 1; i < tableModel.getRowCount() ; i++) {
			Object vaccineLocation = tableModel.getValueAt(i, 5);
			int numberOfDoses = mapLocationToDoses.containsKey(vaccineLocation) ? mapLocationToDoses.get(vaccineLocation) : 0;
			mapLocationToDoses.put(vaccineLocation, numberOfDoses + 1);
		}

		// create pie chart based on data set
		JFreeChart chart = createChart(createDataset(mapLocationToDoses) );
		chartPanel =  new ChartPanel( chart );
		visualizePanel.add(chartPanel,BorderLayout.CENTER);
		//displayPanel.add(chartPanel, BorderLayout.CENTER);
		visualizeTab.setBackground(selected);
		windowFrame.setVisible(true);

	}

	/**
	 * This is the method which create dataset for bar chart.
	 * @param mapVaccineTypeToDoses
	 * @return CategoryDataset
	 */
	private CategoryDataset createDataSet( Map<Object,Integer> mapVaccineTypeToDoses) {

		final String doses = "Doses";
		final DefaultCategoryDataset dataset =
				new DefaultCategoryDataset( );

		for (Map.Entry<Object, Integer> pair : mapVaccineTypeToDoses.entrySet()) {
			// add number of doses, vaccine type, doses keyword to the data set for each value in map
			dataset.addValue(new Double(pair.getValue()), (Comparable) pair.getKey(), doses);
		}

		return dataset;
	}

	/**
	 * This is the method which create dataset for the pie chart.
	 * @param mapTypeToDoses
	 * @return PieDataset
	 */
	private static PieDataset createDataset(Map<Object, Integer> mapTypeToDoses) {

		DefaultPieDataset dataset = new DefaultPieDataset( );
		for (Map.Entry<Object, Integer> pair : mapTypeToDoses.entrySet()) {
			//add vaccine type, number of doses to the data set for each map value
			dataset.setValue((Comparable) pair.getKey(), new Double(pair.getValue()));
		}
		return dataset;
	}

	/**
	 * This is the method which create pie chart based on dataset passed.
	 * @param dataset
	 * @return JFreeChart
	 */
	private static JFreeChart createChart( PieDataset dataset ) {

		//create pie chart based on the dataset
		JFreeChart chart = ChartFactory.createPieChart(
				"Doses by vaccine location",   // chart title
				dataset,          // data
				true,             // include legend
				true,
				false);

		return chart;
	}
}