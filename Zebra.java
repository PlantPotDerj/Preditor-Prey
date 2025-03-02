import javafx.scene.paint.Color; 
/**
 * Write a description of class Zebra here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Zebra extends Prey
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class Zebra
     */
    public Zebra(boolean randomAge, Field field, Location location)
    {
        super(randomAge, field,location, Color.BLACK);
    }


}
