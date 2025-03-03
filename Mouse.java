import javafx.scene.paint.Color; 
/**
 * Write a description of class Mouse here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Mouse extends Prey
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class Mouse
     */
    public Mouse(boolean randomAge, Field field, Location location)
    {
        super(randomAge, field, location, Color.RED);
    }


}
