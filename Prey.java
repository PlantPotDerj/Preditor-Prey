import java.util.List;
import java.util.Random;
import java.util.Iterator;
import javafx.scene.paint.Color; 

/**
 * A simple model of a rabbit.
 * Rabbits age, move, breed, and die.
 * 
 * @author David J. Barnes, Michael Kölling and Jeffery Raphael
 * @version 2025.02.10
 */

public class Prey extends Animal {
    private static final Random rand = Randomizer.getRandom();
    private int foodLevel;
    private Field field;
    private int age;

    /**
     * Create a new Prey. A prey may be created with age
     * zero (a new born) or with a random age.
     * 
     * @param randomAge If true, the prey will have a random age.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Prey(boolean randomAge, Field field, Location location, Color col) {
        super(field, location, col);
        this.field = field;
        age = 0;
        
        setFoodValue(5);
        setMaxAge(10);
        setBreedingAge(3);
        //this is a default it gets overwritten
        
        foodLevel = 10;
        if(randomAge) {
            age = rand.nextInt(getMaxAge());
        }
    }
    
    /**
     * This is what the prey does most of the time - it runs 
     * around. Sometimes it will breed or die of old age.or eat food 
     * @param newRabbits A list to return newly born prey.
     */
    public void act(List<Animal> newPrey) {
        incrementAge();
        if(isAlive()) {
            //
            //giveBirth(newPrey);            
            // Try to move into a free location.
            Location foodLocation = findFood();
            if (foodLocation != null){
                 int nutritionValue = field.getObjectAt(foodLocation).getFoodValue();
                 setLocation(foodLocation);
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
                System.out.println("OVERCROWDING DEATH PREY");
            }
        }
    }
    
    /**
     * Check whether or not this prey is to give birth at this step.
     * New births will be made into free adjacent locations.
     * @param newPreys A list to return newly born prey.
     */
    private void giveBirth(List<Animal> newPreys) {
        // New rabbits are born into adjacent locations.
        // Get a list of adjacent free locations.
        Field field = getField();
        List<Location> free = field.getFreeAdjacentLocations(getLocation());
        int births = breed();
        for(int b = 0; b < births && free.size() > 0; b++) {
            Location loc = free.remove(0);
            Prey young = new Prey(false, field, loc, getColor());
            newPreys.add(young);
        }
    }
        
    /**
     * Generate a number representing the number of births,
     * if it can breed.
     * @return The number of births (may be zero).
     */
    private int breed() {
        int births = 0;
        if(canBreed() && rand.nextDouble() <= getBreedingProbability()) {
            births = rand.nextInt(getMaxLitterSize()) + 1;
        }
        return births;
    }

    /**
     * A prey can breed if it has reached the breeding age.
     * @return true if the prey can breed, false otherwise.
     */
    private boolean canBreed() {
        return age >= getBreedingAge();
    }
    
    /**
     * Look for Preys adjacent to the current location.
     * Only the first live Prey is eaten.
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