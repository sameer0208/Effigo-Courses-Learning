package variables;

import java.util.Scanner;

public class Workbook2FifthProgram {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("What is your first name?");
        String fname = sc.next();

        System.out.println("What is your last name?");
        String lname = sc.next();

        System.out.println("How old are you?");
        int age = sc.nextInt();

        System.out.println("Make a username: ");
        sc.nextLine();
        String username = sc.nextLine();

        System.out.println("Which city do you live in?");
        String city = sc.next();

        System.out.println("What country is that?");
        String country = sc.next();

        System.out.println("Thank you for joining JavaGram!");
        System.out.println("\nHere is the information you entered: ");
        System.out.println("\tFirst Name: "+fname);
        System.out.println("\tLast Name: "+lname);
        System.out.println("\tAge: "+age);
        System.out.println("\tUsername: "+username);
        System.out.println("\tCity: "+city);
        System.out.println("\tCountry: "+country);
    }
}
