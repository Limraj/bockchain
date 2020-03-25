/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devwider.blockchain;

import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.devwider.blockchain.HashUtil.calculateHash;

/**
 *
 * @author Kamil-Tomasz
 */
@ToString
public class BlockChain {
    
    private final List<Block> blockchain;

    public BlockChain() {
        blockchain = new ArrayList<>();
        blockchain.add(Block.start());
    }

    public boolean mine(Data data, int difficulty) {
        Block pre = blockchain.get(blockchain.size() - 1);
        Block current = Block.mine(pre, difficulty, 1000000, data);
        return isChainValid(pre, current) && blockchain.add(current);
    }

    public Boolean isChainValid() {
        for(int i=1; i < blockchain.size(); i++) {
            Block currentBlock = blockchain.get(i);
            Block previousBlock = blockchain.get(i-1);
            if(!isChainValid(previousBlock, currentBlock))
                return false;
        }
        return true;
    }
    
    private boolean isChainValid(Block previousBlock, Block currentBlock) {
        if(!currentBlock.getHash().equals(calculateHash(currentBlock)) ){
            System.out.println("Current Hashes not equal");
            return false;
        }
        if(!previousBlock.getHash().equals(currentBlock.getPreviousHash()) ) {
            System.out.println("Previous Hashes not equal");
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return blockchain.stream()
                .map(Block::toString)
                .collect(Collectors.joining("\n"));
    }
}
