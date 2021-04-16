import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CSV {

    /**
     * Reads a Comma Separated Variable file into a 2D array of strings
     * @param filePath String path of file to read
     * @return String[][] 2D array of the values in the CSV
     */
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
                System.out.println("File not Found");
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

    /**
     * Writes the input 2D array of strings to a Comma Separated Variable file
     * @param data String[][] data to be written to CSV
     * @param filePath String path to which file is created
     * @param fileName String name of the created file
     */
    public static void writeCSV(String[][] data, String filePath, String fileName) {
        try {
            FileWriter writer = new FileWriter(new File(filePath, fileName));
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

