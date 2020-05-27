package com.github.haliibobo.learn.redis;

import java.nio.charset.Charset;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

public class AESUtils {
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    /**
     * 加密后的结果进行base64编码
     * 
     * @param content
     * @param hexKey
     * @param hexIv
     * @return
     */
    public static String encryptBase64(String content, String hexKey, String hexIv) {
        try {
            byte[] result = encrypt(content.getBytes(UTF_8), hexKey, hexIv);
            return Base64.encodeBase64URLSafeString(result);
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 对数据先做base64解密，然后再做aes解密
     * 
     * @param base64String
     * @param hexKey
     * @param hexIv
     * @return
     */
    public static String decryptBase64(String base64String, String hexKey, String hexIv) {
        try {
            byte[] value = decrypt(Base64.decodeBase64(base64String), hexKey, hexIv);// 解密
            if (value != null) {
                return new String(value, UTF_8);
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static byte[] encrypt(byte[] content, String hexKey, String hexIv) {
        try {
            return aes(content, hexKey, hexIv, Cipher.ENCRYPT_MODE);
        } catch (Exception e) {
        }
        return null;
    }

    public static byte[] decrypt(byte[] content, String hexKey, String hexIv) {
        try {
            return aes(content, hexKey, hexIv, Cipher.DECRYPT_MODE);
        } catch (Exception e) {
        }
        return null;
    }

    private static byte[] aes(byte[] content, String hexKey, String hexIv, int mode) {
        try {
            Cipher aesCBC = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec key = new SecretKeySpec(HexUtils.fromHexString(hexKey), "AES");
            IvParameterSpec iv = new IvParameterSpec(HexUtils.fromHexString(hexIv));
            aesCBC.init(mode, key, iv);
            byte[] result = aesCBC.doFinal(content);
            return result;
        } catch (Exception e) {
        }
        return null;
    }

    public static void main(String[] args) {
        String key = "5FE62F39F8EB3A52720D2201637A57DF";
        String iv ="23D1131FAF9BA826B24B81F40CEF2BE1";
        String result = AESUtils.encryptBase64("aaaaaaa", key, iv);
        System.out.println(result);


    }
}
