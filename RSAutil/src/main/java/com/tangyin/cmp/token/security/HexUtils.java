package com.tangyin.cmp.token.security;

public class HexUtils {
    private static final char[] HEXES = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public HexUtils() {
    }

    public static String bytes2Hex(byte[] bytes) {
        if (bytes != null && bytes.length != 0) {
            StringBuilder hex = new StringBuilder();
            byte[] var5 = bytes;
            int var4 = bytes.length;

            for(int var3 = 0; var3 < var4; ++var3) {
                byte b = var5[var3];
                hex.append(HEXES[b >> 4 & 15]);
                hex.append(HEXES[b & 15]);
            }

            return hex.toString();
        } else {
            return null;
        }
    }

    public static byte[] hex2Bytes(String hex) {
        if (hex != null && hex.length() != 0) {
            char[] hexChars = hex.toCharArray();
            byte[] bytes = new byte[hexChars.length / 2];

            for(int i = 0; i < bytes.length; ++i) {
                bytes[i] = (byte)Integer.parseInt("" + hexChars[i * 2] + hexChars[i * 2 + 1], 16);
            }

            return bytes;
        } else {
            return null;
        }
    }
}
