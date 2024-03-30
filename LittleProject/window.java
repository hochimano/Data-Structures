package LittleProject;




import javax.swing.JFrame;

public class window extends JFrame{
    screen _screen;
    public window(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 300);
        setResizable(false);
        setTitle("Sand Sim");
        _screen = new screen();
        add(_screen);

        setVisible(true);
    }
}
