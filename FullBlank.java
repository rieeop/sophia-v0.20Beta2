import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class FullBlank here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FullBlank extends Actor
{
    /**
     * Act - do whatever the FullBlank wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int time = 0;
    GreenfootImage img = new GreenfootImage(970, 600);
    GreenfootImage noImg = new GreenfootImage(970, 600);
    Color trans = new Color(120, 100, 255, 0);
    Color violet = new Color(148, 61, 214, 17);
    Color dark = new Color(10,10,10,3); 
    public void act() 
    {
       /* 
        if(MyWorld.getOptions()){
            openBlank();
        } else{
            closeBlank();
        }
        
        if(Greenfoot.mouseClicked(this)){
            MyWorld.setOptions(false);
        }
        
        */
        
        
       
    }    
    
    public void openBlank(){
        img.fill();
        setImage(img);
        this.setLocation(50, 300);
    }
    
    public FullBlank(Color c){
        img.setColor(c);
    }
    
    public void closeBlank(){
        
        noImg.setColor(trans);
        noImg.fill();
        setImage(noImg);
        this.setLocation(0, 0);
    }
}
