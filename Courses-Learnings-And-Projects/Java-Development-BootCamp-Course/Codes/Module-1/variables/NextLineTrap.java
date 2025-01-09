package variables;

import java.util.Scanner;

public class NextLineTrap {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String line1 = sc.nextLine();
        String line2 = sc.nextLine();
        String line3 = sc.nextLine();
        String line4 = sc.nextLine();
        String line5 = sc.nextLine();
        System.out.println(line1);
        System.out.println(line2);
        System.out.println(line3);

        System.out.println("Enter you age, your friend's age, and your parent's age: ");
        int yourAge = sc.nextInt();
        int friendAge = sc.nextInt();
        int parentAge = sc.nextInt();

        System.out.println("Ages: You - "+yourAge+", Friend - "+friendAge+", Parent - "+parentAge);

        System.out.println("Enter a Greeting: ");
        sc.nextLine();
        String greeting = sc.nextLine();

        System.out.println("Greeting: "+greeting);

    }
}
