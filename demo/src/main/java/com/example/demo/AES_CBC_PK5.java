package com.example.demo;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class AES_CBC_PK5 {
    public String stringKey = "haachamahaachamahaachamahaachama";
    public byte[] iv = {4,2,0,2,4,7,2,3,6,4,1,5,7,8,5,8};
    IvParameterSpec ivp;
    SecretKey secretKey;

    public AES_CBC_PK5(){
        secretKey = generator();
        ivp = new IvParameterSpec(iv);
    }

    public byte[] Encrypt( String msg) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivp);
        byte[] byteCipherText = cipher.doFinal(msg.getBytes("UTF-8"));
        System.out.println("加密結果的Base64編碼：" + Base64.getEncoder().encodeToString(byteCipherText));

        return byteCipherText;
    }

    public String Decrypt(String msg) throws Exception {
        SecretKey secretKey = generator();
        byte[] cipherText = Base64.getDecoder().decode(msg);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivp);
        byte[] decryptedText = cipher.doFinal(cipherText);
        String strDecryptedText = new String(decryptedText);
        return strDecryptedText;
    }
    public SecretKey generator(){
        byte[] decodedKey = Base64.getDecoder().decode(stringKey);
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES"); 
    }
}
