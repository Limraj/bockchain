/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devwider.blockchain;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

/**
 *
 * @author Kamil-Tomasz
 */
public class Start {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        BlockChain chain = new BlockChain();

        chain.mine(new Data("Kamil", new BigDecimal("10000000")), 4);
        chain.mine(new Data("Tomek", new BigDecimal("1")), 4);
        chain.mine(new Data("Eryk", new BigDecimal("1")), 4);

        Data ewa = new Data("Ewa", new BigDecimal("1"));
        int mines = 10;
        while(mines > 0) {
            chain.mine(ewa, 5);
            mines--;
        }
        chain.mine(new Data("Marcin", new BigDecimal("1")), 4);
        mines = 10;
        while(mines > 0) {
            chain.mine(ewa, 3);
            mines--;
        }
        chain.mine(new Data("Edyta", new BigDecimal("1")), 4);
        chain.mine(new Data("Ada", new BigDecimal("123")), 4);
        System.out.println("blockchain: " + chain);
    }

}
