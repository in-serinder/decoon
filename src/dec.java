import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Cipher;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;


public class dec {
    private static final String AES_ALGOR="AES";
    private static final String ENCRYPTION_KEY = KEY.KEY_GAIN(new File(conf.json_read_KEY_PATH("conf.json")));

    public static String DEC_TEXT(String plain_text){
        String dec_text="";
    try {
        SecretKeySpec secretKey = generateKey();
        Cipher cipher=Cipher.getInstance("AES");

        cipher.init(Cipher.DECRYPT_MODE, secretKey);        //add s1

        byte[] enc_bytes=Base64.getDecoder().decode(plain_text);
        byte[] dec_bytes=cipher.doFinal(enc_bytes);
        dec_text=new String(dec_bytes,StandardCharsets.UTF_8);
        if (dec_text.isEmpty()){
            dec_text="null";
        }
    }catch (Exception e){
        e.printStackTrace();
    }
        return dec_text;
    }

    public static void decrypt_file(File input_file,File output_file){
        try{
            Cipher cipher = Cipher.getInstance("AES");
            SecretKeySpec secretKeySpec = generateKey();
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

            byte[] encryptedData = new byte[(int) input_file.length()];
            FileInputStream encryptedFileInputStream = new FileInputStream(input_file);
            encryptedFileInputStream.read(encryptedData);
            encryptedFileInputStream.close();

            byte[] decryptedData = cipher.doFinal(Base64.getDecoder().decode(encryptedData));

            FileOutputStream decryptedFileOutputStream = new FileOutputStream(output_file);
            decryptedFileOutputStream.write(decryptedData);
            decryptedFileOutputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    private static SecretKeySpec generateKey() throws Exception {
        byte[] keyBytes = ENCRYPTION_KEY.getBytes(StandardCharsets.UTF_8);
        SecretKeySpec secretKey = new SecretKeySpec(keyBytes, "AES");
        return secretKey;
    }



}
