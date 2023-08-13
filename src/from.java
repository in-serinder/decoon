import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class from {


    public static String save_dialog() {
        Stage stage=new Stage();
        String path="";
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save File");
        fileChooser.setInitialFileName("KEY.nec"); 

        File file = fileChooser.showSaveDialog(stage); 

        if (file != null) {
            String filePath = file.getAbsolutePath();
            return filePath;
        }
        return path;
    }

        public static String chosefile_dialog() {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select File");

            FileChooser.ExtensionFilter filter=new FileChooser.ExtensionFilter("nec(*.nec)","*.nec");
            FileChooser.ExtensionFilter filter2=new FileChooser.ExtensionFilter("其他文件other file(*.*)","*.*");
            fileChooser.getExtensionFilters().addAll(filter,filter2);
            File selectedFile = fileChooser.showOpenDialog(new Stage());

            if (selectedFile != null) {
                return selectedFile.getAbsolutePath();
            } else {
                return null;
            }
        }



        public static String progress_text(int current)
    {
        int total = 100;
        int percentage = (int)((float)current / total * 100);
        int barLength = 50;
        int completedLength = (int)((float)current / total * barLength);
        int remainingLength = barLength - completedLength - 1;
        String progressBar = "[";
        for (int i = 0; i < completedLength; i++)
        {
            progressBar += "-";
        }
        if (percentage >= 100)
        {
            progressBar += "\\";
        }
        else
        {
            progressBar += "<";
        }
        for (int i = 0; i < remainingLength; i++)
        {
            progressBar += ".";
        }
        progressBar += "]";
        String progress = "{progressBar} {percentage}%";
        return progress;
    }
}
