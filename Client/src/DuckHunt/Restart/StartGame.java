
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package duck_hunt.Restart;


/**
 *
 * @author Sahaj Bamba
 */

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;
import duck_hunt.Restart.GameObjects.Ducks.*;
import duck_hunt.Restart.GameObjects.Guns.*;
import duck_hunt.Restart.utilities.Error;
import duck_hunt.Restart.utilities.playwav;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import static duck_hunt.Restart.Duck_hunt.gamer;

public class StartGame extends GraphicsProgram 
{

    //      Main Game Control Variables
    
    private int[] anglesAllowed ={0,25,50,90,115,155,180,205,230,270,310,335};                                         //  Angles allowed for the ducks for rotation problems
    private int bossRoundNumber = 3;                                                                                          //  Boss round will be after every which level
    private int delay_after_all_left = 2000;                                                                            //  Delay after all ducks have left the screen
    private final int fps = 45;


    private final double game_height = 1000;
    private final double game_width = 1800;
    private final double screenHeightFraction = GameGlobalVariables.getInstance().getScreenHeightFraction();
    private final double screenWidthFraction = GameGlobalVariables.getInstance().getScreenWidthFraction();

    //         In Game Control Variables

    private int[] ducksKilled = {0,0,0,0,0,0,0,0,0,0};
    private long frame = 0;                                                                                             //  Current frame number
    private int activeGun = 0;                                                                                         //  Current gun number                      // TODO: 03/01/20 Try to change code to remove it
    private int numberOfBirds = GameGlobalVariables.getInstance().getDucksCount();
    private Duck[] ducks = new Duck[numberOfBirds];
    private GImage[] ducksPic = new GImage[numberOfBirds];
    private Gun[] guns = new Gun[4];
    private GImage[] gunsImage = new GImage[4];
    private GRect[] gunsRect = new GRect[4];
    private long roundNumber = 0;

    //          Variables used in this class which are drawn on canvas

    GRect level1 = new GRect(1800*screenWidthFraction,550*screenHeightFraction);                        //      gamezone
    GRect level2 = new GRect(1800*screenWidthFraction,200*screenHeightFraction);                        //      mid
    GRect level3 = new GRect(1800*screenWidthFraction,200*screenHeightFraction);                        //      ground base

    //              Main Gun

    Gun mainGun;
    GImage mainGunImage;
    GRect mainGunRect;
    GOval pointer;
    GImage _pointer;

    //          Images for different rects

    GImage _level1 = new GImage("Images/Images/level1.gif");                                              //  Base gif

    //          Scoring part

    GLabel player_name = new GLabel("");
    GImage player_red_duck = new GImage(new RedDuck().getMenuPicLocation());
    GImage player_blue_duck = new GImage(new BlueDuck().getMenuPicLocation());
    GImage player_yellow_duck = new GImage(new YellowDuck().getPicLocation());
    GLabel player_red_kill = new GLabel("");
    GLabel player_blue_kill = new GLabel("");
    GLabel player_yellow_kill = new GLabel("");

    // declare grect for human location for fun human kill instant game over.

    GRect rc = new GRect(40*screenWidthFraction,105*screenHeightFraction);
    GRect rc1 = new GRect(80*screenWidthFraction,70*screenHeightFraction);
    GRect rc2 = new GRect (500*screenWidthFraction ,150*screenHeightFraction);


    private boolean isRoundOver(){
        int f=0;
        for(int i=0;i<numberOfBirds;i++){
            if(!ducks[i].isAlive() || ducks[i].hasLeft())
                f++;
        }
        if(f >= numberOfBirds){
           System.out.println("round over");
            return true;
        }
        return false;
    }

            //  Flash function

