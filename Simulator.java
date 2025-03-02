import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import javafx.scene.paint.Color; 

/**
 * A simple predator-prey simulator, based on a rectangular field
 * containing rabbits and foxes.
 * 
 * @author David J. Barnes, Michael Kölling and Jeffery Raphael
 * @version 2025.02.10
 */

public class Simulator {

    private static final double PREDATOR_CREATION_PROBABILITY = 0.02;
    private static final double PREY_CREATION_PROBABILITY = 0.08; 
    

    private List<Animal> animals;
    private Field field;
    private int step;
    
    /**
     * Create a simulation field with the given size.
     * @param depth Depth of the field. Must be greater than zero.
     * @param width Width of the field. Must be greater than zero.
     */
    public Simulator(int depth, int width) {
        
        animals = new ArrayList<>();
        field = new Field(depth, width);

        reset();
    }
    
    /**
     * Run the simulation from its current state for a single step.
     * Iterate over the whole field updating the state of each
     * fox and rabbit.
     */
    public void simulateOneStep() {
        step++;
        List<Animal> newAnimals = new ArrayList<>();        

        for(Iterator<Animal> it = animals.iterator(); it.hasNext(); ) {
            Animal animal = it.next();
            animal.act(newAnimals);
            if(! animal.isAlive()) {
                it.remove();
            }
        }
               
        animals.addAll(newAnimals);
    }
        
    /**
     * Reset the simulation to a starting position.
     */
    public void reset() {
        step = 0;
        animals.clear();
        populate();
    }
    
    /**
     * Randomly populate the field with foxes and rabbits.
     */
    private void populate() {
        
        Random rand = Randomizer.getRandom();
        Random random = new Random();
        
        
        field.clear();
        
        for(int row = 0; row < field.getDepth(); row++) {
            for(int col = 0; col < field.getWidth(); col++) {
                // predator placing
                if(rand.nextDouble() <= PREDATOR_CREATION_PROBABILITY) {
                    int pred_random = random.nextInt(2);
                    if (pred_random == 0) {
                        Location location = new Location(row, col);
                        Tiger tiger = new Tiger(true, field, location);
                        animals.add(tiger);
                    }
                    else{
                        Location location = new Location(row, col);
                        Wolf wolf = new Wolf(true, field, location);
                        animals.add(wolf);
                    }
                }
                //Prey placing
                else if(rand.nextDouble() <= PREY_CREATION_PROBABILITY) {
                    int prey_random = random.nextInt(3);
                    Location location = new Location(row, col);
                    switch (prey_random){
                        case 0:
                            Zebra zebra = new Zebra(true, field, location);
                            animals.add(zebra);
                            break;
                        case 1:
                            Deer deer = new Deer(true, field, location);
                            animals.add(deer);
                            break;
                        default:
                            Mouse mouse = new Mouse(true, field, location);
                            animals.add(mouse);
                    }
                }
                
                // else leave the location empty.
            }
        }
    }
    
    /**
     * Pause for a given time.
     * @param millisec  The time to pause for, in milliseconds
     */
    public void delay(int millisec) {
        try {
            Thread.sleep(millisec);
        }
        catch (InterruptedException ie) {
            // wake up
        }
    }
    

    public Field getField() {
        return field;
    }

    public int getStep() {
        return step;
    }
}