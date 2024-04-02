package LittleProject;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import LittleProject.screen.element;
/*
 * gets input from key strokes and changes the type of pixel spawned
 */
public class KeyInterceptor implements KeyListener{

    @Override
    public void keyTyped(KeyEvent e) {
       char ch = e.getKeyChar();
       switch (Character.toUpperCase(ch)) {
        
        case '1':
            screen.type = element.Sand;
            break;
        case '2':
            screen.type = element.Water;
            break;
        case '3':
            screen.type = element.Void;
       }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
   
        
    }
    
}