    private void FL(String a)
    {
        Color[] colors = new Color[9];
        colors[0]=Color.red;
        colors[1]=Color.GREEN;
        colors[2]=Color.BLUE;
        colors[3]=Color.CYAN;
        colors[4]=Color.GRAY;
        colors[5]=Color.MAGENTA;
        colors[6]=Color.PINK;
        colors[7]=Color.YELLOW;
        char[] initial =new char [a.length()];
        char[] org =new char[a.length()];
        initial[0]=a.charAt(0);
        org[0]=' ';
        for(int i=1;a.charAt(i)!='\n';i++)
        {
            org[i]=a.charAt(i);
            initial[i]=' ';
            if(a.charAt(i)==' ')
            {
                i++;
                if(a.charAt(i)==' ')
                    break;
                org[i]=' ';
                initial[i]=a.charAt(i);
            }                
        }
        String st_org=new String(org);
        String st_initial =new String(initial);
        GLabel label_initial =new GLabel(st_initial);
        GLabel label_org =new GLabel(st_org);
        GLabel size=new GLabel(a);
        size.setFont("SansSerif-50");
        label_initial.setFont("SansSerif-50");
        label_org.setFont("SansSerif-50");
        double x;
        x = (getWidth()-size.getWidth())/2;
        double y;
        y=(getHeight()+size.getAscent())/2;
        add(label_initial,x,y);
        label_initial.move(0,100);
        for(int i=0;i<10;i++)
        {
            label_initial.move(0,-10);
            label_initial.setColor(Color.CYAN);
            pause(200);
        }
        add(label_org,x,y);
        label_org.move(0,100);
        for(int i=0;i<10;i++)
        {
            label_org.move(0,-10);
            label_org.setColor(colors[i%7]);
            pause(200);
        }
        removeAll();
        add(size,x,y);
        pause(500);
        removeAll();
    }

    private void updateScoring(){
        player_red_kill.setLabel(""+ducksKilled[0]);
        player_yellow_kill.setLabel(""+ducksKilled[1]);
        player_blue_kill.setLabel(""+ducksKilled[2]);
    }

    //  Will be called only once

    private void initialisation(){
        gunslInit();
        ducksInit();
        scoringPanelInit();
        manInit();
        zoneInit();
    }

    void gunslInit(){
        guns[0] = new Rifle();
        guns[1] = new Shotgun();
        guns[2] = new SMG();
        guns[3] = new Sniper();

        for (int i=0;i<4;i++){
            gunsImage[i] = new GImage(guns[i].getMenuPicLocation());
        }

        gunsRect[0] = new GRect(200*screenWidthFraction,101*screenHeightFraction);
        gunsRect[1] = new GRect(200*screenWidthFraction,200*screenHeightFraction);
        gunsRect[2] = new GRect(200*screenWidthFraction,100*screenHeightFraction);
        gunsRect[3] = new GRect(200*screenWidthFraction,200*screenHeightFraction);
        gunsRect[0].setLocation(0*screenWidthFraction,750*screenHeightFraction);
        gunsRect[1].setLocation(200*screenWidthFraction,750*screenHeightFraction);
        gunsRect[2].setLocation(400*screenWidthFraction,750*screenHeightFraction);
        gunsRect[3].setLocation(600*screenWidthFraction,750*screenHeightFraction);
        gunsRect[0].setColor(Color.LIGHT_GRAY);
        gunsRect[1].setColor(Color.ORANGE);
        gunsRect[2].setColor(Color.YELLOW);
        gunsRect[3].setColor(Color.RED);
        for (int i=0;i<4;i++){
            gunsRect[i].setFilled(true);
        }

        gunsImage[0].setSize(200*screenWidthFraction,101*screenHeightFraction);
        gunsImage[1].setSize(200*screenWidthFraction,200*screenHeightFraction);
        gunsImage[2].setSize(200*screenWidthFraction,100*screenHeightFraction);
        gunsImage[3].setSize(200*screenWidthFraction,200*screenHeightFraction);
        gunsImage[0].setLocation(0*screenWidthFraction,750*screenHeightFraction);
        gunsImage[1].setLocation(200*screenWidthFraction,750*screenHeightFraction);
        gunsImage[2].setLocation(400*screenWidthFraction,750*screenHeightFraction);
        gunsImage[3].setLocation(600*screenWidthFraction,750*screenHeightFraction);

                    //  main gun
        mainGun = new Rifle();
        mainGunImage = new GImage(new Rifle().getPicLocation());
        mainGunImage.setLocation(600*screenWidthFraction,550*screenHeightFraction);
        mainGunRect = new GRect(200*screenWidthFraction,400*screenHeightFraction);
        mainGunRect.setLocation(600*screenWidthFraction,550*screenHeightFraction);
        mainGunRect.setFilled(true);
        mainGunRect.setColor(Color.BLUE);
        pointer = new GOval(50*screenWidthFraction,50*screenHeightFraction);
        pointer.setLocation(0*screenWidthFraction,0*screenHeightFraction);
        pointer.setFilled(true);
        pointer.setColor(Color.RED);
        _pointer = new GImage("Images/Images/Pointers/" + (activeGun+1) + ".png");
        _pointer.setSize(50*screenWidthFraction,50*screenHeightFraction);
        _pointer.setLocation(0,0);

    }

