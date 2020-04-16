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
    
    private BufferedImage bufferedImage;
    
    public OpponentCameraFeed(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }
    
    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }
    
    @Override
    public String toString() {
        return String.valueOf(Request.OPPONENTCAMERAFEED);
    }
    
}
