/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DotGame.Request;

import com.DotGame.Constant.LineType;
import com.DotGame.Constant.Request;
import java.awt.Point;
import java.io.Serializable;

/**
 *
 * @author Sahaj
 */
public class Move implements Serializable{
    
    private Point point;
    private LineType lineType;

    public Move(Point point, LineType lineType) {
        this.point = point;
        this.lineType = lineType;
    }

    public Point getPoint() {
        return point;
    }

    public LineType getLineType() {
        return lineType;
    }
    
    
    @Override
    public String toString() {
        return String.valueOf(Request.MOVE);
    }
}
