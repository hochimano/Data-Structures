package LittleProject;

import java.awt.Color;
/*
* this represents a pixel on the screen
*/
public class Pixel {
  private int _x;
  private int _y;

  private int yVelocity;

  private int xVelocity;

  protected Color _color;

  protected int tolerance;

  protected boolean flows;



  public Pixel(int x, int y){
    _x = x;
    _y = y;

    yVelocity = 1;
    xVelocity = 0;

   _color = Color.black;
    

    flows = false;

    tolerance = 2;
  }

  public void yAccelerate(){
    yVelocity += Math.random() * 2;
  }

  public void xAccelerate(){
    xVelocity += Math.random() * 2;
  }

  public void doTranslate(){
    _y += yVelocity;
    _x += xVelocity;
  }

  public void moveInX(int other){
    _x += other;
    if(_x < 0){
      _x = 0;
    }
  }

  public int getYVelocity(){
    return yVelocity;
  }

  public int getX(){
    return _x;
  }

  public int getY(){
    return _y;
  }

  public void addX(int other){
    _x += other;
  }

  public Color getColor() {
    return _color;
  }

  public void setVelocity(int otherX, int otherY){
    yVelocity = otherY;
    xVelocity = otherX;
  }

  public void setY(int other){
    _y = other;
  }

  public int getTolerance(){
    return tolerance;
  }

  public boolean flows(){
    return flows;
  }


}
