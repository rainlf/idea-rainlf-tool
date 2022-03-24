package com.rainlf.component.encode;

import com.google.common.hash.Hashing;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author : rain
 * @date : 3/22/2022 11:39 AM
 */
public class EncodeManager implements EncodeManagerI {

    @Override
    public String md5Encrypt(String input) {
        return Hashing.md5().newHasher().putString(input, StandardCharsets.UTF_8).hash().toString();
    }

    @Override
    public String urlEncode(String input) {
        return URLEncoder.encode(input, StandardCharsets.UTF_8);
    }

    @Override
    public String urlDecode(String input) {
        return URLDecoder.decode(input, StandardCharsets.UTF_8);
    }

    @Override
    public String base64Encode(String input) {
        return new String(Base64.getEncoder().encode(input.getBytes(StandardCharsets.UTF_8)));
    }

    @Override
    public String base64Decode(String input) {
        return new String(Base64.getDecoder().decode(input.getBytes(StandardCharsets.UTF_8)));
    }

    @Override
    public String unicodeEncode(String input) {
        StringBuilder result = new StringBuilder();
        for (char c : input.toCharArray()) {
            String hexC = Integer.toHexString(c);
            if (hexC.length() <= 2) {
                hexC = "00" + hexC;
            }
            result.append("\\u").append(hexC);
        }
        return result.toString();
    }

    @Override
    public String unicodeDecode(String input) {
        int start = 0;
        int end = 0;
        StringBuilder result = new StringBuilder();
        while (start > -1) {
            end = input.indexOf("\\u", start + 2);
            String charStr;
            if (end == -1) {
                charStr = input.substring(start + 2);
            } else {
                charStr = input.substring(start + 2, end);
            }
            result.append((char) Integer.parseInt(charStr, 16));
            start = end;
        }
        return result.toString();
    }
}
