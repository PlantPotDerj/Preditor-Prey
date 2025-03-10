import javafx.scene.paint.Color; 
/**
 * Field item class
 * all field items like animals/plants are created here 
 * has access to the field same field instance here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class FieldItem
{
    // instance variables - replace the example below with your own
    private Field field = null;
    private Location location = null;
    private Color color = Color.PINK;
    private int foodValue = 5;

    /**
     * Constructor for objects of class FieldItem
     */
    public FieldItem(Field field, Location newLocation, Color col)
    {
        this.field = field;
        setLocation(newLocation);
        setColor(col);
    }
    
    /**
     * Place the fieldItem at the new location in the given field.
     * @param newLocation The animal's/plants new location.
     */
    public void setLocation(Location newLocation) {
        if(location != null) {
            field.clear(location);
        }
        location = newLocation;
        field.place(this, newLocation);
    }
    
    /**
     * Changes the color of the fielditem
     */
    public void setColor(Color col) {
        color = col;
    }
    
    /**
     * Returns the fieldItems color
     */
    public Color getColor() {
        return color;
    }   
    
    /**
     * Return the fieldItems's field.
     * @return The fieldItems's field.
     */
    public Field getField() {
        return field;
    }
    
     /**
     * Return the animal's location.
     * @return The animal's location.
     */
    public Location getLocation() {
        return location;
    }
    
    public void setFoodValue(int value){
        foodValue = value;
    }
    
    public int getFoodValue(){
        return foodValue;
    }
    
}
