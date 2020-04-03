/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DotGame.Main;

import DuckHunt.Constant.Request;
import com.DotGame.Other.GameGlobalVariables;
import DuckHunt.Request.GameOver;
import com.DotGame.Request.GameState;
import com.DotGame.Request.Message;
import com.DotGame.Request.Move;
import com.DotGame.Request.RemoveMember;

/**
 *
 * @author Sahaj
 */
public class ListenGame implements Runnable{
    
    private MainGame mainGame;

    public ListenGame(MainGame mainGame) {
        this.mainGame = mainGame;
    }
    
    @Override
    public void run(){
        
        while (true){
            
            Object obj = GameGlobalVariables.getInstance().getClient().receiveMessage();
            
            if (obj.toString().equals(String.valueOf(Request.MESSAGE))){
                receivedMessage((Message)obj);
            }else if (obj.toString().equals(String.valueOf(Request.MEMBERREMOVE))){
                removeMember((RemoveMember)obj);    
            }else if (obj.toString().equals(String.valueOf(Request.GAMESTATE))){
                System.out.println("turn is of " + ((GameState) obj).getTurn());
                System.out.println(((GameState) obj).num);
	        updateGame((GameState)obj);
            }else if (obj.toString().equals(String.valueOf(Request.GAMEOVER))){
                gameOver((GameOver)obj);
                break;
            }if (obj.toString().equals(String.valueOf(Request.MOVE))){
                updateGame((Move)obj);    
            }
            
        }
        
    }

    private void receivedMessage(Message obj) {
        mainGame.gotMessage(obj.getFrom() + " :- " + obj.getContent());
    }

    private void removeMember(RemoveMember removeMember) {
        mainGame.lostPlayer(removeMember.getName());
    }

    private void updateGame(GameState gameState) {
        mainGame.updateGame(gameState);    
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.println(gameState.getHColor(i, j));
            }
        }
    }
    
    private void updateGame(Move move) {
        mainGame.updateGame(move);    
    }

    private void gameOver(GameOver gameOver) {
        System.out.println("Game Over ");
        mainGame.gameOver(gameOver.getName());
    }
    

    
}
