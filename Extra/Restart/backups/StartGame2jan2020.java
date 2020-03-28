
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package duck_hunt.Restart.backups;


/**
 *
 * @author Sahaj Bamba
 */

/*

*/
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import acm.graphics.*;
import acm.program.GraphicsProgram;
import duck_hunt.Restart.Duck_hunt;
import duck_hunt.Restart.GameGlobalVariables;
import duck_hunt.Restart.GameObjects.Ducks.*;
import duck_hunt.Restart.GameObjects.Guns.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Date;

import static duck_hunt.Restart.Duck_hunt.gamer;

public class StartGame2jan2020 extends GraphicsProgram
{

        //          Flash Part

    int[] angles_allowed ={0,25,50,90,115,155,180,205,230,270,310,335};
    int round = 0;
    int bos_round = 3;
    //int ducks_left;
    int delay_after_all_left = 2000;
    boolean is_bypass = false;
    private boolean is_over = false;
    int original_num_birds ;
    int boss_active =0;

    private double screenHeightFraction = GameGlobalVariables.getInstance().getScreenHeightFraction();
    private double screenWidthFraction = GameGlobalVariables.getInstance().getScreenWidthFraction();

    private void round_over(){

        int f=0;
        for(int i=0;i<number_of_birds;i++){
            if(!ducks[i].isAlive() || ducks[i].hasLeft())
                f++;
        }
        if(f >= number_of_birds){
           System.out.println("round over");
            bypasser();
            //pause(delay_after_all_left);
            is_over = true;
        }

    }

    public void bypasser(){
        Date ds = new Date();
        System.out.println("int bypasser starting");
        while(true){
        long de = new Date().getTime() - ds.getTime();
        if(de>5000)
            break;
        move_all();
        //check_collision();
        check_death();
        //check_has_left();
        update_scoring();
        //round_over();

        //frame++;

        pause(1000/fps);
        //add(rc);
        //add(rc1);
        }
        System.out.println("bypasser end");
    }


    public void FL(String a)
    {

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
          //  label_org.scale(1.1);
            pause(200);
        }

        removeAll();
        add(size,x,y);
        pause(500);

