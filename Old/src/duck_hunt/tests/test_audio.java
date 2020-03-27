
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duck_hunt.tests;

import duck_hunt.Restart.utilities.playwav;

/**
 *
 * @author user
 */
public class test_audio {
    public static void main(String[] args){
        playwav p = new playwav("Song.wav");
        p.start();
    }
}
