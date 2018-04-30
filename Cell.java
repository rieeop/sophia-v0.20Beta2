import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.*;
/**
  Sophia 0.18 BETA
 **/
 
public class Cell extends Actor
{
     int countTime = 0;
     int lastClicked = -31;
     boolean clicked = false;
     boolean sampleValuesAreSet = false;
     int click = 0;
     int move = 0;
    
     int[][] matrix = new int[4][4];
     String[][] visual = new String[4][4];
     boolean moved = false;
     boolean shouldMakeRandom = false;
     int countRandom = 0;
    int randomIndex = 0;
    int lastRandomMadeTime = 0;
    int countMatch = 0;
    int score = 0;
    int scorePlus = 0;
    int title = getTitle();
    String fileName = "db.txt";
    // undo
   int[][] pass = new int[2][3];
   int[][][] past = new int[3][4][4];
   
    boolean undid = false;
    int hiNum = 0;
    int highScore = 0;
    String cmd = "";
    int bgMainX = 556;
    int bgMainY = 556;
    
    
    int xPlus[][] = new int[4][4];
    int yPlus[][] = new int[4][4];
    
    int pX = 0;
    int pY = 0;
    
    int size = 100;
    boolean started = false;
    int fontSize = 36;
    int x = size;
    int y = size;
    int marginLeft = 10;
    Color bgColor = new Color(255, 255, 255, 0);
    
    boolean music = true;
    boolean option = false;
    
      static int c[] = new int[4];
      static String s[] = new String[4];
    
      
      
    public void act() 
    {   
        
        MyWorld.setValue(matrix);
        
        MyWorld.setTime(countTime);
        MyWorld.setScore(score);
        MyWorld.setBestScore(highScore);
        MyWorld.setHiNum(hiNum);
        this.option = MyWorld.getOptions();
        countTime();
        check();
        if(!isGameOver() && !MyWorld.getOptions()){
                
                
                
                
                if(!clicked ){
                    
                    if(Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("w")){
                        
                        cmd = "up";
                        receive();
                        countClick();
                    } else if(Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("s")){
                        cmd = "down";
                        receive();
                        countClick();
                    }else if(Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("d")){
                        cmd = "right";
                        receive();
                        countClick();
                    } else if(Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("a")){
                        cmd = "left";
                        receive();
                        countClick();
                    } else if(Greenfoot.isKeyDown("enter")){
                        save();
                    } else if(Greenfoot.isKeyDown("r") || MyWorld.getReset()){
                        reset();
                        MyWorld.setReset(false);
                    } else if(Greenfoot.isKeyDown("u")){
                        undo();
                    } else if(Greenfoot.isKeyDown("escape")){
                        MyWorld.setOptions(!option);
                        lastClicked = countTime;
                        clicked = true;
                        click++;
                    }
                    countEmpty();
                    pass();
                    MyWorld.setCommand(cmd);
                    
                }               
                
        } else if( MyWorld.getOptions()&& !clicked){
            if( Greenfoot.isKeyDown("escape") ) {
                MyWorld.setOptions(!option);
                lastClicked = countTime;
                clicked = true;
                click++;
                
            } else if(Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("s")){
                countClick();
                MyWorld.moveDown();
            } else if(Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("w")){
                countClick();
                MyWorld.moveUp();
            } else if(MyWorld.getClose()){
                MyWorld.setClose(false);
                save();
                System.exit(0);
                MyWorld.setSave(false);
            }
            
        }else{
            
        }
        showCells();
    }    
    
