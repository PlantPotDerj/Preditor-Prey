import javafx.scene.paint.Color; 
/**
 * wolf class, sets maxAge, BreedingAge, Breeding porbabilit, Max Litter Size,
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Wolf extends Predator
{
        /**
     * Constructor for objects of class Wolf
     */
    public Wolf(boolean randomAge, Field field,Location location)
    {
        super(randomAge, field, location, Color.GREY, AnimalType.Wolf);
        setMaxAge(18);
        setBreedingAge(13);
        setBreedingProbability(0.15);
        setMaxLitterSize(4);
    }
}