    private void scoringPanelInit(){

        // to set Loaction
        player_name.setLocation((1300.0*screenWidthFraction) , (screenHeightFraction*850.0));
        player_red_duck.setLocation((1570.0*screenWidthFraction),(screenHeightFraction)*810.0);
        player_blue_duck.setLocation((1500.0*screenWidthFraction),(screenHeightFraction)*810.0);
        player_yellow_duck.setLocation((1640.0*screenWidthFraction),(screenHeightFraction)*810.0);
        player_red_kill.setLocation((1565.0*screenWidthFraction),(screenHeightFraction)*840);
        player_blue_kill.setLocation((1495.0*screenWidthFraction),(screenHeightFraction)*840);
        player_yellow_kill.setLocation((1635.0*screenWidthFraction),(screenHeightFraction)*840);

        // To set Text Field
        player_name.setLabel(gamer.get_name());
        player_red_kill.setLabel(""+gamer.get_kill(0));
        player_yellow_kill.setLabel(""+gamer.get_kill(1));
        player_blue_kill.setLabel(""+gamer.get_kill(2));

        // To set the image size
        player_red_duck.setSize(50*screenWidthFraction, 50*screenHeightFraction);
        player_blue_duck.setSize(50*screenWidthFraction, 50*screenHeightFraction);
        player_yellow_duck.setSize(50*screenWidthFraction,50*screenHeightFraction);

        //  To set Font
        player_name.setFont((new Font("Serif", Font.BOLD,(int)(40*screenWidthFraction))));
        player_red_kill.setFont((new Font("Serif", Font.BOLD,(int)(30*screenWidthFraction))));
        player_red_kill.setColor(Color.RED);
        player_blue_kill.setFont((new Font("Serif", Font.BOLD,(int)(30*screenWidthFraction))));
        player_blue_kill.setColor(Color.BLUE);
        player_yellow_kill.setFont((new Font("Serif", Font.BOLD,(int)(30*screenWidthFraction))));
        player_yellow_kill.setColor(Color.yellow);

        player_name.setColor(Color.red);

        add(player_name);
        add(player_red_duck);
        add(player_blue_duck);
        add(player_yellow_duck);
        add(player_red_kill);
        add(player_blue_kill);
        add(player_yellow_kill);

        player_name.setLabel(gamer.get_name());
    }

    private void ducksInit(){
        numberOfBirds = GameGlobalVariables.getInstance().getDucksCount();
        for(int i=0;i<numberOfBirds;i++){
            int x =(int) (Math.random() * 4);
            int y =(int) (Math.random() * anglesAllowed.length);
            switch (x) {
                case 0:
                    ducks[i] = new RedDuck();
                    break;
                case 1:
                    ducks[i] = new YellowDuck();
                    break;
                case 2:
                    ducks[i] = new BlueDuck();
                    break;
                case 3:
                    ducks[i] = new BlackDuck();
                    break;
                default:
                    break;
            }
            ducks[i].setAngle(anglesAllowed[y]);
            ducksPic[i] = new GImage(ducks[i].getPicLocation());
            ducksPic[i].setSize(ducks[i].getSize(),ducks[i].getSize());
            x =(int) ((Math.random() * 1300) + 200);
            y = (int) ((Math.random() * 600) + 50);
            ducksPic[i].setLocation( x, y);
            ducks[i].setLocation(x,y);
        }
    }