    public void countClick(){
        lastClicked = countTime;
        clicked = true;
        click++;
        if(moved && countEmpty() > 0){
            move++;
            undid = false;
            moved = false;
            MyWorld.setMoved(true);
            shouldMakeRandom = true;  
        } 
        if(shouldMakeRandom && !MyWorld.getOptions()){
            makeRandom();
        }
        
        
    }
    public void makeRandom(){
        countRandom++;
        int empty = countEmpty();

        if(empty > 0){
            int emptyCells[] = new int[empty];
            int c = 0;
            while(c < empty){
                for(int a = 0; a < 4; a++){
                    for(int b = 0; b < 4; b++){
                        if(matrix[a][b] == 0){
                            emptyCells[c] = a*4+b;
                            c++;
                        }
                    }
                }
            }
            
            randomIndex = emptyCells[Greenfoot.getRandomNumber(empty)];
            int random2 = Greenfoot.getRandomNumber(10);
            lastRandomMadeTime = countTime;
            if(random2 == 1){
                matrix[randomIndex/4][randomIndex%4] = 4;
            } else{
                matrix[randomIndex/4][randomIndex%4] = 2;
            }
        }
    }
    public void countTime(){
        countTime += 1;
    }
    
    
    
    
    public Cell(){
     
        showCells();
       
        
    }

    
    
    
    public String time(){
        int second = (countTime/ 60) % 60;
        int minute = countTime /3600;
        return minute + " : " + second;
    }
    public String toTime(int i){
        int second = (i/ 60) % 60;
        int minute = i /3600;
        return minute + ":" + second + ":" + i%60;
    }
    public void check(){
        if(countTime - lastClicked  > 5   && MyWorld.getOptions()){
            clicked = false;
            moved = false;
            MyWorld.setMoved(false);
        } else if(countTime - lastClicked  > 12){
            clicked = false;
            moved = false;
            MyWorld.setMoved(false);
        }
        
        if(countTime - lastRandomMadeTime > 12){
            
        }
        if(countEmpty() >= 16){
                shouldMakeRandom = true;
        }
            
        if(!sampleValuesAreSet){
            
            load();
           
            sampleValuesAreSet = true;
        }   
        
        if(getTitle() > hiNum){
            hiNum = getTitle();
        }
        if(score > highScore){
            highScore = score;
        }
    }
    //end of check
    public int countEmpty(){
        int empty = 0;
        for(int a = 0; a < 4; a++){
            for(int b = 0; b < 4; b++){
                if(matrix[a][b] == 0){
                    empty++;
                }
            }
        }
        return empty;
    }
    
    public boolean isGameOver(){
       countMatch = 0;
         
        for(int a = 0; a < 3; a++){
            for(int b = 0; b < 4; b++){
                if(matrix[a][b] !=  0 && matrix[a][b] == matrix[a+1][b]){
                    countMatch++;
                }        
            }   
        }    
        
        for(int a = 0; a < 4; a++){
            for(int b = 0; b < 3; b++){
                if(matrix[a][b] !=  0 && matrix[a][b] == matrix[a][b+1]){
                    countMatch++;
                }        
            }   
        }
        
        return countMatch == 0 && countEmpty() == 0;
    }
    
    public int getTitle(){
        return  Math.max(Math.max(Math.max(Math.max(matrix[0][0], matrix[0][1]), Math.max(matrix[0][2], matrix[0][3])), Math.max(Math.max(matrix[1][0], matrix[1][1]), Math.max(matrix[1][2], matrix[1][3]))), Math.max(Math.max(Math.max(matrix[2][0], matrix[2][1]), Math.max(matrix[2][2], matrix[2][3])), Math.max(Math.max(matrix[3][0], matrix[3][1]), Math.max(matrix[3][2], matrix[3][3]))));
       

    }
    
    

   
    
  
  
  
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
  public void receive(){
    for(int a = 0; a < 4; a++){
     
        process(a);
       
    }
    }  
    
    
    public void in(int n){
        for(int i = 0; i < 4; i++){
            s[i] = "stable";
        }
        
    if(cmd.equals("up")){
      c[0] = matrix[0][n];
      c[1] = matrix[1][n];
      c[2] = matrix[2][n];
      c[3] = matrix[3][n];
    } else if(cmd.equals("down")){
      c[0] = matrix[3][n];
      c[1] = matrix[2][n];
      c[2] = matrix[1][n];
      c[3] = matrix[0][n];
    } else if(cmd.equals("left")){
      c[0] = matrix[n][0];
      c[1] = matrix[n][1];
      c[2] = matrix[n][2];
      c[3] = matrix[n][3];
    } else if(cmd.equals("right")){
      c[0] = matrix[n][3];
      c[1] = matrix[n][2];
      c[2] = matrix[n][1];
      c[3] = matrix[n][0];
    } 
    
  }
  
