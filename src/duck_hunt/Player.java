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
    
    public Player(String na,int sc,int id,int gems) 
    {
        
        this.name = na;
        this.score = sc;
        this.gems = gems;
        this.id = id;
        //this.password = pass;
        
    }

    Player() {
    
    }
    
    public int get_score(){
        return this.score;
    }
    
    public void set_score(int x){
        this.score = x;
    }
    
}