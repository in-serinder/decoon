import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.awt.*;


public class file_dec {

    private static Integer file_number=0;
    private static Double file_size;

    public static void TEXT_DEC() {
        Stage Mstage=new Stage();
        BorderPane BPane = new BorderPane();
        Scene scene = new Scene(BPane);
        Mstage.setScene(scene);
        BPane.setPrefSize(650,330);
        scene.getStylesheets().add("/css/concise.css");
        javafx.scene.image.Image icon=new Image("file:src/img/file.png");
        Mstage.getIcons().add(icon);
        Mstage.setTitle("File encryption current: idle");//busy

        /*
        right top pane
         */



        Pane top_info=new Pane();
        top_info.setStyle("-fx-border-color: #232222;-fx-border-width: 1px");
        top_info.setPrefSize(400,100);
        top_info.setLayoutX(220);
        top_info.setLayoutY(5);
        Label list_num_lab=new Label("当前列表中任务数:"+file_number);
        list_num_lab.setLayoutX(3);list_num_lab.setLayoutY(3);

        Label Total_file_size=new Label("文件总大小:"+file_size);
        Total_file_size.setLayoutX(2);Total_file_size.setLayoutY(20);

        Hyperlink add_file=new Hyperlink("添加文件(add files)");
        add_file.setLayoutX(3);add_file.setLayoutY(36);

        Hyperlink move_all_file=new Hyperlink("清空所有文件(Clear All)");
        move_all_file.setLayoutX(3);move_all_file.setLayoutY(55);

        Pane top_ind_pane=new Pane();
        top_ind_pane.setPrefSize(250,100);
        top_ind_pane.setStyle("-fx-border-color: #232222;-fx-border-width: 1px");
        top_ind_pane.setLayoutX(150);

        top_info.getChildren().addAll(list_num_lab,Total_file_size,add_file,move_all_file,top_ind_pane);
        /*
        left pane
         */
        String list_title="File list:\n";
        Label File_list=new Label(list_title);

        Integer left_pane_hight=320;
        Integer left_pane_width=180;
        ScrollPane scrollPane_filearea=new ScrollPane();
        scrollPane_filearea.setPrefSize(left_pane_width,left_pane_hight);
        Pane ttt=new Pane(File_list);
        ttt.setPrefSize(left_pane_width,left_pane_hight);
        scrollPane_filearea.setContent(ttt);
        Pane file_area=new Pane();
        file_area.getChildren().addAll(scrollPane_filearea);
        file_area.setStyle("-fx-border-color: #232222;-fx-border-width: 1px");
        file_area.setPrefSize(left_pane_width,left_pane_hight);
        file_area.setLayoutY(5);
        file_area.setLayoutX(15);
        Pane root=new Pane(file_area);
        root.getChildren().add(top_info);
        BPane.setCenter(root);
        Mstage.show();
    }

}
