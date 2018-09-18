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
    
    private double angle;                                //      angle
    private double speed;                                //      pixel per frame
    private int randomability;                          //      frames
    public int hitpoints;                              //      integer
    private int leavetime;                              //      frames
    public String bio;                              
    public String pic_location;
    public String name;
    
    Ducks(double angle , double speed , int randomability , int HP , int LT , String b , String p , String name){
        this.angle = angle;
        this.speed = speed;
        this.randomability = randomability;
        this.hitpoints = HP;
        this.bio = b;
        this.leavetime = LT;
        this.pic_location = p;
        this.name = name;
    }
    
    
}
