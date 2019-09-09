package com.tangyin.cmp.token.security;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AES {
    static final String ALGORITHM = "AES/ECB/NoPadding";
    private static final String TAG = "AES";
    private static final String ENCODING = "UTF-8";

    public AES() {
    }

    public static byte[] encryptByAES(String content, String password) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
            int blockSize = cipher.getBlockSize();
            byte[] byteContent = content.getBytes();
            int plaintextLength = byteContent.length;
            if (plaintextLength % blockSize != 0) {
                plaintextLength += blockSize - plaintextLength % blockSize;
            }

            byte[] plaintext = new byte[plaintextLength];
            System.arraycopy(byteContent, 0, plaintext, 0, byteContent.length);
            int len = password.getBytes().length;
            if (len % 16 != 0) {
                len += 16 - len % 16;
            }

            byte[] newpass = new byte[len];
            System.arraycopy(password.getBytes(), 0, newpass, 0, password.getBytes().length);
            SecretKeySpec keySpec = new SecretKeySpec(newpass, "AES");
            cipher.init(1, keySpec);
            byte[] result = cipher.doFinal(plaintext);
            return result;
        } catch (Exception var11) {
            var11.printStackTrace();
            return null;
        }
    }

    public static String decryptByAES(byte[] content, String password) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
            int blockSize = cipher.getBlockSize();
            int plaintextLength = content.length;
            if (plaintextLength % blockSize != 0) {
                plaintextLength += blockSize - plaintextLength % blockSize;
            }

            byte[] plaintext = new byte[plaintextLength];
            System.arraycopy(content, 0, plaintext, 0, content.length);
            int len = password.getBytes().length;
            if (len % 16 != 0) {
                len += 16 - len % 16;
            }

            byte[] newpass = new byte[len];
            System.arraycopy(password.getBytes(), 0, newpass, 0, password.getBytes().length);
            SecretKeySpec keySpec = new SecretKeySpec(newpass, "AES");
            cipher.init(2, keySpec);
            byte[] result = cipher.doFinal(content);
            return new String(result, "UTF-8");
        } catch (Exception var10) {
            var10.printStackTrace();
            return null;
        }
    }
}

