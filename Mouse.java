import javafx.scene.paint.Color; 
/**
 * Mouse class, sets maxAge, BreedingAge, Breeding porbabilit, Max Litter Size,
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Mouse extends Prey
{
    
    /**
     * Constructor for objects of class Mouse
     */
    public Mouse(boolean randomAge, Field field, Location location, Gene gene)
    {
        super(randomAge, field, location, Color.RED, AnimalType.Mouse, gene);
        setFoodValue(1);
        //setMaxAge(10);
        //setBreedingAge(5);
        //setBreedingProbability(0.11);
        //setMaxLitterSize(5);
    }
}
