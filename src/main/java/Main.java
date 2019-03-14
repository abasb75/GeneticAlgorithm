//بسم الله الرحمن الرحیم

import javax.print.DocFlavor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.*;
import java.util.List;
import java.util.Timer;

public class Main {
    public static int POPULATION_SIZE = 32;
    public static int MAX_GENE_COUNT = 64;
    public static int MIN_GENE_COUNT = 4;
    public static int MUTATION_POROPEBLY = 1;
    public static int PERSON_COUNT = 1834;
    public static int LONG_TIME = 300000;
    public static int STATUS_1_BEST_FITNESS = 2000;
    public static int STATUS_2_BEST_FITNESS = 1000000;

    public static void main(String[] arg) throws FileNotFoundException {
        System.out.println("بسم الله الرحمن الرحیم");
        final SplashForm splashForm = new SplashForm();
        splashForm.setVisible(true);
        final HomeForm homeForm = new HomeForm();
        homeForm.status1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("بسم الله الرحمن الرحیم");
                try {
                    String minOfGeneSize = homeForm.getMinOfGenes.getText();
                    String maxOfGeneSize = homeForm.getMaxOfGenes.getText();
                    String populationSize = homeForm.getSizePopilation.getText();

                    String maxTime = homeForm.getMaxTime.getText();
                    String sutibleFitness = homeForm.getSutibleFitness.getText();
                    String mutationPropebly = homeForm.getMutationPropebly.getText();

                    MIN_GENE_COUNT = (minOfGeneSize.equals("")? 4 : Integer.parseInt(minOfGeneSize));
                    MAX_GENE_COUNT = (maxOfGeneSize.equals("")? 64 : Integer.parseInt(maxOfGeneSize));
                    POPULATION_SIZE = (populationSize.equals("")? 32 : Integer.parseInt(populationSize));

                    LONG_TIME = (maxTime.equals("")? 300000 : Integer.parseInt(maxTime));
                    STATUS_1_BEST_FITNESS = (sutibleFitness.equals("")? 2000 : Integer.parseInt(sutibleFitness));
                    MUTATION_POROPEBLY = (mutationPropebly.equals("")? 25 : Integer.parseInt(mutationPropebly));
                    doStatus1GeneticAlgorithm();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        homeForm.status2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("بسم الله الرحمن الرحیم");
                try {
                    String minOfGeneSize = homeForm.getMinOfGenes.getText();
                    String maxOfGeneSize = homeForm.getMaxOfGenes.getText();
                    String populationSize = homeForm.getSizePopilation.getText();

                    String maxTime = homeForm.getMaxTime.getText();
                    String sutibleFitness = homeForm.getSutibleFitness.getText();
                    String mutationPropebly = homeForm.getMutationPropebly.getText();

                    MIN_GENE_COUNT = (minOfGeneSize.equals("")? 4 : Integer.parseInt(minOfGeneSize));
                    MAX_GENE_COUNT = (minOfGeneSize.equals("")? 64 : Integer.parseInt(maxOfGeneSize));
                    POPULATION_SIZE = (populationSize.equals("")? 32 : Integer.parseInt(populationSize));

                    LONG_TIME = (maxTime.equals("")? 300000 : Integer.parseInt(maxTime));
                    STATUS_2_BEST_FITNESS = (sutibleFitness.equals("")? 1000000 : Integer.parseInt(sutibleFitness));
                    MUTATION_POROPEBLY = (mutationPropebly.equals("")? 25 : Integer.parseInt(mutationPropebly));
                    doStatus2GeneticAlgorithm();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        new Timer().schedule(new TimerTask() {
                                 @Override
                                 public void run() {
                                     splashForm.dispose();
                                     homeForm.setVisible(true);
                                 }
                             }
                , 3000);




    }

    public static void doStatus1GeneticAlgorithm() throws FileNotFoundException {
        System.out.println("");
        List<Person> personList =  Person.loadPersonInfo();
        long t1 = System.currentTimeMillis();
        Chromosome[] chromosomes = Chromosome.createFirstPopulation();
        //DO 10 TIME
        while (true){
            //CALCULATE FITNESS FOR ALL CHROMOSOME
            for (int i=0;i<chromosomes.length;i++){
                chromosomes[i].computingFitnessForStatus1(personList);
            }
            chromosomes = Chromosome.computingPercentageFitness(chromosomes);
            //SORT CHROMOSOMES BY FITNESS HIGH TO LOW
            chromosomes = Chromosome.sortingWithFitness(chromosomes);
            for (int i=0;i<chromosomes.length;i++){
                System.out.println(i + " => " + chromosomes[i].getFitness());
            }
            long t3 = System.currentTimeMillis();
            if (chromosomes[0].getFitness()> Main.STATUS_1_BEST_FITNESS || Chromosome.isSameAllChromosome(chromosomes) || (t3-t1)>Main.LONG_TIME){
                break;
            }

            //SELECTION
            chromosomes = Chromosome.createParrents(chromosomes);

            //CROSSOVER
            chromosomes = Chromosome.crossover(chromosomes);
        }
        long t2 = System.currentTimeMillis();

        ShowResultForm showResultForm = new ShowResultForm();
        showResultForm.timer.setText("انجام شده در : " + (t2-t1) + " میلی ثانیه");
        showResultForm.addResult(chromosomes[0].getFitness());
        printGenes(chromosomes[0]);
        showResultForm.setVisible(true);
    }

    public static void doStatus2GeneticAlgorithm() throws FileNotFoundException {

        //CREATE FIRST POPULATION
        List<Person> personList =  Person.loadPersonInfo();

        long t1 = System.currentTimeMillis();
        Chromosome[] chromosomes = Chromosome.createFirstPopulation();

        while (true){
            //CALCULATING FITNESS FOR ALL CHROMOSOME
            for (int i=0;i<chromosomes.length;i++){
                chromosomes[i].computingFitnessForStatus2(personList);
            }
            chromosomes = Chromosome.computingPercentageFitness(chromosomes);
            //SORT CHROMOSOMES BY FITNESS HIGH TO LOW
            chromosomes = Chromosome.sortingWithFitness(chromosomes);
            for (int i=0;i<chromosomes.length;i++){
                System.out.println(i + " => " + chromosomes[i].getPercentageFitness());
            }
            long t3 = System.currentTimeMillis();
            if (chromosomes[0].getFitness() > Main.STATUS_2_BEST_FITNESS || Chromosome.isSameAllChromosome(chromosomes)|| (t3-t1)>Main.LONG_TIME){
                break;
            }

            //SELECTION
            chromosomes = Chromosome.createParrents(chromosomes);

            //CROSSOVER
            chromosomes = Chromosome.crossover(chromosomes);
        }
        long t2 = System.currentTimeMillis();

        ShowResultForm showResultForm = new ShowResultForm();
        showResultForm.timer.setText("انجام شده در : " + (t2-t1) + " میلی ثانیه");
        showResultForm.addResult(chromosomes[0].getFitness());
        printGenes(chromosomes[0]);
        showResultForm.setVisible(true);
    }

    public static String getPath(String res){
        URL url = Main.class.getClassLoader().getResource(res);
        return url.getPath();
    }

    public static void printGenes(Chromosome chromosome){
        int[] genes = chromosome.getGenes();
        for (int i = 0;i<genes.length;i++){
            System.out.print(genes[i]+":");
        }
    }

}
