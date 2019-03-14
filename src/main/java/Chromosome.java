//بسم الله الرحمن الرحیم

import java.io.FileNotFoundException;
import java.rmi.activation.ActivationGroup_Stub;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Chromosome {

    private int[] genes;

    private long fitness;

    private float percentageFitness;


    //CHROMOSOME CLASS CONSTRUCTOR
    public Chromosome(int[] gene){
        this.genes = gene;
    }


    //GETTHER AND SETTER METHODS
    public long getFitness() {
        return this.fitness;
    }

    public float getPercentageFitness() {
        return percentageFitness;
    }

    public void setPercentageFitness(float percentageFitness) {
        this.percentageFitness = percentageFitness;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    public int[] getGenes() {
        return this.genes;
    }

    public void setGenes(int[] genes) {
        this.genes = genes;
    }

    public int getLength(){
        return genes.length;
    }


    //CREATE FIRST POPULATION METHOD
    public static Chromosome createRandomChromosome() throws FileNotFoundException {
        Random random = new Random();
        int geneCount = random.nextInt(Main.MAX_GENE_COUNT-Main.MIN_GENE_COUNT)+Main.MIN_GENE_COUNT;
        int[] gene = new int[geneCount];
        for (int i=0;i<geneCount;i++){
            int currentGene = random.nextInt(Person.getPersonCount()-1)+1;
            for (int j=0;j<gene.length;j++){
                if (currentGene == gene[j]){
                    i--;
                    break;
                }
            }
            gene[i]=currentGene;
        }
        System.out.println(geneCount);
        for (int i=0;i<gene.length;i++){
            System.out.print(gene[i] + " , ");
        }
        Chromosome chromosome = new Chromosome(gene);
        return chromosome;
    }

    public static Chromosome[] createFirstPopulation() throws FileNotFoundException {
        Chromosome[] chromosomes = new Chromosome[Main.POPULATION_SIZE];
        for (int i = 0; i<Main.POPULATION_SIZE; i++){
            chromosomes[i] = Chromosome.createRandomChromosome();
        }
        return chromosomes;
    }


    //EVULATE METHODS
    public void computingFitnessForStatus1(List<Person> personList) throws FileNotFoundException {
        int[] researchs = new int[957];
        for (int i=0;i<researchs.length;i++) {
            researchs[i]=0;
        }
        for (int i = 0; i < this.genes.length ; i++){
            for (int j=0 ; j < personList.size() ; j++){
                if(personList.get(j).id == this.genes[i]){
                    Person person = personList.get(j);
                    for (int k=0;k<person.resarchArray.length;k++){
                        researchs[person.resarchArray[k]] = 1;
                    }
                }
            }
        }
        int counter=0;
        for (int i=0;i<researchs.length;i++){
            if(researchs[i]==1){
                counter++;
            }
        }

        this.fitness = (2*counter)-this.genes.length;
    }

    public void computingFitnessForStatus2(List<Person> personList) throws FileNotFoundException {
        int[] researchs = new int[957];
        for (int i=0;i<researchs.length;i++) {
            researchs[i]=0;
        }
        int researchNumber = 0;
        for (int i = 0; i < this.genes.length ; i++){
            for (int j=0 ;j < personList.size() ; j++){
                if(personList.get(j).id == this.genes[i]){
                    Person person = personList.get(j);
                    researchNumber += person.resarchArray.length;
                    for (int k=0;k<person.resarchArray.length;k++){
                        researchs[person.resarchArray[k]] = 1;
                    }
                }
            }
        }
        int counter=0;
        for (int i=0;i<researchs.length;i++){
            if(researchs[i]==1){
                counter++;
            }
        }



        this.fitness = (counter*counter)-this.genes.length;
    }

    public static Chromosome[] computingPercentageFitness(Chromosome[] chromosomes){
        float fitnessSumation = 0;
        for (int i=0;i<chromosomes.length;i++){
            fitnessSumation += chromosomes[i].fitness;
        }
        for (int i=0;i<chromosomes.length;i++){
            System.out.println(fitnessSumation);
            float fitness = chromosomes[i].fitness;
            chromosomes[i].percentageFitness = ((fitness)/fitnessSumation);
        }
        return chromosomes;
    }

    public static Chromosome[] sortingWithFitness(Chromosome[] chromosomes){
        for (int i=1;i<chromosomes.length;i++){
            int j=i;
            while (j>0 && chromosomes[j].fitness>chromosomes[j-1].fitness){
                Chromosome currentChromosome = chromosomes[j];
                chromosomes[j] = chromosomes[j-1];
                chromosomes[j-1] = currentChromosome;
                System.out.println("\n");
                j--;
            }
        }

        return chromosomes;
    }

    public static int getFitnessSumation(Chromosome[] chromosomes){
        int fitnessSumation = 0;
        for (int i=0;i<chromosomes.length;i++){
            fitnessSumation += chromosomes[i].fitness;
        }
        return fitnessSumation;
    }

    //SELECTION METHODS
    public static Chromosome createParrent(Chromosome[] chromosomes){
        Random random = new Random();
        float randomNumber = (random.nextInt(100));
        //randomNumber = getFitnessSumation(chromosomes)-randomNumber;
        randomNumber = randomNumber/ 100;
        System.out.print(randomNumber+"<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        int currentFitnessSumation = 0;
        for (int i=0;i<chromosomes.length;i++){
            currentFitnessSumation += chromosomes[i].percentageFitness;
            if(randomNumber<=currentFitnessSumation){
                System.out.println(i);
                return chromosomes[i];
            }
        }
        return chromosomes[0];
    }

    public static Chromosome[] createParrents(Chromosome[] chromosomes){
        Chromosome[] currentChromosome = new Chromosome[chromosomes.length];
        for (int i=0;i<chromosomes.length;i++){
            currentChromosome[i] = createParrent(chromosomes);
        }
        return currentChromosome;
    }


    //CROSSOVER METHODS
    public static Chromosome[] combationTwoChromosome(int[] firstGenes,int[] secondGenes){
        Chromosome[] chromosomes = new Chromosome[2];
        int sliceRandomNumberForFirstGene=firstGenes.length/2;
        if(firstGenes.length>3){
            sliceRandomNumberForFirstGene = new Random().nextInt(firstGenes.length-2)+1;
        }
        int sliceRandomNumberForSecondGene = secondGenes.length/2;
        if (secondGenes.length>3){
            sliceRandomNumberForSecondGene = new Random().nextInt(secondGenes.length-2)+1;
        }
        int[] firstGenesPart1 = new int[sliceRandomNumberForFirstGene+1];
        int[] firstGenesPart2 = new int[firstGenes.length-(sliceRandomNumberForFirstGene+1)];
        int[] secondGenesPart1 = new int[sliceRandomNumberForSecondGene+1];
        int[] secondGenesPart2 = new int[secondGenes.length-(sliceRandomNumberForSecondGene+1)];

        for (int i=0;i<firstGenesPart1.length;i++){
            firstGenesPart1[i] = firstGenes[i];
        }
        int counter =0;
        for (int i=firstGenesPart1.length;i<firstGenes.length;i++){
            firstGenesPart2[counter] = firstGenes[i];
            counter++;
        }
        for (int i=0;i<secondGenesPart1.length;i++){
            secondGenesPart1[i] = secondGenes[i];
        }
        counter=0;
        for (int i=secondGenesPart1.length;i<secondGenes.length;i++){
            secondGenesPart2[counter] = secondGenes[i];
            counter++;
        }
        int[] newFirstGene = new int[firstGenesPart1.length+secondGenesPart2.length];
        int[] newSecondGene = new int[secondGenesPart1.length+firstGenesPart2.length];
        counter=0;
        for (int i=0;i<newFirstGene.length;i++){
            if (i<=sliceRandomNumberForFirstGene){
                newFirstGene[i] = firstGenesPart1[i];
            }else {
                newFirstGene[i] = secondGenesPart2[counter];
                counter++;
            }
        }
        counter=0;
        for (int i=0;i<newSecondGene.length;i++){
            if (i<=sliceRandomNumberForSecondGene){
                newSecondGene[i] = secondGenesPart1[i];
            }else {
                newSecondGene[i] = firstGenesPart2[counter];
                counter++;
            }
        }

        newFirstGene = mutation(newFirstGene);
        newSecondGene = mutation(newSecondGene);
        newFirstGene = removeSameGenes(newFirstGene);
        newSecondGene = removeSameGenes(newSecondGene);

        chromosomes[0] = new Chromosome(newFirstGene);
        chromosomes[1] = new Chromosome(newSecondGene);

        return chromosomes;

    }

    public static Chromosome[] crossover(Chromosome[] chromosomes){
        for (int i=0;i<chromosomes.length;i+=2){
            Chromosome[] chromosomes1 = combationTwoChromosome(chromosomes[i].genes,chromosomes[i+1].genes);
            chromosomes[i] = chromosomes1[0];
            chromosomes[i+1] = chromosomes1[1];
        }
        return chromosomes;
    }

    public static int[] removeSameGenes(int[] genes){
        List<Integer> genesList = new ArrayList<Integer>();
        genesList.add(genes[0]);
        for (int i=1;i<genes.length;i++){
            boolean isSame = false;
            for (int j=0;j<genesList.size();j++){
                if(genesList.get(j)==genes[i]){
                    isSame = true;
                    break;
                }
            }
            if (!isSame){
                genesList.add(genes[i]);
            }
        }
        int[] currenGenes = new int[genesList.size()];
        for (int i=0;i<genesList.size();i++){
            currenGenes[i] = genesList.get(i);
        }
        return currenGenes;
    }

    public static long findBestFitness(Chromosome[] chromosomes){
        return chromosomes[0].fitness;
    }

    public static boolean isSameAllChromosome(Chromosome[] chromosomes){
        for (int i=1;i< chromosomes.length/2;i++){
            if(chromosomes[i-1].getFitness() != chromosomes[i].getFitness()){
                return false;
            }
        }
        return true;
    }


    //MUTATION METHODS
    public static int[] mutation(int[] array){
        Random random = new Random();
        int randomNumber = random.nextInt(100);
        if(randomNumber<Main.MUTATION_POROPEBLY){
            array = doMutation(array);
        }
        return array;
    }

    private static int[] doMutation(int[] array) {
        Random random = new Random();
        int randomNumber = random.nextInt(4);
        for (int i=0;i<randomNumber;i++){
            int randomIndex = random.nextInt(array.length-1);
            int randomPerson = random.nextInt(Main.PERSON_COUNT-1)+1;
            array[randomIndex] = randomPerson;
        }
        return array;
    }
}
