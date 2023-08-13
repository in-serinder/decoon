import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;

import java.awt.*;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.MenuItem;
import java.awt.TrayIcon;

public class all_dec {

    private static String private_key="";
    private static String public_key="";
    private static Integer pane_top=4;

    public static void ALL_DEC(){
        BorderPane BPane=new BorderPane();
        Stage Mstage=new Stage();
        Scene scene=new Scene(BPane,1024,550);
        Mstage.setScene(scene);
        javafx.scene.image.Image icon=new Image("file:src/adp.png");
        Mstage.getIcons().add(icon);
        scene.getStylesheets().add("/css/darcula.css");
        Mstage.setTitle("Wirless_Decoon");
        Mstage.setResizable(false);

    //TEXT
        //input
        Pane input_control=new Pane();
        ScrollPane sc_TEXT_input=new ScrollPane();
        TextArea TEXT_input_area=new TextArea();
        sc_TEXT_input.setPrefSize(640,210);
        TEXT_input_area.setPrefSize(640,210);
        TEXT_input_area.setPromptText("输入需加密文本.....");
        TEXT_input_area.setStyle("-fx-prompt-text-fill: #8B8B8B");
        TEXT_input_area.setWrapText(true);

        sc_TEXT_input.setLayoutX(15);
        sc_TEXT_input.setLayoutY(15);
        TEXT_input_area.setLayoutY(15);
        TEXT_input_area.setLayoutX(15);
        TEXT_input_area.setTranslateZ(15);
        input_control.setPrefSize(640,50);

        input_control.setLayoutX(15);
        input_control.setLayoutY(660);
            //control
            Button nec_but=new Button("加密内容");
            Button dec_but=new Button("解密内容");
            Hyperlink copy_input=new Hyperlink("复制");
            Hyperlink clear_input_area=new Hyperlink("清空输入框");
            Label key_data_info=new Label("KEY日期:"+conf.json_read_date_PATH("conf.json"));

            nec_but.setLayoutY(5);
            dec_but.setLayoutY(5);
            key_data_info.setLayoutY(10);
            clear_input_area.setLayoutY(-25);
            copy_input.setLayoutY(-25);

            nec_but.setLayoutX(5);
            dec_but.setLayoutX(75);
            key_data_info.setLayoutX(340);
            clear_input_area.setLayoutX(580);
            copy_input.setLayoutX(540);
            input_control.getChildren().addAll(nec_but,dec_but,clear_input_area,key_data_info,copy_input);



    input_control.setLayoutX(4);
    input_control.setLayoutY(230);



        //out

        TextArea TEXT_out_area=new TextArea();
        Hyperlink clear_out_area=new Hyperlink("清空输出框");
        Hyperlink copy_out=new Hyperlink("复制");
        TEXT_out_area.setPrefSize(640,210);
        TEXT_out_area.setPromptText("解密内容.....");
        TEXT_out_area.setStyle("-fx-prompt-text-fill: #8B8B8B");
        TEXT_out_area.setWrapText(true);

        TEXT_out_area.setLayoutX(15);
        TEXT_out_area.setLayoutY(270);
            //control

            clear_out_area.setLayoutX(580);
            clear_out_area.setLayoutY(458);
            copy_out.setLayoutY(458);
            copy_out.setLayoutX(540);





        //c2
        Label tips=new Label(
                "Windows CTRL+c 复制 CTRL+V粘贴 \t Linux CTRL+Shift+C & CTRL+Shift+V" +
                "\n^E键加密，^D键解密" +
                "\nESC快捷缩小窗口到托盘，KEY包在MAKE-KEY中更改");

        Pane button_pane=new Pane(tips);
        button_pane.setLayoutY(485);
        button_pane.setLayoutX(15);
        Pane TEXT_pane=new Pane(TEXT_input_area,TEXT_out_area,input_control,button_pane,clear_out_area,copy_out);
        Pane IMG_pane=new Pane();
        TEXT_pane.setStyle("-fx-border-color: #232222;-fx-border-width: 1px");
        IMG_pane.setStyle("-fx-border-color: #232222;-fx-border-width: 1px");
        TEXT_pane.setPrefSize(675,540);
        IMG_pane.setPrefSize(335,540);

        //make
            //enen
                nec_but.setOnMouseClicked(mouseEvent -> {
                    if(!TEXT_input_area.getText().isEmpty()){
                    TEXT_out_area.setText(enc.TEXT_ENC(TEXT_input_area.getText()));
                    }else{
                        TEXT_out_area.setText("输入框为空!");
                    }
                });

                dec_but.setOnMouseClicked(mouseEvent -> {
                    if (!TEXT_input_area.getText().isEmpty()){
                        TEXT_out_area.setText(dec.DEC_TEXT(TEXT_input_area.getText()));
                    }else{
                        TEXT_out_area.setText("输入框为空!");
                    }
                });
                    //used key
                        KeyCombination keyCombination_E=new KeyCodeCombination(KeyCode.E,KeyCombination.CONTROL_DOWN);
                        KeyCombination keyCombination_D=new KeyCodeCombination(KeyCode.D,KeyCombination.CONTROL_DOWN);
                        KeyCombination keyCombination_ESC=new KeyCodeCombination(KeyCode.ESCAPE);
                        TEXT_input_area.setOnKeyPressed(keyEvent -> {
                            if (keyCombination_E.match(keyEvent)) {
                                if (!TEXT_input_area.getText().isEmpty()) {
                                    TEXT_out_area.setText(enc.TEXT_ENC(TEXT_input_area.getText()));
                                } else {
                                    TEXT_out_area.setText("输入框为空!");
                                }
                            }
                                //2
                                if (keyCombination_D.match(keyEvent)){
                                    if (!TEXT_input_area.getText().isEmpty()){
                                        TEXT_out_area.setText(dec.DEC_TEXT(TEXT_input_area.getText()));
                                    }else{
                                        TEXT_out_area.setText("输入框为空!");
                                    }
                                }
                                //end

                        });

                        scene.setOnKeyPressed(keyEvent -> {
                            if (keyCombination_ESC.match(keyEvent)){
                                //托盘事件-ESC事件
                                try {
                                    Mstage.hide();
                                    Platform.setImplicitExit(false);
                                    PopupMenu traymenu=new PopupMenu();
                                    MenuItem show=new MenuItem("显示窗口");
                                    MenuItem exit=new MenuItem("退出");
                                    traymenu.add(show);
                                    traymenu.add(exit);

                                    Font font=new Font("宋体",Font.PLAIN,12);

                                    java.awt.Image mimage=Toolkit.getDefaultToolkit().getImage(main_from.class.getResource("xwind.png"));
                                    TrayIcon trayIcon=new TrayIcon(mimage,"双并模式",traymenu);
                                    trayIcon.setImageAutoSize(true);
                                    SystemTray systemTray=SystemTray.getSystemTray();
                                    systemTray.add(trayIcon);
                                    //消息框
                                    trayIcon.displayMessage("Wirless-Decoon","程序隐藏至托盘2",TrayIcon.MessageType.INFO);
                                    show.addActionListener(actionEvent -> {
                                        Platform.runLater(()->Mstage.show());
                                    });
                                    exit.addActionListener(actionEvent -> {
                                        System.exit(0);
                                    });
                                    Mstage.setOnCloseRequest(windowEvent -> {
                                        Mstage.hide();
                                    });
                                } catch (AWTException e) {
                                    e.printStackTrace();
                                }
                            }
                        });

                //copy
                copy_input.setOnMouseClicked(mouseEvent -> {
                    Clipboard clipboard=Clipboard.getSystemClipboard();
                    ClipboardContent content=new ClipboardContent();
                    content.putString(TEXT_input_area.getText());
                    clipboard.setContent(content);
                });

                  copy_out.setOnMouseClicked(mouseEvent -> {
                     Clipboard clipboard=Clipboard.getSystemClipboard();
                     ClipboardContent content=new ClipboardContent();
                     content.putString(TEXT_out_area.getText());
                     clipboard.setContent(content);
                });


                  //clear
                clear_input_area.setOnMouseClicked(mouseEvent -> {
                    TEXT_input_area.setText("");
                });

                clear_out_area.setOnMouseClicked(mouseEvent -> {
                    TEXT_out_area.setText("");
                });




        TEXT_pane.setLayoutY(4);
        TEXT_pane.setLayoutX(4);




        IMG_pane.setLayoutX(685);
        IMG_pane.setLayoutY(4);
        Pane root_pane=new Pane(TEXT_pane,IMG_pane); //root pane
        BPane.getChildren().add(root_pane);
        Mstage.show();

    }
}
