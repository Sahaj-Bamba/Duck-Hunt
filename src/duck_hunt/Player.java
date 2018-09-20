/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duck_hunt;
import java.awt.*;

public class Player {
    public Color colour;
    public int score;
    public String name;
    
    public Player(String a) 
    {
        this.name = a;
        this.score = 0;
    }
    
}