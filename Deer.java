import javafx.scene.paint.Color; 
/**
 * Deer class, sets maxAge, BreedingAge, Breeding porbabilit, Max Litter Size, food value
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Deer extends Prey
{
        /**
     * Constructor for objects of class Deer
     */
    public Deer(boolean randomAge, Field field, Location location)
    {
        super(randomAge,field, location, Color.PURPLE);
        setFoodValue(2);
        setMaxAge(12);
        setBreedingAge(8);
        setBreedingProbability(0.12);
        setMaxLitterSize(2);
    }


}
