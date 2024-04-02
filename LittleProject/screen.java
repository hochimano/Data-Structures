package LittleProject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JPanel;



public class screen extends JPanel implements MouseListener, MouseMotionListener{
    enum element{
        Water, Sand, Void
    }
    //two arrays one is for rendering the other is for updating the other
    Pixel[][] state;
    Pixel[][] state2;
    Queue<Pixel> render;
    //meant to be a height map for the arrays used for the physics part
    int[] height;
    static element type;

    public screen(int x, int y){
        setSize(new Dimension(x, y));
        addMouseListener(this);
        addMouseMotionListener(this);
        type = element.Sand;

        setBackground(new Color(100, 100 ,100));

        state = new Pixel[x-1][y - 1];
        state2 = new Pixel[x-1][y - 1];
        height = new int[x - 1];
        render = new LinkedList<Pixel>();

        for(int i = 0; i < x - 1; i++){
            height[i] = y - 40;
        }
    }

    /*
     * renders the screen called every 18 ms
     */
    public void Render(){
        Graphics g = getGraphics();
        //  for(int i = 0; i < state.length; i++){
        //      for(int j = 0; j < state[i].length; j++){
        //          if(state[i][j] == null){
        //              g.setColor(getBackground());
        //          } else g.setColor(state[i][j].getColor());
        //          g.drawLine(i, j, i, j);
        //     }
        // }       
         while(!render.isEmpty()){
             Pixel p = render.remove();
             g.setColor(p.getColor());
             g.drawLine(p.getX(), p.getY(), p.getX(), p.getY());
        }
    }

    /*
     * incredibly bad could prolly be made way faster and more efficient 
     * but it gets the job done
     */
    public void update(){
        //main loop for going through every pixel on the screen yes i know its bad
        for(int i = 0; i < state.length; i++){
            for(int j = state[i].length - 1; j > 0; j--){
                // checks state to see if it needs to do the big logic
                if(state[i][j] != null && state[i][j].getColor() != getBackground()){
                updatePixel(state[i][j]);
                }
            }
        }
    state = state2.clone();
    state2 = new Pixel[state.length][state[0].length];
}

public void horizontalPhysics(Pixel p){
    if((height[p.getX()] + p.getTolerance() < height[Math.min(p.getX() + 1, getWidth() - 1)]) && p.getY() < getHeight() - 1){
        render.add(new Void(p.getX(), p.getY(), getBackground()));
        height[p.getX()]++;
        p.moveInX(1);
        p.setY(height[p.getX()]);
        height[p.getX()]--;
        render.add(p);
    } else if((height[p.getX()] + p.getTolerance() < height[Math.max(p.getX() - 1, 0)]) && p.getY() < getHeight() - 1){
        render.add(new Void(p.getX(), p.getY(), getBackground()));
        height[p.getX()]++;
        p.moveInX(-1);
        p.setY(height[p.getX()]);
        height[p.getX()]--;
        render.add(p);
    }
}
/*
 * updates the position of the pixel
 */
public void updatePixel(Pixel p){
        //This checks to see if the pixle is above the height of the stack on its X value
        if(p.getY() < height[p.getX()]){
            render.add(new Void(p.getX(), p.getY(), getBackground()));
            state[p.getX()][p.getY()] = null;
            p.yAccelerate();
            p.doTranslate();

            //checks to see if the new pixel will be below another pixel
            if(p.getY() > height[p.getX()]){
                p.setY(height[p.getX()]);
                p.setVelocity(0, 0);
                height[p.getX()]--;
            }
            render.add(p);
        //checks to see if the pixel below the current pixel is null used for physics when erasing stuff
        } else if(p.getY() > height[p.getX()] && state[p.getX()][Math.min(p.getY() + 1, getHeight() - 1)] == null){
            render.add(new Void(p.getX(), p.getY(), getBackground()));
            height[p.getX()] = Math.min(p.getY() + 1, getHeight() - 1);
            p.setY(height[p.getX()]);
            render.add(p);
            
        } else if(p.getY() == height[p.getX()] + 1){
            if(p.getColor().equals(Color.black) && Math.random() * 1000 > 980){
                p = new Grass(p.getX(), p.getY());
                render.add(p);
            }
            horizontalPhysics(p);
        }

        if(p.getColor().equals(Color.green) && p.getY() > height[p.getX()] + 1 && Math.random() * 10 > 8){
            render.add(new Pixel(p.getX(), p.getY()));
        }
    state2[p.getX()][p.getY()] = p;
}
    

public void clear(){
    Graphics g = getGraphics();
    g.setColor(getBackground());
    g.fillRect(0, 0, getWidth(), getHeight());
}

    public void addPoint(int x, int y){
        if(x < getWidth() - 2 && y < getHeight() - 2 && x > 0 && y > 0){
            state2[x][y] = new Pixel(x, y);
            state2[x + 1][y] = new Pixel(x + 1, y);
            state2[x + 1][y + 1] = new Pixel(x + 1, y + 1);
            state2[x][y + 1] = new Pixel(x, y + 1);
        }
    }

    public void addWater(int x, int y){
        if(x < getWidth() - 2 && y < getHeight() - 2 && x > 0 && y > 0){
            state2[x][y] = new Water(x, y);
            state2[x + 1][y] = new Water(x + 1, y);
            state2[x + 1][y + 1] = new Water(x + 1, y + 1);
            state2[x][y + 1] = new Water(x, y + 1);
        }
    }

    public void addVoid(int x, int y){
        if(x < getWidth() - 1 && y < getHeight() - 1 && x > 0 && y > 0){
            if(state[x][y] != null) height[x]++;
            if(state[x][y + 1] != null) height[x]++;
            if(state[x + 1][y] != null) height[x + 1]++;
            if(state[x + 1][y + 1] != null) height[x + 1]++;
            state[x][y] = null;
            state[x + 1][y] = null;
            state[x + 1][y + 1] = null;
            state[x][y + 1] = null;

        }
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        switch(type){
            case Void:
            addVoid(e.getX(), e.getY());
            break;
            case Sand:
            addPoint(e.getX(), e.getY());
            break;
            case Water:
            addWater(e.getX(), e.getY());
            break;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch(type){
            case Void:
            addVoid(e.getX(), e.getY());
            break;
            case Sand:
            addPoint(e.getX(), e.getY());
            break;
            case Water:
            addWater(e.getX(), e.getY());
            break;
        }
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch(type){
            case Void:
            addVoid(e.getX(), e.getY());
            break;
            case Sand:
            addPoint(e.getX(), e.getY());
            break;
            case Water:
            addWater(e.getX(), e.getY());
            break;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
