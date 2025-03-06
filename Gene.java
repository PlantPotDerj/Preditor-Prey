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
    private double breedingProbability;
    private int litterSize;
    private double diseaseProbability;
    private double metabolism;
    private String gene;
    Random random = new Random();
    /**
     * Creates a gene randomly generates
     * breeding AGE, Metabolism, life Span, Litter Size, Disease probability,
     */
    public Gene() {
        Random random = new Random();
        breedingAge = randomIntInRange(90,12);
        lifeSpan = randomIntInRange(120,10);
        breedingProbability = randomDoubleInRange(0.5, 0.0);
        litterSize = randomIntInRange(12,1);
        diseaseProbability = randomDoubleInRange(0.5 ,0.0);
        metabolism = randomDoubleInRange(1.01 ,0.24);
        geneToString();
   
        //System.out.println( breedingAge +" "+  lifeSpan +" "+ breedingProbability +" "+ litterSize +" "+ diseaseProbability +" "+ metabolism);
    }
    
    /**
     * Creates a gene randomly generates
     * breeding AGE, Metabolism, life Span, Litter Size, Disease probability,
     */
    public Gene(String value, String value2){
         combineGene(value, value2);
    }
    
    private int randomIntInRange(int max, int min){
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
    
    private double randomDoubleInRange(double max, double min){
        Random random = new Random();
        double result;
        do {
            result = min + (random.nextDouble() * (max - min));
            result = Math.round(result * 100.0) / 100.0;
        } while (result == min || result == max); 
        //System.out.println(result);
        return result;
    }
    
    private String geneToString(){
        String stringBreedingAge = String.valueOf(breedingAge);
        String stringLifeSpan;
        String stringBreedingProbability;
        String stringLitterSize;
        String stringDiseaseProbability;
        String stringMetabolism;
        
        if (lifeSpan<100){
            stringLifeSpan = "0" + String.valueOf(lifeSpan);
        }else{
            stringLifeSpan = String.valueOf(lifeSpan);
        }
        
        if (litterSize < 10){
            stringLitterSize = "0" + String.valueOf(litterSize);
        }else{
            stringLitterSize = String.valueOf(litterSize);
        }
        
        if (metabolism == 1.0){
            stringMetabolism = String.valueOf(metabolism * 100).substring(0,3);
        }else{
            stringMetabolism = "0" + String.valueOf(metabolism *100).substring(0,2);
        }

        String zeroAfterDecimalPoint = String.valueOf(diseaseProbability).substring(2,3);
        if (zeroAfterDecimalPoint.equals("0")){
            stringDiseaseProbability = "0" + String.valueOf(diseaseProbability * 100).substring(0,1);
        }else{
            stringDiseaseProbability = String.valueOf(diseaseProbability * 100).substring(0,2);
        }
        
        String zeroAfterDecimalPointBreeding = String.valueOf(breedingProbability).substring(2,3);
        if (zeroAfterDecimalPointBreeding.equals("0")){
            stringBreedingProbability  = "0" + String.valueOf(breedingProbability * 100).substring(0,1);
        }else{
            stringBreedingProbability = String.valueOf(breedingProbability * 100).substring(0,2);
        }
        
        gene = stringBreedingAge + stringLifeSpan 
        + stringBreedingProbability + stringLitterSize 
        + stringDiseaseProbability + stringMetabolism;
        //System.out.println("Gene is: "+ gene);
        
        return gene;
        
    }
    
    private void stringToGene(String value){
        breedingAge  = Integer.parseInt(value.substring(0,2));
        //System.out.println("breeding age : "+ breedingAge);
        
        String temporary = value.substring(2,3);
        if (temporary.equals("0")){
            lifeSpan = Integer.parseInt(value.substring(3,5));
        }else{
            lifeSpan = Integer.parseInt(value.substring(2,5));
        }
        //System.out.println("life span : "+ lifeSpan);
        
        String temporary2 = value.substring(7,8);
        if (temporary2.equals("0")){
            litterSize = Integer.parseInt(value.substring(8,9));
        }else{
            litterSize = Integer.parseInt(value.substring(7,9));
        }
        //System.out.println("litter size : "+ litterSize);
        
        String temporary3 = value.substring(11,12);//changed here
        if (temporary3.equals("1")){
            metabolism = 1.0;
        }else{
            metabolism = (Integer.parseInt(value.substring(12))/100.0);
        }
        //System.out.println("metabolism : "+ metabolism);
        

        breedingProbability = (Integer.parseInt(value.substring(5,7))/100.0);
        //System.out.println("breeding prob : "+ breedingProbability);
        
        diseaseProbability = (Integer.parseInt(value.substring(9,11))/100.0);
        //System.out.println("disease prob : "+ diseaseProbability );
        
        geneToString(); //why is this here?
        
    }
    
    private void combineGene(String value , String value2){
        String parentGene1 = value.substring(0,7);
        //System.out.println("parents first half of gene is "+ parentGene1);
        String parentGene2 = value2.substring(7);
        //System.out.println("parents second half of gene is "+ parentGene2);
        String combineGene = parentGene1 + parentGene2;
        //System.out.println(combineGene);
        stringToGene(combineGene); 
        chooseMutatedGene();
        geneToString();
        
        //System.out.println("new gene after any mutaions: "+ gene);
        //return gene;// 1 is just for trial test gene
    }
    
    private void chooseMutatedGene() {
        //testing changing -.2 to 0  & flipped inequality
        if (random.nextDouble() > 0.20) {
            int mutationChoice = random.nextInt(5); 
            switch (mutationChoice) {
                case 0: // Mutate breedingAge
                    //System.out.println("mutated breeding age");
                    if (breedingAge == 90){
                        breedingAge -= 1;
                    }else if (breedingAge == 10){
                        breedingAge += 1 ;
                    }else{
                        breedingAge = mutateIntegerGene(breedingAge);
                    }
                    break;
                case 1: // Mutate lifeSpan
                    //System.out.println("mutated life span");
                    if (lifeSpan == 120){
                        lifeSpan -= 1;
                    }else if (lifeSpan == 10){
                        lifeSpan += 1;
                    }else{
                        lifeSpan = mutateIntegerGene(lifeSpan);
                    }
                    break;
                case 2: // Mutate litterSize
                    //System.out.println("mutated litter size");
                    if (litterSize == 12){
                        litterSize -= 1;
                    }else if (litterSize == 1){
                        litterSize += 1;
                    }else{
                        litterSize = mutateIntegerGene(litterSize);
                    }
                    break;
                case 3: // Mutate diseaseProbability
                    //System.out.println("mutated disease probabilty");
                    if (diseaseProbability == 0.49){
                        diseaseProbability -= 0.1;
                    }else if (diseaseProbability == 0.01){
                        diseaseProbability += 0.1;
                    }else{
                        diseaseProbability = mutateDoubleGene(diseaseProbability);
                    }
                    break;
                case 4: // Mutate metabolism
                    //System.out.println("mutated metabolism");
                    if (metabolism == 1.0){
                        metabolism -= 0.1;
                    }else if (metabolism == 0.25){
                        metabolism += 0.1;
                    }else{
                        metabolism = mutateDoubleGene(metabolism);
                    }
                    break;
                default: // Breeeding probability
                    //System.out.println("mutated breeding probabilty");
                    if (breedingProbability == 0.49){
                        breedingProbability -= 0.1;
                    }else if (breedingProbability == 0.01){
                        breedingProbability += 0.1;
                    }else{
                        breedingProbability = mutateDoubleGene(breedingProbability);
                    }
                    break;
            }
        }
    }
    
    private int mutateIntegerGene(int value ){
        if (random.nextBoolean()) {
            value++;
        } else {
            value--;
        }
        return value;
    }
    
    private double mutateDoubleGene(double value){
        if (random.nextBoolean()) {
            value += 0.1; 
        } else {
            value -= 0.1; 
        }
        return value;
    }
    
    public String getGene(){
        return gene;
    }
    
    public int getBreedingAge(){    
        return breedingAge;
    }
    
    public int getLifeSpan(){
        return lifeSpan;
    }
    
    public int getLitterSize(){
        return litterSize;
    }
    
    public double getMetabolism(){
        return metabolism;
    }
    
    public double getBreedingProbability(){
        return breedingProbability;
    }
    
    public double getDiseaseProbability(){
        return diseaseProbability;
    }
}