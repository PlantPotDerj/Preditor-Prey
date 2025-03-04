import java.util.Random;

/**
 * Represent a gene in a rectangular grid.
 *
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29
 */

public class Gene {

    private int breedingAge;
    private int lifeSpan;
    private int breedingProbability;
    private int litterSize;
    private double diseaseProbability;
    private double metabolism;
    private boolean isFirstGen;
    private String gene;

    /**
     * Creates a gene randomly generates
     * breeding AGE, Metabolism, life Span, Litter Size, Disease probability,
     */
    public Gene() {
        Random random = new Random();
        breedingAge = randomIntInRange(90,12);
        lifeSpan = randomIntInRange(120,10);
        litterSize = randomIntInRange(12,1);
        metabolism = randomDoubleInRange(1.0 ,0.25);
        diseaseProbability = randomDoubleInRange(0.5 ,0.0);
        //this.col = col;
        //testinng purposes only:
        System.out.println("Breeding age: "+breedingAge
            +" life Span: " + lifeSpan 
            + " litter Size: " + litterSize
            +" metabolism: "+ metabolism 
            + "disease Probability:" + diseaseProbability);
    }
    
    /**
     * Creates a gene randomly generates
     * breeding AGE, Metabolism, life Span, Litter Size, Disease probability,
     */
    public Gene(String value){
        stringToGene(value);
    }
    
    private int randomIntInRange(int max, int min){
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
    
    private double randomDoubleInRange(double max, double min){
        Random random = new Random();
        return min + (random.nextDouble() * (max - min));
    }
    
    private String geneToString(){
        String stringBreedingAge;
        String stringLifeSpan;
        String stringBreedingProbability;
        String stringLitterSize;
        String stringDiseaseProbability;
        String metabilosm;
        
        return "1";
    }
    
    private int stringToGene(String value){
        return 2;
    }
    
    private String combineGene(){
        return "3";
    }
    
    public String getGene(){
        return gene;
    }

}