    private void bossDucksInit(){
        numberOfBirds = 4;
        for(int i=0;i<numberOfBirds;i++){
            int y =(int) (Math.random() * anglesAllowed.length);
            switch (i) {
                case 0:
                    ducks[i] = new RedDuck();
                    break;
                case 1:
                    ducks[i] = new YellowDuck();
                    break;
                case 2:
                    ducks[i] = new BlueDuck();
                    break;
                case 3:
                    ducks[i] = new BlackDuck();
                    break;
                default:
                    break;
            }
            ducks[i].bossMode();
            ducks[i].setAngle(anglesAllowed[y]);
            ducksPic[i] = new GImage(ducks[i].getPicLocation());
            ducksPic[i].setSize(ducks[i].getSize(),ducks[i].getSize());
            int x =(int) ((Math.random() * 1300) + 200);
            y = (int) ((Math.random() * 600) + 50);
            ducksPic[i].setLocation( x, y);
            ducks[i].setLocation(x,y);
        }
    }

   public void manInit(){
       // To get location of a man.
       rc.setColor(Color.red);
       rc.setLocation(930*screenWidthFraction,675*screenHeightFraction);
       //To get location of three man
       rc1.setColor(Color.red);
       rc1.setLocation(600*screenWidthFraction,720*screenHeightFraction);
       //to Set Score Background
       rc2.setColor(Color.GRAY);
       rc2.setFilled(true);
       rc2.setLocation(1275*screenWidthFraction,800*screenHeightFraction);
   }

    private void zoneInit(){
        _level1.setSize(1800*screenWidthFraction,950*screenHeightFraction);
        _level1.setLocation(0*screenWidthFraction,0*screenHeightFraction);

        level1.setLocation(0*screenWidthFraction,0*screenHeightFraction);
        level2.setLocation(0*screenWidthFraction,750*screenHeightFraction);
        level3.setLocation(0*screenWidthFraction,750*screenHeightFraction);

        level1.setFilled(true);
        level2.setFilled(true);
        level3.setFilled(true);

        level1.setColor(Color.BLUE);
        level2.setColor(Color.yellow);
        level3.setColor(Color.GREEN);
    }
    
    @Override
    public void init(){

        addMouseListeners();
	    addKeyListeners();

	    initialisation();

        setSize((int)(game_width*screenWidthFraction),(int)(game_height*screenHeightFraction));             //  Setting  Window Size

    }


    public void levelStart(){
        frame = 0;
        addDucks();
        while (!isRoundOver()) {
            moveAll();
            checkCollision();
            checkDeath();
            checkHasLeft();
            updateScoring();
            pause(1000 / fps);
            frame++;
        }
    }

    public void addDucks(){
        for(int i=0; i<numberOfBirds; i++){
            add(ducksPic[i]);
        }
    }

    @Override
    public void run() {

        addBasic();
        roundNumber = 0;

        //  Main Game Loop
        while (true) {
            roundNumber++;
            new playwav("Images/Songs/buz.wav").start();
            pause(2000);
            new playwav("Images/Songs/buz.wav").start();
            pause(2000);
            new playwav("Images/Songs/buz.wav").start();
            pause(2000);

            if (roundNumber % bossRoundNumber == 0) {
                bossDucksInit();
            } else {
                ducksInit();
            }
            levelStart();
        }
    }


    //  add all the basic things which need not be changed during the game . Its initial level setup, will be called only once during the start of the level.
    public void addBasic(){
        //  GRects to mark different zones of game like gun pallet, play zone and score zone.
        add(level2);
        add(level3);
        add(level1);
        /*//  GImage the gif of the main game background
        add(_level1);
        //  Guns Pallet
        for (int i=0;i<4;i++){
            add(gunsImage[i]);
            add(gunsRect[i]);
        }
        //  Main gun
        add(mainGunImage);
        add(mainGunRect);
        add(pointer);
        add(_pointer);
        add(pointer);
        //  Scoring Pallet

        //  Human Rect
        add(rc2);*/

    }

