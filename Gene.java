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
    //private double metabolism;
    private boolean isFirstGen;
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
        
   
        System.out.println( breedingAge +" "+  lifeSpan +" "+ breedingProbability +" "+ litterSize +" "+ diseaseProbability +" "+ metabolism);
    }
    
    /**
     * Creates a gene randomly generates
     * breeding AGE, Metabolism, life Span, Litter Size, Disease probability,
     */
    public Gene(String value, String value2){
         //combineGene(value, value2);
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
        } while (result == min || result == max); // Ensure neither bound is picked
        System.out.println(result);
        return result;
    }
    
    private String geneToString(){
        String stringBreedingAge = String.valueOf(breedingAge);
        String stringLifeSpan;
        String stringBreedingProbability = String.valueOf(breedingProbability * 100).substring(0,2);
        String stringLitterSize;
        String stringDiseaseProbability = String.valueOf(diseaseProbability * 100).substring(0,2);
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
        
        gene = stringBreedingAge + stringLifeSpan 
        + stringBreedingProbability + stringLitterSize 
        + stringDiseaseProbability + stringMetabolism;
        System.out.println("Gene is: "+ gene);
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
        
        breedingProbability = (Integer.parseInt(value.substring(5,7))/100.0);
        //System.out.println("breeding prob : "+ breedingProbability);
        
        String temporary2 = value.substring(7,8);
        if (temporary2.equals("0")){
            litterSize = Integer.parseInt(value.substring(8,9));
        }else{
            litterSize = Integer.parseInt(value.substring(7,9));
        }
        //System.out.println("litter size : "+ litterSize);
        
        String temporary3 = value.substring(11,12);//changed here
        //System.out.println("temporary 3  : "+ temporary3 );
        if (temporary3.equals("1")){
            metabolism = 1.0;
        }else{
            metabolism = (Integer.parseInt(value.substring(12))/100.0);
        }
        diseaseProbability = (Integer.parseInt(value.substring(9,11))/100.0);//cahnged here
        System.out.println("disease prob : "+ diseaseProbability );
        //System.out.println("metabolism : "+ metabolism);
        geneToString();
        
    }
    
    public void combineGene(String value , String value2){
        String parentGene1 = value.substring(0,7);
        String parentGene2 = value2.substring(7);
        String combineGene = parentGene1 + parentGene2;
        System.out.println(combineGene);
        stringToGene(combineGene); 
        chooseMutatedGene();
        geneToString();
        
        //stringToGene(combineGene);
        System.out.println("new gene after any mutaions: "+ gene);
        //return gene;// 1 is just for trial test gene
    }
    
    private void chooseMutatedGene() {
        
        
        // Decide whether mutation happens (20% chance)
        //testing changing -.2 to 0  & flipped inequality
        if (random.nextDouble() > 0.00) {
            int mutationChoice = 6; //random.nextInt(5);  // We have 5 integer properties to mutate: breedingAge, lifeSpan, litterSize, diseaseProbability, metabolism
    
            switch (mutationChoice) {
                case 0: // Mutate breedingAge
                    //System.out.println("Mutated breeding age");
                    if (breedingAge == 90){
                        breedingAge -= 1;
                        //System.out.println("breeding Age dec by 1: " +breedingAge);
                    }else if (breedingAge == 10){
                        breedingAge += 1 ;
                        //System.out.println("breeding Age inc by 1: " +breedingAge);
                    }else{
                        breedingAge = mutateIntegerGene(breedingAge);
                    }
                    break;
                case 1: // Mutate lifeSpan
                    //System.out.println("Mutated life span");
                    //System.out.println(lifeSpan);
                    if (lifeSpan == 120){
                        lifeSpan -= 1;
                        //System.out.println("Mutated life dec by 1: " +lifeSpan);
                    }else if (lifeSpan == 10){
                        lifeSpan += 1;
                        //System.out.println("Mutated life inc by 1: " +lifeSpan);
                    }else{
                        lifeSpan = mutateIntegerGene(lifeSpan);
                    }
                    break;
                case 2: // Mutate litterSize
                    //System.out.println("Mutated littersize");
                    if (litterSize == 12){
                        litterSize -= 1;
                        System.out.println("litterSize dec by 1: " +litterSize);
                    }else if (litterSize == 1){
                        litterSize += 1;
                        System.out.println("litterSize inc by 1: " +litterSize);
                    }else{
                        litterSize = mutateIntegerGene(litterSize);
                    }
                    break;
                case 3: // Mutate diseaseProbability
                    System.out.println("Mutated disease Probability");
                    if (diseaseProbability == 0.49){
                        diseaseProbability -= 0.1;
                        System.out.println("diseaseProbability dec by 1: " +diseaseProbability);
                    }else if (diseaseProbability == 0.01){
                        diseaseProbability += 0.1;
                        System.out.println("diseaseProbability inc by 1: " +diseaseProbability);
                    }else{
                        diseaseProbability = mutateDoubleGene(diseaseProbability);
                    }
                    break;
                case 4: // Mutate metabolism
                    System.out.println("Mutated metabolism");
                    if (metabolism == 1.0){
                        metabolism -= 0.1;
                        System.out.println("metabolism dec by 1: " +metabolism);
                    }else if (metabolism == 0.25){
                        metabolism += 0.1;
                        System.out.println("metabolism inc by 1: " +metabolism);
                    }else{
                        metabolism = mutateDoubleGene(metabolism);
                    }
                    break;
                default: // Breeeding probability 
                    System.out.println("Breeding proabibly  probability");
                    if (breedingProbability == 0.49){
                        breedingProbability -= 0.1;
                        System.out.println("breedingProbability dec by 1: " +breedingProbability);
                    }else if (breedingProbability == 0.01){
                        breedingProbability += 0.1;
                        System.out.println("breedingProbability inc by 1: " +breedingProbability);
                    }else{
                        breedingProbability = mutateDoubleGene(breedingProbability);
                    }
                    break;
            }
        }else{
            System.out.println("no mutation");
        }
    }
    
    private int mutateIntegerGene(int value ){
        
        // 50% chance to add or subtract 1
        if (random.nextBoolean()) {
            value++;  // Add 1
        } else {
            value--;  // Subtract 1
        }
        System.out.println("Value is: " + value);
        
        //System.out.println("Mutated lifeSpan: " + lifeSpan);
        return value;
    }
    
    private double mutateDoubleGene(double value){
        
        // 50% chance to add or subtract 1
        if (random.nextBoolean()) {
            value += 0.1; 
        } else {
            value -= 0.1; 
        }
        System.out.println("Value is: " + value);
        return value;
        //System.out.println("Mutated lifeSpan: " + lifeSpan);
    }
    
    public String getGene(){
        return gene;
    }
}