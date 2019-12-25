package duck_hunt.Restart.GameObjects.Ducks;

import java.awt.*;
import java.util.Date;

/**
 *
 * @author Sahaj Bamba
 *
 * Ducks
 *
 * An abstract class containing different characters and functions of Ducks
 *
 */

public abstract class Duck {

    private double angle;                               //      angle
    private double speed;                               //      pixel per frame
    private int randomability;                          //      frames
    private int hitpoints;                              //      integer
    private int leavetime;                              //      frames
    private String bio;                                 //      About me of the Duck
    private String picLocation;                         //      Pic Location of the Duck
    private String name;                                //      Name of the Duck
    private boolean isAlive;                            //      Tell if the Duck is Alive
    private int entryFrame;                             //      Entry Frame
    private int size;                                   //      Current Size
    private int score;                                  //      Score Awarded
    private int widthDec;                               //      Width Decrement
    private boolean hasLeft;                            //      If the bird has left the field
    private int type;                                   //      Type of Bird
    private Date entryDate;                             //      Entry Date
                                    //  Duck properties stars count for almanac
    private int[] property;
    private Color color;

    public Duck(double angle, double speed, int randomability, int HP, int LT, String b, String p, String name, int sc, int type,int[] property,Color color){
        this.angle = angle;
        this.speed = speed;
        this.randomability = randomability;
        this.hitpoints = HP;
        this.bio = b;
        this.leavetime = LT;
        this.picLocation = p;
        this.name = name;
        this.isAlive = true;
        this.size = 200;
        this.widthDec = 20;
        this.score = sc;
        this.hasLeft = false;
        this.type = type;
        for (int i=0;i<property.length;i++){
            this.property[i]=property[i];
        }
        this.color=color;
    }

    public int[] getProperty() {
        return property;
    }

    public void setProperty(int[] property) {
        this.property = property;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getPicLocation() {
        return picLocation;
    }

    public void setPicLocation(String picLocation) {
        this.picLocation = picLocation;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

/*
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
*/

/*    Ducks(duck_hunt.Ducks a) {
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

    public void setter_obj (duck_hunt.Ducks a) {
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
*/


}
