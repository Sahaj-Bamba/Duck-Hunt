/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DotGame.Test;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Sahaj
 */

public class MyCanvas extends Canvas  
{  
        public MyCanvas() {  
        setBackground (Color.GRAY);  
        setSize(300, 200);  
     }  
  public void paint(Graphics g)  
  {  
    g.setColor(Color.red);  
    g.fillOval(75, 75, 150, 75);  
  }  
}    

