/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devwider.blockchain;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;

/**
 *
 * @author Kamil-Tomasz
 */
public class HashUtil {
    
    public static String applySha256(String input) {		
        try {

            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < hash.length; i++) {
                    String hex = Integer.toHexString(0xff & hash[i]);
                    if(hex.length() == 1) hexString.append('0');
                    hexString.append(hex);
            }
            return hexString.toString();

        } catch(NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
        }
    }

    public static String calculateHash(String previousHash, long timeStamp, int nonce, Data data) {
        String toHash = MessageFormat.format("{0}{1}{2}{3}", previousHash, Long.toString(timeStamp),
                Integer.toString(nonce), data);
        return HashUtil.applySha256(toHash);
    }

    public static String calculateHash(Block block) {
        return calculateHash(block.getPreviousHash(),block.getTimeStamp(),block.getNonce(), block.getData());
    }
}
