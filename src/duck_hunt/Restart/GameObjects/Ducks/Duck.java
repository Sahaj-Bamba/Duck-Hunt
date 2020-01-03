package duck_hunt.Restart.GameObjects.Ducks;

import duck_hunt.Restart.GameGlobalVariables;

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

    private boolean isBoss;                             //      if the bird is a boss
    private double x;                                   //      x coordinate of the pic
    private double y;                                   //      y coordinate of the pic
    private double angle;                               //      angle
    private double speed;                               //      pixel per frame
    private int randomability;                          //      frames
    private int hitpoints;                              //      integer
    private int leavetime;                              //      frames
    private String bio;                                 //      About me of the Duck
    private String picLocation;                         //      Pic Location of the Duck
    private String menuPicLocation;                     //      Pic Location of the Duck
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
        this.menuPicLocation = p;
        this.name = name;
        this.isAlive = true;
        this.size = 200;
        this.widthDec = 20;
        this.score = sc;
        this.hasLeft = false;
        this.type = type;
        this.entryDate = new Date();
        for (int i=0;i<property.length;i++){
            this.property[i]=property[i];
        }
        this.color = color;
        this.isBoss = false;
        this.updatePic();
    }

    public void setter(int size, int widthDec, double speed, int randomability, int HP, int LT, int sc, boolean isBoss){
        this.speed = speed;
        this.randomability = randomability;
        this.hitpoints = HP;
        this.leavetime = LT;
        this.size = size;
        this.widthDec = widthDec;
        this.score = sc;
        this.isBoss = isBoss;
        this.updatePic();
    }

    // Main Functions

    public boolean checkDeath(){
        if(this.isAlive) {
            if (this.hitpoints <= 0) {
                this.isAlive = false;
                return true;
            }
        }
        return false;
    }

    public void move() {
        if (this.isAlive){
            x+=(Math.cos(this.angle * Math.PI / 180.0) * this.speed);
            y+=((-1) * (Math.sin(this.angle * Math.PI / 180.0) * this.speed));
        }
    }

    public void checkLeave(){
        if(new Date().getTime() - this.entryDate.getTime() > this.leavetime)
            this.hasLeft= true;
    }

    public boolean checkCollision(){
        boolean flag = false;
        int y,z,r = 0;
        if(this.isAlive) {
            if (!this.hasLeft) {
                if (this.x > (1600 * GameGlobalVariables.getInstance().getScreenWidthFraction()) || this.x < (200 * GameGlobalVariables.getInstance().getScreenWidthFraction()) || this.y > (600 * GameGlobalVariables.getInstance().getScreenHeightFraction()) || this.y < (10 * GameGlobalVariables.getInstance().getScreenHeightFraction())) {
                    y = (int) (Math.random() * 12);
                    z = (int) (Math.random() * 3);
                    int x = this.type;
                    if (x > (1600 * GameGlobalVariables.getInstance().getScreenWidthFraction())) {
                        switch (z) {
                            case 0:
                                r = 155;
                                break;
                            case 1:
                                r = 180;
                                break;
                            case 2:
                                r = 205;
                                break;
                        }
                    }
                    if (x < (200 * GameGlobalVariables.getInstance().getScreenWidthFraction())) {
                        switch (z) {
                            case 0:
                                r = 25;
                                break;
                            case 1:
                                r = 0;
                                break;
                            case 2:
                                r = 335;
                                break;
                        }
                    }
                    if (y > (600 * GameGlobalVariables.getInstance().getScreenHeightFraction())) {
                        switch (z) {
                            case 0:
                                r = 50;
                                break;
                            case 1:
                                r = 90;
                                break;
                            case 2:
                                r = 115;
                                break;
                        }
                    }
                    if (y < (10 * GameGlobalVariables.getInstance().getScreenHeightFraction())) {
                        switch (z) {
                            case 0:
                                r = 270;
                                break;
                            case 1:
                                r = 310;
                                break;
                            case 2:
                                r = 230;
                                break;
                        }
                    }
                    this.angle = r;
                    this.updatePic();
                    return true;
                }
            }
        }
        return false;
    }

    public void shot(int damage){
        this.hitpoints-=damage;
        this.size-=this.widthDec;
    }

    public void updatePic(){
        if(isBoss)
            this.picLocation = "Images/Images/boss/"+(x+1)+"/"+(int)this.angle+".png" ;
        else{
            this.picLocation = "Images/Images/"+(x+1)+"/"+(int)this.angle+".png" ;
        }
    }

    abstract public void bossMode();

    //  Getters and Setters

    public String getMenuPicLocation() {
        return menuPicLocation;
    }

    public void setLocation(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public int getType() {
        return type;
    }

    public int getHitpoints() {
        return hitpoints;
    }

    public int getScore() {
        return score;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public boolean hasLeft() {
        return hasLeft;
    }

    public void setLeft(boolean hasLeft) {
        this.hasLeft = hasLeft;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
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
