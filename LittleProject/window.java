package LittleProject;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
/*
 * this is just a little window used to dispaly the program on a JFrame
 * runs the thread that updates and renders the screen
 */
public class window extends JFrame{
    screen _screen;
    
    public window(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 600);
        setResizable(false);
        setTitle("Sand Sim");
        _screen = new screen(600, 600);
        add(_screen);
        setVisible(true);
        addKeyListener(new KeyInterceptor());
        TimerTask task = new TimerTask() {

            public void run(){
                _screen.Render();
            }
        };

        TimerTask updateScreen = new TimerTask() {
            
            public void run(){
                _screen.update();
            }
        };
        

        //WOAH threading!
        Timer tick = new Timer();
        tick.schedule(task, 0, 9);

        tick.schedule(updateScreen, 0,  18);

        
    }



}
