/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duck_hunt;
import java.awt.*;

public class Player {
    //public Color colour;
    private int score;
    private String name;
    private int id;
    //private String password;
    private int gems;
    private String pointer_pic;
    
    public Player(String na,int sc,int id,int gems) 
    {
        
        this.name = na;
        this.score = sc;
        this.gems = gems;
        this.id = id;
        //this.password = pass;
    
              
    }

    Player() {
        this.name = "Gamer";
        this.score = 0;
        this.gems = 0;
        this.pointer_pic = "1";
    }
    
    public void set_pointer_pic(String x){
        this.pointer_pic = x;
    }
    
    public String get_pointer_pic(){
        return this.pointer_pic;
    }
    
    public int get_score(){
        return this.score;
    }
    
    public void set_score(int x){
        this.score = x;
    }
    
}