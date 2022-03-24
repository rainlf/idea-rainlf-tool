package com.rainlf.component.encode;

/**
 * @author : rain
 * @date : 3/24/2022 7:20 PM
 */
public interface EncodeManagerI {
    String md5Encrypt(String input);

    String urlEncode(String input);

    String urlDecode(String input);

    String base64Encode(String input);

    String base64Decode(String input);

    String unicodeEncode(String input);

    String unicodeDecode(String input);
}
