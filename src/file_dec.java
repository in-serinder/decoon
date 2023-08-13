import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;



public class file_dec {
    private static String private_key="";
    private static String public_key="";

    public static void TEXT_DEC() {
        Stage Mstage=new Stage();
        BorderPane BPane = new BorderPane();      //set size
        Scene scene = new Scene(BPane);
        Mstage.setScene(scene);
        BPane.setPrefSize(650,330);
        
        Mstage.show();
    }

}
