//Basic Game Application
// Basic Object, Image, Movement
// Threaded

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

//*******************************************************************************

public class Main implements Runnable {

    //Variable Definition Section
    //Declare the variables used in the program
    //You can set their initial values too

    //Sets the width and height of the program window
    final int WIDTH = 1000;
    final int HEIGHT = 700;

    //Declare the variables needed for the graphics
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;

    public BufferStrategy bufferStrategy;

    // Object-Related variables
    // **** Step 1 : Declare Astronaut and its image
    public Chocolate oliver;
    public Image oliverPic;
    public Chocolate Jr;
    public Image JrPic;
    public Chocolate Jp;
    public Image JpPic;

    public Image backgroundpic;



    // Main method definition
    // This is the code that runs first and automatically
    public static void main(String[] args) {
        Main ex = new Main();   //creates a new instance of the game
        new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method
    }


    // This section is the setup portion of the program
    // Initialize your variables and construct your program objects here.
    public Main() { // BasicGameApp constructor

        setUpGraphics();

        //variable and objects
        //create (construct) the objects needed for the game
        //STEP 2: Construct Astronaut and its  image
        oliver = new Chocolate( "Oliver", 200, 300);
        oliverPic = Toolkit.getDefaultToolkit().getImage("white.png");
        Jr = new Chocolate( "Jr", 300, 200);
        JrPic = Toolkit.getDefaultToolkit().getImage("brown.png");
        Jp = new Chocolate( "Jp", 500, 200);
        JpPic = Toolkit.getDefaultToolkit().getImage("wonka.png");

        backgroundpic = Toolkit.getDefaultToolkit().getImage("Factory.png");

    } // end BasicGameApp constructor


//*******************************************************************************
//User Method Section
//
// put your code to do things here.

    // main thread
    // this is the code that plays the game after you set things up
    public void run() {
        //for the moment we will loop things forever.
        while (true) {
            moveThings();  //move all the game objects
            collisions();
            render();  // paint the graphics
            pause(10); // sleep for 10 ms
        }
    }


    public void moveThings() {
        //call the move() code for each object
        oliver.move();
        Jr.wrap();
        Jp.move();

    }
    public void collisions() {
        // check whether oliver and Jr are colliding
        // if they colide, reverse their directions.
        if(oliver.rec.intersects(Jr.rec) && oliver.oliverAndJr == false) {
            oliver.dx = -oliver.dx;
            oliver.dy = -oliver.dy;
            Jr.dx = -Jr.dx;
            Jr.dy = -Jr.dy;
            oliver.oliverAndJr = true;
        }
        if (!oliver.rec.intersects(Jr.rec)) {
            oliver.oliverAndJr = false;
        }
        if(oliver.rec.intersects(Jp.rec) && oliver.oliverAndJp == false) {
            oliver.dx = -oliver.dx;
            oliver.dy = -oliver.dy;
            Jp.dx = -Jp.dx;
            Jp.dy = -Jp.dy;
            Jp.dx = Jp.dx + 2 ;
            oliver.oliverAndJp = true;
        }
        if (!oliver.rec.intersects(Jp.rec)) {
            oliver.oliverAndJp = false;
        }
        if(Jr.rec.intersects(Jp.rec) && Jr.JrAndJp == false) {
            Jr.dx = -Jr.dx;
            Jr.dy = -Jr.dy;
            Jp.dx = -Jp.dx;
            Jp.dy = -Jp.dy;
            Jp.dx = Jp.dx - 1;
            Jr.JrAndJp = true;
        }
        if (!Jr.rec.intersects(Jp.rec)) {
            Jr.JrAndJp = false;
        }



    }

    //Paints things on the screen using bufferStrategy
    private void render() {

        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);
        g.drawImage(backgroundpic, 0, 0, WIDTH, HEIGHT, null);
        //draw the images
        // g.drawImage(oliverPic, 500, 0, 300, 300, null);
        g.drawImage(oliverPic, oliver.xpos, oliver.ypos, oliver.width, oliver.height, null);
        g.drawImage(JrPic, Jr.xpos, Jr.ypos, Jr.width, Jr.height, null);
        g.drawImage(JpPic, Jp.xpos, Jp.ypos, Jp.width, Jp.height, null);


        //  g.drawRect(100,200,100,100);
        g.setColor(Color.RED);
        //    g.drawRect(oliver.xpos, oliver.ypos, oliver.width, oliver.height);
        g.setColor(Color.YELLOW);
        g.drawRect(oliver.rec.x, oliver.rec.y, oliver.rec.width, oliver.rec.height);
        g.drawImage(JrPic, Jr.xpos, Jr.ypos, Jr.width, Jr.height, null);
        g.setColor(Color.ORANGE);
        g.drawRect(Jr.rec.x, Jr.rec.y, Jr.rec.width, Jr.rec.height);
        g.drawRect(Jp.rec.x, Jp.rec.y, Jp.rec.width, Jp.rec.height);



        g.dispose();
        bufferStrategy.show();
    }

    //Pauses or sleeps the computer for the amount specified in milliseconds
    public void pause(int time ) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
        }
    }

    //Graphics setup method
    private void setUpGraphics() {
        frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.

        panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
        panel.setLayout(null);   //set the layout

        // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
        // and trap input events (Mouse and Keyboard events)
        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);

        panel.add(canvas);  // adds the canvas to the panel.

        // frame operations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
        frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
        frame.setResizable(false);   //makes it so the frame cannot be resized
        frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

        // sets up things so the screen displays images nicely.
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        System.out.println("DONE graphic setup");
    }

}
