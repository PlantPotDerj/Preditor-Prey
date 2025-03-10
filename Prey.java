import java.util.List;
import java.util.Random;
import java.util.Iterator;
import javafx.scene.paint.Color; 

/**
 * A simple model of Prey.
 * prey act, and find food
 * 
 * @author David J. Barnes, Michael Kölling and Jeffery Raphael
 * @version 2025.02.10
 */

public class Prey extends Animal {
    //private static final Random rand = Randomizer.getRandom();
    private double foodLevel;
    private Field field;
    private int age;
    private static final double INITIAL_MAX_FOOD_VALUE = 10.0;

    /**
     * Create a new Prey. A prey may be created with age
     * zero (a new born) or with a random age.
     * 
     * @param randomAge If true, the prey will have a random age.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Prey(boolean randomAge, Field field, Location location, Color col, AnimalType animalType, Gene gene) {
        super(field, location, col, animalType, gene);
        this.field = field;
        //Gene gene = getGene();
        age = 0;
        setFoodValue(5);
        //setMaxAge(10);
        //setBreedingAge(3);
        foodLevel = rand.nextDouble(INITIAL_MAX_FOOD_VALUE);
        if(randomAge) {
            age = rand.nextInt(getMaxAge());
        }
    }
    
    /**
     * This is what the prey does most of the time - it runs 
     * around. Sometimes it will breed or die of old age.or eat food, spread disease
     * @param newRabbits A list to return newly born prey.
     */
    public void act(List<Animal> newPrey) {
        super.act(newPrey);
        incrementAge();
        incrementHunger();
        if(isAlive()) {
            giveBirth(newPrey);
            
            catchRandomDisease();
            spreadDisease();
            
            // Try to move into a free location.
            Location foodLocation = findFood();
            if (foodLocation != null){
                // found plant, move to it (Eat it)
                setLocation(foodLocation);
                
                int nutritionValue = field.getObjectAt(foodLocation).getFoodValue();
                decrementHunger(nutritionValue);
                //System.out.println("prey ate plant");
            }
            Location newLocation = getField().getFreeAdjacentLocation(getLocation());
            
            if(newLocation != null ) {
                setLocation(newLocation);
            }
            else {
                // Overcrowding.
                setDead();
                //System.out.println("OVERCROWDING DEATH PREY");
            }
        }
    }
    
    
    
    /**
     * Look for plants adjacent to the current location.
     * Only the first live plant is eaten.
     * locates plants 
     * @return Where food was found, or null if it wasn't.
     */
    private Location findFood() {
        Field field = getField();
        List<Location> adjacent = field.adjacentLocations(getLocation());
        Iterator<Location> it = adjacent.iterator();
        while(it.hasNext()) {
            Location where = it.next();
            FieldItem fieldItem = field.getObjectAt(where);
            if(fieldItem instanceof Plant) {
                return where;
            }
        }
        return null;
    }
}