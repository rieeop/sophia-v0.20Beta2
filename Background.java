import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Background here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Background extends Actor
{
    /**
     * Act - do whatever the Background wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int score = 0;
    int hiNumbr = 0;
    int bestScore = 0;
    
    Color dark = new Color(17, 17, 17);
        Color white = new Color(255, 255, 255);
        Color purple = new Color(150,0,255);
        Color bgColor = dark;
    public void act() 
    {
        show();
        score = MyWorld.getScore();
       hiNumbr = MyWorld.getHiNum();
       bestScore= MyWorld.getBestScore();
       
       
    } 
    
    public void show(){
       
        
         // Main Background
        GreenfootImage bgMain = new GreenfootImage(286 + 190, 286 + 190);
        GreenfootImage bgMainX = new GreenfootImage(266 + 170, 286 + 190);
        GreenfootImage bgMainY = new GreenfootImage(286 + 190, 266 + 170);
        
        
        bgMainX.setColor(dark);
        bgMainY.setColor(dark);
        bgMainX.fill();
        bgMainY.fill();
        
       bgMain.drawImage(bgMainX, 20, 0);
       bgMain.drawImage(bgMainY, 0, 20);
       bgMain.drawImage(bgMainX, 20, 276);
       bgMain.drawImage(bgMainY, 276, 20);
      
        
        GreenfootImage point = new  GreenfootImage(40, 40);
        point.setColor(dark);
        point.drawOval(0, 0, 40, 40);
        point.fillOval(0, 0, 40, 40);
        bgMain.drawImage(point, 0, 0);
        bgMain.drawImage(point, 266+170, 0);
        bgMain.drawImage(point, 0, 266+170);
        bgMain.drawImage(point, 266+170, 266+170);
        
        
        
        for(int a = 0; a < 4; a++){
            for(int b = 0; b < 4; b++){
            
                int x = 98;
                int y = 98;
                int X = b* 114 + 16;
                int Y = a * 114 + 19;
              
               bgColor = new Color(37, 37, 37);
               int m = 20;
               GreenfootImage imgX = new GreenfootImage(80, 142);
               
               GreenfootImage imgY = new GreenfootImage(142, 80);
               imgX.setColor(bgColor);
               imgY.setColor(bgColor);
               imgX.fill();
               imgY.fill();
               GreenfootImage p = new GreenfootImage(x, y);
               
               p.drawImage(imgX, 10, 0);
               p.drawImage(imgY, 0, 10);
               int pX = X;
               int pY = Y;
              
                
               GreenfootImage point1 = new  GreenfootImage(2*m, 2*m);
               point1.setColor(bgColor);
               //point.setColor(new Color(255,255,255));
               point1.drawOval(0, 0, m+3, m+3);
               point1.fillOval(0, 0, m+3, m+3);
               p.drawImage(point1, ((100-x)/2)+0, ((100-x)/2)+0);
               p.drawImage(point1, x-m-3, ((102-x)/2)+0);
               p.drawImage(point1, ((102-x)/2)+0, y-m-3);
               p.drawImage(point1, x-m-4, y-m-4);
               
               
                   bgMain.drawImage(p, pX, pY);
            
            
            
            
            }
        }
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        //Score Background
        
        GreenfootImage bgScore = new GreenfootImage(120, 60);
        
        int x = 120;
        int y = 60;
        int m = 20;
          
        GreenfootImage imgX = new GreenfootImage(x-2*m+12, y);
        GreenfootImage imgY = new GreenfootImage(x, y-2*m+12);
        
        
        imgX.setColor(bgColor);
        imgY.setColor(bgColor);
        imgX.fill();
        imgY.fill();
        
       bgScore.drawImage(imgX, m-6, 0);
       bgScore.drawImage(imgY, 0, m-6);
      
      
        
        GreenfootImage point2 = new  GreenfootImage(2*m, 2*m);
        point2.setColor(bgColor);
      // point2.setColor(new Color(255,255,255));
        point2.drawOval(0, 0, m+3, m+3);
        point2.fillOval(0, 0, m+3, m+3);
        bgScore.drawImage(point2, 0, 0);
        bgScore.drawImage(point2, x-m-3, 0);
        bgScore.drawImage(point2, 0, y-m-3);
        bgScore.drawImage(point2, x-m-4, y-m-4);
        
        
        
        

        GreenfootImage txtscr = new GreenfootImage(score + "",36, white, dark);
        bgScore.drawImage(txtscr, 60 - (this.score+"").length()*8, 18);
        GreenfootImage scr = new GreenfootImage("     SCORE",17, purple, dark);
        bgScore.drawImage(scr, 17, 2);
       
        
        GreenfootImage bgBest = new GreenfootImage(120, 60);
        //

       bgBest.drawImage(imgX, m-6, 0);
       bgBest.drawImage(imgY, 0, m-6);
       
     
        point2.drawOval(0, 0, m+3, m+3);
        point2.fillOval(0, 0, m+3, m+3);
        bgBest.drawImage(point2, 0, 0);
        bgBest.drawImage(point2, x-m-3, 0);
        bgBest.drawImage(point2, 0, y-m-3);
        bgBest.drawImage(point2, x-m-4, y-m-4);
        
        //
        
        
        GreenfootImage txtbest = new GreenfootImage(bestScore + "",36, white, dark);
        bgBest.drawImage(txtbest, 60 - (this.bestScore+"").length()*8, 18);
        GreenfootImage best = new GreenfootImage("BEST SCORE",17, purple, dark);
        bgBest.drawImage(best, 18, 2);
        
        
        GreenfootImage hiNum = new GreenfootImage(120, 60);
       hiNum.drawImage(imgX, m-6, 0);
       hiNum.drawImage(imgY, 0, m-6);
       
        
        hiNum.drawImage(point2, 0, 0);
        hiNum.drawImage(point2, x-m-3, 0);
        hiNum.drawImage(point2, 0, y-m-3);
        hiNum.drawImage(point2, x-m-4, y-m-4);
        
        GreenfootImage title = new GreenfootImage(hiNumbr+ "",36, white, dark);
        hiNum.drawImage(title, 60 - (this.hiNumbr+"").length()*8, 18);
        GreenfootImage hinum = new GreenfootImage("  HIGH-NUMBER",17, purple, dark);
        hiNum.drawImage(hinum, 6, 2);
        
        
        GreenfootImage img = new GreenfootImage(970, 600);
        img.drawImage(bgMain, 60, 110);
        img.drawImage(bgScore, 81, 37);
        img.drawImage(bgBest, 226, 37);
        img.drawImage(hiNum, 371, 37);
        setImage(img);
    }
    
    public Background(){
        show();
    }
}
