package LittleProject;

import java.awt.Color;

public class Fire extends Pixel{
    int state;
    public Fire(int x, int y) {
        super(x, y);
        state = 0;

        _color = Color.red;
    }

    public boolean update(){
        if(state > 5){
            return true;
        }
        state++;
        return false;
    }
    
}
