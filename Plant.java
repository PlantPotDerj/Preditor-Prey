import javafx.scene.paint.Color; 
/**
 * plant class fieldItem item, has a food nutrition value of 1.
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
