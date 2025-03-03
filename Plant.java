import javafx.scene.paint.Color; 
/**
 * Write a description of class Plant here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Plant extends FieldItem
{
    /**
     * Constructor for objects of class Plant
     */
    public Plant(Field field, Location location)
    {
        super(field, location, Color.GREEN);
        setFoodValue(1);
    }
    
}
