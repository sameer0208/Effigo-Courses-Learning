package exceptionHandling.extraPractice.part1;

public class Main {
    public static void main(String[] args) {
        Magazine magazine1 = new Magazine("Magazine 1", "Publisher 1", 1, 2020);
        System.out.println(magazine1.getTitle());

         magazine1.setTitle("");

         magazine1.setPublisher("");

         magazine1.setIssueNumber(-1);

         magazine1.setPublicationYear(0);

         Magazine magazine2 = new Magazine("", "Publisher 2", 2, 2020);
    }
}
