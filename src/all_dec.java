import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


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
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;


public class all_dec {

    private static String private_key="";
    private static String public_key="";
    private static Integer pane_top=4;

    public static void ALL_DEC(){
        BorderPane BPane=new BorderPane();
        Stage Mstage=new Stage();
        Scene scene=new Scene(BPane,1024,550);
        Mstage.setScene(scene);
        javafx.scene.image.Image icon=new Image("file:src/img/adp.png");
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

                                    java.awt.Image mimage=Toolkit.getDefaultToolkit().getImage(main_from.class.getResource("img/xwind.png"));
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







        //TEXT_pane(675,540);
        //IMG_pane(335,540);
        /*
        Img_pane show
         */
        Label Img_lab_title=new Label("Image encryption");
        Img_lab_title.setLayoutX(120);
        Img_lab_title.setLayoutY(15);
        Image default_oimg=new Image("img/oimg.png");
        Image default_eimg=new Image("img/eimg.png");
        ImageView original_oimg=new ImageView(default_oimg);
        ImageView encryption_eimg=new ImageView(default_eimg);
        Integer defimgwidth=120;
        Integer defimghight=85;
        Integer defimglochight=75;
        original_oimg.prefHeight(defimghight);
        original_oimg.prefWidth(defimgwidth);
        original_oimg.setX(15);
        original_oimg.setY(defimglochight);
        encryption_eimg.prefHeight(defimghight);
        encryption_eimg.prefWidth(defimgwidth);
        encryption_eimg.setX(200);
        encryption_eimg.setY(defimglochight);
        original_oimg.autosize();

        Label arrow_c=new Label("←→");
        arrow_c.setLayoutX(155);
        arrow_c.setLayoutY(115);

        TextField input_img=new TextField();
        TextField output_img=new TextField();
        input_img.setPrefSize(250,15);
        input_img.setPromptText("将文件拖入此处或选择一个文件路径");
        input_img.setLayoutX(15);
        input_img.setLayoutY(190);
        output_img.setPrefSize(250,15);
        output_img.setPromptText("选择输出路径");
        output_img.setLayoutX(15);
        output_img.setLayoutY(220);

        Button inimgbt=new Button("Chose");
        Button outimgbt=new Button("Chose");
        inimgbt.setPrefSize(50,13);
        inimgbt.setLayoutX(275);
        inimgbt.setLayoutY(190);
        outimgbt.setPrefSize(50,13);
        outimgbt.setLayoutX(275);
        outimgbt.setLayoutY(220);

        Button startenc_img=new Button("encryption→");
        Button startdec_img=new Button("decrypt←");
        startenc_img.setPrefSize(140,15);
        startenc_img.setLayoutX(15);
        startenc_img.setLayoutY(275);
        startdec_img.setPrefSize(140,15);
        startdec_img.setLayoutX(170);
        startdec_img.setLayoutY(275);

        Label img_progress=new Label("Progress:");
        img_progress.setLayoutY(320);
        img_progress.setLayoutX(15);

        /*
        function
         */

        File in_img = new File(from.textfield_path(input_img));
        //System.out.println(in_img.toString());



        String sout_img=from.textfield_path(output_img);
            output_img.setText(sout_img);
            File out_img=new File(sout_img);
            sout_img=null;

            original_oimg.setFitWidth(defimgwidth);
            original_oimg.setFitHeight(defimghight);
            encryption_eimg.setFitWidth(defimgwidth);
            encryption_eimg.setFitHeight(defimghight);


        //action
    startenc_img.setOnAction(actionEvent -> {
        String img_outpath=output_img.getText();//+"\\"+"enc_post.eimg"
        try{
        if (!input_img.getText().isEmpty()){
            //graphical.image_overly(in_img,new File("cag.png"),new File("encb.png"));
           ;
            img_progress.setText("Progress:"+from.progress_text(1));

            original_oimg.setImage(new Image(new File(input_img.getText()).toURI().toString()));
            encryption_eimg.setImage(new Image(new File(input_img.getText()).toURI().toString()));
            img_progress.setText("Progress:"+from.progress_text(11));
            enc.enc_file(new File(input_img.getText()),new File(img_outpath));
            img_progress.setText("Progress:"+from.progress_text(97));
            graphical.applyGB(encryption_eimg);

            conf.file_suffx_save(new File(input_img.getText()),new File(output_img.getText()));
            img_progress.setText("Progress:"+from.progress_text(99));
            //System.out.println(img_outpath);
            if (action.hasfile(img_outpath)){
                img_progress.setText("Progress:"+from.progress_text(100));
            }else {
            img_progress.setText("Progress:"+"Filed");}

        }
        }catch (Exception e){
            e.printStackTrace();
        }
    });

    startdec_img.setOnAction(actionEvent -> {
        String pathin=input_img.getText();
        String pathout=output_img.getText();
        String TEMP_F=new File(pathin).getName();
        try{
            action.TEMP_FILE(pathin,true);
            img_progress.setText("Progress:"+from.progress_text(11));
            action.move_suffix_tab(new File(pathin).getName());
            //System.out.println("temp is"+TEMP_F+"\t"+new File(pathin).getName().toString());
            ;
            img_progress.setText("Progress:"+from.progress_text(37));

            //System.out.println(pathin+"\n"+pathout);
            dec.decrypt_file(new File(TEMP_F),new File(pathout));
            img_progress.setText("Progress:"+from.progress_text(81));

            original_oimg.setImage(new Image(new File(pathout).toURI().toString()));
            encryption_eimg.setImage(new Image(new File(pathout).toURI().toString()));

            //conf.file_suffx_save(new File(pathin),new File(pathout));

            img_progress.setText("Progress:"+from.progress_text(90));
            graphical.applyGB(encryption_eimg);
            action.TEMP_FILE(TEMP_F,false);   ///tasdel
            if (action.hasfile(pathout)){
                img_progress.setText("Progress:"+from.progress_text(100));
            }else {
                img_progress.setText("Progress:"+"Filed");}


        }catch (Exception e){
            e.printStackTrace();
        }
    });

    //path action
        inimgbt.setOnAction(actionEvent -> {
            try{
                input_img.setText(from.choseimg_dialog());
            }catch (Exception e){
                e.printStackTrace();
            }
        });

        outimgbt.setOnAction(actionEvent -> {
            Path path= Paths.get(input_img.getText());
            String suffx_filename=conf.view_suffx(input_img.getText());
            String suffx_file_byte=conf.file_suffx_thuen(input_img.getText());
            String firstname="";
            try {
                if (suffx_filename.equals(".eimg")) {
                     //firstname= action.move_suffix(new File(outimgbt.getText()).toString()) +suffx_file_byte;
                    firstname=action.move_suffix(path.toString())+suffx_file_byte;
                     System.out.println("mums:\t"+firstname+"\nlike"+suffx_file_byte);
                }else if (!suffx_filename.equals(".eimg")){
                    firstname=action.move_suffix(path.getFileName().toString())+".eimg";
                    //System.out.println(firstname+firstname.length()+"\t"+suffx_filename+suffx_filename.length()+"\t"+suffx_file_byte+suffx_file_byte.length());
                }
                if (!input_img.getText().isEmpty()){
                output_img.setText(from.save_dialog(firstname));
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        });


        /*
        File encryption embed into Img encryption panel
         */
        javafx.scene.layout.Pane File_pane=new javafx.scene.layout.Pane();
        File_pane.setStyle("-fx-border-color: #232222;-fx-border-width: 1px");
        File_pane.setPrefSize(335,170);
        File_pane.setLayoutY(370);
        Label File_pane_title=new Label("File encryption");
        File_pane_title.setLayoutY(10);
        File_pane_title.setLayoutX(122);


        IMG_pane.setLayoutX(685);
        IMG_pane.setLayoutY(4);
        TEXT_pane.setLayoutY(4);
        TEXT_pane.setLayoutX(4);
        IMG_pane.getChildren().addAll(Img_lab_title,File_pane,original_oimg,encryption_eimg,arrow_c,input_img,output_img,inimgbt,outimgbt,startenc_img,startdec_img,img_progress);
        File_pane.getChildren().addAll(File_pane_title);
        Pane root_pane=new Pane(TEXT_pane,IMG_pane); //root pane
        BPane.getChildren().add(root_pane);
        Mstage.show();

    }
}
