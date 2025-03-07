import java.util.Random;
import java.text.DecimalFormat;

/**
 * Represent a gene in a rectangular grid.
 *
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29
 */

public class Gene {
    private int breedingAge;
    private int lifeSpan;
    private double breedingProbability;
    private int litterSize;
    private double diseaseProbability;
    private double metabolism;
    private String gene="";
    Random random = new Random();
    /**
     * Creates a gene randomly generates
     * breeding AGE, Metabolism, life Span, Litter Size, Disease probability,
     */
    public Gene() {
        Random random = new Random();
        
        breedingAge = randomIntInRange(90,12);
        lifeSpan = randomIntInRange(120,10);
        breedingProbability = randomDoubleInRange(0.49, 0.1);
        litterSize = randomIntInRange(12,1);
        diseaseProbability = randomDoubleInRange(0.49 ,0.1);
        metabolism = randomDoubleInRange(1.00 ,0.25);
    }
    
    /**
     * Creates a child gene
     */
    public Gene(String value, String value2){
        // Combine the two strings to make a gene
        // Generate a gene from the two strings
        // mutate them as they are a child gene.
         mutateAGene(aStringToGene(combineGeneString(value, value2)));
    }
    
    private int randomIntInRange(int max, int min){
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
    
    private double randomDoubleInRange(double max, double min){
        Random random = new Random();
        return min + (max - min) * random.nextDouble();
    }
    
    public static String DataToString(boolean twoOrThreeDigits, int number){
        if (!twoOrThreeDigits){
            return String.format("%02d", number); // returns format 00
        }else{
            return String.format("%03d", number); // returns format 000
        }
    }
    
    // return a string from a gene.
        public static String aGeneToString(Gene gene) {
        String geneString = "";
        
        geneString += DataToString(false, gene.getBreedingAge()); // xx
        geneString += DataToString(true, gene.getLifeSpan()); // xxyyy
        geneString += DataToString(false, ((int)(gene.getBreedingProbability()*100))); // xxyyyzz
        geneString += DataToString(false, gene.getLitterSize()); // xxyyyzzqq
        geneString += DataToString(false, ((int)(gene.getDiseaseProbability()*100))); // xxyyyzzqqww
        geneString += DataToString(true, ((int)(gene.getMetabolism()*100))); // xxyyyzzqqwwrrr
        
        // format xxyyyzzqqwwrrr
        return geneString;
    }
    
    // return a gene from an input string.
    public Gene aStringToGene(String geneString) {
        // gene format xxyyyzzqqwwrrr
        int breedingAge = Integer.parseInt(geneString.substring(0, 2));  // First 2 digits: xx
        int lifeSpan = Integer.parseInt(geneString.substring(2, 5));  // Next 3 digits: yyy
        double breedingProbability = (double) Integer.parseInt(geneString.substring(5, 7)) / 100;  // Next 2 digits: zz
        int litterSize = Integer.parseInt(geneString.substring(7, 9));  // Next 2 digits: qq
        double diseaseProbability = (double) Integer.parseInt(geneString.substring(9, 11)) / 100; // Next 2 digits: ww
        double metabolism = (double) Integer.parseInt(geneString.substring(11, 14)) / 100; // Last 3 digits: rrr
        
        Gene gene = new Gene();
        gene.setBreedingAge(breedingAge);
        gene.setLifeSpan(lifeSpan);
        gene.setBreedingProbability(breedingProbability);
        gene.setLitterSize(litterSize);
        gene.setDiseaseProbability(diseaseProbability);
        gene.setMetabolism(metabolism);
        
        return gene;
    }
    
    private String combineGeneString(String gene01, String gene02){
        String gene1 = gene01.substring(0,7); // gene01 XXXXXXX0000000
        String gene2 = gene02.substring(7,14); // gene02 0000000YYYYYYY
        return gene1+gene2; // XXXXXXXYYYYYYY
    }
    
    public void mutateAGene(Gene inGene){
        int mutatedBreedingAge = mutateAGeneValue(inGene.getBreedingAge(), 12, 90);
        int mutatedLifeSpan = mutateAGeneValue(inGene.getLifeSpan(), 10, 120);
        int mutatedLitterSize = mutateAGeneValue(inGene.getLitterSize(), 1, 12);
        
        if(mutatedBreedingAge != -1)
        {
            inGene.setBreedingAge(mutatedBreedingAge);
        }
        
        if(mutatedLifeSpan != -1)
        {
            inGene.setLifeSpan(mutatedLifeSpan);
        }
        
        if (mutatedLitterSize != -1)
        {
             inGene.setLitterSize(mutatedLitterSize);  
        }
    }

    private int mutateAGeneValue(int inGeneValue, int min, int max)
    {
        if(random.nextDouble() < 20) {
            int mutatedValue = inGeneValue + randomOneOrMinusOne();
            mutatedValue = Math.clamp(mutatedValue, min, max);
            return mutatedValue;
        }
        return -1;
    }
    
    private int randomOneOrMinusOne()
    {
        if(random.nextBoolean())
        {
            return 1;
        }
        return 0;
    }
        
    public String getGene(){
        return gene;
    }
    
    // Breeding age getter setter
    public int getBreedingAge(){    
        return breedingAge;
    }
        public void setBreedingAge(int value){    
        breedingAge = value;
    }
    
    // Life span getter setter
    public int getLifeSpan(){
        return lifeSpan;
    }
        public void setLifeSpan(int value){    
        lifeSpan = value;
    }
    
    // Breeding probability getter setter
    public double getBreedingProbability(){
        return breedingProbability;
    }
    public void setBreedingProbability(double value){    
        breedingProbability = value;
    }
    
    // Litter size getter setter
    public int getLitterSize(){
        return litterSize;
    }    
    public void setLitterSize(int value){    
        litterSize = value;
    }
    
    // Disease probability getter setter
    public double getDiseaseProbability(){
        return diseaseProbability;
    }
    public void setDiseaseProbability(double value){    
        diseaseProbability = value;
    }
    
    // Metabolism getter setter
    public double getMetabolism(){
        return metabolism;
    }
    public void setMetabolism(double value){    
        metabolism = value;
    }
}