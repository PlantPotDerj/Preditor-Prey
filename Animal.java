import java.util.List;
import javafx.scene.paint.Color; 
import java.util.Random;
import java.util.Iterator;

/**
 * A class representing shared characteristics of animals.
 * 
 * @author David J. Barnes, Michael Kölling and Jeffery Raphael
 * @version 2025.02.10
 */

public abstract class Animal extends FieldItem {
    private String animalToMateWithGene = null;
    private boolean alive = true; // By default an animal is alive
    private Color color = Color.BLUE;
    private int age = 0;
    private int stepsBeforeDiseasedDeath = 10;
    private int stepsCounter = 0;
    private double foodLevel = 10.0;
    private int maxAge = 20;
    private int breedingAge = 10;
    private double breedingProbabiltiy = 0.05;
    private double metabolism = 0.05;
    private double diseasedProbabiltiy = 0.02;//changed from 0.22 to 0
    private boolean diseased = false;
    private int maxLitterSize = 2;
    private AnimalType animalType = null;
    private Gender animalGender = null;
    private Gene gene = null;
    public enum  AnimalType {Tiger, Deer, Mouse, Wolf, Zebra};
    public enum Gender{Male, Female};
    public static final Random rand = Randomizer.getRandom();
       
    //how nutritional the animal is default for predator is 0 
    //help
    /**
     * Create a new animal at location in field.
     * 
     * @param field The field currently occupied.
     * @param location The location within the field.
     * @param col the color of the animal 
     * @param animalType the type of the animal set by enum 
     * @param gene the gene of the animal
     */
    
    public Animal(Field field, Location location, Color col, AnimalType animalType, Gene gene){
        super(field, location, col);
        
        this.animalType = animalType;
        this.gene = gene;
        setLocation(location);
        setGender();
        
        /** debugging
        breedingAge = 1;
        maxAge = 60;
        breedingProbabiltiy = 0.49;
        maxLitterSize = 8;
        diseasedProbabiltiy = 0.01;
        metabolism = 0.26;
        */
    }
    
