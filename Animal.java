import java.util.List;
import javafx.scene.paint.Color; 

/**
 * A class representing shared characteristics of animals.
 * 
 * @author David J. Barnes, Michael Kölling and Jeffery Raphael
 * @version 2025.02.10
 */

public abstract class Animal extends FieldItem {
    
    private boolean alive;
    private Color color = Color.BLUE;
    private int age = 0;
    private int foodLevel = 10;
    private int maxAge = 20;
    private int breedingAge = 10;
    private double breedingProbabiltiy = 0.05;
    private int maxLitterSize = 2;
       
    //how nutritional the animal is default for predator is 0 
    
    /**
     * Create a new animal at location in field.
     * 
     * @param field The field currently occupied.
     * @param location The location within the field.
     * @param col the color of the animal 
     */
    
    public Animal(Field field, Location location, Color col){
        super(field, location, col);
        alive = true;
        setLocation(location);
    }
    
    /**
     * Make this animal act - that is: make it do
     * whatever it wants/needs to do.
     * @param newAnimals A list to receive newly born animals.
     */
    abstract public void act(List<Animal> newAnimals);

    /**
     * Check whether the animal is alive or not.
     * @return true if the animal is still alive.
     */
    protected boolean isAlive() {
        return alive;
    }

    /**
     * Indicate that the animal is no longer alive.
     * It is removed from the field.
     */
    protected void setDead() {
        alive = false;
        Location location = getLocation();
        Field field = getField();
        Location lastLocationBeforeDeath = null;
        Field lastFieldBeforeDeath = null;
        if(location != null) {
            lastLocationBeforeDeath = location;
            lastFieldBeforeDeath = field;
            field.clear(location);
            location = null;
            field = null;
        }
        Plant plant = new Plant(lastFieldBeforeDeath, lastLocationBeforeDeath);
    }
    
    /**
     * Make this animal more hungry. This could result in the animals's death
     * .
     */
    public void incrementHunger(int foodValue) {
        foodLevel -= foodValue;
        if(foodLevel <= 0) {
            System.out.println("ANIMAL DEATH FROM HUNGER");
            setDead();
        }
    }
    
    /**
     * Make this Animal less hungry. 
     * @param foodValue increment based on what fieldItem was eaten 
     */
    public void decrementHunger(int foodValue) {
        foodLevel += foodValue;
        //System.out.println("ANIMAL ate food");
    }
    
    /**
     * Increase the age.
     * This could result in the animals
     */
    public void incrementAge() {
        age++;
        if(age > maxAge) {
            //System.out.println("animal age was " + age + "max age was" + maxAge);
            setDead();
            System.out.println("DEATH OF AGE");
        }
    }
    
    public void setMaxAge(int value){
        maxAge = value;
    }
    
    public int getMaxAge(){
        return maxAge;
    }
    
    public void setBreedingAge(int value){
        breedingAge = value;    
    }
    
    public int getBreedingAge(){
        return breedingAge;
    }
    
    public void setBreedingProbability(double value){
        breedingProbabiltiy = value;    
    }    
    
    public double getBreedingProbability(){
        return breedingProbabiltiy;
    }
    
    public void setMaxLitterSize(int value){
        maxLitterSize = value;    
    }    
    
    public int getMaxLitterSize(){
        return maxLitterSize; 
    }
    
    public void setAge(int value){
        age = value;
    }
    
}