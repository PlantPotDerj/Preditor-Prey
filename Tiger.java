import javafx.scene.paint.Color; 
/**
 * Write a description of class Tiger here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Tiger extends Predator
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class Tiger
     */
    public Tiger(boolean randomAge, Field field, Location location)
    {
        super(randomAge, field,location, Color.ORANGE);
    }


}
