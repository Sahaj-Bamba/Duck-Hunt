/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DotGame.Main;

import DuckHunt.Constant.Request;
import com.DotGame.Other.GameGlobalVariables;
import DuckHunt.Request.AddMember;
import com.DotGame.Request.Message;
import com.DotGame.Request.MoveToStart;
import com.DotGame.Request.RemoveMember;
import DuckHunt.Request.StartGame;
import com.DotGame.Utilities.GroupView;

/**
 *
 * @author Sahaj
 */
public class ListenGroup implements Runnable{
    
    private GroupView groupView;

    public ListenGroup(GroupView groupView) {
        this.groupView = groupView;
    }
    
    @Override
    public void run(){
        
        while (true){
            
            Object obj = GameGlobalVariables.getInstance().getClient().receiveMessage();
            
            if (obj.toString().equals(String.valueOf(Request.MESSAGE))){
                receivedMessage((Message)obj);
            }else if (obj.toString().equals(String.valueOf(Request.MEMBERADD))){
                addMember((AddMember)obj);
            }else if (obj.toString().equals(String.valueOf(Request.MEMBERREMOVE))){
                removeMember((RemoveMember)obj);    
            }else if (obj.toString().equals(String.valueOf(Request.STARTGAME))){
                startGame((StartGame) obj);    
            }
            
        }
        
    }

    private void receivedMessage(Message obj) {
        groupView.gotMessage(obj.getFrom() + " :- " + obj.getContent());
    }

    private void addMember(AddMember addMember) {
        if (addMember.getName().equals(GameGlobalVariables.getInstance().getClient().getName())){ 
            return;
        }
        groupView.gotPlayer(addMember.getName());
    }

    private void removeMember(RemoveMember removeMember) {
        groupView.lostPlayer(removeMember.getName());
    }

    private void startGame(StartGame startGame) {
        GameGlobalVariables.getInstance().getClient().sendMessage(new MoveToStart());
        System.out.println("Started Game in group " + GameGlobalVariables.getInstance().getClient().getGroupName() + " of " + GameGlobalVariables.getInstance().getClient().getName() + " Of size "+ startGame.getSize());
        groupView.startGame(startGame.getSize());
    }
    
}
