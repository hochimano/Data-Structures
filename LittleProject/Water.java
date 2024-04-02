package LittleProject;

import java.awt.Color;
/*
 * this represents a pixel of water on teh screen
 */
public class Water extends Pixel{

    public Water(int x, int y) {
        super(x, y);
        _color = Color.cyan;

        tolerance = 0;

        flows = true;
    }


    
}
