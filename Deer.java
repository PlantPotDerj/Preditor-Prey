import javafx.scene.paint.Color; 
/**
 * Write a description of class Deer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Deer extends Prey
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class Deer
     */
    public Deer(boolean randomAge, Field field, Location location)
    {
        super(randomAge, field,location, Color.PURPLE);
    }


}
