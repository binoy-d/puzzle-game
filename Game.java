import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Game extends JPanel implements KeyListener{
  int level = 0;
  static int squareSize = 20;
  static int offset = 20;
  int speed = 1;
  int[][] currentMap = new int[1][1];
  ArrayList<Point> players = new ArrayList<Point>();
  ArrayList<Point> enemies = new ArrayList<Point>();
  /*
   * LEVEL DESIGN CODE:
   * 
   * 0 = empty
   * 1 = wall
   * 2 = finish
   * 3 = lava
   * 4 = 
   * 5 =
   * 6 = 
   * 7 =
   * 8 = enemy
   * 9 =
   * 
   * */
  int playersDone = 0;
  int[][][] levels = {
    {
      //level 0
      {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
      {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
      {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
      {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
      {1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1,1,1,1,1},
      {1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,7,0,1,1,1,1,1,1,1,1},
      {1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1,1,1,1,1},
      {1,1,1,1,1,1,1,7,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1},
      {1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1},
      {1,1,1,1,1,1,1,0,1,1,0,0,0,0,0,0,0,0,3,1,1,1,1,1,1},
      {1,1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
      {1,1,1,1,1,1,1,0,0,0,0,0,0,1,1,1,1,0,1,2,1,1,1,1,1},
      {1,1,1,1,1,1,1,1,1,1,0,1,3,1,1,0,0,0,1,0,1,1,1,1,1},
      {1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,1,0,1,0,1,1,1,1,1},
      {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,8,0,0,1,1,1,1,1},
      {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
    },
    {
      //level 1
      {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
      {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1},
      {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1},
      {1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,8,0,3,1,1,1,1,1,1,1},
      {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1},
      {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1},
      {1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,1,1,1,1,1,1,1,1,1},
      {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,7,1,1,1,1,1,1,1,1,1},
      {1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1},
      {1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
      {1,1,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
      {1,1,0,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
      {1,1,0,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
      {1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
      {1,1,7,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
      {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
    },
    {
      //level 2
      {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
      {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
      {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
      {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
      {1,1,1,1,7,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
      {1,1,1,1,0,1,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1},
      {1,1,1,1,0,1,0,1,1,1,1,7,1,1,0,0,2,0,1,1,1,1,1,1,1},
      {1,1,1,1,0,1,0,0,0,0,0,0,0,0,0,1,1,0,1,1,1,1,1,1,1},
      {1,1,1,1,0,1,0,1,1,1,1,1,0,1,0,1,1,0,1,1,1,1,1,1,1},
      {1,1,1,1,0,0,0,1,1,1,1,1,0,0,8,0,0,0,1,1,1,1,1,1,1},
      {1,1,1,1,0,1,0,1,1,1,1,1,0,1,1,1,0,1,1,1,1,1,1,1,1},
      {1,1,1,1,0,1,0,1,1,1,1,3,0,0,0,0,0,1,1,1,1,1,1,1,1},
      {1,1,1,1,0,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
      {1,1,1,1,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
      {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
      {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
    },
  };
  
  
  
  public void keyPressed(KeyEvent e) {
    int key = e.getKeyCode();
    if(key == KeyEvent.VK_D){
      changeAll(speed,0);
      return;
    }
    if(key == KeyEvent.VK_A){
      changeAll(-speed,0);
      return;
    }
    if(key == KeyEvent.VK_S){
      changeAll(0,speed);
      return;
    }
    if(key == KeyEvent.VK_W){
      changeAll(0,-speed);
      return;
    }
  }
  
  public void keyReleased(KeyEvent e) {}
  public void keyTyped(KeyEvent e) {}
  
  public Game(){
    addKeyListener(this);
    setFocusable(true);
    setFocusTraversalKeysEnabled(false);
  }
  
  public void initialize(){
    currentMap = levels[level];
    players = new ArrayList<Point>();
    enemies = new ArrayList<Point>();
    for(int i = 0;i<currentMap.length;i++){
      for(int j = 0;j<currentMap[0].length;j++){
        if(currentMap[i][j] == 7){
          players.add(new Point(j,i)) ;
        }
        if(currentMap[i][j] == 8){
          enemies.add(new Point(j,i)) ;
        }
      }
    }
  }
  
  
  
  public void paint(Graphics g)
  {
    super.paint(g);
    Graphics2D g2d = (Graphics2D) g;
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                         RenderingHints.VALUE_ANTIALIAS_ON);
    setBackground(Color.black);
    g2d.setPaint(Color.white);
    g2d.drawString("LEVEL: " +level , 10, 10);
    for(int y = 0; y < currentMap.length; y++)
    {
      for(int x = 0; x < currentMap[0].length; x++)
      {
        int val = currentMap[y][x];
        switch (val){
          case (1):
            g2d.setPaint(Color.gray);break;
          case (2):
            g2d.setPaint(Color.green);break;
          case (3):
            g2d.setPaint(Color.orange);break;
          default:
            g2d.setPaint(Color.black);break;
        }
        g2d.fillRect(offset+x*squareSize, offset+y*squareSize, squareSize, squareSize);
      }
    }
    for(int i = 0; i < players.size(); i++){
      Point p = players.get(i);
      g2d.setPaint(Color.white);
      g2d.fillRect(offset+p.x*squareSize, offset+p.y*squareSize, squareSize, squareSize);
    }
    for(int i = 0; i < enemies.size(); i++){
      Point p = enemies.get(i);
      g2d.setPaint(Color.red);
      g2d.fillRect(offset+p.x*squareSize, offset+p.y*squareSize, squareSize, squareSize);
    }
  }
  public void enemyTick(){
    for(Point e: enemies){
      int x = (int)(Math.random()*4-2);
      int y = (int)(Math.random()*4-2);
      
      int val = currentMap[e.y+y][e.x+x];
      if(val == 0 || val == 7){
        e.x += x;
        e.y += y;
      }
    }
  }
  
  private void tick() throws InterruptedException {
    repaint();
    enemyTick();
    Thread.sleep(200);
  }  
  private void changeAll(int x, int y){
    for(int i = 0; i < players.size(); i++){
      Point p = players.get(i);
      
      try{
        int val = currentMap[p.y+y][p.x+x];
        if(val == 0 || val == 7){
          p.x += x;
          p.y += y;
        }
        else if(val == 2){
          
          players = new ArrayList<Point>();
          level++;
          initialize();
        }
        
        else if(val == 3 || hitEnemy(p)){
          players = new ArrayList<Point>();
          initialize();
        }
        
      }
      catch(ArrayIndexOutOfBoundsException e){
        players.remove(p);
      }
    }
  }
  public boolean hitEnemy(Point p){
    for(Point e: enemies){
      if(p.x == e.x && e.y == p.y){
       return true; 
      }
    }
    return false;
  }
  public static void main(String[] args) throws Exception {
    JFrame frame = new JFrame("boi");
    Game game = new Game();
    
    game.initialize();
    frame.add(game);
    frame.setSize(squareSize*27+offset, 400);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    while (true)
    {
      game.tick();
    }
  }
  
}