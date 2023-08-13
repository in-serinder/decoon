
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
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


}
