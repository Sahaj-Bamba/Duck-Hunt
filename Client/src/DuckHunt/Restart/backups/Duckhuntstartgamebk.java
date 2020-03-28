package duck_hunt.Restart.backups;

public class Duckhuntstartgamebk {






	//    addall();


	//init();

/*
            for(int i=0;i<numberOfBirds;i++){

            int x =(int) (Math.random() * 4);
            //x=1;          //  for testing
            int y =(int) (Math.random() * anglesAllowed.length);

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

            ducks[i].angle=anglesAllowed[y];
            ducks_pic[i] = new GImage("Images\\Images\\"+(x+1)+"\\"+(int)ducks[i].angle+".png");
        }


    for(int i=0; i<numberOfBirds; i++){
        ducks_pic[i].setSize(ducks[i].get_size(),ducks[i].get_size());
    }

            System.out.println("size setted");
            System.out.println(ducks[0].get_size());

        //          Start all threads for initial and suspend


        //          Seting initial location of birds

        for(int i=0;i<numberOfBirds;i++){

            int x =(int) ((Math.random() * 1300) + 200);
            int y = (int) ((Math.random() * 600) + 50);

            ducks_pic[i].setLocation( x, y);

        }
        */

/*
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
*/
/*            System.out.println("out of inner loop");
            //init();
            roundNumber++;
            //removeAll();
            if (roundNumber % bossRoundNumber == 0) {

            numberOfBirds = 4;
            boss_active = 1;
            ducks[0].setter_obj(Red);
            ducks[2].setter_obj(Blue);
            ducks[1].setter_obj(Yellow);
            ducks[3].setter_obj(Black);
            ducks_pic[0].setImage("Images\\Images\\boss\\1\\0.png");
            ducks_pic[2].setImage("Images\\Images\\boss\\3\\0.png");
            ducks_pic[1].setImage("Images\\Images\\boss\\2\\0.png");
            ducks_pic[3].setImage("Images\\Images\\boss\\4\\0.png");


            for(int i=0;i<numberOfBirds;i++){

            ducks[i].set_entry_date(new Date());

            int y =(int) (Math.random() * anglesAllowed.length);
            ducks[i].angle=anglesAllowed[y];
            ducks_pic[i] = new GImage("Images\\Images\\boss\\"+(i+1)+"\\"+(int)ducks[i].angle+".png");

*/


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

/*
            for (int i = 0; i < numberOfBirds; i++) {
                ducks_pic[i].setSize(ducks[i].getSize(), ducks[i].getSize());
                int x = (int) ((Math.random() * 1300) + 200);
                int y = (int) ((Math.random() * 600) + 50);
                ducks_pic[i].setLocation(x, y);
            }

            //continue;

*/
 /*           boss_active = 0;

            numberOfBirds = original_num_birds;
            for (int i = 0; i < numberOfBirds; i++) {
                int x = (int) (Math.random() * 4);
                int y = (int) (Math.random() * anglesAllowed.length);
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
                ducks[i].setAngle(anglesAllowed[y]);
                ducks_pic[i] = new GImage("Images\\Images\\" + (x + 1) + "\\" + (int) ducks[i].getAngle() + ".png");
            }


            for (int i = 0; i < numberOfBirds; i++) {
                ducks_pic[i].setSize(ducks[i].getSize(), ducks[i].getSize());
            }

            System.out.println("size setted");
            System.out.println(ducks[0].getSize());

            //          Start all threads for initial and suspend

            //          Seting initial location of birds

            for (int i = 0; i < numberOfBirds; i++) {

                int x = (int) ((Math.random() * 1300) + 200);
                int y = (int) ((Math.random() * 600) + 50);

                ducks_pic[i].setLocation(x, y);

            }


        }
   */

}
