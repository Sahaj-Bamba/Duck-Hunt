/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DuckHunt.Request;

import DuckHunt.Constant.Request;

import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 *
 * @author Sahaj
 */
public class OpponentCameraFeed implements Serializable{
    
    private byte[] imageData;
    private String from;
    private String group;
    
    public OpponentCameraFeed(byte[] imageData, String from, String group) {
        this.imageData = imageData;
        this.from = from;
        this.group = group;
    }
    
    public byte[] getImageData() {
        return imageData;
    }
    
    public String getFrom() {
        return from;
    }
    
    public String getGroup() {
        return group;
    }
    
    @Override
    public String toString() {
        return String.valueOf(Request.OPPONENTCAMERAFEED);
    }
    
}
