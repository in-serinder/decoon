
import java.io.*;

public class action {


public static boolean hasfile(String file){
    File file1=new File(file);
    if (file1.exists()){
        return true;
    }else {
        return false;
    }
}

//move suffix
    public static String move_suffix(String filename){
    int dotIndex=filename.lastIndexOf('.');
    if (dotIndex>0){
        filename=filename.substring(0,dotIndex);
    }
    return filename;
    }

    public static void move_suffix_tab(String filename){
    Integer len=conf.file_suffx_thuen(filename).length();
       try {
           File file=new File(filename);
           if (!file.exists()){
               return;
           }
           RandomAccessFile raf=new RandomAccessFile(file,"rw");
           long lenth=raf.length();
           if (len>=lenth){
               raf.close();
               return;
           }
           raf.setLength(lenth-len);
           raf.close();
       }catch (IOException e){
           e.printStackTrace();
       }
       }

    public static void TEMP_FILE(String filepath,boolean copyordel) throws IOException {
        try {
            File sfile = new File(filepath);
            File TEMP = new File(sfile.getName());
           // System.out.println(TEMP.toString());
            if (sfile.exists()) {
                FileInputStream filein = new FileInputStream(filepath);
                FileOutputStream fileout = new FileOutputStream(TEMP);
                byte[] buffer = new byte[10240];
                int len;
                while ((len = filein.read(buffer)) !=-1) {
                    fileout.write(buffer, 0, len);
                }
                filein.close();
                fileout.close();
                if (!copyordel) {
                    TEMP.delete();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
