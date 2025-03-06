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
    private boolean alive;
    private Color color = Color.BLUE;
    private int age = 0;
    private int stepsBeforeDiseasedDeath = 5;
    private int stepsCounter = 0;
    private double foodLevel = 10.0;
    private int maxAge = 20;
    private int breedingAge = 10;
    private double breedingProbabiltiy = 0.05;
    private double metabolism = 0.05;
    private double diseasedProbabiltiy = 0.02;//changed from 0.22 to 0
    private boolean diseased = false;
    //boolean canBreed = false;
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
     */
    
    public Animal(Field field, Location location, Color col, AnimalType animalType, Gene gene){
        super(field, location, col);
        alive = true;
        this.animalType = animalType;
        this.gene = gene;
        //System.out.println("animals gene is" + gene.getGene());
        diseasedProbabiltiy = gene.getDiseaseProbability();
        breedingProbabiltiy = gene.getBreedingProbability();
        maxAge = gene.getLifeSpan();
        maxLitterSize = gene.getLitterSize();
        breedingAge = gene.getBreedingAge();
        metabolism = gene.getMetabolism();
        
        setLocation(location);
        setGender();
    }
    
    /**
     * Make this animal act - that is: make it do
     * whatever it wants/needs to do.
     * @param newAnimals A list to receive newly born animals.
     */
    public void act(List<Animal> newAnimals){
        if (diseased){
            if (stepsCounter < stepsBeforeDiseasedDeath){
                stepsCounter ++;
                //System.out.println(stepsCounter);//
            }else{
                stepsCounter = 0;
                //System.out.println("TIMES UP ANIMAL DIED");//
                setDead();
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
    
    public void giveBirth(List<Animal> newAnimals) {
        // New animals are born into adjacent locations.
        // Get a list of adjacent free locations.
        Field field = getField();
        List<Location> free = field.getFreeAdjacentLocations(getLocation());
        int births = breed();
        for(int b = 0; b < births && free.size() > 0; b++) {
            Location loc = free.remove(0);
            switch (animalType){
                case (AnimalType.Tiger):
                    Tiger tiger = new Tiger(true,field, loc,new Gene(gene.getGene(),animalToMateWithGene));
                    newAnimals.add(tiger);
                    break;
                case (AnimalType.Wolf):
                    Wolf wolf = new Wolf(true,field, loc, new Gene(gene.getGene(),animalToMateWithGene));
                    newAnimals.add(wolf);
                    break;
                case (AnimalType.Zebra):
                    Zebra zebra = new Zebra(true,field, loc, new Gene(gene.getGene(),animalToMateWithGene)); 
                    newAnimals.add(zebra);
                    break;
                case (AnimalType.Deer):
                    Deer deer = new Deer(true,field, loc, new Gene(gene.getGene(),animalToMateWithGene));
                    newAnimals.add(deer);
                    break;
                default:
                    Mouse mouse = new Mouse(true,field, loc, new Gene(gene.getGene(),animalToMateWithGene));
                    newAnimals.add(mouse);
            }
        }
    }
    
    /**
     * Generate a number representing the number of births,
     * if it can breed.
     * @return The number of births (may be zero).
     */
    private int breed() {
        int births = 0;
        // place after the &&
        if(canBreed()){//&& rand.nextDouble() <= getBreedingProbability()) {
            births = rand.nextInt(getMaxLitterSize()) + 1;
        }
        return births;
    }

    /**
     * A prey can breed if it has reached the breeding age.
     * @return true if the prey can breed, false otherwise.
     */
    private boolean canBreed() {
        if (age >= breedingAge){
            //System.out.println("sufficent age");
            List<Location> adjacentLocations = getField().adjacentLocations(getLocation());
            if(!adjacentLocations.isEmpty()){
                //System.out.println(" not empty");
                for (Location loc : adjacentLocations){
                    //Location location = adjacentLocations.remove(0);
                    //System.out.println("False bitch");
                    FieldItem fieldItemAtLocation = getField().getObjectAt(loc);
                    if(fieldItemAtLocation != null){
                        //System.out.println("item has been located empty");
                        if(fieldItemAtLocation instanceof Animal){
                            //System.out.println(" item is an animal will be casted");
                            Animal animalToBreedWith = (Animal) fieldItemAtLocation;
                            if (animalType == animalToBreedWith.getAnimalType()){
                                //System.out.println(" same breed of animal ");
                                if (!(animalToBreedWith.getGender() == animalGender )){
                                    //System.out.println("Animal will mate");
                                    animalToMateWithGene = animalToBreedWith.gene.getGene();
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    
    /**
     * Make this animal more hungry. This could result in the animals's death
     * .
     */
    public void incrementHunger() {
        foodLevel -= metabolism;
        if(foodLevel <= 0) {
            //System.out.println("ANIMAL DEATH FROM HUNGER");
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
            //System.out.println("DEATH OF AGE");
        }
    }
    
    public void catchRandomDisease(){
        if (!diseased){
            Random rand = Randomizer.getRandom();
            Random random = new Random();
            
            if(rand.nextDouble() >= diseasedProbabiltiy){
                diseased = true;
                //System.out.println("Animal is diseased");
            }
        }
    }
    
    public void spreadDisease(){
        if (diseased){
            Random rand = Randomizer.getRandom();
            Random random = new Random();
            
            if(rand.nextDouble() >= diseasedProbabiltiy){
                List<FieldItem> NeighbourFieldItems = getField().getLivingNeighbours(getLocation());
                if(!NeighbourFieldItems.isEmpty())
                {
                    for (FieldItem fieldItem : NeighbourFieldItems) {
                        if (fieldItem instanceof Animal){
                            Animal animal = (Animal) fieldItem;
                            if (animal.getAnimalType() == animalType){
                                animal.setDiseased(true);
                                //System.out.println("Animal has spread disease");
                            }
                        }
                    }
                }
            }
        }
    }
    
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
    
    //public Gene getGene(){
    //    return gene;
    //}
    
}