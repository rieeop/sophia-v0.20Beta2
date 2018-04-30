import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Selection here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Selection extends Actor
{
    /**
     * Act - do whatever the Selection wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    GreenfootImage div  = new GreenfootImage(400,90);
    GreenfootImage t = new GreenfootImage(20, 20);
     int index = 1;    
    String text = "";
    Color transparent = new  Color(10,10,10, 0);
    Color white = new Color(255,255,255);
    Color green = new Color(30, 255, 30);
    Color yellow = new Color(255, 255, 0, 40); 
    
    int margin = 0;
    public void act() 
    {
        
       
        this.setLocation(getX()-70, getY());
            t.setColor(transparent);
            t.fill();
            if(getX() < 40){
                setImage(t);
            }
            
        
        if(MyWorld.getOptions()){
           
                div.setColor(transparent);
                
        div.fill();
        div.setFont(new Font("Cooper Black", 60));
        if(this.index == MyWorld.getIndex()){
            div.setColor(green);
        } else{
            div.setColor(white);
        }
        
        div.drawString(text, margin, 45);
        setImage(div);
       
        if( getX() < 280){
            this.setLocation(getX() + 70, getY());
        }
        }
        if(MyWorld.getOptions() && getX() < 280){
            this.setLocation(getX() + 70, getY());
        }
        
        
        if(MyWorld.getOptions()){
            if((this.index == 1 &&Greenfoot.mouseClicked(this)) ||
                (MyWorld.getIndex() == 1 && Greenfoot.isKeyDown("enter"))){
                MyWorld.setOptions(false);
                
            } else if((this.index == 2 &&Greenfoot.mouseClicked(this)) || 
                (MyWorld.getIndex() == 2 && Greenfoot.isKeyDown("enter")) ){
                MyWorld.setOptions(false);
                MyWorld.setReset(true);
            } else if((this.index == 5 &&Greenfoot.mouseClicked(this)) ||
            (MyWorld.getIndex() == 5 && Greenfoot.isKeyDown("enter"))){
                MyWorld.setClose(true);
            }
        }
    }   
    
    
    public Selection(int a){
        this.index = a;
        if(a == 1){
            text = "Continue";
            margin = 40;
        } else if(a == 2){
            text = "New Game";
            margin = 016;
        } else if(a == 3){
            text = "Settings";
            margin = 48;
        } else if(a==4){
            text = "About";
            margin = 72;
        } else if(a == 5){
            text = "Exit";
            margin = 104;
        }
    
        
        t.setColor(transparent);
            t.fill();
            setImage(t);
    }
}
