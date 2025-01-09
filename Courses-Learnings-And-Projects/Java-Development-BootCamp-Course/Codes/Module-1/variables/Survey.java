package variables;

import java.util.Scanner;

public class Survey {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int counter =0;
        System.out.println("What is your name?");
        String name = sc.next();
        counter++;

        System.out.println("How much money do you spend on coffee?");
        double coffeePrice = sc.nextDouble();
        counter++;

        System.out.println("How much money do you spend on fast food?");
        double foodPrice = sc.nextDouble();
        counter++;

        System.out.println("How many times a week do you buy a coffee?");
        int coffeeAmount = sc.nextInt();
        counter++;

        System.out.println("How many times a week do you buy a fast food?");
        int foodAmount = sc.nextInt();
        counter++;

        sc.close();

        System.out.println("Thank you "+name+" for answering all "+counter+" questions");
        System.out.println("Weekly you spend $"+(coffeeAmount*coffeePrice)+" on coffee");
        System.out.println("Weekly you spend $"+(foodAmount*foodPrice)+" on fast food");
    }
}
