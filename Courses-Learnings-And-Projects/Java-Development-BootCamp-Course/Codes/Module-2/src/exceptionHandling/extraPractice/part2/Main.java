package exceptionHandling.extraPractice.part2;

public class Main {
    public static void main(String[] args) {
        Magazine magazine1 = new Magazine("Magazine 1", "Publisher 1", 1, 2020);
        Magazine magazine2 = new Magazine("Magazine 2", "Publisher 2", 2, 2021);

        MagazineLibrary library = new MagazineLibrary();

        library.addMagazine(magazine1);
        library.addMagazine(magazine2);

        Magazine retrievedMagazine = library.getMagazine(0);
        System.out.println(retrievedMagazine.getTitle());

        Magazine newMagazine = new Magazine("Magazine 3", "Publisher 3", 3, 2022);
        library.setMagazine(newMagazine, 0);

        retrievedMagazine = library.getMagazine(0);
        System.out.println(retrievedMagazine.getTitle());
    }
}
