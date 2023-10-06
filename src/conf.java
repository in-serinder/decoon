
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.jar.JarException;

public class conf {
    private static String JSON_PATH="conf.json";



      //  cet_json(json_config());




    /**
     *
     *
     *
     * **/
    public static IOException cet_json(String CONTENT,boolean over_bl){
        IOException err = new IOException();

        try {

            FileWriter path = new FileWriter("conf.json", over_bl);
            path.write("\n" + CONTENT);
            path.close();
        } catch (IOException e) {
            err = e;

        }
        return err;
    }

    public static String json_config(String key_path){
        Date Cur_time=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String cur_time_str=simpleDateFormat.format(Cur_time);
        String content="{\n"
                + "    \"Date\": \""+Cur_time+"\",\n"
                + "     \"KEY_path\":\""+ key_path +"\""
                + "\n}"
                +"\n\n\n";
        return content;
    }

    public static String json_read_KEY_PATH(String path){
        String conetct="";
    try{
        JSONParser parser =new JSONParser();
        JSONObject jsonObject=(JSONObject) parser.parse(new FileReader(path));
        conetct=(String)jsonObject.get("KEY_path");
        if (conetct.isEmpty()){
            conetct="无有效数据,或出现路径错误";
        }
    }catch (JarException e){
        e.printStackTrace();
    }catch (IOException e1){
        e1.printStackTrace();
    }catch (ParseException e2){
        e2.printStackTrace();
    }
    return conetct;
    }

    public static String json_read_date_PATH(String path){
        String conetct="";
        try{
            JSONParser parser =new JSONParser();
            JSONObject jsonObject=(JSONObject) parser.parse(new FileReader(path));
            conetct=(String)jsonObject.get("Date");
            if (conetct.isEmpty()){
                conetct="无有效数据,或出现路径错误";
            }
        }catch (JarException e){
            e.printStackTrace();
        }catch (IOException e1){
            e1.printStackTrace();
        }catch (ParseException e2){
            e2.printStackTrace();
        }
        return conetct;
    }

    public static void file_suffx_save(File file_name,File wfile){
        String siffx="";
     String filename1=file_name.getName();
     int dotindex=filename1.lastIndexOf('.');
     if (dotindex>0&&dotindex<filename1.length()-1){
       siffx=filename1.substring(dotindex+1).toLowerCase();
       System.out.println(siffx+siffx.length());
     }else {
         siffx="";
     }
    try {
        FileWriter writer=new FileWriter(wfile,true) ;
        writer.write(siffx+siffx.length());
        writer.close();
    }catch (IOException e){
        e.printStackTrace();
    }

    }

    public static String file_suffx_thuen(String file_name){
        String suffx="";
    try {
        RandomAccessFile file=new RandomAccessFile(file_name,"r");
            long file_length=file.length();
            StringBuffer buffer=new StringBuffer();
            long suffx_len=file_length-1;
            if (suffx_len<0){
                suffx_len=0;
            }
            file.seek(suffx_len);
            int read;
            while ((read=file.read())!=-1){
                buffer.append((char)read);
            }
            //buffer tepp
            String aa=buffer.toString();
            int cut=Integer.parseInt(aa)+1;
            byte[] hsuffx=new byte[cut];
            long pointer_suffx=file_length-cut;
            if (pointer_suffx<0){
                pointer_suffx=0;
                cut=(int)file_length;
                hsuffx=new byte[cut];
            }
            file.seek(pointer_suffx);
            file.readFully(hsuffx);
            //done
        byte[] ary= Arrays.copyOf(hsuffx,hsuffx.length-1);
        StringBuilder tran=new StringBuilder();
        for (byte b:hsuffx){
            tran.append((char)b);
        }
        suffx="."+tran.toString().substring(0,tran.toString().length()-1);


    }catch (IOException e1){
        e1.printStackTrace();
    }catch (NumberFormatException e2){
        e2.printStackTrace();
    }
    return suffx;
    }

    public static String view_suffx(String filename){
        if (filename==null||filename.isEmpty()){
            return "";
        }
        int dotindex=filename.lastIndexOf('.');
        if (dotindex==-1||dotindex==filename.length()-1){
            return "";
        }
        return "."+filename.substring(dotindex+1);
    }


}
