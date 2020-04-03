/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DotGame.Main;

import DuckHunt.Constant.LineType;
import com.DotGame.Other.GameGlobalVariables;
import com.DotGame.Request.GameState;
import com.DotGame.Request.Move;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author Sahaj
 */
public class GamePanel extends JPanel {

    private int WIDTH  = 440;
    private int HEIGHT = 440;
    
    private Color[] colors = new Color[9];
    private GameState gameState;
    private int size;

    private int gridSize = 50;
    private int dotSize = 20;
    private int offset = 10;
    private Point click1 = null;
    private Point click2 = null;
    
    private Ellipse2D[][] dots = new Ellipse2D[10][10];
    private Line2D[][] vLines = new Line2D[10][10];
    private Line2D[][] hLines = new Line2D[10][10];
    private Rectangle2D[][] rects = new Rectangle2D[10][10];
    
    
    private boolean isOnline;
    private OfflineGame offlineGame;
    private boolean canMakeMove;
    
    public GamePanel(int size){
        this.isOnline = true;
        this.size = size;
        setBorder(BorderFactory.createLineBorder(Color.black));
        setBackground(Color.white);
        setSize(WIDTH, HEIGHT);
        canMakeMove = false;
        gameState = new GameState(1,1);
        init();
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                clicked(e.getX(),e.getY());
            }
        });
   
    }    
    
    public GamePanel(int size,OfflineGame offlineGame){
        this.isOnline = false;
        this.offlineGame = offlineGame;
        this.size = size;
        setBorder(BorderFactory.createLineBorder(Color.black));
        setBackground(Color.white);
        setSize(WIDTH, HEIGHT);
        canMakeMove = true;
        gameState = new GameState(size,GameGlobalVariables.getInstance().getNumberOfPlayers());
        init();
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                clicked(e.getX(),e.getY());
            }
        });
   
    }

    private void init(){
        colors[0]=Color.red;
        colors[1]=Color.GREEN;
        colors[2]=Color.BLUE;
        colors[3]=Color.CYAN;
        colors[4]=Color.GRAY;
        colors[5]=Color.MAGENTA;
        colors[6]=Color.PINK;
        colors[7]=Color.YELLOW;
        
        for(int i=0;i<size;i++)
        {
            for(int j=0;j<size;j++)
            {
                rects[i][j] = new Rectangle2D.Double(offset + j*gridSize , offset + i*gridSize,gridSize,gridSize);
                dots[i][j] = new Ellipse2D.Double(offset + j*gridSize - dotSize/2, offset + i*gridSize - dotSize/2,dotSize,dotSize);
                vLines[i][j] = new Line2D.Double(offset + j*gridSize , offset + i*gridSize,offset + j*gridSize , offset + (i+1)*gridSize);
                hLines[i][j] = new Line2D.Double(offset + j*gridSize , offset + i*gridSize,offset + (j+1)*gridSize , offset + (i)*gridSize);
            }
        }
        repaint();
    }
    
    
    
    
    protected void paintComponent(Graphics g) {
        System.out.println("painting");
        super.paintComponent(g);       
        Graphics2D g2d = (Graphics2D)g;
        
        // rects 
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (gameState.getRColor(i,j) != -1){
                    g2d.setColor(colors[gameState.getRColor(i,j)]);
                    g2d.fill(rects[i][j]);
                }
            }
        }
        // Draw Dots
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                g2d.setColor(Color.blue);
                if (click1 != null && click1.x == i && click1.y == j){
                    g2d.setColor(Color.red);
                }
                g2d.fill(dots[i][j]);
            }
        }
        // V lines
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (gameState.getVColor(i,j) != -1){
                    g2d.setColor(colors[gameState.getVColor(i,j)]);
                    g2d.draw(vLines[i][j]);
                }
            }
        }
        // H lines
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (gameState.getHColor(i,j) != -1){
                    g2d.setColor(colors[gameState.getHColor(i,j)]);
                    g2d.draw(hLines[i][j]);
                }
            }
        }
        
    }
    
    public void update(GameState gameState){
        this.gameState = gameState;
        this.repaint();
    }
    
    public void update(Move move){
        this.gameState.makeMove(move);
        this.canMakeMove = false;
        this.repaint();
    }
        
    public void makeMove(){
        canMakeMove = true;
    }
    
    private void clicked(int x, int y){
    
        if (!canMakeMove) {
            return;
        }
        
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(dots[i][j].contains(x, y)){
                    System.out.println(i + " " + j);
                    if (click1 == null){
                        click1 = new Point(i, j);
                        repaint();
                        return;
                    }else{
                        click2 = new Point(i, j);
                        if (click1.equals(click2)){
                            click1 = null;
                            click2 = null;
                            repaint();
                            return;
                        }
                        move ();
                    }
                }
            }
        }
        
    }
    
    private void move(){
        int tmp;
        if ( Math.abs(click1.x - click2.x) + Math.abs(click1.y - click2.y) == 1 ) {
                // line possible
            if (click1.y == click2.y){
                // vertical line
                tmp = Math.min(click1.x, click2.x);
                if (gameState.getVColor(tmp,click1.y) == -1){
                    if (isOnline) {
                        GameGlobalVariables.getInstance().getClient().sendMessage(new Move(new Point(tmp,click1.y), LineType.Vertical));
                    }else{
                        gameState.makeMove(new Move(new Point(tmp,click1.y), LineType.Vertical));
                        offlineGame.updateGame(gameState);
                    }
                }
            }
            else{
                // horizontal line
                tmp = Math.min(click1.y, click2.y );
                if (gameState.getHColor(click1.x,tmp) == -1){
                    if (isOnline) {
                        GameGlobalVariables.getInstance().getClient().sendMessage(new Move(new Point(click1.x,tmp), LineType.Horizontal));
                    }else{
                        gameState.makeMove(new Move(new Point(click1.x,tmp), LineType.Horizontal));
                        offlineGame.updateGame(gameState);
                    }
                }
            }
            
        }
        if (!isOnline) {
            if (gameState.isOver()) {
                offlineGame.gameOver(gameState.getWinner());
            }
        }
        click1 = null;
        click2 = null;
        repaint();
    }

    public GameState getGameState() {
        return gameState;
    }
    
    public void lostPlayer(int i){
        gameState.remove(i);
    }
    
}
