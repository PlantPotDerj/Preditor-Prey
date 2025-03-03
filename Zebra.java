import javafx.scene.paint.Color; 
/**
 * Zebra class, sets maxAge, BreedingAge, Breeding porbabilit, Max Litter Size, food value
 * @author (your name)
 * @version (a version number or a date)
 */
public class Zebra extends Prey
{
       /**
     * Constructor for objects of class Zebra
     */
    public Zebra(boolean randomAge, Field field, Location location)
    {
        super(randomAge, field, location, Color.BLUE, AnimalType.Zebra);
        setFoodValue(3);
        //setAge(10);
        setMaxAge(11);
        setBreedingAge(4);
        setBreedingProbability(0.10);
        setMaxLitterSize(2);
    }
}