  public void out(int n){
    if(cmd.equals("up")){
      matrix[0][n] = c[0] ;
      matrix[1][n] = c[1];
      matrix[2][n] = c[2];
      matrix[3][n] = c[3];
      
      visual[0][n] = s[0] ;
      visual[1][n] = s[1];
      visual[2][n] = s[2];
      visual[3][n] = s[3];
    } else if(cmd.equals("down")){
      matrix[3][n] = c[0];
      matrix[2][n] = c[1];
      matrix[1][n] = c[2];
      matrix[0][n] = c[3];
    
      visual[3][n] = s[0];
      visual[2][n] = s[1];
      visual[1][n] = s[2];
      visual[0][n] = s[3];
    } else if(cmd.equals("left")){
      matrix[n][0] = c[0];
      matrix[n][1] = c[1];
      matrix[n][2] = c[2];
      matrix[n][3] = c[3];
      
      visual[n][0] = s[0];
      visual[n][1] = s[1];
      visual[n][2] = s[2];
      visual[n][3] = s[3];
    } else if(cmd.equals("right")){
      matrix[n][3] = c[0];
      matrix[n][2] = c[1];
      matrix[n][1] = c[2];
      matrix[n][0] = c[3];
      
      visual[n][3] = s[0];
      visual[n][2] = s[1];
      visual[n][1] = s[2];
      visual[n][0] = s[3];
    }
    
    visual[randomIndex/4][randomIndex%4] = "random";
    
  }
  
  public int countFull(){
    int b = 0;
    for(int a = 0; a < 4; a++){
      if(c[a] != 0){
        b++;
      }
    }
    return b;
  }
  
  public void join(int a, int b){
    c[a] += c[b];
    c[b] = 0;
    
    s[a] = "doubled";
    s[b] = "joined";  
    
    score += c[a];
    scorePlus = c[a];
    if(c[a] != 0){
        moved = true;
        MyWorld.setMoved(true);
    }
  }
  
  
  
