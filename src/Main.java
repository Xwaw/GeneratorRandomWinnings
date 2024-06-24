import com.google.gson.Gson;

import javax.swing.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main extends JFrame {
    // There will probably be options here if I need them in the future.
    public static void main(String[] args) {
        final String JsonRecover = """
                {"randomCash":false,
                "spaceOfRandomCash":10,
                "openGeneratedTxt":false,
                "cupsToSetAt":"2",
                "generateCupStyle":"Constant",
                "filePath":"C:/Users/admin/Desktop",
                "rangeRandomTime":[1,24],
                "cup2":[1.0,2.0],
                "cup3":[2.0,3.0],
                "cup4":[3.0,4.0],
                "cup5":[4.0,5.0]}
                """;

        final Path filePathJson = Paths.get("optionsSave.json");
        if(!filePathJson.toFile().exists()){
            try(FileWriter fileWriter = new FileWriter("optionsSave.json")){
                fileWriter.write(JsonRecover);

                JOptionPane.showMessageDialog(null, "Information: optionsSave file recovered. Open program again.");

                return;
            }catch(IOException e){
                JOptionPane.showMessageDialog(null, "Error writing to file: " + e.getMessage());
            }
        }

        try {
            MainWindow mainWindow = new MainWindow();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error with mainWindow: " + e.getMessage());
        }
    }
}