        removeAll();

    }

            //              Flash Ends




    private final double game_height = 1000;
    private final double game_width = 1800;


    Color[] colors;                                     // Array of colors for flash


            //          Scoring part

    GLabel player_name = new GLabel("");
    GImage player_red_duck = new GImage(new RedDuck().getPicLocation());
    GImage player_blue_duck = new GImage(new BlueDuck().getPicLocation());
    GImage player_yellow_duck = new GImage(new YellowDuck().getPicLocation());
    GLabel player_red_kill = new GLabel("");
    GLabel player_blue_kill = new GLabel("");
    GLabel player_yellow_kill = new GLabel("");

    private void set_loc_add(){

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

    private void update_scoring(){

        player_red_kill.setLabel(""+gamer.get_kill(0));
        player_yellow_kill.setLabel(""+gamer.get_kill(1));
        player_blue_kill.setLabel(""+gamer.get_kill(2));

    }




    //      Public variables must be transported to Duck-Hunt class

    //          Game control Variables

    long frame = 0;
    int active_gun = 0;



    private final int fps = 45;



    private int number_of_birds = GameGlobalVariables.getInstance().getDucksCount();
    private Duck[] ducks = new Duck[number_of_birds];
    private GImage[] ducks_pic = new GImage[number_of_birds];
    private int round_num = 0;



    //          Variables used in this class

    GRect level1 = new GRect(1800*screenWidthFraction,550*screenHeightFraction);                        //      gamezone
    GRect level2 = new GRect(1800*screenWidthFraction,200*screenHeightFraction);                        //      mid
    GRect level3 = new GRect(1800*screenWidthFraction,200*screenHeightFraction);                        //      graund base
    GRect Gun = new GRect(200*screenWidthFraction,400*screenHeightFraction);
    GRect Gun1 = new GRect(200*screenWidthFraction,101*screenHeightFraction);
    GRect Gun2 = new GRect(200*screenWidthFraction,200*screenHeightFraction);
    GRect Gun3 = new GRect(200*screenWidthFraction,100*screenHeightFraction);
    GRect Gun4 = new GRect(200*screenWidthFraction,200*screenHeightFraction);
    GPoint last_pos = new GPoint();
    GOval Pointer = new GOval(50*screenWidthFraction,50*screenHeightFraction);

    //      Sounds
    /*
    playwav ak_sd = new playwav(ak.getsd());
    playwav shot_sd = new playwav(shotgun.getsd());
    playwav laser_sd = new playwav(laser.getsd());
    playwav sniper_sd = new playwav(sniper.getsd());
    */
    //          Images for different rects

    GImage _level1 = new GImage("Images\\Images\\level1.gif");

    GImage _gun1 = new GImage(new Rifle().getPicLocation()+"_1_1.png");
    GImage _gun2 = new GImage(new Shotgun().getPicLocation()+"_1_1.png");
    GImage _gun3 = new GImage(new SMG().getPicLocation()+"_1_1.png");
    GImage _gun4 = new GImage(new Sniper().getPicLocation()+"_1_1.png");
    GImage _gun = new GImage(new Rifle().getPicLocation()+"_2_1.png");

    Gun __gun1 = new Rifle();
    Gun __gun2 = new Shotgun();
    Gun __gun3 = new SMG();
    Gun __gun4 = new Sniper();
    Gun[] __gun = new Gun[4];

    public void initialisations(){
        /*
        for(int i=0;i<4;i++){
            switch(i){
                case 0:
                            __gun[i]=new Guns(ak);
                    break;
                case 1:
                            __gun[i]=new Guns(shotgun);
                    break;
                case 2:
                            __gun[i]=new Guns(laser);
                    break;
                case 3:
                            __gun[i]=new Guns(sniper);
                    break;
                default:
                    break;
            }
        }
        */
    }

    GImage _pointer = new GImage("Images\\Images\\Pointers\\" + (active_gun+1) + ".png");



    @Override
    public void init(){

        addMouseListeners();
	    addKeyListeners();

        colors = new Color[9];
        colors[0]=Color.red;
        colors[1]=Color.GREEN;
        colors[2]=Color.BLUE;
        colors[3]=Color.CYAN;
        colors[4]=Color.GRAY;
        colors[5]=Color.MAGENTA;
        colors[6]=Color.PINK;
        colors[7]=Color.YELLOW;


        setSize((int)(game_width*screenWidthFraction),(int)(game_height*screenHeightFraction));
        //setLocation(1222220,112222220);


        for(int i=0;i<number_of_birds;i++){
            int x =(int) (Math.random() * 4);
            int y =(int) (Math.random() * angles_allowed.length);
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
            ducks_pic[i] = new GImage(ducks[i].getPicLocation());
            ducks[i].setAngle(angles_allowed[y]);
            ducks_pic[i] = new GImage("Images\\Images\\"+(x+1)+"\\"+(int)ducks[i].getAngle()+".png");
        }


        //          Set initial angle of ducks


        //      Adjusting size

        _level1.setSize(1800*screenWidthFraction,950*screenHeightFraction);                        //      gamezone

        _gun1.setSize(200*screenWidthFraction,100*screenHeightFraction);
        _gun2.setSize(200*screenWidthFraction,200*screenHeightFraction);
        _gun3.setSize(200*screenWidthFraction,100*screenHeightFraction);
        _gun4.setSize(200*screenWidthFraction,200*screenHeightFraction);

        _gun.setSize(300*screenWidthFraction,400*screenHeightFraction);

        _pointer.setSize(50*screenWidthFraction,50*screenHeightFraction);



        for(int i=0; i<number_of_birds; i++){
            ducks_pic[i].setSize(ducks[i].getSize(),ducks[i].getSize());
        }

        //          Start all threads for initial and suspend

        //          Seting initial location of birds

        for(int i=0;i<number_of_birds;i++){

            int x =(int) ((Math.random() * 1300) + 200);
            int y = (int) ((Math.random() * 600) + 50);

            ducks_pic[i].setLocation( x, y);

        }

        //      Adhusting different zones or levels

        level1.setLocation(0*screenWidthFraction,0*screenHeightFraction);
        level2.setLocation(0*screenWidthFraction,750*screenHeightFraction);
        level3.setLocation(0*screenWidthFraction,750*screenHeightFraction);
        Gun.setLocation(600*screenWidthFraction,550*screenHeightFraction);
        Gun1.setLocation(0*screenWidthFraction,750*screenHeightFraction);
        Gun2.setLocation(200*screenWidthFraction,750*screenHeightFraction);
        Gun3.setLocation(400*screenWidthFraction,750*screenHeightFraction);
        Gun4.setLocation(600*screenWidthFraction,750*screenHeightFraction);

        Pointer.setLocation(0*screenWidthFraction,0*screenHeightFraction);

        _level1.setLocation(0*screenWidthFraction,0*screenHeightFraction);


        _gun.setLocation(600*screenWidthFraction,550*screenHeightFraction);
        _gun1.setLocation(0*screenWidthFraction,750*screenHeightFraction);
        _gun2.setLocation(200*screenWidthFraction,750*screenHeightFraction);
        _gun3.setLocation(400*screenWidthFraction,750*screenHeightFraction);
        _gun4.setLocation(600*screenWidthFraction,750*screenHeightFraction);

        _pointer.setLocation(0,0);



        level1.setFilled(true);
        level2.setFilled(true);
        level3.setFilled(true);
        Gun.setFilled(true);
        Gun1.setFilled(true);
        Gun2.setFilled(true);
        Gun3.setFilled(true);
        Gun4.setFilled(true);

        Pointer.setFilled(true);

        level1.setColor(Color.BLUE);
        level2.setColor(Color.yellow);
        level3.setColor(Color.GREEN);
        Gun.setColor(Color.BLUE);
        Gun1.setColor(Color.LIGHT_GRAY);
        Gun2.setColor(Color.ORANGE);
        Gun3.setColor(Color.YELLOW);
        Gun4.setColor(Color.RED);

        Pointer.setColor(Color.RED);

        frame = 0;


    }
    // declare grect for location
         GRect rc = new GRect(40*screenWidthFraction,105*screenHeightFraction);
         GRect rc1 = new GRect(80*screenWidthFraction,70*screenHeightFraction);
         GRect rc2 = new GRect (500*screenWidthFraction ,150*screenHeightFraction);
    @Override
    public void run(){
//        init();

    original_num_birds = number_of_birds;
         initialisations();

        FL("Game Begins  ");

        /*
        System.out.println("hi");
            ak_sd.start();
            ak_sd.suspend();
            laser_sd.start();
            laser_sd.suspend();
            shot_sd.start();
            shot_sd.suspend();
            sniper_sd.start();
            sniper_sd.suspend();
            System.out.println("bye");
        */

        System.out.println("hello");
        /*
        add(level1);
        new playwav("C:\\Users\\admin\\Desktop\\Avishkar\\Cyber Quest\\Softblitz\\Duck_hunt\\Images\\Songs\\buz.wav").start();
        pause(1000);
        new playwav("C:\\Users\\admin\\Desktop\\Avishkar\\Cyber Quest\\Softblitz\\Duck_hunt\\Images\\Songs\\buz.wav").start();
        pause(1000);
        new playwav("C:\\Users\\admin\\Desktop\\Avishkar\\Cyber Quest\\Softblitz\\Duck_hunt\\Images\\Songs\\buz.wav").start();
        pause(1000);
        addall();
        */
        frame = 0;
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
        //add(rc2);

        set_loc_add();




        while(true) {


            add(_level1);
            new playwav("Images\\Songs\\buz.wav").start();
            pause(2000);
            new playwav("Images\\Songs\\buz.wav").start();
            pause(2000);
            new playwav("Images\\Songs\\buz.wav").start();
            pause(2000);
            addall();


            //init();

/*
            for(int i=0;i<number_of_birds;i++){

            int x =(int) (Math.random() * 4);
            //x=1;          //  for testing
            int y =(int) (Math.random() * angles_allowed.length);

            switch (x) {
                case 0:
                    ducks[i].setter_obj(Red);
                    ducks_pic[i].setImage(Red.pic_location);

                    break;
                case 1:
                    ducks[i].setter_obj(Yellow);
                    ducks_pic[i].setImage(Yellow.pic_location);
                    break;
                case 2:
                    ducks[i].setter_obj(Blue);
                    ducks_pic[i].setImage(Blue.pic_location);
                    break;
                case 3:
                    ducks[i].setter_obj(Black);
                    ducks_pic[i].setImage(Black.pic_location);
                    break;
                default:
                    break;
            }

            ducks[i].set_entry_date(new Date());

            ducks[i].angle=angles_allowed[y];
            ducks_pic[i] = new GImage("Images\\Images\\"+(x+1)+"\\"+(int)ducks[i].angle+".png");
        }


    for(int i=0; i<number_of_birds; i++){
        ducks_pic[i].setSize(ducks[i].get_size(),ducks[i].get_size());
    }

            System.out.println("size setted");
            System.out.println(ducks[0].get_size());

        //          Start all threads for initial and suspend


        //          Seting initial location of birds

        for(int i=0;i<number_of_birds;i++){

            int x =(int) ((Math.random() * 1300) + 200);
            int y = (int) ((Math.random() * 600) + 50);

            ducks_pic[i].setLocation( x, y);

        }
        */


            while (true) {


                move_all();
                check_collision();
                check_death();
                check_has_left();
                update_scoring();
                round_over();

                frame++;

                pause(1000 / fps);

                //add(rc);
                //add(rc1);

                if (is_over) {
                    System.out.println("inside is over");
                    is_over = false;
                    break;
                }

            }

            System.out.println("out of inner loop");
            //init();
            round_num++;
            //removeAll();
            if (round_num % bos_round == 0) {
/*
            number_of_birds = 4;
            boss_active = 1;
            ducks[0].setter_obj(Red);
            ducks[2].setter_obj(Blue);
            ducks[1].setter_obj(Yellow);
            ducks[3].setter_obj(Black);
            ducks_pic[0].setImage("Images\\Images\\boss\\1\\0.png");
            ducks_pic[2].setImage("Images\\Images\\boss\\3\\0.png");
            ducks_pic[1].setImage("Images\\Images\\boss\\2\\0.png");
            ducks_pic[3].setImage("Images\\Images\\boss\\4\\0.png");


            for(int i=0;i<number_of_birds;i++){

            ducks[i].set_entry_date(new Date());

            int y =(int) (Math.random() * angles_allowed.length);
            ducks[i].angle=angles_allowed[y];
            ducks_pic[i] = new GImage("Images\\Images\\boss\\"+(i+1)+"\\"+(int)ducks[i].angle+".png");

*/

            }

/*

            ducks[0].hitpoints = 160;
            ducks[0].speed = 10;
            ducks[0].leavetime = 35*1000;
            ducks[0].set_size((int) (400*screenWidthFraction));
            ducks[0].set_has_left(false);
            ducks[0].set_entry_date(new Date());

            ducks[1].hitpoints = 200;
            ducks[1].speed = 15;
            ducks[1].leavetime = 40*1000;
            ducks[1].set_size((int) (400*screenWidthFraction));
            ducks[1].set_has_left(false);
            ducks[1].set_entry_date(new Date());

            ducks[2].hitpoints = 100;
            ducks[2].speed = 8;
            ducks[2].leavetime = 25*1000;
            ducks[2].set_size((int) (400*screenWidthFraction));
            ducks[2].set_has_left(false);
            ducks[2].set_entry_date(new Date());
*/

            for (int i = 0; i < number_of_birds; i++) {
                ducks_pic[i].setSize(ducks[i].getSize(), ducks[i].getSize());
                int x = (int) ((Math.random() * 1300) + 200);
                int y = (int) ((Math.random() * 600) + 50);
                ducks_pic[i].setLocation(x, y);
            }

            //continue;


            boss_active = 0;

            number_of_birds = original_num_birds;
            for (int i = 0; i < number_of_birds; i++) {
                int x = (int) (Math.random() * 4);
                int y = (int) (Math.random() * angles_allowed.length);
                switch (x) {
                    case 0:
                        ducks[i] = new RedDuck();
                        break;
                    case 1:
                        ducks[i] = new BlueDuck();
                        break;
                    case 2:
                        ducks[i] = new YellowDuck();
                        break;
                    case 3:
                        ducks[i] = new BlackDuck();
                        break;
                    default:
                        break;
                }
                ducks[i].setAngle(angles_allowed[y]);
                ducks_pic[i] = new GImage("Images\\Images\\" + (x + 1) + "\\" + (int) ducks[i].getAngle() + ".png");
            }


            for (int i = 0; i < number_of_birds; i++) {
                ducks_pic[i].setSize(ducks[i].getSize(), ducks[i].getSize());
            }

            System.out.println("size setted");
            System.out.println(ducks[0].getSize());

            //          Start all threads for initial and suspend


            //          Seting initial location of birds

            for (int i = 0; i < number_of_birds; i++) {

                int x = (int) ((Math.random() * 1300) + 200);
                int y = (int) ((Math.random() * 600) + 50);

                ducks_pic[i].setLocation(x, y);

            }


        }
    }


    public void addall(){

        add(level2);
        add(level3);
        add(level1);

        add(Gun1);
        add(Gun2);
        add(Gun3);
        add(Gun4);



//        add(Gun);

//        add(Pointer);


       // add(rc1);



        add(_level1);

        add(rc2);
        set_loc_add();
        for(int i=0; i<number_of_birds; i++){
            if(ducks[i].isAlive())
            add(ducks_pic[i]);
        }

        add(_gun1);
        add(_gun2);
        add(_gun3);
        add(_gun4);



        add(_gun);



        add(_pointer);




    }

    public void render(){

        removeAll();

        //              Perform Changes


        move_all();
        check_collision();
        check_death();
        check_has_left();



        pause(1000/fps);
        //              Add Back All

        addall();

    }

    private void check_death(){
        for(int i=0; i<number_of_birds ;i++){
            if(ducks[i].checkDeath()) {
                Duck_hunt.gamer.set_score(Duck_hunt.gamer.get_score()+ducks[i].getScore());
                remove(ducks_pic[i]);
                gamer.kill_inc(ducks[i].getType());
                System.out.println(Duck_hunt.gamer.get_score());
            }
        }
    }

    public void move_all(){
        for(int i=0; i<number_of_birds; i++){
            ducks[i].move();
        }
    }

    public void check_has_left(){
        for(int i=0;i<number_of_birds;i++){
            ducks[i].checkLeave();
        }
    }

    public void check_collision(){
        for(int i=0; i<number_of_birds; i++) {
            ducks[i].checkCollision();

    /*       if(boss_active == 0)
                ducks_pic[i].setImage("Images\\Images\\"+(x+1)+"\\"+(int)ducks[i].angle+".png");
            else{
                ducks_pic[i].setImage("Images\\Images\\boss\\"+(x+1)+"\\"+(int)ducks[i].angle+".png");
            }
    */
        }
        update_pic();
        move_all();
    }

    public void update_pic() {
        for (int i = 0; i < number_of_birds; i++) {
  //          ducks_pic[i].setImage(ducks[i].getPic());
            ducks_pic[i].setSize(ducks[i].getSize(), ducks[i].getSize());
        }
    }
    @Override
    public void keyPressed(KeyEvent e){
        System.out.println(player_blue_duck.getLocation().getY());
        System.out.println(rc2.getLocation().getY());
        switch(e.getKeyCode()){
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                                active_gun++;
                                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                                active_gun--;
                                break;
        }
        if(active_gun<0)
            active_gun=3;
        if(active_gun>3)
            active_gun=0;

        remove(_pointer);
        remove(_gun);
        _gun.setImage("Images\\Images\\Guns\\"+(active_gun+1)+"_2_1"+".png");
        _pointer.setImage("Images\\Images\\Pointers\\" + (active_gun+1) + ".png");
        _pointer.setSize(50, 50);
        _gun.setSize(300*GameGlobalVariables.getInstance().getScreenWidthFraction(),400*GameGlobalVariables.getInstance().getScreenHeightFraction());
        add(_gun);
        add(_pointer);
        System.out.println(active_gun);
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

            remove(_gun);
            remove(_pointer);
            _pointer.setLocation(e.getX()-25*screenWidthFraction,e.getY()-25*screenWidthFraction);
            _gun.setLocation(e.getX()-50*screenWidthFraction,600*screenHeightFraction);
            _gun.setSize(300*screenWidthFraction,400*screenHeightFraction);
            _pointer.setSize(50*screenWidthFraction,50*screenHeightFraction);
            add(_pointer);
            add(_gun);
        }

}

    @Override
    public void mouseDragged(MouseEvent e){

        if(e.getY()<750*screenHeightFraction){
            remove(_gun);
            remove(_pointer);
            _pointer.setLocation(e.getX()-25*screenWidthFraction,e.getY()-25*screenWidthFraction);
            _gun.setLocation(e.getX()-50*screenWidthFraction,600*screenHeightFraction);
            _gun.setSize(300*screenWidthFraction,400*screenHeightFraction);
            _pointer.setSize(50*screenWidthFraction,50*screenHeightFraction);
            add(_pointer);
            add(_gun);
        }

    }

    @Override
    public void mouseClicked(MouseEvent e){

        System.out.println(active_gun);

        Date dt = new Date();

        if(rc.contains(e.getX(),e.getY()) || rc1.contains(e.getX(),e.getY()))
        {
            new Error().run("How dare you kill a fellow human you murderer ?");
            System.exit(0);
        }
        


        // __gun[active_gun].set_previous_shoot(frame);
        
        for(int i=0; i<number_of_birds; i++){
            if(ducks[i].isAlive()) {
                if (ducks_pic[i].contains(e.getX(), e.getY())) {
                    ducks[i].shot(__gun[active_gun].shot());
                }
            }
        }
        
        //          Sounds

            
            
        
        
        
        /*
    playwav ak_sd = new playwav(ak.getsd());
    playwav shot_sd = new playwav(shotgun.getsd());
    playwav laser_sd = new playwav(laser.getsd());
    playwav sniper_sd = new playwav(sniper.getsd());
        */  
        
    }
    
    @Override
    public void mousePressed(MouseEvent e){

            
    }
    
    @Override
    public void mouseReleased(MouseEvent e){


    }

}