  public void move(int a, int b){
    c[b] = c[a];
    c[a] = 0;
    
    s[a] = "moved " + (a-b);
   if(c[a] != 0){
        moved = true;
        MyWorld.setMoved(true);
    }
  }
  
  
  
  
  public void process(int n){
  
    in(n);
    
    
    
    
    s[0] = "stable";
    s[1] = "stable";
    s[2] = "stable";
    s[3] = "stable";
    
    
    
    
    if(countFull() == 4){
      if(c[0] == c[1] && c[2] == c[3]){
        join(0,1);
        join(2,3);
        move(2,1);
      } else if(c[0] == c[1]){
        join(0,1);
        move(2,1);
        move(3,2);
      } else if(c[1] == c[2]){
        join(1,2);
        move(3,2);  
      } else if(c[2] == c[3]){
        join(2,3);
      }
    } else if(countFull() == 3){
      if(c[0] == 0){
        if(c[1] == c[2]){
          join(1,2);
          move(1, 0);
          move(3,1);
        } else if(c[2] == c[3]){
          join(2,3);
          move(1, 0);
          move(2,1);
        } else{
          move(1, 0);
          move(2,1);
          move(3,2);
        }
      } else if(c[1] == 0){
        if(c[0] == c[2]){
          move(2,1);
          join(0,1);
          move(3,1);
        } else if(c[2] == c[3]){
          join(2,3);
          move(2,1);
        } else{
          move(2,1);
          move(3,2);
        }
      } else if(c[2] == 0){
        if(c[0] == c[1]){
          join(0,1);
          move(3,1);
        } else if(c[1] == c[3]){
          move(3,2);
          join(1,2);
        } else{
          move(3,2);
        }
      } else if(c[3] == 0){
        if(c[0] == c[1]){
          join(0,1);
          move(2,1);
        } else if(c[1] == c[2]){
          join(1,2);
        }
      }
      
    } else if(countFull() == 2){
      
      if(c[0] == 0 && c[1] == 0){
        if(c[2] == c[3]){
          join(2,3);
          move(2,0);
        } else{
          move(2,0);
          move(3,1);
        }
      } else if(c[0] == 0 && c[2] == 0){
        if(c[1] == c[3]){
          move(1, 0);
          move(3, 1);
          join(0,1);
        } else {
          move(1,0);
          move(3,1);
        }
      } else if(c[0] == 0 && c[3] == 0){    
        if(c[1] == c[2]){
          join(1, 2);
          move(1, 0);
          
        } else {
            move(1, 0);
            move(2,1);
          
        }
      } else if(c[1] == 0 && c[2] == 0){
        if(c[0] == c[3]){
          move(3, 1);
          join(0, 1);
        } else {
          move(3,1);
        }
      } else if(c[1] == 0 && c[3] == 0){
        if(c[0] == c[2]){
          move(2, 1);
          join(0, 1);
        } else {
          move(2,1);
        }
      } else if(c[2] == 0 && c[3] == 0){
        if(c[0] == c[1]){
          join(0, 1);
        } 
      }
    } else if(countFull() == 1){
      if(c[1] != 0){
        move(1, 0);
      } else if(c[2] != 0){
        move(2, 0);
      } else if(c[3] != 0){
        move(3,0);
      } 
      
    }
    
    
    
    
      out(n);
      
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
  }
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
 
  
  
  
  
  
  
  
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public void showCells(){
         GreenfootImage imgCells = new GreenfootImage(bgMainX, bgMainY);
        for(int a = 0; a < 4; a++){
            for(int b = 0; b < 4; b++){
                int value = 0;
                
                boolean c= false;
                int t = countTime -lastClicked;
                
                pX = b * 114 + 60 + xPlus[a][b];
                pY = a * 114 + 90 + yPlus[a][b];
                        
                
                
                if(t < 5 ){
                    if(moved){
                        value = past[(move+2)%3][a][b];
                    } else{
                        value = matrix[a][b];
                    
                    }
                   if(value != 0 && !MyWorld.getOptions()){ 
                    if(visual[a][b].equals("moved 1") || visual[a][b].equals("joined") ){
                        
                        if(cmd.equals("left")){
                            xPlus[a][b] +=  -14;
                        } else if(cmd.equals("right")){
                            xPlus[a][b] +=  14;
                        } else if(cmd.equals("up")){
                            yPlus[a][b] += - 14;
                        } else if(cmd.equals("down")){
                            yPlus[a][b] +=  14;
                        }
                        
                    
                    } else if(visual[a][b].equals("moved 2")){
                        
                        if(cmd.equals("left")){
                            xPlus[a][b] +=  -28;
                        } else if(cmd.equals("right")){
                            xPlus[a][b] +=  + 28;
                        } else if(cmd.equals("up")){
                            yPlus[a][b] += - 28;
                        } else if(cmd.equals("down")){
                            yPlus[a][b] += + 25;
                        }
                        
                    
                    } else if(visual[a][b].equals("moved 3")){
                        
                        if(cmd.equals("left")){
                            xPlus[a][b] -=  60;
                        } else if(cmd.equals("right")){
                            xPlus[a][b] += 60;
                        } else if(cmd.equals("up")){
                            yPlus[a][b] +=  - 60;
                        } else if(cmd.equals("down")){
                            yPlus[a][b] += 60;
                        }
                        
                    
                    } else {
                       
                        
                       if(visual[a][b].equals("random")){
                       if(t == 8){
                           x = 120;
                           y = 120;
                        }
                        if(x > 100){
                            x -= 4;
                            y -= 4;
                        }
                    }
                    }
                    
                }
                    
            
                    
                    
                    
                    
                    
                    
                    
                } else {
                    value = matrix[a][b];
                    xPlus[a][b] = 0;
                    yPlus[a][b] = 0;
                         pX = b * 114 + 60;
                         pY = a * 114 + 90;
                }
                /*
                 
                if( t > 5 ){
                    if(matrix[a][b] != 0){
                        value = matrix[a][b];
                    } else{
                        value = 0;
                    }
                    pX = X;
                    pY = Y;
                    
                    if(moved && matrix[a][b] != past[(move+2)%3][a][b] && matrix[a][b] != 0){
                        if(x < 120){
                            x =  100;
                            y =  100;
                        } else{
                            x = 114;
                            y = 1;
                        }
                    }
                    
                } else{
                    if(moved){
                        value = past[(move+2)%3][a][b];
                    } else{
                        value = matrix[a][b];
                    }
                       if(visual[a][b].equals("moved 1") && value != 0){ 
                            
                        if(cmd.equals("up")){
                            
                            pX = X;
                            pY = Y - t * 20;
                        } else if(cmd.equals("down")){
                            pX = X;
                            pY = Y + t * 20;
                        } else if (cmd.equals("left")){
                            pX = X - t * 20;
                            pY = Y;
                        } else if(cmd.equals("right")){
                            pX = X + t * 20;
                            pY = Y;
                        }
                    }
                    
                }
                
                * 
                 */
                String text = "";
               if(value != 0) {
                   text += value;
               }
               
               bgColor = new Color(255, 255, 255, 0);
               Color red = new Color(255, 100, 100, 0);
               Color fontColor = Color.WHITE; 
               GreenfootImage txtImg = new GreenfootImage(text, fontSize, fontColor, bgColor);
               getColor(value);
               int m = 20;
               GreenfootImage imgX = new GreenfootImage(80, 142);
               
               GreenfootImage imgY = new GreenfootImage(142, 80);
               imgX.setColor(bgColor);
               imgY.setColor(bgColor);
               imgX.fill();
               imgY.fill();
               GreenfootImage img = new GreenfootImage(x, y);
               
               img.drawImage(imgX, 10, 0);
               img.drawImage(imgY, 0, 10);
               
              
                
               GreenfootImage point = new  GreenfootImage(2*m, 2*m);
               point.setColor(bgColor);
               //point.setColor(new Color(255,255,255));
               point.drawOval(0, 0, m+3, m+3);
               point.fillOval(0, 0, m+3, m+3);
               img.drawImage(point, ((100-x)/2)+0, ((100-x)/2)+0);
               img.drawImage(point, x-m-3, ((102-x)/2)+0);
               img.drawImage(point, ((102-x)/2)+0, y-m-3);
               img.drawImage(point, x-m-4, y-m-4);
               
               img.drawImage(txtImg, getMarginLeft(text.length()) , getMarginTop(y));
               if(value != 0){
                   imgCells.drawImage(img, pX, pY);
                }
            }
        }
        
        
          
              setImage(imgCells);
          
    }
    
   
    public int getMarginLeft(int n){
        if(n == 1){
            return 40;
        } else if(n == 2){
            return 32;
        } else if(n == 3){
            return 24;
        } else if(n == 4){
            return 16;
        }else if(n == 5){
            return 9;
        }else {
            return 0;
        }
    }
    
