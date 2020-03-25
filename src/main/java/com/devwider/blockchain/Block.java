/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devwider.blockchain;

import lombok.*;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.UUID;

import static com.devwider.blockchain.HashUtil.calculateHash;

/**
 *
 * @author Kamil-Tomasz
 */

@Getter
@ToString
public class Block {

    private final String hash;
    private final String previousHash;
    private final Data data;
    private final long timeStamp;
    private final int nonce;

    public final static Block START = new Block(HashUtil.applySha256("1"),new Data("start", BigDecimal.ZERO),
            System.currentTimeMillis(),0);

    @Builder
    private Block(String previousHash, Data data, long timeStamp, int nonce) {
        this.hash = calculateHash(previousHash,timeStamp,nonce,data);
        this.previousHash = previousHash;
        this.data = data;
        this.timeStamp = timeStamp;
        this.nonce = nonce;
    }

    public String getHash() {
        return hash;
    }

    public static Block mine(Block previous, int difficulty, int limit, Data data) {

        String target = new String(new char[difficulty]).replace('\0', '0'); //Create a string with difficulty * "0"


        long timestamp = System.currentTimeMillis();
        data.add(BigDecimal.ONE);

        String hash = "";
        int nonce = -1;
        do {
            hash = calculateHash(previous.getHash(),timestamp,++nonce,data);
        } while(!isOk(difficulty, target, hash) && nonce < limit);

        System.out.println("HASH: " + hash);

        if(!isOk(difficulty, target, hash)) {
            return Block.failed();
        }
        return Block.builder()
                .data(data)
                .nonce(nonce)
                .previousHash(previous.getHash())
                .timeStamp(timestamp)
                .build();
    }

    private static boolean isOk(int difficulty, String target, String hash) {
        return target.equals(hash.substring(0, difficulty));
    }

    public static Block block(Block previous, Data data) {
        return Block.builder()
                .timeStamp(System.currentTimeMillis())
                .previousHash(previous.getHash())
                .data(data)
                .nonce(0)
                .build();
    }

    public static Block start() {
        return START;
    }

    public static Block failed() {
        return START;
    }
}
