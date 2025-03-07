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
    public Zebra(boolean randomAge, Field field, Location location, Gene gene)
    {
        super(randomAge, field, location, Color.BLUE, AnimalType.Zebra, gene);
        setFoodValue(3);
        //setAge(10);//
        
    
        //setMale();
        //setBreedingAge(4);//
        //setBreedingProbability(0.10);
        //setMaxLitterSize(2);
        //System.out.println("age is;" + getAge());
    }
}
