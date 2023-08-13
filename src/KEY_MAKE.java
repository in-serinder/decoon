import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;

public class KEY_MAKE {

    private static String KEY_DATE="key更新日期:";
    private static String Save_path;
    private static String Used_path;



    public static void key_make(){
        BorderPane BPane=new BorderPane();
        Stage Mstage=new Stage();
        Scene scene=new Scene(BPane,530,300);
        Mstage.setScene(scene);
        Mstage.setResizable(false);
        javafx.scene.image.Image icon=new Image("file:src/key.png");
        Mstage.getIcons().add(icon);
        scene.getStylesheets().add("/css/darcula.css");

        TextArea key_input_area=new TextArea();
        TextField used_path=new TextField();
        TextField save_path=new TextField();
        Button used_path_but=new Button("....");
        Button save_path_but=new Button("....");
        Button run_make_but=new Button("进行");
        Label key_data=new Label();
        Label key_info=new Label();
        Label prigress=new Label();

        used_path.setPromptText("使用秘钥路径");
        save_path.setPromptText("保存秘钥路径");
        key_input_area.setPromptText("32位自定义秘钥(0-9,A-Z[大写]组合的字符串)");

        used_path.setPrefSize(150,15);
        save_path.setPrefSize(150,15);
        key_input_area.setPrefSize(260,60);

        key_input_area.setLayoutY(20);
        key_input_area.setLayoutX(15);
        key_input_area.setStyle("-fx-prompt-text-fill: #8b8888");
        key_input_area.setWrapText(true);
        TextFormatter<String> textFormatter=new TextFormatter<>(change -> {
            if (change.getText().length() > 0) {
                String text = key_input_area.getText();
                int caretPosition = key_input_area.getCaretPosition();
                int lineStart = text.lastIndexOf('\n', caretPosition);
                int lineEnd = text.indexOf('\n', caretPosition);
                if (lineEnd == -1) {
                    lineEnd = text.length();
                }
                if (lineEnd - lineStart > 32) {
                    return null;
                }
            }
            return change;
        });
        key_input_area.setTextFormatter(textFormatter);

        run_make_but.setLayoutY(100);
        run_make_but.setLayoutX(124);

        key_data.setLayoutY(280);
        key_data.setLayoutX(280);

        save_path.setLayoutY(240);
        save_path.setLayoutX(15);

        save_path_but.setLayoutY(237);
        save_path_but.setLayoutX(180);

        used_path.setLayoutY(270);
        used_path.setLayoutX(15);

        used_path_but.setLayoutY(267);
        used_path_but.setLayoutX(180);

        key_info.setLayoutY(240);
        key_info.setLayoutX(280);

        key_data.setText(KEY_DATE+conf.json_read_date_PATH("conf.json"));
        used_path.setText(conf.json_read_KEY_PATH("conf.json"));
        key_info.setText(conf.json_read_KEY_PATH("conf.json"));



        //Line l1=new Line(300,0,300,350);

//
        save_path_but.setOnMouseClicked(mouseEvent -> {
            Save_path=from.save_dialog();
            save_path.setText(Save_path);
        });

        run_make_but.setOnMouseClicked(mouseEvent -> {
            if (key_input_area.getText().length()==32) {
                KEY.KEY_MAKE(key_input_area.getText(),Save_path);
                System.out.println(Save_path);
            }else{
                Label wl=new Label("输入框为空或长度不足32位");
                wl.setLayoutX(280);
                wl.setLayoutY(260);
            }
        });

        used_path_but.setOnMouseClicked(mouseEvent -> {
        conf.cet_json(conf.json_config((Used_path=from.chosefile_dialog()).replace("\\","\\\\")),false);
        });

        Pane RooT_Pane=new Pane(key_input_area,run_make_but,key_data,used_path,used_path_but,save_path,save_path_but,key_info);
        BPane.getChildren().add(RooT_Pane);
        Mstage.show();
    }
}