    public int getMarginTop(int n){
        return (n - 34)/2;
    }
    public void getColor(int value){
        if(value == 2){
                bgColor = new Color(150,0,255);
            }  else  if(value == 4){
                bgColor = new Color(245, 20, 92);
            }else  if(value == 8){
                bgColor = new Color(254, 201, 26);
            } else  if(value == 16){
                bgColor = new Color( 0, 200, 26);
            } else  if(value == 32){
                bgColor = new Color( 0, 153, 206);
            } else  if(value == 64){
                bgColor = new Color( 203, 0, 131);
            } else  if(value == 128){
                bgColor = new Color(253, 86, 35);
            } else  if(value == 256){
                bgColor = new Color(38, 217, 162);
            } else  if(value == 512){
                bgColor = new Color(60, 19, 176);
            } else  if(value == 1024){
                bgColor = new Color(239, 0, 31);
            } else  if(value == 2048){
                bgColor = new Color(68, 7, 111);
            } else  if(value == 4096){
                bgColor = new Color(254, 0, 248);
            } else  if(value == 8192){
                bgColor = new Color(133, 1, 68);
            } else  if(value == 16384){
                bgColor = new Color(1, 170, 115);
            } else{
                bgColor = new Color(54, 54, 54);
            }
    }
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  // text file
  
