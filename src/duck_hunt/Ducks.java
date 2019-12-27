/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duck_hunt;

import java.util.Date;

/**
 *
 * @author admin
 */
public class Ducks {
    

    public double angle;                                //      angle
    public double speed;                                //      pixel per frame
    public int randomability;                          //      frames
    public int hitpoints;                              //      integer
    public int leavetime;                              //      frames
    public String bio;                              
    public String pic_location;
    public String name;
    public boolean is_alive;
    private int entry_frame;
    private int size;
    private int score;
    public int width_dec;
    private boolean has_left = false;
    private int type ;
    private Date entry_date ;
    
    public Date get_entry_date(){
        return entry_date;
    }
    
    public void set_entry_date(Date de){
        this.entry_date = de;
    }
    
    public int get_type(){
        return this.type;
    }
    
    public void set_type(int t){
        this.type = t;
    }
    
    public void set_has_left(boolean x){
        this.has_left = x;
    }
    
    public boolean get_has_left(){
        return this.has_left;
    }
    
    public int get_score(){
        return this.score;
    }
    
    public void set_score(int x){
        this.score = x;
    }
    
    public int get_entry_frame(){
        return entry_frame;
    }
    
    public void set_entry_frame( int x){
        this.entry_frame = x;
    }
    
    public void set_size(int s){
        this.size = s;
    }
    
    public int get_size(){
        return this.size;
    }
    
    public Ducks(double angle, double speed, int randomability, int HP, int LT, String b, String p, String name, int sc, int type){
        this.angle = angle;
        this.speed = speed;
        this.randomability = randomability;
        this.hitpoints = HP;
        this.bio = b;
        this.leavetime = LT;
        this.pic_location = p;
        this.name = name;
        this.is_alive = true;
        this.size = 200;
        this.width_dec = 20;
        this.score = sc;
        this.has_left = false;
        this.type = type;
    }

    Ducks(Ducks a) {
        this.type = a.type;
        this.has_left = a.has_left;
        this.angle = a.angle;
        this.speed = a.speed;
        this.randomability = a.randomability;
        this.hitpoints = a.hitpoints;
        this.bio = a.bio;
        this.leavetime = a.leavetime;
        this.pic_location = a.pic_location;
        this.name = a.name;
        this.is_alive = true;
        this.size = a.size;
        this.score = a.score;
        this.width_dec = a.width_dec;
    }
    
    public void setter_obj (Ducks a) {
        this.type = a.type;
        this.has_left = a.has_left;
        this.angle = a.angle;
        this.speed = a.speed;
        this.randomability = a.randomability;
        this.hitpoints = a.hitpoints;
        this.bio = a.bio;
        this.leavetime = a.leavetime;
        this.pic_location = a.pic_location;
        this.name = a.name;
        this.is_alive = true;
        this.size = a.size;
        this.score = a.score;
        this.width_dec = a.width_dec;
    }


}
