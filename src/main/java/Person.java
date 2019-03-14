//بسم الله الرحمن الرحیم

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Person {
    int id;
    int [] resarchArray;
    public Person(int id,int[] resarchArray){
        this.id = id;
        this.resarchArray = resarchArray;
    }
    public static List<Person> loadPersonInfo() throws FileNotFoundException {
        List<Person> personList = new ArrayList<Person>();
        File file = new File(Main.getPath("dataset.txt"));
        Scanner scanner = new Scanner(file);
        int researchNumber = Integer.parseInt(scanner.nextLine());
        int personNumber = Integer.parseInt(scanner.nextLine());
        System.out.println(researchNumber + " " + personNumber);
        while (scanner.hasNextLine()){
            String sr = scanner.nextLine();
            String[] srArray = sr.split(",");
            int[] srIntArray = new int[srArray.length];
            for(int i=0;i<srArray.length;i++){
                srIntArray[i] = Integer.parseInt(srArray[i]);
            }
            Person person = new Person(personList.size()+1,srIntArray);
            personList.add(person);
        }
        for (int i=0;i<personList.size();i++){
            System.out.print(personList.get(i).id + " => ");
            for (int j=0;j<personList.get(i).resarchArray.length;j++){
                System.out.print(personList.get(i).resarchArray[j]+",");
            }
            System.out.println(" ");
        }
        return personList;

    }
    public static int getPersonCount() throws FileNotFoundException {
        File file = new File(Main.getPath("dataset.txt"));
        Scanner scanner = new Scanner(file);
        scanner.nextLine();
        return Integer.parseInt(scanner.nextLine());
    }
    public static int getResearchSUbjectCount() throws FileNotFoundException {
        File file = new File(Main.getPath("dataset.txt"));
        Scanner scanner = new Scanner(file);
        scanner.close();
        return Integer.parseInt(scanner.nextLine());
    }
}
