package immutableObjects.mutableObjects;

public class Main {
    public static void main(String[] args) {
        Integer applesWrapper = 5;

        Integer applesWrapper2 = applesWrapper;
        applesWrapper2 = 10;

        System.out.println(applesWrapper);
        System.out.println(applesWrapper2);

        City city = new City("Paris", 2161000);
        City secondCity = city;            // DANGEROUS

        city.setPopulation(2261000);
        secondCity.setPopulation(2263400);
        city.setPopulation(2163400);
        secondCity.setPopulation(2443400);

    }
}