import java.util.ArrayList;

public class StoreData {
    private ArrayList<String> data;     // Stores the rows in ArrayList
  
    StoreData(){
        data = new ArrayList<>();
    }

    public setData(String id, String lname,String fname,String type, String date, String location  ){
        String row = id+","+lname+","+fname+","+type+","+date+","+location;
        data.add(row);
    }

    public ArrayList<String> getData(){
        return data;
    }

    public int getRowCount(){
        return data.size();      // Returns number of rows currently in ArrayList
    }

    public boolean deleteRow(String id){
        for(String row1 : data){
            if(row1.contains(id))   // Searching for row with id
            {
                data.remove(row1);
                return true;
            }
        }
        return false;    // False if id not found
    }
}