package variables;

import java.util.Scanner;

public class Challenge1Chatbot {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("Hello. What is your name?");
        String name = sc.next();

        System.out.println("\nHi "+name+"! I'm Javabot. Where are you from?");
        String place = sc.next();

        System.out.println("\nI hear it's beautiful at "+place+"! I'm from a place called Oracle");
        System.out.println("\nHow old are you?");
        int age = sc.nextInt();

        System.out.println("\nSo you're "+age+", cool! I'm 400 years old.");
        System.out.println("\nThis means I'm "+(400/age)+" times older than you.");
        
        System.out.println("\nEnough about me. What's your favourite language? (just don't say Python)");
        String favlang = sc.next();
        System.out.println("\n"+favlang+", that's great! Nice chatting with you "+name+". I have to log off now. See ya!");
    }
}