    /**
     * Make this animal act - that is: make it do
     * whatever it wants/needs to do.
     * @param newAnimals A list to receive newly born animals.
     * alwasy check if deases and start the counter
     * if counter greater then steps before death set dead
     */
    public void act(List<Animal> newAnimals){
        if (diseased){
            if (stepsCounter < stepsBeforeDiseasedDeath){
                stepsCounter ++;
                //System.out.println(stepsCounter);
            }else{
                stepsCounter = 0;
                setDead();
                //System.out.println("ANIMAL DEATH FROM DISEASE");
            }
        }   
    }

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
     * and plant is placed in the location
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
            //System.out.println("ANIMAL DEAD");
        }
        Plant plant = new Plant(lastFieldBeforeDeath, lastLocationBeforeDeath);
    }
    
    Animal currentBreedingMate;
    /**
     * A prey can breed if it has reached the breeding age 
     * and  has a animal of same type and diff gender adjacent to it.
     * @return true if the prey can breed, false otherwise.
     */
    private boolean canBreed() {
        if (age < gene.getBreedingAge()){
            return false;
        }

        List<Location> adjacentLocations = getField().adjacentLocations(getLocation());
        if(adjacentLocations.isEmpty()){
            return false;
        }
        
        for (Location loc : adjacentLocations){
            FieldItem fieldItemAtLocation = getField().getObjectAt(loc);
            if(fieldItemAtLocation == null){
                continue;
            }
            
            boolean isAnimal = fieldItemAtLocation instanceof Animal;
            if(!isAnimal){
                continue;
            }
            
            currentBreedingMate = (Animal) fieldItemAtLocation;
            if (animalType != currentBreedingMate.getAnimalType()){
                continue;
            }
            
            // Make sure they're not the same gender
            if (animalGender == currentBreedingMate.getGender())
            {
                continue;
            }
            
            // Check for probability
            if (rand.nextDouble() > gene.getBreedingProbability())
            {
                continue;
            }
            
            //System.out.println("ANIMAL IS BREEDING");
            return true;
        }
        return false;
    }
    
    /**
     * animal gives birth by calling canBreed which checks for conditions
     * breeds animal of dame type with genes taken from itself and neigbouring animal
     */
    public void giveBirth(List<Animal> newAnimals) {
        // New animals are born into adjacent locations.
        // Get a list of adjacent free locations.
        Field field = getField();
        List<Location> free = field.getFreeAdjacentLocations(getLocation());
 
        if(canBreed()) {
           int births = amountToBirth();
            for(int b = 0; b < births && free.size() > 0; b++) {
                Location loc = free.remove(0);
                
                // Create a new gene that is a mix of both.
                String GeneSelf = gene.aGeneToString(gene);
                String GeneMate = currentBreedingMate.gene.aGeneToString(currentBreedingMate.gene);
                
                Gene childGene = new Gene(GeneSelf, GeneMate);
                
                switch (animalType){
                    case (AnimalType.Tiger):
                        Tiger tiger = new Tiger(true,field, loc, childGene);
                        newAnimals.add(tiger);
                        break;
                    case (AnimalType.Wolf):
                        Wolf wolf = new Wolf(true,field, loc, childGene);
                        newAnimals.add(wolf);
                        break;
                    case (AnimalType.Zebra):
                        Zebra zebra = new Zebra(true,field, loc, childGene);
                        newAnimals.add(zebra);
                        break;
                    case (AnimalType.Deer):
                        Deer deer = new Deer(true,field, loc, childGene);
                        newAnimals.add(deer);
                        break;
                    default:
                        Mouse mouse = new Mouse(true,field, loc, childGene);
                        newAnimals.add(mouse);
                    }
                } 
            }
    }
    
    /**
     * Generate a number representing the number of births,
     * if it can breed.
     * @return The number of births (may be zero).
     */
    private int amountToBirth() {
        int births = 0;
        if(rand.nextDouble() <= gene.getBreedingProbability()) {
            births = rand.nextInt(gene.getLitterSize()) + 1;
        }
        return births;
    }
    
    /**
     * Make this animal more hungry. This could result in the animals's death
     * decerements by metabolism 
     */
    public void incrementHunger() {
        foodLevel -= gene.getMetabolism();
        if(foodLevel <= 0) {
            setDead();
        }
    }
    
    /**
     * Make this Animal less hungry. 
     * @param foodValue increment based on what fieldItem was eaten 
     */
    public void decrementHunger(int foodValue) {
        foodLevel += foodValue;
    }
    
    /**
     * Increase the age.
     * This could result in the animals
     */
    public void incrementAge() {
        age++;
        if(age > gene.getLifeSpan()) {
            setDead();
        }
    }
    
    /**
     * checks if animal is not deseased  
     * generates a double if double < disease probability make animal diseased
     */
    public void catchRandomDisease(){
        if (!diseased){
            Random rand = Randomizer.getRandom();
            Random random = new Random();
            // CHANGED > TO <
            double var = rand.nextDouble();
            if(var <= gene.getDiseaseProbability()){
                //System.out.println("random double was:" + var + " Disease prob was" + gene.getDiseaseProbability());
                diseased = true;
                //System.out.println(animalType+ "IS DISEASED");
            }
        }
    }
    
    /**
     * checks if adacent animals are of same type and spreads disease randomly 
     */
    public void spreadDisease(){
        if (diseased){
            Random rand = Randomizer.getRandom();
            Random random = new Random();
            
            //Changed from > to <
            if(rand.nextDouble() <= gene.getDiseaseProbability()){
                List<FieldItem> NeighbourFieldItems = getField().getLivingNeighbours(getLocation());
                if(!NeighbourFieldItems.isEmpty()) {
                    for (FieldItem fieldItem : NeighbourFieldItems) {
                        if (fieldItem instanceof Animal){
                            Animal animal = (Animal) fieldItem;
                            if (animal.getAnimalType() == animalType){
                                animal.setDiseased(true);
                                //System.out.println("ANIMLA SPREAD DISEASE");
                            }
                        }
                    }
                }
            }
        }
    }
    
    //Getters and setters
    private void setGender(){
        if (rand.nextBoolean()) {
            animalGender = Gender.Male;
        } else {
            animalGender = Gender.Female;
        }
    }
    
    public Gender getGender(){
        return animalGender;
    }
    
    public void setMaxAge(int value){
        maxAge = value;
    }
    
    public double getMetabolism(){
        return metabolism;
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
    
    public int getAge(){
        return age;
    }
    
    public AnimalType getAnimalType(){
        return animalType;
    }
    
    public void setDiseased(boolean value){
        diseased = value;
    }
    
    public boolean getDiseased()
    {
        return diseased;
    }
    
    public void setMale()
    {
        animalGender = Gender.Male;
    }
    
    public void setFemale()
    {
        animalGender = Gender.Female;
    }
    
}