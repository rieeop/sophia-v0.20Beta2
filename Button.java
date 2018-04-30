import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot 
/**
 * Write a description of class Buttons here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Button extends Actor
{
    /**
     * Act - do whatever the Buttons wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int x = 70;
    int y = 70;
    boolean playMusic = true;
    GreenfootImage img = new GreenfootImage(70, 70);
    String type = "options";
    boolean clicked;
    String text = "...";
    public void act() 
    {
        if(!clicked && Greenfoot.mouseClicked(this)){
          MyWorld.setOptions(true);
          
        }
        
        show();
       
    }    
    public Button(){
        
        show();
        
    }
    
    
    
    public void show(){
     if(type.equals("options")){
        GreenfootImage txt = new GreenfootImage(x, y);
        txt = new GreenfootImage(text,80, new Color(17, 17 ,17), new  Color(255,100,100,0));
        setRotation(90);
        txt.setFont(new Font("Calibri",  12));
        img.drawImage(txt, 0, 0);
        
        setImage(img);
        }
        
    }
}
