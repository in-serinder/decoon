import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.io.IOException;
import javax.imageio.ImageIO;

public class graphical {

    public static void image_overly(File input_img,File output_img,File overly_img){
        try {
            BufferedImage in_img=ImageIO.read(input_img);
            BufferedImage out_img=new BufferedImage(in_img.getWidth(),in_img.getHeight(),
                    BufferedImage.TYPE_INT_RGB);//reg svb
            Graphics2D graphics2D=out_img.createGraphics();

            BufferedImage overlay_img=ImageIO.read(overly_img);
            graphics2D.drawImage(overlay_img,0,0,null);
            ImageIO.write(out_img,"jpg",output_img);

        }catch (IOException e){
            e.printStackTrace();
        }

    }


   public static  void applyGB(ImageView imageView){
        GaussianBlur blur=new GaussianBlur();
        imageView.setEffect(blur);
    }


}
