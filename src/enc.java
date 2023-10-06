import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;



public class enc {
    private static final String ENCRYPTION_KEY = KEY.KEY_GAIN(new File(conf.json_read_KEY_PATH("conf.json")));

    //private static String KEY_n= KEY.KEY_GAIN(new File(conf.json_read("conf.json")));
    public static String TEXT_ENC(String plain_text){
        String enc_text="";
        try{
            SecretKeySpec keySpec=generateKey();
            Cipher cipher=Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE,keySpec);
            //enc_steam
            byte[] enc_bytes=cipher.doFinal(plain_text.getBytes(StandardCharsets.UTF_8));   //ucode
            enc_text= Base64.getEncoder().encodeToString(enc_bytes);
            if (enc_text.isEmpty()){
                enc_text="null";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return enc_text;
    }



    public static  void  enc_file(File input_file,File output_file){
        try {
            SecretKeySpec keySpec=generateKey();
            Cipher cipher=Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE,keySpec);

            byte[] file_byte= Files.readAllBytes(input_file.toPath());
            byte[] enc_byte=cipher.doFinal(file_byte);

            String encodedata=Base64.getEncoder().encodeToString(enc_byte);
            Files.write(output_file.toPath(),encodedata.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE);
            System.out.println(output_file.toPath().toString());


        }catch (Exception e){
            e.printStackTrace();
        }
    }

/*
key generate
 */
    private static SecretKeySpec generateKey() throws Exception {
        byte[] keyBytes = ENCRYPTION_KEY.getBytes(StandardCharsets.UTF_8);
        SecretKeySpec secretKey = new SecretKeySpec(keyBytes, "AES");
        return secretKey;
    }



}