  public void load(){
        
        
        String line = null;
        try {
            FileReader fileReader = 
                new FileReader(fileName);
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);
            for(int a = 0; a < 4; a++){
              for(int b = 0; b < 4; b++){
                if((line = bufferedReader.readLine()) != null) {
                  matrix[a][b] = Integer.parseInt(line);
                }   
              }
            
            }
            
           
            if((line = bufferedReader.readLine()) != null) {
                 countTime = Integer.parseInt(line);
            }   
            if((line = bufferedReader.readLine()) != null) {
                 move = Integer.parseInt(line);
            }   
            if((line = bufferedReader.readLine()) != null) {
                  score = Integer.parseInt(line);
            }   
            if((line = bufferedReader.readLine()) != null) {
                 title = Integer.parseInt(line);
            }   
           
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  

        }
     setRecord();
        getRecord();
  }
  
  public void save(){
      setRecord();
      try {
          PrintWriter writer = new PrintWriter(fileName);
          for(int a = 0; a < 4; a++){
              for(int b = 0; b < 4; b++){
                  writer.print(matrix[a][b] + "\n");
              }
          }
        
          writer.print(countTime + "\n");
          writer.print(move + "\n");
          writer.print(score + "\n");
          writer.print(title + "\n");
          
          writer.close();
  
        }
      catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.print(
                "Error reading file '" 
                + fileName + "'");                  

        }
        

  }
  
  
  public void reset(){
       for(int a = 0; a < 4; a++){
              for(int b = 0; b < 4; b++){
                 matrix[a][b] = 0;
              }
          }
          countTime = 0;
          move = 0;
          score = 0;
          title = 0;
          makeRandom();
        save();
  }
  
  
  public void pass(){
      for(int a = 0; a < 4; a++){
          for(int b = 0; b < 4; b++){
              past[move % 3][a][b] = matrix[a][b];
          }
      }
      
      pass[move%2][0] = move;
      pass[move%2][1] = score;
      
  }
  
  public void undo(){
      if(!undid){
           for(int a = 0; a < 4; a++){
              for(int b = 0; b < 4; b++){
                  matrix[a][b] = past[(move+2) % 3][a][b];
              }
          }
          
          move -= 1; 
          undid = true;
          score = score - scorePlus;
          MyWorld.setMoved(true);
      }
      
    }
    
    public void getRecord(){
        String fileName1 = "record.txt";
        String line = null;
        try {
            FileReader fileReader = 
                new FileReader(fileName1);
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);
             
           
            if((line = bufferedReader.readLine()) != null) {
                 hiNum = Integer.parseInt(line);
            }   
            if((line = bufferedReader.readLine()) != null) {
                 highScore = Integer.parseInt(line);
            }   
               
           
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName1 + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName1 + "'");                  

        }
     
    }
        public void setRecord(){
        String fileName1 = "record.txt";
        if(getTitle() > hiNum){
            hiNum = getTitle();
        }
        if(score > highScore){
            highScore = score;
        }
       try {
          PrintWriter writer = new PrintWriter(fileName1);
          
          writer.print(hiNum + "\n");
          writer.print(highScore + "\n");
          
          writer.close();
  
        }
      catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName1 + "'");                
        }
        catch(IOException ex) {
            System.out.print(
                "Error reading file '" 
                + fileName1  + "'");                  

        }
         getRecord();
    }

}
