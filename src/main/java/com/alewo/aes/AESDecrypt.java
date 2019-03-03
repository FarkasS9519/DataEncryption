package com.alewo.aes;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AESDecrypt {

    /**
     * Decrypts the String with the help of the secret.
     *
     * @example
     * encrypted username: UkMSiJRLZ8Ruyvw1tcF6rw==
     * secret: 696fc516953fdd5918f07a41ba8899d74e628f5f13ce7c7c1cce1ebdbf5716e7
     * decrypted username: pah1230
     *
     * @param strToDecrypt = The earlier encrypted String value, read from the DB.
     * @param secret = The secret which was used for encryption.
     * @return = The original String value.
     */
    public static String decrypt(String strToDecrypt, String secret) {
        try {
            SecretKeySpec secretKey = AESEncrypt.setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
            System.err.println("Error while trying to decrypt string using AES.");
            e.printStackTrace();
        }
        return null;
    }
}
