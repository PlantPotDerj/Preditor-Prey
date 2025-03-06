import java.util.List;
import java.util.Iterator;
import java.util.Random;
import java.util.LinkedList;
import javafx.scene.paint.Color; 

/**
 * A simple model of a Predator.
 * Predatores age, move, eat rabbits, and die.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2025.02.10
 */

public class Predator extends Animal {
    private static final double INITIAL_MAX_FOOD_VALUE = 9.0;
    //private static final Random rand = Randomizer.getRandom();
    
    private int age;
    private double foodLevel;
    private Field field;
    
    /**
     * Create a Predator. A Predator can be created as a new born (age zero
     * and not hungry) or with a random age and food level.
     * 
     * @param randomAge If true, the Predator will have random age and hunger level.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Predator(boolean randomAge, Field field, Location location, Color col, AnimalType animalType, Gene gene) {
        super(field, location, col, animalType, gene);
        this.field = field;
        
        setFoodValue(0);
        //setMaxAge(20);
        //setBreedingAge(8);
        //thease are defaults they get overwritten
        
        if(randomAge) {
            age = rand.nextInt(getMaxAge());
            foodLevel = rand.nextDouble(INITIAL_MAX_FOOD_VALUE);
        }
        else {
            age = 0;
            foodLevel = INITIAL_MAX_FOOD_VALUE;
        }
    }
    
    /**
     * This is what the Predator does most of the time: it hunts for
     * Preys. In the process, it might breed, die of hunger,
     * or die of old age.
     * @param field The field currently occupied.
     * @param newPredatores A list to return newly born Predatores.
     */
    public void act(List<Animal> newPredators) {
        incrementAge();
        incrementHunger();// might change for metabolism
        if(isAlive()) {
            giveBirth(newPredators);
            catchRandomDisease();
            //spreadDisease();
            
            Location newLocation = findFood();
            if(newLocation == null) { 
                // No food found - try to move to a free location.
                newLocation = getField().getFreeAdjacentLocation(getLocation());
            }
            if(newLocation != null) {
                // Found prey, take location of prey (kill it)
                setLocation(newLocation);
            }
            else {
                // Overcrowding.
                setDead();
                //System.out.println("OVERCROWDING DEATH PRED");
            }
        }
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
            FieldItem animal = field.getObjectAt(where);
            if(animal instanceof Prey) {
                Prey prey = (Prey) animal;
                if(prey.isAlive()) { 
                    //System.out.println("PRED KILLED PREY");
                    prey.setDead();
                    decrementHunger(prey.getFoodValue());
                    
                    //foodLevel = PREY_FOOD_VALUE;
                    return where;
                }
            }
        }
        return null;
    }
    
}