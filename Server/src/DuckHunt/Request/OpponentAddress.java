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
    private int port;
    
    public OpponentAddress(byte[] name, int port) {
        this.name = name;
//        this.port = port;
    }
    
    public byte[] getAddress() {
        return name;
    }
    
    public int getPort() {
        return port;
    }
    
    @Override
    public String toString() {
        return String.valueOf(Request.OPPONENTADDRESS);
    }
    
}
