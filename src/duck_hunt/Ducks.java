/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duck_hunt;

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
    public int size;
    
    Ducks(double angle , double speed , int randomability , int HP , int LT , String b , String p , String name){
        this.angle = angle;
        this.speed = speed;
        this.randomability = randomability;
        this.hitpoints = HP;
        this.bio = b;
        this.leavetime = LT;
        this.pic_location = p;
        this.name = name;
        this.is_alive = true;
        this.size = 100;
    }

    Ducks(Ducks a) {
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
    }
    
    
    
}