    private void checkDeath(){
        for(int i=0; i<numberOfBirds ;i++){
            if(ducks[i].checkDeath()) {
                ducksKilled[ducks[i].getType()] += ducks[i].getScore();
                remove(ducksPic[i]);
                updateScoring();
            }
        }
    }
    
    public void moveAll(){
        for(int i=0; i<numberOfBirds; i++){
            ducks[i].move();
        }
    }
    
    public void checkHasLeft(){
        for(int i=0;i<numberOfBirds;i++){
            ducks[i].checkLeave();
        }
    }

    public void checkCollision(){
        for(int i=0; i<numberOfBirds; i++) {
            if (ducks[i].checkCollision()){
                ducksPic[i].setImage(ducks[i].getPicLocation());
                ducksPic[i].setSize(ducks[i].getSize(), ducks[i].getSize());
            }
        }
        moveAll();
    }


    @Override
    public void keyPressed(KeyEvent e){
        System.out.println(player_blue_duck.getLocation().getY());
        System.out.println(rc2.getLocation().getY());
        switch(e.getKeyCode()){
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                                activeGun++;
                                break;
            case KeyEvent.VK_LEFT:    
            case KeyEvent.VK_A:
                                activeGun--;
                                break;
        }
        if(activeGun<0)
            activeGun=3;
        if(activeGun>3)
            activeGun=0;
        
        //remove(_pointer);
        //remove(mainGunImage);
        mainGun = guns[activeGun];
        mainGunImage.setImage(mainGun.getPicLocation());
        _pointer.setImage("Images/Images/Pointers/" + (activeGun+1) + ".png");
        _pointer.setSize(50, 50);
        mainGunImage.setSize(300*GameGlobalVariables.getInstance().getScreenWidthFraction(),400*GameGlobalVariables.getInstance().getScreenHeightFraction());
        //add(_gun);
        //add(_pointer);
        System.out.println(activeGun);
    }
    
    @Override
    public void mouseMoved(MouseEvent e){
        if(e.getY()<750*GameGlobalVariables.getInstance().getScreenHeightFraction()){
    /*
            remove(Gun);
            Gun.setLocation(e.getX()-100*screenWidthFraction,550*screenHeightFraction);
            add(Gun);

            remove(Pointer);
            Pointer.setLocation(e.getX()-25*screenWidthFraction,e.getY()-25*screenWidthFraction);
            add(Pointer);
    */
            //remove(_gun);
            //remove(_pointer);
            _pointer.setLocation(e.getX()-25*screenWidthFraction,e.getY()-25*screenWidthFraction);
            mainGunImage.setLocation(e.getX()-50*screenWidthFraction,600*screenHeightFraction);
            //mainGunImage.setSize(300*screenWidthFraction,400*screenHeightFraction);
            //_pointer.setSize(50*screenWidthFraction,50*screenHeightFraction);
            //add(_pointer);
            //add(_gun);
        }
    }
    
    @Override
    public void mouseDragged(MouseEvent e){
        if(e.getY()<750*screenHeightFraction){
            //remove(_gun);
            //remove(_pointer);
            _pointer.setLocation(e.getX()-25*screenWidthFraction,e.getY()-25*screenWidthFraction);
            mainGunImage.setLocation(e.getX()-50*screenWidthFraction,600*screenHeightFraction);
            //mainGunImage.setSize(300*screenWidthFraction,400*screenHeightFraction);
            //_pointer.setSize(50*screenWidthFraction,50*screenHeightFraction);
            //add(_pointer);
            //add(_gun);
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e){
        
        //      Human Kill Game Over Part
        if(rc.contains(e.getX(),e.getY()) || rc1.contains(e.getX(),e.getY()))
        {
            new Error().run("How dare you kill a fellow human you murderer ?");
            System.exit(0);
        }

        for(int i=0; i<numberOfBirds; i++){
            if(ducks[i].isAlive()) {
                if (ducksPic[i].contains(e.getX(), e.getY())) {
                    ducks[i].shot(mainGun.shot());
                }
            }
        }

    }
    
    @Override
    public void mousePressed(MouseEvent e){

    }
    
    @Override
    public void mouseReleased(MouseEvent e){

    }

}
