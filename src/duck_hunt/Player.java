/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package duck_hunt;

public class Player {
    //public Color colour;
    private int score;
    private String name;
    private int id;
    //private String password;
    private int gems;
    private String pointer_pic;
    private int red_kill = 0;
    private int blue_kill = 0;
    private int yellow_kill = 0;
    
    public String get_name(){
        return this.name;
    }
    
    public void set_name(String x){
        this.name = x;
    }
    
    public void kill_inc(int x){
        switch(x){
            case 0:
                red_kill++;
                break;
            case 1:
                yellow_kill++;
                break;
            case 2:
                blue_kill++;
                break;
        }
    }
    
    /*
    public void set_name(String x){
        this.name = x;
    }
    */
    
    public int get_kill(int x){
        switch(x){
            case 0:
                return (red_kill);
                
            case 1:
                return yellow_kill;
            case 2:
                return blue_kill;
                
            default: return -1;
        }    
    }
    
    public Player(String na,int sc,int id,int gems) 
    {
        this.name = "Gamer";
        this.name = na;
        this.score = sc;
        this.gems = gems;
        this.id = id;
        //this.password = pass;
    
              
    }
    
    public void setter (String na,int sc,int id,int gems) 
    {
        
        this.name = na;
        this.score = sc;
        this.gems = gems;
        this.id = id;
        //this.password = pass;
        
              
    }

    public Player() {
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