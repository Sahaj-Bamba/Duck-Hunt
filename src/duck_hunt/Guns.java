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
public class Guns {
    
    public int Delay;                                                   //      frames
    public int Reload;                                                  //      frames
    public int Damage;                                                  //      integer
    public int Clip_size;                                               //      number of bullets    
    public String pic_location;
    private String sound;
    private int previous_shoot = 0;
    private Date prev_shoot_date = new Date();
    
    public Date get_prev_shoot_date(){
        return this.prev_shoot_date;
    }
    
    public void set_prev_shoot_date(Date de){
        this.prev_shoot_date = de;
    }
    
    public int get_previous_shoot(){
        return this.previous_shoot;
    }
    
    public void set_previous_shoot(int x){
        this.previous_shoot = x;
    }
    
    public Guns(int a, int b, int c, int d, String e, String sd){
        
        this.Delay=a;
        this.Reload=b;
        this.Damage=c;
        this.Clip_size=d;
        this.pic_location=e;
        this.sound=sd;
        
    }
    
    Guns(Guns g){
        this.Clip_size = g.Clip_size;
        this.Damage = g.Damage;
        this.Reload = g.Reload;
        this.Delay = g.Delay;
        this.pic_location = g.pic_location;
        this.sound=g.sound;
    }
    
    public String getsd(){
        return this.sound;
    }
    
    
    
}
