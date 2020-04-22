/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DuckHunt.Request;

import DuckHunt.Constant.Request;

import java.io.Serializable;

/**
 *
 * @author Sahaj
 */
public class OpponentAddress implements Serializable{
    
    private byte[] name;
    
    public OpponentAddress(byte[] name) {
        this.name = name;
    }
    
    public byte[] getAddress() {
        return name;
    }
    
    @Override
    public String toString() {
        return String.valueOf(Request.OPPONENTADDRESS);
    }
    
}
