import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
  Sophia 0.18 BETA
 **/
 
public class MyWorld extends World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    static boolean playMusic = false; 
    GreenfootSound bgMusic = new GreenfootSound("home.mp3");
    static boolean save = false;
    static int[][] value = new int[4][4];
    static int time = 0;
    static int move = 0;
    static int score = 0;
    static int bestScore = 0;
    static int title = 0;
    static int hiNum = 0;
    static String command = "";
    static String actions[][] = new String[4][4];
    static boolean moved = false;
    Blank blank = new Blank();
    static boolean reset = false;
    static boolean options = false;
    Cell status = new Cell();
    static int index = 1;
    static boolean close = false;
    static void setMoved(boolean b){
        moved = b;
    }
    static boolean getMoved(){
        return moved;
    }
    static void setActionForNow(){
     for(int a = 0; a < 4; a++){
            for(int b = 0; b < 4; b++){
                actions[a][b] = "appear";
            }
        }
    }
   
    static void setValue(int[][] matrix){
        
        for(int a = 0; a < 4; a++){
            for(int b = 0; b < 4; b++){
                value[a][b] = matrix[a][b];
            }
        }
    }
    static void setCommand(String c){
        command = c;
    }
    static String getCommand(){
        return command;
    }
    static int getValue(int a, int b){
        return value[a][b];
    }
    
    static void setTime(int clock){
        time = clock;
    }
    
    static int getTime(){
        return time;
    }
    static void setData(int moved){
        move = moved;
    }
    
    static int getMove(){
        return move;
    }
    static void setScore(int s){
        score = s;
    }
    static int getScore(){
        return score;
    }
    
    static void setBestScore(int n){
        bestScore = n;
    }
    
    static int getBestScore(){
        return bestScore;
    }
    
    static void setHiNum(int n){
        hiNum = n;
    }
    
     static int getHiNum(){
        return hiNum;
    }
    
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(970, 600, 1); 
        if(playMusic){
            bgMusic.playLoop();
        }
       GreenfootImage pic = new GreenfootImage(600, 400);
       pic.setColor(new Color(148, 61, 214));
        pic.fill();
        setBackground(pic);
        
        
        /*if(shouldAddObject){
            addObject(blank, 435, 300);
        } else if(shouldRemoveObject){
            removeObject(blank);
        } 
        */
        prepare();
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {

        Background bg = new Background();
        addObject(bg, 480, 292);
        
        

        
        addObject(status, 288, 310);

        
        Button button = new Button();
        addObject(button,550,70);
        
        addObject(blank, 0, 900);
        
        Selection c = new  Selection(1);
        Selection n = new Selection(2);
        Selection d = new Selection(3);
        Selection a = new Selection(4);
        Selection e = new Selection(5);
        
        addObject(c, 280, 100);
        addObject(n, 280, 200);
        addObject(d, 280, 300);
        addObject(a, 280, 400);
        addObject(e, 280, 500);
    }
    
    static void setReset(boolean b){
        reset = b;
    }
    
    static boolean getReset(){
        return reset;
       
    }
    static void setOptions(boolean b){
        options = b;
    }
    
    
    
    static boolean getOptions(){
        return options;
    }
    
    
    static void moveUp(){
        if(index > 1){
            index--;
        }
    }
    static void moveDown(){
        if(index < 5){
            index++;
        }
    }
    static int getIndex(){
        return index;
    }
    
    static void setSave(boolean b){
        save = b;
    }
    static boolean getSave(){
        return save;
    }
    
    static void setClose(boolean b){
        close = b;
    }
    static boolean getClose(){
        return close;
    }
    
    static void setMusic(boolean b){
        playMusic = b;
    }
}
