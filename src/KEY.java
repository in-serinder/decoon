

import java.io.*;


public class KEY {
    private static String FILE_NAME="KEY.nec";
    private static String HEAD_DATA="DSA7YAUDA93WDH12"; //16bit
    private static String XOR_KEY="KDJ992J30J2039DJ203JD023JDJCJ320";   //32bit
    private static String END_DATA="8DUSD3E8"; //8bit
//KEY_MAKE
    public static void KEY_MAKE(String text,String out_path){
    try {
        String wr="";
        File file=new File(out_path);
        file.createNewFile();
        FileWriter writer=new FileWriter(file);
       for (int i=0;i<text.length();i++){
           wr+=text.charAt(i)^XOR_KEY.charAt(i);
       }
        writer.write(HEAD_DATA+wr+END_DATA);
        writer.flush();
        writer.close();
    }catch (IOException e){
        e.printStackTrace();
    }
    }

    public static String KEY_GAIN(File in_path){
        String FIND_KEY="";
        String swp="";
        try {
            FileReader fileReader = new FileReader(in_path);
            char[] chars = new char[48];
            fileReader.read(chars);
            StringBuilder sb = new StringBuilder();
            for (char c : chars) {
                sb.append(c);
            }
            swp = sb.toString();
            //System.out.println(swp);
            swp = swp.substring(16);
            //System.out.println(swp);
            for (int i = 0; i < swp.length(); i++) {
                FIND_KEY += (char) (swp.charAt(i) ^ XOR_KEY.charAt(i));
                //System.out.printf("第%d次\t a:%c\t b:%c\t %c\n", i, swp.charAt(i), XOR_KEY.charAt(i), FIND_KEY.charAt(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return FIND_KEY;
    }



    private void File_w(File path){


    }

}
