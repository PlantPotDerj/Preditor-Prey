import javafx.scene.paint.Color; 
/**
 * Write a description of class Wolf here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Wolf extends Predator
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class Wolf
     */
    public Wolf(boolean randomAge, Field field,Location location)
    {
        super(randomAge, field, location, Color.GREY);
    }


}
