package com.example.mealer24.Utilities;

import java.nio.charset.StandardCharsets;

/**
 * Encodes a string to UTF8 bytes.
 * This class's purpose is to encode emails and use the encoded emails as the node keys in database
 *
 */
public class UTF8Encoder{
    private byte[] bytes;
    private String encodedString;

    /**
     * Class constructor.
     * Takes input string and converts in array of bytes then stored in string format
     * @param string  converted to an array of UTF8 bytes then stored in string format
     */
    public UTF8Encoder(String string){
        this.encodedString = "";
        //get the string as UTF8 bytes
        this.bytes = string.getBytes(StandardCharsets.UTF_8);
        //save the array of bytes as a string to be read in database
        for (byte b : bytes) {
            this.encodedString += String.format("%s", b);
        }
    }

    public String getEncodedString() {
        return encodedString;
    }

    public void setEncodedString(String encodedString) {
        this.encodedString = encodedString;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }


    public String decodeUTF8ToString(String string){
        return new String(this.bytes, StandardCharsets.UTF_8);
    }


}


