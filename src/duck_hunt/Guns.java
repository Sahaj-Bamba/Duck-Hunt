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
public class Guns {
    
    public int Delay;                                                   //      frames
    public int Reload;                                                  //      frames
    public int Damage;                                                  //      integer
    public int Clip_size;                                               //      number of bullets
    
    public String pic_location;
    
    Guns(int a,int b,int c,int d,String e){
        
        this.Delay=a;
        this.Reload=b;
        this.Damage=c;
        this.Clip_size=d;
        
        this.pic_location=e;
        
    }
    
}
