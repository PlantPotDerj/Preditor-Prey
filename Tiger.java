import javafx.scene.paint.Color; 
/**
 * Tiger class, sets maxAge, BreedingAge, Breeding porbabilit, Max Litter Size,
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Tiger extends Predator
{
    /**
     * Constructor for objects of class Tiger
     */
    public Tiger(boolean randomAge, Field field, Location location)
    {
        super(randomAge, field, location, Color.ORANGE, AnimalType.Tiger);
        setMaxAge(20);
        setBreedingAge(6);
        setBreedingProbability(0.1);
        setMaxLitterSize(1);
    }
    

}
