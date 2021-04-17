import com.sun.org.apache.xpath.internal.operations.Variable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class CSV {

    //Reads a Comma Separated Variable file into a 2D array of strings

    public static String[][] readCSV(String filePath) {
        ArrayList<String[]> data = new ArrayList<String[]>();
        boolean found = false;
        String[][] output;
        // Import Data from CSV
        while(!found) {
            try {
                // Create BufferedReader to read from the filepath
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream(filePath),
                                "UTF-8"));
                // Remove BOM from start of file
                reader.mark(1);
                if (reader.read() != 0xFEFF) {
                    reader.reset();
                }
                // Add lines of CSV to List
                String addLine = "";
                while((addLine = reader.readLine()) != null) {
                    data.add(addLine.split(","));
                }
                found = true;
                reader.close();
            } catch (FileNotFoundException e) {
                found = true;
                JOptionPane.showMessageDialog(null, "File not found");
            } catch (NullPointerException e) {
                found = true;
                JOptionPane.showMessageDialog(null, "No file selected");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // Convert to 2D String Array
        output = new String[data.size()][];
        for (int i = 0; i < data.size(); i++) {
            output[i] = data.get(i);
        }
        return output;
    }

    //Writes the input 2D array of strings to a Comma Separated Variable file

    public static void writeCSV(String[][] data, String filePath) {
        try {
            FileWriter writer = new FileWriter(new File(filePath));
            for(int i = 0; i < data.length; i++) {
                writer.append(String.join(",", data[i]));
                writer.append("\n");
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

