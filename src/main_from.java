import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;

import javafx.scene.layout.BorderPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;




import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.MenuItem;
import java.awt.TrayIcon;
import java.awt.Font;


import java.awt.*;


public class main_from extends Application {

    private Integer Xint=15;
    private  final static String title="wirless加密";
    private String compla_in="wirless加密程序至托盘";
    private String compla_close="窗口关闭,wirless加密程序退出至托盘";
    private String compla_out="wirless加密程序显式，注意环境";


    @Override
    public void start(Stage primaryStage) {
        BorderPane BPane=new BorderPane();
        Scene scene=new Scene(BPane,250,85);
        primaryStage.setScene(scene);
        primaryStage.setTitle("项目");
        javafx.scene.image.Image icon=new Image("file:src/adp.png");
        primaryStage.getIcons().add(icon);
        primaryStage.setResizable(false);
        scene.getStylesheets().add("/css/common.css");

        CheckBox text_mode=new CheckBox("文件加密模式");
        CheckBox make_mode=new CheckBox("MAKE KEY&USED");
        CheckBox all_mode=new CheckBox("双并形式");

        text_mode.setLayoutY(Xint);
        make_mode.setLayoutY(Xint+20);
        all_mode.setLayoutY(Xint+40);

        text_mode.setLayoutX(Xint);
        make_mode.setLayoutX(Xint);
        all_mode.setLayoutX(Xint);

        all_mode.setSelected(true);

        Button Start =new Button("Strat");
        Start.setPrefSize(80,20);
        Start.setStyle("-fx-background-color: transparent;-fx-border-color: #232222;-fx-border-width: 1px");
        Start.setLayoutX(150);
        Start.setLayoutY(25);

        Start.setOnMouseEntered(mouseEvent -> {
            Start.setStyle("-fx-background-color: #3d6efb;-fx-border-color: #232222;-fx-border-width: 1px");
            //Start.getStyleClass().add("button_drop_rule");
            Start.setOpacity(0.6);
        });

        Start.setOnMouseExited(mouseEvent -> {
            Start.setStyle("-fx-background-color: transparent;-fx-border-color: #232222;-fx-border-width: 1px");
           // Start.getStyleClass().add("button_exit_rule");
            Start.setOpacity(1);
        });

        Start.setOnMouseClicked(mouseEvent -> {
            if (text_mode.isSelected()){
                //st 1
                file_dec.TEXT_DEC();
                primaryStage.hide();
            }else{
                if (make_mode.isSelected()){
                    //st2
                    KEY_MAKE.key_make();
                    primaryStage.hide();
                }else{
                    if (all_mode.isSelected()){
                        //st3
                        all_dec.ALL_DEC();
                        primaryStage.hide();
                    }
                }
            }

        //托盘事件



        });

        //关闭窗口时的托盘行为

        try {
            Platform.setImplicitExit(false);
            PopupMenu traymenu=new PopupMenu();
            MenuItem show=new MenuItem("显示窗口");
            MenuItem exit=new MenuItem("退出");
            traymenu.add(show);
            traymenu.add(exit);

            Font font=new Font("宋体",Font.PLAIN,12);

            java.awt.Image mimage=Toolkit.getDefaultToolkit().getImage(main_from.class.getResource("adp.png"));
            TrayIcon trayIcon=new TrayIcon(mimage,title,traymenu);
            trayIcon.setImageAutoSize(true);
            SystemTray systemTray=SystemTray.getSystemTray();
            systemTray.add(trayIcon);
            primaryStage.setOnCloseRequest(windowEvent -> {
            trayIcon.displayMessage(title,compla_in,TrayIcon.MessageType.INFO);
            });

            //消息框
            trayIcon.displayMessage(title,compla_in,TrayIcon.MessageType.INFO);
            show.addActionListener(actionEvent -> {
                Platform.runLater(()->primaryStage.show());
                trayIcon.displayMessage(title,compla_out,TrayIcon.MessageType.INFO);

            });
            exit.addActionListener(actionEvent -> {
                System.exit(0);
            });


            primaryStage.setOnCloseRequest(windowEvent -> {
                primaryStage.hide();
            });
        } catch (AWTException e) {
            e.printStackTrace();
        }


        Pane select_pane=new Pane(text_mode,make_mode,all_mode,Start);
        BPane.getChildren().add(select_pane);
        primaryStage.show();




        /*
        *test
        * */

        //KEY.KEY_MAKE("0DJ902J30J2039DJ203JD023JDJCJ320","M:\\");
        //System.out.println(KEY.KEY_GAIN(new File("M:\\KEY.nec")));
        //conf.cet_json(conf.json_config("C://"),true);
        //System.out.println(conf.json_read_date_PATH("conf.json"));
        //System.out.println(enc.TEXT_ENC("你妈死了"));
        //System.out.println(dec.DEC_TEXT("JDbhMQYXRjtXxisQVap4QA=="));
        //chinese sup==PKCS#5 2^31 xo ONPADDING 2^36 chinese(utf-8 full 3bytes)
    }

    public static void main(String[] args) {
        launch(args);
    }
}
