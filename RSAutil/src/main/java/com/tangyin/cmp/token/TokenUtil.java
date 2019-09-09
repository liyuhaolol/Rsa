package com.tangyin.cmp.token;

import com.alibaba.fastjson.JSONObject;
import com.tangyin.cmp.token.security.AES;
import com.tangyin.cmp.token.security.HexUtils;
import com.tangyin.cmp.token.security.RSA;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class TokenUtil {
    private static final String RSA_PUBLIC_KEY = Config.getValue("rsa_public_key");

    public TokenUtil() {
    }

    public static String getToken(String jsonString) {
        return createToken(jsonString);
    }

    private static String createToken(String jsonString) {
        String scanToken = null;

        try {
            long timeStamp = System.currentTimeMillis();
            String APP_ID = "-" + timeStamp + "00";
            byte[] encode4Aes = AES.encryptByAES(jsonString, APP_ID);
            String bytes2Hex = HexUtils.bytes2Hex(encode4Aes);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("timeStamp", timeStamp);
            jsonObject.put("EncrypedStr", bytes2Hex);
            String jsontoString = jsonObject.toJSONString();
            byte[] bytes = jsontoString.getBytes();
            scanToken = RSA.encryptByPublicKey(bytes, RSA_PUBLIC_KEY);
        } catch (Exception var10) {
            var10.printStackTrace();
        }

        return scanToken;
    }

    private static String map2str(Map<String, Object> paramsMap) {
        StringBuffer content = null;
        if (paramsMap != null && paramsMap.size() > 0) {
            content = new StringBuffer("{");
            Iterator var3 = paramsMap.entrySet().iterator();

            while(true) {
                while(var3.hasNext()) {
                    Map.Entry<String, Object> param = (Map.Entry)var3.next();
                    content.append("\\\"").append((String)param.getKey()).append("\\\":");
                    if (param.getValue() instanceof String) {
                        content.append("\\\"").append(param.getValue()).append("\\\",");
                    } else if (param.getValue() instanceof Integer || param.getValue() instanceof Long) {
                        content.append(param.getValue()).append(",");
                    }
                }

                content.deleteCharAt(content.toString().length() - 1).append("}");
                break;
            }
        }

        return content.toString();
    }

    private static Map<String, Object> str2map(String content) {
        Map<String, Object> paramsMap = null;
        if (content != null && content.trim().length() > 0) {
            paramsMap = new HashMap();
            content = content.trim().substring(1, content.trim().length() - 1).replaceAll("\"", "").replaceAll("\\\\", "");
            String[] temp = content.split(",");
            String[] var6 = temp;
            int var5 = temp.length;

            for(int var4 = 0; var4 < var5; ++var4) {
                String param = var6[var4];
                String[] value = param.split(":");
                paramsMap.put(value[0], value[1]);
            }
        }

        return paramsMap;
    }
}