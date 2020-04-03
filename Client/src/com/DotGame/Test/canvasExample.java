/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DotGame.Test;
import java.awt.*;  

/**
 *
 * @author Sahaj
 */
public class canvasExample {  

    
    
    public canvasExample() {
      
    Frame f= new Frame("Canvas Example");  
    f.add(new MyCanvas());  
    f.setLayout(null);  
    f.setSize(400, 400);  
    f.setVisible(true);  
  }
    
  public static void main(String args[])  
  {  
    new canvasExample();  
  }  
}  
    