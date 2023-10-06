import javafx.scene.control.TextField;
import javafx.scene.input.TransferMode;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class from {


    public static String save_dialog(String save_name) {
        Stage stage=new Stage();
        String path="";
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save File");
        fileChooser.setInitialFileName(save_name); // 指定默认文件名

        File file = fileChooser.showSaveDialog(stage); // 打开保存文件对话框

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



    public static String choseimg_dialog() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select File");

        FileChooser.ExtensionFilter filter=new FileChooser.ExtensionFilter("eimg(*.neimg)加密形式图像","*.eimg");
        FileChooser.ExtensionFilter filter2=new FileChooser.ExtensionFilter("img(*.png,*.jpg,*.webp,*.dump)图像文件","*.png","*.jpg","*.webp","*.dump");
        FileChooser.ExtensionFilter filter3=new FileChooser.ExtensionFilter("其他文件other file(*.*)","*.*");
        fileChooser.getExtensionFilters().addAll(filter,filter2,filter3);
        File selectedFile = fileChooser.showOpenDialog(new Stage());

        if (selectedFile != null) {
            return selectedFile.getAbsolutePath();
        } else {
            return null;
        }
    }

    public static  String textfield_path(TextField textField){

        textField.setOnDragOver(dragEvent -> {
            if (dragEvent.getDragboard().hasFiles()) {
                dragEvent.acceptTransferModes(TransferMode.COPY);
            }
            dragEvent.consume();
        });

        textField.setOnDragDropped(dragEvent -> {
            String file_path="";
            if (dragEvent.getDragboard().hasFiles()){
                file_path=dragEvent.getDragboard().getFiles().get(0).getAbsolutePath();
                textField.setText(file_path);
            }
            dragEvent.setDropCompleted(true);
            dragEvent.consume();
        });
        return  textField.getText();
    }


        public static String progress_text(int current)
    {
        int total = 100;
        int percentage = (int)((float)current / total * 100);
        int barLength = 40;
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
        String progress = String.format("%s %d%%", progressBar.toString(), percentage);
        return progress;
    }
}
